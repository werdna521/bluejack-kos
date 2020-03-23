package app.android.werdna.bluejack.kos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.pojos.Kos;

public class KosAdapter extends RecyclerView.Adapter<KosAdapter.KosHolder> {

    private ArrayList<Kos> _kos;
    private Context _context;

    public KosAdapter(ArrayList<Kos> kos, Context context) {
        _kos = kos;
        _context = context;
    }

    @NonNull
    @Override
    public KosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(_context).inflate(R.layout.item_kos, parent, false);
        return new KosHolder(v);
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

        KosHolder(View v) {
            super(v);
            _imageView = v.findViewById(R.id.image_view);
            _kosName = v.findViewById(R.id.kos_name);
            _kosPrice = v.findViewById(R.id.kos_price);
            _kosFacility = v.findViewById(R.id.kos_facility);
        }

        void bind(Kos k) {
            _imageView.setImageResource(k.getDrawableResource());
            _kosName.setText(k.getName());
            _kosPrice.setText(String.format(Locale.US, "Rp%d", k.getPrice()));
            _kosFacility.setText(String.format(Locale.US, "Facilities: %s", k.getFacility()));
        }
    }
}
