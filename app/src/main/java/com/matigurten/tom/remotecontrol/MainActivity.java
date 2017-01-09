package com.matigurten.tom.remotecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.matigurten.tom.remotecontrol.common.SharedPref;

/**
 * Created by Mati on 08/01/2017.
 */


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(2000); //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_LONG).show();

        RemoteTypeEnum remoteType = SharedPref.getRemoteType(this);
        Log.d(TAG, "starting activity " + remoteType.name());
        startActivity(new Intent(getApplicationContext(), remoteType.getActivityClass()));
    }

    @Override
    protected  void onPause(Bundle savedInstanceState) {

    }
}
