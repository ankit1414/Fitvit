package com.example.fitvit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    float weightInKg;
    float heightInCm;

    TextView bmi_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);
        weightInKg = sharedPreferences.getFloat("weightInKg" , 80);
        heightInCm = sharedPreferences.getFloat("heightInCm" , 160);

        float bmi = ((weightInKg)/((heightInCm*heightInCm)/(100*100)));

        sharedPreferences.edit().putFloat("bmi" , bmi).apply();

        bmi_tv = findViewById(R.id.tv_bmi_bmiActivity);

        bmi_tv.setText(bmi+"");


    }
}