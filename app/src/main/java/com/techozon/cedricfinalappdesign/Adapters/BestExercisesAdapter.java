//package com.example.cedricfinalappdesign.Adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cedricfinalappdesign.BestProgramExercises;
//
//import com.example.cedricfinalappdesign.R;
//import com.example.cedricfinalappdesign.Model.WarmupDataModel;
//
//import java.util.ArrayList;
//
//public class BestExercisesAdapter  extends RecyclerView.Adapter<BestExercisesAdapter.MyViewHolder> {
//private static ArrayList<WarmupDataModel> dataSet;
//
//public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//    TextView exerciseName;
//    TextView exerciseTimer;
//    ImageView exerciseIcon;
//
//
//    public MyViewHolder(View itemView) {
//        super(itemView);
//
//        this.exerciseName = (TextView) itemView.findViewById(R.id.textViewWarmUP);
//        this.exerciseTimer = (TextView) itemView.findViewById(R.id.textViewWarmUpTime);
//        this.exerciseIcon = (ImageView) itemView.findViewById(R.id.imageWarmUp);
//
////            itemView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    String specificExercise =exerciseName.getText().toString();
////                    Intent intent=new Intent(v.getContext(), ExerciseDetailsFragment.class);
////                    //Bundle bundle=new Bundle();
////                    //intent.putExtra("Image",  dataSet.get(getAdapterPosition()).getProfile_img());
////                    //intent.putExtras(bundle);
////
////                    intent.putExtra("Exercise",specificExercise);
////                    v.getContext().startActivity(intent);
////                }
////            });
//
//
//    }
//}
//
//    public BestExercisesAdapter(ArrayList<WarmupDataModel> data) {
//        this.dataSet = data;
//    }
//
//    @Override
//    public BestExercisesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
//                                                         int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.warm_up, parent, false);
//
//
//
//
//        view.setOnClickListener(BestProgramExercises.myOnClickListener);
//
//
//        BestExercisesAdapter.MyViewHolder myViewHolder = new BestExercisesAdapter.MyViewHolder(view);
//        return myViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final BestExercisesAdapter.MyViewHolder holder, final int listPosition) {
//
//        TextView textViewName = holder.exerciseName;
//        TextView textViewTimer = holder.exerciseTimer;
//        ImageView imageViewIcon = holder.exerciseIcon;
//
//
//        textViewName.setText(dataSet.get(listPosition).getExerciseName());
//        textViewTimer.setText(dataSet.get(listPosition).getExerciseTimer());
//        imageViewIcon.setImageResource(dataSet.get(listPosition).getDemo_img());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataSet.size();
//    }
//}
