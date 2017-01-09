package com.matigurten.tom.remotecontrol;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.matigurten.tom.remotecontrol.common.SharedPref;

/**
 * Created by Mati on 08/01/2017.
 */


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_actiivty_layout);

        RemoteTypeEnum remoteType = SharedPref.getRemoteType(this);
        Log.d(TAG, "starting activity " + remoteType.name());
        final Intent i = new Intent(getApplicationContext(), remoteType.getActivityClass());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
            }
        }, 2000);
    }


}
