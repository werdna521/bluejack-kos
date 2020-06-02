package app.android.werdna.bluejack.kos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import app.android.werdna.bluejack.kos.pojos.BookingTransaction;
import app.android.werdna.bluejack.kos.pojos.User;

import static app.android.werdna.bluejack.kos.db.schema.DbSchema.BookingTransactionTable.*;

public class BookingTransactionRepository {
    private static BookingTransactionRepository _bookingTransactionRepository;
    private SQLiteDatabase _db;

    private BookingTransactionRepository(Context context) {
        _db = new Database(context).getWritableDatabase();
    }

    public static BookingTransactionRepository with(Context context) {
        if (_bookingTransactionRepository == null) {
            _bookingTransactionRepository = new BookingTransactionRepository(context);
        }
        return _bookingTransactionRepository;
    }

    public ArrayList<BookingTransaction> getAll() {
        Cursor cursor = _db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        );

        ArrayList<BookingTransaction> bts = new ArrayList<>();
        while (cursor.moveToNext()) {
            bts.add(new BookingTransaction(
                    cursor.getString(cursor.getColumnIndex(Cols.BOOKING_ID)),
                    cursor.getString(cursor.getColumnIndex(Cols.USER_ID)),
                    cursor.getString(cursor.getColumnIndex(Cols.KOS_NAME)),
                    cursor.getString(cursor.getColumnIndex(Cols.KOS_FACILITY)),
                    cursor.getInt(cursor.getColumnIndex(Cols.KOS_PRICE)),
                    cursor.getString(cursor.getColumnIndex(Cols.KOS_DESC)),
                    cursor.getDouble(cursor.getColumnIndex(Cols.KOS_LONGITUDE)),
                    cursor.getDouble(cursor.getColumnIndex(Cols.KOS_LATITUDE)),
                    cursor.getString(cursor.getColumnIndex(Cols.BOOKING_DATE))
            ));
        }
        cursor.close();

        return bts;
    }

    public ArrayList<BookingTransaction> get(User user) {
        Cursor cursor = _db.query(
            TABLE_NAME,
            null,
            Cols.USER_ID + "=?",
            new String[] { user.getUserId() },
            null,
            null,
            null
        );

        ArrayList<BookingTransaction> bts = new ArrayList<>();
        while (cursor.moveToNext()) {
            bts.add(new BookingTransaction(
                cursor.getString(cursor.getColumnIndex(Cols.BOOKING_ID)),
                cursor.getString(cursor.getColumnIndex(Cols.USER_ID)),
                cursor.getString(cursor.getColumnIndex(Cols.KOS_NAME)),
                cursor.getString(cursor.getColumnIndex(Cols.KOS_FACILITY)),
                cursor.getInt(cursor.getColumnIndex(Cols.KOS_PRICE)),
                cursor.getString(cursor.getColumnIndex(Cols.KOS_DESC)),
                cursor.getDouble(cursor.getColumnIndex(Cols.KOS_LONGITUDE)),
                cursor.getDouble(cursor.getColumnIndex(Cols.KOS_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(Cols.BOOKING_DATE))
            ));
        }
        cursor.close();

        return bts;
    }

    public void insert(BookingTransaction bt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.BOOKING_ID, bt.getBookingId());
        contentValues.put(Cols.USER_ID, bt.getUserId());
        contentValues.put(Cols.KOS_NAME, bt.getName());
        contentValues.put(Cols.KOS_FACILITY, bt.getFacility());
        contentValues.put(Cols.KOS_PRICE, bt.getPrice());
        contentValues.put(Cols.KOS_DESC, bt.getDesc());
        contentValues.put(Cols.KOS_LONGITUDE, bt.getLongitude());
        contentValues.put(Cols.KOS_LATITUDE, bt.getLatitude());
        contentValues.put(Cols.BOOKING_DATE, bt.getBookingDate());

        _db.insert(
            TABLE_NAME,
            null,
            contentValues
        );
    }

    public void remove(BookingTransaction bt) {
        _db.delete(
            TABLE_NAME,
            Cols.BOOKING_ID + "=?",
            new String[] { bt.getBookingId() }
        );
    }
}
