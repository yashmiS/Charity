package com.example.anonymouscharityapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.CalendarContract;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


import android.os.Bundle;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;

public class Contactus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


        Element adsElement = new Element();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Anonymous Hope is a non-profit Android application developed for Social Welfare Services!")
                .addItem(new Element().setTitle("Help us make a difference"))
                .addGroup("CONNECT WITH US!")
                .addEmail("Anonymoushope@gmail.com ")
                .addYoutube("www.youtube.com")   //Enter your youtube link here (replace with my channel link)
                .addPlayStore("com.example.yourprojectname")//Replace all this with your package name
                .addFacebook("www.facebook.com")
                .addInstagram("https://www.instagram.com/accounts/login/")    //Your instagram id
                .create();
        setContentView(aboutPage);
    }
    private Element createCopyright()
    {
        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright %d by Your Name", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Contactus.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
}
