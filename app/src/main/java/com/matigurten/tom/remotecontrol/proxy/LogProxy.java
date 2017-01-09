package com.matigurten.tom.remotecontrol.proxy;

import android.util.Log;

/**
 * Created by Mati on 25/12/2016.
 */
public class LogProxy implements RemoteProxy {
    @Override
    public void fl(boolean fast) {
        Log.d("Proxy", "fl" + (fast ? " fast" : ""));
    }

    @Override
    public void f(boolean fast) {
        Log.d("Proxy", "f" + (fast ? " fast" : ""));
    }

    @Override
    public void fr(boolean fast) {
        Log.d("Proxy", "fr" + (fast ? " fast" : ""));
    }

    @Override
    public void r(boolean fast) {
        Log.d("Proxy", "r" + (fast ? " fast" : ""));
    }

    @Override
    public void stop() {
        Log.d("Proxy", "stop");
    }

    @Override
    public void l(boolean fast) {
        Log.d("Proxy", "l" + (fast ? " fast" : ""));
    }

    @Override
    public void br(boolean fast) {
        Log.d("Proxy", "br" + (fast ? " fast" : ""));
    }

    @Override
    public void b(boolean fast) {
        Log.d("Proxy", "b" + (fast ? " fast" : ""));
    }

    @Override
    public void bl(boolean fast) {
        Log.d("Proxy", "bl " + (fast ? " fast" : ""));
    }
}
