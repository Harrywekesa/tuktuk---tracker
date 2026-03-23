package com.tuktuk.manager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuktuk.manager.R
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.model.MonthlyStats
import com.tuktuk.manager.databinding.FragmentDashboardBinding
import com.tuktuk.manager.ui.adapter.RecentEntryAdapter
import com.tuktuk.manager.util.CurrencyFormatter
import com.tuktuk.manager.util.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private lateinit var recentAdapter: RecentEntryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupClickListeners()
        observeData()
        setGreeting()
    }

    private fun setGreeting() {
        val hour = LocalTime.now().hour
        val greeting = when {
            hour < 12 -> "Good morning"
            hour < 17 -> "Good afternoon"
            else -> "Good evening"
        }
        val monthLabel = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("MMMM yyyy"))
        binding.tvGreeting.text = greeting
        binding.tvMonthLabel.text = monthLabel
    }

    private fun setupRecyclerView() {
        recentAdapter = RecentEntryAdapter { entry ->
            val action = DashboardFragmentDirections
                .actionDashboardToEntryDetail(entry.id)
            findNavController().navigate(action)
        }
        binding.rvRecentEntries.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recentAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun setupClickListeners() {
        binding.fabLogTrip.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_dailyEntry)
        }
        binding.cardTodayEmpty.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_dailyEntry)
        }
        binding.btnViewAllEntries.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_history)
        }
        binding.cardBankBalance.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_analytics)
        }
        binding.cardFuelEfficiency.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_analytics)
        }
        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_settings)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collectLatest { loading ->
                binding.swipeRefresh.isRefreshing = loading
                binding.shimmerLayout.isVisible = loading && viewModel.monthlyStats.value == null
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.monthlyStats.collectLatest { stats ->
                stats?.let { bindMonthlyStats(it) }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todayEntry.collectLatest { entry ->
                bindTodayEntry(entry)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recentEntries.collectLatest { entries ->
                recentAdapter.submitList(entries)
                binding.tvNoRecentData.isVisible = entries.isEmpty()
                binding.rvRecentEntries.isVisible = entries.isNotEmpty()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.settings.collectLatest { settings ->
                settings?.let {
                    binding.tvOwnerName.text = it.ownerName
                }
            }
        }
    }

    private fun bindMonthlyStats(stats: MonthlyStats) {
        with(binding) {
            // Main KPIs
            tvTotalGross.text = CurrencyFormatter.format(stats.totalGross)
            tvNetProfit.text = CurrencyFormatter.format(stats.totalNetProfit)
            tvBankBalance.text = CurrencyFormatter.format(stats.currentBankBalance)
            tvDaysWorked.text = stats.daysWorked.toString()

            // 4-way split cards
            tvOwnerSalary.text = CurrencyFormatter.format(stats.totalOwnerSalary)
            tvRiderPay.text = CurrencyFormatter.format(stats.totalRiderPay)
            tvMaintenance.text = CurrencyFormatter.format(stats.netMaintenanceBalance)
            tvBizProfit.text = CurrencyFormatter.format(stats.totalBusinessProfit)

            // Fuel efficiency
            tvAvgIncomePkm.text = CurrencyFormatter.formatPerKm(stats.avgIncomePerKm)
            tvAvgFuelPkm.text = CurrencyFormatter.formatPerKm(stats.avgFuelPerKm)
            tvFuelPct.text = "%.1f%%".format(stats.fuelAsPercentOfGross * 100)

            // Fuel status badge
            val (fuelColor, fuelText) = when {
                stats.avgFuelPercentage < 0.25 -> Pair(
                    requireContext().getColor(R.color.status_good), "✅ GOOD"
                )
                stats.avgFuelPercentage <= 0.35 -> Pair(
                    requireContext().getColor(R.color.status_warning), "⚠️ HIGH"
                )
                else -> Pair(
                    requireContext().getColor(R.color.status_alert), "🔴 ALERT"
                )
            }
            tvFuelStatus.text = fuelText
            tvFuelStatus.setTextColor(fuelColor)

            // Target progress
            val settings = viewModel.settings.value
            val target = settings?.dailyGrossTarget ?: 3000.0
            val avgProgress = if (target > 0) (stats.avgDailyGross / target * 100).toInt().coerceIn(0, 100) else 0
            progressTarget.progress = avgProgress
            tvTargetHitRate.text = "${stats.daysHitTarget} / ${stats.daysWorked} days"

            // Performance chips
            tvBestDay.text = CurrencyFormatter.format(stats.bestDay)
            tvWorstDay.text = CurrencyFormatter.format(stats.worstDay)
        }
    }

    private fun bindTodayEntry(entry: DailyEntry?) {
        binding.cardTodayEntry.isVisible = entry != null
        binding.cardTodayEmpty.isVisible = entry == null

        entry?.let {
            binding.tvTodayGross.text = CurrencyFormatter.format(it.grossIncome)
            binding.tvTodayNet.text = CurrencyFormatter.format(it.netProfit)
            binding.tvTodayBankDeposit.text = CurrencyFormatter.format(it.bankDeposit)
            binding.tvTodayFuelStatus.text = when (it.fuelStatus) {
                DailyEntry.FuelStatus.GOOD -> "✅ GOOD"
                DailyEntry.FuelStatus.HIGH -> "⚠️ HIGH"
                DailyEntry.FuelStatus.ALERT -> "🔴 ALERT"
                DailyEntry.FuelStatus.NONE -> "—"
            }

            // Target badge
            val targetColor = when {
                it.meetsTarget -> requireContext().getColor(R.color.status_good)
                it.aboveMinimum -> requireContext().getColor(R.color.status_warning)
                else -> requireContext().getColor(R.color.status_alert)
            }
            binding.tvTodayTargetStatus.setTextColor(targetColor)
            binding.tvTodayTargetStatus.text = when {
                it.meetsTarget -> "✅ Target Hit"
                it.aboveMinimum -> "⚠️ Above Min"
                else -> "🔴 Below Min"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
