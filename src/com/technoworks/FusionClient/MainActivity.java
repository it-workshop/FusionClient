package com.technoworks.FusionClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setSettingsView(MenuItem item){
        setContentView(R.layout.settings);
    }

    @Override
    public void onBackPressed() {
        switch (currentViewID){
            case R.layout.settings:
                setContentView(R.layout.main);
                return;
            default:
                super.onBackPressed();
        }
    }

    private int currentViewID;
    private int previousViewID;

    @Override
    public void setContentView(final int layoutResID) {
        if (currentViewID == layoutResID) return;
        previousViewID = currentViewID;
        currentViewID = layoutResID;
        super.setContentView(layoutResID);
    }

    public void saveProperties(View v){
        EditText serverAddress = (EditText) findViewById(R.id.serverAddress);
        EditText email = (EditText) findViewById(R.id.email);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkShowMeOnMap);
        Toast.makeText(this, "data saved, server : " + serverAddress.getText() + "\n email = " + email.getText() +
                "\n show me = " + checkBox.isChecked(), Toast.LENGTH_SHORT).show();

        setContentView(previousViewID);
    }
}
