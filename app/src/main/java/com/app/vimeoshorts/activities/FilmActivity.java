package com.app.vimeoshorts.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.webkit.WebView;
import android.widget.TextView;

import com.app.vimeoshorts.R;


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
        videoView.getSettings().setUserAgentString("desktop");
        videoView.getSettings().setLoadWithOverviewMode(true);
        videoView.getSettings().setUseWideViewPort(true);
        videoView.setScrollbarFadingEnabled(false);
        videoView.loadUrl(videoResult);

        description.setMovementMethod(new ScrollingMovementMethod());
        description.setText(descriptionResult);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // TODO: Create better implementation of stopping video audio.
        // A hack to stop video audio from playing when
        // back button is pressed.
        videoView.destroy();
    }
}
