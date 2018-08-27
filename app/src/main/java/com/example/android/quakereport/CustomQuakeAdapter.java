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
        TextView locView = (TextView)customView.findViewById(R.id.location);
        TextView dateView = (TextView)customView.findViewById(R.id.date);

        magView.setText(Float.toString(singleQuakeInfo.getMagnitude()));
        locView.setText(singleQuakeInfo.getLocation());
        dateView.setText(singleQuakeInfo.getDate());

        return customView;

    }
}
