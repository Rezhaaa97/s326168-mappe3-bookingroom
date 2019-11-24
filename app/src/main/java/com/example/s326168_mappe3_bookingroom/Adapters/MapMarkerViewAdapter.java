package com.example.s326168_mappe3_bookingroom.Adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.s326168_mappe3_bookingroom.R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;


public class MapMarkerViewAdapter implements InfoWindowAdapter {
    LayoutInflater inflater = null;
    private TextView textViewTitle;

    public MapMarkerViewAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View v = inflater.inflate(R.layout.balloon, null);
        if (marker != null) {
            textViewTitle = v.findViewById(R.id.textViewTitle);
            String snippet = marker.getSnippet();

            if(snippet.trim().isEmpty() ){
                textViewTitle.setText("Du har ingen\n reservation i dag");
            } else {
                textViewTitle.setText("Dagens bestilling \n  "+snippet);
            }

        }
        return (v);
    }

    @Override
    public View getInfoContents(Marker marker) {
        return (null);
    }
}