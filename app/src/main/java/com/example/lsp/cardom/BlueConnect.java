package com.example.lsp.cardom;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class BlueConnect implements Serializable {


    public  final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");//Serial Port Service ID
   public BluetoothDevice device;
    public  BluetoothSocket socket;
    public OutputStream outputStream;
    public  InputStream inputStream;
    public  TextView textView;

    public  int bufferPosition;
    public  boolean stopThread;
    public   Context context;
    public String[] stringArray_split;
    public String[] stringArray=new String[10];
    int count=0;
    int data=0;


    public int byteCount;
    public BlueConnect(Context conText ){
        context=conText;

    }

    public boolean BlueOn(){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean check =true;
        if (!bluetoothAdapter.isEnabled())

        {


            check =false;

        }
        return check;
    }


    public boolean testconection() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        boolean found = false;

        if (bluetoothAdapter == null) {


            Toast.makeText(context.getApplicationContext(), "Device doesnt Support Bluetooth", Toast.LENGTH_SHORT).show();

        }


        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

        if (bondedDevices.isEmpty()) {

            Toast.makeText(context.getApplicationContext(), "Please Pair the Device first", Toast.LENGTH_SHORT).show();

        } else {

            for (BluetoothDevice iterator : bondedDevices) {

                if (iterator.getName().equals("lowie")) //Replace with iterator.getName() if comparing Device names.

                {

                    device = iterator; //device is an object of type BluetoothDevice

                    found = true;


                    break;

                }

            }
        }
        return found;

    }

    public boolean connect() {
        boolean connected = true;

        try {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
        }

        if (connected) {
            try {
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return connected;
    }

    public boolean StartConnect(){
        boolean checkup = false;
        if(testconection())
        {
            if(connect())
            {
                checkup=true;
            }

        }
        return checkup;

    }

//string.split("\r\n")
    public void ReadData(TextView textView) {



        final String string;



        try {
            int byteCount = inputStream.available();

            if (byteCount > 0) {
                byte[] rawBytes = new byte[byteCount];
                inputStream.read(rawBytes);
                string = new String(rawBytes, "UTF-8");
                stringArray_split=string.split("u");
                if (stringArray_split.length>0) {


                    textView.setText("distance:" + stringArray_split[0]);
                }























            }

        } catch (IOException e) {
            e.printStackTrace();


        }



    }


    public void Send(String in) {
        String string = in;
        string.concat("\n");
        try {
            outputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
