package com.matigurten.tom.remotecontrol.proxy;

import android.util.Log;

/**
 * Created by Mati on 25/12/2016.
 */
public class LogProxy implements RemoteProxy {
    @Override
    public void fl(boolean fast) {
        logOut("Forward Left" + (fast ? " fast" : ""));
    }

    @Override
    public void f(boolean fast) {
        logOut("Forward" + (fast ? " fast" : ""));
    }

    @Override
    public void fr(boolean fast) {
        logOut("Forward Right" + (fast ? " fast" : ""));
    }

    @Override
    public void r(boolean fast) {
        logOut("Right" + (fast ? " fast" : ""));
    }

    @Override
    public void stop() {
        logOut("STOP!");
    }

    @Override
    public void l(boolean fast) {
        logOut("Left" + (fast ? " fast" : ""));
    }

    @Override
    public void br(boolean fast) {
        logOut("Back Right" + (fast ? " fast" : ""));
    }

    @Override
    public void b(boolean fast) {
        logOut("Back" + (fast ? " fast" : ""));
    }

    @Override
    public void bl(boolean fast) {
        logOut("Back Left" + (fast ? " fast" : ""));
    }

    private void logOut(String cmd) {
        Log.d("Proxy", cmd);
    }
}
