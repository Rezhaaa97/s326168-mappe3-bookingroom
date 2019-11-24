package com.example.s326168_mappe3_bookingroom.Model;

public class Room {
    private String name;
    private String description;
    private String latitude;
    private String longitude;
    private String reservationInfo;

    public Room(String name, String description, String latitude, String longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reservationInfo = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setReservationInfo(String reservationInfo) {
        this.reservationInfo = reservationInfo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getReservationInfo() {
        return reservationInfo;
    }
}
