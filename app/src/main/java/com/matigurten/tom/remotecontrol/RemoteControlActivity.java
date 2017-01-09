package com.matigurten.tom.remotecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.matigurten.tom.remotecontrol.bluetooth.BLConn;
import com.matigurten.tom.remotecontrol.proxy.RemoteProxy;

public class RemoteControlActivity extends AppCompatActivity {

    RemoteProxy remote = BLConn.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_remote_control);
        try {
            BLConn.getInstance().connect(getApplicationContext());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }

        // Regular Buttons
        final View fButton = findViewById(R.id.fButton);
        fButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    fButtonOnClick(fButton);
                else
                    stopButtonOnClick(fButton);
                return true;
            }
        });
        final View flButton = findViewById(R.id.flButton);
        flButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    flButtonOnClick(fButton);
                else
                    stopButtonOnClick(fButton);
                return true;
            }
        });

        final View lButton = findViewById(R.id.lButton);
        lButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    lButtonOnClick(lButton);
                else
                    stopButtonOnClick(lButton);
                return true;
            }
        });
        final View blButton = findViewById(R.id.blButton);
        blButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    blButtonOnClick(blButton);
                else
                    stopButtonOnClick(blButton);
                return true;
            }
        });

        final View bButton = findViewById(R.id.bButton);
        bButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    bButtonOnClick(bButton);
                else
                    stopButtonOnClick(bButton);
                return true;
            }
        });
        final View brButton = findViewById(R.id.brButton);
        brButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    brButtonOnClick(brButton);
                else
                    stopButtonOnClick(brButton);
                return true;
            }
        });

        final View rButton = findViewById(R.id.rButton);
        rButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    rButtonOnClick(rButton);
                else
                    stopButtonOnClick(rButton);
                return true;
            }
        });
        final View frButton = findViewById(R.id.frButton);
        frButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    frButtonOnClick(frButton);
                else
                    stopButtonOnClick(frButton);
                return true;
            }
        });

        // Power Buttons
        final View pfButton = findViewById(R.id.pfButton);
        pfButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    pfButtonOnClick(pfButton);
                else
                    stopButtonOnClick(pfButton);
                return true;
            }
        });
        final View pflButton = findViewById(R.id.pflButton);
        pflButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    pflButtonOnClick(pflButton);
                else
                    stopButtonOnClick(pflButton);
                return true;
            }
        });
        final View plButton = findViewById(R.id.plButton);
        plButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    plButtonOnClick(plButton);
                else
                    stopButtonOnClick(plButton);
                return true;
            }
        });
        final View pblButton = findViewById(R.id.pblButton);
        pblButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    pblButtonOnClick(pblButton);
                else
                    stopButtonOnClick(pblButton);
                return true;
            }
        });
        final View pbButton = findViewById(R.id.pbButton);
        pbButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    pbButtonOnClick(pbButton);
                else
                    stopButtonOnClick(pbButton);
                return true;
            }
        });
        final View pbrButton = findViewById(R.id.pbrButton);
        pbrButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    pbrButtonOnClick(pbrButton);
                else
                    stopButtonOnClick(pbrButton);
                return true;
            }
        });

        final View prButton = findViewById(R.id.prButton);
        prButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    prButtonOnClick(prButton);
                else
                    stopButtonOnClick(prButton);
                return true;
            }
        });
        final View pfrButton = findViewById(R.id.pfrButton);
        pfrButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() != MotionEvent.ACTION_UP)
                    pfrButtonOnClick(pfrButton);
                else
                    stopButtonOnClick(pfrButton);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.activity_settings) {
            startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
            return true;
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
}
