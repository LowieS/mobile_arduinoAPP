package com.example.lsp.cardom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void goToConnect(View view) {
        Intent intent1 = new Intent(this, MainScreen.class);
        startActivity(intent1);
    }
}
