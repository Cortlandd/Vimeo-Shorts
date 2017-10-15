package com.app.vimeoshorts.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.vimeoshorts.R;
import com.app.vimeoshorts.VimeoShorts;
import com.app.vimeoshorts.adapter.VideoAdapter;
import com.app.vimeoshorts.classes.VideoRecyclerView;
import com.app.vimeoshorts.event.VideoInfoReceivedEvent;
import com.app.vimeoshorts.task.GetVimeoVideosTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.recycler_view) VideoRecyclerView recycler_view;

    @BindView(R.id.video_list_refresh) SwipeRefreshLayout refreshLayout;

    // Look and feel for Videos inside MainActivity
    VideoAdapter videoAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(this.getContext(), "Loading Content..", Toast.LENGTH_SHORT).show();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(llm);

        return view;

    }


    private void initRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(llm);

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
    public void onResume() {
        super.onResume();
        VimeoShorts.getEventBus().register(this);

        initRecyclerView();
    }

    @Override
    public void onPause() {
        VimeoShorts.getEventBus().unregister(this);
        super.onPause();
    }

    @SuppressWarnings("unused")
    public void onEvent(VideoInfoReceivedEvent event){
        if(refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);

        if(event.getVideos() != null) {
            videoAdapter = new VideoAdapter(event.getVideos());
            recycler_view.setAdapter(videoAdapter);
        } else {
            Toast.makeText(this.getContext(), "Error fetching videos :(", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
