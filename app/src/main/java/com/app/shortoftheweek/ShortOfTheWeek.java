package com.app.shortoftheweek;

import android.app.Application;

import java.lang.String;

/* Vimeo Configuration Class */
import com.vimeo.networking.Configuration;
import com.vimeo.networking.VimeoClient;

import de.greenrobot.event.EventBus;


public class ShortOfTheWeek extends Application {
    // Variable to make Vimeo API Requests
    private static VimeoClient mApiClient;
    // Shortoftheweek Channel endpoint
    public static final String SHORTOFTHEWEEK_VIDEO_URI = "/groups/1615/videos";
    private static EventBus eBus;

    // Method to return Variable used to make Vimeo API Requests
    public static VimeoClient getVimeoClient() {
        return mApiClient;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        eBus = EventBus.getDefault();

        // Setup initialization of access token and Vimeo client variable.
        Configuration.Builder configBuilder =
                new Configuration.Builder(getString(R.string.access_token));
        VimeoClient.initialize(configBuilder.build());
        mApiClient = VimeoClient.getInstance();
    }

    public static EventBus getEventBus() {
        return eBus;
    }

}


