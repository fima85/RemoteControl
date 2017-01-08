package com.matigurten.tom.remotecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Mati on 08/01/2017.
 */


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(5000); //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_LONG).show();

        startActivity(new Intent(getApplicationContext(), JoystickActivity.class));
    }
}
