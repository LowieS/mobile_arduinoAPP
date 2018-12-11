package com.example.lsp.cardom;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    public  byte buffer[];
    public  int bufferPosition;
    public  boolean stopThread;
    public   Context context;


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


    public String ReadData()  {

        final Handler handler = new Handler();
        stopThread = false;
        buffer = new byte[1024];
        final String[] Input = new String[1];
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted() && !stopThread) {

                    try {
                        int byteCount = inputStream.available();

                        if (byteCount > 0) {

                            byte[] rawBytes = new byte[byteCount];
                            inputStream.read(rawBytes);
                            final String string = new String(rawBytes, "UTF-8");
                           Input[0] =string;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        stopThread=true;
                    }
                }
            }
        });
        thread.start();
        return Input[0];
    }

    public void SendT() {
        String string = "t";
        string.concat("\n");
        try {
            outputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.append("\nSent Data:"+string+"\n");

    }
    public void SendF() {
        String string = "f";
        string.concat("\n");
        try {
            outputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.append("\nSent Data:"+string+"\n");

    }
}
