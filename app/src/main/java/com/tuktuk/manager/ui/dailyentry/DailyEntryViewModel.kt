package com.tuktuk.manager.ui.dailyentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.repository.TukTukRepository
import com.tuktuk.manager.util.DateUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DailyEntryViewModel(private val repo: TukTukRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DailyEntryUiState())
    val uiState: StateFlow<DailyEntryUiState> = _uiState.asStateFlow()

    // Live preview — recomputes every time gross or fuel changes
    val livePreview: StateFlow<LivePreview> = uiState.map { state ->
        val gross = state.grossIncome
        val fuel = state.fuelCost
        val net = if (gross > 0) gross - fuel else 0.0
        val each = net * 0.25
        val floatDeduction = if (state.deductFloat) 200.0 else 0.0
        val bank = each + each - floatDeduction
        val fuelPct = if (gross > 0) fuel / gross else 0.0
        val fuelStatus = when {
            gross <= 0 -> DailyEntry.FuelStatus.NONE
            fuelPct < 0.25 -> DailyEntry.FuelStatus.GOOD
            fuelPct <= 0.35 -> DailyEntry.FuelStatus.HIGH
            else -> DailyEntry.FuelStatus.ALERT
        }
        LivePreview(
            netProfit = net,
            ownerSalary = each,
            riderPay = each,
            maintenance = each,
            bizProfit = each,
            bankDeposit = bank.coerceAtLeast(0.0),
            fuelStatus = fuelStatus,
            fuelPct = fuelPct
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LivePreview())

    fun loadEntry(entryId: Long) {
        if (entryId < 0) {
            // New entry — default to today
            _uiState.update { it.copy(date = DateUtils.today(), isEditMode = false) }
            return
        }
        viewModelScope.launch {
            val entry = repo.getAllEntries().first().find { it.id == entryId } ?: return@launch
            _uiState.update {
                it.copy(
                    editingEntry = entry,
                    date = entry.date,
                    timeIn = entry.timeIn,
                    timeOut = entry.timeOut,
                    startOdo = entry.startOdometer,
                    endOdo = entry.endOdometer,
                    grossIncome = entry.grossIncome,
                    fuelCost = entry.actualFuelCost,
                    notes = entry.notes,
                    deductFloat = entry.deductFloat,
                    isEditMode = true
                )
            }
        }
    }

    fun setDate(date: String) = _uiState.update { it.copy(date = date) }
    fun setTimeIn(time: String) = _uiState.update { it.copy(timeIn = time) }
    fun setTimeOut(time: String) = _uiState.update { it.copy(timeOut = time) }
    fun setStartOdo(value: Double) = _uiState.update { it.copy(startOdo = value) }
    fun setEndOdo(value: Double) = _uiState.update { it.copy(endOdo = value) }
    fun setGross(value: Double) = _uiState.update { it.copy(grossIncome = value) }
    fun setFuel(value: Double) = _uiState.update { it.copy(fuelCost = value) }
    fun setNotes(text: String) = _uiState.update { it.copy(notes = text) }
    fun setDeductFloat(value: Boolean) = _uiState.update { it.copy(deductFloat = value) }

    fun saveEntry() {
        val state = _uiState.value
        if (state.grossIncome <= 0) {
            _uiState.update { it.copy(error = "Please enter gross income") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true, error = null) }
            try {
                if (state.isEditMode && state.editingEntry != null) {
                    repo.updateEntry(
                        state.editingEntry.copy(
                            date = state.date,
                            timeIn = state.timeIn,
                            timeOut = state.timeOut,
                            startOdometer = state.startOdo,
                            endOdometer = state.endOdo,
                            grossIncome = state.grossIncome,
                            actualFuelCost = state.fuelCost,
                            notes = state.notes,
                            deductFloat = state.deductFloat
                        )
                    )
                } else {
                    // Check for duplicate
                    val existing = repo.getEntryByDate(state.date)
                    if (existing != null) {
                        _uiState.update { it.copy(isSaving = false, error = "Entry already exists for this date") }
                        return@launch
                    }
                    repo.saveEntry(
                        DailyEntry(
                            date = state.date,
                            timeIn = state.timeIn,
                            timeOut = state.timeOut,
                            startOdometer = state.startOdo,
                            endOdometer = state.endOdo,
                            grossIncome = state.grossIncome,
                            actualFuelCost = state.fuelCost,
                            notes = state.notes,
                            deductFloat = state.deductFloat
                        )
                    )
                }
                _uiState.update { it.copy(isSaving = false, savedSuccessfully = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isSaving = false, error = e.message) }
            }
        }
    }

    fun deleteEntry() {
        val entry = _uiState.value.editingEntry ?: return
        viewModelScope.launch {
            repo.deleteEntry(entry)
            _uiState.update { it.copy(deletedSuccessfully = true) }
        }
    }

    fun clearError() = _uiState.update { it.copy(error = null) }
}

data class DailyEntryUiState(
    val editingEntry: DailyEntry? = null,
    val date: String = "",
    val timeIn: String = "",
    val timeOut: String = "",
    val startOdo: Double = 0.0,
    val endOdo: Double = 0.0,
    val grossIncome: Double = 0.0,
    val fuelCost: Double = 0.0,
    val notes: String = "",
    val deductFloat: Boolean = true,
    val isEditMode: Boolean = false,
    val isSaving: Boolean = false,
    val savedSuccessfully: Boolean = false,
    val deletedSuccessfully: Boolean = false,
    val error: String? = null
) {
    val businessKm: Double
        get() = if (startOdo > 0 && endOdo > startOdo) (endOdo - startOdo) - 10.0 else 0.0
}

data class LivePreview(
    val netProfit: Double = 0.0,
    val ownerSalary: Double = 0.0,
    val riderPay: Double = 0.0,
    val maintenance: Double = 0.0,
    val bizProfit: Double = 0.0,
    val bankDeposit: Double = 0.0,
    val fuelStatus: DailyEntry.FuelStatus = DailyEntry.FuelStatus.NONE,
    val fuelPct: Double = 0.0
)
