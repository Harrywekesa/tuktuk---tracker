package com.tuktuk.manager.ui.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.tuktuk.manager.data.local.entity.Expense
import com.tuktuk.manager.data.model.MonthlyStats
import com.tuktuk.manager.data.repository.TukTukRepository
import com.tuktuk.manager.databinding.FragmentExpensesBinding
import com.tuktuk.manager.databinding.FragmentAddExpenseBinding
import com.tuktuk.manager.ui.adapter.ExpenseAdapter
import com.tuktuk.manager.util.CurrencyFormatter
import com.tuktuk.manager.util.DateUtils
import com.tuktuk.manager.util.ViewModelFactory
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// ── ViewModel ──────────────────────────────────────────────────────────────
class ExpensesViewModel(private val repo: TukTukRepository) : ViewModel() {

    private val _selectedYearMonth = MutableStateFlow(DateUtils.currentYearMonth())

    val expenses: StateFlow<List<Expense>> = _selectedYearMonth.flatMapLatest { ym ->
        repo.getExpensesForMonth(ym)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _monthlyStats = MutableStateFlow<MonthlyStats?>(null)
    val monthlyStats: StateFlow<MonthlyStats?> = _monthlyStats.asStateFlow()

    val selectedYearMonth: StateFlow<String> = _selectedYearMonth.asStateFlow()

    private val _event = MutableSharedFlow<String>()
    val event: SharedFlow<String> = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            _selectedYearMonth.collectLatest { ym ->
                _monthlyStats.value = repo.getMonthlyStats(ym)
            }
        }
    }

    fun previousMonth() {
        val parts = _selectedYearMonth.value.split("-")
        if (parts.size == 2) {
            val y = parts[0].toInt()
            val m = parts[1].toInt()
            _selectedYearMonth.value = "%04d-%02d".format(if (m == 1) y - 1 else y, if (m == 1) 12 else m - 1)
        }
    }
    fun nextMonth() {
        val parts = _selectedYearMonth.value.split("-")
        if (parts.size == 2) {
            val y = parts[0].toInt()
            val m = parts[1].toInt()
            _selectedYearMonth.value = "%04d-%02d".format(if (m == 12) y + 1 else y, if (m == 12) 1 else m + 1)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repo.deleteExpense(expense)
            _event.emit("Expense deleted")
        }
    }
}

class AddExpenseViewModel(private val repo: TukTukRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AddExpenseState())
    val uiState: StateFlow<AddExpenseState> = _uiState.asStateFlow()

    fun loadExpense(expenseId: Long) {
        if (expenseId < 0) {
            _uiState.update { it.copy(date = DateUtils.today()) }
            return
        }
        viewModelScope.launch {
            val expense = repo.getAllExpenses().first().find { it.id == expenseId } ?: return@launch
            _uiState.update {
                it.copy(
                    editingExpense = expense, date = expense.date, category = expense.category,
                    description = expense.description, mechanic = expense.mechanicVendor,
                    cost = expense.cost, paidFrom = expense.paidFrom,
                    notes = expense.receiptNotes, isEditMode = true
                )
            }
        }
    }

    fun setDate(d: String) = _uiState.update { it.copy(date = d) }
    fun setCategory(c: String) = _uiState.update { it.copy(category = c) }
    fun setDescription(d: String) = _uiState.update { it.copy(description = d) }
    fun setMechanic(m: String) = _uiState.update { it.copy(mechanic = m) }
    fun setCost(c: Double) = _uiState.update { it.copy(cost = c) }
    fun setPaidFrom(p: String) = _uiState.update { it.copy(paidFrom = p) }
    fun setNotes(n: String) = _uiState.update { it.copy(notes = n) }

    fun save() {
        val s = _uiState.value
        if (s.category.isEmpty()) { _uiState.update { it.copy(error = "Please select a category") }; return }
        if (s.cost <= 0) { _uiState.update { it.copy(error = "Please enter a cost") }; return }
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            try {
                if (s.isEditMode && s.editingExpense != null) {
                    repo.updateExpense(s.editingExpense.copy(
                        date=s.date, category=s.category, description=s.description,
                        mechanicVendor=s.mechanic, cost=s.cost, paidFrom=s.paidFrom, receiptNotes=s.notes
                    ))
                } else {
                    repo.saveExpense(Expense(
                        date=s.date, category=s.category, description=s.description,
                        mechanicVendor=s.mechanic, cost=s.cost, paidFrom=s.paidFrom, receiptNotes=s.notes
                    ))
                }
                _uiState.update { it.copy(isSaving = false, savedSuccessfully = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isSaving = false, error = e.message) }
            }
        }
    }

    fun clearError() = _uiState.update { it.copy(error = null) }
}

data class AddExpenseState(
    val editingExpense: Expense? = null, val date: String = "", val category: String = "",
    val description: String = "", val mechanic: String = "", val cost: Double = 0.0,
    val paidFrom: String = "Maintenance Fund", val notes: String = "", val isEditMode: Boolean = false,
    val isSaving: Boolean = false, val savedSuccessfully: Boolean = false, val error: String? = null
)

