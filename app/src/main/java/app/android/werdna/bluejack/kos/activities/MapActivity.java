package app.android.werdna.bluejack.kos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.android.werdna.bluejack.kos.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String EXTRA_LAT = "lat";
    private static final String EXTRA_LANG = "lang";
    private static final String EXTRA_NAME = "name";

    public static Intent createIntent(Context context, double lat, double lang, String name) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra(EXTRA_LAT, lat);
        intent.putExtra(EXTRA_LANG, lang);
        intent.putExtra(EXTRA_NAME, name);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapAPI);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat = getIntent().getDoubleExtra(EXTRA_LAT,0.0);
        double lang = getIntent().getDoubleExtra(EXTRA_LANG,0.0);
        String name = getIntent().getStringExtra(EXTRA_NAME);
        LatLng location = new LatLng(lat, lang);
        googleMap.addMarker(new MarkerOptions().position(location).title(name));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18.0f));
    }
}