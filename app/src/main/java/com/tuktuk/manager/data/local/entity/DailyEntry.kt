package com.tuktuk.manager.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_entries")
data class DailyEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,           // "2026-03-01" ISO format
    val timeIn: String = "",    // "07:00"
    val timeOut: String = "",   // "18:00"
    val startOdometer: Double = 0.0,
    val endOdometer: Double = 0.0,
    val grossIncome: Double,
    val actualFuelCost: Double,
    val notes: String = "",
    val isSynced: Boolean = false,
    val deductFloat: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    // Constants
    companion object {
        const val FLOAT_AMOUNT = 200.0
        const val COMMUTE_KM = 10.0
        const val SPLIT_RATIO = 0.25
        const val DAILY_TARGET = 3000.0
        const val MIN_GROSS = 2500.0
        const val FUEL_GOOD_THRESHOLD = 0.25
        const val FUEL_HIGH_THRESHOLD = 0.35
    }

    // Computed properties
    val businessKm: Double
        get() = if (startOdometer > 0 && endOdometer > startOdometer)
            kotlin.math.max(0.0, (endOdometer - startOdometer) - COMMUTE_KM) else 0.0

    val netProfit: Double
        get() = grossIncome - actualFuelCost

    val ownerSalary: Double
        get() = netProfit * SPLIT_RATIO

    val riderPay: Double
        get() = netProfit * SPLIT_RATIO

    val maintenanceSave: Double
        get() = netProfit * SPLIT_RATIO

    val businessProfit: Double
        get() = netProfit * SPLIT_RATIO

    val bankDeposit: Double
        get() = maintenanceSave + businessProfit - if (deductFloat) FLOAT_AMOUNT else 0.0

    val incomePerKm: Double
        get() = if (businessKm > 0) grossIncome / businessKm else 0.0

    val fuelPerKm: Double
        get() = if (businessKm > 0) actualFuelCost / businessKm else 0.0

    val fuelPercentage: Double
        get() = if (grossIncome > 0) actualFuelCost / grossIncome else 0.0

    val fuelStatus: FuelStatus
        get() = when {
            grossIncome <= 0 -> FuelStatus.NONE
            fuelPercentage < FUEL_GOOD_THRESHOLD -> FuelStatus.GOOD
            fuelPercentage <= FUEL_HIGH_THRESHOLD -> FuelStatus.HIGH
            else -> FuelStatus.ALERT
        }

    val meetsTarget: Boolean get() = grossIncome >= DAILY_TARGET
    val aboveMinimum: Boolean get() = grossIncome >= MIN_GROSS

    enum class FuelStatus { NONE, GOOD, HIGH, ALERT }
}
