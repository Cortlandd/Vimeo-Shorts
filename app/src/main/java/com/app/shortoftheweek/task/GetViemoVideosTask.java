package com.app.shortoftheweek.task;

import android.os.AsyncTask;
import android.util.Log;

import com.app.shortoftheweek.ShortOfTheWeek;
import com.app.shortoftheweek.event.VideoInfoReceivedEvent;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import java.util.ArrayList;

/**
 * Created by BraxtonN on 3/13/2017.
 */

public class GetViemoVideosTask {

    public void getVideoInfo() {

        ShortOfTheWeek.getVimeoClient().fetchNetworkContent(ShortOfTheWeek.SHORTOFTHEWEEK_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {

            @Override
            public void success(VideoList videoList) {
                if (videoList != null && videoList.data != null && !videoList.data.isEmpty()) {
                    ArrayList<Video> videos = new ArrayList<>();

                    for(Video video : videoList.data) {
                        videos.add(video);
                    }

                    ShortOfTheWeek.getEventBus().post(new VideoInfoReceivedEvent(videos));
                }
            }

            @Override
            public void failure(VimeoError error) {
                ShortOfTheWeek.getEventBus().post(new VideoInfoReceivedEvent(null));
            }
        });
    }
}
