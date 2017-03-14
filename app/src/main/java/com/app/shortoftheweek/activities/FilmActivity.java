package com.app.shortoftheweek.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.app.shortoftheweek.R;

/**
 * Created by cortland on 3/13/2017.
 */

public class FilmActivity extends AppCompatActivity {
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        title = (TextView) findViewById(R.id.titleText);

        String titleResult = getIntent().getExtras().getString("TitleKet");

        title.setText(titleResult);
    }
}
