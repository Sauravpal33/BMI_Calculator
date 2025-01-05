package com.example.bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    // Declare variables
    TextView currentHeight, currentWeight, currentAge;
    SeekBar seekBarHeight;
    RelativeLayout maleLayout, femaleLayout;
    Button calculateBmiButton;

    int weight = 55;
    int age = 20;
    int currentProgressHeight = 170;
    String gender = ""; // Default gender

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        seekBarHeight = findViewById(R.id.seekbarforheight);
        currentHeight = findViewById(R.id.currentheight);
        currentWeight = findViewById(R.id.currentweight);
        currentAge = findViewById(R.id.currentage);

        maleLayout = findViewById(R.id.male);
        femaleLayout = findViewById(R.id.female);
        calculateBmiButton = findViewById(R.id.calculatebmi);

        // Handle SeekBar
        seekBarHeight.setMax(300);
        seekBarHeight.setProgress(currentProgressHeight);
        currentHeight.setText(String.valueOf(currentProgressHeight));

        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgressHeight = progress;
                currentHeight.setText(String.valueOf(currentProgressHeight));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Handle gender selection
        maleLayout.setOnClickListener(v -> {
            maleLayout.setBackgroundResource(R.drawable.malefemalefocus);
            femaleLayout.setBackgroundResource(R.drawable.malefemalenotfocus);
            gender = "male";
        });

        femaleLayout.setOnClickListener(v -> {
            femaleLayout.setBackgroundResource(R.drawable.malefemalefocus);
            maleLayout.setBackgroundResource(R.drawable.malefemalenotfocus);
            gender = "female";
        });

        // Handle weight increment/decrement
        findViewById(R.id.incremetweight).setOnClickListener(v -> {
            weight++;
            currentWeight.setText(String.valueOf(weight));
        });

        findViewById(R.id.decrementweight).setOnClickListener(v -> {
            if (weight > 1) {
                weight--;
                currentWeight.setText(String.valueOf(weight));
            }
        });

        // Handle age increment/decrement
        findViewById(R.id.incrementage).setOnClickListener(v -> {
            age++;
            currentAge.setText(String.valueOf(age));
        });

        findViewById(R.id.decrementage).setOnClickListener(v -> {
            if (age > 1) {
                age--;
                currentAge.setText(String.valueOf(age));
            }
        });

        // Calculate BMI Button Click
        calculateBmiButton.setOnClickListener(v -> {
            if (!gender.equals("male") && !gender.equals("female")) {
                Toast.makeText(MainActivity.this, "Select Gender", Toast.LENGTH_SHORT).show();
            } else {
                double heightInMeters = currentProgressHeight / 100.0;
                double bmi = weight / (heightInMeters * heightInMeters);

                // Open Result Activity
                Intent intent = new Intent(MainActivity.this, bmiactivity.class);
                intent.putExtra("bmi", bmi);
                intent.putExtra("gender", gender);
                startActivity(intent);
            }
        });
    }
}


