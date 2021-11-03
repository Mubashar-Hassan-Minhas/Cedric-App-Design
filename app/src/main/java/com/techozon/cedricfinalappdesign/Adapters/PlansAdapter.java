package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techozon.cedricfinalappdesign.Model.PlansDataModel;
import com.techozon.cedricfinalappdesign.R;
import com.techozon.cedricfinalappdesign.SharedData;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.MyViewHolder> {

    // creating a variable for our array list and context.
    private ArrayList<PlansDataModel> plansDataArrayList;
    private Context mcontext;
        String plan,  planMonthlyPrice, planDiscount;
    String planPrice="";
    MaterialTextView textViewDuration, textViewPerMonthPrice, textViewTotalPrice, textViewDiscount;
    MaterialCardView mCardViewForPlans;
    int index = -1;

    public PlansAdapter(ArrayList<PlansDataModel> plansDataArrayList, Context mcontext) {
        this.plansDataArrayList = plansDataArrayList;
        this.mcontext = mcontext;
    }

    @Override
    public PlansAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plans_recyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlansDataModel model = plansDataArrayList.get(position);
        String planId = model.getId();

        holder.textViewDiscount.setText(model.getDiscount());
        holder.textViewDuration.setText(model.getDuration());
        holder.textViewPerMonthPrice.setText(model.getMonthlyPrice());
        holder.textViewTotalPrice.setText(model.getTotalPrice());

        holder.mCardViewForPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();


            }
        });
        if (index == position) {
            holder.textViewDuration.setTextColor(Color.BLACK);
            holder.textViewTotalPrice.setTextColor(Color.BLACK);
            holder.mCardViewForPlans.setCardBackgroundColor(Color.parseColor("#D5A243"));

            SharedData.plan = holder.textViewDuration.getText().toString();
            SharedData.planDiscount = holder.textViewDiscount.getText().toString();
            SharedData.planMonthlyPrice = holder.textViewPerMonthPrice.getText().toString();
            SharedData.planPrice = holder.textViewTotalPrice.getText().toString();





        } else {
            holder.textViewDuration.setTextColor(Color.WHITE);
            holder.textViewTotalPrice.setTextColor(Color.WHITE);
            holder.mCardViewForPlans.setCardBackgroundColor(Color.TRANSPARENT);

        }

    }

    @Override
    public int getItemCount() {
        return plansDataArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView textViewDuration, textViewPerMonthPrice, textViewTotalPrice, textViewDiscount;
        MaterialCardView mCardViewForPlans;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDuration = itemView.findViewById(R.id.planDuration);
            textViewPerMonthPrice = itemView.findViewById(R.id.planPerMonthPrice);
            textViewTotalPrice = itemView.findViewById(R.id.planTotalPrice);
            textViewDiscount = itemView.findViewById(R.id.planDiscount);
            mCardViewForPlans = itemView.findViewById(R.id.cardViewPayMethod);

        }
    }
}
