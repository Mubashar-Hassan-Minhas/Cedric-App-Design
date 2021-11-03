//package com.example.cedricfinalappdesign;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.transition.Visibility;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.util.TypedValue;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.cedricfinalappdesign.Adapters.CoachesAdapter;
//import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.card.MaterialCardView;
//import com.google.android.material.textview.MaterialTextView;
//
//import java.util.ArrayList;
//
//@SuppressWarnings("SwitchStatementWithoutDefaultBranch")
//public class Dashboard extends AppCompatActivity implements View.OnClickListener {
//
//    public static View.OnClickListener myOnClickListener;
//    private RecyclerView.LayoutManager layoutManager;
//    FrameLayout frameLayout;
//    private ImageView mAvatar;
//    private static RecyclerView recyclerView;
//    private static ArrayList<CoachesDataModel> data;
//    private static RecyclerView.Adapter adapter;
//    private MaterialCardView mSleepVisualizationCardView, mBreakFastCardview;
//    private LinearLayout mLinearLayoutDashboard, mLinearLayoutTextViewMAE, mLinearLayoutForMorning,
//            mLinearLayoutForAfternoon, mLinearLayoutForNight;
//    private MaterialTextView mTextViewMorning, mTextViewAfternoon, mTextViewNight, mTextViewSleepVisualization, mTe;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//        frameLayout=findViewById(R.id.navigation_container);
//
//        //Avatar id
//        mAvatar=findViewById(R.id.Avatar);
//        //Linear id's
//        mLinearLayoutDashboard = findViewById(R.id.linearlayoutMain);
//        mLinearLayoutTextViewMAE = findViewById(R.id.linearLayoutForMAEView);
//        mLinearLayoutForMorning = findViewById(R.id.linearLayoutForMorning);
//        mLinearLayoutForAfternoon = findViewById(R.id.linearLayoutForAfternoon);
//        mLinearLayoutForNight = findViewById(R.id.linearLayoutForNight);
//        //CardView's Id
//        mSleepVisualizationCardView = findViewById(R.id.sleepVisualCardView);
//        mBreakFastCardview=findViewById(R.id.breakfastCardView);
//        //TextView's Id's
//        mTextViewMorning = findViewById(R.id.textViewMorning);
//        mTextViewAfternoon = findViewById(R.id.textViewAfternoon);
//        mTextViewNight = findViewById(R.id.textViewNight);
//        myOnClickListener = new MyItemOnClickListener(this);
//
//        //listener for LinearLayout
//        mLinearLayoutForMorning.setOnClickListener(this);
//        mLinearLayoutForAfternoon.setOnClickListener(this);
//        mLinearLayoutForNight.setOnClickListener(this);
//
//        mBreakFastCardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mLinearLayoutDashboard.setVisibility(View.GONE);
//                Fragment fragment = new ShoppingFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.navigation_container, fragment);
//                transaction.commit();
//
//            }
//        });
//
//        //Avatar listener
//        mAvatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mLinearLayoutDashboard.setVisibility(View.GONE);
//                Fragment fragment = new UserProfileFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragmentContainerProfile, fragment);
//                transaction.commit();
//
//            }
//        });
//
//
//
////        //bottom Navigation
////        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationView);
////        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
////        for (int i = 0; i < menuView.getChildCount(); i++) {
////            final View iconView = menuView.getChildAt(2).findViewById(R.id.icon);
////            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
////            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
////            // set your height here
////            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
////            // set your width here
////            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
////            iconView.setLayoutParams(layoutParams);
////
////
////
////            navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
////                @Override
////                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////                    Fragment fragment;
////                    switch (item.getItemId()) {
////                        case R.id.navigation_plans_Home:
////                            //return true;
////                        case R.id.navigation_programs:
////                            fragment = new ProgramsFragment();
////                            loadFragment(fragment);
////                            return true;
////                        case R.id.navigation_explore:
////                            fragment = new ExploreFragment();
////                            loadFragment(fragment);
////                            return true;
////                        case R.id.navigation_shopping:
////                            fragment = new ShoppingFragment();
////                            loadFragment(fragment);
////                            return true;
////                        case R.id.navigation_setting:
////                            fragment = new SettingFragment();
////                            loadFragment(fragment);
////                            return true;
////                    }
////                    return false;
////                }
////            });
////        }
//
//
//
//
//        //   recyclerview for first horizontal cardView
//        recyclerView = findViewById(R.id.recyclerviewForCoaches);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
//        //   Populate data of first horizontal cardview
//        data = new ArrayList<CoachesDataModel>();
//        for (int i = 0; i < CoachesData.nameArray.length; i++) {
//            data.add(new CoachesDataModel(
//                    CoachesData.nameArray[i],
//                    CoachesData.imageArray[i],
//                    CoachesData.id_[i]
//
//            ));
//        }
//
//        adapter = new CoachesAdapter(data);
//        recyclerView.setAdapter(adapter);
//    }
//
////    private void loadFragment(Fragment fragment) {
////        // load fragment
////        mLinearLayoutDashboard.setVisibility(View.GONE);
////        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.navigation_container, fragment);
////        transaction.addToBackStack(null);
////        transaction.commit();
////    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.linearLayoutForMorning) {
//            String textViewMorning = mTextViewMorning.getText().toString();
//            mLinearLayoutDashboard.setVisibility(View.GONE);
//            Fragment fragment = new SleepVisualizationFragment();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//            Bundle bundle = new Bundle();
//            bundle.putString("Morning", textViewMorning); //key and value
//            fragment.setArguments(bundle);
//            transaction.replace(R.id.fragmentContainer1, fragment);
//            transaction.commit();
//
//
//        }
//        if (v.getId() == R.id.linearLayoutForAfternoon) {
//            String textViewAfternoon = mTextViewAfternoon.getText().toString();
//            mLinearLayoutDashboard.setVisibility(View.GONE);
//            Fragment fragment = new SleepVisualizationFragment();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//            Bundle bundle = new Bundle();
//            bundle.putString("Morning", textViewAfternoon); //key and value
//            fragment.setArguments(bundle);
//            transaction.replace(R.id.fragmentContainer1, fragment);
//            transaction.commit();
//
//
//        }
//        if (v.getId() == R.id.linearLayoutForNight) {
//            String textViewNight = mTextViewNight.getText().toString();
//            mLinearLayoutDashboard.setVisibility(View.GONE);
//            Fragment fragment = new SleepVisualizationFragment();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//            Bundle bundle = new Bundle();
//            bundle.putString("Morning", textViewNight); //key and value
//            fragment.setArguments(bundle);
//            transaction.replace(R.id.fragmentContainer1, fragment);
//            transaction.commit();
//
//
//        }
//
//    }
//
//
//
//
//
//    class MyItemOnClickListener implements View.OnClickListener {
//        private final Context context;
//
//
//        MyItemOnClickListener(Context context) {
//            this.context = context;
//        }
//
//
//        @Override
//        public void onClick(View v) {
//            RecyclerView.ViewHolder viewHolder = recyclerView.findContainingViewHolder(v);
//            MaterialTextView textViewName
//                    = (MaterialTextView) viewHolder.itemView.findViewById(R.id.coachesName);
//            String name = textViewName.getText().toString();
//            mLinearLayoutDashboard.setVisibility(View.GONE);
//           // Fragment fragment = new CoachesExercisesFragment();
//
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            Bundle bundle = new Bundle();
//            bundle.putString("CoachName", name); //key and value
//            //bundle.putExtra("Image",  dataSet.get(viewHolder.getAdapterPosition()).getProfile_img());
//            //set Fragmentclass Arguments
//            fragment.setArguments(bundle);
//            fragmentTransaction.replace(R.id.fragmentContainer0, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//
//        }
//    }
//}