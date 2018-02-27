package com.dartmouth.kd.devents;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;
import java.util.Map;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;
    TextView tvLocInfo;
    private Context mContext;
    boolean markerClicked;
    PolylineOptions rectOptions;
    Polyline polyline;
    static final LatLng HANOVER = new LatLng(43.7022, 72.2896);
    private CampusEventDbHelper mDbHelper;
    private String TAG = "KF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAGG","Made it in maps activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMap();
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
        Log.d("TAGG", "Made it in on map ready");
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        //Move the camera instantly to around Hanover with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HANOVER, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

        mDbHelper = new CampusEventDbHelper(this);

        List<CampusEvent> allEvents = mDbHelper.fetchEvents();
        for (CampusEvent event : allEvents) {
            long id = event.getmId();
            double lat = event.getmLatitude();
            Log.d("KF", "lat getting set " + lat);
            double longi = event.getmLongitude();
            Log.d("kf", "long getting set" + longi);
            String mTitle = event.getmTitle();
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, longi))
                    .title(mTitle));

            //addEventPins(mMap);
        }

        mMap.setOnInfoWindowClickListener(this);
    }


    //get all of the events stored in the SQLite database and add them to the map as pins
    public void addEventPins(Map map){
        mDbHelper = new CampusEventDbHelper(this);

        List<CampusEvent> allEvents = mDbHelper.fetchEvents();
        for (CampusEvent event : allEvents) {
            long id = event.getmId();
            double lat = event.getmLatitude();
            double longi = event.getmLongitude();
            /*map.addMarker(new MarkerOptions()
                    .position(new LatLng(10, 10))
                    .title("Hello world"));*/
            //create a pin
        }

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
            //marker.
            //going to show the event associated with the marker
            //Intent intent = new Intent();
            //intent.putExtra(Globals.KEY_ROWID, )
        }
        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
    }


    private void setUpMap() {
        if (mMap == null) {
            Log.d(TAG, "Map is being setup");
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(com.dartmouth.kd.devents.R.id.map);
            mapFragment.getMapAsync(this);
        }
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
