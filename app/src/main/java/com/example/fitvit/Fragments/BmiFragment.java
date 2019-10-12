package com.example.fitvit.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fitvit.MainActivity;
import com.example.fitvit.R;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class BmiFragment extends Fragment {
    float weightInKg;
    float heightInCm;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        SharedPreferences sharedPreferences = getActivity().
                getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);
        weightInKg = sharedPreferences.getFloat("weightInKg" , 80);
        heightInCm = sharedPreferences.getFloat("heightInCm" , 160);

        float bmi = ((weightInKg)/((heightInCm*heightInCm)/(100*100)));
        bmi = Float.parseFloat(df.format(bmi));

        sharedPreferences.edit().putFloat("bmi" , bmi).apply();

        TextView bmi_tv;
        bmi_tv = view.findViewById(R.id.tv_bmi_frag);

        bmi_tv.setText(bmi+"");
        return view;
//        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

}
