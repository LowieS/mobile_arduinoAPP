package com.example.lsp.cardom;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class MenuScreen extends AppCompatActivity {
    boolean mBounded;
    BlueServer mServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_menuscreen);


    }
    @Override
    protected void onStart() {
        super.onStart();

        Intent mIntent = new Intent(this, BlueServer.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    };

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(getApplicationContext(), "disconected", Toast.LENGTH_LONG).show();
            mBounded = false;
            mServer = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(getApplicationContext(), "connection with service", Toast.LENGTH_LONG).show();
            mBounded = true;
            BlueServer.LocalBinder mLocalBinder = (BlueServer.LocalBinder)service;
            mServer = mLocalBinder.getServerInstance();
            mServer.MyBlue.Send("m");

        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBounded) {
            unbindService(mConnection);
            mBounded = false;
        }
    };

    public void StartLED(View view) {
        mServer.MyBlue.Send("0");
        Intent intent1 = new Intent(this,Leds.class);
        startActivity(intent1);
    }

    public void StartLights(View view) {
        mServer.MyBlue.Send("1");
        Intent intent1 = new Intent(this,Lights.class);
        startActivity(intent1);
    }

    public void StartSafety(View view) {
        mServer.MyBlue.Send("4");
        Intent intent1 = new Intent(this,Safety.class);
        startActivity(intent1);
    }
}
