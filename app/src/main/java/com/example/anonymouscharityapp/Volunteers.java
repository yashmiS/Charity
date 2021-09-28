package com.example.anonymouscharityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Volunteers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference root;
    private FirebaseAuth auth;
    private String userId;


    private MyAdapterV adapterV;
    private ArrayList<ModelVolunteers> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers);

        recyclerView = findViewById(R.id.recycleView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterV = new MyAdapterV(this, list);
        recyclerView.setAdapter(adapterV);
        auth = FirebaseAuth.getInstance();

        userId = auth.getCurrentUser().getUid();
        root = FirebaseDatabase.getInstance().getReference().child("Users");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String fullname = dataSnapshot.child("fullname").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    ModelVolunteers model = new ModelVolunteers(fullname, email);
                    list.add(model);
                }

                adapterV.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}