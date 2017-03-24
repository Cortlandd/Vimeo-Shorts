package com.app.vimeoshorts.activities;

/* Android imports*/
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* App imports */

/* Vimeo Imports */
import com.app.vimeoshorts.R;
import com.app.vimeoshorts.VimeoShorts;
import com.app.vimeoshorts.adapter.VideoAdapter;
import com.app.vimeoshorts.classes.VideoRecyclerView;
import com.app.vimeoshorts.event.VideoInfoReceivedEvent;
import com.app.vimeoshorts.task.GetVimeoVideosTask;

public class MainActivity extends Activity implements OnClickListener {

    // Settings for Videos view inside MainActivity
    private VideoRecyclerView recyclerView;
    // Variable to enable refreshing videos
    private SwipeRefreshLayout refreshLayout;
    // Look and feel for Videos inside MainActivity
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Assign view for recycler view
        recyclerView = (VideoRecyclerView)findViewById(R.id.recycler_view);
        // TODO: Create comment here
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.video_list_refresh);

        Toast.makeText(this, "Loading Content..", Toast.LENGTH_SHORT).show();
    }

    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(llm);

        // Variable declaration of the Vimeo API Request
        GetVimeoVideosTask task = new GetVimeoVideosTask();
        // Request to Vimeo API to fetch videos
        task.getVideoInfo();

        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("Main", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        GetVimeoVideosTask task = new GetVimeoVideosTask();
                        task.getVideoInfo();
                    }
                }
        );
    }


    @Override
    protected void onResume() {
        super.onResume();
        VimeoShorts.getEventBus().register(this);

        initRecyclerView();
    }

    @Override
    protected void onPause() {
        VimeoShorts.getEventBus().unregister(this);
        super.onPause();
    }

    @SuppressWarnings("unused")
    public void onEvent(VideoInfoReceivedEvent event){
        if(refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);

        if(event.getVideos() != null) {
            videoAdapter = new VideoAdapter(event.getVideos());
            recyclerView.setAdapter(videoAdapter);
        } else {
            Toast.makeText(this, "Error fetching videos :(", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
