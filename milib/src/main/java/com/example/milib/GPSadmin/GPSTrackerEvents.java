package com.example.milib.GPSadmin;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by juan.jusue on 15/01/2018.
 */

public class GPSTrackerEvents implements LocationListener {

    GPSTracker gpsTracker;

    public GPSTrackerEvents(GPSTracker gpsTracker) {
        this.gpsTracker = gpsTracker;
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.v("GPSTrackerEvents","HA CAMIADO DE POSICION: "+location);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
