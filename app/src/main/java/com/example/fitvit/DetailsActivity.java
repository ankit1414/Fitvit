package com.example.fitvit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DetailsActivity extends AppCompatActivity {

    TextInputLayout textInputName;
    TextInputLayout textInputWeightInKg;
    TextInputLayout textInputHeightInCm;
    TextInputLayout textInputAge;
    TextInputLayout textInputStatus;
    Button confirmButton;

    private static DecimalFormat df = new DecimalFormat("0.00");
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textInputName = findViewById(R.id.name_til);
        textInputWeightInKg = findViewById(R.id.weight_til);
        textInputHeightInCm = findViewById(R.id.height_til);
        confirmButton = findViewById(R.id.confirm_button);
        textInputAge = findViewById(R.id.age_til);
        textInputStatus = findViewById(R.id.status_til);
        sharedPreferences = this.getSharedPreferences("com.example.fitvit" , Context.MODE_PRIVATE);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textInputName.getEditText().getText().toString().trim();
                float weightInKg = Float.parseFloat(textInputWeightInKg.getEditText().getText().toString().trim());
                float heightInCm = Float.parseFloat(textInputHeightInCm.getEditText().getText().toString().trim());
                String status = textInputStatus.getEditText().getText().toString().trim();
                int age = Integer.parseInt(textInputAge.getEditText().getText().toString().trim());
                if(checks(name , weightInKg,heightInCm , age)){

                    sharedPreferences.edit().putString(ProfileActivity.NAME , name).apply();
                    sharedPreferences.edit().putFloat(ProfileActivity.HEIGHT_IN_CM , heightInCm).apply();
                    sharedPreferences.edit().putFloat(ProfileActivity.WEIGHT_IN_KG, weightInKg).apply();
                    sharedPreferences.edit().putInt(ProfileActivity.AGE , age).apply();
                    sharedPreferences.edit().putString(ProfileActivity.STATUS,status).apply();
                    float bmi = ((weightInKg)/((heightInCm*heightInCm)/(100*100)));

                    bmi = Float.parseFloat(df.format(bmi));

                    sharedPreferences.edit().putFloat(ProfileActivity.BMI , bmi).apply();
                    // now we will change first time to nope so that this activity will never execute again
                    sharedPreferences.edit().putString("firstTime","nope").apply();
                    Intent intent = new Intent(DetailsActivity.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }
    boolean checks(String name , float weightInkg , float heightInCm, float age){
        boolean t = true;
        if(name.isEmpty()){
            textInputName.setError("Field can't be empty.");
            t = false;

        } else {
            textInputName.setError(null);
        }

        if(weightInkg <= 0){
            textInputWeightInKg.setError("Weight can't be negative or zero");
            t = false;
        } else if(weightInkg >0 && weightInkg <20){
            textInputWeightInKg.setError("Weight isn't practical");
            t = false;
        }else{
            textInputWeightInKg.setError(null);
        }

        if(heightInCm <= 0) {
            textInputHeightInCm.setError("Height can't be negative or zero");
            t = false;
        } else if(heightInCm >0 && heightInCm <60){
            textInputHeightInCm.setError("Height isn't practical");
            t = false;

        } else{
            textInputHeightInCm.setError(null);
        }
        if(age <= 0){
            textInputAge.setError("Invalid age");
            t = false;
        }
        return  t;

    }
}
