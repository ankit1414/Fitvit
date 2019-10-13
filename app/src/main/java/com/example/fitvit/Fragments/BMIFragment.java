package com.example.fitvit.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitvit.BMIActivity;
import com.example.fitvit.MainActivity;
import com.example.fitvit.R;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class BMIFragment extends Fragment {

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

    TextView bmi_tv, height_tv, weight_tv;
    EditText height_et, weight_et;

    SharedPreferences sharedPreferences;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_bmi,container,false);

        sharedPreferences = getActivity().getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);
        calculateBMI();


        grp_height_edit = view.findViewById(R.id.height_edit);
        grp_weight_edit = view.findViewById(R.id.weight_edit);
        grp_height_save = view.findViewById(R.id.height_save);
        grp_weight_save = view.findViewById(R.id.weight_save);

        edit_height = view.findViewById(R.id.edit_button_height);
        edit_weight = view.findViewById(R.id.edit_button_weight);

        save_height = view.findViewById(R.id.save_button_height);
        save_weight = view.findViewById(R.id.save_button_weight);

        height_tv = view.findViewById(R.id.height_bmi_activity);
        weight_tv = view.findViewById(R.id.weight_bmi_activity);

        height_et = view.findViewById(R.id.height_bmi_edit);
        weight_et = view.findViewById(R.id.weight_bmi_edit);

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

        return view;
        //return inflater.inflate(R.layout.fragment_bmi, container, false);
    }
    public void calculateBMI(){

        sharedPreferences = getActivity().getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);

        weightInKg = sharedPreferences.getFloat("weightInKg" , 0);
        heightInCm = sharedPreferences.getFloat("heightInCm" , 1);

        height_tv = view.findViewById(R.id.height_bmi_activity);
        weight_tv = view.findViewById(R.id.weight_bmi_activity);

        height_tv.setText(heightInCm + " cm ");
        weight_tv.setText(weightInKg + " kg ");


        float bmi = ((weightInKg)/((heightInCm*heightInCm)/(100*100)));
        bmi = Float.parseFloat(df.format(bmi));

        sharedPreferences.edit().putFloat("bmi" , bmi).apply();

        bmi_tv = view.findViewById(R.id.tv_bmi_bmiActivity);

        bmi_tv.setText(bmi+"");



    }

}
