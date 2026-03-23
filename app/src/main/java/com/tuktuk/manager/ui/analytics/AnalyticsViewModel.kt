package com.tuktuk.manager.ui.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.model.MonthlyStats
import com.tuktuk.manager.data.model.WeeklyStats
import com.tuktuk.manager.data.repository.TukTukRepository
import com.tuktuk.manager.util.DateUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AnalyticsViewModel(private val repo: TukTukRepository) : ViewModel() {

    private val _selectedYearMonth = MutableStateFlow(DateUtils.currentYearMonth())
    val selectedYearMonth: StateFlow<String> = _selectedYearMonth.asStateFlow()

    private val _monthlyStats = MutableStateFlow<MonthlyStats?>(null)
    val monthlyStats: StateFlow<MonthlyStats?> = _monthlyStats.asStateFlow()

    private val _weeklyStats = MutableStateFlow<List<WeeklyStats>>(emptyList())
    val weeklyStats: StateFlow<List<WeeklyStats>> = _weeklyStats.asStateFlow()

    val entries: StateFlow<List<DailyEntry>> = _selectedYearMonth.flatMapLatest { ym ->
        repo.getEntriesForMonth(ym)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            _selectedYearMonth.collectLatest { ym ->
                loadStats(ym)
            }
        }
    }

    private fun loadStats(ym: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _monthlyStats.value = repo.getMonthlyStats(ym)
            _weeklyStats.value = repo.getWeeklyStats(ym)
            _isLoading.value = false
        }
    }

    fun selectMonth(yearMonth: String) {
        _selectedYearMonth.value = yearMonth
    }

    fun previousMonth() {
        val current = _selectedYearMonth.value
        val parts = current.split("-")
        val year = parts[0].toInt()
        val month = parts[1].toInt()
        val newMonth = if (month == 1) 12 else month - 1
        val newYear = if (month == 1) year - 1 else year
        _selectedYearMonth.value = "%04d-%02d".format(newYear, newMonth)
    }

    fun nextMonth() {
        val current = _selectedYearMonth.value
        val parts = current.split("-")
        val year = parts[0].toInt()
        val month = parts[1].toInt()
        val newMonth = if (month == 12) 1 else month + 1
        val newYear = if (month == 12) year + 1 else year
        _selectedYearMonth.value = "%04d-%02d".format(newYear, newMonth)
    }
}
