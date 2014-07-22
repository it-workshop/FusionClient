package com.technoworks.FusionClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    GPSTracker mGPSTracker;
    Button btnReqQop;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnReqQop = (Button) findViewById(R.id.btnReqQop);

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



    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setSettingsView(MenuItem item)
    {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
