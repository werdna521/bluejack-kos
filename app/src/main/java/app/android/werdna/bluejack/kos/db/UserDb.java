package app.android.werdna.bluejack.kos.db;

import java.util.ArrayList;

import app.android.werdna.bluejack.kos.pojos.User;

public class UserDb {

    private static UserDb _db;
    private ArrayList<User> _users;

    private UserDb() {
        _users = new ArrayList<>();
    }

    public static UserDb getDb() {
        if (_db == null) {
            _db = new UserDb();
        }
        return _db;
    }

    public User get(int i) {
        return _users.get(i);
    }

    public ArrayList<User> getAll() {
        return _users;
    }

    public void insert(User user) {
        _users.add(user);
    }

}
