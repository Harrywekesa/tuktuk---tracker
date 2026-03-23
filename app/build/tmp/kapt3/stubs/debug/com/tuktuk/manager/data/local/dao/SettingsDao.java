package com.tuktuk.manager.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\'J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/tuktuk/manager/data/local/dao/SettingsDao;", "", "getSettings", "Lkotlinx/coroutines/flow/Flow;", "Lcom/tuktuk/manager/data/local/entity/AppSettings;", "getSettingsOnce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "settings", "(Lcom/tuktuk/manager/data/local/entity/AppSettings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "updateLastSynced", "timestamp", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTheme", "mode", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface SettingsDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.local.entity.AppSettings settings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.tuktuk.manager.data.local.entity.AppSettings settings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM app_settings WHERE id = 1 LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.tuktuk.manager.data.local.entity.AppSettings> getSettings();
    
    @androidx.room.Query(value = "SELECT * FROM app_settings WHERE id = 1 LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSettingsOnce(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tuktuk.manager.data.local.entity.AppSettings> $completion);
    
    @androidx.room.Query(value = "UPDATE app_settings SET lastSyncedAt = :timestamp WHERE id = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLastSynced(long timestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE app_settings SET themeMode = :mode WHERE id = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTheme(@org.jetbrains.annotations.NotNull()
    java.lang.String mode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}