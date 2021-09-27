package com.example.anonymouscharityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registeruser extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText Fullname, Email, NIC, Username, Pass;
    private Button regbutton;
    private FirebaseAuth mAuth;
    private TextView loginpg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        mAuth = FirebaseAuth.getInstance();
        Fullname = findViewById(R.id.RegFullname);
        Email = findViewById(R.id.RegEmail);
        NIC = findViewById(R.id.RegNIC);
        Username = findViewById(R.id.RegUsername);
        Pass = findViewById(R.id.RegPass);
        loginpg = findViewById(R.id.logpage);


        regbutton = findViewById(R.id.regbutton);
        regbutton.setOnClickListener(this);


        loginpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( Registeruser.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regbutton:
                regbutton();
                break;
        }
    }

    private void regbutton() {
        String email = Email.getText().toString().trim();
        String fullname = Fullname.getText().toString().trim();
        String password = Pass.getText().toString().trim();
        String Nic = NIC.getText().toString().trim();
        String username = Username.getText().toString().trim();


        if (fullname.isEmpty()) {
            Fullname.setError("Full Name Required");
            Fullname.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            Email.setError("Email Required");
            Email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Invalid Email");
            Email.requestFocus();
            return;
        }

        if (Nic.isEmpty()) {
            NIC.setError("NIC Required");
            NIC.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            Username.setError("Username Required");
            Username.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            Pass.setError("Password Required");
            Pass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            Pass.setError("Password is less than 6 characters!");
            Pass.requestFocus();
            return;
        }

               mAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(fullname, username, email, Nic);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        Toast.makeText(Registeruser.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(Registeruser.this,"Registration Failed", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });




                        }else{
                            Toast.makeText(Registeruser.this,"Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });




    }
}
