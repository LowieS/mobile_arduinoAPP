package com.example.lsp.cardom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
BlueConnect MyBlue;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent i = getIntent();
        MyBlue=(BlueConnect) i.getSerializableExtra("ack2_class");
        textView = (TextView) findViewById(R.id.textView);

    }


    public void SENDT(View view) {


                MyBlue.SendT();


    }

    public void SENDF(View view) {

                MyBlue.SendF();

    }

    public void click(View view) {

                textView.setText("new data");
              //
        // textView.append(MyBlue.ReadData());



    }
}
