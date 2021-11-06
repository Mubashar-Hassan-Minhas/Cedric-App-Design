package com.techozon.cedricfinalappdesign;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.sql.Time;


@SuppressWarnings("ALL")
public class BestExerciseDetailsFragment extends Fragment {
    private MaterialTextView materialTextViewExerciseName, mVideoDescription;
    String exerciseName, description;
    VideoView videoView;
    String position;
    private MaterialButton mNextVideoButton;


    public BestExerciseDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_exercise_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videoView = (VideoView) view.findViewById(R.id.videoPlayer);
        materialTextViewExerciseName = view.findViewById(R.id.textViewExerciseName);
        mVideoDescription = view.findViewById(R.id.textViewExerciseDescriptionText);
        mNextVideoButton = view.findViewById(R.id.btnNextWorkout);
        mNextVideoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                nextWorkOut();

            }
        });
        assert getArguments() != null;
        exerciseName = getArguments().getString("exercise");
        materialTextViewExerciseName.setText(exerciseName);
        position = "https://firebasestorage.googleapis.com/v0/b/cedric-8cb7d.appspot.com/o/Windmill.mp4?alt=media&token=d890a9e1-7102-446d-8814-92afd19bcc74";
        description = getArguments().getString("videoDescription");
        mVideoDescription.setText(description);
        // getSupportActionBar().hide();

        playVideo();
    }

    private void nextWorkOut() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        }
    }

    private void playVideo() {
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        // videoView.setVideoPath(String.valueOf(MainActivity.fileArrayList.get(Integer.parseInt(position))));
        videoView.setVideoURI(Uri.parse(position));
        videoView.requestFocus();
        Time duration = new Time(videoView.getDuration());
        Toast.makeText(getContext(), "" + duration, Toast.LENGTH_SHORT).show();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println(position + 1 + "========================");
                try {
                    videoView.setVideoURI(Uri.parse(String.valueOf((DashboardFragment
                            .fileArrayList.get(Integer.parseInt(position + 1))))));
                    System.out.println(position + "===========++++++++++++=============");
                    videoView.start();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    System.out.println("===========++++++++++++=============");


                }
                //  nextWorkOut();

                // requireActivity().onBackPressed();


            }
        });
    }

}