package com.example.anonymouscharityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView regpage;
    private EditText logEmail, logPassword;
    private Button logbtn;

    private FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regpage = findViewById(R.id.regpage);
        regpage.setOnClickListener(this);

        logbtn = findViewById(R.id.logbutton);
        logbtn.setOnClickListener(this);

        logEmail = findViewById(R.id.logemail);
        logPassword = findViewById(R.id.logpass);

        mauth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.regpage:
                startActivity(new Intent(this, Registeruser.class));
                break;

            case R.id.logbutton:
                userLogin();
                break;
        }

    }

    private void userLogin() {

        String email = logEmail.getText().toString().trim();
        String pass = logPassword.getText().toString().trim();

        if(email.isEmpty()){
            logEmail.setError("Email is Required");
            logEmail.requestFocus();
            return;
        }

        /*if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            logEmail.setError("Invalid Email!");
            logEmail.requestFocus();
            return;
        }*/
        if(pass.isEmpty()){
            logPassword.setError("Passsword Required");
            logPassword.requestFocus();
            return;
        }
        if(pass.length()<6){
            logPassword.setError("Password must be more than 6 Characters!");
            logPassword.requestFocus();
            return;
        }

        mauth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to home page
                    startActivity(new Intent(MainActivity.this, home.class));
                }else{
                    Toast.makeText(MainActivity.this, "Failed to Login", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}