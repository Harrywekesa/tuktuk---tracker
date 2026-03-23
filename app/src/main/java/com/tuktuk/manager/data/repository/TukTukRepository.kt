package com.tuktuk.manager.data.repository

import com.tuktuk.manager.data.local.TukTukDatabase
import com.tuktuk.manager.data.local.entity.AppSettings
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.local.entity.Expense
import com.tuktuk.manager.data.model.MonthlyStats
import com.tuktuk.manager.data.model.WeeklyStats
import com.tuktuk.manager.data.remote.FirebaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Locale

class TukTukRepository(
    private val db: TukTukDatabase,
    private val firebaseRepo: FirebaseRepository
) {
    private val entryDao = db.dailyEntryDao()
    private val expenseDao = db.expenseDao()
    private val settingsDao = db.settingsDao()

    // ── Daily Entries ──────────────────────────────────────────────────────
    fun getAllEntries(): Flow<List<DailyEntry>> = entryDao.getAllEntries()

    fun getEntriesForMonth(yearMonth: String): Flow<List<DailyEntry>> =
        entryDao.getEntriesForMonth(yearMonth)

    fun getTodayEntry(today: String): Flow<DailyEntry?> =
        entryDao.getTodayEntry(today)

    fun getLastSevenEntries(): Flow<List<DailyEntry>> =
        entryDao.getLastSevenEntries()

    suspend fun getEntryByDate(date: String): DailyEntry? =
        entryDao.getEntryByDate(date)

    suspend fun saveEntry(entry: DailyEntry): Result<Long> {
        return try {
            val id = entryDao.insert(entry)
            // Attempt Firebase sync
            firebaseRepo.syncEntry(entry.copy(id = id))
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateEntry(entry: DailyEntry): Result<Unit> {
        return try {
            entryDao.update(entry.copy(updatedAt = System.currentTimeMillis(), isSynced = false))
            firebaseRepo.syncEntry(entry)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteEntry(entry: DailyEntry): Result<Unit> {
        return try {
            entryDao.delete(entry)
            firebaseRepo.deleteEntry(entry)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ── Expenses ───────────────────────────────────────────────────────────
    fun getAllExpenses(): Flow<List<Expense>> = expenseDao.getAllExpenses()

    fun getExpensesForMonth(yearMonth: String): Flow<List<Expense>> =
        expenseDao.getExpensesForMonth(yearMonth)

    suspend fun saveExpense(expense: Expense): Result<Long> {
        return try {
            val id = expenseDao.insert(expense)
            firebaseRepo.syncExpense(expense.copy(id = id))
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateExpense(expense: Expense): Result<Unit> {
        return try {
            expenseDao.update(expense.copy(updatedAt = System.currentTimeMillis()))
            firebaseRepo.syncExpense(expense)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteExpense(expense: Expense): Result<Unit> {
        return try {
            expenseDao.delete(expense)
            firebaseRepo.deleteExpense(expense)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ── Settings ───────────────────────────────────────────────────────────
    fun getSettings(): Flow<AppSettings?> = settingsDao.getSettings()

    suspend fun saveSettings(settings: AppSettings) {
        settingsDao.update(settings)
    }

    // ── Monthly Stats (computed) ───────────────────────────────────────────
    suspend fun getMonthlyStats(yearMonth: String): MonthlyStats {
        val entries = entryDao.getEntriesForMonthOnce(yearMonth)
        val expenses = expenseDao.getExpensesForMonthOnce(yearMonth)
        val settings = settingsDao.getSettingsOnce() ?: AppSettings()

        val totalGross = entries.sumOf { it.grossIncome }
        val totalFuel = entries.sumOf { it.actualFuelCost }
        val totalNet = entries.sumOf { it.netProfit }
        val totalOwner = entries.sumOf { it.ownerSalary }
        val totalRider = entries.sumOf { it.riderPay }
        val totalMaint = entries.sumOf { it.maintenanceSave }
        val totalBiz = entries.sumOf { it.businessProfit }
        val totalBank = entries.sumOf { it.bankDeposit }
        val totalKm = entries.sumOf { it.businessKm }
        val totalExpenses = expenses.sumOf { it.cost }
        val daysWorked = entries.size
        val daysHit = entries.count { it.grossIncome >= settings.dailyGrossTarget }
        val daysBelow = entries.count { it.grossIncome < settings.minimumGross }
        val avgGross = if (daysWorked > 0) totalGross / daysWorked else 0.0
        val avgFuelPct = if (entries.isNotEmpty()) entries.map { it.fuelPercentage }.average() else 0.0
        val kmEntries = entries.filter { it.businessKm > 0 }
        val avgIncomePkm = if (kmEntries.isNotEmpty()) kmEntries.map { it.incomePerKm }.average() else 0.0
        val avgFuelPkm = if (kmEntries.isNotEmpty()) kmEntries.map { it.fuelPerKm }.average() else 0.0
        val best = entries.maxOfOrNull { it.grossIncome } ?: 0.0
        val worst = entries.filter { it.grossIncome > 0 }.minOfOrNull { it.grossIncome } ?: 0.0
        val fuelGood = entries.count { it.fuelStatus == DailyEntry.FuelStatus.GOOD }
        val fuelHigh = entries.count { it.fuelStatus == DailyEntry.FuelStatus.HIGH }
        val fuelAlert = entries.count { it.fuelStatus == DailyEntry.FuelStatus.ALERT }

        return MonthlyStats(
            yearMonth = yearMonth,
            totalGross = totalGross,
            totalFuel = totalFuel,
            totalNetProfit = totalNet,
            totalOwnerSalary = totalOwner,
            totalRiderPay = totalRider,
            totalMaintenanceSave = totalMaint,
            totalBusinessProfit = totalBiz,
            totalBankDeposits = totalBank,
            totalExpenses = totalExpenses,
            totalBusinessKm = totalKm,
            daysWorked = daysWorked,
            daysHitTarget = daysHit,
            daysBelowMinimum = daysBelow,
            avgDailyGross = avgGross,
            avgFuelPercentage = avgFuelPct,
            avgIncomePerKm = avgIncomePkm,
            avgFuelPerKm = avgFuelPkm,
            bestDay = best,
            worstDay = worst,
            fuelGoodDays = fuelGood,
            fuelHighDays = fuelHigh,
            fuelAlertDays = fuelAlert
        )
    }

    suspend fun getWeeklyStats(yearMonth: String): List<WeeklyStats> {
        val entries = entryDao.getEntriesForMonthOnce(yearMonth)
        val weekField = WeekFields.of(Locale.getDefault()).weekOfMonth()
        val grouped = entries.groupBy {
            val date = LocalDate.parse(it.date, DateTimeFormatter.ISO_DATE)
            date.get(weekField)
        }
        return grouped.entries.sortedBy { it.key }.mapIndexed { i, (week, weekEntries) ->
            val dates = weekEntries.map { LocalDate.parse(it.date, DateTimeFormatter.ISO_DATE) }
            WeeklyStats(
                weekLabel = "Week $week",
                startDate = dates.minOrNull()?.toString() ?: "",
                endDate = dates.maxOrNull()?.toString() ?: "",
                totalGross = weekEntries.sumOf { it.grossIncome },
                totalFuel = weekEntries.sumOf { it.actualFuelCost },
                totalNetProfit = weekEntries.sumOf { it.netProfit },
                daysWorked = weekEntries.size
            )
        }
    }

    // ── Background Sync ────────────────────────────────────────────────────
    suspend fun syncUnsynced() {
        val unsyncedEntries = entryDao.getUnsyncedEntries()
        unsyncedEntries.forEach { entry ->
            try {
                firebaseRepo.syncEntry(entry)
                entryDao.markSynced(entry.id)
            } catch (_: Exception) { }
        }
        val unsyncedExpenses = expenseDao.getUnsyncedExpenses()
        unsyncedExpenses.forEach { expense ->
            try {
                firebaseRepo.syncExpense(expense)
                expenseDao.markSynced(expense.id)
            } catch (_: Exception) { }
        }
    }
}
