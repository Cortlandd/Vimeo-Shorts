package com.app.shortoftheweek.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.VideoView;

import com.app.shortoftheweek.R;

/**
 * Created by cortland on 3/13/2017.
 */

public class FilmActivity extends Activity {
    TextView title;
    WebView embedVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        title = (TextView) findViewById(R.id.titleText);
        embedVideo = (WebView) findViewById(R.id.videoWebView);

        String titleResult = getIntent().getExtras().getString("TitleKey");
        String videoResult = getIntent().getExtras().getString("VideoKey");

        title.setText(titleResult);
        embedVideo.loadData(videoResult, "text/html", "utf-8");
    }
}
