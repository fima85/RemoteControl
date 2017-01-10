package com.matigurten.tom.remotecontrol;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.matigurten.tom.remotecontrol.bluetooth.BLConn;
import com.matigurten.tom.remotecontrol.proxy.RemoteProxy;

public abstract class ControllerActivity extends AppCompatActivity {

    RemoteProxy remote = BLConn.getInstance();
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        ProgressBar spinner = (ProgressBar)findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        this.menu = menu;
        menu.findItem(R.id.connect).setEnabled(!BLConn.getInstance().isConnected());
        menu.findItem(R.id.connect).setVisible(!BLConn.getInstance().isConnected());

        menu.findItem(R.id.disconnect).setEnabled(BLConn.getInstance().isConnected());
        menu.findItem(R.id.disconnect).setVisible(BLConn.getInstance().isConnected());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.activity_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        } else if (id == R.id.app_about) {
            startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            return true;
        } else if (id == R.id.connect) {

//            final ProgressBar spinner = (ProgressBar) findViewById(R.id.spinner);
//            if(this instanceof RemoteControlActivity) {
//                spinner.setVisibility(View.VISIBLE);
//            }

            BLConn.getInstance().connect(getApplicationContext(), new BLConn.BLUpdateCallback() {
                @Override
                public void onConnect(boolean success) {
                    runOnUiThread(new Runnable() {
                                      @Override
                                      public void run() {
                                          if(BLConn.getInstance().isConnected()) {
                                              item.setVisible(false);
                                              item.setEnabled(false);
                                              menu.findItem(R.id.disconnect).setEnabled(true);
                                              menu.findItem(R.id.disconnect).setVisible(true);
//                                              if(this instanceof RemoteControlActivity) {
//                                                  spinner.setVisibility(View.GONE);
//                                              }
                                              Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                                          }
                                          else{
//                                              spinner.setVisibility(View.GONE);
                                              Toast.makeText(getApplicationContext(), "Failed to connect", Toast.LENGTH_LONG).show();
                                          }
                                      }
                                  }
                    );
                }
            });
        } else if (id == R.id.disconnect) {
            BLConn.getInstance().disconnect();

            item.setVisible(false);
            item.setEnabled(false);

            menu.findItem(R.id.connect).setEnabled(true);
            menu.findItem(R.id.connect).setVisible(true);
        }
        return super.onOptionsItemSelected(item);
    }

    public void flButtonOnClick(View v) {
        remote.fl(false);
    }

    public void fButtonOnClick(View v) {
        remote.f(false);
    }

    public void frButtonOnClick(View v) {
        remote.fr(false);
    }

    public void lButtonOnClick(View v) {
        remote.l(false);
    }

    public void stopButtonOnClick(View v) {
        remote.stop();
    }

    public void rButtonOnClick(View v) {
        remote.r(false);
    }

    public void blButtonOnClick(View v) {
        remote.bl(false);
    }

    public void bButtonOnClick(View v) {
        remote.b(false);
    }

    public void brButtonOnClick(View v) {
        remote.br(false);
    }

    public void pflButtonOnClick(View v) {
        remote.fl(true);
    }

    public void pfButtonOnClick(View v) {
        remote.f(true);
    }

    public void pfrButtonOnClick(View v) {
        remote.fr(true);
    }

    public void plButtonOnClick(View v) {
        remote.l(true);
    }

    public void pstopButtonOnClick(View v) {
        remote.stop();
    }

    public void prButtonOnClick(View v) {
        remote.r(true);
    }

    public void pblButtonOnClick(View v) {
        remote.bl(true);
    }

    public void pbButtonOnClick(View v) {
        remote.b(true);
    }

    public void pbrButtonOnClick(View v) {
        remote.br(true);
    }

//    @Override
//    public void onBackPressed() {
//        return;
//    }
}
