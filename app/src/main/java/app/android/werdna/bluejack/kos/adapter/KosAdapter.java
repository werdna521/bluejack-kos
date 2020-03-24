package app.android.werdna.bluejack.kos.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Locale;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.activities.KosDetailActivity;
import app.android.werdna.bluejack.kos.pojos.Kos;
import app.android.werdna.bluejack.kos.pojos.User;

public class KosAdapter extends RecyclerView.Adapter<KosAdapter.KosHolder> {

    private ArrayList<Kos> _kos;
    private Context _context;
    private User _user;

    public KosAdapter(ArrayList<Kos> kos, Context context, User user) {
        _kos = kos;
        _context = context;
        _user = user;
    }

    @NonNull
    @Override
    public KosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(_context).inflate(R.layout.item_kos, parent, false);
        return new KosHolder(v, _user, _context);
    }

    @Override
    public void onBindViewHolder(@NonNull KosHolder holder, int position) {
        holder.bind(_kos.get(position));
    }

    @Override
    public int getItemCount() {
        return _kos.size();
    }

    static class KosHolder extends RecyclerView.ViewHolder {

        private ImageView _imageView;
        private TextView _kosName;
        private TextView _kosPrice;
        private TextView _kosFacility;
        private MaterialCardView _card;
        private User _user;
        private Context _context;

        KosHolder(View v, User user, Context context) {
            super(v);
            _imageView = v.findViewById(R.id.image_view);
            _kosName = v.findViewById(R.id.kos_name);
            _kosPrice = v.findViewById(R.id.kos_price);
            _kosFacility = v.findViewById(R.id.kos_facility);
            _card = v.findViewById(R.id.card);
            _user = user;
            _context = context;
        }

        void bind(final Kos k) {
            _imageView.setImageResource(k.getDrawableResource());
            _kosName.setText(k.getName());
            _kosPrice.setText(String.format(Locale.US, "Rp%d", k.getPrice()));
            _kosFacility.setText(String.format(Locale.US, "Facilities: %s", k.getFacility()));
            _card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = KosDetailActivity.createIntent(_context, _user, k);
                    _context.startActivity(intent);
                }
            });
        }
    }
}
