package com.matigurten.tom.remotecontrol.Proxy;

/**
 * Created by Mati on 25/12/2016.
 */
public interface RemoteProxy {
//
//    Plr (Power left right)
//    000 -> axis x
//    000 -> axis y
//
//    00 Reserved

    public void fl(boolean fast);
    public void f(boolean fast);
    public void fr(boolean fast);
    public void r(boolean fast);
    public void stop();
    public void l(boolean fast);
    public void br(boolean fast);
    public void b(boolean fast);
    public void bl(boolean fast);
}
