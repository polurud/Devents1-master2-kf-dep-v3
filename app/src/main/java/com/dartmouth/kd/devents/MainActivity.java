package com.dartmouth.kd.devents;

import android.*;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends AppCompatActivity  {

    private CampusEventDbHelper mEventDbHelper;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userID;
    private ArrayAdapter<String> adapter;
    public static ArrayList<CampusEvent> eventlist;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkPermissions();

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("masterSheet");
        mcontext = this;
        mEventDbHelper = new CampusEventDbHelper(mcontext);
        mEventDbHelper.deleteAllEvents();

        //databaseReference = FirebaseDatabase.getInstance().getReference();

        //if(firebaseUser == null) {

        //}else{
            //userID = firebaseUser.getUid();
            //databaseReference = databaseReference.child("users").child(userID).child("items");
            Intent intent = new Intent(this, FunctionActivity.class);
            startActivity(intent);
        //}

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               //eventlist = new ArrayList<>();
            for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                Map<String,Object> singleRun =  (Map<String, Object>) eventSnapshot.getValue();

                CampusEvent event = new CampusEvent();
                event.setmTitle(singleRun.get("Title").toString());
                event.setmLocation(singleRun.get("Location").toString());
                event.setmDate(0, 0, 0);
                event.setmEnd(singleRun.get("End").toString());
                event.setmStart(singleRun.get("Start").toString());
                //event.setmDate(singleRun.get("Date").toString());
                //event.setmEnd(singleRun.get("End").toString());
                //event.setmStart(singleRun.get("Start").toString());
                event.setmUrl(singleRun.get("URL").toString());
                event.setmDescription(singleRun.get("Description").toString());
                //event.setmLongitude(singleRun.get("Longitude").toString());
                //event.setmLatitude(singleRun.get("Latitude").toString());
                //Double longi = (double) singleRun.get("Longitude");
                double dub = 0;
                event.setmLongitude(dub);
                //Double lat = (double) singleRun.get("Latitude");
                event.setmLatitude(dub);
                event.setmGreekSociety(0);
                event.setmMajor(0);
                event.setmGender(0);
                event.setmYear(0);
                event.setmProgramType(0);
                event.setmEventType(0);
                event.setmFood(2);
                mEventDbHelper = new CampusEventDbHelper(mcontext);

                new InsertIntoDbTask().execute(event);
                //eventlist.add(event);
            }
                //Log.d("Data.....", String.valueOf(eventlist.size()));
                //Log.d("Data.....First Value", String.valueOf(eventlist.get(0)));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    //called when login button is pressed
    public void login_button(View view)
    {
        Intent intent = new Intent(this, LogInActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
    //called when signup button is pressed
    public void signup_button(View view)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void checkPermissions() {
        if (Build.VERSION.SDK_INT < 23)
            return;

        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED) {


        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA) || shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        || shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)){

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("This permission is important for the app.")
                            .setTitle("Important permission required");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA}, 0);
                            }

                        }
                    });
                    requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }else{
                    //Never ask again and handle your app without permission.
                }
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            Utils.showActivity(this, LogInActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }



    public class InsertIntoDbTask extends AsyncTask<CampusEvent, Void, String> {
        @Override
        protected String doInBackground(CampusEvent... campusEvents) {
            long id = mEventDbHelper.insertEntry(campusEvents[0]);

            return ""+id;
            // Pop up a toast

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Event #" + result + " saved.", Toast.LENGTH_SHORT)
                    .show();
        }

    }
}



