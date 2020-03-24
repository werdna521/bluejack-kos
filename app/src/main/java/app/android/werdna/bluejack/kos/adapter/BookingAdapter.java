package app.android.werdna.bluejack.kos.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.db.BookingTransactionDb;
import app.android.werdna.bluejack.kos.pojos.BookingTransaction;
import app.android.werdna.bluejack.kos.pojos.User;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingHolder> {

    public interface ClickHandler {
        void onPositiveButtonClick();
    }

    private ArrayList<BookingTransaction> _bookingTransactions;
    private Context _context;
    private User _user;
    private TextView _noBooking;

    public BookingAdapter(ArrayList<BookingTransaction> bookingTransactions, Context context,
                          User user, TextView noBooking) {
        _bookingTransactions = bookingTransactions;
        _context = context;
        _user = user;
        _noBooking = noBooking;
    }

    private void updateData(ArrayList<BookingTransaction> bts) {
        _bookingTransactions = bts;
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

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(_context).inflate(R.layout.item_booking, parent, false);
        return new BookingHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHolder holder, int position) {
        holder.bind(_bookingTransactions.get(position), _context, new ClickHandler() {
            @Override
            public void onPositiveButtonClick() {
                updateData(filterBookings(BookingTransactionDb.getDb().getAll(), _user));
                notifyDataSetChanged();
                if (_bookingTransactions.size() == 0) {
                    _noBooking.setVisibility(View.VISIBLE);
                } else {
                    _noBooking.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return _bookingTransactions.size();
    }

    static class BookingHolder extends RecyclerView.ViewHolder {

        private TextView _bookingId;
        private TextView _kosName;
        private TextView _date;
        private LinearLayout _container;

        BookingHolder(View v) {
            super(v);
            _bookingId = v.findViewById(R.id.item_booking);
            _kosName = v.findViewById(R.id.item_kos_name);
            _date = v.findViewById(R.id.item_booking_date);
            _container = v.findViewById(R.id.container);
        }

        void bind(final BookingTransaction bt, final Context context, final ClickHandler ch) {
            _bookingId.setText(String.format("Booking ID: %s", bt.getBookingId()));
            _kosName.setText(String.format("Kos Name: %s", bt.getName()));
            _date.setText(String.format("Booking Date: %s", bt.getBookingDate()));
            _container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialAlertDialogBuilder(context)
                            .setTitle("Confirm Cancellation")
                            .setMessage("Are you sure you want to cancel the booking?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    BookingTransactionDb.getDb().remove(bt);
                                    ch.onPositiveButtonClick();
                                    dialog.dismiss();
                                    Toast.makeText(context, "Booking cancelled", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            });
        }
    }
}
