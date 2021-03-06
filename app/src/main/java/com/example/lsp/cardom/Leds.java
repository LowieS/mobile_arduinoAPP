package com.example.lsp.cardom;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Leds extends AppCompatActivity {
    boolean mBounded;
    BlueServer mServer;
    TextView text_R;
    TextView text_G;
    TextView text_B;
    SeekBar seekBar_R;
    SeekBar seekBar_G;
    SeekBar seekBar_B;
    boolean basicmenu;
    Button aquaBut;
    Button blueBut;
    Button greenBut;
    Button orangeBut;
    Button pinkBut;
    Button redBut;
    Button violetBut;
    Button yellowBut;
    TextView textViewLetR;
    TextView textViewLetG;
    TextView textViewLetB;
    Button button11;
    Button button12;
    TextView textViewColors;


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_leds);

        aquaBut = findViewById(R.id.aquaBut);
        blueBut = findViewById(R.id.blueBut);
        orangeBut = findViewById(R.id.orangeBut);
        greenBut = findViewById(R.id.greenBut);
        pinkBut = findViewById(R.id.pinkBut);
        redBut = findViewById(R.id.redBut);
        violetBut = findViewById(R.id.violetBut);
        yellowBut = findViewById(R.id.yellowBut);

        textViewLetB = findViewById(R.id.textViewLetB);
        textViewLetG = findViewById(R.id.textViewLetG);
        textViewLetR = findViewById(R.id.textViewLetR);

        textViewColors = findViewById(R.id.textViewColors);

        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);

         seekBar_R = findViewById(R.id.seekBar_R);
        seekBar_R.setOnSeekBarChangeListener(seekBarChangeListener_R);

        int progress_R = seekBar_R.getProgress();

         seekBar_G = findViewById(R.id.seekBar_G);
        seekBar_G.setOnSeekBarChangeListener(seekBarChangeListener_G);

        int progress_G = seekBar_G.getProgress();

        seekBar_B = findViewById(R.id.seekBar_B);
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
            SetProgress();


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
            mServer.R_waarde=seekBar.getProgress();
            Send_R(mServer.R_waarde);


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
            mServer.G_waarde=seekBar.getProgress();
          Send_G(mServer.G_waarde);
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
            mServer.B_waarde=seekBar.getProgress();
           Send_B(mServer.B_waarde);
        }


    };

    public void Sendcolor_button(int R,int G , int B){
        mServer.R_waarde=R;
        mServer.B_waarde=B;
        mServer.G_waarde=G;
        SetProgress();
        Send_B(mServer.B_waarde);
        Send_G(mServer.G_waarde);
        Send_R(mServer.R_waarde);




    }

    public void SetProgress(){
        seekBar_R.setProgress(mServer.R_waarde);
        seekBar_G.setProgress(mServer.G_waarde);
        seekBar_B.setProgress(mServer.B_waarde);
        text_R.setText("value:"+mServer.R_waarde);
        text_G.setText("value:"+mServer.G_waarde);
        text_B.setText("value:"+mServer.B_waarde);

    }

    public void Send_R(int R){
        if (R<10){
            mServer.MyBlue.Send("0"+"0"+"0"+R);
        }
        else if (R<100){
            mServer.MyBlue.Send("0"+"0"+R);
        }
        else {
            mServer.MyBlue.Send("0" +R);
        }

    }
    public void Send_G(int G){
        if (G<10){
            mServer.MyBlue.Send("2"+"0"+"0"+G);
        }
        else if (G<100){
            mServer.MyBlue.Send("2"+"0"+G);
        }
        else {
            mServer.MyBlue.Send("2" +G);
        }

    }

    public void Send_B(int B){
        if (B<10){
            mServer.MyBlue.Send("1"+"0"+"0"+B);
        }
        else if (B<100){
            mServer.MyBlue.Send("1"+"0"+B);
        }
        else {
            mServer.MyBlue.Send("1" +B);
        }

    }


    public void basicled(View view) {
        basicmenu = true;
        button11.setTextColor(Color.parseColor("#0000FF"));
        button12.setTextColor(Color.parseColor("#CBCBCB"));

        textViewColors.setVisibility(View.VISIBLE);

        aquaBut.setVisibility(View.VISIBLE);        //R=0 G=255 B=255
        blueBut.setVisibility(View.VISIBLE);        //R=0 G=0 B=255
        orangeBut.setVisibility(View.VISIBLE);      //R=255 G=136 B=0
        greenBut.setVisibility(View.VISIBLE);       //R=0 G=255 B=0
        pinkBut.setVisibility(View.VISIBLE);        //R=255 G=0 B=238
        redBut.setVisibility(View.VISIBLE);         //R= 255 G=0 B=0
        violetBut.setVisibility(View.VISIBLE);      //R=185 G=0 B=142
        yellowBut.setVisibility(View.VISIBLE);      //R=255 G=255 B=0

        text_B.setVisibility(View.GONE);
        text_G.setVisibility(View.GONE);
        text_B.setVisibility(View.GONE);

        seekBar_B.setVisibility(View.GONE);
        seekBar_G.setVisibility(View.GONE);
        seekBar_R.setVisibility(View.GONE);

        textViewLetR.setVisibility(View.GONE);
        textViewLetG.setVisibility(View.GONE);
        textViewLetB.setVisibility(View.GONE);
    }


    public void advancedled(View view) {
        basicmenu = false;

        button11.setTextColor(Color.parseColor("#CBCBCB"));
        button12.setTextColor(Color.parseColor("#0000FF"));

        textViewColors.setVisibility(View.GONE);

        aquaBut.setVisibility(View.GONE);
        blueBut.setVisibility(View.GONE);
        orangeBut.setVisibility(View.GONE);
        greenBut.setVisibility(View.GONE);
        pinkBut.setVisibility(View.GONE);
        redBut.setVisibility(View.GONE);
        violetBut.setVisibility(View.GONE);
        yellowBut.setVisibility(View.GONE);

        text_B.setVisibility(View.VISIBLE);
        text_G.setVisibility(View.VISIBLE);
        text_R.setVisibility(View.VISIBLE);

        seekBar_B.setVisibility(View.VISIBLE);
        seekBar_G.setVisibility(View.VISIBLE);
        seekBar_R.setVisibility(View.VISIBLE);

        textViewLetR.setVisibility(View.VISIBLE);
        textViewLetG.setVisibility(View.VISIBLE);
        textViewLetB.setVisibility(View.VISIBLE);
    }


    public void Send_aqua(View view) {
        Sendcolor_button(0,255,255);
    }

    public void Send_blue(View view) {
        Sendcolor_button(0,0,255);
    }

    public void Send_green(View view) {
        Sendcolor_button(0,255,0);
    }

    public void Send_orange(View view) {
        Sendcolor_button(255,136,0);
    }

    public void Send_pink(View view) {
        Sendcolor_button(255,0,238);
    }

    public void Send_red(View view) {
        Sendcolor_button(255,0,0);
    }

    public void Send_violet(View view) {
        Sendcolor_button(185,0,142);
    }

    public void Send_yellow(View view) {
        Sendcolor_button(255,255,0);
    }
}
