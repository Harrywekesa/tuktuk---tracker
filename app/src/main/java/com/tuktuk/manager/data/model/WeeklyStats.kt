package com.tuktuk.manager.data.model

data class WeeklyStats(
    val weekLabel: String,
    val startDate: String,
    val endDate: String,
    val totalGross: Double,
    val totalFuel: Double,
    val totalNetProfit: Double,
    val daysWorked: Int
)
