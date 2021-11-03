//package com.techozon.cedricfinalappdesign;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Bundle;
//import android.widget.MediaController;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import com.google.android.material.textview.MaterialTextView;
//
//import java.sql.Time;
//
//public class ExerciseDetails extends AppCompatActivity {
//    private MaterialTextView materialTextViewExerciseName;
//
//    String name;
//    VideoView videoView;
//    String position;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_exercise_details);
//        materialTextViewExerciseName=findViewById(R.id.textViewExerciseName);
//        videoView = (VideoView) findViewById(R.id.videoPlayer);
//
//
//        //get the intent
//        Intent intent=getIntent();
//         //name=intent.getStringExtra("Exercise");
//        materialTextViewExerciseName.setText(name);
//        position = intent.getStringExtra("position");
//      //  getSupportActionBar().hide();
//
//        playVideo();
//    }
//
//
//    private void playVideo()
//    {
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//
//        videoView.setMediaController(mediaController);
//        // videoView.setVideoPath(String.valueOf(MainActivity.fileArrayList.get(Integer.parseInt(position))));
//        videoView.setVideoURI(Uri.parse(position));
//        videoView.requestFocus();
//        Time duration =new Time(videoView.getDuration());
//        Toast.makeText(this, ""+duration, Toast.LENGTH_SHORT).show();
//
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                videoView.start();
//            }
//        });
//
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                videoView.setVideoURI(Uri.parse(String.valueOf((CoachesExercises
//                        .fileArrayList.get(Integer.parseInt(position+1))))));
//                videoView.start();
//
//            }
//        });
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        videoView.stopPlayback();
//    }
//
//
//}