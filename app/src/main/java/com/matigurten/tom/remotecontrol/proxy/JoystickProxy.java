package com.matigurten.tom.remotecontrol.proxy;

import com.matigurten.util.MathUtils;

/**
 * Created by Mati on 28/12/2016.
 */

public class JoystickProxy extends LogProxy {

    private int lastCode;

    public int translate(double distance, double angle) {

        //      5  6  7
        //      4 -1  0
        //      3  2  1
        if (distance < 50) {
            if (lastCode != -1) {
                stop();
                lastCode = -1;
            }
        } else {
            boolean fast = distance > 450;
            int code = (int) Math.floor(8 * (MathUtils.mod(angle + Math.PI / 8, Math.PI * 2) / (2 * Math.PI))) + (fast ? 8 : 0);
            if (code != lastCode) {
//                Log.d("Joystick", distance + " / " + angle + " / " + code);
                switch (code % 8) {
                    case 0:
                        r(fast);
                        break;
                    case 1:
                        br(fast);
                        break;
                    case 2:
                        b(fast);
                        break;
                    case 3:
                        bl(fast);
                        break;
                    case 4:
                        l(fast);
                        break;
                    case 5:
                        fl(fast);
                        break;
                    case 6:
                        f(fast);
                        break;
                    case 7:
                        fr(fast);
                        break;
                }
            }
            lastCode = code;
        }
        return lastCode;
    }
}
