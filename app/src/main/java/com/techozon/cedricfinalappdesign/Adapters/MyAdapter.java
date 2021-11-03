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
import com.techozon.cedricfinalappdesign.R;
import com.google.android.material.textview.MaterialTextView;
import com.techozon.cedricfinalappdesign.SharedData;

import java.util.ArrayList;
import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<VideoHolder> {

    private Context context;
    String exercise, exerciseVideo,exerciseDescription;
    private CoachesDataModel mUploads;
    private ImageView imageThumbnail;
    private Bitmap bitmap;


    public MyAdapter(Context context, CoachesDataModel uploads) {
        this.context = context;
        mUploads = uploads;
    }



    @Override
    public VideoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.warm_up, viewGroup, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoHolder videoHolder, int position) {
        CoachesDataModel uploadCurrent = mUploads;
         //Integer warmUpCoachId= uploadCurrent.warmup.get(position).coachNumber;



                videoHolder.txtFileName.setText(uploadCurrent.getWarmup().get(position).name);
                videoHolder.textVideoDuration.setText(uploadCurrent.getWarmup().get(position).duration);
                Glide.with(context).load(uploadCurrent.getWarmup().get(position).getThumbnail()).into(videoHolder.imageThumbnail);
                exerciseVideo = uploadCurrent.warmup.get(position).videoUrl;
                exerciseDescription = uploadCurrent.warmup.get(position).description;
                exercise = videoHolder.txtFileName.getText().toString();
                System.out.println(exerciseVideo + "Video is there");
        //Picasso.get().load(uploadCurrent.getImgThumbnail()).into(videoHolder.imageThumbnail);
        try {
           // bitmap = uploadCurrent.getImgThumbnail();
            //if (bitmap != null) {
                //videoHolder.imageThumbnail.setImageBitmap(bitmap);

           // }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Are you there in thumbnail");
        }


        videoHolder.mWarmUpLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ExerciseDetailsFragment();
                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context)
                        .getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("position", exerciseVideo);
                bundle.putString("videoDescription", exerciseDescription);
                bundle.putString("exercise", exercise); //key and value
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.navigation_container, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
//
////                Intent intent = new Intent(context, ExerciseDetailsFragment.class);
////                intent.putExtra("exercise",uploadCurrent.getTitle());
////                intent.putExtra("position",uploadCurrent.getUrl());
////                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(intent);
            }
        });
    }


//    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
//        Bitmap bitmap = null;
//        MediaMetadataRetriever mediaMetadataRetriever = null;
//        try {
//            mediaMetadataRetriever = new MediaMetadataRetriever();
//            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
//            //mediaMetadataRetriever.setDataSource(videoPath);
//            bitmap = mediaMetadataRetriever.getFrameAtTime();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());
//
//        } finally {
//            if (mediaMetadataRetriever != null) {
//                mediaMetadataRetriever.release();
//            }
//        }
//        return bitmap;
//    }


    @Override
    public int getItemCount() {
        return mUploads.getWarmup().size();

    }




}

class VideoHolder extends RecyclerView.ViewHolder {

    MaterialTextView txtFileName;
    ImageView imageThumbnail;
    MaterialTextView textVideoDuration;
    LinearLayout mWarmUpLinearLayout;

    VideoHolder(View view) {
        super(view);

        txtFileName = view.findViewById(R.id.textViewWarmUP);
        textVideoDuration=view.findViewById(R.id.textViewWarmUpTime);
        imageThumbnail = view.findViewById(R.id.imageWarmUp);
        mWarmUpLinearLayout = view.findViewById(R.id.warmUpLinearLayout);


    }

}
