package com.techozon.cedricfinalappdesign;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class SleepVisualizationFragment extends Fragment implements Runnable {
    private MaterialTextView mTextViewDayTime,mTextViewVisualizationTitle,mTextViewVisualizationDescription;
    private ImageButton backArrow;
    //for seekbar
    ImageView mSleepVisualizationImg;
    MediaPlayer mediaPlayer = new MediaPlayer();
    SeekBar seekBar;
    boolean wasPlaying = false;
    FloatingActionButton fab;
    String audio, image, dayTime,description,title;


    public SleepVisualizationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep_visualization, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextViewDayTime = view.findViewById(R.id.textViewDayTime);
        mSleepVisualizationImg = view.findViewById(R.id.imageSleepVisualization);
        mTextViewVisualizationDescription=view.findViewById(R.id.textViewVisualizationDescription);
        mTextViewVisualizationTitle=view.findViewById(R.id.textViewVisualizationTitle);


        backArrow = view.findViewById(R.id.backArrow);
        // mCoachImage=view.findViewById(R.id.coachDp);
        dayTime = getArguments().getString("dayTime");
        image = getArguments().getString("imgUrl");
        audio = "https://firebasestorage.googleapis.com/v0/b/cedric-8cb7d.appspot.com/o/suits.mp3?alt=media&token=fbf38d3e-13fe-445b-84e1-840d5abb900e";
        title=getArguments().getString("visualizationTitle");
        description=getArguments().getString("audioDescription");
        System.out.println(image + "  hello hy by");
      //  Glide.with(getContext()).load(image).into(mSleepVisualizationImg);


        mTextViewDayTime.setText(dayTime);
        mTextViewVisualizationTitle.setText(title);
        mTextViewVisualizationDescription.setText(description);

        //go back to previous
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //seek bar for play music
        fab = view.findViewById(R.id.button);
        final TextView seekBarHint = view.findViewById(R.id.textView);

        seekBar = view.findViewById(R.id.seekbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong(audio);
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                seekBarHint.setVisibility(View.VISIBLE);
            }

            @SuppressLint("UseRequireInsteadOfGet")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                seekBarHint.setVisibility(View.VISIBLE);
                int x = (int) Math.ceil(progress / 1000f);

                if (x < 10)
                    seekBarHint.setText("0:0" + x);
                else
                    seekBarHint.setText("0:" + x);

                double percent = progress / (double) seekBar.getMax();
                int offset = seekBar.getThumbOffset();
                int seekWidth = seekBar.getWidth();
                int val = (int) Math.round(percent * (seekWidth - 2 * offset));
                int labelWidth = seekBarHint.getWidth();
                seekBarHint.setX(offset + seekBar.getX() + val
                        - Math.round(percent * offset)
                        - Math.round(percent * labelWidth / 2));

                if (progress > 0 && mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    clearMediaPlayer();
                    fab.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),
                            android.R.drawable.ic_media_play));
                    seekBar.setProgress(0);
                }

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });

    }

    private void onBackPressed() {

       // mediaPlayer.stop();
        Fragment fragment = new DashboardFragment();
            //replacing the fragment
            if (fragment != null) {
                FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.navigation_container, fragment);
                ft.addToBackStack("SleepVisualizationFragment");
                ft.commit();
        }

    }


    @SuppressLint("UseRequireInsteadOfGet")
    public void playSong(String audio) {

        try {


            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                clearMediaPlayer();
                seekBar.setProgress(0);
                wasPlaying = true;
                fab.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),
                        android.R.drawable.ic_media_play));
            }


            if (!wasPlaying) {

                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();

                }

                fab.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),
                        android.R.drawable.ic_media_pause));

                //  AssetFileDescriptor descriptor = getContext().getAssets().openFd("suits.mp3");
                // AssetFileDescriptor descriptor = getContext().getAssets().openFd(audio);
//                mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
//                descriptor.close();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(audio);
                mediaPlayer.prepare();
                mediaPlayer.setVolume(0.5f, 0.5f);
                mediaPlayer.setLooping(false);
                seekBar.setMax(mediaPlayer.getDuration());

                mediaPlayer.start();
                new Thread(this).start();

            }

            wasPlaying = false;
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void run() {

        int currentPosition = mediaPlayer.getCurrentPosition();
        int total = mediaPlayer.getDuration();


        while (mediaPlayer != null && mediaPlayer.isPlaying() && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mediaPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }

            seekBar.setProgress(currentPosition);

        }
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        clearMediaPlayer();
//    }

    private void clearMediaPlayer() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void onStop() {
       // mediaPlayer.release();
        super.onStop();
    }
}