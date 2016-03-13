package com.example.markus.databaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoeschActivity extends AppCompatActivity {

    DatabaseHelper dphelper=new DatabaseHelper(LoeschActivity.this);
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loesch);
        Button bz= (Button)findViewById(R.id.b_loesch_zu);
        id= (EditText) findViewById(R.id.et_loesch_id);
        bz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button bd= (Button)findViewById(R.id.b_loesch_l);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pk = Integer.parseInt(id.getText().toString());
                boolean erfolg = dphelper.deleteFreund(pk);

                if (erfolg=true){
                    Toast.makeText(LoeschActivity.this, "Eintrag erfolgreich gelöscht", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
