package com.androidcafe.newschoolmanagement.Online_Class;

import androidx.appcompat.app.AppCompatActivity;
import tv.danmaku.ijk.media.player.IMediaPlayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.R;
import com.khizar1556.mkvideoplayer.MKPlayer;

public class NewVideoPlayer extends AppCompatActivity {


    ImageButton btn_play_pause;


    MKPlayer mkplayer;
    String purl = "https://firebasestorage.googleapis.com/v0/b/gits-80131.appspot.com/o/About%20app%2FApp.mp4?alt=media&token=9100f9d7-fcd2-4d56-add1-8d2575ecaf80";

    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_video_player);



        btn_play_pause = (ImageButton)findViewById(R.id.btn_play_pause);

        mkplayer = new  MKPlayer(this);

        Intent intent = getIntent();

        url = intent.getStringExtra("video");


        mkplayer.play(url);


        mkplayer.onComplete(new Runnable() {
            @Override
            public void run() {
                //      Toast.makeText(PlayVideo.this, "Video completed", Toast.LENGTH_SHORT).show();
            }
        }).onInfo(new MKPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {

                switch (what)
                {
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        //            Toast.makeText(PlayVideo.this, "Buffering start", Toast.LENGTH_SHORT).show();
                        break;
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        //          Toast.makeText(PlayVideo.this, "Buffering end", Toast.LENGTH_SHORT).show();
                        break;
                    case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                        //        Toast.makeText(PlayVideo.this, "Downloading speed", Toast.LENGTH_SHORT).show();
                        break;
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        //      Toast.makeText(PlayVideo.this, "Rendering start", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        }).onError(new MKPlayer.OnErrorListener() {
            @Override
            public void onError(int i, int i1) {
                Toast.makeText(NewVideoPlayer.this, "Video Player error", Toast.LENGTH_SHORT).show();
            }
        });


        mkplayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            @Override
            public void onNextClick() {
                String url = "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";
                mkplayer.play(url);
                mkplayer.setTitle(url);
            }

            @Override
            public void onPreviousClick() {

                mkplayer.play(purl);
                mkplayer.setTitle("Ankur Upadhyay");

            }


        });













    }


    @Override
    protected void onPause() {
        super.onPause();

        if (mkplayer!=null)
        {
            mkplayer.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mkplayer!=null)
        {
            mkplayer.onResume();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mkplayer!=null)
        {
            mkplayer.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged( Configuration newConfig) {
        super.onConfigurationChanged(newConfig);



        if (mkplayer!=null)
        {
            mkplayer.onConfigurationChanged(newConfig);
        }



    }

    @Override
    public void onBackPressed() {
        if (mkplayer!=null&&mkplayer.onBackPressed())
        {
            return;
        }
        super.onBackPressed();

    }
}
