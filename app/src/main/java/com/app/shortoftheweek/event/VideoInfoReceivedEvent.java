package com.app.shortoftheweek.event;

import com.vimeo.networking.model.Video;

import java.util.ArrayList;

/**
 * Created by BraxtonN on 3/13/2017.
 */

public class VideoInfoReceivedEvent {
    private final ArrayList<Video> videos;

    public VideoInfoReceivedEvent(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }
}
