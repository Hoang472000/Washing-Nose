package com.android.washingnose;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListDevice extends AppCompatActivity {

    private ListView listView;
    private BluetoothAdapter BTAdapter;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.add_device);
        listView = findViewById(R.id.list);
        initBluetooth();
        pairDevice();
    }

    private void initBluetooth() {
        BTAdapter = BluetoothAdapter.getDefaultAdapter();
        // Phone does not support Bluetooth so let the user know and exit.
        if (BTAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if (!BTAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, 1);
        }
    }

    private void pairDevice() {
        if (BTAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
        } else {
            Set<BluetoothDevice> pairedDevices = BTAdapter.getBondedDevices();
            List<Device> list = new ArrayList<Device>();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice bluetoothDevice : pairedDevices) {
                    Device device = new Device(bluetoothDevice.getAddress(), bluetoothDevice.getName());
                    list.add(device);
                }
                myAdapter = new MyAdapter(list);
                listView.setAdapter(myAdapter);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            switch (resultCode)
            {
                // When the user press OK button.
                case Activity.RESULT_OK:
                {
                    pairDevice();
                }
                break;

                // When the user press cancel button or back button.
                case Activity.RESULT_CANCELED:
                {
                    finish();
                }
                break;
                default:
                    break;
            }
        }
    }
}

