package com.app.shortoftheweek;

import android.app.Application;
import android.content.Context;

import java.lang.String;

/* Vimeo Configuration Class */
import com.vimeo.networking.Configuration;
import com.vimeo.networking.VimeoClient;

import de.greenrobot.event.EventBus;

public class ShortOfTheWeek extends Application {

    private static VimeoClient mApiClient;
    public static final String SHORTOFTHEWEEK_VIDEO_URI = "/channels/shortoftheweek/videos";
    private static EventBus eBus;

    public static VimeoClient getVimeoClient() {
        return mApiClient;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        eBus = EventBus.getDefault();

        Configuration.Builder configBuilder =
                new Configuration.Builder(getString(R.string.access_token));
        VimeoClient.initialize(configBuilder.build());
        mApiClient = VimeoClient.getInstance();
    }

    public static EventBus getEventBus() {
        return eBus;
    }

}


