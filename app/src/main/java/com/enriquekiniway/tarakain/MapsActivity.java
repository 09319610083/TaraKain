package com.enriquekiniway.tarakain;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.enriquekiniway.tarakain.databinding.ActivityMapsBinding;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions marker;
    LatLng centerlocation;

    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(16.41639, 120.59306);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("YMCA Baguio City")
                .position(new LatLng(16.411034, 120.599782))
                .snippet("Open during Monday to Friday 7am - 8 pm")
        );

        markerOptions.add(new MarkerOptions().title("Baguio Siloam Christian Academy")
                .position(new LatLng(16.423694, 120.577178))
                .snippet("Open during Monday to Friday 7am - 8 pm")
        );

        markerOptions.add(new MarkerOptions().title("St.Vincent Gym")
                .position(new LatLng(16.44777, 120.587990))
                .snippet("Open during Monday to Friday 7am - 8 pm")
        );

        markerOptions.add(new MarkerOptions().title("PFVR Gymnasium Baguio City")
                .position(new LatLng(16.403603, 120.604722))
                .snippet("Open during Monday to Friday 7am - 8 pm")

        );

        markerOptions.add(new MarkerOptions().title("Green Valley Hotel and Resort")
                .position(new LatLng(16.382871, 120.568508))
                .snippet("Open during Monday to Friday 7am - 8 pm")
        );


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        for (MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation, 15));
    }
    private void enableMyLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
            ActivityCompat.requestPermissions(this,perms,10000);
        }
    }
}