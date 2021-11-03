package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techozon.cedricfinalappdesign.ExerciseDetailsFragment;
import com.techozon.cedricfinalappdesign.Model.CoachesDataModel;
import com.techozon.cedricfinalappdesign.Model.WorkoutDataModel;
import com.techozon.cedricfinalappdesign.R;
import com.google.android.material.textview.MaterialTextView;
import com.techozon.cedricfinalappdesign.SharedData;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutVideoHolder> {

    private Context context;
    String exercise, exerciseVideo,exerciseDescription;
    private CoachesDataModel workOutUploads;
    private ImageView imageThumbnail;
    private Bitmap bitmap;


    public WorkoutAdapter(Context context, CoachesDataModel uploads) {
        this.context = context;
        workOutUploads = uploads;
    }


    @Override
    public WorkoutVideoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coaches_workout, viewGroup, false);
        return new WorkoutVideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkoutVideoHolder workoutVideoHolder, int position) {
        CoachesDataModel uploadCurrent = workOutUploads;//.get(position);
        Integer workoutCoachId= uploadCurrent.workout.get(position).coachNumber;
        System.out.println(workoutCoachId +"dsder");
        System.out.println(SharedData.coachId +"dffdf");


            workoutVideoHolder.txtFileName.setText(uploadCurrent.getWorkout().get(position).name);
            workoutVideoHolder.textVideoDuration.setText(uploadCurrent.getWorkout().get(position).duration);
            Glide.with(context).load(uploadCurrent.getWorkout().get(position).thumbnail).into(workoutVideoHolder.imageThumbnail);
            exerciseVideo = uploadCurrent.workout.get(position).videoUrl;
            exerciseDescription = uploadCurrent.workout.get(position).description;
            exercise = workoutVideoHolder.txtFileName.getText().toString();
            //Picasso.get().load(uploadCurrent.getImgThumbnail()).into(WorkoutVideoHolder.imageThumbnail);


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

                Fragment fragment = new ExerciseDetailsFragment();
                FragmentTransaction mFragmentTransaction = ((FragmentActivity) context)
                        .getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("videoDescription", exerciseDescription);
                bundle.putString("position", exerciseVideo);
                bundle.putString("exercise", exercise); //key and value
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.navigation_container, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();

            }
        });
    }


    @Override
    public int getItemCount() {

            return workOutUploads.getWorkout().size();


    }
}

class WorkoutVideoHolder extends RecyclerView.ViewHolder {

    MaterialTextView txtFileName;
    ImageView imageThumbnail;
    MaterialTextView textVideoDuration;
    LinearLayout mWarmUpLinearLayout;

    WorkoutVideoHolder(View view) {
        super(view);

        txtFileName = view.findViewById(R.id.textViewWorkout);
        textVideoDuration = view.findViewById(R.id.textViewWorkoutTime);
        imageThumbnail = view.findViewById(R.id.imageWorkout);
        mWarmUpLinearLayout = view.findViewById(R.id.workoutLinearLayout);


    }

}


