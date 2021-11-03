package com.techozon.cedricfinalappdesign.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.techozon.cedricfinalappdesign.ExploreBodySpecificDataModel;
import com.techozon.cedricfinalappdesign.R;

import java.util.ArrayList;

public class ExploreBodySpecificAdapter extends RecyclerView.Adapter<ExploreBodySpecificAdapter.MyViewHolder> {
    private static ArrayList<ExploreBodySpecificDataModel> dataSet;
    // private Fragment context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView bodySpecificImage;





        public MyViewHolder(View itemView) {
            super(itemView);


            this.bodySpecificImage = (ImageView) itemView.findViewById(R.id.bodySpecificExerciseImg);



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
    public ExploreBodySpecificAdapter(ArrayList<ExploreBodySpecificDataModel> data) {
        this.dataSet = data;
    }

    @Override
    public ExploreBodySpecificAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.explore_body_specific_cardview, parent, false);

       // view.setOnClickListener(ProgramsFragment.myOnClickListener);

        ExploreBodySpecificAdapter.MyViewHolder myViewHolder = new ExploreBodySpecificAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ExploreBodySpecificAdapter.MyViewHolder holder, final int listPosition) {


        ImageView imageViewMainWorkout = holder.bodySpecificImage;
        imageViewMainWorkout.setImageResource(dataSet.get(listPosition).getImageBodySpecific());




    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
