package com.technoworks.FusionClient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

/**
 * активити для настроек.
 * Created by lgor on 21.07.14.
 */
public class SettingsActivity extends Activity {

    public static class SettingsState {
        public String server, nickname;
        public boolean sendGPSWhenHide;

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof SettingsState)) return false;
            SettingsState s = (SettingsState) o;
            return server.equals(s.server) &&
                    nickname.equals(s.nickname) &&
                    (sendGPSWhenHide == s.sendGPSWhenHide);
        }

        private boolean isCorrect() {
            return nickname.contains("@");
        }

        // 4debug
        @Override
        public String toString() {
            return "server address = " + server + "\nnickname = " + nickname + "\nsendGPS = " + sendGPSWhenHide;
        }
    }

    private SettingsState settingsNow;

    private EditText
            fieldServer,    //server address
            userID;         //user identifier - email or nickname
    private CheckBox boxAboutGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        boxAboutGPS = (CheckBox) findViewById(R.id.checkShowMeOnMap);
        fieldServer = (EditText) findViewById(R.id.serverAddress);
        userID = (EditText) findViewById(R.id.email);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (settingsNow == null) {
            settingsNow = loadSettings(this);

            fieldServer.setText(settingsNow.server, TextView.BufferType.EDITABLE);
            userID.setText(settingsNow.nickname, TextView.BufferType.EDITABLE);
            boxAboutGPS.setChecked(settingsNow.sendGPSWhenHide);
        }
    }

    public void savePropertiesClick(View v) {
        // можно коварно отредактировать текст так, что не вызовется onFieldClick.
        // чтобы наверняка, вызовем ещё разок. такой вот костыль
        onFieldsClick(null);
        if (settingsNow.isCorrect()) {
            saveSettings(settingsNow);
        }
    }

    public void onFieldsClick(View v) {
        settingsNow.nickname = userID.getText().toString();
        settingsNow.server = fieldServer.getText().toString();
        settingsNow.sendGPSWhenHide = boxAboutGPS.isChecked();

        int color = settingsNow.isCorrect() ? 0xFF00FF00 : 0x77FF0000;
        findViewById(R.id.savePropertiesButton).setBackgroundColor(color);
        //myLog("onFieldsClick, settings = " + settingsNow);
    }

    @Override
    public void onBackPressed() {
        settingsNow = null;
        super.onBackPressed();      // return to previous activity
    }

    //naive realization
    private boolean saveSettings(SettingsState settings) {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("server", settings.server);
        editor.putString("userID", settings.nickname);
        editor.putBoolean("sendGPS", settings.sendGPSWhenHide);

        return editor.commit();
        //myLog("settings saving = " + success + " :\n" + settings);
    }

    /**
     * любое активити сможет узнать настройки
     */
    public static SettingsState loadSettings(Activity activity) {
        SharedPreferences pref = activity.getPreferences(MODE_PRIVATE);
        SettingsState settings = new SettingsState();

        settings.server = pref.getString("server", "def server value");
        settings.nickname = pref.getString("userID", "");
        settings.sendGPSWhenHide = pref.getBoolean("sendGPS", false);

        //myLog("settings loading :\n" + settings);
        return settings;
    }

    private static void myLog(String s) {
        Log.d("mylog", s);
    }
}
