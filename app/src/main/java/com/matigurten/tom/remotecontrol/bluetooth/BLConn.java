package com.matigurten.tom.remotecontrol.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.matigurten.tom.remotecontrol.proxy.LogProxy;
import com.matigurten.tom.remotecontrol.proxy.RemoteProxy;

import java.io.IOException;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fima on 05/01/17.
 */
public class BLConn extends LogProxy {
    public interface BLUpdateCallback {
        public void onConnect(boolean success);
    }
    private static BLConn ourInstance = new BLConn();

    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btConn = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String address = null;
    private static final String TAG = "BLConn";
    public static final String PREFS_NAME = "SETTINGS";
    public static final String ADDRESS = "deviceAddress";
    private String error = null;
    private boolean isError = false;
    private Context appContext;

    public static BLConn getInstance() {
        return ourInstance;
    }

    private Thread btThread;

    private String lastCommand = null;
    private int waitTime = 100;

    public void connect(final Context context, final BLUpdateCallback callback) {

        btThread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                address = prefs.getString(ADDRESS, null);
                appContext = context;
                if (address == null) {
                    Log.d(TAG, "no address to connect to");
//                    throw new Exception("missing address");
                    return;
                }
                try {
                    if (btConn == null || !isBtConnected) {
                        myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                        btConn = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                        btConn.connect();//start connection
                        isBtConnected = true;
                    }
                } catch (IOException e) {
                    error = e.getMessage();
                    isError = false;
                    Log.e(TAG, e.getMessage());

                    callback.onConnect(false);
                    return;

//                    throw new Exception("missing address");
//           ConnectSuccess = false;//if the try failed, you can check the exception here
                }
//                    isBtConnected = true;
                callback.onConnect(true);
                    while (isBtConnected) {
                        sendCommand(lastCommand);
                        try {
                            Thread.sleep(waitTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

            }
        });
        btThread.start();
    }

    public void disconnect(){

        if(btConn != null && isBtConnected){
            try {
                btConn.close();
                isBtConnected = false;
            }
            catch (IOException e){
                Log.d(TAG, "disconnect: " + e);
            }
        }
    }

    public boolean isConnected(){
        return isBtConnected;
    }
    private BLConn() {

    }

    public void stop() {
        super.stop();
        setCommand("AAaE");
    }

    public void f(boolean fast) {
        super.f(fast);
        setCommand(!fast ? "AAbE" : "AAkE");
    }

    public void fl(boolean fast) {
        super.fl(fast);
setCommand(!fast ? "AAcE" : "AAlE");
    }

    public void l(boolean fast) {
        super.l(fast);
        setCommand(!fast ? "AAdE" : "AAmE");
    }

    public void bl(boolean fast) {
        super.bl(fast);
        setCommand(!fast ? "AAeE" : "AAnE");
    }

    public void b(boolean fast) {
        super.b(fast);
        setCommand(!fast ? "AAfE" : "AAoE");
    }

    public void br(boolean fast) {
        super.br(fast);
        setCommand(!fast ? "AAgE" : "AApE");
    }

    public void r(boolean fast) {
        super.r(fast);
        setCommand(!fast ? "AAhE" : "AAqE");
    }

    public void fr(boolean fast) {
        super.fr(fast);
        setCommand(!fast ? "AAiE" : "AArE");
    }

    private void setCommand(String cmd) {
        lastCommand = cmd;
    }

    long lastSentTime = 0;
    String lastSentCommand = null;

    private void sendCommand(String cmd) {
        if (btConn != null && cmd != lastSentCommand) {
            try {
                long current = System.currentTimeMillis();
                Log.d("Bluetooth", cmd + " @ " + current + " / " + (current - lastSentTime));
                lastSentCommand = cmd;
                lastSentTime = current;
                btConn.getOutputStream().write(cmd.getBytes());
            } catch (IOException e) {
                Log.e(TAG, "failed: " + e.getMessage());
            }
        }
    }
}
