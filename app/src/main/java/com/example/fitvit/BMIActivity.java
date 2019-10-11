package com.example.fitvit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {

    float weightInKg;
    float heightInCm;
    private static DecimalFormat df = new DecimalFormat("0.00");

    Button edit_height;
    Button edit_weight;
    LinearLayout grp_height_edit;
    LinearLayout grp_weight_edit;
    LinearLayout grp_height_save;
    LinearLayout grp_weight_save;
    Button save_height;
    Button save_weight;

    TextView bmi_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);
        weightInKg = sharedPreferences.getFloat("weightInKg" , 80);
        heightInCm = sharedPreferences.getFloat("heightInCm" , 160);

        float bmi = ((weightInKg)/((heightInCm*heightInCm)/(100*100)));
        bmi = Float.parseFloat(df.format(bmi));

        sharedPreferences.edit().putFloat("bmi" , bmi).apply();

        bmi_tv = findViewById(R.id.tv_bmi_bmiActivity);

        bmi_tv.setText(bmi+"");

        grp_height_edit = findViewById(R.id.height_edit);
        grp_weight_edit = findViewById(R.id.weight_edit);
        grp_height_save = findViewById(R.id.height_save);
        grp_weight_save = findViewById(R.id.weight_save);

        edit_height = findViewById(R.id.edit_button_height);
        edit_weight = findViewById(R.id.edit_button_weight);

        save_height = findViewById(R.id.save_button_height);
        save_weight = findViewById(R.id.save_button_weight);

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
                grp_height_edit.setVisibility(LinearLayout.VISIBLE);
                grp_height_save.setVisibility(LinearLayout.GONE);
            }
        });

        save_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grp_weight_edit.setVisibility(LinearLayout.VISIBLE);
                grp_weight_save.setVisibility(LinearLayout.GONE);
            }
        });



    }
}
