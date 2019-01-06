package com.example.lsp.cardom;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Safety extends AppCompatActivity {

    boolean mBounded;
    BlueServer mServer;
    TextView textView;
    ImageView imageView;
    public  byte buffer[];
    boolean readUltra = false;
    Thread thread;
    boolean flag =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        textView = (TextView) findViewById(R.id.textview);
        imageView = (ImageView) findViewById(R.id.imageView);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent mIntent = new Intent(this, BlueServer.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    }

    ;

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
            BlueServer.LocalBinder mLocalBinder = (BlueServer.LocalBinder) service;
            mServer = mLocalBinder.getServerInstance();

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBounded) {
            unbindService(mConnection);
            mBounded = false;
        }
    }

    ;

    public void GetData(View view) {
        final Handler handler = new Handler();
        flag=true;

        buffer = new byte[1024];


         thread = new Thread(new Runnable() {
            public void run() {
                while (!(Thread.currentThread().isInterrupted())) {
                    if (flag) {




                        mServer.MyBlue.Send("u");


                        mServer.MyBlue.ReadData(textView);
                        if (mServer.MyBlue.data<300){
                            //imageView.setImageDrawable();
                        }
                    }

                }
            }

        });
        thread.start();
    }

    public void PrevMenu(View view) {
        flag=false;
        Intent intent1 = new Intent(this, MenuScreen.class);


        mServer.MyBlue.Send("m");


        startActivity(intent1);


    }
}