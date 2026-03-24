package com.tuktuk.manager.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tuktuk.manager.data.local.dao.DailyEntryDao
import com.tuktuk.manager.data.local.dao.ExpenseDao
import com.tuktuk.manager.data.local.dao.SettingsDao
import com.tuktuk.manager.data.local.entity.AppSettings
import com.tuktuk.manager.data.local.entity.DailyEntry
import com.tuktuk.manager.data.local.entity.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [DailyEntry::class, Expense::class, AppSettings::class],
    version = 2,
    exportSchema = false
)
abstract class TukTukDatabase : RoomDatabase() {

    abstract fun dailyEntryDao(): DailyEntryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        val MIGRATION_1_2 = object : androidx.room.migration.Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE daily_entries ADD COLUMN deductFloat INTEGER NOT NULL DEFAULT 1")
            }
        }

        @Volatile
        private var INSTANCE: TukTukDatabase? = null

        fun getDatabase(context: Context): TukTukDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TukTukDatabase::class.java,
                    "tuktuk_manager.db"
                )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Seed default settings
                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                database.settingsDao().insert(AppSettings())
                            }
                        }
                    }
                })
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
