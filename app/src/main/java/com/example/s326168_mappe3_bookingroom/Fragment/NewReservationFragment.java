package com.example.s326168_mappe3_bookingroom.Fragment;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.s326168_mappe3_bookingroom.MapsActivity;
import com.example.s326168_mappe3_bookingroom.Model.Room;
import com.example.s326168_mappe3_bookingroom.R;
import com.example.s326168_mappe3_bookingroom.Service;

import java.util.Calendar;
import java.util.List;

public class NewReservationFragment extends Fragment {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String room;
    private String date;
    private String time;
    private RadioButton hour1;
    private RadioButton hour2;
    private RadioButton hour3;
    private RadioButton hour4;
    private Spinner buildingDropdown;
    private Spinner roomDropdown;
    private Button dateBtn;
    private Button reservBtn;
    private EditText firstnameEditView;
    private EditText lastnameEditView;
    private RadioGroup radioGroup;
    Service service;

    public NewReservationFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservasjon, container, false);

        dateBtn = view.findViewById(R.id.date);
        dateBtn.setTransformationMethod(null);
        hour1 = view.findViewById(R.id.time1);
        hour2 = view.findViewById(R.id.time2);
        hour3 = view.findViewById(R.id.time3);
        hour4 = view.findViewById(R.id.time4);
        firstnameEditView = view.findViewById(R.id.firstname);
        lastnameEditView = view.findViewById(R.id.lastname);
        reservBtn = view.findViewById(R.id.reserver_btn);
        roomDropdown = view.findViewById(R.id.room);
        radioGroup = view.findViewById(R.id.radio);
        service = new Service();

        showDateDropdown(dateBtn);
        showRoomDropdown(roomDropdown);

        reservBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReservation(firstnameEditView.getText().toString(), lastnameEditView.getText().toString(),
                        roomDropdown.getSelectedItem().toString(), showDateDropdown(dateBtn), time);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.time1:
                        time = "08:30-10:15";
                        break;
                    case R.id.time2:
                        time = "10:30-12:15";
                        break;
                    case R.id.time3:
                        time = "12:30-14:15";
                        break;
                    case R.id.time4:
                        time = "14:30-16:15";
                        break;
                }
            }
        });
        return view;
    }


    public String showDateDropdown(final Button dateBtn) {


        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                NewReservationFragment.this.date = date;
                dateBtn.setText("Valgt date: " + date);
                room = roomDropdown.getSelectedItem().toString();
            }
        };

        return date;
    }

    public void addReservation(String firstname, String lastname, String room,
                               String date, String time) {

        ReservationListFragment listeFragment = new ReservationListFragment();
        String url = "http://student.cs.hioa.no/~s326168/bookingin.php?firstname=" + firstname + "&lastname=" + lastname
                + "&room=" + room + "&date=" + date + "&time=" + time;
        if (isFormValid(firstname, lastname , room, date, time)) {
            service.execute(url);
            Toast.makeText(getContext(),"Lagt til ny bestilling",Toast.LENGTH_LONG);
            showAllReservations(listeFragment);
        } else {
            Toast.makeText(getContext(),"Vennligst dobbel sjekk alle feltene",Toast.LENGTH_LONG);
        }


    }


    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        if (fragment.isHidden()) {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }


    public void showAllReservations(final Fragment fragment) {
        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        setFragment(fragment);
                    }
                }, 3000);
    }


    public boolean isFormValid(String firstname, String lastname, String rom,
                               String dato, String tidspunkt) {

        if (firstname == null || firstname.trim().isEmpty()
                || lastname == null || lastname.trim().isEmpty()
                || rom.equals("Velg room") || dato == null || tidspunkt == null) {
            return false;
        }
        return true;
    }



    public String showRoomDropdown(Spinner spinner) {
        MapsActivity mapsActivity = new MapsActivity();
        final String[] rom = new String[1];
        String[] romListe = getRoomNames(mapsActivity.getRooms());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, romListe);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rom[0] = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return rom[0];

    }


    public String[] getRoomNames(List<Room> rooms){
        StringBuilder roomArray = new StringBuilder();
        for(Room r: rooms){
            roomArray.append(r.getName()).append(","); }
        return roomArray.toString().split(","); }


}
