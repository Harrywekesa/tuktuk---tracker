package com.tuktuk.manager.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.local.entity.Expense
import kotlinx.coroutines.tasks.await

class FirebaseRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val userId: String?
        get() = auth.currentUser?.uid ?: run {
            // Anonymous auth for free tier — each device gets its own anonymous user
            null
        }

    suspend fun ensureAnonymousAuth() {
        if (auth.currentUser == null) {
            auth.signInAnonymously().await()
        }
    }



    suspend fun syncEntry(entry: DailyEntry) {
        if (userId == null) { ensureAnonymousAuth() }
        val uid = userId ?: return
        val data = mapOf(
            "id" to entry.id,
            "date" to entry.date,
            "timeIn" to entry.timeIn,
            "timeOut" to entry.timeOut,
            "startOdometer" to entry.startOdometer,
            "endOdometer" to entry.endOdometer,
            "grossIncome" to entry.grossIncome,
            "actualFuelCost" to entry.actualFuelCost,
            "notes" to entry.notes,
            "updatedAt" to entry.updatedAt
        )
        db.collection("users").document(uid)
            .collection("daily_entries")
            .document(entry.date)
            .set(data, SetOptions.merge())
            .await()
    }

    suspend fun deleteEntry(entry: DailyEntry) {
        val uid = userId ?: return
        db.collection("users").document(uid)
            .collection("daily_entries")
            .document(entry.date)
            .delete()
            .await()
    }

    suspend fun syncExpense(expense: Expense) {
        if (userId == null) { ensureAnonymousAuth() }
        val uid = userId ?: return
        val data = mapOf(
            "id" to expense.id,
            "date" to expense.date,
            "category" to expense.category,
            "description" to expense.description,
            "mechanicVendor" to expense.mechanicVendor,
            "cost" to expense.cost,
            "paidFrom" to expense.paidFrom,
            "receiptNotes" to expense.receiptNotes,
            "updatedAt" to expense.updatedAt
        )
        db.collection("users").document(uid)
            .collection("expenses")
            .document(expense.id.toString())
            .set(data, SetOptions.merge())
            .await()
    }

    suspend fun deleteExpense(expense: Expense) {
        val uid = userId ?: return
        db.collection("users").document(uid)
            .collection("expenses")
            .document(expense.id.toString())
            .delete()
            .await()
    }
}
