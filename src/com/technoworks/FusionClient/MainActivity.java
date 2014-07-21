package com.technoworks.FusionClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    static int count=0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        myLog("onCreate");
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            myLog("instance is null");
        } else {
            myLog("loaded : "+ savedInstanceState.get("test"));
        }
        setContentView(R.layout.main);
    }

    @Override
    protected void onResume() {
        myLog("on Resume");
        super.onResume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        myLog("onRestore :");
        if (savedInstanceState == null){
            myLog("instance is null");
        } else {
            myLog("loaded : "+ savedInstanceState.get("test"));
        }
        super.onRestoreInstanceState(savedInstanceState);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("test","we was saved");
        myLog("we try to save");
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

    public static void myLog(String s){
        Log.d("mylog",s);
    }
}
