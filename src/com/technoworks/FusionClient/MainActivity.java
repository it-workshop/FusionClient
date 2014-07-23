package com.technoworks.FusionClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    private int currentViewID;

    //GPSTracker mGPSTracker;
    //Button btnReqQop;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*btnReqQop = (Button) findViewById(R.id.);

        btnReqQop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mGPSTracker = new GPSTracker(MainActivity.this);
                if (mGPSTracker.canGetLocation())
                {
                    double latitude = mGPSTracker.getLatitude();
                    double longitude = mGPSTracker.getLongitude();

                    Toast.makeText(getApplicationContext(), "lat = " + latitude + " long " + longitude, Toast.LENGTH_LONG).show();
                }
                else
                {
                    mGPSTracker.showSettingsAlert();
                }

            }
        });
        */


    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setSettingsView(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void setContentView(int layoutResID) {
        this.currentViewID = layoutResID;
        super.setContentView(layoutResID);
    }

    public void setNextView(View v){
        switch (currentViewID){
            case R.layout.main:
                setContentView(R.layout.request_parameters);
                break;
            case R.layout.request_parameters:
                //TODO check parameters, using GPS
                setContentView(R.layout.copter_waiting);
                break;
            case R.layout.copter_waiting:
                //TODO GPS sending, waiting
                setContentView(R.layout.copter_send);
                break;
            case R.layout.copter_send:
                //TODO что-нибудь с доставкой сообщений
                setContentView(R.layout.main);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        switch (currentViewID){
            case R.layout.main:
            case R.layout.request_parameters:
                super.onBackPressed();
                break;
            case R.layout.copter_waiting:
            case R.layout.copter_send:
                //в самый последний момент. хз что делать. Фигню с вопросом - подтверждением?
                break;
            default:
                throw new RuntimeException("unknown layout");
        }
    }
}
