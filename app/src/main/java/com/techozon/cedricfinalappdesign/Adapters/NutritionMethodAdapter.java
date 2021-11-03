package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.techozon.cedricfinalappdesign.Model.NutritionDataModel;
import com.techozon.cedricfinalappdesign.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class NutritionMethodAdapter extends RecyclerView.Adapter<NutritionMethodAdapter.MyViewHolder> {
private ArrayList<String> methodArrayList;
private Context context;
int index=-1;





public static class MyViewHolder extends RecyclerView.ViewHolder {

    MaterialTextView stepNumber;
    MaterialTextView stepDetails;



    public MyViewHolder(View itemView) {
        super(itemView);

        this.stepNumber = (MaterialTextView) itemView.findViewById(R.id.textViewSerialNo);
        this.stepDetails =(MaterialTextView) itemView.findViewById(R.id.textViewSteps);


    }
}

    public  NutritionMethodAdapter(Context context, ArrayList<String> uploads) {
        this.context = context;
        methodArrayList = uploads;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nutrition_method, parent, false);

       // view.setOnClickListener(DashboardFragment.myOnClickListener);

       MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int listPosition) {
        ArrayList<String> uploadCurrent = methodArrayList;

        //  index++;
//        TextView textViewName = holder.name;
//        ImageView imageViewIcon = holder.imageViewIcon;
        System.out.println(uploadCurrent +"+++++");

        //holder.stepNumber.setText(listPosition);
        holder.stepDetails.setText(uploadCurrent.get(listPosition));






        // textViewName.setText(dataArrayList.get(listPosition).getName());
        // imageViewIcon.setImageResource(Integer.parseInt(dataArrayList.get(listPosition).getImgurl()));


//        holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCoachName = holder.name.getText().toString();
//                Fragment fragment = new CoachesExercisesFragment();
//                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context)
//                        .getSupportFragmentManager().beginTransaction();
//                Bundle bundle = new Bundle();
//                bundle.putString("profileImage", uploadCurrent.getImgurl());
//                System.out.println(uploadCurrent.getImgurl()+";;;;;;;;;;;;;;;;;;;;;;");
//                bundle.putString("CoachName", uploadCurrent.getName()); //key and value
//                bundle.putString("id",post_key);
//                fragment.setArguments(bundle);
//                mFragmentTransaction.replace(R.id.navigation_container, fragment);
//                mFragmentTransaction.addToBackStack(null);
//                mFragmentTransaction.commit();


//            }
//        });



    }

    @Override
    public int getItemCount() {
        return methodArrayList.size();

    }
}
