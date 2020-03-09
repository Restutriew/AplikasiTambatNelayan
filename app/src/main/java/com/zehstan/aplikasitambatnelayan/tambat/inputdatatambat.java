package com.zehstan.aplikasitambatnelayan.tambat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.zehstan.aplikasitambatnelayan.R;

public class inputdatatambat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambatinputdata_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Intent intent = getIntent();
//        String message = intent.getStringExtra(scanqrtambat.EXTRA_MESSAGE);
//        TextView textView = (TextView)findViewById(R.id.hasil);
//        textView.setText(message);
    }

}
