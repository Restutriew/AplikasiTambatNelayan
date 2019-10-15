package com.zehstan.aplikasitambatnelayan.pilihan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.zehstan.aplikasitambatnelayan.R;
import com.zehstan.aplikasitambatnelayan.history;
import com.zehstan.aplikasitambatnelayan.login.login;
import com.zehstan.aplikasitambatnelayan.mainActivity.MainActivity;

public class pilihan extends AppCompatActivity {

    Button scanqr,lihat_histori,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilihan_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scanqr = findViewById(R.id.scanqr);
        lihat_histori = findViewById(R.id.lihat_histori);
        logout = findViewById(R.id.logout);

        scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pilihan.this, MainActivity.class);
                startActivity(i);
            }
        });

        lihat_histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(pilihan.this, history.class);
                startActivity(j);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(pilihan.this, login.class);
                startActivity(k);
                finish();
            }
        });

    }

}
