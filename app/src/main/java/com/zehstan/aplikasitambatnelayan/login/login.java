package com.zehstan.aplikasitambatnelayan.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zehstan.aplikasitambatnelayan.R;
import com.zehstan.aplikasitambatnelayan.models.Admin;
import com.zehstan.aplikasitambatnelayan.pilihan.pilihan;

public class login extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Login";
    Button login,daftar;
    EditText login_username, login_password;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ProgressBar loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        daftar = findViewById(R.id.daftar_submit);
        login = findViewById(R.id.login_submit);
        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        loading = findViewById(R.id.loading);

        //variabel tadi untuk memanggil fungsi
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //nambahin method onClick, biar tombolnya bisa diklik
        login.setOnClickListener(this);
        daftar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.login_submit) {
            signIn();
        } else if (i == R.id.daftar_submit) {
            signUp();
        }

    }

    private void signUp() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        String email = login_username.getText().toString();
        String password = login_password.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        //hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(login.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn() {
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }

        loading.setVisibility(View.VISIBLE);
        String email = login_username.getText().toString();
        String password = login_password.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
                        //hideProgressDialog();

                        if (task.isSuccessful()) {
                            loading.setVisibility(View.GONE);
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(login.this, "Sign In Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    //fungsi dipanggil ketika proses Authentikasi berhasil
    private void onAuthSuccess(FirebaseUser user) {
            String username = usernameFromEmail(user.getEmail());

            // membuat User admin baru
            writeNewAdmin(user.getUid(), username, user.getEmail());

            // Go to MainActivity
            startActivity(new Intent(login.this, pilihan.class));
            finish();
            }

    /*
    ini fungsi buat bikin username dari email
        contoh email: abcdefg@mail.com
        maka username nya: abcdefg
 */
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }


    // menulis ke Database
    private void writeNewAdmin(String userId, String name, String email) {
        Admin admin = new Admin(name, email);

        mDatabase.child("admins").child(userId).setValue(admin);
    }




    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(login_username.getText().toString())) {
            login_username.setError("Required");
            result = false;
        } else {
            login_username.setError(null);
        }

        if (TextUtils.isEmpty(login_password.getText().toString())) {
            login_password.setError("Required");
            result = false;
        } else {
            login_password.setError(null);
        }

        return result;
    }
    }
