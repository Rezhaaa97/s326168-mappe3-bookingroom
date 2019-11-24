package com.example.s326168_mappe3_bookingroom.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.s326168_mappe3_bookingroom.R;


public class AddFragment extends Fragment {

    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final NewReservationFragment newReservationFragment = new NewReservationFragment();
        final NewRoomFragment newRoomFragment = new NewRoomFragment();

        View view =inflater.inflate(R.layout.fragment_add, container, false);
        Button addReservasjonBtn = view.findViewById(R.id.add_reserv);
        Button addRoom = view.findViewById(R.id.add_room);

        addReservasjonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(newReservationFragment);

            }
        });

        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(newRoomFragment);
            }
        });
        return view;
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        if (fragment.isHidden()) {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }


}
