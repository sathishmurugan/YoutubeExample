package com.example.sathish.youtubeexample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY = "AIzaSyDXF_JXprKJOLDCqQnVw1LaetDlEJcjPj4";
    YouTubePlayer player;
    //https://www.youtube.com/watch?v=<VIDEO_ID>
    public static final String VIDEO_ID = "-m3V8w_7vhk";
    private RecyclerView recyclerView;
    YouTubePlayerView youTubePlayerView;
    String videoNewUrl;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // attaching layout xml
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        imageView = (ImageView) findViewById(R.id.imageView);
        // Initializing YouTube player view
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        // youTubePlayerView.initialize(API_KEY, this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Please Subscribe Us!", Toast.LENGTH_SHORT).show();
            }
        });
        MyListData[] myListData = new MyListData[]{
                new MyListData("video1", "-m3V8w_7vhk"),
                new MyListData("Info", "nkze5-eDInQ"),
                new MyListData("Delete", "aJ7BoNG-r2c"),
                new MyListData("Dialer", "wKwCgabRV2A"),
                new MyListData("Alert", "ZLNO2c7nqjw"),
                new MyListData("Map", "dusNpcOiTb8"),
                new MyListData("Email", "-uwr_KOzwaE")

        };


        MyListAdapter adapter = new MyListAdapter(myListData, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer player, boolean wasRestored) {
        imageView.setVisibility(View.GONE);
        youTubePlayerView.setVisibility(View.VISIBLE);
        if (null == player) return;

        // Start buffering
        if (!wasRestored) {

            // player.cueVideo(VIDEO_ID);
            player.loadVideo(getVideoNewUrl());
            this.player = player;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (player.getCurrentTimeMillis() <= 20000) {
                        handler.postDelayed(this, 1000);
                    } else {
                        handler.removeCallbacks(this);
                        player.release();
                        youTubePlayerView.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                    }
                }
            }, 1000);
        }

    }

    public YouTubePlayer getPlayer() {
        return player;
    }

    public void setPlayer(YouTubePlayer player) {
        this.player = player;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public YouTubePlayerView getYouTubePlayerView() {
        return youTubePlayerView;
    }

    public void setYouTubePlayerView(YouTubePlayerView youTubePlayerView) {
        this.youTubePlayerView = youTubePlayerView;
    }

    public String getVideoNewUrl() {
        return videoNewUrl;
    }

    public void setVideoNewUrl(String videoNewUrl) {
        this.videoNewUrl = videoNewUrl;
    }
}