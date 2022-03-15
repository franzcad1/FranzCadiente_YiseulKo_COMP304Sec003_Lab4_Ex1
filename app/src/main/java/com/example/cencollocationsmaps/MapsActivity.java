package com.example.cencollocationsmaps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.cencollocationsmaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private LatLng restaurantLocation;
    String[] restaurantInfo;
    String restaurantName;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button button;
    private boolean isSatelliteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        restaurantName = getIntent().getStringExtra("restaurant");
        switch (restaurantName) {
            case "Bistro Manila":
                restaurantInfo = getResources().getStringArray(R.array.bistroManila);
                restaurantLocation = new LatLng(Double.parseDouble(restaurantInfo[0]),Double.parseDouble(restaurantInfo[1]));
                break;
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(restaurantLocation).title(restaurantName));
        CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(restaurantLocation));

        button = findViewById(R.id.mapTypeBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSatelliteView) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    button.setText("Satellite Map");
                }
                if (!isSatelliteView) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    button.setText("Standard Map");
                }
                isSatelliteView = !isSatelliteView;

            }
        });

    }
}