// ── Expenses List Fragment ─────────────────────────────────────────────────
class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExpensesViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var adapter: ExpenseAdapter

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentExpensesBinding.inflate(i, c, false); return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupClickListeners()
        observeData()
    }

    private fun setupRecyclerView() {
        adapter = ExpenseAdapter { expense ->
            val action = ExpensesFragmentDirections.actionExpensesToAddExpense(expense.id)
            findNavController().navigate(action)
        }
        binding.rvExpenses.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExpenses.adapter = adapter

        // Swipe to delete
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder) = false
            override fun onSwiped(vh: RecyclerView.ViewHolder, dir: Int) {
                val expense = adapter.currentList[vh.adapterPosition]
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete Expense")
                    .setMessage("Delete ${expense.category} — ${CurrencyFormatter.format(expense.cost)}?")
                    .setPositiveButton("Delete") { _, _ -> viewModel.deleteExpense(expense) }
                    .setNegativeButton("Cancel") { _, _ -> adapter.notifyItemChanged(vh.adapterPosition) }
                    .show()
            }
        }).attachToRecyclerView(binding.rvExpenses)
    }

    private fun setupClickListeners() {
        binding.fabAddExpense.setOnClickListener {
            findNavController().navigate(ExpensesFragmentDirections.actionExpensesToAddExpense(-1L))
        }
        binding.btnPrevMonth.setOnClickListener { viewModel.previousMonth() }
        binding.btnNextMonth.setOnClickListener { viewModel.nextMonth() }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedYearMonth.collectLatest { ym ->
                binding.tvExpenseMonth.text = DateUtils.monthLabel(ym)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.expenses.collectLatest { expenses ->
                adapter.submitList(expenses)
                binding.tvNoExpenses.isVisible = expenses.isEmpty()
                binding.rvExpenses.isVisible = expenses.isNotEmpty()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.monthlyStats.collectLatest { stats ->
                stats?.let {
                    binding.tvTotalExpenses.text = CurrencyFormatter.format(it.totalExpenses)
                    binding.tvMaintBalance.text = CurrencyFormatter.format(it.netMaintenanceBalance)
                    val balanceColor = if (it.netMaintenanceBalance >= 0)
                        requireContext().getColor(com.tuktuk.manager.R.color.status_good)
                    else requireContext().getColor(com.tuktuk.manager.R.color.status_alert)
                    binding.tvMaintBalance.setTextColor(balanceColor)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.event.collect { msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}

// ── Add Expense Fragment ───────────────────────────────────────────────────
class AddExpenseFragment : Fragment() {

    private var _binding: FragmentAddExpenseBinding? = null
    private val binding get() = _binding!!
    private val args: AddExpenseFragmentArgs by navArgs()
    private val viewModel: AddExpenseViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View {
        _binding = FragmentAddExpenseBinding.inflate(i, c, false); return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadExpense(args.expenseId)
        setupDropdowns()
        setupDatePicker()
        setupInputListeners()
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.btnSaveExpense.setOnClickListener { viewModel.save() }
        observeState()
    }

    private fun setupDropdowns() {
        val catAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_dropdown_item_1line, Expense.CATEGORIES)
        binding.actvCategory.setAdapter(catAdapter)
        binding.actvCategory.setOnItemClickListener { _, _, i, _ ->
            viewModel.setCategory(Expense.CATEGORIES[i])
        }

        val paidAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_dropdown_item_1line, Expense.PAID_FROM_OPTIONS)
        binding.actvPaidFrom.setAdapter(paidAdapter)
        binding.actvPaidFrom.setOnItemClickListener { _, _, i, _ ->
            viewModel.setPaidFrom(Expense.PAID_FROM_OPTIONS[i])
        }
    }

    private fun setupDatePicker() {
        binding.etExpenseDate.setOnClickListener {
            val cal = java.util.Calendar.getInstance()
            android.app.DatePickerDialog(requireContext(), { _, y, m, d ->
                viewModel.setDate("%04d-%02d-%02d".format(y, m+1, d))
            }, cal.get(java.util.Calendar.YEAR), cal.get(java.util.Calendar.MONTH),
                cal.get(java.util.Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setupInputListeners() {
        binding.etDescription.addTextChangedListener(simpleWatcher { viewModel.setDescription(it) })
        binding.etMechanic.addTextChangedListener(simpleWatcher { viewModel.setMechanic(it) })
        binding.etCost.addTextChangedListener(simpleWatcher { viewModel.setCost(it.toDoubleOrNull() ?: 0.0) })
        binding.etExpenseNotes.addTextChangedListener(simpleWatcher { viewModel.setNotes(it) })
    }

    private fun simpleWatcher(block: (String) -> Unit) = object : android.text.TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: android.text.Editable?) { block(s?.toString()?.trim() ?: "") }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { s ->
                if (s.date.isNotEmpty() && binding.etExpenseDate.text.isNullOrEmpty())
                    binding.etExpenseDate.setText(s.date)
                
                if (s.isEditMode) {
                    if (binding.etDescription.text.isNullOrEmpty() && s.description.isNotEmpty())
                        binding.etDescription.setText(s.description)
                    if (binding.etMechanic.text.isNullOrEmpty() && s.mechanic.isNotEmpty())
                        binding.etMechanic.setText(s.mechanic)
                    if (binding.etCost.text.isNullOrEmpty() && s.cost > 0)
                        binding.etCost.setText(s.cost.toString())
                    if (binding.etExpenseNotes.text.isNullOrEmpty() && s.notes.isNotEmpty())
                        binding.etExpenseNotes.setText(s.notes)
                    if (binding.actvCategory.text.isNullOrEmpty() && s.category.isNotEmpty())
                        binding.actvCategory.setText(s.category, false)
                    if (binding.actvPaidFrom.text.toString() == "Maintenance Fund" && s.paidFrom.isNotEmpty() && s.paidFrom != "Maintenance Fund")
                        binding.actvPaidFrom.setText(s.paidFrom, false)
                }

                binding.toolbar.title = if (s.isEditMode) "Edit Expense" else "Add Expense"
                binding.btnSaveExpense.text = if (s.isSaving) "Saving…"
                    else if (s.isEditMode) "Update" else "Save Expense"
                binding.btnSaveExpense.isEnabled = !s.isSaving
                if (s.savedSuccessfully) findNavController().navigateUp()
                s.error?.let {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    viewModel.clearError()
                }
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
