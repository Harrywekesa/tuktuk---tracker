package com.tuktuk.manager.data.local.dao

import androidx.room.*
import com.tuktuk.manager.data.local.entity.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense): Long

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE date LIKE :yearMonth || '%' ORDER BY date DESC")
    fun getExpensesForMonth(yearMonth: String): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE date LIKE :yearMonth || '%' ORDER BY date DESC")
    suspend fun getExpensesForMonthOnce(yearMonth: String): List<Expense>

    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getExpensesBetween(startDate: String, endDate: String): Flow<List<Expense>>

    @Query("SELECT SUM(cost) FROM expenses WHERE date LIKE :yearMonth || '%'")
    suspend fun getTotalExpensesForMonth(yearMonth: String): Double?

    @Query("SELECT SUM(cost) FROM expenses WHERE date LIKE :yearMonth || '%' AND paidFrom = 'Maintenance Fund'")
    suspend fun getMaintenanceExpensesForMonth(yearMonth: String): Double?

    @Query("SELECT * FROM expenses WHERE isSynced = 0")
    suspend fun getUnsyncedExpenses(): List<Expense>

    @Query("UPDATE expenses SET isSynced = 1 WHERE id = :id")
    suspend fun markSynced(id: Long)

    @Query("SELECT SUM(cost) FROM expenses")
    suspend fun getTotalAllExpenses(): Double?

    @Query("SELECT * FROM expenses WHERE category = :category ORDER BY date DESC")
    fun getExpensesByCategory(category: String): Flow<List<Expense>>
}
