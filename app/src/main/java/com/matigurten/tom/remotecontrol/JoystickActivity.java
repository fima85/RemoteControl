package com.matigurten.tom.remotecontrol;

/**
 * Created by Mati on 26/12/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.matigurten.tom.remotecontrol.Proxy.JoystickProxy;
import com.matigurten.util.BitmapUtils;

import java.util.LinkedList;
import java.util.List;


public class JoystickActivity extends Activity implements View.OnTouchListener {

    private Joystick joystick;
    private Bitmap arrowBmp, stopBmp;

    private float touchX, touchY;

    private int sleep = 6;
    private static int sleepTimeMillis = 50;
    private int fieldRadius;
    private int minRadius;

    private Double orientation;
    private double r0, a0;

    TextView azimuthTV, distanceTV;
    //    private double adhd = 0;
    private float retardFactor = 0.75f;
//    private boolean isGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        joystick = new Joystick(JoystickActivity.this);
        joystick.setOnTouchListener(this);

//        azimuthTV = (TextView) findViewById(R.id.azimuthTextView);
//        distanceTV = (TextView) findViewById(R.id.distanceTextView);
//        int testNumber = 0;
//        azimuthTV.setText(testNumber + "");
//        distanceTV.setText(testNumber + "");

        Bitmap bigBmp = BitmapFactory.decodeResource(getResources(), R.drawable.arrowright);
        arrowBmp = BitmapUtils.shrinkBitmap(bigBmp, 0.5f);
        r0 = Math.sqrt(arrowBmp.getHeight() * arrowBmp.getHeight() + arrowBmp.getWidth() * arrowBmp.getWidth()) / 2;
        a0 = Math.atan2(arrowBmp.getHeight(), arrowBmp.getWidth());

        bigBmp = BitmapFactory.decodeResource(getResources(), R.drawable.stop);
        stopBmp = BitmapUtils.shrinkBitmap(bigBmp, 0.5f);

        minRadius = (int) r0;

        fieldRadius = 450;

        setContentView(joystick);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
//        try {
//            Thread.sleep(sleep);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        touchX = event.getX();
        touchY = event.getY();

        switch (event.getAction()) { // Careful here...
            case MotionEvent.ACTION_UP:
                joystick.reset();
                joystick.redraw();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                if (touchX != -1 && touchY != -1) {
                    joystick.tryToMove(touchX, touchY);
//                    azimuthTV.setText(azimuth + "");
//                    distanceTV.setText(distance + "");
                }
        }

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        joystick.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        joystick.resume();
    }

    public class Joystick extends SurfaceView implements Runnable {
        Thread thread = null;
        SurfaceHolder holder;
        boolean running = true;
        private Paint blue, red, yellow, green;
        List<float[]> path;
        public float centerX, centerY, startPointX, startPointY;
        private double angularField, angularField0 = 45;
        RectF oval = new RectF();
        JoystickProxy proxy = new JoystickProxy();

        public Joystick(Context context) {
            super(context);
            holder = getHolder();
            blue = new Paint();
            blue.setColor(Color.BLUE);
            blue.setAlpha(80);
            red = new Paint();
            red.setColor(Color.RED);
            red.setAlpha(100);
            yellow = new Paint();
            yellow.setColor(Color.YELLOW);
            yellow.setAlpha(150);
            green = new Paint();
            green.setColor(Color.GREEN);
            green.setAlpha(150);

            centerX = centerY = -1;
            path = new LinkedList<>();
            angularField = angularField0;
        }

        public void pause() {
            running = false;
            while (true) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            thread = null;
        }

        public void resume() {
            running = true;
            thread = new Thread(this);
            try {
                Thread.sleep(1000); // Allow for proper initialization ?
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reset();
            thread.start();
        }

        @Override
        public void run() {
            while (running) {
                redraw();
            }
        }

        private void redraw() {
            // perform canvas drawing
            if (!holder.getSurface().isValid()) {
                return;
            }
            Canvas c = holder.lockCanvas();
//                if (!isInBounds(c)) {
//                    isGameOver = true;
//                }
//                if (!isGameOver) {
//                    centerX += adhd * (Math.random() * 2 - 1); // (MathUtils.random(-1 , 1)
//                    centerY += adhd * (Math.random() * 2 - 1);
//                    path.add(new float[]{centerX, centerY});
            // Add random noise to orientation
//                    orientation += Math.toRadians(adhd * (Math.random() * 2 - 1));
            Bitmap bitmap = getBitMap();
            Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            Rect dst = new Rect(
                    (int) (centerX - bitmap.getWidth() / 2),
                    (int) (centerY - bitmap.getHeight() / 2),
                    (int) (centerX + bitmap.getWidth() / 2),
                    (int) (centerY + bitmap.getHeight() / 2));
            // Paint background
            c.drawARGB(255, 150, 150, 150);
            // fast oval
            c.drawArc(oval, 0, 360, true, getDistance() > fieldRadius ? yellow : blue);
            if (orientation != null) {
                c.drawArc(oval, (float) (Math.toDegrees(orientation) - angularField / 2), (float) angularField, true, getDistance() > fieldRadius ? red : green);

                float quadrantAngleStart = (float) (Math.floor((Math.toDegrees(orientation) - 22.5f) / 45) * 45) + 22.5f;
                c.drawArc(oval, quadrantAngleStart, 45, true, getDistance() > fieldRadius ? red : green);
            }

            c.drawBitmap(bitmap, src, dst, null);

//                c.drawArc(oval, (float) (Math.toDegrees(orientation) - angularField / 2), (float) angularField, true, blue);
//                c.drawArc(oval, (float) (Math.toDegrees(orientation) + angularField / 2), (float) (360 - angularField), true, red);

            holder.unlockCanvasAndPost(c);
        }

        private boolean isInBounds(Canvas c) {
            return 0 <= centerX && centerX <= c.getWidth() && 0 <= centerY && centerY <= c.getHeight();
        }

        private boolean drawPath(Canvas c) {
            float[] firstLocation, secondLocation = null;
            if (path.size() > 1) {
                firstLocation = path.get(0);
                for (float[] location : path) {
                    secondLocation = location;
                    c.drawLine(firstLocation[0], firstLocation[1], secondLocation[0], secondLocation[1], blue);
                    firstLocation = secondLocation;
                }
            }
            return true;
        }

        public void tryToMove(float touchX, float touchY) {
            float relativeX = touchX - startPointX;
            float relativeY = touchY - startPointY;
            float deltaX = touchX - centerX;
            float deltaY = touchY - centerY;
            double distance = Math.hypot(relativeX, relativeY);
            double azimuth = Math.atan2(relativeY, relativeX);
            double deltaR = Math.hypot(deltaX, deltaY);
            double deltaA = Math.atan2(deltaY, deltaX);
            double farAway = getDistance() / startPointX; // value between 0 and a bit above 1. overshooting rail
            if (deltaR < minRadius) { //&& (orientation == null || MathUtils.angleDifferenceRad(azimuth, orientation) <= Math.toRadians(angularField))
                try {
                    Thread.sleep(sleepTimeMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                orientation = azimuth; // * retardFactor;
                centerX += deltaX * retardFactor;
                centerY += deltaY * retardFactor;
                proxy.translate(distance, azimuth);

                angularField = (1 - farAway) * 180 + farAway * angularField0;
                double focus = Math.pow((Math.abs(orientation % (Math.PI / 4)) / (Math.PI / 4)) * 2 - 1, 2);
                angularField = angularField0 * focus;
            } else {
                reset();
            }
        }

        private double getDistance() {
            return Math.hypot(centerX - startPointX, centerY - startPointY);
        }

        private void reset() {
            Canvas c = holder.lockCanvas();
            reset(c);
proxy.stop();
//            Bitmap stopBmp = JoystickActivity.this.stopBmp;
//            Rect src = new Rect(0, 0, stopBmp.getWidth(), stopBmp.getHeight());
//            Rect dst = new Rect(
//                    (int) (centerX - stopBmp.getWidth() / 2),
//                    (int) (centerY - stopBmp.getHeight() / 2),
//                    (int) (centerX + stopBmp.getWidth() / 2),
//                    (int) (centerY + stopBmp.getHeight() / 2));
            if (c != null) //{
//                c.drawARGB(255, 150, 150, 150);
////            drawPath(c);
//
//                c.drawBitmap(stopBmp, src, dst, null);
//            }
                holder.unlockCanvasAndPost(c);
        }

        private void reset(Canvas c) {
            if (c != null && (startPointX == 0 || startPointY == 0)) {
                startPointX = c.getWidth() / 2;
                startPointY = c.getHeight() / 2;
                oval.set(startPointX - fieldRadius, startPointY - fieldRadius, startPointX + fieldRadius, startPointY + fieldRadius);
            }
            centerX = startPointX;
            centerY = startPointY;
            orientation = null;
            angularField = 360;
            path = new LinkedList<>();
        }

        public Bitmap getBitMap() {
            return getDistance() > 0 ? BitmapUtils.rotateBitmap(arrowBmp, (float) Math.toDegrees(orientation)) : stopBmp;
        }
    }
}
