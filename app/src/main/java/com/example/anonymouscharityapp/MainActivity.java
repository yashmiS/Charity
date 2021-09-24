package com.example.anonymouscharityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private Button button1;

    private TextView regpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        button1= findViewById(R.id.blogbutton);

        regpage = findViewById(R.id.regpage);
        regpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( MainActivity.this, Registeruser.class);
                startActivity(intent);
            }
        });

//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,Blog.class);
//                startActivity(intent);
//
//            }
//        });

    }
}