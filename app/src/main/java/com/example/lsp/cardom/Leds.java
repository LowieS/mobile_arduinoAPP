package com.example.lsp.cardom;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Leds extends AppCompatActivity {
    boolean mBounded;
    BlueServer mServer;
    TextView text_R;
    TextView text_G;
    TextView text_B;


TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leds);

        SeekBar seekBar_R = findViewById(R.id.seekBar_R);
        seekBar_R.setOnSeekBarChangeListener(seekBarChangeListener_R);

        int progress_R = seekBar_R.getProgress();

        SeekBar seekBar_G = findViewById(R.id.seekBar_G);
        seekBar_G.setOnSeekBarChangeListener(seekBarChangeListener_G);

        int progress_G = seekBar_G.getProgress();

        SeekBar seekBar_B = findViewById(R.id.seekBar_B);
        seekBar_B.setOnSeekBarChangeListener(seekBarChangeListener_B);

        int progress_B = seekBar_B.getProgress();

        text_R=findViewById(R.id.textView_R);
        text_R.setText("value:"+progress_R);

        text_G=findViewById(R.id.textView_G);
        text_G.setText("value:"+progress_G);

        text_B=findViewById(R.id.textView_B);
        text_B.setText("value:"+progress_B);




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
            mServer.MyBlue.Send("0");

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

    SeekBar.OnSeekBarChangeListener seekBarChangeListener_R = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            text_R.setText("value:"+progress);


        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (seekBar.getProgress()<10){
                mServer.MyBlue.Send("0"+"0"+"0"+seekBar.getProgress());
            }
            else if (seekBar.getProgress()<100){
                mServer.MyBlue.Send("0"+"0"+seekBar.getProgress());
            }
            else {
                mServer.MyBlue.Send("0" + seekBar.getProgress());
            }

            // called after the user finishes moving the SeekBar
        }


    };
    SeekBar.OnSeekBarChangeListener seekBarChangeListener_G = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            text_G.setText("value:"+progress);

        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (seekBar.getProgress()<10){
                mServer.MyBlue.Send("1"+"0"+"0"+seekBar.getProgress());
            }
            else if (seekBar.getProgress()<100){
                mServer.MyBlue.Send("1"+"0"+seekBar.getProgress());
            }
            else {
                mServer.MyBlue.Send("1" + seekBar.getProgress());
            }
        }


    };
    SeekBar.OnSeekBarChangeListener seekBarChangeListener_B = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            text_B.setText("value:"+progress);

        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
            if (seekBar.getProgress()<10){
                mServer.MyBlue.Send("2"+"0"+ "0"+seekBar.getProgress());
            }
            else if (seekBar.getProgress()<100){
                mServer.MyBlue.Send("2"+"0"+seekBar.getProgress());
            }
            else {
                mServer.MyBlue.Send("2" + seekBar.getProgress());
            }
        }


    };





}
