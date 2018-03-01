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
import java.util.Iterator;
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

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Context mContext;
    private String userID = "userID";
    private String entryID = "entry";
    private CampusEvent event;
    public EventUploader(Context context) {
        this.mContext = context.getApplicationContext();
    }


    public void fetchAllBackend(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d(Globals.TAGG, "Getting into fetch backend");
        //mDatabase.
       // ValueEventListener postListener = new ValueEventListener(mContext);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(Globals.TAGG, "On data change is getting called");
                if(dataSnapshot.hasChildren()) {
                    Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = iterable.iterator();
                    DataSnapshot snapshot = iterator.next();
                    //Object val = snapshot.getValue();
                    //val.
                    //CampusEvent event = snapshot.getRef();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


    public void syncBackend() {
        //removeDataFromDatabase();
        Log.d(Globals.TAGG, "sync backend is getting called");
        fetchAllBackend();
        mDatabase = FirebaseDatabase.getInstance().getReference();
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
        }
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
        if(dataSnapshot.hasChildren()) {
            Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
            Iterator<DataSnapshot> iterator = iterable.iterator();
            DataSnapshot snapshot = iterator.next();
            //snapshot.getRef().removeValue();
        }
    }

    public void onCancelled (DatabaseError databaseError){}
}
