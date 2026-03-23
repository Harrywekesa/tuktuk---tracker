package com.tuktuk.manager.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.repository.TukTukRepository
import com.tuktuk.manager.databinding.FragmentHistoryBinding
import com.tuktuk.manager.databinding.FragmentEntryDetailBinding
import com.tuktuk.manager.ui.adapter.DailyEntryAdapter
import com.tuktuk.manager.util.CurrencyFormatter
import com.tuktuk.manager.util.DateUtils
import com.tuktuk.manager.util.ViewModelFactory
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// ── ViewModel ──────────────────────────────────────────────────────────────
class HistoryViewModel(private val repo: TukTukRepository) : ViewModel() {

    private val _selectedYearMonth = MutableStateFlow(DateUtils.currentYearMonth())
    val selectedYearMonth: StateFlow<String> = _selectedYearMonth.asStateFlow()

    val entries: StateFlow<List<DailyEntry>> = _selectedYearMonth.flatMapLatest { ym ->
        repo.getEntriesForMonth(ym)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun previousMonth() {
        val (y, m) = _selectedYearMonth.value.split("-").map { it.toInt() }
        _selectedYearMonth.value = "%04d-%02d".format(if (m==1) y-1 else y, if (m==1) 12 else m-1)
    }
    fun nextMonth() {
        val (y, m) = _selectedYearMonth.value.split("-").map { it.toInt() }
        _selectedYearMonth.value = "%04d-%02d".format(if (m==12) y+1 else y, if (m==12) 1 else m+1)
    }

    suspend fun getEntry(id: Long): DailyEntry? =
        entries.value.find { it.id == id }
            ?: repo.getAllEntries().first().find { it.id == id }
}

// ── History Fragment ────────────────────────────────────────────────────────
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var adapter: DailyEntryAdapter

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentHistoryBinding.inflate(i, c, false); return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DailyEntryAdapter(
            onItemClick = { entry ->
                val action = HistoryFragmentDirections.actionHistoryToEntryDetail(entry.id)
                findNavController().navigate(action)
            },
            onEditClick = { entry ->
                val action = HistoryFragmentDirections.actionHistoryToDailyEntry(entry.id)
                findNavController().navigate(action)
            }
        )
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.adapter = adapter

        binding.btnPrevMonth.setOnClickListener { viewModel.previousMonth() }
        binding.btnNextMonth.setOnClickListener { viewModel.nextMonth() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedYearMonth.collectLatest { ym ->
                binding.tvHistoryMonth.text = DateUtils.monthLabel(ym)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.entries.collectLatest { entries ->
                adapter.submitList(entries)
                binding.tvNoEntries.isVisible = entries.isEmpty()
                binding.rvHistory.isVisible = entries.isNotEmpty()

                // Summary bar
                if (entries.isNotEmpty()) {
                    val totalGross = entries.sumOf { it.grossIncome }
                    val totalNet = entries.sumOf { it.netProfit }
                    val totalBank = entries.sumOf { it.bankDeposit }
                    binding.tvHistorySummaryGross.text = CurrencyFormatter.format(totalGross)
                    binding.tvHistorySummaryNet.text = CurrencyFormatter.format(totalNet)
                    binding.tvHistorySummaryBank.text = CurrencyFormatter.format(totalBank)
                    binding.tvHistoryDays.text = "${entries.size} days"
                }
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}

// ── Entry Detail Fragment ───────────────────────────────────────────────────
class EntryDetailFragment : Fragment() {

    private var _binding: FragmentEntryDetailBinding? = null
    private val binding get() = _binding!!
    private val args: EntryDetailFragmentArgs by navArgs()
    private val viewModel: HistoryViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentEntryDetailBinding.inflate(i, c, false); return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        viewLifecycleOwner.lifecycleScope.launch {
            val entry = viewModel.getEntry(args.entryId) ?: return@launch
            bindEntry(entry)
            binding.btnEditEntry.setOnClickListener {
                val action = EntryDetailFragmentDirections.actionEntryDetailToDailyEntry(entry.id)
                findNavController().navigate(action)
            }
        }
    }

    private fun bindEntry(e: DailyEntry) {
        binding.toolbar.title = DateUtils.toDisplay(e.date)
        binding.tvDetailDate.text = DateUtils.toDisplay(e.date)
        binding.tvDetailTimeIn.text = e.timeIn.ifEmpty { "—" }
        binding.tvDetailTimeOut.text = e.timeOut.ifEmpty { "—" }

        // Gross & Fuel
        binding.tvDetailGross.text = CurrencyFormatter.format(e.grossIncome)
        binding.tvDetailFuel.text = CurrencyFormatter.format(e.actualFuelCost)
        binding.tvDetailFuelPct.text = CurrencyFormatter.formatPercent(e.fuelPercentage)
        binding.tvDetailNet.text = CurrencyFormatter.format(e.netProfit)

        // Split
        binding.tvDetailOwner.text = CurrencyFormatter.format(e.ownerSalary)
        binding.tvDetailRider.text = CurrencyFormatter.format(e.riderPay)
        binding.tvDetailMaint.text = CurrencyFormatter.format(e.maintenanceSave)
        binding.tvDetailBiz.text = CurrencyFormatter.format(e.businessProfit)

        // Bank
        binding.tvDetailFloat.text = CurrencyFormatter.format(200.0)
        binding.tvDetailBankDeposit.text = CurrencyFormatter.format(e.bankDeposit)

        // KM
        if (e.businessKm > 0) {
            binding.cardKmDetails.isVisible = true
            binding.tvDetailStartOdo.text = "${e.startOdometer.toInt()} KM"
            binding.tvDetailEndOdo.text = "${e.endOdometer.toInt()} KM"
            binding.tvDetailBusinessKm.text = CurrencyFormatter.formatKm(e.businessKm)
            binding.tvDetailIncomePkm.text = CurrencyFormatter.formatPerKm(e.incomePerKm)
            binding.tvDetailFuelPkm.text = CurrencyFormatter.formatPerKm(e.fuelPerKm)
        } else {
            binding.cardKmDetails.isVisible = false
        }

        // Status
        val (statusColor, statusText) = when {
            e.meetsTarget -> Pair(requireContext().getColor(com.tuktuk.manager.R.color.status_good), "✅ Target Hit")
            e.aboveMinimum -> Pair(requireContext().getColor(com.tuktuk.manager.R.color.status_warning), "⚠️ Above Minimum")
            else -> Pair(requireContext().getColor(com.tuktuk.manager.R.color.status_alert), "🔴 Below Minimum")
        }
        binding.tvDetailTargetStatus.text = statusText
        binding.tvDetailTargetStatus.setTextColor(statusColor)

        val (fuelColor, fuelText) = when (e.fuelStatus) {
            DailyEntry.FuelStatus.GOOD -> Pair(requireContext().getColor(com.tuktuk.manager.R.color.status_good), "✅ GOOD")
            DailyEntry.FuelStatus.HIGH -> Pair(requireContext().getColor(com.tuktuk.manager.R.color.status_warning), "⚠️ HIGH")
            DailyEntry.FuelStatus.ALERT -> Pair(requireContext().getColor(com.tuktuk.manager.R.color.status_alert), "🔴 ALERT")
            else -> Pair(requireContext().getColor(com.tuktuk.manager.R.color.dark_hint), "—")
        }
        binding.tvDetailFuelStatus.text = fuelText
        binding.tvDetailFuelStatus.setTextColor(fuelColor)

        // Notes
        binding.tvDetailNotes.isVisible = e.notes.isNotEmpty()
        binding.tvDetailNotes.text = e.notes
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
