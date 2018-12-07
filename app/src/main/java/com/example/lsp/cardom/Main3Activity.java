package com.example.lsp.cardom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {
BlueConnect MyBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        MyBlue = new BlueConnect(this.getApplicationContext());

    }


    public void SENDT(View view) {
        if (MyBlue.BlueOn()){

            if (MyBlue.StartConnect()){
                MyBlue.SendT();
            }
        }
    }

    public void SENDF(View view) {
        if (MyBlue.BlueOn()){

            if (MyBlue.StartConnect()){
                MyBlue.SendF();
            }
        }
    }
}
