package com.tuktuk.manager.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuktuk.manager.data.local.entity.AppSettings
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.model.MonthlyStats
import com.tuktuk.manager.data.repository.TukTukRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DashboardViewModel(private val repo: TukTukRepository) : ViewModel() {

    private val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
    private val currentYearMonth = today.substring(0, 7)

    // Today's entry
    val todayEntry: StateFlow<DailyEntry?> = repo.getTodayEntry(today)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Recent entries (last 7)
    val recentEntries: StateFlow<List<DailyEntry>> = repo.getLastSevenEntries()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Settings
    val settings: StateFlow<AppSettings?> = repo.getSettings()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Monthly stats
    private val _monthlyStats = MutableStateFlow<MonthlyStats?>(null)
    val monthlyStats: StateFlow<MonthlyStats?> = _monthlyStats.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadMonthlyStats()
        // Reload when entries change
        viewModelScope.launch {
            repo.getEntriesForMonth(currentYearMonth).collect {
                loadMonthlyStats()
            }
        }
    }

    private fun loadMonthlyStats() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _monthlyStats.value = repo.getMonthlyStats(currentYearMonth)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() = loadMonthlyStats()
}
