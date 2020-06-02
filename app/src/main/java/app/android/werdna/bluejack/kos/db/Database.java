package app.android.werdna.bluejack.kos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.android.werdna.bluejack.kos.db.schema.DbSchema;

import static app.android.werdna.bluejack.kos.db.schema.DbSchema.*;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, DbSchema.DB_NAME, null, DbSchema.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + UserTable.TABLE_NAME + " (" +
            UserTable.Cols.USER_ID + " TEXT PRIMARY KEY," +
            UserTable.Cols.USERNAME + " TEXT," +
            UserTable.Cols.PASSWORD + " TEXT," +
            UserTable.Cols.PHONE_NUMBER + " TEXT," +
            UserTable.Cols.GENDER + " TEXT," +
            UserTable.Cols.DATE_OF_BIRTH + " TEXT" +
            ")"
        );
        db.execSQL("CREATE TABLE IF NOT EXISTS " + BookingTransactionTable.TABLE_NAME + " (" +
            BookingTransactionTable.Cols.BOOKING_ID + " TEXT PRIMARY KEY," +
            BookingTransactionTable.Cols.USER_ID + " TEXT," +
            BookingTransactionTable.Cols.KOS_NAME + " TEXT," +
            BookingTransactionTable.Cols.KOS_FACILITY + " TEXT," +
            BookingTransactionTable.Cols.KOS_PRICE + " NUMBER," +
            BookingTransactionTable.Cols.KOS_DESC + " TEXT," +
            BookingTransactionTable.Cols.KOS_LONGITUDE + " NUMBER," +
            BookingTransactionTable.Cols.KOS_LATITUDE + " NUMBER," +
            BookingTransactionTable.Cols.BOOKING_DATE + " TEXT" +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
