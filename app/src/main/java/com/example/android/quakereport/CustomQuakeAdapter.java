package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomQuakeAdapter extends ArrayAdapter<CustomQuakeInfo> {


    public CustomQuakeAdapter(@NonNull Context context, ArrayList<CustomQuakeInfo> quakeDetails) {
        super(context,R.layout.custom_layout, quakeDetails);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater sidsLayoutInflater =  LayoutInflater.from(getContext());
        View customView = sidsLayoutInflater.inflate(R.layout.custom_layout,parent,false);

        CustomQuakeInfo singleQuakeInfo = getItem(position);
        TextView magView = (TextView)customView.findViewById(R.id.magnitude);
        TextView offLocView = (TextView)customView.findViewById(R.id.locationOffset);
        TextView locView = (TextView)customView.findViewById(R.id.primaryLocation);
        TextView dateView = (TextView)customView.findViewById(R.id.date);
        TextView timeView = (TextView)customView.findViewById(R.id.time);

        magView.setText(Float.toString(singleQuakeInfo.getMagnitude()));

        //in this section the String will be split at "of "
        String primaryLocation, locationOffset;
        String location = singleQuakeInfo.getLocation();
        if(location.contains("of "))
        {
            String[] strArr = location.split("of ");
            locationOffset = strArr[0] +" of ";
            primaryLocation = strArr[1];


        }
        else
        {
            locationOffset = "Near the";
            primaryLocation = location;

        }



        offLocView.setText(locationOffset);
        locView.setText(primaryLocation);



        dateView.setText(singleQuakeInfo.getDate());
        timeView.setText(singleQuakeInfo.getTime());

        return customView;

    }
}
