package com.corona.coronacount.Activities;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.corona.coronacount.Models.AllCountries;
import com.corona.coronacount.Models.CoronaLocations;
import com.corona.coronacount.R;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import net.sharewire.googlemapsclustering.Cluster;
import net.sharewire.googlemapsclustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

public class GoogleMapss extends FragmentActivity implements OnMapReadyCallback {
    private com.google.android.gms.maps.GoogleMap mMap;
    private static final String TAG = GoogleMapss.class.getSimpleName();
    private AllCountries allCountries = new AllCountries();
    private List<AllCountries> locations = new ArrayList<>();
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        locations = (List<AllCountries>)getIntent().getSerializableExtra("list");
        type = getIntent().getStringExtra("type");



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap google) {
        mMap = google;
        mMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID);


        mMap.setOnMapLoadedCallback(new com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

            }
        });


        ClusterManager<CoronaLocations> clusterManager = new ClusterManager<>(this, mMap);
        clusterManager.setCallbacks(new ClusterManager.Callbacks<CoronaLocations>() {
            @Override
            public boolean onClusterClick(@NonNull Cluster<CoronaLocations> cluster) {
                Log.d(TAG, "onClusterClick");
                return false;
            }

            @Override
            public boolean onClusterItemClick(@NonNull CoronaLocations clusterItem) {
                Log.d(TAG, "onClusterItemClick");
                return false;
            }
        });
        mMap.setOnCameraIdleListener(clusterManager);

        List<CoronaLocations> clusterItems = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            LatLng latLng = new LatLng(locations.get(i).getCountryInfo().getLat(), locations.get(i).getCountryInfo().getLongitude());
            clusterItems.add(new CoronaLocations(latLng, "" + locations.get(i).getCases()));
        }
        clusterManager.setItems(clusterItems);
    }
}
