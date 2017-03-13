package com.app.shortoftheweek.models;

import android.databinding.BaseObservable;

/**
 * Created by mac10 on 3/12/2017.
 */

public class VideoModel extends BaseObservable{


    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String language;
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
