package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techozon.cedricfinalappdesign.BestExerciseDetailsFragment;
import com.techozon.cedricfinalappdesign.ExerciseDetailsFragment;
import com.techozon.cedricfinalappdesign.Model.BestProgramModel;
import com.techozon.cedricfinalappdesign.Model.BestProgramWorkoutModel;
import com.techozon.cedricfinalappdesign.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class BestProgramWorkoutAdapter extends RecyclerView.Adapter<BestProgramWorkoutVideoHolder> {

    private Context context;
    String exercise, videoUrl;
    private BestProgramModel mUploads;
    private ImageView imageThumbnail;
    private Bitmap bitmap;
    private String exerciseVideo,exerciseDescription;


    public BestProgramWorkoutAdapter(Context context,BestProgramModel uploads) {
        this.context = context;
        mUploads = uploads;
    }


    @Override
    public BestProgramWorkoutVideoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.best_program_workout, viewGroup, false);
        return new BestProgramWorkoutVideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BestProgramWorkoutVideoHolder workoutVideoHolder, int position) {
        BestProgramModel uploadCurrent = mUploads;


        workoutVideoHolder.txtFileName.setText(uploadCurrent.workout.get(position).name);
        workoutVideoHolder.textVideoDuration.setText(uploadCurrent.workout.get(position).duration);
        //videoUrl=uploadCurrent.getUrl();
        Glide.with(context).load(uploadCurrent.workout.get(position).thumbnail).into(workoutVideoHolder.imageThumbnail);
        //Picasso.get().load(uploadCurrent.getImgThumbnail()).into(videoHolder.imageThumbnail);
        exerciseVideo = uploadCurrent.workout.get(position).videoUrl;
        exerciseDescription = uploadCurrent.workout.get(position).description;

        try {
            // bitmap = uploadCurrent.getImgThumbnail();
            //if (bitmap != null) {
            //WorkoutVideoHolder.imageThumbnail.setImageBitmap(bitmap);

            // }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Are you there in thumbnail");
        }


        workoutVideoHolder.mWarmUpLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise = workoutVideoHolder.txtFileName.getText().toString();
                Fragment fragment = new BestExerciseDetailsFragment();
                FragmentTransaction mFragmentTransaction = ((FragmentActivity) context)
                        .getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("position", exerciseVideo);
                bundle.putString("videoDescription", exerciseDescription);
                bundle.putString("exercise",exercise); //key and value
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.navigation_container, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return mUploads.workout.size();
    }
}

class BestProgramWorkoutVideoHolder extends RecyclerView.ViewHolder {

    MaterialTextView txtFileName;
    ImageView imageThumbnail;
    MaterialTextView textVideoDuration;
    LinearLayout mWarmUpLinearLayout;

    BestProgramWorkoutVideoHolder(View view) {
        super(view);

        txtFileName = view.findViewById(R.id.textViewBestProgramWorkout);
        textVideoDuration = view.findViewById(R.id.textViewBEstProgramWorkoutTime);
        imageThumbnail = view.findViewById(R.id.bestProgramImageWorkout);
        mWarmUpLinearLayout = view.findViewById(R.id.bestProgramWorkoutLinearLayout);


    }

}
