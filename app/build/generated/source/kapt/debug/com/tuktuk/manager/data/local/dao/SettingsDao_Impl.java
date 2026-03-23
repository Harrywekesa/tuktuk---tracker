package com.tuktuk.manager.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tuktuk.manager.data.local.entity.AppSettings;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SettingsDao_Impl implements SettingsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppSettings> __insertionAdapterOfAppSettings;

  private final EntityDeletionOrUpdateAdapter<AppSettings> __updateAdapterOfAppSettings;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLastSynced;

  private final SharedSQLiteStatement __preparedStmtOfUpdateTheme;

  public SettingsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppSettings = new EntityInsertionAdapter<AppSettings>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `app_settings` (`id`,`ownerName`,`riderName`,`dailyGrossTarget`,`minimumGross`,`startingFloat`,`commuteKmOneWay`,`targetWorkingDays`,`themeMode`,`lastSyncedAt`,`currencySymbol`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppSettings entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getOwnerName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOwnerName());
        }
        if (entity.getRiderName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRiderName());
        }
        statement.bindDouble(4, entity.getDailyGrossTarget());
        statement.bindDouble(5, entity.getMinimumGross());
        statement.bindDouble(6, entity.getStartingFloat());
        statement.bindDouble(7, entity.getCommuteKmOneWay());
        statement.bindLong(8, entity.getTargetWorkingDays());
        if (entity.getThemeMode() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getThemeMode());
        }
        statement.bindLong(10, entity.getLastSyncedAt());
        if (entity.getCurrencySymbol() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getCurrencySymbol());
        }
      }
    };
    this.__updateAdapterOfAppSettings = new EntityDeletionOrUpdateAdapter<AppSettings>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `app_settings` SET `id` = ?,`ownerName` = ?,`riderName` = ?,`dailyGrossTarget` = ?,`minimumGross` = ?,`startingFloat` = ?,`commuteKmOneWay` = ?,`targetWorkingDays` = ?,`themeMode` = ?,`lastSyncedAt` = ?,`currencySymbol` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppSettings entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getOwnerName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOwnerName());
        }
        if (entity.getRiderName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRiderName());
        }
        statement.bindDouble(4, entity.getDailyGrossTarget());
        statement.bindDouble(5, entity.getMinimumGross());
        statement.bindDouble(6, entity.getStartingFloat());
        statement.bindDouble(7, entity.getCommuteKmOneWay());
        statement.bindLong(8, entity.getTargetWorkingDays());
        if (entity.getThemeMode() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getThemeMode());
        }
        statement.bindLong(10, entity.getLastSyncedAt());
        if (entity.getCurrencySymbol() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getCurrencySymbol());
        }
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateLastSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE app_settings SET lastSyncedAt = ? WHERE id = 1";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateTheme = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE app_settings SET themeMode = ? WHERE id = 1";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final AppSettings settings, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppSettings.insert(settings);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final AppSettings settings, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAppSettings.handle(settings);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLastSynced(final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLastSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateLastSynced.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTheme(final String mode, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateTheme.acquire();
        int _argIndex = 1;
        if (mode == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, mode);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateTheme.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<AppSettings> getSettings() {
    final String _sql = "SELECT * FROM app_settings WHERE id = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_settings"}, new Callable<AppSettings>() {
      @Override
      @Nullable
      public AppSettings call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwnerName = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerName");
          final int _cursorIndexOfRiderName = CursorUtil.getColumnIndexOrThrow(_cursor, "riderName");
          final int _cursorIndexOfDailyGrossTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyGrossTarget");
          final int _cursorIndexOfMinimumGross = CursorUtil.getColumnIndexOrThrow(_cursor, "minimumGross");
          final int _cursorIndexOfStartingFloat = CursorUtil.getColumnIndexOrThrow(_cursor, "startingFloat");
          final int _cursorIndexOfCommuteKmOneWay = CursorUtil.getColumnIndexOrThrow(_cursor, "commuteKmOneWay");
          final int _cursorIndexOfTargetWorkingDays = CursorUtil.getColumnIndexOrThrow(_cursor, "targetWorkingDays");
          final int _cursorIndexOfThemeMode = CursorUtil.getColumnIndexOrThrow(_cursor, "themeMode");
          final int _cursorIndexOfLastSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSyncedAt");
          final int _cursorIndexOfCurrencySymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "currencySymbol");
          final AppSettings _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwnerName;
            if (_cursor.isNull(_cursorIndexOfOwnerName)) {
              _tmpOwnerName = null;
            } else {
              _tmpOwnerName = _cursor.getString(_cursorIndexOfOwnerName);
            }
            final String _tmpRiderName;
            if (_cursor.isNull(_cursorIndexOfRiderName)) {
              _tmpRiderName = null;
            } else {
              _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
            }
            final double _tmpDailyGrossTarget;
            _tmpDailyGrossTarget = _cursor.getDouble(_cursorIndexOfDailyGrossTarget);
            final double _tmpMinimumGross;
            _tmpMinimumGross = _cursor.getDouble(_cursorIndexOfMinimumGross);
            final double _tmpStartingFloat;
            _tmpStartingFloat = _cursor.getDouble(_cursorIndexOfStartingFloat);
            final double _tmpCommuteKmOneWay;
            _tmpCommuteKmOneWay = _cursor.getDouble(_cursorIndexOfCommuteKmOneWay);
            final int _tmpTargetWorkingDays;
            _tmpTargetWorkingDays = _cursor.getInt(_cursorIndexOfTargetWorkingDays);
            final String _tmpThemeMode;
            if (_cursor.isNull(_cursorIndexOfThemeMode)) {
              _tmpThemeMode = null;
            } else {
              _tmpThemeMode = _cursor.getString(_cursorIndexOfThemeMode);
            }
            final long _tmpLastSyncedAt;
            _tmpLastSyncedAt = _cursor.getLong(_cursorIndexOfLastSyncedAt);
            final String _tmpCurrencySymbol;
            if (_cursor.isNull(_cursorIndexOfCurrencySymbol)) {
              _tmpCurrencySymbol = null;
            } else {
              _tmpCurrencySymbol = _cursor.getString(_cursorIndexOfCurrencySymbol);
            }
            _result = new AppSettings(_tmpId,_tmpOwnerName,_tmpRiderName,_tmpDailyGrossTarget,_tmpMinimumGross,_tmpStartingFloat,_tmpCommuteKmOneWay,_tmpTargetWorkingDays,_tmpThemeMode,_tmpLastSyncedAt,_tmpCurrencySymbol);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getSettingsOnce(final Continuation<? super AppSettings> $completion) {
    final String _sql = "SELECT * FROM app_settings WHERE id = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AppSettings>() {
      @Override
      @Nullable
      public AppSettings call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwnerName = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerName");
          final int _cursorIndexOfRiderName = CursorUtil.getColumnIndexOrThrow(_cursor, "riderName");
          final int _cursorIndexOfDailyGrossTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyGrossTarget");
          final int _cursorIndexOfMinimumGross = CursorUtil.getColumnIndexOrThrow(_cursor, "minimumGross");
          final int _cursorIndexOfStartingFloat = CursorUtil.getColumnIndexOrThrow(_cursor, "startingFloat");
          final int _cursorIndexOfCommuteKmOneWay = CursorUtil.getColumnIndexOrThrow(_cursor, "commuteKmOneWay");
          final int _cursorIndexOfTargetWorkingDays = CursorUtil.getColumnIndexOrThrow(_cursor, "targetWorkingDays");
          final int _cursorIndexOfThemeMode = CursorUtil.getColumnIndexOrThrow(_cursor, "themeMode");
          final int _cursorIndexOfLastSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSyncedAt");
          final int _cursorIndexOfCurrencySymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "currencySymbol");
          final AppSettings _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwnerName;
            if (_cursor.isNull(_cursorIndexOfOwnerName)) {
              _tmpOwnerName = null;
            } else {
              _tmpOwnerName = _cursor.getString(_cursorIndexOfOwnerName);
            }
            final String _tmpRiderName;
            if (_cursor.isNull(_cursorIndexOfRiderName)) {
              _tmpRiderName = null;
            } else {
              _tmpRiderName = _cursor.getString(_cursorIndexOfRiderName);
            }
            final double _tmpDailyGrossTarget;
            _tmpDailyGrossTarget = _cursor.getDouble(_cursorIndexOfDailyGrossTarget);
            final double _tmpMinimumGross;
            _tmpMinimumGross = _cursor.getDouble(_cursorIndexOfMinimumGross);
            final double _tmpStartingFloat;
            _tmpStartingFloat = _cursor.getDouble(_cursorIndexOfStartingFloat);
            final double _tmpCommuteKmOneWay;
            _tmpCommuteKmOneWay = _cursor.getDouble(_cursorIndexOfCommuteKmOneWay);
            final int _tmpTargetWorkingDays;
            _tmpTargetWorkingDays = _cursor.getInt(_cursorIndexOfTargetWorkingDays);
            final String _tmpThemeMode;
            if (_cursor.isNull(_cursorIndexOfThemeMode)) {
              _tmpThemeMode = null;
            } else {
              _tmpThemeMode = _cursor.getString(_cursorIndexOfThemeMode);
            }
            final long _tmpLastSyncedAt;
            _tmpLastSyncedAt = _cursor.getLong(_cursorIndexOfLastSyncedAt);
            final String _tmpCurrencySymbol;
            if (_cursor.isNull(_cursorIndexOfCurrencySymbol)) {
              _tmpCurrencySymbol = null;
            } else {
              _tmpCurrencySymbol = _cursor.getString(_cursorIndexOfCurrencySymbol);
            }
            _result = new AppSettings(_tmpId,_tmpOwnerName,_tmpRiderName,_tmpDailyGrossTarget,_tmpMinimumGross,_tmpStartingFloat,_tmpCommuteKmOneWay,_tmpTargetWorkingDays,_tmpThemeMode,_tmpLastSyncedAt,_tmpCurrencySymbol);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
