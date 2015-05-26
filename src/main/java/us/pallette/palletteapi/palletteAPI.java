package us.pallette.palletteapi;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;

import palletteFramework.APIBluetoothDeviceManager;

public class palletteAPI extends Activity {

    APIBluetoothDeviceManager manager;

    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                manager.discovered_list.add(new Pair<String, String>(device.getName(), device.getAddress()));
                Log.d(device.getName(), device.getAddress());
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pallette_api);

        manager = new APIBluetoothDeviceManager();


        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy}

        startBluetoothDiscovery();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pallette_api, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startBluetoothDiscovery()
    {
        try{
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                // Device does not support Bluetooth
                throw new Exception("Bluetooth not supported on this device");
            }
            else if (!mBluetoothAdapter.isEnabled()) {
                throw new Exception("Bluetooth is not enabled on this device");
            }
            else
            {
                mBluetoothAdapter.startDiscovery();
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    // Show discovered devices
    public void showDiscovered()
    {
        // For every pair in manager.discovered list

    }

    public void connectTo(String MAC)
    {

    }
}
