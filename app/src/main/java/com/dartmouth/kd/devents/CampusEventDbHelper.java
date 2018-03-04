package com.dartmouth.kd.devents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kathrynflattum on 2/25/18.
 */

public class CampusEventDbHelper extends SQLiteOpenHelper {
    // Database name string
    public static final String DATABASE_NAME = "CampusEventsDB";
    // Table name string. (Only one table)
    private static final String TABLE_EVENT_ENTRIES = "EVENTS";
    private SQLiteDatabase dbObj;
    // Version code
    private static final int DATABASE_VERSION = 1;

    // Table schema, column names
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TITLE = "Title";
    public static final String KEY_DATE = "Date";
    public static final String KEY_START = "Start";
    public static final String KEY_END = "End";
    public static final String KEY_LOCATION = "Location";
    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_URL = "URL";
    public static final String KEY_LATITUDE = "Latitude";
    public static final String KEY_LONGITUDE = "Longitude";
    public static final String KEY_FOOD = "Food";
    public static final String KEY_MAJOR = "Major";
    public static final String KEY_EVENT_TYPE = "Event_Type";
    public static final String KEY_PROGRAM_TYPE = "Program_Type";
    public static final String KEY_YEAR = "Year";
    public static final String KEY_GREEK_SOCIETY = "Greek_Society";
    public static final String KEY_GENDER = "Gender";

    // SQL query to create the table for the first time
    // Data types are defined below
    private static final String CREATE_TABLE_ENTRIES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_EVENT_ENTRIES
            + "("
            + KEY_ROWID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE
            + " TEXT, "
            + KEY_DATE
            + " TEXT, "
            + KEY_START
            + " TEXT, "
            + KEY_END
            + " TEXT, "
            + KEY_LOCATION
            + " TEXT, "
            + KEY_DESCRIPTION
            + " TEXT, "
            + KEY_URL
            + " TEXT, "
            + KEY_LATITUDE
            + " DOUBLE, "
            + KEY_LONGITUDE
            + " DOUBLE, "
            + KEY_FOOD
            + " INT, "
            + KEY_EVENT_TYPE
            + " INT, "
            + KEY_PROGRAM_TYPE
            + " INT, "
            + KEY_YEAR
            + " INT, "
            + KEY_MAJOR
            + " INT, "
            + KEY_GREEK_SOCIETY
            + " INT, "
            + KEY_GENDER
            + " INT "
            + ");";

    private static final String[] mColumnList = new String[]{KEY_ROWID,
            KEY_TITLE, KEY_DATE, KEY_START, KEY_END,
            KEY_LOCATION, KEY_DESCRIPTION, KEY_URL, KEY_LATITUDE, KEY_LONGITUDE, KEY_FOOD, KEY_EVENT_TYPE,
            KEY_PROGRAM_TYPE, KEY_YEAR, KEY_MAJOR, KEY_GREEK_SOCIETY, KEY_GENDER};

    public CampusEventDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public void deleteAllEvents() {
        SQLiteDatabase dbObj = getWritableDatabase();
        dbObj.delete(CampusEventDbHelper.TABLE_EVENT_ENTRIES, null, null);
    }

    // Insert a item given each column value
    public long insertEntry(CampusEvent event) {

        ContentValues value = new ContentValues();

        value.put(KEY_TITLE, event.getmTitle());
        value.put(KEY_DATE, event.getmDateTimeInMillis());

        //THIS NEEDS TO BE CHANGED
        value.put(KEY_START, event.getmDateTimeInMillis());
        value.put(KEY_END, event.getmDateTimeInMillis());

        value.put(KEY_LOCATION, event.getmLocation());
        value.put(KEY_DESCRIPTION, event.getmDescription());
        value.put(KEY_URL, event.getmUrl());
        value.put(KEY_LATITUDE, event.getmLatitude());
        value.put(KEY_LONGITUDE, event.getmLongitude());
        value.put(KEY_FOOD, event.getmFood());
        value.put(KEY_EVENT_TYPE, event.getmEventType());
        value.put(KEY_PROGRAM_TYPE, event.getmProgramType());
        value.put(KEY_YEAR, event.getmYear());
        value.put(KEY_MAJOR, event.getmMajor());
        value.put(KEY_GENDER, event.getmGender());
        value.put(KEY_GREEK_SOCIETY, event.getmGreekSociety());
        dbObj = getWritableDatabase();
        long id = dbObj.insert(TABLE_EVENT_ENTRIES, null, value);
        dbObj.close();
        return id;
    }

