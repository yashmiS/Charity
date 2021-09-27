package com.example.anonymouscharityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView nav_view;
    private Button donate;
    private TextView event;

    private CircleImageView nav_profile_image;
    private TextView nav_full_name, nav_user_name;

    private DatabaseReference userRef;

    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        event = findViewById(R.id.event);


        donate = findViewById(R.id.projectButton);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, PaymentMethod.class);
                startActivity(intent);
            }
        });
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

        int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

        v_flipper = findViewById(R.id.v_flipper);


        for (int image : images) {
            flipperImages(image);
        }

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Anonymous Charity App");



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(home.this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//       nav_view.setNavigationItemSelectedListener(this);

//        nav_profile_image = nav_view.getHeaderView(0).findViewById(R.id.nav_user_image);
        nav_full_name = nav_view.getHeaderView(0).findViewById(R.id.nav_user_full_name);
        nav_user_name = nav_view.getHeaderView(0).findViewById(R.id.nav_user_name);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(home.this, MainActivity.class));
        }

        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(
                FirebaseAuth.getInstance().getCurrentUser().getUid());

        userRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("fullname").getValue().toString();
                    nav_full_name.setText(name);

                    String username = snapshot.child("username").getValue().toString();
                    nav_user_name.setText(username);

//                        if(snapshot.hasChild("imageUrl")){
//                            String imageUrl = snapshot.child("imageUrl").getValue().toString();
//                            Glide.with(getApplicationContext()).load(imageUrl).into(nav_profile_image);
//                        }else{
//                            nav_profile_image.setImageResource(R.drawable.profile_image);
//                        }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);//4 sec
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.event:
                Intent intent = new Intent(home.this,OngoingEvents.class);
                startActivity(intent);

                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(home.this,MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.requestForm:

                Intent intent3 = new Intent(home.this,Request.class);
                startActivity(intent3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}