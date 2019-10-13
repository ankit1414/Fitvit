package com.example.fitvit;
//<<<<<<< HEAD

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.graphics.Color;
//=======
//<<<<<<< HEAD
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
//=======
//
//>>>>>>> a7e84aeef89a558cd4f3c2f32691e9b83c42c7cc
//
//>>>>>>> c98e3fe6029f4fc4e02bc3e5fa36b034aeaa6e46
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitvit.Fragments.BMIFragment;
import com.example.fitvit.Fragments.ExerciseFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , SensorEventListener {

    BarChart stepsbarchart;
    SensorManager sensorManager;

    TextView tv_steps;
    Boolean sensor_running = false;


    SharedPreferences sharedPreferences;
    TextView tv_bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = this.getSharedPreferences("com.example.fitvit" , Context.MODE_PRIVATE);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        TextView username = navigationView.findViewById(R.id.username_navMenu); //this causes blast as text view in navigation header cant be accessed directly
//        username.setText(sharedPreferences.getString("name" , "username"));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

//      method to update name in navigation header
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.username_navMenu);
        navUsername.setText(sharedPreferences.getString("name" , "username"));






        String firstTime = sharedPreferences.getString("firstTime","yes");
        if(firstTime.equals("yes")){
            //i will update firsttime in details activity
            Intent details = new Intent(MainActivity.this , DetailsActivity.class);
            startActivity(details);
            finish(); // this is very imp here else if user presses back in details activity without entering details he will enter main activity but this shouldnt happen first time

        }


        tv_steps  = findViewById(R.id.tv_steps);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        tv_bmi = findViewById(R.id.tv_bmi);


        generateTemporaryGraph();



    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = this.getSharedPreferences("com.example.fitvit",Context.MODE_PRIVATE);
        tv_bmi.setText(sharedPreferences.getFloat("bmi" , 0)+"");


        sensor_running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);


        if(countSensor != null) {
            sensorManager.registerListener(this, countSensor, sensorManager.SENSOR_DELAY_UI);
        }   else{
            //sensor not found
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensor_running = false;


        //if we unregister the hardware will stop detecting steps
        //sensorManager.unregisterListener(this);
    }

    //funtion to generate temporary bar graph
    public void generateTemporaryGraph(){


        stepsbarchart = findViewById(R.id.steps_bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(0,180f ));
        barEntries.add(new BarEntry(1,730f ));
        barEntries.add(new BarEntry(2,590f ));
        barEntries.add(new BarEntry(3,830f ));
        barEntries.add(new BarEntry(4,640f ));
        barEntries.add(new BarEntry(5,330f ));
        barEntries.add(new BarEntry(6,220f ));
        BarDataSet bardataset = new BarDataSet(barEntries , "Steps");
        String [] dates = new String[]{"01/10/2019","02/10/2019","03/10/2019","04/10/2019","05/10/2019","06/10/2019","07/10/2019"};


        BarData bardata = new BarData(bardataset);
        bardata.setBarWidth(0.9f);
        bardata.setValueTextSize(12);
        int whiteColorValue = Color.WHITE;
        bardataset.setValueTextColor(whiteColorValue);

        stepsbarchart.setData(bardata);
        stepsbarchart.setTouchEnabled(true);
        stepsbarchart.setDragEnabled(true);
        stepsbarchart.setScaleEnabled(true);
        stepsbarchart.setBackgroundColor(getResources().getColor(android.R.color.black));
        stepsbarchart.getXAxis().setTextColor(whiteColorValue);
        stepsbarchart.getAxisLeft().setDrawGridLines(false);
        stepsbarchart.getXAxis().setDrawGridLines(false);
        stepsbarchart.getAxisRight().setDrawGridLines(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        TextView username = findViewById(R.id.username_navMenu);
//        sharedPreferences = this.getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);
//        username.setText(sharedPreferences.getString("name" , "User Name"));
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        TextView username = findViewById(R.id.username_navMenu);
//        sharedPreferences = this.getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);
//        username.setText(sharedPreferences.getString("name" , "User Name"));
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        TextView username = findViewById(R.id.username_navMenu);
//        sharedPreferences = this.getSharedPreferences("com.example.fitvit", Context.MODE_PRIVATE);
//        username.setText(sharedPreferences.getString("name" , "User Name"));
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent profile_intent = new Intent(MainActivity.this , ProfileActivity.class);
            startActivity(profile_intent);
        } else if (id == R.id.nav_bmi) {
            Intent bmi_intent  = new Intent(MainActivity.this,BMIActivity.class);
            startActivity(bmi_intent);


        } else if (id == R.id.nav_water_reminder) {

            Intent i=new Intent (MainActivity.this,water_main.class);
            startActivity(i);

        } else if (id == R.id.nav_exercise) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.clContainer, new ExerciseFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "share functionality to be added soon !",
                    Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Send functionality to be added soon!",
                    Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensor_running){
            tv_steps.setText(String.valueOf(sensorEvent.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}