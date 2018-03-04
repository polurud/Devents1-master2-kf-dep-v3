package com.dartmouth.kd.devents;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kathrynflattum on 2/28/18.
 */

public class EventUploader implements ValueEventListener {
    private static final String TAG = "HistoryUploader";

    private DatabaseReference rootRef, mDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Context mContext;
    private String userID = "userID";
    private String entryID = "entry";
    public static ArrayList<CampusEvent> eventsList;
    public EventUploader(Context context) {
        this.mContext = context.getApplicationContext();
    }


    public void fetchAllBackend(){
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        /*rootRef = FirebaseDatabase.getInstance().getReference("masterSheet");
        Log.d(Globals.TAGG, "Getting into fetch backend");
        //mDatabase.
       // ValueEventListener postListener = new ValueEventListener(mContext);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                    Object sr;
                    sr = eventSnapshot.getValue();
                    Map<Integer,Object> singleRun =  (Map<Integer, Object>) eventSnapshot.getValue();
                    //eventSnapshot.child()
                    CampusEvent event = new CampusEvent();
                    //event = (CampusEvent) eventSnapshot.getValue();

                    event.setmTitle(singleRun.get(0).toString());
                    event.setmDate(0,0, 0);
                    event.setmStart(0);
                    event.setmEnd(0);
                    event.setmLocation(singleRun.get(4).toString());
                    //event.setmDate(singleRun.get("Date").toString());
                    //event.setmEnd(singleRun.get("End").toString());
                    //event.setmStart(singleRun.get("Start").toString());
                    event.setmDescription(singleRun.get(5).toString());
                    event.setmUrl(singleRun.get(6).toString());
                    //event.setmLongitude(singleRun.get("Longitude").toString());
                    //event.setmLatitude(singleRun.get("Latitude").toString());
                    Double longi = (double) singleRun.get(7);
                    event.setmLongitude(longi);
                    Double lat = (double) singleRun.get(8);
                    int food = (int) singleRun.get(9);
                    event.setmFood(food);
                    int event_type = (int) singleRun.get(10);
                    event.setmEventType(event_type);
                    int program_type = (int) singleRun.get(11);
                    event.setmProgramType(program_type);
                    int year = (int) singleRun.get(12);
                    event.setmYear(year);
                    int gender = (int) singleRun.get(13);
                    event.setmGender(gender);
                    int greek = (int) singleRun.get(14);
                    event.setmGreekSociety(greek);
                    int major = (int) singleRun.get(15);
                    event.setmMajor(major);
                    /*event.setmTitle(singleRun.get("Title").toString());
                    event.setmLocation(singleRun.get("Location").toString());
                    event.setmDate(0,0, 0);
                    event.setmEnd(0);
                    event.setmStart(0);
                    //event.setmDate(singleRun.get("Date").toString());
                    //event.setmEnd(singleRun.get("End").toString());
                    //event.setmStart(singleRun.get("Start").toString());
                    event.setmUrl(singleRun.get("URL").toString());
                    event.setmDescription(singleRun.get("Description").toString());
                    //event.setmLongitude(singleRun.get("Longitude").toString());
                    //event.setmLatitude(singleRun.get("Latitude").toString());
                    Double longi = (double) singleRun.get("Longitude");
                    event.setmLongitude(longi);
                    Double lat = (double) singleRun.get("Latitude");
                    int food = (int) singleRun.get("Food");
                    event.setmFood(food);
                    int event_type = (int) singleRun.get("Event Type");
                    event.setmEventType(event_type);
                    int program_type = (int) singleRun.get("Program Type");
                    event.setmProgramType(program_type);
                    int year = (int) singleRun.get("Year");
                    event.setmYear(year);
                    int gender = (int) singleRun.get("Gender");
                    event.setmGender(gender);
                    int greek = (int) singleRun.get("Greek");
                    event.setmGreekSociety(greek);
                    int major = (int) singleRun.get("Major");
                    event.setmMajor(major);*/

                   // eventsList.add(event);
                //}

                /*Log.d(Globals.TAGG, "On data change is getting called");
                if(dataSnapshot.hasChildren()) {
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = iterable.iterator();
                    DataSnapshot snapshot = iterator.next();
                    //Object val = snapshot.getValue();
                    //val.
                    //CampusEvent event = snapshot.getRef();
                }*/
    }


    public void syncBackend() {
        //removeDataFromDatabase();
        Log.d(Globals.TAGG, "sync backend is getting called");
        fetchAllBackend();
       /* mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            //do nothing right now
            //Utilities.showActivity(mContext, LogInActivity.class);
        }else {
            userID = firebaseUser.getUid();
            // Fetch all ExerciseEntries
            CampusEventDbHelper mCampusEventDbHelper = new CampusEventDbHelper(mContext);
            ArrayList<CampusEvent> eventsList = mCampusEventDbHelper
                    .fetchEvents();

            for (CampusEvent event : eventsList) {
                long id = event.getmId();
                String key = mDatabase.push().getKey();
                String idString = String.valueOf(id);
                Log.d(TAG, "idString is" + idString);
                mDatabase.child(userID).child(idString).setValue(event);
            }
        }*/
    }

    public void deleteEntry(long id){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String idString = String.valueOf(id);
        mDatabase.child(idString).setValue(null);
        CampusEventDbHelper db = new CampusEventDbHelper(mContext);
        db.removeEvent(id);
        syncBackend();

    }

    void removeDataFromDatabase(){
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        root.setValue(null);
    }

    public void onDataChange(DataSnapshot dataSnapshot){

    }

    public void onCancelled (DatabaseError databaseError){}

}
