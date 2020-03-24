package app.android.werdna.bluejack.kos.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Objects;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.adapter.BookingAdapter;
import app.android.werdna.bluejack.kos.db.BookingTransactionDb;
import app.android.werdna.bluejack.kos.pojos.BookingTransaction;
import app.android.werdna.bluejack.kos.pojos.User;

public class BookingActivity extends AppCompatActivity {

    private User _user;

    public static Intent createIntent(Context context, User _user) {
        Intent intent = new Intent(context, BookingActivity.class);
        intent.putExtra("user", _user);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        _user = getIntent().getParcelableExtra("user");
        ArrayList<BookingTransaction> bookingTransactions = filterBookings(BookingTransactionDb.getDb().getAll(),
                _user);

        RecyclerView recyclerView = findViewById(R.id.booking_recycler);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        BookingAdapter adapter = new BookingAdapter(bookingTransactions, BookingActivity.this, _user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BookingActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<BookingTransaction> filterBookings(ArrayList<BookingTransaction> bookingTransactions,
                                                         User user) {
        ArrayList<BookingTransaction> bts = new ArrayList<>();
        for (BookingTransaction bt: bookingTransactions) {
            if (bt.getUserId().equals(user.getUserId())) {
                bts.add(bt);
            }
        }
        return bts;
    }
}
