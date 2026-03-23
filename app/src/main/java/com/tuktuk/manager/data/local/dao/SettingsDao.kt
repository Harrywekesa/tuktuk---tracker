package com.tuktuk.manager.data.local.dao

import androidx.room.*
import com.tuktuk.manager.data.local.entity.AppSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(settings: AppSettings)

    @Update
    suspend fun update(settings: AppSettings)

    @Query("SELECT * FROM app_settings WHERE id = 1 LIMIT 1")
    fun getSettings(): Flow<AppSettings?>

    @Query("SELECT * FROM app_settings WHERE id = 1 LIMIT 1")
    suspend fun getSettingsOnce(): AppSettings?

    @Query("UPDATE app_settings SET lastSyncedAt = :timestamp WHERE id = 1")
    suspend fun updateLastSynced(timestamp: Long)

    @Query("UPDATE app_settings SET themeMode = :mode WHERE id = 1")
    suspend fun updateTheme(mode: String)
}
