package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.graphics.Color;
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
import com.techozon.cedricfinalappdesign.BestProgramExercises;
import com.techozon.cedricfinalappdesign.Model.ProgressDataModel;
import com.techozon.cedricfinalappdesign.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import static com.techozon.cedricfinalappdesign.R.drawable.textview_circular_shape_click;


public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.MyViewHolder> {
    private static List<ProgressDataModel> progressDataList;

    private Context context;
    MaterialTextView mTextViewDay1, mTextViewDay2, mTextViewDay3, mTextViewDay4,
            mTextViewDay5, mTextViewDay6, mTextViewDay7;
    MaterialCardView mCardViewNext;
    //private static View.OnClickListener myOnClickListener;


    //private Fragment context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView weekName;
        ImageView progressImage;
        MaterialTextView mTextViewDay1, mTextViewDay2, mTextViewDay3, mTextViewDay4,
                mTextViewDay5, mTextViewDay6, mTextViewDay7;
        ImageView mImageViewDay8;
        LinearLayout mCardViewDay1, mCardViewDay2, mCardViewDay3, mCardViewDay4,
                mCardViewDay5, mCardViewDay6, mCardViewDay7, mCardViewDay8;
        MaterialCardView mCardViewNext;

        public MyViewHolder(View itemView) {
            super(itemView);


            this.weekName = (MaterialTextView) itemView.findViewById(R.id.textViewWeek);
            this.progressImage = (ImageView) itemView.findViewById(R.id.progress_cardView_image);
            this.mTextViewDay1 = itemView.findViewById(R.id.textDay1);
            this.mTextViewDay2 = itemView.findViewById(R.id.textDay2);
            this.mTextViewDay3 = itemView.findViewById(R.id.textDay3);
            this.mTextViewDay4 = itemView.findViewById(R.id.textDay4);
            this.mTextViewDay5 = itemView.findViewById(R.id.textDay5);
            this.mTextViewDay6 = itemView.findViewById(R.id.textDay6);
            this.mTextViewDay7 = itemView.findViewById(R.id.textDay7);
            this.mImageViewDay8 = itemView.findViewById(R.id.imgViewNext);
            this.mCardViewNext = itemView.findViewById(R.id.cardViewNext);

        }


    }


    public ProgressAdapter(Context context, List<ProgressDataModel> progressDataList) {
        this.progressDataList = progressDataList;
        this.context = context;
    }

    @Override
    public ProgressAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.progress_cardview, parent, false);

        //view.setOnClickListener(ProgressAdapter.myOnClickListener);

        ProgressAdapter.MyViewHolder myViewHolder = new ProgressAdapter.MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        ProgressDataModel uploadCurrent = progressDataList.get(listPosition);

        holder.mTextViewDay1.setText(uploadCurrent.getDay1());
        holder.mTextViewDay2.setText(uploadCurrent.getDay2());
        holder.mTextViewDay3.setText(uploadCurrent.getDay3());
        holder.mTextViewDay4.setText(uploadCurrent.getDay4());
        holder.mTextViewDay5.setText(uploadCurrent.getDay5());
        holder.mTextViewDay6.setText(uploadCurrent.getDay6());
        holder.mTextViewDay7.setText(uploadCurrent.getDay7());
        holder.weekName.setText(uploadCurrent.getWeekNo());
        Glide.with(context).asBitmap().load(uploadCurrent.getProgress_img()).into(holder.progressImage);


        View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay1) {
                    //holder.mTextViewDay1.setBackgroundResource(R.color.yellow);
                    holder.mCardViewNext.setCardBackgroundColor(Color.parseColor("#D5A243"));
                    holder.mCardViewNext.setStrokeColor(Color.parseColor("#D5A243"));
                    holder.mTextViewDay1.setBackgroundColor(Color.parseColor("#D5A243"));
                    holder.mTextViewDay1.setBackgroundResource(textview_circular_shape_click);
                    startFragment();
                }
                if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay2) {
                    holder.mCardViewNext.setCardBackgroundColor(Color.parseColor("#D5A243"));
                    holder.mCardViewNext.setStrokeColor(Color.parseColor("#D5A243"));
                    holder.mTextViewDay2.setBackgroundResource(textview_circular_shape_click);
                    startFragment();
                }
                if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay3) {
                    holder.mCardViewNext.setCardBackgroundColor(Color.parseColor("#D5A243"));
                    holder.mCardViewNext.setStrokeColor(Color.parseColor("#D5A243"));
                    holder.mTextViewDay3.setBackgroundResource(textview_circular_shape_click);
                    startFragment();
                }
                if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay4) {
                    holder.mCardViewNext.setCardBackgroundColor(Color.parseColor("#D5A243"));
                    holder.mCardViewNext.setStrokeColor(Color.parseColor("#D5A243"));
                    holder.mTextViewDay4.setBackgroundResource(textview_circular_shape_click);
                    startFragment();
                }
                if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay5) {
                    holder.mCardViewNext.setCardBackgroundColor(Color.parseColor("#D5A243"));
                    holder.mCardViewNext.setStrokeColor(Color.parseColor("#D5A243"));
                    holder.mTextViewDay5.setBackgroundResource(textview_circular_shape_click);
                     startFragment();
                }
                if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay6) {
                    holder.mCardViewNext.setCardBackgroundColor(Color.parseColor("#D5A243"));
                    holder.mCardViewNext.setStrokeColor(Color.parseColor("#D5A243"));
                    holder.mTextViewDay6.setBackgroundResource(textview_circular_shape_click);
                     startFragment();
                }
                if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay7) {

                    holder.mCardViewNext.setCardBackgroundColor(Color.parseColor("#D5A243"));
                    holder.mCardViewNext.setStrokeColor(Color.parseColor("#D5A243"));
                    holder.mTextViewDay7.setBackgroundResource(textview_circular_shape_click);
                    startFragment();
                }


            }
        };
        holder.mTextViewDay1.setOnClickListener(myOnClickListener);
        holder.mTextViewDay2.setOnClickListener(myOnClickListener);
        holder.mTextViewDay3.setOnClickListener(myOnClickListener);
        holder.mTextViewDay4.setOnClickListener(myOnClickListener);
        holder.mTextViewDay5.setOnClickListener(myOnClickListener);
        holder.mTextViewDay6.setOnClickListener(myOnClickListener);
        holder.mTextViewDay7.setOnClickListener(myOnClickListener);


//
//        holder.mTextViewDay1.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay1) {
//                    //holder.mTextViewDay1.setBackgroundResource(R.color.yellow);
//                    holder.mCardViewNext.setCardBackgroundColor(Color.parseColor("#D5A243"));
//                    holder.mCardViewNext.setStrokeColor(Color.parseColor("#D5A243"));
//                    holder.mTextViewDay1.setBackgroundColor(Color.parseColor("#D5A243"));
//                    holder.mTextViewDay1.setBackgroundResource(textview_circular_shape_click);
//
//                }
//
//            }
//        });


    }

    private void startFragment() {
        Fragment fragment = new BestProgramExercises();
        FragmentTransaction transaction =  ((FragmentActivity)context)
                .getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navigation_container, fragment);
        transaction.addToBackStack("ProgressFragment");
        transaction.commit();

    }


    @Override
    public int getItemCount() {
        return progressDataList.size();
    }
}
