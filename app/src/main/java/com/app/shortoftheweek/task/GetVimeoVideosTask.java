package com.app.shortoftheweek.task;

import com.app.shortoftheweek.ShortOfTheWeek;
import com.app.shortoftheweek.event.VideoInfoReceivedEvent;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import java.util.ArrayList;


public class GetVimeoVideosTask {

    // Request to the Vimeo API
    public void getVideoInfo() {
        // Initializer to fetch content from API
        ShortOfTheWeek.getVimeoClient().fetchNetworkContent(ShortOfTheWeek.SHORTOFTHEWEEK_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {

            @Override
            public void success(VideoList videoList) {
                if (videoList != null && videoList.data != null && !videoList.data.isEmpty()) {
                    // Create array of Vimeo Videos called videos
                    ArrayList<Video> videos = new ArrayList<>();

                    // For Vimeo video represented as Vimeo response data in the Json
                    for(Video video : videoList.data) {
                        // Add Vimeo video to videos array previously created
                        videos.add(video);
                    }
                    // Return Vimeo videos to VideoInfoReceivedEvent
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
