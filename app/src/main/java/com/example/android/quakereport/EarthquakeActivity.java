/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends Activity implements LoaderManager.LoaderCallbacks<ArrayList<CustomQuakeInfo>>{

    public static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ArrayList<CustomQuakeInfo> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
//
//        EarthquakeAsyncTask eqTask = new EarthquakeAsyncTask();
//        eqTask.execute(USGS_REQUEST_URL);

        getLoaderManager().initLoader(1,null,this).forceLoad();

        // Create a fake list of earthquake locations.





    }

    public void loadUI(ArrayList<CustomQuakeInfo> earthquakeList)
    {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ListAdapter adapter = new CustomQuakeAdapter(this, earthquakeList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakes = earthquakeList;

        //Create an intent to go to the earthquake url
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = earthquakes.get(i).getUrl();
                Intent in = new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse(url));
                startActivity(in);

            }
        });

    }


    @Override
    public Loader<ArrayList<CustomQuakeInfo>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<CustomQuakeInfo>> loader, ArrayList<CustomQuakeInfo> customQuakeInfos) {
        loadUI(customQuakeInfos);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<CustomQuakeInfo>> loader) {
        loadUI(new ArrayList<CustomQuakeInfo>());
    }


    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<CustomQuakeInfo>> {
        protected ArrayList<CustomQuakeInfo> doInBackground(String... urls) {
            ArrayList<CustomQuakeInfo> earthquakeLst = QueryUtils.fetchEarthquakeData(urls[0]);

            return earthquakeLst;
        }



        protected void onPostExecute(ArrayList<CustomQuakeInfo> eList) {

            loadUI(eList);

        }
    }

}
