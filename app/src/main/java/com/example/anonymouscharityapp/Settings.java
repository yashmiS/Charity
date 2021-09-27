package com.example.anonymouscharityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.Inet4Address;

public class Settings extends AppCompatActivity {


    private Button feedback;
    private Button help;
    private Button privacy;
    private Button logout;
    private TextView username,fullname;
    private FirebaseAuth auth;
    private DatabaseReference root;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        feedback = findViewById(R.id.bt_feed);
        help = findViewById(R.id.bt_help);
        privacy = findViewById(R.id.bt_privacy);
        logout = findViewById(R.id.bt_logout);
        username = findViewById(R.id.user_name);
        fullname = findViewById(R.id.full_name);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser()==null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        }else{
          userId = auth.getCurrentUser().getUid();
          root = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
          root.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  username.setText(snapshot.child("username").getValue().toString());
                  fullname.setText(snapshot.child("fullname").getValue().toString());
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
        }





//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Settings.this,feedback.Class());
//                  startActivity(intent);
//            }
//        });



        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, HelpCenter.class);
                startActivity(intent);
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this ,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}