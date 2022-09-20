package com.example.quakerreport;

public class EarthQuick {

    private double magnitude;
    private String location;
    private String date;
    private long mTimeInMillisecond;
    private String mUrl;



    public EarthQuick(double magnitude,String location,long mTimeInMillisecond,String mUrl)
    {
        this.magnitude=magnitude;
        this.location=location;
        this.mTimeInMillisecond=mTimeInMillisecond;
        this.mUrl=mUrl;
    }

    public double getMagnitude(){
        return magnitude;
    }
    public String getLocation(){
        return location;
    }
    public String getUrl(){
        return mUrl;
    }
    public long getmTimeInMillisecond(){return mTimeInMillisecond;}

}
