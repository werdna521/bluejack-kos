package app.android.werdna.bluejack.kos.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.db.BookingTransactionDb;
import app.android.werdna.bluejack.kos.pojos.BookingTransaction;
import app.android.werdna.bluejack.kos.pojos.Kos;
import app.android.werdna.bluejack.kos.pojos.User;

public class KosDetailActivity extends AppCompatActivity {

    private Kos _kos;
    private User _user;
    private ImageView _kosImage;
    private TextView _kosName;
    private TextView _kosPrice;
    private TextView _kosFacility;
    private TextView _kosDesc;
    private TextView _kosLatitude;
    private TextView _kosLongitude;

    public static Intent createIntent(Context context, User user, Kos kos) {
        Intent intent = new Intent(context, KosDetailActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("kos", kos);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kos_detail);

        _kos = getIntent().getParcelableExtra("kos");
        _user = getIntent().getParcelableExtra("user");

        _kosImage = findViewById(R.id.kos_detail_img);
        _kosName = findViewById(R.id.kos_detail_name);
        _kosPrice = findViewById(R.id.kos_detail_price);
        _kosFacility = findViewById(R.id.kos_detail_facility);
        _kosDesc = findViewById(R.id.kos_detail_desc);
        _kosLatitude = findViewById(R.id.kos_detail_latitude);
        _kosLongitude = findViewById(R.id.kos_detail_longitude);
        MaterialToolbar toolbar = findViewById(R.id.detail_toolbar);

        toolbar.setTitle(_kos.getName());

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        bindData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindData() {
        _kosImage.setImageResource(_kos.getDrawableResource());
        _kosName.setText(_kos.getName());
        _kosPrice.setText(String.format(Locale.US, "Rp%d", _kos.getPrice()));
        _kosFacility.setText(String.format(Locale.US, "Facility: %s", _kos.getFacility()));
        _kosDesc.setText(String.format(Locale.US, "Desc: %s", _kos.getDesc()));
        _kosLatitude.setText(String.format(Locale.US, "Latitude: %s", _kos.getLatitude()));
        _kosLongitude.setText(String.format(Locale.US, "Longitude: %s", _kos.getLongitude()));
    }

    private int getLastIdNumber() {
        ArrayList<BookingTransaction> bts = BookingTransactionDb.getDb().getAll();
        if (bts.size() == 0) return 1;
        BookingTransaction last = bts.get(bts.size() - 1);
        String lastBookingId = last.getBookingId();
        String numberString = String.format("%c%c%c", lastBookingId.charAt(2), lastBookingId.charAt(3), lastBookingId.charAt(4));
        return Integer.parseInt(numberString) + 1;
    }

    private void doBooking(String date) {
        String bookingId = "BK" + String.format(Locale.US, "%03d", getLastIdNumber());
        String userId = _user.getUserId();
        String name = _kos.getName();
        String facility = _kos.getFacility();
        Integer price = _kos.getPrice();
        String desc = _kos.getDesc();
        Double longitude = _kos.getLongitude();
        Double latitude = _kos.getLatitude();

        BookingTransaction bt = new BookingTransaction(bookingId, userId, name, facility, price,
                desc, longitude, latitude, date);

        BookingTransactionDb.getDb().insert(bt);
        Toast.makeText(KosDetailActivity.this, getString(R.string.booking_success),
                Toast.LENGTH_SHORT).show();
    }

    private boolean checkEligibility() {
        boolean eligible = true;
        for (BookingTransaction bt : BookingTransactionDb.getDb().getAll()) {
            if (bt.getUserId().equals(_user.getUserId()) && bt.getName().equals(_kos.getName())) {
                eligible = false;
                break;
            }
        }
        return eligible;
    }

    public void onClickBooking(View v) {
        Calendar c = Calendar.getInstance();
        final int y = c.get(Calendar.YEAR);
        final int m = c.get(Calendar.MONTH);
        final int d = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (checkEligibility()) {
                            if (year > y || (year == y && monthOfYear > m) || (year == y && monthOfYear == m && dayOfMonth >= d)) {
                                doBooking(getResources().getString(R.string.date_format, dayOfMonth, monthOfYear + 1, year));
                            } else {
                                Toast.makeText(KosDetailActivity.this, "Booking date must be at least today",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(KosDetailActivity.this, "Cannot book the same kos",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, y, m, d);
        datePickerDialog.show();
    }
}