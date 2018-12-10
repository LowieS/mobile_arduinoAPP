package com.example.lsp.cardom;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
BlueConnect myBlue;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView = (TextView) findViewById(R.id.textView);
        myBlue= new BlueConnect(this.getApplicationContext());


    }


    public void onClickStart(View view) {
        if (myBlue.BlueOn()) {


            if (myBlue.StartConnect()) {
                Intent intent1 = new Intent(this, Main2Activity.class);
                Toast.makeText(getApplicationContext(), "connection", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
            }
        }
        else {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivityForResult(enableAdapter, 0);
        }
    }
}
