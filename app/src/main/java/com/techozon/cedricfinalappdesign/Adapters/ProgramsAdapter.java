package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techozon.cedricfinalappdesign.Model.ProgramsDataModel;
import com.techozon.cedricfinalappdesign.ProgressFragment;
import com.techozon.cedricfinalappdesign.R;
import com.google.android.material.textview.MaterialTextView;
import com.techozon.cedricfinalappdesign.SharedData;

import java.util.List;

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.MyViewHolder> {
    private static List<ProgramsDataModel> programsList;
    private Context context;
    String ProgramName;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView programName;
        ImageView programImage;
        MaterialTextView plan;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.programName = (MaterialTextView) itemView.findViewById(R.id.textViewProgramName);
            this.programImage = (ImageView) itemView.findViewById(R.id.bodyProgramImg);
            this.plan = (MaterialTextView) itemView.findViewById(R.id.textViewProgramPlan);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Fragment fragment = new ProgressFragment();
//                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//
//                    fragmentTransaction.replace(R.id.progress_fragment, fragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//
//
//
//
//
//                }
//            });


        }
    }
    public ProgramsAdapter(Context context, List<ProgramsDataModel> data) {
        this.context=context;
        this.programsList = data;
    }

    @Override
    public ProgramsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.programs_cardview, parent, false);

       // view.setOnClickListener(ProgramsFragment.myOnClickListener);

        ProgramsAdapter.MyViewHolder myViewHolder = new ProgramsAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        ProgramsDataModel uploadCurrent = programsList.get(listPosition);
        System.out.println(uploadCurrent +"programs Data");

        holder.programName.setText(uploadCurrent.getName());
        System.out.println(uploadCurrent.name +"program name");
       // holder.plan.setText(uploadCurrent.getPlan());
        Glide.with(context).asBitmap().load(uploadCurrent.getThumbnail()).into(holder.programImage);




        holder.programImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramName = holder.programName.getText().toString();
                SharedData.bestProgramId=uploadCurrent.getProgramId();
                System.out.println(SharedData.bestProgramId +"pId");
                SharedData.programName=uploadCurrent.getName();



                Fragment fragment = new ProgressFragment();
                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context)
                        .getSupportFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();

                bundle.putString("ProgramName", uploadCurrent.getName()); //key and value
                bundle.putString("ProgramId", String.valueOf(uploadCurrent.getProgramId()));
                bundle.putString("noOfWeeks", (uploadCurrent.getTotalWeeks()));
                //bundle.putString("id",post_key);
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.navigation_container, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();


            }
        });


    }

    @Override
    public int getItemCount() {
        return programsList.size();
    }
}
