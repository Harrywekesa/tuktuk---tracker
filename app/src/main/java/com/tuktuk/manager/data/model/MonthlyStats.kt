package com.tuktuk.manager.data.model

data class MonthlyStats(
    val yearMonth: String,          // "2026-03"
    val totalGross: Double = 0.0,
    val totalFuel: Double = 0.0,
    val totalNetProfit: Double = 0.0,
    val totalOwnerSalary: Double = 0.0,
    val totalRiderPay: Double = 0.0,
    val totalMaintenanceSave: Double = 0.0,
    val totalBusinessProfit: Double = 0.0,
    val totalBankDeposits: Double = 0.0,
    val totalExpenses: Double = 0.0,
    val totalBusinessKm: Double = 0.0,
    val daysWorked: Int = 0,
    val daysHitTarget: Int = 0,
    val daysBelowMinimum: Int = 0,
    val avgDailyGross: Double = 0.0,
    val avgFuelPercentage: Double = 0.0,
    val avgIncomePerKm: Double = 0.0,
    val avgFuelPerKm: Double = 0.0,
    val bestDay: Double = 0.0,
    val worstDay: Double = 0.0,
    val fuelGoodDays: Int = 0,
    val fuelHighDays: Int = 0,
    val fuelAlertDays: Int = 0
) {
    val netMaintenanceBalance: Double
        get() = totalMaintenanceSave - totalExpenses

    val currentBankBalance: Double
        get() = totalBankDeposits

    val fuelAsPercentOfGross: Double
        get() = if (totalGross > 0) totalFuel / totalGross else 0.0

    val targetHitRate: Double
        get() = if (daysWorked > 0) daysHitTarget.toDouble() / daysWorked else 0.0

    val profitMargin: Double
        get() = if (totalGross > 0) totalNetProfit / totalGross else 0.0
}

data class DashboardSummary(
    val monthlyStats: MonthlyStats,
    val todayEntry: com.tuktuk.manager.data.local.entity.DailyEntry?,
    val recentEntries: List<com.tuktuk.manager.data.local.entity.DailyEntry>,
    val settings: com.tuktuk.manager.data.local.entity.AppSettings
)
