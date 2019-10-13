package com.example.fitvit;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataHelper {
    public static ArrayList<Workout> loadWorkout(Context context){
        ArrayList<Workout> workouts = new ArrayList<>();
        String json = "";
        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer,"UTF-8");
        } catch (IOException e) {
            Toast.makeText(context, "error 1", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("girlsBenchmark");
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Workout workout = new Workout();
                workout.setTitle(jsonObject.getString("title"));
                workout.setWod(jsonObject.getString("wod"));
                workouts.add(workout);

            }
        } catch (JSONException e) {
            Toast.makeText(context, "error 2", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return workouts;

    }
}
