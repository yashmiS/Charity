package com.example.anonymouscharityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Blog extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button4;

    ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        button1= findViewById(R.id.voButton);
        button2= findViewById(R.id.doButton3);
        button4= findViewById(R.id.coeButton4);


        flipper = (ViewFlipper) findViewById(R.id.flipper);

        int imgarray[] = {R.drawable.animals,R.drawable.doggi,R.drawable.group,R.drawable.kids};

        for (int i=0;i<imgarray.length;i++)
            showimage(imgarray[i]);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Blog.this,Donations.class);
                startActivity(intent);

            }
        });

    }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Blog.this,Volunteers.class);
                startActivity(intent);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Blog.this,CompletedEvent.class);
                startActivity(intent);

            }
        });
}


    public void showimage(int img){

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


}