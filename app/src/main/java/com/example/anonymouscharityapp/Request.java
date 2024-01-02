package com.example.anonymouscharityapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.PrimitiveIterator;


public class Request extends AppCompatActivity {
    private TextInputEditText requestTitle,requestLocation,requestStartDate,requestPerson,requestContact,requestDescription,requestEmail;
    private Button projectButton;
    private FirebaseAuth mAuth;
    private DatabaseReference root;
    private String userId;
    private Spinner projectTypesSpinner;
    private TextView backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        backButton = findViewById(R.id.backButton);
       backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Request.this,home.class);
               startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(Request.this, MainActivity.class));
        }
        userId = mAuth.getCurrentUser().getUid();
        requestTitle = findViewById(R.id.requestTitle);
        requestLocation = findViewById(R.id.requestLocation);
        requestStartDate = findViewById(R.id.requestStartDate);
        requestPerson = findViewById(R.id.requestPerson);
        requestContact = findViewById(R.id.requestContact);
        requestDescription = findViewById(R.id.requestDescription);
        requestEmail = findViewById(R.id.requestEmail);
        projectTypesSpinner = findViewById(R.id.projectTypesSpinner);
        projectButton = findViewById(R.id.projectButton);


        projectButton = findViewById(R.id.projectButton);
        root = FirebaseDatabase.getInstance().getReference();
        projectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String  title = requestTitle.getText().toString().trim();
                 String  location = requestLocation.getText().toString().trim();
                 String  startDate = requestStartDate.getText().toString().trim();
                 String  person = requestPerson.getText().toString().trim();
                 String  contact = requestContact.getText().toString().trim();
                 String  description = requestDescription.getText().toString().trim();
                 String email = requestEmail.getText().toString().trim();
                 String  projectType = projectTypesSpinner.getSelectedItem().toString();



                if (title.isEmpty()) {
                    requestTitle.setError("Title is required");
                    requestTitle.requestFocus();
                    return;
                }
                if (location.isEmpty()) {
                    requestLocation.setError("Location is Required");
                    requestLocation.requestFocus();
                    return;
                }
                if (startDate.isEmpty()) {
                    requestStartDate.setError("Start date is Required");
                    requestStartDate.requestFocus();
                    return;
                }
                if (person.isEmpty()) {
                    requestPerson.setError("Person is Required");
                    requestPerson.requestFocus();
                    return;
                }
                if (contact.isEmpty()) {
                    requestContact.setError("Contact is  Required");
                    requestContact.requestFocus();
                    return;
                }
                if (description.isEmpty()) {
                    requestDescription.setError("Description is  Required");
                    requestDescription.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    requestEmail.setError("Email is  Required");
                    requestEmail.requestFocus();
                    return;
                }
               if (projectType.equals("Select a project type")){
                   Toast.makeText(Request.this, "Select a project type", Toast.LENGTH_SHORT).show();
                   return;
               }



                Req req = new Req (title,location,startDate,person,contact,description,email);
               String reqId = userId+contact;
                root.child("request").child(userId).child(reqId).setValue(req).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Request.this, "Thank You!!!...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Request.this, home.class));
                    }
                });
            }
        });
    }
}









//package com.example.anonymouscharityapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//
//public class Request extends AppCompatActivity {
//
//
//    private TextView backButton;
//
//    private TextInputEditText requestTitle,requestLocation,requestStartDate,requestPerson,requestContact,requestDescription,requestEmail;
//
//    private Spinner projectTypesSpinner;
//
//    private Button projectButton;
//
//    private ProgressDialog loader;
//
//    private FirebaseAuth mAuth;
//
//    private DatabaseReference userDatabaseRef;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_request);
//
//        backButton = findViewById(R.id.backButton);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Request.this,home.class);
//                startActivity(intent);
//            }
//        });
//
//        requestTitle = findViewById(R.id.requestTitle);
//        requestLocation = findViewById(R.id.requestLocation);
//        requestStartDate = findViewById(R.id.requestStartDate);
//        requestPerson = findViewById(R.id.requestPerson);
//        requestContact = findViewById(R.id.requestContact);
//        requestDescription = findViewById(R.id.requestDescription);
//        requestEmail = findViewById(R.id.requestEmail);
//        projectTypesSpinner = findViewById(R.id.projectTypesSpinner);
//        projectButton = findViewById(R.id.projectButton);
//        loader = new ProgressDialog(this);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        projectButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String email = requestEmail.getText().toString().trim();
//                final String  title = requestTitle.getText().toString().trim();
//                final String  location = requestLocation.getText().toString().trim();
//                final String  startDate = requestStartDate.getText().toString().trim();
//                final String  person = requestPerson.getText().toString().trim();
//                final String  contact = requestContact.getText().toString().trim();
//                final String  description = requestDescription.getText().toString().trim();
//                final String  projectType = projectTypesSpinner.getSelectedItem().toString();
//
//               if (TextUtils.isEmpty(email)){
//                   requestEmail.setError("Email is required");
//                   return;
//               }
//                if (TextUtils.isEmpty(title)){
//                    requestTitle.setError("Title is required");
//                    return;
//                }
//                if (TextUtils.isEmpty(location)){
//                    requestLocation.setError("Location is required");
//                    return;
//                }
//                if (TextUtils.isEmpty(startDate)){
//                    requestStartDate.setError("Start Date is required");
//                    return;
//                }
//                if (TextUtils.isEmpty(person)){
//                    requestPerson.setError("Person is required");
//                    return;
//                }
//                if (TextUtils.isEmpty(contact)){
//                    requestContact.setError("Contact is required");
//                    return;
//                }
//                if (TextUtils.isEmpty(description)){
//                    requestDescription.setError("Description is required");
//                    return;
//                }
//               if (projectType.equals("Select a project type")){
//                   Toast.makeText(Request.this, "Select a project type", Toast.LENGTH_SHORT).show();
//                   return;
//               }
//            else {
//                    loader.setMessage("Request get...");
//                    loader.setCanceledOnTouchOutside(false);
//                    loader.show();
//
//
//
//
//               }
//            }
//        });
//
//    }
//}