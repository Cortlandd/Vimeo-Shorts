package com.app.shortoftheweek.activities;

/* Android imports*/
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* App imports */

/* Vimeo Imports */
import com.app.shortoftheweek.R;
import com.app.shortoftheweek.models.VideoModel;
import com.vimeo.networking.*;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener {

    private VimeoClient mApiClient = VimeoClient.getInstance();
    // private ProgressDialog mProgressDialog;

    private TextView mRequestOutputTv;

    ArrayList<VideoModel> myFilms = new ArrayList<>();
//    ArrayList<Video> myTest = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ---- View Binding ---- */
        mRequestOutputTv = (TextView) findViewById(R.id.request_output_tv);
        findViewById(R.id.fetch_videos).setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fetch_videos:
                fetchShorts();
                break;
        }
    }

    private void updateUpdateUI(ArrayList<Video> videos) {
        boolean addNewLine = false;
        String videoTitlesString = "";
        for(Video video : videos) {
            if (addNewLine) {
                videoTitlesString += "\n";
            }
            addNewLine = true;
            videoTitlesString += video.name;
            mRequestOutputTv.setText(videoTitlesString);
        }
    }

    private void fetchShorts() {

        mApiClient.fetchNetworkContent(SHORTOFTHEWEEK_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {

            @Override
            public void success(VideoList videoList) {
                if (videoList != null && videoList.data != null) {

                    for (Video video : videoList.data) {
                        VideoModel model = new VideoModel();
                        model.setTitle(video.name);
                        model.setLanguage(video.language);
                        myFilms.add(model);
                    }
                }
                toast("Staff Picks Success");
            }

            @Override
            public void failure(VimeoError error) {
                toast("Staff Picks Failure");
                mRequestOutputTv.setText(error.getDeveloperMessage());
            }
        });

    }

    private void toast(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
