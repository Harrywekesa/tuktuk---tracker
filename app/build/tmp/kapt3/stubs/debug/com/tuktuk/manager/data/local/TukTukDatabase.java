package com.tuktuk.manager.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\n"}, d2 = {"Lcom/tuktuk/manager/data/local/TukTukDatabase;", "Landroidx/room/RoomDatabase;", "()V", "dailyEntryDao", "Lcom/tuktuk/manager/data/local/dao/DailyEntryDao;", "expenseDao", "Lcom/tuktuk/manager/data/local/dao/ExpenseDao;", "settingsDao", "Lcom/tuktuk/manager/data/local/dao/SettingsDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.tuktuk.manager.data.local.entity.DailyEntry.class, com.tuktuk.manager.data.local.entity.Expense.class, com.tuktuk.manager.data.local.entity.AppSettings.class}, version = 2, exportSchema = false)
public abstract class TukTukDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.tuktuk.manager.data.local.TukTukDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.tuktuk.manager.data.local.TukTukDatabase.Companion Companion = null;
    
    public TukTukDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tuktuk.manager.data.local.dao.DailyEntryDao dailyEntryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tuktuk.manager.data.local.dao.ExpenseDao expenseDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tuktuk.manager.data.local.dao.SettingsDao settingsDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/tuktuk/manager/data/local/TukTukDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/tuktuk/manager/data/local/TukTukDatabase;", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getMIGRATION_1_2", "()Landroidx/room/migration/Migration;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_1_2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.tuktuk.manager.data.local.TukTukDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}