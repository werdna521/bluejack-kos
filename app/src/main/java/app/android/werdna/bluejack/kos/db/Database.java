package app.android.werdna.bluejack.kos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.android.werdna.bluejack.kos.db.schema.DbSchema;

import static app.android.werdna.bluejack.kos.db.schema.DbSchema.UserTable.*;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, DbSchema.DB_NAME, null, DbSchema.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            Cols.USER_ID + " TEXT PRIMARY KEY," +
            Cols.USERNAME + " TEXT," +
            Cols.PASSWORD + " TEXT," +
            Cols.PHONE_NUMBER + " TEXT," +
            Cols.GENDER + " TEXT," +
            Cols.DATE_OF_BIRTH + " TEXT" +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
