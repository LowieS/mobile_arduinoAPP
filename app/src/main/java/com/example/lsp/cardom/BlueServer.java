package com.example.lsp.cardom;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BlueServer extends Service {
    Context Mycontext ;
    boolean check= false;

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

    public boolean getShit() {

        BlueConnect MyBlue = new BlueConnect(Mycontext);
        if (MyBlue.BlueOn()) {
            if (MyBlue.StartConnect()) {

                Toast.makeText(getApplicationContext(), "connection", Toast.LENGTH_SHORT).show();
                // intent1.putExtra("ack1_class", myBlue);
                check=true;
            }
        }
        return check;
    }
}
