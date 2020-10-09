package com.github.mmarchan.roger.tools;

public class Location {
    private double longitude;
    private double latitude;

    public Location(){
        this.longitude = 0;
        this.latitude = 0;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
