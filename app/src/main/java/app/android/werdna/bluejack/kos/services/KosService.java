package app.android.werdna.bluejack.kos.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import app.android.werdna.bluejack.kos.R;
import app.android.werdna.bluejack.kos.pojos.Kos;

public class KosService {
    private static final String URL = "https://bit.ly/2zd4uhX";
    private static RequestQueue _requestQueue;

    public interface OnKosReadyCallback {
        void onKosDataParsed(ArrayList<Kos> kosList);
    }

    public static void retrieveKos(final Context context, final OnKosReadyCallback cb) {
        if (_requestQueue == null) {
            _requestQueue = Volley.newRequestQueue(context);
        }
        _requestQueue.add(new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Kos> kosList = new ArrayList<>();
                            for (int i = 0; i < response.length(); ++i) {
                                JSONObject kosJson = (JSONObject) response.get(i);
                                Kos kos = new Kos(
                                    kosJson.getString("name"),
                                    kosJson.getString("facilities"),
                                    kosJson.getInt("price"),
                                    kosJson.getString("address"),
                                    kosJson.getDouble("LNG"),
                                    kosJson.getDouble("LAT"),
                                    kosJson.getString("image")
                                );
                                kosList.add(kos);
                            }
                            cb.onKosDataParsed(kosList);
                        } catch (Exception e) {
                            Toast.makeText(context, "Can't fetch boarding list", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Can't fetch boarding list", Toast.LENGTH_SHORT).show();
                    }
                }
        ));
    }
}
