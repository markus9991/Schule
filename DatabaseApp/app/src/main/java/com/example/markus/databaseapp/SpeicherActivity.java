package com.example.markus.databaseapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SpeicherActivity extends AppCompatActivity {

    public EditText id,vn,nn,a;
    DatabaseHelper dphelper=new DatabaseHelper(SpeicherActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speicher);
        id= (EditText) findViewById(R.id.et_eintrag_id);
        vn= (EditText) findViewById(R.id.et_eintrag_vn);
        nn= (EditText) findViewById(R.id.et_eintrag_nn);
        a= (EditText) findViewById(R.id.et_eintrag_a);
        Button bz= (Button)findViewById(R.id.b_eintrag_zu);
        bz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });

        Button bs= (Button) findViewById(R.id.b_eintrag_sp);
        bs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i1= Integer.parseInt(id.getText().toString());
                String s1= vn.getText().toString();
                String s2= nn.getText().toString();
                int i2= Integer.parseInt(a.getText().toString());

                boolean erfolg = dphelper.insertFreund(i1, s1, s2, i2);

                if(erfolg=true){
                    Toast.makeText(SpeicherActivity.this, "Erfolgreich eingetragen", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SpeicherActivity.this, "Fehler!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
