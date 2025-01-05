package com.example.bmi_calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class bmiactivity extends AppCompatActivity {

    TextView resultText, resultCategory, genderText;
    Button gotomain;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmiactivity);

        resultText = findViewById(R.id.bmidisplay);
        genderText = findViewById(R.id.genderdisplay);
        resultCategory = findViewById(R.id.bmicategorydispaly);
        gotomain = findViewById(R.id.gotomain);

        double bmi = getIntent().getDoubleExtra("bmi", 0);
        String gender = getIntent().getStringExtra("gender");

        resultText.setText(String.format("%.2f", bmi));
        genderText.setText(gender);

        gotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(bmiactivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 24.9) {
            category = "Normal weight";
        } else if (bmi < 29.9) {
            category = "Overweight";
        } else {
            category = "Obesity";
        }

        resultCategory.setText(category);
    }
}
