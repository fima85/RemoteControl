package com.matigurten.tom.remotecontrol.proxy;

import android.content.Intent;
import android.util.Log;

import com.matigurten.tom.remotecontrol.RemoteTypeEnum;
import com.matigurten.tom.remotecontrol.bluetooth.BLConn;
import com.matigurten.tom.remotecontrol.common.SharedPref;
import com.matigurten.util.MathUtils;

/**
 * Created by Mati on 28/12/2016.
 */

public class JoystickProxy extends LogProxy {

    BLConn btProxy = BLConn.getInstance();

    public JoystickProxy() {
    }

    public static int INNER_R = 50, OUTER_R = 450;

    public int translate(double distance, double angle) {
        if (distance < INNER_R) {
            stop();
            return -1;
        } else {
            boolean fast = distance > OUTER_R;
            int code = (int) Math.floor(8 * (MathUtils.mod(angle + Math.PI / 8, Math.PI * 2) / (2 * Math.PI))) + (fast ? 8 : 0);
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
            return code;
        }
    }

    @Override
    public void stop() {
        btProxy.stop();
        super.stop();
    }


}
