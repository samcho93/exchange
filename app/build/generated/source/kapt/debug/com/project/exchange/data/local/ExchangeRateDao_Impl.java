package com.project.exchange.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ExchangeRateDao_Impl implements ExchangeRateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExchangeRateEntity> __insertionAdapterOfExchangeRateEntity;

  public ExchangeRateDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExchangeRateEntity = new EntityInsertionAdapter<ExchangeRateEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `exchange_rates` (`currencyPair`,`baseCurrency`,`targetCurrency`,`rate`,`updatedAt`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExchangeRateEntity entity) {
        if (entity.getCurrencyPair() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getCurrencyPair());
        }
        if (entity.getBaseCurrency() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getBaseCurrency());
        }
        if (entity.getTargetCurrency() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTargetCurrency());
        }
        statement.bindDouble(4, entity.getRate());
        statement.bindLong(5, entity.getUpdatedAt());
      }
    };
  }

  @Override
  public Object insertRates(final List<ExchangeRateEntity> rates,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfExchangeRateEntity.insert(rates);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRatesForBase(final String base,
      final Continuation<? super List<ExchangeRateEntity>> $completion) {
    final String _sql = "SELECT * FROM exchange_rates WHERE baseCurrency = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (base == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, base);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ExchangeRateEntity>>() {
      @Override
      @NonNull
      public List<ExchangeRateEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCurrencyPair = CursorUtil.getColumnIndexOrThrow(_cursor, "currencyPair");
          final int _cursorIndexOfBaseCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "baseCurrency");
          final int _cursorIndexOfTargetCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "targetCurrency");
          final int _cursorIndexOfRate = CursorUtil.getColumnIndexOrThrow(_cursor, "rate");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ExchangeRateEntity> _result = new ArrayList<ExchangeRateEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExchangeRateEntity _item;
            final String _tmpCurrencyPair;
            if (_cursor.isNull(_cursorIndexOfCurrencyPair)) {
              _tmpCurrencyPair = null;
            } else {
              _tmpCurrencyPair = _cursor.getString(_cursorIndexOfCurrencyPair);
            }
            final String _tmpBaseCurrency;
            if (_cursor.isNull(_cursorIndexOfBaseCurrency)) {
              _tmpBaseCurrency = null;
            } else {
              _tmpBaseCurrency = _cursor.getString(_cursorIndexOfBaseCurrency);
            }
            final String _tmpTargetCurrency;
            if (_cursor.isNull(_cursorIndexOfTargetCurrency)) {
              _tmpTargetCurrency = null;
            } else {
              _tmpTargetCurrency = _cursor.getString(_cursorIndexOfTargetCurrency);
            }
            final double _tmpRate;
            _tmpRate = _cursor.getDouble(_cursorIndexOfRate);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ExchangeRateEntity(_tmpCurrencyPair,_tmpBaseCurrency,_tmpTargetCurrency,_tmpRate,_tmpUpdatedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