    // Remove a entry by giving its index
    public void removeEvent(long rowIndex) {
        SQLiteDatabase dbObj = getWritableDatabase();
        dbObj.delete(TABLE_EVENT_ENTRIES, KEY_ROWID + "=" + rowIndex, null);
        dbObj.close();
    }

    // Query a specific entry by its index. Return a cursor having each column
    // value
    public CampusEvent fetchEventByIndex(long rowId) throws SQLException {
        SQLiteDatabase dbObj = getReadableDatabase();
        CampusEvent event = null;

        Cursor cursor = dbObj.query(true, TABLE_EVENT_ENTRIES, mColumnList,
                KEY_ROWID + "=" + rowId, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            event = cursorToEvent(cursor);
        }

        cursor.close();
        dbObj.close();

        return event;
    }

    // Query the entire table, return all rows
    public ArrayList<CampusEvent> fetchEvents() {
        SQLiteDatabase dbObj = getReadableDatabase();
        ArrayList<CampusEvent> entryList = new ArrayList<CampusEvent>();

        Cursor cursor = dbObj.query(TABLE_EVENT_ENTRIES, mColumnList, null,
                null, null, null, null);

        while (cursor.moveToNext()) {
            CampusEvent event = cursorToEvent(cursor);
            entryList.add(event);
        }

        cursor.close();
        dbObj.close();

        return entryList;
    }

    private CampusEvent cursorToEvent(Cursor cursor) {
        CampusEvent event = new CampusEvent();
        event.setmId(cursor.getLong(cursor.getColumnIndex(KEY_ROWID)));
        event.setmDateTime(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
        event.setmTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
        event.setmStart(cursor.getString(cursor.getColumnIndex(KEY_START)));
        event.setmEnd(cursor.getString(cursor.getColumnIndex(KEY_END)));
        event.setmLocation(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
        event.setmDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
        event.setmUrl(cursor.getString(cursor.getColumnIndex(KEY_URL)));
        event.setmLatitude(cursor.getDouble(cursor.getColumnIndex(KEY_LATITUDE)));
        event.setmLongitude(cursor.getDouble(cursor.getColumnIndex(KEY_LONGITUDE)));
        event.setmFood(cursor.getInt(cursor.getColumnIndex(KEY_FOOD)));
        event.setmMajor(cursor.getInt(cursor.getColumnIndex(KEY_MAJOR)));
        event.setmEventType(cursor.getInt(cursor.getColumnIndex(KEY_EVENT_TYPE)));
        event.setmProgramType(cursor.getInt(cursor.getColumnIndex(KEY_PROGRAM_TYPE)));
        event.setmYear(cursor.getInt(cursor.getColumnIndex(KEY_YEAR)));
        event.setmGreekSociety(cursor.getInt(cursor.getColumnIndex(KEY_GREEK_SOCIETY)));
        event.setmGender(cursor.getInt(cursor.getColumnIndex(KEY_GENDER)));
        return event;
    }


    public ArrayList<CampusEvent> eventListFilter (ArrayList<CampusEvent> campusEvents, Filters filter) {
        ArrayList<CampusEvent> newList = new ArrayList<CampusEvent>();
        //if all the filters are zero, return original list
        Log.d(Globals.TAGG, "Showing what the filters are");

        if (filter == null) {
            Log.d(Globals.TAGG, "All filters are null");
            return campusEvents;
        } else {
            Log.d(Globals.TAGG, "Food filter value" + filter.getfFood());
            if (filter.getfFood() == 0 && filter.getfEventType() == 0 && filter.getfProgramType() == 0 && filter.getfGender() == 0 && filter.getfGreekSociety() == 0 && filter.getfMajor() == 0 && filter.getfYear() == 0) {
                return campusEvents;
            }
            if (filter.getfFood() != 0) {
                for (CampusEvent event : campusEvents) {
                    int scaleval = filter.getfFood() - 1;
                    if (event.getmFood() == scaleval) {
                        newList.add(event);
                    }
                }
            }
            if (filter.getfEventType() != 0) {
                for (CampusEvent event : campusEvents) {
                    int scaleval = filter.getfEventType() - 1;
                    if (event.getmEventType() == scaleval) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfProgramType() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getmProgramType() == filter.getfProgramType()) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfYear() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getmYear() == filter.getfYear()) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfMajor() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getmMajor() == filter.getfMajor()) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfGender() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getmGender() == filter.getfGender()) {
                        newList.add(event);
                    }
                }
            }

            if (filter.getfGreekSociety() != 0) {
                for (CampusEvent event : campusEvents) {
                    if (event.getmGreekSociety() == filter.getfGreekSociety()) {
                        newList.add(event);
                    }
                }
            }

            return newList;
        }
    }

}
