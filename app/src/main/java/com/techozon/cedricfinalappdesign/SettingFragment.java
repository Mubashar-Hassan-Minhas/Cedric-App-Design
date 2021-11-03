package com.techozon.cedricfinalappdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SettingFragment extends Fragment implements View.OnClickListener {
    private ImageButton backArrow;
    MaterialTextView mTextViewLogout,mProfileSetting;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        backArrow = view.findViewById(R.id.backArrow);
        mTextViewLogout = view.findViewById(R.id.textViewLogout);
        mProfileSetting=view.findViewById(R.id.textViewProfileSettings);
        backArrow.setOnClickListener(this);
        mTextViewLogout.setOnClickListener(this);


        mProfileSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new UpdateProfileFragment();
                FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.navigation_container, fragment);
                ft.addToBackStack("UpdateProfileFragment");
                ft.commit();

            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backArrow) {
            Fragment fragment = new DashboardFragment();
            //replacing the fragment
            if (fragment != null) {
                FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.navigation_container, fragment);
                ft.addToBackStack("DashboardFragment");
                ft.commit();

            }
        }
        if (v.getId() == R.id.textViewLogout) {
           // SharedData.id.signOut();
            Intent intent = new Intent(getActivity(), SignupActivity.class);
            startActivity(intent);
        }


    }
}