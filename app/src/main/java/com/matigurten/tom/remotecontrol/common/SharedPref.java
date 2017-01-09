package com.matigurten.tom.remotecontrol.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.matigurten.tom.remotecontrol.RemoteTypeEnum;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fima on 08/01/17.
 */

public class SharedPref {

    public static final String PREFS_NAME = "SETTINGS";
    public static final String ADDRESS = "deviceAddress";
    public static final String RETMOTE_POSITION = "remotePosition";
    private static final String TAG = "SharedPref";
    public static int INNER_R = 50, OUTER_R = 450;

    public static final String[] remoteTypes = new String[] {
            "Remote Control",
            "Joystick"
    };

    public static final String[] remoteActivity = new String[]{
            "RemoteControlActivity",
            "JoystickActivity"
    };

    public enum RemoteType {
        REMOTE_CONTROL,
        JOYSTICK
    }

    public static int getRemotePos(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int pos = prefs.getInt(RETMOTE_POSITION, 0);
        return pos;
    }

    public static void setRemotePos(Context context, int remotePos) {

        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(RETMOTE_POSITION, remotePos);
        editor.commit();
        Log.d(TAG, RETMOTE_POSITION + " set with: " + remotePos);
    }

    public static RemoteTypeEnum getRemoteType(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int pos = prefs.getInt(RETMOTE_POSITION, 0);
        Log.d(TAG, "getRemoteType: " + RemoteTypeEnum.values()[pos]);
        return RemoteTypeEnum.values()[pos];
    }
}
