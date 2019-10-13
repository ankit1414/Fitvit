package com.example.fitvit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {

    float weightInKg;
    float heightInCm;
    public static DecimalFormat df = new DecimalFormat("0.00");

    Button edit_height;
    Button edit_weight;
    LinearLayout grp_height_edit;
    LinearLayout grp_weight_edit;
    LinearLayout grp_height_save;
    LinearLayout grp_weight_save;
    Button save_height;
    Button save_weight;

    TextView bmi_tv, height_tv, weight_tv;
    EditText height_et, weight_et;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        sharedPreferences = this.getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);
        calculateBMI();


        grp_height_edit = findViewById(R.id.height_edit);
        grp_weight_edit = findViewById(R.id.weight_edit);
        grp_height_save = findViewById(R.id.height_save);
        grp_weight_save = findViewById(R.id.weight_save);

        edit_height = findViewById(R.id.edit_button_height);
        edit_weight = findViewById(R.id.edit_button_weight);

        save_height = findViewById(R.id.save_button_height);
        save_weight = findViewById(R.id.save_button_weight);

        height_tv = findViewById(R.id.height_bmi_activity);
        weight_tv = findViewById(R.id.weight_bmi_activity);

        height_et = findViewById(R.id.height_bmi_edit);
        weight_et = findViewById(R.id.weight_bmi_edit);

        edit_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grp_height_edit.setVisibility(LinearLayout.GONE);
                grp_height_save.setVisibility(LinearLayout.VISIBLE);


            }
        });

        edit_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grp_weight_edit.setVisibility(LinearLayout.GONE);
                grp_weight_save.setVisibility(LinearLayout.VISIBLE);
            }
        });

        save_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!height_et.getText().toString().isEmpty() && Float.parseFloat(height_et.getText().toString()) > 0) {
                    float newHeight = Float.parseFloat(height_et.getText().toString());
                    sharedPreferences.edit().putFloat("heightInCm", newHeight).apply();

                    grp_height_edit.setVisibility(LinearLayout.VISIBLE);
                    grp_height_save.setVisibility(LinearLayout.GONE);
                    calculateBMI();
                }

            }
        });

        save_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!weight_et.getText().toString().isEmpty() && Float.parseFloat(weight_et.getText().toString()) > 0) {
                    float newWeight = Float.parseFloat(weight_et.getText().toString());
                    sharedPreferences.edit().putFloat("weightInKg", newWeight).apply();
                    grp_weight_edit.setVisibility(LinearLayout.VISIBLE);
                    grp_weight_save.setVisibility(LinearLayout.GONE);
                    calculateBMI();
                }
            }
        });



    }
    public void calculateBMI(){

        sharedPreferences = this.getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);

        weightInKg = sharedPreferences.getFloat("weightInKg" , 0);
        heightInCm = sharedPreferences.getFloat("heightInCm" , 1);

        height_tv = findViewById(R.id.height_bmi_activity);
        weight_tv = findViewById(R.id.weight_bmi_activity);

        height_tv.setText(heightInCm + " cm ");
        weight_tv.setText(weightInKg + " kg ");


        float bmi = ((weightInKg)/((heightInCm*heightInCm)/(100*100)));
        bmi = Float.parseFloat(df.format(bmi));

        sharedPreferences.edit().putFloat("bmi" , bmi).apply();

        bmi_tv = findViewById(R.id.tv_bmi_bmiActivity);

        bmi_tv.setText(bmi+"");



    }
}
