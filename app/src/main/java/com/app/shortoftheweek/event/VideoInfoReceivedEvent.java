package com.app.shortoftheweek.event;

import com.vimeo.networking.model.Video;

import java.util.ArrayList;


public class VideoInfoReceivedEvent {
    // Declaration of an array representing Vimeo videos
    private final ArrayList<Video> videos;

    // TODO: Create comment here
    public VideoInfoReceivedEvent(ArrayList<Video> videos) {
        this.videos = videos;
    }

    // TODO: Create comment here
    public ArrayList<Video> getVideos() {
        return videos;
    }
}
