package com.example.anonymouscharityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.anonymouscharityapp.Adapter.ProjectAdapter;
import com.example.anonymouscharityapp.Model1.Project;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OngoingEvents extends AppCompatActivity {

    private RecyclerView recycleView;
    private ArrayList<Project> projectList;
    private ProjectAdapter projectAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_events);


        recycleView = findViewById(R.id.recycleView5);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);

        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        projectList = new ArrayList<>();
        projectAdapter = new ProjectAdapter(OngoingEvents.this, projectList);

        recycleView.setAdapter(projectAdapter);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(OngoingEvents.this, MainActivity.class));
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("request");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                projectList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String requestTitle = ds.child("requestTitle").getValue(String.class);
                        String requestLocation = ds.child("requestLocation").getValue(String.class);
                        String requestStartDate = ds.child("requestStartDate").getValue(String.class);
                        String requestDescription = ds.child("requestDescription").getValue(String.class);


                        Project project = new Project(requestTitle, requestLocation, requestStartDate, requestDescription);
                        projectList.add(project);
                    }

                    projectAdapter.notifyDataSetChanged();
                }


//     DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("request");
//     Query query = reference.orderByChild("uid").equalTo("uid");
//     query.addValueEventListener(new ValueEventListener() {
//         @Override
//         public void onDataChange(@NonNull DataSnapshot snapshot) {
//             projectList.clear();;
//             for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                Project project = dataSnapshot.getValue(Project.class);
//                projectList.add(project);
//             }
//             projectAdapter.notifyDataSetChanged();
//             if(projectList.isEmpty()){
//                 Toast.makeText(OngoingEvents.this, "No Projects", Toast.LENGTH_SHORT).show();
//
//             }
//         }

//         @Override
//         public void onCancelled(@NonNull DatabaseError error) {
//
//         }
//     });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String Query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                projectAdapter.getFilter().filter(nextText);
                return false;
            }
        });
        return true;
    }


//    Map<String, Object> map = new HashMap<>();
//map.put("requestStartDate", ServerValue.TIMESTAMP);
//ref.child("request").updateChildren(map);
//    public static String getTimeDate(long timeStamp){
//        try{
//            DateFormat dateFormat = getDateTimeInstance();
//            Date netDate = (new Date(timeStamp));
//            return dateFormat.format(netDate);
//        } catch(Exception e) {
//            return "requestStartDate";
//        }
//    }
}