package app.android.werdna.bluejack.kos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import app.android.werdna.bluejack.kos.pojos.User;

import static app.android.werdna.bluejack.kos.db.schema.DbSchema.UserTable.*;

public class UserRepository {
    private static UserRepository _userRepository;
    private SQLiteDatabase _db;

    private UserRepository(Context context) {
        _db = new Database(context).getWritableDatabase();
    }

    public static UserRepository with(Context context) {
        if (_userRepository == null) {
            _userRepository = new UserRepository(context);
        }
        return _userRepository;
    }

    public void register(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.USER_ID, user.getUserId());
        contentValues.put(Cols.USERNAME, user.getUsername());
        contentValues.put(Cols.PASSWORD, user.getPassword());
        contentValues.put(Cols.PHONE_NUMBER, user.getPhoneNumber());
        contentValues.put(Cols.GENDER, user.getGender());
        contentValues.put(Cols.DATE_OF_BIRTH, user.getDateOfBirth());

        _db.insert(
            TABLE_NAME,
            null,
            contentValues
        );
    }

    public User getUser(String username) {
        Cursor cursor = _db.query(
            TABLE_NAME,
            null,
            Cols.USERNAME + "=?",
            new String[] { username },
            null,
            null,
            null
        );
        cursor.moveToFirst();
        User user = new User(
            cursor.getString(cursor.getColumnIndex(Cols.USER_ID)),
            cursor.getString(cursor.getColumnIndex(Cols.USERNAME)),
            cursor.getString(cursor.getColumnIndex(Cols.PASSWORD)),
            cursor.getString(cursor.getColumnIndex(Cols.PHONE_NUMBER)),
            cursor.getString(cursor.getColumnIndex(Cols.GENDER)),
            cursor.getString(cursor.getColumnIndex(Cols.DATE_OF_BIRTH))
        );
        cursor.close();
        return user;
    }

    public boolean authenticate(String username, String password) {
        Cursor cursor = _db.query(
            TABLE_NAME,
            null,
            Cols.USERNAME + "=? AND " + Cols.PASSWORD + "=?",
            new String[] { username, password },
            null,
            null,
            null
        );
        boolean authenticated = cursor.getCount() == 1;
        cursor.close();
        return authenticated;
    }

    public boolean isRegistered(String username) {
        Cursor cursor = _db.query(
            TABLE_NAME,
            null,
            Cols.USERNAME + "=?",
            new String[] { username },
            null,
            null,
            null
        );
        boolean registered = cursor.getCount() == 1;
        cursor.close();
        return registered;
    }
}