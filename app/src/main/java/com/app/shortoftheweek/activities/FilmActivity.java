package com.app.shortoftheweek.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.VideoView;

import com.app.shortoftheweek.R;

import uk.breedrapps.vimeoextractor.OnVimeoExtractionListener;
import uk.breedrapps.vimeoextractor.VimeoExtractor;
import uk.breedrapps.vimeoextractor.VimeoVideo;

/**
 * Created by cortland on 3/13/2017.
 */

public class FilmActivity extends Activity {
    TextView title;
    WebView videoView;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        title = (TextView) findViewById(R.id.videoTitle);
        videoView = (WebView) findViewById(R.id.videoView);
        description = (TextView) findViewById(R.id.videoDescription);

        String titleResult = getIntent().getExtras().getString("TitleKey");
        String videoResult = getIntent().getExtras().getString("LinkKey");
        String descriptionResult = getIntent().getExtras().getString("DescriptionKey");

        title.setText(titleResult);

        videoView.setInitialScale(1);
        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.getSettings().setLoadWithOverviewMode(true);
        videoView.getSettings().setUseWideViewPort(true);
        videoView.setScrollbarFadingEnabled(false);
        videoView.loadData(videoResult, "text/html", "utf-8");

        description.setMovementMethod(new ScrollingMovementMethod());
        description.setText(descriptionResult);
    }

}
