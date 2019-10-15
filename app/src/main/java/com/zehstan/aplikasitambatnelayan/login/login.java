package com.zehstan.aplikasitambatnelayan.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zehstan.aplikasitambatnelayan.R;
import com.zehstan.aplikasitambatnelayan.pilihan.pilihan;

public class login extends AppCompatActivity {

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login = findViewById(R.id.login_submit);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, pilihan.class);
                startActivity(i);
                finish();
            }
        });

    }
}
