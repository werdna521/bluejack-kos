package app.android.werdna.bluejack.kos.db.schema;

public class DbSchema {
    public static final String DB_NAME = "blue_jack";
    public static final Integer DB_VERSION = 1;

    public static class UserTable {
        public static final String TABLE_NAME = "user";

        public static class Cols {
            public static final String USER_ID = "user_id";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String PHONE_NUMBER = "phone_number";
            public static final String GENDER = "gender";
            public static final String DATE_OF_BIRTH = "date_of_birth";
        }
    }
}
