package com.example.lsp.cardom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    BlueConnect MyBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        MyBlue=(BlueConnect) i.getSerializableExtra("ack1_class");
    }

    public void StartLED(View view) {
        Intent intent1 = new Intent(this,Main3Activity.class);
        intent1.putExtra("ack2_class", MyBlue);
        startActivity(intent1);
    }
}
