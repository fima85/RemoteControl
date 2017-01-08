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

    Thread btThread;
    private String lastCommand = null;

    public void connect(final Context context) throws Exception {

        btThread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                address = prefs.getString(ADDRESS, null);
                appContext = context;
                if (address == null) {
                    throw new Error("need to set device address");
                }
                try {
                    if (btConn == null || !isBtConnected) {
                        myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                        BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                        btConn = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                        btConn.connect();//start connection
                        isBtConnected = true;

//                        while (lastCommand != null) {
//                            sendCommand(lastCommand);
//                            lastCommand = null;
//                        }
                    }
                } catch (IOException e) {
                    error = e.getMessage();
                    isError = false;
                    Log.e(TAG, e.getMessage());
//           ConnectSuccess = false;//if the try failed, you can check the exception here
                }
            }
        });
        btThread.start();
    }

    private BLConn() {

    }

    public void stop() {
        sendCommand("AAaE");
    }

    public void f(boolean fast) {
        sendCommand(!fast ? "AAbE" : "AAkE");
    }

    public void fl(boolean fast) {
        sendCommand(!fast ? "AAcE" : "AAlE");
    }

    public void l(boolean fast) {
        sendCommand(!fast ? "AAdE" : "AAmE");
    }

    public void bl(boolean fast) {
        sendCommand(!fast ? "AAeE" : "AAnE");
    }

    public void b(boolean fast) {
        sendCommand(!fast ? "AAfE" : "AAoE");
    }

    public void br(boolean fast) {
        sendCommand(!fast ? "AAgE" : "AApE");
    }

    public void r(boolean fast) {
        sendCommand(!fast ? "AAhE" : "AAqE");
    }

    public void fr(boolean fast) {
        sendCommand(!fast ? "AAiE" : "AArE");
    }


    private void sendCommand(String cmd) {

        if (btConn != null) {
            try {
//                cmd += "\n";
                btConn.getOutputStream().write(cmd.getBytes());
            } catch (IOException e) {
                Log.e(TAG, "failed: " + e.getMessage());
            }
//            byte[] buf = new byte[1024];
//            try {
//                int res = btConn.getInputStream().read(buf);
//                Toast.makeText(appContext, new String(buf), Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
        }
    }
}
