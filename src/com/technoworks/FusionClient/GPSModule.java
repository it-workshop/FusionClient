package com.technoworks.FusionClient;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import java.io.*;

/**
 * Created by Nats on 26.07.2014.
 */
public class GPSModule implements LocationListener
{
    final String LOG_TAG_LOCATION = "App Location";

    private double latitude;
    private double longitude;


    @Override
    public void onLocationChanged(Location location)
    {
        if (location != null)
        {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            Log.d(LOG_TAG_LOCATION, "Latitude: " + latitude + ", Longitude: " + longitude);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {
        Log.d(LOG_TAG_LOCATION, "Location provider enabled");
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        Log.d(LOG_TAG_LOCATION, "Location provider disabled");
    }
}
