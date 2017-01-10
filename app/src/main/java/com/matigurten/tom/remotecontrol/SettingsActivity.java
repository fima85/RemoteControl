package com.matigurten.tom.remotecontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.matigurten.tom.remotecontrol.bluetooth.BLConn;

import com.matigurten.tom.remotecontrol.common.SharedPref;
import com.matigurten.tom.remotecontrol.proxy.RemoteProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class SettingsActivity extends AppCompatActivity {

    ListView devicelist;
    //Bluetooth
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    HashMap<String, String> deviceHash;
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final ProgressBar spinner = (ProgressBar)findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);
        // Getting object reference to listview of main.xml
        final ListView listView = (ListView) findViewById(R.id.remoteType);

        // Instantiating array adapter to populate the listView
        // The layout android.R.layout.simple_list_item_single_choice creates radio button for each listview item
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, SharedPref.remoteTypes);

        int pos = SharedPref.getRemotePos(this);
        listView.setAdapter(adapter);
        listView.setItemChecked(pos, true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, true);
                SharedPref.setRemotePos(getApplicationContext(), position);
            }

        });

        devicelist = (ListView) findViewById(R.id.listView);
        deviceHash = new HashMap<String, String>();
        //if the device has bluetooth
        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if (myBluetooth == null) {
            //Show a mensag. that the device has no bluetooth adapter
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();

            //finish apk
            finish();
        } else if (!myBluetooth.isEnabled()) {
            //Ask to the user turn the bluetooth on
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon, 1);
        }
        pairedDevicesList();
    }

    private void pairedDevicesList() {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() /*+ "\n" + bt.getAddress()*/); //Get the device's name and the address
                deviceHash.put(bt.getName(), bt.getAddress());
                Log.d(TAG, bt.getName() + "  " + bt.getAddress());
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = deviceHash.get(info);

            Log.d(TAG, "added " + info + " with address " + address);
            SharedPreferences.Editor editor = getSharedPreferences(BLConn.getInstance().PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString(BLConn.getInstance().ADDRESS, address);
            editor.commit();

//            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
            BLConn blConn = BLConn.getInstance();

            final ProgressBar spinner = (ProgressBar)findViewById(R.id.spinner);
            spinner.setVisibility(View.VISIBLE);

                blConn.connect(getApplicationContext(), new BLConn.BLUpdateCallback() {
                    @Override
                    public void onConnect(boolean success) {
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {

                                              if(BLConn.getInstance().isConnected()) {

                                                  Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                                                  spinner.setVisibility(View.GONE);
                                              }
                                              else{
                                                  Toast.makeText(getApplicationContext(), "Failed to connect", Toast.LENGTH_LONG).show();
                                                  spinner.setVisibility(View.GONE);
                                              }
                                          }
                                      }
                        );
                    }
                });
        }
    };

    @Override
    public void onBackPressed() {
        //do whatever you want the 'Back' button to do
        //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
        RemoteTypeEnum remoteType = SharedPref.getRemoteType(this);
        Log.d(TAG, "starting activity " + remoteType.name());
        Intent i = new Intent(getApplicationContext(), remoteType.getActivityClass());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        this.startActivity(i);

        return;
    }
}

