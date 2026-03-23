package com.tuktuk.manager.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,           // "2026-03-01"
    val category: String,
    val description: String,
    val mechanicVendor: String = "",
    val cost: Double,
    val paidFrom: String = "Maintenance Fund",
    val receiptNotes: String = "",
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    companion object {
        val CATEGORIES = listOf(
            "Oil Change",
            "Preventive Maintenance",
            "Minor Repair",
            "Major Repair",
            "Replacing Parts",
            "Labor / Mechanic",
            "County Fees",
            "Insurance",
            "Other"
        )

        val PAID_FROM_OPTIONS = listOf(
            "Maintenance Fund",
            "Business Profit",
            "Personal"
        )
    }
}
