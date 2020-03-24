package app.android.werdna.bluejack.kos.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.adapter.KosAdapter;
import app.android.werdna.bluejack.kos.pojos.Kos;
import app.android.werdna.bluejack.kos.pojos.User;

public class KosListActivity extends AppCompatActivity {

    private ArrayList<Kos> _kos;

    public static Intent createIntent(Context context, User user) {
        Intent intent = new Intent(context, KosListActivity.class);
        intent.putExtra("user", user);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kos_list);

        init();
        User user = getIntent().getParcelableExtra("user");

        RecyclerView recyclerView = findViewById(R.id.kos_list_recycler);
        KosAdapter adapter = new KosAdapter(_kos, KosListActivity.this, user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.booking_transaction_menu_item) {
            Toast.makeText(this, "Booking", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.logout_menu_item) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        _kos = new ArrayList<>();
        _kos.add(new Kos("Maharaja", "AC, WiFi, Bathroom", 1450000,
                "The best boarding", -6.2000809, 106.7833355, R.drawable.maharaja));
        _kos.add(new Kos("Haji Indra", "AC, WiFi", 1900000,
                "The cheapest boarding", -6.2261741, 106.9078293, R.drawable.haji_indra));
    }
}
