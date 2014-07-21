package com.technoworks.FusionClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lgor on 21.07.14.
 */
public class Settings extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void saveProperties(View v){
        EditText serverAddress = (EditText) findViewById(R.id.serverAddress);
        EditText email = (EditText) findViewById(R.id.email);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkShowMeOnMap);
        Toast.makeText(this, "data saved, server : " + serverAddress.getText() + "\n email = " + email.getText() +
                "\n show me = " + checkBox.isChecked(), Toast.LENGTH_SHORT).show();
    }

    public void validate(View v){
        Log.d("mylog","validate");
    }

    @Override
    public void onBackPressed() {
        // if settings good, save and exit
        super.onBackPressed();
    }


}
