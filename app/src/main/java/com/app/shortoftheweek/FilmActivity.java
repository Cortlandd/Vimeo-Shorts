package com.app.shortoftheweek;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.app.shortoftheweek.Film;
import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import static com.app.shortoftheweek.MainActivity.SHORTOFTHEWEEK_VIDEO_URI;

public class FilmActivity extends AppCompatActivity {

    VimeoClient mApiClient = VimeoClient.getInstance();

    /* Constant */
    public static final String EXTRA_FILMNO = "filmNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_detail);

        // Get the drink from the intent
        int filmNo = (Integer)getIntent().getExtras().get(EXTRA_FILMNO);
        // Using drinkNo to get the details of the drink the user chose
        Film film = Film.films[filmNo];

        /* =========================================================== */

        /* Populate the film title section */
        TextView name = (TextView) findViewById(R.id.filmTitle);
        // Used to set the text from film inside a textView
        name.setText(film.getTitle());

    }
    /*
    public void fetchTitles() {
        mApiClient.fetchNetworkContent(SHORTOFTHEWEEK_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {
            @Override
            public void success(VideoList videoList) {
                String videoTitleString = "";
                for (Video video : videoList.data) {
                    videoTitleString += video.name;
                }
                titleBox.setText(videoTitleString);
            }

            @Override
            public void failure(VimeoError error) {
                //toast("Staff Picks Failure");
            }
        });
    } */
}
