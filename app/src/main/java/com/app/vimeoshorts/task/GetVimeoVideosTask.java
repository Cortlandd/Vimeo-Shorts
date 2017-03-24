package com.app.vimeoshorts.task;

import com.app.vimeoshorts.VimeoShorts;
import com.app.vimeoshorts.event.VideoInfoReceivedEvent;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import java.util.ArrayList;


public class GetVimeoVideosTask {

    // Request to the Vimeo API
    public void getVideoInfo() {
        // Initializer to fetch content from API
        VimeoShorts.getVimeoClient().fetchNetworkContent(VimeoShorts.SHORTFILMS_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {

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
                    VimeoShorts.getEventBus().post(new VideoInfoReceivedEvent(videos));
                }
            }

            @Override
            public void failure(VimeoError error) {
                VimeoShorts.getEventBus().post(new VideoInfoReceivedEvent(null));
            }
        });
    }
}
