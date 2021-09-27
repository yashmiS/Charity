package com.example.anonymouscharityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentMethod extends AppCompatActivity {

    private Button method1;
    private Button method2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        method1 = findViewById(R.id.method1Button);
        method2 = findViewById(R.id.method2Button);

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