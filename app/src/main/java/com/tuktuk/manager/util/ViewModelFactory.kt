package com.tuktuk.manager.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuktuk.manager.data.local.TukTukDatabase
import com.tuktuk.manager.data.remote.FirebaseRepository
import com.tuktuk.manager.data.repository.TukTukRepository
import com.tuktuk.manager.ui.analytics.AnalyticsViewModel
import com.tuktuk.manager.ui.dashboard.DashboardViewModel
import com.tuktuk.manager.ui.dailyentry.DailyEntryViewModel
import com.tuktuk.manager.ui.expenses.ExpensesViewModel
import com.tuktuk.manager.ui.history.HistoryViewModel
import com.tuktuk.manager.ui.reports.ReportsViewModel
import com.tuktuk.manager.ui.settings.SettingsViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    private val db by lazy { TukTukDatabase.getDatabase(context) }
    private val firebaseRepo by lazy { FirebaseRepository() }
    private val repo by lazy { TukTukRepository(db, firebaseRepo) }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(DashboardViewModel::class.java) ->
            DashboardViewModel(repo) as T
        modelClass.isAssignableFrom(DailyEntryViewModel::class.java) ->
            DailyEntryViewModel(repo) as T
        modelClass.isAssignableFrom(HistoryViewModel::class.java) ->
            HistoryViewModel(repo) as T
        modelClass.isAssignableFrom(AnalyticsViewModel::class.java) ->
            AnalyticsViewModel(repo) as T
        modelClass.isAssignableFrom(ExpensesViewModel::class.java) ->
            ExpensesViewModel(repo) as T
        modelClass.isAssignableFrom(com.tuktuk.manager.ui.expenses.AddExpenseViewModel::class.java) ->
            com.tuktuk.manager.ui.expenses.AddExpenseViewModel(repo) as T
        modelClass.isAssignableFrom(ReportsViewModel::class.java) ->
            ReportsViewModel(repo) as T
        modelClass.isAssignableFrom(SettingsViewModel::class.java) ->
            SettingsViewModel(repo) as T
        else -> throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
    }
}
