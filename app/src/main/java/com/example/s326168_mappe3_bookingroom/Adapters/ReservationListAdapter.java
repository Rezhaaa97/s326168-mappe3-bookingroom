package com.example.s326168_mappe3_bookingroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.s326168_mappe3_bookingroom.Model.Reservation;
import com.example.s326168_mappe3_bookingroom.R;

import java.util.List;



public class ReservationListAdapter extends ArrayAdapter<Reservation> {

    private Context context;
    private int resource;
    private List<Reservation> reservationArrayList;

    private static class ViewHolder {
        TextView firstname;
        TextView lastname;
        TextView room;
        TextView date;
        TextView time;

    }

    // Konstruktør
    public ReservationListAdapter(Context context, int resource, List<Reservation> reservationArrayList) {

        super(context, resource, reservationArrayList);
        this.reservationArrayList = reservationArrayList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        String firstName = getItem(position).getFirstName();
        String lastName = getItem(position).getLastName();
        String room = getItem(position).getRoom();
        String date = getItem(position).getDate();
        String time = getItem(position).getTime();
        ViewHolder holder;


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();

            holder.firstname = convertView.findViewById(R.id.firstname);
            holder.lastname = convertView.findViewById(R.id.lastname);
            holder.room = convertView.findViewById(R.id.room);
            holder.date = convertView.findViewById(R.id.date);
            holder.time = convertView.findViewById(R.id.time);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.firstname.setText("Fornavn: " + firstName);
        holder.lastname.setText("Etternavn: " + lastName);
        holder.room.setText("Rom: " + room);
        holder.date.setText("Dato: " + date);
        holder.time.setText("Tidspunkt: " + time);

        return convertView;
    }

}