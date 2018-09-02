package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<CustomQuakeInfo>> {

    public EarthquakeLoader(Context context) {
        super(context);
    }
    @Override
    public ArrayList<CustomQuakeInfo> loadInBackground() {

        ArrayList<CustomQuakeInfo> qInfo = QueryUtils.fetchEarthquakeData(EarthquakeActivity.USGS_REQUEST_URL);
        return qInfo;
    }
}
