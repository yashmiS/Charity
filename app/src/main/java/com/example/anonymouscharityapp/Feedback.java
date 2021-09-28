package com.example.anonymouscharityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    private TextInputEditText fdname, fdexperience, fdsuggestion;
    private Button feedbutton;
    private FirebaseAuth mAuth;
    private DatabaseReference root;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            startActivity(new Intent(Feedback.this, home.class));
        }
        userId = mAuth.getCurrentUser().getUid();
        fdname = findViewById(R.id.feedname);
        fdexperience = findViewById(R.id.feedexp);
        fdsuggestion = findViewById(R.id.feedsug);

        feedbutton = findViewById(R.id.feedbtn);
        root = FirebaseDatabase.getInstance().getReference();

        feedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fdnames = fdname.getText().toString().trim();
                String fdexperiences = fdexperience.getText().toString().trim();
                String fdsuggestions = fdsuggestion.getText().toString().trim();

                if(fdnames.isEmpty()){
                    fdname.setError("Name Required!");
                    fdname.requestFocus();
                    return;
                }

                if(fdexperiences.isEmpty()){
                    fdexperience.setError("Rating Required!");
                    fdexperience.requestFocus();
                    return;
                }

                if(fdsuggestions.isEmpty()){
                    fdsuggestion.setError("Feedback Required!");
                    fdsuggestion.requestFocus();
                    return;
                }

                Suggest suggest = new Suggest(fdnames, fdexperiences, fdsuggestions);
                root.child("Feedback").child(userId).setValue(suggest).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Feedback.this, "Thank You!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Feedback.this, Profile.class));
                    }
                });
            }
        });


    }
}