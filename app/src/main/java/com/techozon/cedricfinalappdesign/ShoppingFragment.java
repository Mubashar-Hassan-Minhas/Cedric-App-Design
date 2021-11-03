package com.techozon.cedricfinalappdesign;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;


@SuppressWarnings("ALL")
public class ShoppingFragment extends Fragment {
    private ImageButton backArrow;
   private  MaterialButton mAddToShoppingList;
   private MaterialTextView mNutritionName;
   private ConstraintLayout mConstraintLayout;


    public ShoppingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddToShoppingList=view.findViewById(R.id.btnAddToShoppingList);
        mNutritionName=view.findViewById(R.id.textViewNutritionName);
        backArrow= view.findViewById(R.id.backArrow);
        mConstraintLayout=view.findViewById(R.id.shopping_fragment);

        mAddToShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nutritionName = mNutritionName.getText().toString();
                //mConstraintLayout.setVisibility(View.GONE);
                Fragment fragment = new ShoppingListFragment();
                @SuppressLint("UseRequireInsteadOfGet") FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("Nutrition", nutritionName); //key and value
                fragment.setArguments(bundle);
                transaction.replace(R.id.navigation_container, fragment);
                transaction.commit();
            }
        });




        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DashboardFragment();
                //replacing the fragment
                if (fragment != null) {
                    FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.navigation_container, fragment);
                    ft.addToBackStack("DashboardFragment");
                    ft.commit();
                }
            }
        });
    }
}