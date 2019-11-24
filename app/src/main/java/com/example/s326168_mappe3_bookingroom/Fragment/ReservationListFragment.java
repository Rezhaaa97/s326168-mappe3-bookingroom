package com.example.s326168_mappe3_bookingroom.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.example.s326168_mappe3_bookingroom.Adapters.ReservationListAdapter;
import com.example.s326168_mappe3_bookingroom.MapsActivity;
import com.example.s326168_mappe3_bookingroom.Model.Reservation;
import com.example.s326168_mappe3_bookingroom.R;
import java.util.List;

public class ReservationListFragment extends Fragment {
    private ListView listView;
    private ReservationListAdapter adapter;
    private MapsActivity mapActivity;

    public ReservationListFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mapActivity = new MapsActivity();
        View view = inflater.inflate(R.layout.fragment_reservasjon_liste, container, false);
        listView = view.findViewById(R.id.listview);
        List<Reservation> reservations = mapActivity.getReservations();
        adapter = new ReservationListAdapter(getContext(), R.layout.reservasjon_liste_view, reservations);
        listView.setAdapter(adapter);
        return view;
    }

}
