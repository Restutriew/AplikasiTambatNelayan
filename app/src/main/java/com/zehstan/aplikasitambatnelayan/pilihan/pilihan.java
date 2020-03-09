package com.zehstan.aplikasitambatnelayan.pilihan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.zehstan.aplikasitambatnelayan.R;
import com.zehstan.aplikasitambatnelayan.login.login;
import com.zehstan.aplikasitambatnelayan.parkir.inputdataparkir;
import com.zehstan.aplikasitambatnelayan.parkir.scanqrparkir;
import com.zehstan.aplikasitambatnelayan.tambat.inputdatatambat;
import com.zehstan.aplikasitambatnelayan.tambat.scanqrtambat;

public class pilihan extends AppCompatActivity {

    Button scanqrtambat,inputdatatambat,scanqrparkir,inputdataparkir,logout;
    TextView username;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilihan_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scanqrtambat = findViewById(R.id.scanqrtambat);
        inputdatatambat = findViewById(R.id.inputdatatambat);
        scanqrparkir = findViewById(R.id.scanqrparkir);
        inputdataparkir = findViewById(R.id.inputdataparkir);
        username = findViewById(R.id.tvusername);
        logout = findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        username.setText(mUser.getEmail());

        scanqrtambat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pilihan.this, scanqrtambat.class);
                startActivity(i);
            }
        });

        inputdatatambat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pilihan.this, inputdatatambat.class);
                startActivity(i);
            }
        });

        scanqrparkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pilihan.this, scanqrparkir.class);
                startActivity(i);
            }
        });

        inputdataparkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(pilihan.this, inputdataparkir.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_out();
            }
        });

    }

    private void sign_out() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        Intent intent = new Intent(pilihan.this, login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
