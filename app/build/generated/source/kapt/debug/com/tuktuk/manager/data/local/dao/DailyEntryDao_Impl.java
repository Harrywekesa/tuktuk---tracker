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
import com.tuktuk.manager.data.local.entity.DailyEntry;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DailyEntryDao_Impl implements DailyEntryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyEntry> __insertionAdapterOfDailyEntry;

  private final EntityDeletionOrUpdateAdapter<DailyEntry> __deletionAdapterOfDailyEntry;

  private final EntityDeletionOrUpdateAdapter<DailyEntry> __updateAdapterOfDailyEntry;

  private final SharedSQLiteStatement __preparedStmtOfMarkSynced;

  public DailyEntryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyEntry = new EntityInsertionAdapter<DailyEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_entries` (`id`,`date`,`timeIn`,`timeOut`,`startOdometer`,`endOdometer`,`grossIncome`,`actualFuelCost`,`notes`,`isSynced`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getDate() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDate());
        }
        if (entity.getTimeIn() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTimeIn());
        }
        if (entity.getTimeOut() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTimeOut());
        }
        statement.bindDouble(5, entity.getStartOdometer());
        statement.bindDouble(6, entity.getEndOdometer());
        statement.bindDouble(7, entity.getGrossIncome());
        statement.bindDouble(8, entity.getActualFuelCost());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        final int _tmp = entity.isSynced() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfDailyEntry = new EntityDeletionOrUpdateAdapter<DailyEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `daily_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyEntry entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfDailyEntry = new EntityDeletionOrUpdateAdapter<DailyEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `daily_entries` SET `id` = ?,`date` = ?,`timeIn` = ?,`timeOut` = ?,`startOdometer` = ?,`endOdometer` = ?,`grossIncome` = ?,`actualFuelCost` = ?,`notes` = ?,`isSynced` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyEntry entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getDate() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDate());
        }
        if (entity.getTimeIn() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTimeIn());
        }
        if (entity.getTimeOut() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTimeOut());
        }
        statement.bindDouble(5, entity.getStartOdometer());
        statement.bindDouble(6, entity.getEndOdometer());
        statement.bindDouble(7, entity.getGrossIncome());
        statement.bindDouble(8, entity.getActualFuelCost());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        final int _tmp = entity.isSynced() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfMarkSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE daily_entries SET isSynced = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final DailyEntry entry, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDailyEntry.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final DailyEntry entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDailyEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final DailyEntry entry, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDailyEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markSynced(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfMarkSynced.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DailyEntry>> getAllEntries() {
    final String _sql = "SELECT * FROM daily_entries ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_entries"}, new Callable<List<DailyEntry>>() {
      @Override
      @NonNull
      public List<DailyEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DailyEntry> _result = new ArrayList<DailyEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
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
  public Object getEntryByDate(final String date,
      final Continuation<? super DailyEntry> $completion) {
    final String _sql = "SELECT * FROM daily_entries WHERE date = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyEntry>() {
      @Override
      @Nullable
      public DailyEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final DailyEntry _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
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

  @Override
  public Object getEntryById(final long id, final Continuation<? super DailyEntry> $completion) {
    final String _sql = "SELECT * FROM daily_entries WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyEntry>() {
      @Override
      @Nullable
      public DailyEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final DailyEntry _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
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

  @Override
  public Flow<List<DailyEntry>> getEntriesBetween(final String startDate, final String endDate) {
    final String _sql = "SELECT * FROM daily_entries WHERE date BETWEEN ? AND ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_entries"}, new Callable<List<DailyEntry>>() {
      @Override
      @NonNull
      public List<DailyEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DailyEntry> _result = new ArrayList<DailyEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
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
  public Flow<List<DailyEntry>> getEntriesForMonth(final String yearMonth) {
    final String _sql = "SELECT * FROM daily_entries WHERE date LIKE ? || '%' ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_entries"}, new Callable<List<DailyEntry>>() {
      @Override
      @NonNull
      public List<DailyEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DailyEntry> _result = new ArrayList<DailyEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
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
  public Object getEntriesForMonthOnce(final String yearMonth,
      final Continuation<? super List<DailyEntry>> $completion) {
    final String _sql = "SELECT * FROM daily_entries WHERE date LIKE ? || '%' ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyEntry>>() {
      @Override
      @NonNull
      public List<DailyEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DailyEntry> _result = new ArrayList<DailyEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DailyEntry>> getLastSevenEntries() {
    final String _sql = "SELECT * FROM daily_entries ORDER BY date DESC LIMIT 7";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_entries"}, new Callable<List<DailyEntry>>() {
      @Override
      @NonNull
      public List<DailyEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DailyEntry> _result = new ArrayList<DailyEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
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
  public Flow<DailyEntry> getTodayEntry(final String today) {
    final String _sql = "SELECT * FROM daily_entries WHERE date = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (today == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, today);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_entries"}, new Callable<DailyEntry>() {
      @Override
      @Nullable
      public DailyEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final DailyEntry _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getTotalGrossForMonth(final String yearMonth,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(grossIncome) FROM daily_entries WHERE date LIKE ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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

  @Override
  public Object getTotalFuelForMonth(final String yearMonth,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(actualFuelCost) FROM daily_entries WHERE date LIKE ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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

  @Override
  public Object getDaysWorkedForMonth(final String yearMonth,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM daily_entries WHERE date LIKE ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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

  @Override
  public Object getDaysHitTargetForMonth(final String yearMonth, final double target,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM daily_entries WHERE date LIKE ? || '%' AND grossIncome >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    _argIndex = 2;
    _statement.bindDouble(_argIndex, target);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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

  @Override
  public Object getDaysBelowMinForMonth(final String yearMonth, final double minimum,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM daily_entries WHERE date LIKE ? || '%' AND grossIncome < ? AND grossIncome > 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    _argIndex = 2;
    _statement.bindDouble(_argIndex, minimum);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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

  @Override
  public Object getAvgGrossForMonth(final String yearMonth,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT AVG(grossIncome) FROM daily_entries WHERE date LIKE ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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

  @Override
  public Object getBestDayForMonth(final String yearMonth,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT MAX(grossIncome) FROM daily_entries WHERE date LIKE ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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

  @Override
  public Object getWorstDayForMonth(final String yearMonth,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT MIN(grossIncome) FROM daily_entries WHERE date LIKE ? || '%' AND grossIncome > 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (yearMonth == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, yearMonth);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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

  @Override
  public Object getUnsyncedEntries(final Continuation<? super List<DailyEntry>> $completion) {
    final String _sql = "SELECT * FROM daily_entries WHERE isSynced = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyEntry>>() {
      @Override
      @NonNull
      public List<DailyEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<DailyEntry> _result = new ArrayList<DailyEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLatestEntry(final Continuation<? super DailyEntry> $completion) {
    final String _sql = "SELECT * FROM daily_entries ORDER BY date DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyEntry>() {
      @Override
      @Nullable
      public DailyEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "timeIn");
          final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "timeOut");
          final int _cursorIndexOfStartOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "startOdometer");
          final int _cursorIndexOfEndOdometer = CursorUtil.getColumnIndexOrThrow(_cursor, "endOdometer");
          final int _cursorIndexOfGrossIncome = CursorUtil.getColumnIndexOrThrow(_cursor, "grossIncome");
          final int _cursorIndexOfActualFuelCost = CursorUtil.getColumnIndexOrThrow(_cursor, "actualFuelCost");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "isSynced");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final DailyEntry _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTimeIn;
            if (_cursor.isNull(_cursorIndexOfTimeIn)) {
              _tmpTimeIn = null;
            } else {
              _tmpTimeIn = _cursor.getString(_cursorIndexOfTimeIn);
            }
            final String _tmpTimeOut;
            if (_cursor.isNull(_cursorIndexOfTimeOut)) {
              _tmpTimeOut = null;
            } else {
              _tmpTimeOut = _cursor.getString(_cursorIndexOfTimeOut);
            }
            final double _tmpStartOdometer;
            _tmpStartOdometer = _cursor.getDouble(_cursorIndexOfStartOdometer);
            final double _tmpEndOdometer;
            _tmpEndOdometer = _cursor.getDouble(_cursorIndexOfEndOdometer);
            final double _tmpGrossIncome;
            _tmpGrossIncome = _cursor.getDouble(_cursorIndexOfGrossIncome);
            final double _tmpActualFuelCost;
            _tmpActualFuelCost = _cursor.getDouble(_cursorIndexOfActualFuelCost);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSynced);
            _tmpIsSynced = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new DailyEntry(_tmpId,_tmpDate,_tmpTimeIn,_tmpTimeOut,_tmpStartOdometer,_tmpEndOdometer,_tmpGrossIncome,_tmpActualFuelCost,_tmpNotes,_tmpIsSynced,_tmpCreatedAt,_tmpUpdatedAt);
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
