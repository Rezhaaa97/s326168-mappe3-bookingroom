package com.example.s326168_mappe3_bookingroom.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.s326168_mappe3_bookingroom.MapsActivity;
import com.example.s326168_mappe3_bookingroom.Model.Room;
import com.example.s326168_mappe3_bookingroom.R;
import com.example.s326168_mappe3_bookingroom.Service;

public class NewRoomFragment extends Fragment {
    EditText nameEditText;
    EditText detailsEditText;
    EditText latitudeEditText;
    EditText longittudeEditText;



    public NewRoomFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_room2, container, false);
        nameEditText = view.findViewById(R.id.room_name);
        detailsEditText = view.findViewById(R.id.room_details);
        latitudeEditText = view.findViewById(R.id.latitude);
        longittudeEditText = view.findViewById(R.id.longitude);
        final Button addBtn = view.findViewById(R.id.add_btn);
        addBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addRoom(nameEditText.getText().toString(), detailsEditText.getText().toString(),
                        latitudeEditText.getText().toString(), longittudeEditText.getText().toString());
            }
        } );
        return view;
    }

    public void addRoom(String name, String description,
                        String latitude, String longitude){


      if(isRoomExist(name)){
          Toast.makeText(getContext(),"Rom finnes allerede!",Toast.LENGTH_LONG);
        }
        else{
            String url = "http://student.cs.hioa.no/~s326168/roominn.php?name=" + name + "&description=" + description
                    + "&latitude=" + latitude + "&longitude=" + longitude ;
            Service service = new Service();
            service.execute(url);
            Toast.makeText(getContext(),"Rom lagt til!",Toast.LENGTH_LONG);
            changeToMapActivity();
        }
    }

    public boolean isRoomExist(String roomName){
        MapsActivity activity = new MapsActivity();
        for(Room r: activity.getRooms() ){
            if(r.getName().equals(roomName)) return true;
        }
        return false;
    }

    public void changeToMapActivity(){
        new Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent intent = new Intent(getContext(), MapsActivity.class);
                            startActivity(intent);
                        }
                    }, 3000);
    }


}
