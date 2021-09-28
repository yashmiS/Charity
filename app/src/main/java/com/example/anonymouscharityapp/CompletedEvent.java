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

public class CompletedEvent extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference root;
    private FirebaseAuth auth;
    private String userId;

    private MyAdapterC adapterC;
    private ArrayList<ModelComplete> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_event);

        recyclerView = findViewById(R.id.recycleView8);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterC = new MyAdapterC(this, list);
        recyclerView.setAdapter(adapterC);
        auth = FirebaseAuth.getInstance();

        userId = auth.getCurrentUser().getUid();
        root = FirebaseDatabase.getInstance().getReference().child("CompletedEvents");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String eventName = dataSnapshot.child("eventName").getValue(String.class);
                    String image = dataSnapshot.child("image").getValue(String.class);
                    ModelComplete model = new ModelComplete(eventName, image);
                    list.add(model);
                }

                adapterC.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}