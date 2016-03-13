package com.example.markus.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button be= (Button)findViewById(R.id.b_main_ein);
        be.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SpeicherActivity.class);
                startActivity(i);
            }
        });
        Button bab= (Button)findViewById(R.id.b_main_ab);
        bab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AbfrageActivity.class);
                startActivity(i);
            }
        });
        Button baus= (Button)findViewById(R.id.b_main_lo);
        baus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoeschActivity.class);
                startActivity(i);
            }
        });
    }
}
