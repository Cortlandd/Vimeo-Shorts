package com.app.shortoftheweek;

import android.app.Application;
import android.content.Context;

import java.lang.String;

/* Vimeo Configuration Class */
import com.vimeo.networking.Configuration;
import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.callbacks.AuthCallback;
import com.vimeo.networking.model.error.VimeoError;

import com.app.shortoftheweek.AccountPreferenceManager;

public class VimeoConfig extends Application {

    public static final boolean ACCESS_TOKEN_PROVIDED = true;

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
        AccountPreferenceManager.initializeInstance(sContext);

        Configuration.Builder configBuilder;

        if (ACCESS_TOKEN_PROVIDED) {
            configBuilder = getAccessTokenBuilder();
        }

        VimeoClient.initialize(configBuilder.build());
    }


    public Configuration.Builder getAccessTokenBuilder() {

        // The values file is left out of git, so you'll have to provide your own access token

        String accessToken = getString(R.string.access_token);

        return new Configuration.Builder(accessToken);

    }


}


