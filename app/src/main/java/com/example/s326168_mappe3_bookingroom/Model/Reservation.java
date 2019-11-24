package com.example.s326168_mappe3_bookingroom.Model;

public class Reservation {
    private String firstName;
    private String lastName;
    private String room;
    private String date;
    private String time;

    public Reservation(String firstName, String lastName, String room, String date, String time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.room = room;
        this.date = date;
        this.time = time;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
