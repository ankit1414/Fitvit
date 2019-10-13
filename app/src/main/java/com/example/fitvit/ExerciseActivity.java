package com.example.fitvit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    ListView lv ;
    ArrayList<Workout> wodlist;
    ArrayList<String> TitleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        lv = findViewById(R.id.lv);
        wodlist = DataHelper.loadWorkout(this);
        TitleList = new ArrayList<>();
        for(int i = 0; i < wodlist.size(); i++)
        {
            String str = wodlist.get(i).getTitle();
            TitleList.add(str);
        }
        Adapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,TitleList);
        lv.setAdapter((ListAdapter) adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExerciseActivity.this, DetailActivity.class);
                String title = wodlist.get(position).getTitle();
                String wod = wodlist.get(position).getWod();

                intent.putExtra("Extra_title",title);
                intent.putExtra("Extra_wod",wod);
                startActivity(intent);
            }
        });
    }
}
