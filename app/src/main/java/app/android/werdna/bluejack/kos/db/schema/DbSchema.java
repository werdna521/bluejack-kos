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

    public static class BookingTransactionTable {
        public static final String TABLE_NAME = "booking_transaction";

        public static class Cols {
            public static final String BOOKING_ID = "booking_id";
            public static final String USER_ID = "user_id";
            public static final String KOS_NAME = "kos_name";
            public static final String KOS_FACILITY = "kos_facility";
            public static final String KOS_PRICE = "kos_price";
            public static final String KOS_DESC = "kos_desc";
            public static final String KOS_LONGITUDE = "kos_longitude";
            public static final String KOS_LATITUDE = "kos_latitude";
            public static final String BOOKING_DATE = "booking_date";
        }
    }
}
