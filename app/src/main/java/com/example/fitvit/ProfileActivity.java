package com.example.fitvit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    ImageButton profile_image;
    Uri imageUri;
    public static final int PICK_IMAGE = 1;
    TextInputLayout etname;

    TextInputLayout etheight;
    TextInputLayout etweight;
    TextInputLayout etage;
    TextInputLayout etstatus;

    TextView averagesteps_tv;
    TextView bmi_tv;
    SharedPreferences sharedPreferences;
    static final String MYPREFERENCES = "com.example.fitvit";
    static final String NAME = "name";
    static final String STATUS = "status";
    static final String WEIGHT_IN_KG = "weightInKg";
    static final String HEIGHT_IN_CM = "heightInCm";
    static final String AGE = "age";
    static final String BMI = "bmi";
    static final String AVERATGE_STEPS = "averageSteps";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_button_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemid = item.getItemId();
        if (itemid == R.id.save_button_ab) {

//            String n = etname.getEditText().getText().toString().trim();
//            String t = etstatus.getEditText().getText().toString().trim();
//            String a = etage.getEditText().getText().toString();
//            String h = etheight.getEditText().getText().toString();
//            String w = etweight.getEditText().getText().toString();
//
//
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//
//            editor.putString(NAME, n);
//            editor.putInt(AGE, Integer.parseInt(a));
//            editor.putString(STATUS, t);
//            editor.putFloat(WEIGHT_IN_KG, Float.parseFloat(w));
//            editor.putFloat(HEIGHT_IN_CM, Float.parseFloat(h));
//
//            editor.apply();
//
//            calculateBMI();
            boolean log = checksAndUpdate();
            if(log == true)
                Toast.makeText(this, "Information updated.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "some fields are filled incorrectly.", Toast.LENGTH_SHORT).show();

            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_image = findViewById(R.id.profile_img);
        etname = findViewById(R.id.etname);
        etstatus = findViewById(R.id.etstatus);
        etheight = findViewById(R.id.etheight);
        etweight = findViewById(R.id.etweight);
        etage = findViewById(R.id.etage);






        averagesteps_tv = findViewById(R.id.avgsteps_tv);
        bmi_tv = findViewById(R.id.profileBMI_tv);
        sharedPreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        bmi_tv.setText(sharedPreferences.getFloat(BMI , 21)+"");
        averagesteps_tv.setText(sharedPreferences.getString(AVERATGE_STEPS,"4558"));

        if (sharedPreferences.contains(NAME)) {
            etname.getEditText().setText(sharedPreferences.getString(NAME, ""));
            etname.setError(null);
        }
        if (sharedPreferences.contains(STATUS)) {
            etstatus.getEditText().setText(sharedPreferences.getString(STATUS, ""));
        }
        if (sharedPreferences.contains(AGE)) {
            etage.getEditText().setText(sharedPreferences.getInt(AGE, 0) + "");
            etage.setError(null);
        }
        if (sharedPreferences.contains(WEIGHT_IN_KG)) {
            etweight.getEditText().setText(sharedPreferences.getFloat(WEIGHT_IN_KG, 0) + "");
            etweight.setError(null);
        }
        if (sharedPreferences.contains(HEIGHT_IN_CM)) {
            etheight.getEditText().setText(sharedPreferences.getFloat(HEIGHT_IN_CM, 1) + "");
            etheight.setError(null);
        }




        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallary = new Intent();
                gallary.setType("image/*");
                gallary.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallary, "select picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == PICK_IMAGE && resultcode == RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profile_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    boolean checksAndUpdate(){

        String n = etname.getEditText().getText().toString().trim();
        String st = etstatus.getEditText().getText().toString().trim();
        String a = etage.getEditText().getText().toString();
        String h = etheight.getEditText().getText().toString();
        String w = etweight.getEditText().getText().toString();

        String name = n;
        float weightInkg = Float.parseFloat(w);
        float heightInCm = Float.parseFloat(h);
        int age = Integer.parseInt(a);

        SharedPreferences.Editor editor = sharedPreferences.edit();


        boolean t = true;
        if(name.isEmpty()){
            etname.setError("Field can't be empty.");
            t = false;

        } else {
            etname.setError(null);
            editor.putString(NAME, name);
        }

        if(weightInkg <= 0){
            etweight.setError("Weight can't be negative or zero");
            t = false;
        } else if(weightInkg >0 && weightInkg <20){
            etweight.setError("Weight isn't practical");
            t = false;
        }else{
            etweight.setError(null);
            editor.putFloat(WEIGHT_IN_KG, weightInkg);
        }

        if(heightInCm <= 0) {
            etheight.setError("Height can't be negative or zero");
            t = false;
        } else if(heightInCm >0 && heightInCm <60){
            etheight.setError("Height isn't practical");
            t = false;

        } else{
            etheight.setError(null);
            editor.putFloat(HEIGHT_IN_CM, heightInCm);
        }
        if(age < 8){
            etage.setError("Age should be above 8 years");
            t = false;
        } else{
            etage.setError(null);
            editor.putInt(AGE, age);
        }
        editor.putString(STATUS, st);

        editor.apply();

        calculateBMI();
        return  t;

    }

    public void calculateBMI(){

        sharedPreferences = this.getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);

        float weightInKg = sharedPreferences.getFloat("weightInKg" , 0);
        float heightInCm = sharedPreferences.getFloat("heightInCm" , 1);


        float bmi = ((weightInKg)/((heightInCm*heightInCm)/(100*100)));
        bmi = Float.parseFloat(BMIActivity.df.format(bmi));

        sharedPreferences.edit().putFloat(BMI , bmi).apply();

        bmi_tv.setText(sharedPreferences.getFloat(BMI , 21)+"");



    }
}
