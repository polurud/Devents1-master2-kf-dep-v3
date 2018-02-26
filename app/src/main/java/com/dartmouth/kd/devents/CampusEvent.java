package com.dartmouth.kd.devents;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by kathrynflattum on 2/25/18.
 */

public class CampusEvent {

    private Long mId;
    private String mTitle;
    private String mLocation;
    private String mDescription;
    private Calendar mDate;
    private Calendar mStart;
    private Calendar mEnd;
    private URL mUrl;


    public CampusEvent(){
        this.mTitle = "";
        this.mLocation = "";
        this.mDescription = "";
        this.mDate = Calendar.getInstance();
        this.mStart = Calendar.getInstance();
        this.mEnd = Calendar.getInstance();
        this.mUrl = null;


    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long id) {
        this.mId = id;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String title) {
        this.mTitle = title;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String location) {
        this.mLocation = location;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String description) {
        this.mDescription = description;
    }

    public Calendar getmDate() {
        return mDate;
    }

    public void setmDate(int year, int monthOfYear, int dayOfMonth) {
        mDate.set(year, monthOfYear, dayOfMonth);
    }

    public long getmDateTimeInMillis() {
        return mDate.getTimeInMillis();
    }

    public void setmDateTime(Calendar dateTime) {
        this.mDate = dateTime;

    }

    public void setmDateTime(long timestamp) {
        this.mDate.setTimeInMillis(timestamp);

    }

    public void setmStart(int hourOfDay, int minute) {
        mStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mStart.set(Calendar.MINUTE, minute);
        mStart.set(Calendar.SECOND, 0);
    }

    public Calendar getmStart() {
        return mStart;
    }

    public void setmEnd(int hourOfDay, int minute) {
        mEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mEnd.set(Calendar.MINUTE, minute);
        mEnd.set(Calendar.SECOND, 0);
    }

    public Calendar getmEnd() {
        return mEnd;
    }

    public URL getmUrl() {
        return mUrl;
    }

    public void setmUrl(URL url) {
        this.mUrl = url;
    }

}