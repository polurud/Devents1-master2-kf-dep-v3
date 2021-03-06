package com.dartmouth.kd.devents;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HP on 3/3/2018.
 */

//Load events parsed from https://news.dartmouth.edu/events
//public class Load_Urlevents extends Activity {



//   // public static  int i = 1;
//    public static  int offset = 0;
//    public static ArrayList<String> urls;
//
//    public static Document doc, doc1;
//    private static CampusEventDbHelper mEventDbHelper1;
//    SQLiteDatabase db;
//
//
//    public static ArrayList<String> arr_linkText; //store event Url
//    public static ArrayList<String> arr_titleText; //store event Title
//    public static  ArrayList<String> arr_dayText; //store event day
//    public static  ArrayList<String> arr_timestartText;//store start time
//    public static ArrayList<String> arr_timeendText;//store end time
//    public static ArrayList<String> arr_summaryText;//store description of the event
//    public static ArrayList<String> arr_location;//store location
////    public DatabaseReference rootRef, demoRef;
//    public  static String Url = "https://news.dartmouth.edu/events";
//
//   public  Context mContext;
//
//
//    public static void loadevent() {
//        urls = new ArrayList<>();
//        arr_linkText = new ArrayList<>();
//        arr_titleText = new ArrayList<>();
//        arr_dayText = new ArrayList<>();
//        arr_timestartText = new ArrayList<>();
//        arr_timeendText = new ArrayList<>();
//        arr_summaryText = new ArrayList<>();
//        arr_location = new ArrayList<>();
//
//        //while loop for storing all Url of each the home Url and its Next page Url
//        //offset  value determines the number of pages to be loaded. No. of pages will be (offset_value/10)+1
//        while(offset<11) //load two pages
//        {
//            Url = "https://news.dartmouth.edu/events?"+"offset="+Integer.toString(offset)+"&audience_ids=3";
//            Log.d("show",Url);
//            urls.add(Url);
//            offset +=10;
//        }
//        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
//        jsoupAsyncTask.execute();
//    }


    //parse and store url
 public  class Load_Urlevents extends AsyncTask<Void, Void, ArrayList<String>> {

        private Context mcontext;
        public Load_Urlevents(Context context)
        {
            mcontext = context;
        }
        public static  int offset = 0;
        public static Document doc, doc1;
        public static ArrayList<String> arr_linkText = new ArrayList<>(); //store event Url
        public static ArrayList<String> arr_titleText = new ArrayList<>(); //store event Title
        public static  ArrayList<String> arr_dayText = new ArrayList<>(); //store event day
        public static  ArrayList<String> arr_timestartText = new ArrayList<>();//store start time
        public static ArrayList<String> arr_timeendText = new ArrayList<>();//store end time
        public static ArrayList<String> arr_summaryText = new ArrayList<>();//store description of the event
        public static ArrayList<String> arr_location = new ArrayList<>();//store location
        public  static String Url = "https://news.dartmouth.edu/events";

        public static ArrayList<String> urls = new ArrayList<>();


        ArrayList<String> arr_final = new ArrayList<>();
       public static ArrayList<CampusEvent> campus_Eventdata = new ArrayList<>();


        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            String linkText = "";

            while(offset<11) //load two pages
            {
                Url = "https://news.dartmouth.edu/events?"+"offset="+Integer.toString(offset)+"&audience_ids=3";
                Log.d("show",Url);
                urls.add(Url);
                offset +=10;
            }

            try {
                for(String temp: urls) {
                    doc = Jsoup.connect(temp).get();
                    Elements title = doc.select("h2.title");
                    Elements day = doc.select("h3.event-day");
                    Elements time = doc.select("h3.event-time");
                    Elements summary = doc.select("p.summary");
                    Elements hre = doc.select("h2.title,abs.href");



                    //parse url text
                    for (Element link : hre) {
                        linkText = link.html();
                        String s1 = linkText.substring(linkText.indexOf('/', 0), linkText.indexOf('>', 0) - 1);

                        String s2 = "https://news.dartmouth.edu" + s1;


                        arr_linkText.add(s2);
                    }
//                parse location from each href
                    for (int j = 0; j < arr_linkText.size(); j++) {
                        String newlink = arr_linkText.get(j);
                        doc1 = Jsoup.connect(newlink).get();
                        Elements location = doc1.getElementsByAttributeValue("class", "location");
                        for (Element a : location) {
                            String lo = a.text();
                            arr_location.add(lo);
                            break;
                        }

                    }

                    //parse event title
                    for (Element link : title) {
                        linkText = link.text();

                        arr_titleText.add(linkText);
                    }
                    for (Element link : day) {
                        linkText = link.html();
                        arr_dayText.add(linkText);
                    }
                    //parse start-time
                    for (Element link : time) {
                        linkText = link.text();
                        if (linkText.contains("All")) {
                            arr_timestartText.add(linkText);
                        } else {
                            String s1 = linkText.substring(linkText.indexOf(':') - 1, linkText.indexOf('-', 0));
                            arr_timestartText.add(s1);
                        }

                    }
                    //parse end-time
                    for (Element link : time) {
                        linkText = link.text();
                        if (linkText.contains("All")) {
                            arr_timeendText.add("");
                        } else {
                            String s1 = linkText.substring(linkText.indexOf('-', 0) + 1, linkText.lastIndexOf('m') + 1);
                            arr_timeendText.add(s1);
                        }
                    }
                    //parse summary
                    for (Element link : summary) {
                        linkText = link.text();
                        arr_summaryText.add(linkText);
                    }

                    for (int j = 0; j < arr_summaryText.size(); j++) {

                        String url1 = arr_linkText.get(j);
                        String title1 = arr_titleText.get(j);
                        String start1 = arr_timestartText.get(j);
                        String end1 = arr_timeendText.get(j);
                        String day1 = arr_dayText.get(j);
                        String summary1 = arr_summaryText.get(j);
                        String loc1 = arr_location.get(j);

                        String addn = url1 + "::" + title1 + "::" + start1 + "::" + end1 + "::" + day1 + "::" + summary1 + "::" + loc1;

                        if (!arr_final.contains(addn))
                            arr_final.add(addn);
                        Log.d("String returning value", addn);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arr_final;
        }
        @Override
        protected void onPostExecute(ArrayList<String> result) {

            Log.d("insidePost", "onPostExecute: ");
            for (String temp_result : result) {
                String str = temp_result;
                List<String> finalList = Arrays.asList(str.split("::"));
                CampusEvent campus_event = new CampusEvent();
                campus_event.setURL(finalList.get(0));
                campus_event.setTitle(finalList.get(1));
                campus_event.setStart(finalList.get(2));
                campus_event.setEnd(finalList.get(3));

                campus_event.setDateTime(finalList.get(4));
                campus_event.setDescription(finalList.get(5));
                campus_event.setLocation(finalList.get(6));
                double lati = 43.70566;
                campus_event.setLatitude(lati);
                double longi = -72.288745;
                campus_event.setLatitude(longi);

//                campus_Eventdata.add(campus_event);

                CampusEventDbHelper campusdb = new CampusEventDbHelper(mcontext);

                try {
                    long id = campusdb.insertEntry(campus_event);
                    Log.d("inserttinDB1", "inserttinDB1");

                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("masterSheet");
                    //String key = mDatabase.push().getKey();
                    String idString = String.valueOf(id);
                    rootRef.child(idString).setValue(campus_event);
                }
                catch (Exception e)
                {}


                //push onto firebase

//                rootRef = FirebaseDatabase.getInstance().getReference("masterSheet");
//
//                CampusEventDbHelper db = new CampusEventDbHelper(mContext);
//
//                long id = mEventDbHelper.insertEntry(exerciseEntries[0]);
//                CampusEvent event = db.fetchEventByIndex(id);
//                //String key = mDatabase.push().getKey();
//                String idString = String.valueOf(id);
//
//                //Log.d(TAG, "idString is" + idString);
//                //mDatabase.setValue(event);
//                //rootRef = FirebaseDatabase.getInstance().getReference("masterSheet");
//                rootRef.child(idString).setValue(event);


//                demoRef = rootRef.child("masterSheet").child(x).child("Url");
//                demoRef.push().setValue(finalList.get(0));
//                demoRef = rootRef.child("masterSheet").child(x).child("Title");
//                demoRef.push().setValue(finalList.get(1));
//                demoRef = rootRef.child("masterSheet").child(x).child("Start");
//                demoRef.push().setValue(finalList.get(2));
//                demoRef = rootRef.child("masterSheet").child(x).child("End");
//                demoRef.push().setValue(finalList.get(3));
//                demoRef = rootRef.child("masterSheet").child(x).child("Date");
//                demoRef.push().setValue(finalList.get(4));
//                demoRef = rootRef.child("masterSheet").child(x).child("Description");
//                demoRef.push().setValue(finalList.get(5));
//                demoRef = rootRef.child("masterSheet").child(x).child("Location");
//                demoRef.push().setValue(finalList.get(6));
//                demoRef = rootRef.child("masterSheet").child(x).child("Latitude");
////
////                demoRef.push().setValue(lati);
////                demoRef = rootRef.child("masterSheet").child(x).child("Longitude");
//                double longi = -72.288745;
//                demoRef.push().setValue(longi);
//
//                i = i + 1;


            }
        }

    }



