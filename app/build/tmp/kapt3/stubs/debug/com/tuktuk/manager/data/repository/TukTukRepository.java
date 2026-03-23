package com.tuktuk.manager.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001aJ\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001b0\u001aJ\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001a2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0018\u0010 \u001a\u0004\u0018\u00010\u00112\u0006\u0010!\u001a\u00020\u001fH\u0086@\u00a2\u0006\u0002\u0010\"J\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001b0\u001a2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001aJ\u0016\u0010%\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00a2\u0006\u0002\u0010\"J\u000e\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0\u001aJ\u0016\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u001a2\u0006\u0010*\u001a\u00020\u001fJ\u001c\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00a2\u0006\u0002\u0010\"J$\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b/\u0010\u0013J$\u00100\u001a\b\u0012\u0004\u0012\u00020.0\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b1\u0010\u0018J\u0016\u00102\u001a\u00020\u000f2\u0006\u00103\u001a\u00020(H\u0086@\u00a2\u0006\u0002\u00104J\u000e\u00105\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u00106J$\u00107\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b8\u0010\u0013J$\u00109\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b:\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006;"}, d2 = {"Lcom/tuktuk/manager/data/repository/TukTukRepository;", "", "db", "Lcom/tuktuk/manager/data/local/TukTukDatabase;", "firebaseRepo", "Lcom/tuktuk/manager/data/remote/FirebaseRepository;", "(Lcom/tuktuk/manager/data/local/TukTukDatabase;Lcom/tuktuk/manager/data/remote/FirebaseRepository;)V", "entryDao", "Lcom/tuktuk/manager/data/local/dao/DailyEntryDao;", "expenseDao", "Lcom/tuktuk/manager/data/local/dao/ExpenseDao;", "settingsDao", "Lcom/tuktuk/manager/data/local/dao/SettingsDao;", "deleteEntry", "Lkotlin/Result;", "", "entry", "Lcom/tuktuk/manager/data/local/entity/DailyEntry;", "deleteEntry-gIAlu-s", "(Lcom/tuktuk/manager/data/local/entity/DailyEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExpense", "expense", "Lcom/tuktuk/manager/data/local/entity/Expense;", "deleteExpense-gIAlu-s", "(Lcom/tuktuk/manager/data/local/entity/Expense;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllEntries", "Lkotlinx/coroutines/flow/Flow;", "", "getAllExpenses", "getEntriesForMonth", "yearMonth", "", "getEntryByDate", "date", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExpensesForMonth", "getLastSevenEntries", "getMonthlyStats", "Lcom/tuktuk/manager/data/model/MonthlyStats;", "getSettings", "Lcom/tuktuk/manager/data/local/entity/AppSettings;", "getTodayEntry", "today", "getWeeklyStats", "Lcom/tuktuk/manager/data/model/WeeklyStats;", "saveEntry", "", "saveEntry-gIAlu-s", "saveExpense", "saveExpense-gIAlu-s", "saveSettings", "settings", "(Lcom/tuktuk/manager/data/local/entity/AppSettings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncUnsynced", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateEntry", "updateEntry-gIAlu-s", "updateExpense", "updateExpense-gIAlu-s", "app_debug"})
public final class TukTukRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.local.TukTukDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.remote.FirebaseRepository firebaseRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.local.dao.DailyEntryDao entryDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.local.dao.ExpenseDao expenseDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tuktuk.manager.data.local.dao.SettingsDao settingsDao = null;
    
    public TukTukRepository(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.local.TukTukDatabase db, @org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.remote.FirebaseRepository firebaseRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> getAllEntries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> getEntriesForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.tuktuk.manager.data.local.entity.DailyEntry> getTodayEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String today) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> getLastSevenEntries() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getEntryByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tuktuk.manager.data.local.entity.DailyEntry> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.Expense>> getAllExpenses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.Expense>> getExpensesForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.tuktuk.manager.data.local.entity.AppSettings> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveSettings(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.local.entity.AppSettings settings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMonthlyStats(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tuktuk.manager.data.model.MonthlyStats> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWeeklyStats(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tuktuk.manager.data.model.WeeklyStats>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncUnsynced(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}