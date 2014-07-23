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

/**
 * Created by Nats on 22.07.2014.
 */
public class GPSTracker extends Service implements LocationListener
    {
        private final Context mContext;
        boolean isGPSEnabled = false;
        boolean isNetworkEnabled = false;
        boolean canGetLocation = false;
        Location mLocation;
        double latitude;
        double longitude;
        protected LocationManager mLocationManager;

        public  GPSTracker(Context context)
        {
            this.mContext = context;
            getLocation();
        }

        public  Location getLocation()
        {
            try
            {
                mLocationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
                isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                //isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (!isGPSEnabled)
                        //&& !isNetworkEnabled)
                {

                }
                else
                {
                    this.canGetLocation = true;
//                    if (isNetworkEnabled)
//                    {
//                        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
//                        Log.d("Network", "Network");
//                        if (mLocationManager != null)
//                        {
//                            mLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                            if (mLocation != null)
//                            {
//                                latitude = mLocation.getLatitude();
//                                longitude = mLocation.getLongitude();
//                            }
//                        }
//                    }
                    if (isGPSEnabled)
                    {
                        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                        Log.d("GPS", "GPS");
                        if (mLocationManager != null)
                        {
                            mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (mLocation != null)
                            {
                                latitude = mLocation.getLatitude();
                                longitude = mLocation.getLongitude();
                                Log.d("GPS", "lat = " + latitude + " long " + longitude);
                            }

                        }
                    }
                }
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }

            return mLocation;
        }

        public void stopUsingGPS()
        {
            if (mLocationManager != null)
            {
                mLocationManager.removeUpdates(GPSTracker.this);
            }
        }

        public double getLatitude()
        {
            if (mLocation != null)
            {
                latitude = mLocation.getLatitude();
            }
            return latitude;
        }

        public double getLongitude()
        {
            if (mLocation != null)
            {
                longitude = mLocation.getLongitude();
            }
            return longitude;
        }

        public  boolean canGetLocation()
        {
            return this.canGetLocation;
        }

        public void showSettingsAlert()
        {
            AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);
            mAlertDialog.setTitle("GPS Settings");
            mAlertDialog.setMessage("GPS is disable. Do you want to turn it on?");
            mAlertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    mContext.startActivity(intent);
                }
            });

            mAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            mAlertDialog.show();
        }

        @Override
        public void onLocationChanged(Location location)
        {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {

        }

        @Override
        public void onProviderEnabled(String provider)
        {

        }

        @Override
        public void onProviderDisabled(String provider)
        {

        }

        @Override
        public IBinder onBind(Intent intent)
        {
            return null;
        }
    }