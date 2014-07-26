package com.technoworks.FusionClient;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Nats on 26.07.2014.
 */
public class GPSModule implements LocationListener
{
    final String LOG_TAG_LOCATION = "App Location";
    final int DIALOG_SETTINGS = 1;

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
        onCreateDialog(DIALOG_SETTINGS);
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        Log.d(LOG_TAG_LOCATION, "Location provider disabled");
    }

    public Dialog onCreateDialog(int id)
    {
        if (id == DIALOG_SETTINGS)
        {
            AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(this);
            mAlertDialog.setTitle(R.string.dialog_settings_title);
            mAlertDialog.setMessage(R.string.dialog_setting_msg);
            mAlertDialog.setPositiveButton(R.string.dialog_setting_btnYes, mClickListener);
            mAlertDialog.setNegativeButton(R.string.dialog_setting_btnNo, mClickListener);

            return mAlertDialog.create();
        }
        return super.onCreateDialog(id);
    }

    OnClickListener mClickListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case Dialog.BUTTON_POSITIVE:
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    finish();
                    break;
            }
        }
    };

}
