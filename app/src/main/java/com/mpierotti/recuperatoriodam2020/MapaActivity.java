package com.mpierotti.recuperatoriodam2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<String> ciudad;

    List<LatLng> latLngList = new ArrayList<>();
    List<Marker> markerList = new ArrayList<>();
    Polyline polyline = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            ciudad = (ArrayList<String>) b.get("ciudad");
        }



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updateMapa();
    }

    public void updateMapa(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }
        MarkerOptions ciudad_marker = new MarkerOptions()
                .position(new LatLng(-31.7473137,-60.5499826))
                .title(ciudad.get(0) +", "+ciudad.get(1))
                .snippet(ciudad.get(0));

        Marker marker = mMap.addMarker(ciudad_marker);
        markerList.add(marker);
        latLngList.add(marker.getPosition());

        MarkerOptions ciudad_marker2 = new MarkerOptions()
                .position(new LatLng(-31.6181236,-60.7762974))
                .title("Otra ciudad");

        Marker marker2 = mMap.addMarker(ciudad_marker2);
        markerList.add(marker2);
        latLngList.add(marker2.getPosition());

        dibujarPolyline();
    }
    public void dibujarPolyline(){
        if (polyline != null){
            polyline.remove();
        }
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(latLngList).clickable(true);
        polyline = mMap.addPolyline(polylineOptions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String
            permissions[], int[] grantResults) {
        switch (requestCode) {
            case 9999: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    updateMapa();
                }
            }

        }
    }
}