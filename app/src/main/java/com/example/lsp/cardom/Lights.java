package com.example.lsp.cardom;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Lights extends AppCompatActivity {
    boolean mBounded;
    boolean GROOTLICHT = false;
    boolean DIMLICHT = false;
    BlueServer mServer;
    Button GROOTLICHTbut;
    Button DIMLICHTbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_lights);
    }
    @Override
    protected void onStart() {
        super.onStart();

        Intent mIntent = new Intent(this, BlueServer.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
         GROOTLICHTbut = findViewById(R.id.button8);
         DIMLICHTbut = findViewById(R.id.button9);
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
            GROOTLICHT=mServer.lamp1;
            DIMLICHT=mServer.lamp2;
            Check_Groot();
            Check_Dim();


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

    public void SEND1(View view) {
        GROOTLICHT = !GROOTLICHT;
        Check_Groot();


        mServer.MyBlue.Send("1");
        mServer.lamp1=GROOTLICHT;


    }

    public void SEND2(View view) {
        DIMLICHT = !DIMLICHT;

       Check_Dim();

        mServer.MyBlue.Send("2");
        mServer.lamp2=DIMLICHT;
    }

public void Check_Groot(){
    if(GROOTLICHT == true){
        GROOTLICHTbut.setBackgroundResource(R.drawable.lights_enabled);
        GROOTLICHTbut.setTextColor(Color.parseColor("#0000FF"));
    }
    else if(GROOTLICHT == false){
        GROOTLICHTbut.setBackgroundResource(R.drawable.lights_disabled);
        GROOTLICHTbut.setTextColor(Color.parseColor("#CBCBCB"));
    }


}

public void Check_Dim(){
    if(DIMLICHT == true){
        DIMLICHTbut.setBackgroundResource(R.drawable.lights_enabled);
        DIMLICHTbut.setTextColor(Color.parseColor("#0000FF"));
    }
    else if(DIMLICHT == false){
        DIMLICHTbut.setBackgroundResource(R.drawable.lights_disabled);
        DIMLICHTbut.setTextColor(Color.parseColor("#CBCBCB"));
    }

}


}
