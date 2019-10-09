package com.example.fitvit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailsActivity extends AppCompatActivity {

    TextInputLayout textInputName;
    TextInputLayout textInputWeightInKg;
    TextInputLayout textInputHeightInCm;
    Button confirmButton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textInputName = findViewById(R.id.name_til);
        textInputWeightInKg = findViewById(R.id.weight_til);
        textInputHeightInCm = findViewById(R.id.height_til);
        confirmButton = findViewById(R.id.confirm_button);
        sharedPreferences = this.getSharedPreferences("com.example.fitvit" , Context.MODE_PRIVATE);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textInputName.getEditText().getText().toString().trim();
                float weightInKg = Float.parseFloat(textInputWeightInKg.getEditText().getText().toString().trim());
                float heightInCm = Float.parseFloat(textInputHeightInCm.getEditText().getText().toString().trim());
                if(checks(name , weightInKg,heightInCm)){

                    sharedPreferences.edit().putString("name" , name).apply();
                    sharedPreferences.edit().putFloat("heightInCm" , heightInCm).apply();
                    sharedPreferences.edit().putFloat("weightInKg" , weightInKg).apply();
                    float bmi = ((weightInKg)/((heightInCm*heightInCm)/(100*100)));

                    sharedPreferences.edit().putFloat("bmi" , bmi).apply();
                    finish();
                }

            }
        });


    }
    boolean checks(String name , float weightInkg , float heightInCm){
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
        return  t;

    }
}
