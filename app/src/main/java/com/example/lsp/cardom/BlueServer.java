package com.example.lsp.cardom;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BlueServer extends Service {
    Context Mycontext ;
    boolean check= false;
    BlueConnect MyBlue;
    int R_waarde=0;
    int G_waarde=0;
    int B_waarde = 0;

    boolean lamp1= false;
    boolean lamp2=false;

    IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;

    }

    public class LocalBinder extends Binder {
        public BlueServer getServerInstance() {
            return BlueServer.this;
        }
    }
    public  boolean BlueCon() {
        boolean check2=false;
        MyBlue = new BlueConnect(Mycontext);
        if (MyBlue.BlueOn()) {
            check2=true;
        }
        return check2;
    }


    public boolean getCon() {



            if (MyBlue.StartConnect()) {

                Toast.makeText(getApplicationContext(), "connection", Toast.LENGTH_SHORT).show();
                // intent1.putExtra("ack1_class", myBlue);
                check=true;
            }


        return check;
    }
}
