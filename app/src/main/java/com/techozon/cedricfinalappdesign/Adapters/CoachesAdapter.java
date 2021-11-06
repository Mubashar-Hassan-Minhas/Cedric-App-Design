package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.techozon.cedricfinalappdesign.Model.CoachesDataModel;
import com.techozon.cedricfinalappdesign.CoachesExercisesFragment;
import com.techozon.cedricfinalappdesign.DashboardFragment;
import com.techozon.cedricfinalappdesign.R;
import com.techozon.cedricfinalappdesign.SharedData;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class CoachesAdapter extends RecyclerView.Adapter<CoachesAdapter.MyViewHolder> {
    private  List<CoachesDataModel> dataArrayList;
    private Context context;
    String mCoachName;

    private Bitmap bitmap;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageViewIcon;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.coachesName);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.profileImage);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String coachName = name.getText().toString();
//                    Intent intent = new Intent(v.getContext(), CoachesExercises.class);
//                    //Bundle bundle=new Bundle();
//                    // intent.putExtra("Image",  dataSet.get(getAdapterPosition()).getProfile_img());
//                    //intent.putExtras(bundle);
//
//                    //intent.putExtra("Name",coachName);
//                    v.getContext().startActivity(intent);
//
//
//                }
//            });
        }
    }

    public  CoachesAdapter(Context context, List<CoachesDataModel> uploads) {
        this.context = context;
        dataArrayList = uploads;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_coaches, parent, false);

        view.setOnClickListener(DashboardFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        CoachesDataModel uploadCurrent = dataArrayList.get(listPosition);


        MaterialTextView textViewName = (MaterialTextView) holder.name;
        textViewName.setText(uploadCurrent.name);

        //holder.name.setText(uploadCurrent.name);

        Glide.with(context).asBitmap().load(uploadCurrent.imageURL).into(holder.imageViewIcon);

        // Picasso.get().load(uploadCurrent.getImgurl()).into(holder.imageViewIcon);
//        Bitmap bitmapThumbnail = ThumbnailUtils.createImageThumbnail(uploadCurrent.getImgurl(),
//                MediaStore.Images.Thumbnails.MINI_KIND);
//        holder.imageViewIcon.setImageBitmap(bitmapThumbnail);






        holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCoachName = holder.name.getText().toString();
              //  SharedData.coachId=uploadCurrent.coachNumber;

                System.out.println(SharedData.coachId+"id");

                Fragment fragment = new CoachesExercisesFragment();
                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context)
                        .getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putParcelable("coach", uploadCurrent);
                bundle.putString("profileImage", String.valueOf(uploadCurrent.imageURL));
                bundle.putString("CoachName", uploadCurrent.name);
                bundle.putString("description", uploadCurrent.description);
                bundle.putString("role", uploadCurrent.role);//key and value
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.navigation_container, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();

////                Intent intent = new Intent(context, ExerciseDetailsFragment.class);
////                intent.putExtra("exercise",uploadCurrent.getTitle());
////                intent.putExtra("position",uploadCurrent.getUrl());
////                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }
}
