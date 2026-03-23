package com.tuktuk.manager.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u000e\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\'J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\f\u001a\u00020\rH\'J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001d\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020 H\u00a7@\u00a2\u0006\u0002\u0010!J\u0014\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0010\u0010#\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00a2\u0006\u0002\u0010$J\u0018\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b2\u0006\u0010&\u001a\u00020\rH\'J\u0018\u0010\'\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010(\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00a7@\u00a2\u0006\u0002\u0010$J\u0018\u0010*\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010+\u001a\u00020 2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010,\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 H\u00a7@\u00a2\u0006\u0002\u0010!J\u0016\u0010-\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006."}, d2 = {"Lcom/tuktuk/manager/data/local/dao/DailyEntryDao;", "", "delete", "", "entry", "Lcom/tuktuk/manager/data/local/entity/DailyEntry;", "(Lcom/tuktuk/manager/data/local/entity/DailyEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllEntries", "Lkotlinx/coroutines/flow/Flow;", "", "getAvgGrossForMonth", "", "yearMonth", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBestDayForMonth", "getDaysBelowMinForMonth", "", "minimum", "(Ljava/lang/String;DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDaysHitTargetForMonth", "target", "getDaysWorkedForMonth", "getEntriesBetween", "startDate", "endDate", "getEntriesForMonth", "getEntriesForMonthOnce", "getEntryByDate", "date", "getEntryById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastSevenEntries", "getLatestEntry", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTodayEntry", "today", "getTotalFuelForMonth", "getTotalGrossForMonth", "getUnsyncedEntries", "getWorstDayForMonth", "insert", "markSynced", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface DailyEntryDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.local.entity.DailyEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.local.entity.DailyEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.local.entity.DailyEntry entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> getAllEntries();
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries WHERE date = :date LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEntryByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tuktuk.manager.data.local.entity.DailyEntry> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEntryById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tuktuk.manager.data.local.entity.DailyEntry> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> getEntriesBetween(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate);
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries WHERE date LIKE :yearMonth || \'%\' ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> getEntriesForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth);
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries WHERE date LIKE :yearMonth || \'%\' ORDER BY date DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getEntriesForMonthOnce(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries ORDER BY date DESC LIMIT 7")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> getLastSevenEntries();
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries WHERE date = :today LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.tuktuk.manager.data.local.entity.DailyEntry> getTodayEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String today);
    
    @androidx.room.Query(value = "SELECT SUM(grossIncome) FROM daily_entries WHERE date LIKE :yearMonth || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalGrossForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(actualFuelCost) FROM daily_entries WHERE date LIKE :yearMonth || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalFuelForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM daily_entries WHERE date LIKE :yearMonth || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDaysWorkedForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM daily_entries WHERE date LIKE :yearMonth || \'%\' AND grossIncome >= :target")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDaysHitTargetForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, double target, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM daily_entries WHERE date LIKE :yearMonth || \'%\' AND grossIncome < :minimum AND grossIncome > 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDaysBelowMinForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, double minimum, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT AVG(grossIncome) FROM daily_entries WHERE date LIKE :yearMonth || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAvgGrossForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT MAX(grossIncome) FROM daily_entries WHERE date LIKE :yearMonth || \'%\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBestDayForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT MIN(grossIncome) FROM daily_entries WHERE date LIKE :yearMonth || \'%\' AND grossIncome > 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWorstDayForMonth(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries WHERE isSynced = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnsyncedEntries(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tuktuk.manager.data.local.entity.DailyEntry>> $completion);
    
    @androidx.room.Query(value = "UPDATE daily_entries SET isSynced = 1 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markSynced(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM daily_entries ORDER BY date DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestEntry(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tuktuk.manager.data.local.entity.DailyEntry> $completion);
}