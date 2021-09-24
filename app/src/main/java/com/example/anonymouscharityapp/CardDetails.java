package com.example.anonymouscharityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class CardDetails extends AppCompatActivity {

    private TextInputEditText donation, name, card, date, cvn;
    private Button donatebutton;
    private FirebaseAuth mAuth;
    private DatabaseReference root;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);


        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null){
            startActivity(new Intent(CardDetails.this,MainActivity.class));
        }
        userId = mAuth.getCurrentUser().getUid();
        donation = findViewById(R.id.filledTextField01);
        name = findViewById(R.id.filledTextField0);
        card = findViewById(R.id.filledTextField1);
        date = findViewById(R.id.filledTextField2);
        cvn = findViewById(R.id.filledTextField3);


        donatebutton = findViewById(R.id.donateButton);
        root = FirebaseDatabase.getInstance().getReference();
        donatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String donations = donation.getText().toString().trim();
                String names = name.getText().toString().trim();
                String cards = card.getText().toString().trim();
                String dates = date.getText().toString().trim();
                String cvns = cvn.getText().toString().trim();


                Card card = new Card( donations,names,cards,dates,cvns);

                root.child("card details").child(userId).setValue(card).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });

            }
        });
    }






//    private void donatebutton() {
//
//        String donations = donation.getText().toString().trim();
//        String names = name.getText().toString().trim();
//        String cards = card.getText().toString().trim();
//        String dates = date.getText().toString().trim();
//        String cvns = cvn.getText().toString().trim();
//
//
//
//        if (donations.isEmpty()) {
//            donation.setError("Full Name Required");
//            donation.requestFocus();
//            return;
//        }
//        if (names.isEmpty()) {
//            name.setError("Email Required");
//            name.requestFocus();
//            return;
//        }
//
//
//        if (cards.isEmpty()) {
//            card.setError("NIC Required");
//            card.requestFocus();
//            return;
//        }
//        if (dates.isEmpty()) {
//            date.setError("Username Required");
//            date.requestFocus();
//            return;
//        }
//        if (cvns.isEmpty()) {
//            cvn.setError("Username Required");
//            cvn.requestFocus();
//            return;
//        }
//
//        mAuth.createUserWithEmailAndPassword(cards, cvns)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()) {
//                            Card card = new Card(donations, names, cards, dates, cvns);
//
//                            FirebaseDatabase.getInstance().getReference("Cards")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(card).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(CardDetails.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        Toast.makeText(CardDetails.this, "Registration Failed", Toast.LENGTH_LONG).show();
//                                    }
//
//                                }
//                            });
//                        }else{
//                            Toast.makeText(CardDetails.this,"Registration Failed", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//
//
//
//
//    }
}