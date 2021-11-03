package com.techozon.cedricfinalappdesign.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import com.techozon.cedricfinalappdesign.Model.NutritionDataModel;
import com.techozon.cedricfinalappdesign.R;

import java.util.ArrayList;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder> {


    @NonNull
    private Context context;
    private NutritionDataModel nutritionIngredients;

    public IngredientsAdapter(@NonNull Context context, NutritionDataModel nutritionIngredients) {
        this.context = context;
        this.nutritionIngredients = nutritionIngredients;
    }

    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingerdient_recyclerview, parent, false);
        return new IngredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsHolder holder, int position) {
        NutritionDataModel uploadCurrentData = nutritionIngredients;

        holder.ingredientName.setText(uploadCurrentData.ingredients.get(position).getName());
        holder.ingredientQuantity.setText(uploadCurrentData.ingredients.get(position).getQuantity());
        holder.ingredientFats.setText( uploadCurrentData.ingredients.get(position).getFats());
        holder.ingredientCarbs.setText(uploadCurrentData.ingredients.get(position).getCarbs());
        holder.ingredientKcal.setText( uploadCurrentData.ingredients.get(position).getCalories());
        holder.ingredientProteins.setText(uploadCurrentData.ingredients.get(position).getProtiens());

    }

    @Override
    public int getItemCount() {
        return nutritionIngredients.ingredients.size();
    }

    class IngredientsHolder extends RecyclerView.ViewHolder {
        MaterialTextView ingredientName, ingredientQuantity, ingredientFats, ingredientProteins, ingredientCarbs, ingredientKcal;

        public IngredientsHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.textViewIngredientName);
            ingredientQuantity=itemView.findViewById(R.id.ingredQnty);
            ingredientCarbs = itemView.findViewById(R.id.ingredCarbs);
            ingredientFats = itemView.findViewById(R.id.ingredFats);
            ingredientKcal = itemView.findViewById(R.id.ingredKcal);
            ingredientProteins = itemView.findViewById(R.id.ingredProteins);

        }
    }
}
