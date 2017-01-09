package com.matigurten.tom.remotecontrol.proxy;

import com.matigurten.tom.remotecontrol.bluetooth.BLConn;
import com.matigurten.util.MathUtils;

/**
 * Created by Mati on 28/12/2016.
 */

public class JoystickProxy extends LogProxy {

    BLConn btProxy = BLConn.getInstance();
    private int lastCode;

    public JoystickProxy() {
    }

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
//                Log.d("JOYSTICK", distance + " / " + angle + " / " + code);
                switch (code % 8) {
                    case 0:
                        btProxy.r(fast);
                        r(fast);
                        break;
                    case 1:
                        btProxy.br(fast);
                        br(fast);
                        break;
                    case 2:
                        btProxy.b(fast);
                        b(fast);
                        break;
                    case 3:
                        btProxy.bl(fast);
                        bl(fast);
                        break;
                    case 4:
                        btProxy.l(fast);
                        l(fast);
                        break;
                    case 5:
                        btProxy.fl(fast);
                        fl(fast);
                        break;
                    case 6:
                        btProxy.f(fast);
                        f(fast);
                        break;
                    case 7:
                        btProxy.fr(fast);
                        fr(fast);
                        break;
                }
            }
            lastCode = code;
        }
        return lastCode;
    }

    @Override
    public void stop() {
        super.stop();
        btProxy.stop();
    }
}
