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
            return true;
        }

        @Override
        public String toString() {
            return "server adress = " + server + "\nnickname = " + nickname + "\nsendGPS = " + sendGPSWhenHide;
        }
    }

    private SettingsState settingsNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (settingsNow == null) {
            settingsNow = loadSettings(this);
            ((EditText) findViewById(R.id.serverAddress)).setText(settingsNow.server, TextView.BufferType.EDITABLE);
            ((EditText) findViewById(R.id.email)).setText(settingsNow.nickname, TextView.BufferType.EDITABLE);
            ((CheckBox) findViewById(R.id.checkShowMeOnMap)).setChecked(settingsNow.sendGPSWhenHide);
        }
    }

    public void savePropertiesClick(View v) {
        // можно коварно отредактировать текст так, что не вызовется onFieldClick.
        // чтобы наверняка, вызовем ещё разок
        onFieldsClick(null);
        if (!settingsNow.isCorrect()) return;
        saveSettings(settingsNow);
    }

    public void onFieldsClick(View v) {
        settingsNow.nickname = ((EditText) findViewById(R.id.email)).getText().toString();
        settingsNow.server = ((EditText) findViewById(R.id.serverAddress)).getText().toString();
        settingsNow.sendGPSWhenHide = ((CheckBox) findViewById(R.id.checkShowMeOnMap)).isChecked();
        int color = settingsNow.isCorrect() ? 0xFF00FF00 : 0x77FF0000;
        findViewById(R.id.savePropertiesButton).setDrawingCacheBackgroundColor(color);
        myLog("onFieldsClick, settings = " + settingsNow);
    }

    @Override
    public void onBackPressed() {
        settingsNow = null;
        super.onBackPressed();
    }

    //naive realization
    private boolean saveSettings(SettingsState settings) {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear(); //нужно ли?
        editor.putString("server", settings.server);
        editor.putString("nickname", settings.nickname);
        editor.putBoolean("sendGPS", settings.sendGPSWhenHide);
        boolean success = editor.commit();
        myLog("settings saving = " + success + " :\n" + settings);
        return success;
    }

    public static SettingsState loadSettings(Activity activity) {
        SharedPreferences pref = activity.getPreferences(MODE_PRIVATE);
        SettingsState settings = new SettingsState();
        settings.server = pref.getString("server", "def server value");
        settings.nickname = pref.getString("nickname", "");
        settings.sendGPSWhenHide = pref.getBoolean("sendGPS", false);
        myLog("settings loading :\n" + settings);
        return settings;
    }

    private static void myLog(String s) {
        Log.d("mylog", s);
    }
}
