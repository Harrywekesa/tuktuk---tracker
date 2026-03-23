package com.tuktuk.manager.data.local.dao

import androidx.room.*
import com.tuktuk.manager.data.local.entity.DailyEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: DailyEntry): Long

    @Update
    suspend fun update(entry: DailyEntry)

    @Delete
    suspend fun delete(entry: DailyEntry)

    @Query("SELECT * FROM daily_entries ORDER BY date DESC")
    fun getAllEntries(): Flow<List<DailyEntry>>

    @Query("SELECT * FROM daily_entries WHERE date = :date LIMIT 1")
    suspend fun getEntryByDate(date: String): DailyEntry?

    @Query("SELECT * FROM daily_entries WHERE id = :id LIMIT 1")
    suspend fun getEntryById(id: Long): DailyEntry?

    @Query("SELECT * FROM daily_entries WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getEntriesBetween(startDate: String, endDate: String): Flow<List<DailyEntry>>

    @Query("SELECT * FROM daily_entries WHERE date LIKE :yearMonth || '%' ORDER BY date DESC")
    fun getEntriesForMonth(yearMonth: String): Flow<List<DailyEntry>>

    @Query("SELECT * FROM daily_entries WHERE date LIKE :yearMonth || '%' ORDER BY date DESC")
    suspend fun getEntriesForMonthOnce(yearMonth: String): List<DailyEntry>

    @Query("SELECT * FROM daily_entries ORDER BY date DESC LIMIT 7")
    fun getLastSevenEntries(): Flow<List<DailyEntry>>

    @Query("SELECT * FROM daily_entries WHERE date = :today LIMIT 1")
    fun getTodayEntry(today: String): Flow<DailyEntry?>

    // Summary queries
    @Query("SELECT SUM(grossIncome) FROM daily_entries WHERE date LIKE :yearMonth || '%'")
    suspend fun getTotalGrossForMonth(yearMonth: String): Double?

    @Query("SELECT SUM(actualFuelCost) FROM daily_entries WHERE date LIKE :yearMonth || '%'")
    suspend fun getTotalFuelForMonth(yearMonth: String): Double?

    @Query("SELECT COUNT(*) FROM daily_entries WHERE date LIKE :yearMonth || '%'")
    suspend fun getDaysWorkedForMonth(yearMonth: String): Int

    @Query("SELECT COUNT(*) FROM daily_entries WHERE date LIKE :yearMonth || '%' AND grossIncome >= :target")
    suspend fun getDaysHitTargetForMonth(yearMonth: String, target: Double): Int

    @Query("SELECT COUNT(*) FROM daily_entries WHERE date LIKE :yearMonth || '%' AND grossIncome < :minimum AND grossIncome > 0")
    suspend fun getDaysBelowMinForMonth(yearMonth: String, minimum: Double): Int

    @Query("SELECT AVG(grossIncome) FROM daily_entries WHERE date LIKE :yearMonth || '%'")
    suspend fun getAvgGrossForMonth(yearMonth: String): Double?

    @Query("SELECT MAX(grossIncome) FROM daily_entries WHERE date LIKE :yearMonth || '%'")
    suspend fun getBestDayForMonth(yearMonth: String): Double?

    @Query("SELECT MIN(grossIncome) FROM daily_entries WHERE date LIKE :yearMonth || '%' AND grossIncome > 0")
    suspend fun getWorstDayForMonth(yearMonth: String): Double?

    @Query("SELECT * FROM daily_entries WHERE isSynced = 0")
    suspend fun getUnsyncedEntries(): List<DailyEntry>

    @Query("UPDATE daily_entries SET isSynced = 1 WHERE id = :id")
    suspend fun markSynced(id: Long)

    @Query("SELECT * FROM daily_entries ORDER BY date DESC LIMIT 1")
    suspend fun getLatestEntry(): DailyEntry?
}
