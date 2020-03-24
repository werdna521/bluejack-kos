package app.android.werdna.bluejack.kos.db;

import java.util.ArrayList;

import app.android.werdna.bluejack.kos.pojos.BookingTransaction;

public class BookingTransactionDb {

    private static BookingTransactionDb _db;
    private ArrayList<BookingTransaction> _bookingTransactions;

    private BookingTransactionDb() {
        _bookingTransactions = new ArrayList<>();
    }

    public static BookingTransactionDb getDb() {
        if (_db == null) {
            _db = new BookingTransactionDb();
        }
        return _db;
    }

    public BookingTransaction get(int i) {
        return _bookingTransactions.get(i);
    }

    public ArrayList<BookingTransaction> getAll() {
        return _bookingTransactions;
    }

    public void insert(BookingTransaction bt) {
        _bookingTransactions.add(bt);
    }
}
