package com.example.sachtech_pc.mapdemo;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,Runnable{

    SupportMapFragment supportMapFragment;
    LatLng chandigarh = new LatLng(30.7333, 76.7794);
    LatLng pathankot = new LatLng(32.2643, 75.6421);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.myMap);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);

        MarkerOptions chdMarker = new MarkerOptions();
        chdMarker.title("Chandigarh Heart City");
        chdMarker.snippet("Ut of India");
        chdMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        chdMarker.position(chandigarh);
        googleMap.addMarker(chdMarker);

        MarkerOptions phMarker = new MarkerOptions();
        phMarker.title("My mam city");
        phMarker.snippet("Pathankot.....");
        phMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        phMarker.position(pathankot);
        googleMap.addMarker(phMarker);
        GPSTracker gps = new GPSTracker(MainActivity.this);

        Double srcLatitude = gps.getLatitude();
        Double srclongitude = gps.getLongitude();
        SystemClock.sleep(1000);
        Double destlatitude = gps.getLatitude() + 100;
        Double destlongitude = gps.getLongitude() + 100;

        Polyline line = googleMap.addPolyline(new PolylineOptions()
                .add(chandigarh,pathankot)
                .width(5)
                .color(Color.RED));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chandigarh, 12));


    }

    @Override
    public void run() {

    }
}
