package com.example.fitvit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvtitle;
    TextView tvWod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvtitle = findViewById(R.id.tvTitle);
        tvWod = findViewById(R.id.tvWod);

        Bundle extra = getIntent().getExtras();
        if(extra != null)
        {
            String t = extra.getString("Extra_title");
            String w = extra.getString("Extra_wod");
            tvtitle.setText(t);
            tvWod.setText(w);
        }

    }
}
