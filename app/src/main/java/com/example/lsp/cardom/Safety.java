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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Safety extends AppCompatActivity {

    boolean mBounded;
    BlueServer mServer;
    TextView textView;
    ImageView imageView;
    public  byte buffer[];


    Thread thread;
    boolean flag =false;
    boolean change =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_safety);
        //textView = (TextView) findViewById(R.id.textView_data);
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
            thread.interrupt();
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
                        change=true;
                        ChangeImg();

                    }

                }
            }

        });
        thread.start();
    }



    public  void ChangeImg(){
        if (change){
            if (mServer.MyBlue.data>=150){
                //imageView.setImageDrawable();
                ImageView imageView = (ImageView)findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.ic_p_green);

                ImageView imageView1 = (ImageView)findViewById(R.id.imageView2);
                imageView1.setImageResource(R.drawable.ic_p_orange_empty);

                ImageView imageView2 = (ImageView)findViewById(R.id.imageView3);
                imageView2.setImageResource(R.drawable.ic_p_red_empty);

               // textView.setTextColor(Color.parseColor("#00FF1A"));
            }
            else if(mServer.MyBlue.data>=50 && mServer.MyBlue.data<150){
                ImageView imageView = (ImageView)findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.ic_p_green_empty);

                ImageView imageView1 = (ImageView)findViewById(R.id.imageView2);
                imageView1.setImageResource(R.drawable.ic_p_orange);

                ImageView imageView2 = (ImageView)findViewById(R.id.imageView3);
                imageView2.setImageResource(R.drawable.ic_p_red_empty);

                //textView.setTextColor(Color.parseColor("#FFB300"));
            }
            else if(mServer.MyBlue.data<50){
                ImageView imageView = (ImageView)findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.ic_p_green_empty);

                ImageView imageView1 = (ImageView)findViewById(R.id.imageView2);
                imageView1.setImageResource(R.drawable.ic_p_orange_empty);

                ImageView imageView2 = (ImageView)findViewById(R.id.imageView3);
                imageView2.setImageResource(R.drawable.ic_p_red);

                //textView.setTextColor(Color.parseColor("#FF0000"));
            }
            change=false;

        }
    }
}