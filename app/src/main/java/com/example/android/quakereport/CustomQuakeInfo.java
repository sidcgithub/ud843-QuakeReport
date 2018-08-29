package com.example.android.quakereport;

public class CustomQuakeInfo {

    double magnitude;
    String location, date, time,url;

    public CustomQuakeInfo(double magnitude, String location, String date, String time, String url)
    {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
        this.time = time;
        this.url = url;
    }

    public double getMagnitude()
    {
        return magnitude;
    }

    public String getLocation()
    {
        return location;
    }

    public String getDate()
    {
       return date;
    }

    public String getTime()
    {
        return time;
    }
    public String getUrl()
    {
        return url;
    }
}
