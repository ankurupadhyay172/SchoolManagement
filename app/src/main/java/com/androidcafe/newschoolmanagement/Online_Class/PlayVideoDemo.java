package com.androidcafe.newschoolmanagement.Online_Class;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.androidcafe.newschoolmanagement.R;

public class PlayVideoDemo extends AppCompatActivity {
    ProgressDialog dialog;
    VideoView videoView;
    ImageButton btnPlayPause;

    MediaController mediaController;
    String videoURL = "https://firebasestorage.googleapis.com/v0/b/gits-80131.appspot.com/o/About%20app%2FApp.mp4?alt=media&token=9100f9d7-fcd2-4d56-add1-8d2575ecaf80";

    SurfaceView surfaceView;
    ImageView download;

    String download_url = "https://firebasestorage.googleapis.com/v0/b/gits-80131.appspot.com/o/About%20app%2FSchool%20Management%20Application.pptx?alt=media&token=0319a7bd-eca3-4b24-a18e-76fbd381f48d";


    ImageView videoback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_demo);



        videoView = (VideoView)findViewById(R.id.videoView);
        btnPlayPause = (ImageButton)findViewById(R.id.btn_play_pause);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        download = (ImageView)findViewById(R.id.download_file);

        videoback = (ImageView)findViewById(R.id.videoback);

        videoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(download_url));
                startActivity(browserIntent);
            }
        });





        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlayVideoDemo.this,NewVideoPlayer.class);
                intent.putExtra("video",videoURL);
                startActivity(intent);
            }
        });












    }



}
