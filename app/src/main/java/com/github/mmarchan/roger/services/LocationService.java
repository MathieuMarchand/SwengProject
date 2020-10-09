package com.github.mmarchan.roger.services;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.github.mmarchan.roger.tools.Location;

public class LocationService {
    private final static int REQUEST_LOCATION = 44;
    private Context context;

    public LocationService(Context context) {
        this.context = context;
    }

    public Location getLocation() {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location res = new Location();

        if (!(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            android.location.Location loc = lm.getLastKnownLocation(lm.getBestProvider(new Criteria(), false));
            res.setLatitude(loc.getLatitude());
            res.setLongitude(loc.getLongitude());
        }

        return res;
    }
}
