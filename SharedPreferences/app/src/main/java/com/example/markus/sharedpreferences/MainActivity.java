package com.example.markus.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final String keyvn="vorname";
    public final String keynn="nachame";
    public final String prefs="shpref";

    EditText evn, enn;
    Button buttonload,buttonsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        evn=(EditText) findViewById(R.id.editText);
        enn=(EditText) findViewById(R.id.editText2);
        buttonload=(Button)findViewById(R.id.button);
        buttonload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences;
                sharedpreferences = getPreferences( Context.MODE_PRIVATE);
                String vn=sharedpreferences.getString(keyvn,null);
                evn.setText(vn);
                String nn=sharedpreferences.getString(keynn,null);
                enn.setText(nn);

            }
        });
        buttonsave=(Button)findViewById(R.id.button2);
        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences;
                sharedpreferences = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(keyvn,evn.getText().toString());
                editor.putString(keynn,enn.getText().toString());
                editor.commit();

            }
        });

    }


}
