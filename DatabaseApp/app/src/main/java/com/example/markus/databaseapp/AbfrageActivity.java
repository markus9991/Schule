package com.example.markus.databaseapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AbfrageActivity extends AppCompatActivity {

    EditText id, vn, nn, a;
    DatabaseHelper dphelper=new DatabaseHelper(AbfrageActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abfrage);
        id= (EditText) findViewById(R.id.et_abfrage_id);
        vn= (EditText) findViewById(R.id.et_abfrage_vn);
        nn= (EditText) findViewById(R.id.et_abfrage_nn);
        a= (EditText) findViewById(R.id.et_abfrage_a);
        Button bz= (Button)findViewById(R.id.b_abfrage_zu);
        bz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button ba= (Button)findViewById(R.id.b_abfrage_s);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pk= Integer.parseInt(id.getText().toString());
                Cursor c=dphelper.getData(pk);

                c.moveToFirst();

                int key = c.getInt(c.getColumnIndex(DatabaseContract.Freunde.COLUMN_NAME_ENTRY_ID));
                String vorname = c.getString(c.getColumnIndex(DatabaseContract.Freunde.COLUMN_NAME_FIRST_NAME));
                String nachname = c.getString(c.getColumnIndex(DatabaseContract.Freunde.COLUMN_NAME_LAST_NAME));
                int alter = c.getInt(c.getColumnIndex(DatabaseContract.Freunde.COLUMN_NAME_AGE));

                id.setText(String.valueOf(key));
                vn.setText(vorname);
                nn.setText(nachname);
                a.setText(String.valueOf(alter));


            }
        });


    }
}
