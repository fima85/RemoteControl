package com.matigurten.tom.remotecontrol.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.matigurten.tom.remotecontrol.proxy.RemoteProxy;

import java.io.IOException;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fima on 05/01/17.
 */
public class BLConn implements RemoteProxy{
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

    public void connect(Context context) throws Error{

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        address = prefs.getString(ADDRESS, null);
        appContext = context;
        if (address == null){
            throw new Error("need to set device address");
        }
        try
        {
            if (btConn == null || !isBtConnected)
            {
                myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                btConn = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                btConn.connect();//start connection
                isBtConnected = true;
            }
        }
        catch (IOException e)
        {
            error = e.getMessage();
            isError = false;
            Log.e(TAG,e.getMessage());
            throw new Error("failed to connect");
//           ConnectSuccess = false;//if the try failed, you can check the exception here
        }

    }

    private BLConn() {

    }

    public void fl(boolean fast){
        sendCommand("fl");
    }

    public void f(boolean fast){
        sendCommand("f");
    }

    public void fr(boolean fast){
        sendCommand("fr");
    }

    public void r(boolean fast){
       sendCommand("r");
    }

    public void stop(){
        sendCommand("stop");
    }
    public void l(boolean fast){
        sendCommand("l");
    }

    public void br(boolean fast){
        sendCommand("br");
    }

    public void b(boolean fast){
        sendCommand("b");
    }

    public void bl(boolean fast){
        sendCommand("bl");
    }


    private void sendCommand(String cmd){

        if (btConn!=null)
        {
            try
            {
                cmd += "\n";
                btConn.getOutputStream().write(cmd.getBytes());
            }
            catch (IOException e)
            {
                Log.e(TAG, "failed: " + e.getMessage());
            }
            byte[] buf = new byte[1024];
            try {
                int res = btConn.getInputStream().read(buf);
                Toast.makeText(appContext, new String(buf), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
