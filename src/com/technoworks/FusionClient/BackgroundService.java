package com.technoworks.FusionClient;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.*;
import android.provider.Settings;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nats on 22.07.2014.
 */
public class BackgroundService extends Service
{
    final String LOG_TAG_SERVICE = "App Service";

    private LocationManager mLocationManager;
    private GPSModule mGPSModule;
    private long MIN_TIME_TO_UPDATE = 1000 * 60 * 1;

    public void onCreate()
    {
        super.onCreate();
        Log.d(LOG_TAG_SERVICE, "Service onCreate");
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.d(LOG_TAG_SERVICE, "Service onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(LOG_TAG_SERVICE, "Service onStartCommand");
        try
        {
            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            mGPSModule = new GPSModule();
            Log.d(LOG_TAG_SERVICE, "Location run");
            if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_TO_UPDATE, 0, mGPSModule);
                Log.d(LOG_TAG_SERVICE, "GPS Enabled");

            }
            else
            {
                Log.d(LOG_TAG_SERVICE, "GPS Disabled");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(LOG_TAG_SERVICE, "Service onBind");
        return null;
    }




}