package com.github.mmarchan.roger.services;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.github.mmarchan.roger.tools.Location;

import java.io.IOException;
import java.util.List;

public class GeocodingService {
    private Geocoder geocoder;
    public GeocodingService(Context context){
        this.geocoder = new Geocoder(context);
    }

    public Location getLocationByName(String name) {
        Location res = new Location();
        try {
            List<Address> adresses = geocoder.getFromLocationName(name, 1);
            res.setLatitude(adresses.get(0).getLatitude());
            res.setLatitude(adresses.get(0).getLongitude());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return res;
    }

}
