package com.techozon.cedricfinalappdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("ALL")
public class Bottom_Navigation_Bar extends AppCompatActivity {
    FrameLayout frameLayout;
    View iconView;
    // Session Manager Class
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom__navigation__bar);

//        session = new Session(getApplicationContext());
//        session.checkLogin();

        //bottom Navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_container,new DashboardFragment()).commit();

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
//            iconView = menuView.getChildAt(2).findViewById(R.id.icon);
//             ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(iconView.getLayoutParams());
//            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//            // set your height here
//            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
//            // set your width here
//            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
//            iconView.setLayoutParams(layoutParams);




            navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;
                    switch (item.getItemId()) {
                        case R.id.navigation_plans_Home:
                            fragment = new DashboardFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.navigation_programs:
                            fragment = new ProgramsFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.navigation_explore:
                            fragment = new ExploreFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.navigation_shopping:
                            fragment = new ShoppingFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.navigation_setting:
                            fragment = new SettingFragment();
                            loadFragment(fragment);
                            return true;
                    }
                    return false;
                }
            });
        }

    }
    private void loadFragment(Fragment fragment) {
        // load fragment
       // mLinearLayoutDashboard.setVisibility(View.GONE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navigation_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStackImmediate();
        }
        else{
            super.onBackPressed();
        }
    }
}