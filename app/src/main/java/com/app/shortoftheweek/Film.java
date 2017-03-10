package com.app.shortoftheweek;


import android.widget.TextView;

import com.app.shortoftheweek.MainActivity;
import com.vimeo.networking.Vimeo;
import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Film {

    private String title;

    /* Shortoftheweek Vimeo URL */
    public static final String SHORTOFTHEWEEK_VIDEO_URI = "/channels/shortoftheweek/videos";

    private TextView titleText;

    public VideoList videoList;

    private VimeoClient mApiClient = VimeoClient.getInstance();

    public static final Film[] films = {
            new Film("First on List"),
            new Film("Second on List")
    };

    private void fetchTitles() {

        mApiClient.fetchNetworkContent(SHORTOFTHEWEEK_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {

            @Override
            public void success(VideoList videoList) {
                if (videoList != null && videoList.data != null && !videoList.data.isEmpty()) {
                    Video video = videoList.data.get(0); // just an example of getting the first video
                }
            }

            @Override
            public void failure(VimeoError error) {

            }
        });

    }

    public Film(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


}
