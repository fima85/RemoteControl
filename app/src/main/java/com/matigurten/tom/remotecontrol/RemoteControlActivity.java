package com.matigurten.tom.remotecontrol;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class RemoteControlActivity extends ControllerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_remote_control);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        // Regular Buttons
        final View fButton = findViewById(R.id.fButton);
        fButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    fButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    fButtonOnClick(fButton);
                } else
                    stopButtonOnClick(fButton);
                return true;
            }
        });
        final View flButton = findViewById(R.id.flButton);
        flButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    flButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    flButtonOnClick(flButton);
                } else
                    stopButtonOnClick(flButton);
                return true;
            }
        });

        final View lButton = findViewById(R.id.lButton);
        lButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    lButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    lButtonOnClick(lButton);
                } else
                    stopButtonOnClick(lButton);
                return true;
            }
        });
        final View blButton = findViewById(R.id.blButton);
        blButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    blButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    blButtonOnClick(blButton);
                } else
                    stopButtonOnClick(blButton);
                return true;
            }
        });

        final View bButton = findViewById(R.id.bButton);
        bButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    bButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    bButtonOnClick(bButton);
                } else
                    stopButtonOnClick(bButton);
                return true;
            }
        });
        final View brButton = findViewById(R.id.brButton);
        brButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    brButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    brButtonOnClick(brButton);
                } else
                    stopButtonOnClick(brButton);
                return true;
            }
        });

        final View rButton = findViewById(R.id.rButton);
        rButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    rButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    rButtonOnClick(rButton);
                } else
                    stopButtonOnClick(rButton);
                return true;
            }
        });
        final View frButton = findViewById(R.id.frButton);
        frButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    frButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    frButtonOnClick(frButton);
                } else
                    stopButtonOnClick(frButton);
                return true;
            }
        });

        // Power Buttons
        final View pfButton = findViewById(R.id.pfButton);
        pfButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    pfButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    pfButtonOnClick(pfButton);
                } else
                    stopButtonOnClick(pfButton);
                return true;
            }
        });
        final View pflButton = findViewById(R.id.pflButton);
        pflButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    pflButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    pflButtonOnClick(pflButton);
                } else
                    stopButtonOnClick(pflButton);
                return true;
            }
        });
        final View plButton = findViewById(R.id.plButton);
        plButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    plButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    plButtonOnClick(plButton);
                } else
                    stopButtonOnClick(plButton);
                return true;
            }
        });
        final View pblButton = findViewById(R.id.pblButton);
        pblButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    pblButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    pblButtonOnClick(pblButton);
                } else
                    stopButtonOnClick(pblButton);
                return true;
            }
        });
        final View pbButton = findViewById(R.id.pbButton);
        pbButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    pbButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    pbButtonOnClick(pbButton);
                } else
                    stopButtonOnClick(pbButton);
                return true;
            }
        });
        final View pbrButton = findViewById(R.id.pbrButton);
        pbrButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    pbrButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    pbrButtonOnClick(pbrButton);
                } else
                    stopButtonOnClick(pbrButton);
                return true;
            }
        });
        final View prButton = findViewById(R.id.prButton);
        prButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    prButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    prButtonOnClick(prButton);
                } else
                    stopButtonOnClick(prButton);
                return true;
            }
        });
        final View pfrButton = findViewById(R.id.pfrButton);
        pfrButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (isPressing(arg1)) {
                    pfrButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    pfrButtonOnClick(pfrButton);
                } else {
                    stopButtonOnClick(pfrButton);
                }
                return true;
            }
        });
    }

    private boolean isPressing(MotionEvent arg1) {
        return arg1.getAction() == MotionEvent.ACTION_DOWN || arg1.getAction() == MotionEvent.ACTION_MOVE;
    }

    public void stopButtonOnClick(View v) {
        super.stopButtonOnClick(v);
        v.getBackground().clearColorFilter();
    }
}
