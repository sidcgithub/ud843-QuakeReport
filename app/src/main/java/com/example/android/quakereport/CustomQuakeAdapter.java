package com.example.android.quakereport;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import android.support.v4.content.*;
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

        magView.setText(doubleFormatter(singleQuakeInfo.getMagnitude()));

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

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getContext().getColor(getMagnitudeColor(singleQuakeInfo.getMagnitude()));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return customView;

    }

    public static String doubleFormatter(double x)
    {
        DecimalFormat df = new DecimalFormat("0.0");
        String formattedNumber = df.format(x);
        return formattedNumber;

    }

    public static int getMagnitudeColor(double magnitude)
    {
        int magInt = (int)Math.floor(magnitude);
        int color;

        switch(magInt)
        {
            case 0: ;
            case 1:color =  R.color.magnitude1;
            break;
            case 2:color =  R.color.magnitude2;
            break;
            case 3:color =  R.color.magnitude3;
            break;
            case 4:color =  R.color.magnitude4;
            break;
            case 5:color =  R.color.magnitude5;
            break;
            case 6:color =  R.color.magnitude6;
            break;
            case 7:color =  R.color.magnitude7;
            break;
            case 8:color =  R.color.magnitude8;
            break;
            case 9:color =  R.color.magnitude9;
            break;
            case 10:color =  R.color.magnitude10plus;
            break;
            default:color =  R.color.magnitude10plus;



        }
        return color;

    }

}
