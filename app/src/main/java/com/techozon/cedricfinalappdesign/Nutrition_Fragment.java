package com.techozon.cedricfinalappdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techozon.cedricfinalappdesign.Adapters.IngredientsAdapter;
import com.techozon.cedricfinalappdesign.Adapters.MyAdapter;
import com.techozon.cedricfinalappdesign.Adapters.NutritionMethodAdapter;
import com.techozon.cedricfinalappdesign.Model.NutritionDataModel;
import com.techozon.cedricfinalappdesign.Model.NutritionMethodModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Nutrition_Fragment extends Fragment {
    private ImageButton backArrow;
    private MaterialButton mAddToShoppingListButton;
    private MaterialTextView mNutritionName, mPrepareTime, mCooking, mIngredients
            ,mIngredient1,mIngredient2,mIngredient3,mIngredient4;


    int count = 5;

    private ImageView mNutritionImage;
    DatabaseReference mDatabaseReference;
    RecyclerView mMethodRecyclerView,mIngredientsRecyclerview;
    private static RecyclerView.Adapter adapter;
    IngredientsAdapter ingredientsAdapter;

    String nName, nPreparationTime, nCookStatus, nImg,nutritionResponse;
    ArrayList<String> method;
   // ArrayList<NutritionDataModel.Ingredients> ingredsList;
    NutritionDataModel ingredsList;
    DatabaseReference nutritionIngredientsReference;


    public Nutrition_Fragment() {
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
        View v = inflater.inflate(R.layout.fragment_nutritions, container, false);

        return v;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backArrow = view.findViewById(R.id.backArrow);

        //getting id's
        mMethodRecyclerView = view.findViewById(R.id.methodRecyclerview);
        mAddToShoppingListButton = view.findViewById(R.id.btnAddToShoppingList);
        mNutritionName = view.findViewById(R.id.textViewNutritionName);
        mNutritionImage = view.findViewById(R.id.nutritionImage);
        mCooking = view.findViewById(R.id.textViewCookStatus);
        mPrepareTime = view.findViewById(R.id.textViewPrepareTime);




//getting strings and set on specific views
        assert getArguments() != null;
        nName = getArguments().getString("nutritionName");
        nPreparationTime = getArguments().getString("prepareTime");
        nCookStatus = getArguments().getString("cook");
        method= (ArrayList<String>) getArguments().getStringArrayList("method");
        ingredsList= getArguments().getParcelable("ingredients");


        System.out.println(method + "pplpl");
        mNutritionName.setText(nName);
        //mPrepareTime.setText(nPreparationTime);
       // mCooking.setText(nCookStatus);

        try {
            nImg = getArguments().getString("nutritionImage");
            System.out.println(nImg +"nutrition Image");
            //Glide.with(getContext()).load(nImg).into(mNutritionImage);
            //Picasso.get().load(nImg).into(mNutritionImage);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("not working...");

        }
        //recyclerview for nutrition Ingredients
        mIngredientsRecyclerview = view.findViewById(R.id.ingredientsRecyclerview);
        mIngredientsRecyclerview.setHasFixedSize(true);
        mIngredientsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ingredientsAdapter = new IngredientsAdapter(getContext(),ingredsList);
        mIngredientsRecyclerview.setAdapter(ingredientsAdapter);

        //recyclerview for nutrition preparing method
        mMethodRecyclerView = view.findViewById(R.id.methodRecyclerview);
        mMethodRecyclerView.setHasFixedSize(true);
        mMethodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NutritionMethodAdapter(getContext(), method);
        mMethodRecyclerView.setAdapter(adapter);

        //fetching nutrition making steps
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Nutrition").child("BreakFast");
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    for (DataSnapshot subSnapshot : postSnapshot.getChildren()) {
//
//                        String uid = subSnapshot.getKey();
//                        System.out.println(uid + "=====sm======[[[[]]]]]");
//                        assert uid != null;
//                        //for ingredients data
//                        if (((uid.equals("Ingredients")))) {
//
//                            for (DataSnapshot nutritionIngredientsSnapshot : subSnapshot.getChildren()) {
//                                String ingredientsName= nutritionIngredientsSnapshot.getKey();
//                                if(ingredientsName.equals("Ingredient1")){
//                                    String iName = nutritionIngredientsSnapshot.child("ingredName").getValue(String.class);
//                                    String iQuantity = nutritionIngredientsSnapshot.child("quantity").getValue(String.class);
//                                    String iCarbs = nutritionIngredientsSnapshot.child("carbs").getValue(String.class);
//                                    String iFats = nutritionIngredientsSnapshot.child("fats").getValue(String.class);
//                                    String iProteins = nutritionIngredientsSnapshot.child("proteins").getValue(String.class);
//                                    String iKcal = nutritionIngredientsSnapshot.child("kcal").getValue(String.class);
//
//
//                                    //set on textViews
//                                    mIngredient1.setText(iName);
//                                    ingredient1[0].setText(iQuantity);
//                                    ingredient1[1].setText(iCarbs);
//                                    ingredient1[2].setText(iFats);
//                                    ingredient1[3].setText(iKcal);
//                                    ingredient1[4].setText(iProteins);
//                                    System.out.println(mIngredient1 + "=====Ingredient======[[[[]]]]]");
//
//
//                                }
//                                else if(ingredientsName.equals("Ingredient2")){
//
//                                    String iName = nutritionIngredientsSnapshot.child("ingredName").getValue(String.class);
//                                    String iQuantity = nutritionIngredientsSnapshot.child("quantity").getValue(String.class);
//                                    String iCarbs = nutritionIngredientsSnapshot.child("carbs").getValue(String.class);
//                                    String iFats = nutritionIngredientsSnapshot.child("fats").getValue(String.class);
//                                    String iProteins = nutritionIngredientsSnapshot.child("proteins").getValue(String.class);
//                                    String iKcal = nutritionIngredientsSnapshot.child("kcal").getValue(String.class);
//
//
//                                    //set on textViews
//                                    mIngredient2.setText(iName);
//                                    ingredient2[0].setText(iQuantity);
//                                    ingredient2[1].setText(iCarbs);
//                                    ingredient2[2].setText(iFats);
//                                    ingredient2[3].setText(iKcal);
//                                    ingredient2[4].setText(iProteins);
//                                    System.out.println(mIngredient2 + "=====Ingredient======[[[[]]]]]");
//
//                                }
//                                else if(ingredientsName.equals("Ingredient3")){
//
//                                    String iName = nutritionIngredientsSnapshot.child("ingredName").getValue(String.class);
//                                    String iQuantity = nutritionIngredientsSnapshot.child("quantity").getValue(String.class);
//                                    String iCarbs = nutritionIngredientsSnapshot.child("carbs").getValue(String.class);
//                                    String iFats = nutritionIngredientsSnapshot.child("fats").getValue(String.class);
//                                    String iProteins = nutritionIngredientsSnapshot.child("proteins").getValue(String.class);
//                                    String iKcal = nutritionIngredientsSnapshot.child("kcal").getValue(String.class);
//
//
//                                    //set on textViews
//                                    mIngredient3.setText(iName);
//                                    ingredient3[0].setText(iQuantity);
//                                    ingredient3[1].setText(iCarbs);
//                                    ingredient3[2].setText(iFats);
//                                    ingredient3[3].setText(iKcal);
//                                    ingredient3[4].setText(iProteins);
//                                    System.out.println(mIngredient3 + "=====Ingredient======[[[[]]]]]");
//
//                                }
//                                else if(ingredientsName.equals("Ingredient4")){
//
//                                    String iName = nutritionIngredientsSnapshot.child("ingredName").getValue(String.class);
//                                    String iQuantity = nutritionIngredientsSnapshot.child("quantity").getValue(String.class);
//                                    String iCarbs = nutritionIngredientsSnapshot.child("carbs").getValue(String.class);
//                                    String iFats = nutritionIngredientsSnapshot.child("fats").getValue(String.class);
//                                    String iProteins = nutritionIngredientsSnapshot.child("proteins").getValue(String.class);
//                                    String iKcal = nutritionIngredientsSnapshot.child("kcal").getValue(String.class);
//
//
//                                    //set on textViews
//                                    mIngredient4.setText(iName);
//                                    ingredient4[0].setText(iQuantity);
//                                    ingredient4[1].setText(iCarbs);
//                                    ingredient4[2].setText(iFats);
//                                    ingredient4[3].setText(iKcal);
//                                    ingredient4[4].setText(iProteins);
//                                    System.out.println(mIngredient4 + "=====Ingredient======[[[[]]]]]");
//
//                                }
//
//                            }
//                           // fetchNutritionIngredientData();
//
//                        }
//                        //for preparing Steps
//                        if ((uid.equals("method"))) {
//                            for (DataSnapshot nutritionSnapshot : subSnapshot.getChildren()) {
//                                System.out.println(nutritionSnapshot + "=====innner loop======[[[[]]]]]");
//
//                                NutritionMethodModel upload = nutritionSnapshot.getValue(NutritionMethodModel.class);
//                                nutritionMethodArrayList.add(upload);
//
//                            }
//                            adapter = new NutritionMethodAdapter(getContext(), nutritionMethodArrayList);
//                            mMethodRecyclerView.setAdapter(adapter);
//                        } else {
//
//                            //Toast.makeText(getContext(), "No Data ", Toast.LENGTH_LONG).show();
//                            System.out.println("not working...");
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });


//        nutritionIngredientsReference = FirebaseDatabase.getInstance().getReference()
//                .child("Cedric").child("Nutrition");
//        fetchNutritionIngredientData();


        mAddToShoppingListButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String nutritionName = mNutritionName.getText().toString();
                //mConstraintLayout.setVisibility(View.GONE);
                Fragment fragment = new ShoppingListFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("Nutrition", nName);    //key and value
                bundle.putString("nutritionImage", nImg);//key and value
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.navigation_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        backArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DashboardFragment();
                //replacing the fragment
                if (fragment != null) {
                    FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.navigation_container, fragment);
                    ft.addToBackStack("Nutrition_Fragment");
                    ft.commit();
                }
            }
        });

    }

   // private void fetchNutritionIngredientData() {
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//          @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//              for (DataSnapshot nutritionIngredientsSnapshot : snapshot.getChildren()) {
//                  System.out.println(nutritionIngredientsSnapshot + "=====innner nnn loop======[[[[]]]]]");
//
//              }
//          }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }


        // });


   // }

}



