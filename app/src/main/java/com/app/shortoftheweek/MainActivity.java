package com.app.shortoftheweek;

/* Android imports*/
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* App imports */

/* Vimeo Imports */
import com.vimeo.networking.*;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity implements OnClickListener {

    /* Shortoftheweek Vimeo URL */
    public static final String SHORTOFTHEWEEK_VIDEO_URI = "/channels/shortoftheweek/videos";

    private VimeoClient mApiClient = VimeoClient.getInstance();
    // private ProgressDialog mProgressDialog;

    private TextView mRequestOutputTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ---- View Binding ---- */
        mRequestOutputTv = (TextView) findViewById(R.id.request_output_tv);
        findViewById(R.id.fetch_videos).setOnClickListener(this);
    }



    //@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fetch_videos:
                fetchShorts();
                break;
        }
    }

    private void fetchShorts() {

        mApiClient.fetchNetworkContent(SHORTOFTHEWEEK_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {

            @Override
            public void success(VideoList videoList) {
                if (videoList != null && videoList.data != null) {
                    String videoTitlesString = "";
                    boolean addNewLine = false;
                    for (Video video : videoList.data) {
                        if (addNewLine) {
                            videoTitlesString += "\n";
                        }
                        addNewLine = true;
                        videoTitlesString += video.name;
                    }
                    mRequestOutputTv.setText(videoTitlesString);
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
