package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.techozon.cedricfinalappdesign.ExerciseDetailsFragment;
import com.techozon.cedricfinalappdesign.R;
import com.techozon.cedricfinalappdesign.Model.WarmupDataModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class BestProgramAdapter extends RecyclerView.Adapter<BestProgramAdapter.MyViewHolder> {
    private Context context;
    String exercise,videoUrl;
    private List<WarmupDataModel> mUploads;
    private ImageView imageThumbnail;
    private Bitmap bitmap;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView txtFileName;
        ImageView imageThumbnail;
        MaterialTextView textVideoDuration;
        LinearLayout mWarmUpLinearLayout;


        public MyViewHolder(View itemView) {
            super(itemView);

            txtFileName = itemView.findViewById(R.id.textViewBestWarmUP);
            textVideoDuration=itemView.findViewById(R.id.textViewBestWarmUpTime);
            imageThumbnail = itemView.findViewById(R.id.imageBestWarmUp);
            mWarmUpLinearLayout = itemView.findViewById(R.id.warmUpBestLinearLayout);


        }
    }

    public  BestProgramAdapter(Context context, List<WarmupDataModel> uploads) {
        this.context = context;
        mUploads = uploads;
    }

    @Override
    public BestProgramAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.best_program_warmup, parent, false);

        //view.setOnClickListener(DashboardFragment.myOnClickListener);

        BestProgramAdapter.MyViewHolder myViewHolder = new BestProgramAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final BestProgramAdapter.MyViewHolder holder, final int listPosition) {
        WarmupDataModel uploadCurrent = mUploads.get(listPosition);

        holder.txtFileName.setText(uploadCurrent.getTitle());
        holder.textVideoDuration.setText(uploadCurrent.getTime());
        //videoUrl=uploadCurrent.getUrl();
        Glide.with(context).load(uploadCurrent.getImgThumbnail()).into(holder.imageThumbnail);
        //Picasso.get().load(uploadCurrent.getImgThumbnail()).into(videoHolder.imageThumbnail);



        holder.mWarmUpLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise = holder.txtFileName.getText().toString();
                Fragment fragment = new ExerciseDetailsFragment();
                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context)
                        .getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("position", uploadCurrent.url);
                bundle.putString("exercise", uploadCurrent.title); //key and value
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.navigation_container, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();

            }
        });




    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }
}

