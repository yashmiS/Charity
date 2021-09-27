package com.example.anonymouscharityapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentMethod extends AppCompatActivity {

    private Button method1;
    private Button method2;
    private Button delete;
    private DatabaseReference root;
    private String userId;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        method1 = findViewById(R.id.method1Button);
        method2 = findViewById(R.id.method2Button);
        delete = findViewById(R.id.delete_details);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(PaymentMethod.this, MainActivity.class));
        }
        userId = mAuth.getCurrentUser().getUid();
        root = FirebaseDatabase.getInstance().getReference().child("card details");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Permanently Delete")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                root.child(userId).child("cvns").removeValue();
                                root.child(userId).child("cards").removeValue();
                                root.child(userId).child("dates").removeValue();
                                Toast.makeText(PaymentMethod.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Delete Card Details");
                alertDialog.show();

            }
        });


        method1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentMethod.this,CardDetails.class);
                startActivity(intent);
            }
        });

        method2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentMethod.this,SlipUploard.class);
                startActivity(intent);
            }
        });



    }
}