package com.dartmouth.kd.devents;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    TextView tvLocInfo;

    boolean markerClicked;
    PolylineOptions rectOptions;
    Polyline polyline;
    static final LatLng HANOVER = new LatLng(43.7022, 72.2896);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAGG","Made it in maps activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvLocInfo = (TextView)findViewById(R.id.locinfo);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Log.d("kf", "map ready ");
        Log.d("TAGG","Made it in on map ready");
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        //Move the camera instantly to the best city in the world! with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HANOVER, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    @Override
    public void onMapClick(LatLng point) {
        tvLocInfo.setText(point.toString());
        mMap.animateCamera(CameraUpdateFactory.newLatLng(point));

        markerClicked = false;
    }

    @Override
    public void onMapLongClick(LatLng point) {
        tvLocInfo.setText("New marker added@" + point.toString());
        mMap.addMarker(new MarkerOptions().position(point).title(point.toString()));
        Toast.makeText(getApplicationContext(), "Event location added",
                Toast.LENGTH_SHORT).show();
        markerClicked = false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if(markerClicked) {

            //going to show the event associated with the marker
        }
        return true;
    }


    public void onLocationChanged(Location location){}
    public void onProviderDisabled(String provider) {}
    public void onProviderEnabled(String provider) {}
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    public void onExitMap(View view) {
        view.setEnabled(false);
        //Stop tracking service
        finish();
    }

}
