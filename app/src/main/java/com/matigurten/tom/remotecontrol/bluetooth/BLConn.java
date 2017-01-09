package com.matigurten.tom.remotecontrol.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.matigurten.tom.remotecontrol.SettingsActivity;
import com.matigurten.tom.remotecontrol.proxy.RemoteProxy;

import java.io.IOException;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fima on 05/01/17.
 */
public class BLConn implements RemoteProxy {
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
    private int waitTime = 50;

    public void connect(final Context context) throws Exception {

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
//                    throw new Exception("missing address");
//           ConnectSuccess = false;//if the try failed, you can check the exception here
                } finally {
                    isBtConnected = true;
                    while (isBtConnected) {
                        sendCommand(lastCommand);
                        try {
                            Thread.sleep(waitTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
        setCommand("AAaE");
    }

    public void f(boolean fast) {
        setCommand(!fast ? "AAbE" : "AAkE");
    }

    public void fl(boolean fast) {
        setCommand(!fast ? "AAcE" : "AAlE");
    }

    public void l(boolean fast) {
        setCommand(!fast ? "AAdE" : "AAmE");
    }

    public void bl(boolean fast) {
        setCommand(!fast ? "AAeE" : "AAnE");
    }

    public void b(boolean fast) {
        setCommand(!fast ? "AAfE" : "AAoE");
    }

    public void br(boolean fast) {
        setCommand(!fast ? "AAgE" : "AApE");
    }

    public void r(boolean fast) {
        setCommand(!fast ? "AAhE" : "AAqE");
    }

    public void fr(boolean fast) {
        setCommand(!fast ? "AAiE" : "AArE");
    }

    long lastSend = 0;

    private void setCommand(String cmd) {
        lastCommand = cmd;
    }

    private void sendCommand(String cmd) {
        if (btConn != null && cmd != null) {
            try {
                long current = System.currentTimeMillis();
                Log.d("Bluetooth", cmd + " @ " + current + " / " + (current - lastSend));
                lastSend = current;
                btConn.getOutputStream().write(cmd.getBytes());
            } catch (IOException e) {
                Log.e(TAG, "failed: " + e.getMessage());
            }
        }
    }
}
