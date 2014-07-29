package com.technoworks.FusionClient;

import android.app.*;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;


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

    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.d(LOG_TAG_SERVICE, "Service onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(LOG_TAG_SERVICE, "Service onStartCommand");

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(LOG_TAG_SERVICE, "Service onBind");
        return null;
    }
}