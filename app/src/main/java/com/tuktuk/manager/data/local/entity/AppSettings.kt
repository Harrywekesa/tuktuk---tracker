package com.tuktuk.manager.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_settings")
data class AppSettings(
    @PrimaryKey
    val id: Int = 1,
    val ownerName: String = "Owner",
    val riderName: String = "Rider",
    val dailyGrossTarget: Double = 3000.0,
    val minimumGross: Double = 2500.0,
    val startingFloat: Double = 200.0,
    val commuteKmOneWay: Double = 5.0,
    val targetWorkingDays: Int = 23,
    val themeMode: String = "dark",   // "dark" | "light" | "system"
    val lastSyncedAt: Long = 0L,
    val currencySymbol: String = "KSh"
)
