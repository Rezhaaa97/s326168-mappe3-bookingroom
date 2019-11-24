package com.example.s326168_mappe3_bookingroom;

import android.os.AsyncTask;

import com.example.s326168_mappe3_bookingroom.Model.Reservation;
import com.example.s326168_mappe3_bookingroom.Model.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Service extends AsyncTask<String, Void, String> {
    List<Room> rooms = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();
    @Override
    protected String doInBackground(String... urls) {
        String retur = "";
        String s = "";
        String output = "";

        for(int i= 0; i<urls.length; i++){
            try {
            URL theUrl = new URL(urls[i]);
            HttpURLConnection conn = (HttpURLConnection)
                    theUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader( (conn.getInputStream())));
            System.out.println("Output from Server .... \n"); while ((s = br.readLine()) != null) {
                output = output + s;
            }
            conn.disconnect();

            try {
                if(!output.isEmpty()){

                    JSONArray json = new JSONArray(output);

                        if(urls[i].equals("http://student.cs.hioa.no/~s326168/bookingout.php")) {
                            for (int j = 0; j < json.length(); j++) {
                                JSONObject jsonobject = json.getJSONObject(j);
                                String firstName = jsonobject.getString("firstname");
                                String lastName = jsonobject.getString("lastname");
                                String room = jsonobject.getString("room");
                                String date = jsonobject.getString("date");
                                String time = jsonobject.getString("time");
                                reservations.add(new Reservation(firstName, lastName, room, date, time));
                            }
                        }
                        else if(urls[i].equals("http://student.cs.hioa.no/~s326168/roomout.php")){
                                for (int j = 0; j < json.length(); j++) {
                                    JSONObject jsonobject = json.getJSONObject(j);
                            String name = jsonobject.getString("name");
                            String description = jsonobject.getString("description");
                            String latitude = jsonobject.getString("latitude");
                            String longitude = jsonobject.getString("longitude");
                            rooms.add(new Room(name , description, latitude, longitude));
                           // List<Room> roomList = rooms;
                        }

                    }
                    output = "";
                } else {
                    return retur;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            return "Noe gikk feil";
            }
        }
        return retur;
    }

    public List<Room> getRooms(){
        return this.rooms;
    }

    public List<Reservation> getReservations(){
        return this.reservations;
    }
}