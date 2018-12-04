package com.example.lsp.cardom;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device ;
        boolean found;

        if(bluetoothAdapter==null){


            Toast.makeText(getApplicationContext(),"Device doesnt Support Bluetooth",Toast.LENGTH_SHORT).show();

        }

        if(!bluetoothAdapter.isEnabled())

        {

            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivityForResult(enableAdapter, 0);

        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        if(bondedDevices.isEmpty()) {

            Toast.makeText(getApplicationContext(),"Please Pair the Device first",Toast.LENGTH_SHORT).show();

        } else {

            for (BluetoothDevice iterator : bondedDevices) {

                if(iterator.getName().equals("michie")) //Replace with iterator.getName() if comparing Device names.

                {

                    device=iterator; //device is an object of type BluetoothDevice

                    found=true;

                    Toast.makeText(getApplicationContext(),"connection",Toast.LENGTH_SHORT).show();

                    break;

                }
                else {
                    Toast.makeText(getApplicationContext(),"no connection",Toast.LENGTH_SHORT).show();
                }
            } }
    }




}
