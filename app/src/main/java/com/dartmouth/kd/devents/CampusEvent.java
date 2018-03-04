package com.dartmouth.kd.devents;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

import java.net.URL;
import java.nio.DoubleBuffer;
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
    private String mDate;
    private String mStart;
    private String mEnd;
    private String mUrl;
    private Double mLatitude;
    private Double mLongitude;
    private int mFood;
    private int mEventType;
    private int mProgramType;
    private int mYear;
    private int mMajor;
    private int mGreekSociety;
    private int mGender;

    public CampusEvent(){
        this.mTitle = "";
        this.mLocation = "";
        this.mDescription = "";
        this.mDate = "";
        this.mStart = "";
        this.mEnd = "";
        this.mUrl = "";
        this.mLatitude = null;
        this.mLongitude = null;
        this.mFood = 0;
        this.mEventType = 0;
        this.mProgramType = 0;
        this.mYear = 0;
        this.mMajor = 0;
        this.mGender = 0;
        this.mGreekSociety=0;


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

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double latitude) {
        this.mLatitude = latitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double longitude) {
        this.mLongitude = longitude;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String description) {
        this.mDescription = description;
    }

    //@Exclude
    public String getmDate() {
        return mDate;
    }

    public void setmDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar date = Calendar.getInstance();
        date.set(year, monthOfYear, dayOfMonth);
        date.toString();
        mDate = date.toString();
    }

    public long getmDateTimeInMillis() {
        long mDate = 0;
        return mDate;
    }


    public void setmDateTime(Calendar dateTime) {
        //this.mDate = dateTime;

    }

    public void setmDateTime(long timestamp) {

        //this.mDate.setTimeInMillis(timestamp);
    }


    public void setmStart(int hourOfDay, int minute) {
        Calendar date = Calendar.getInstance();
        mStart = date.toString();
        //mStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
        //mStart.set(Calendar.MINUTE, minute);
        //mStart.set(Calendar.SECOND, 0);
    }

    public void setmStart(String start){
        this.mStart = start;
        //this.mDate.setTimeInMillis(timestamp);
    }

    public void setmDateTime(String date){
        this.mDate = date;
    }

    //public void setmDate(String d

    //@Exclude
    public String getmStart() {
        return mStart;
    }


    public void setmEnd(int hourOfDay, int minute) {
        Calendar date = Calendar.getInstance();
        mEnd = date.toString();
        //mEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
        //mEnd.set(Calendar.MINUTE, minute);
        //mEnd.set(Calendar.SECOND, 0);
    }

    public void setmEnd(String end){
        this.mEnd = end;
    }

    //@Exclude
    public String getmEnd() {
        return mEnd;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String url) {
        this.mUrl = url;
    }

    public int getmFood(){
        return mFood;
    }

    public void setmFood(int food){
        this.mFood = food;
    }

    public int getmEventType() {
        return mEventType;
    }

    public void setmEventType(int eventType) {
        this.mEventType = eventType;
    }

    public int getmProgramType() {
        return mProgramType;
    }

    public void setmProgramType(int programType) {
        this.mProgramType = programType;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int year) {
        this.mYear = year;
    }

    public int getmMajor() {
        return mMajor;
    }

    public void setmMajor(int major) {
        this.mMajor = major;
    }

    public int getmGreekSociety() {
        return mGreekSociety;
    }

    public void setmGreekSociety(int greekSociety) {
        this.mGreekSociety = greekSociety;
    }

    public int getmGender() {
        return mGender;
    }

    public void setmGender(int gender) {
        this.mGender = gender;
    }

}