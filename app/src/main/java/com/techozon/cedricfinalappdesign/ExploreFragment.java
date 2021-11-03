package com.techozon.cedricfinalappdesign;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.techozon.cedricfinalappdesign.Adapters.ExploreBodySpecificAdapter;
import com.techozon.cedricfinalappdesign.Adapters.ExploreWorkoutAdapter;
import com.techozon.cedricfinalappdesign.Model.ExploreWorkoutDataModel;

import java.util.ArrayList;


public class ExploreFragment extends Fragment {
    private static RecyclerView mExploresRecyclerView;
    private static RecyclerView mExploreBodySpecificRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager1;
    private static ArrayList<ExploreWorkoutDataModel> data;
    private static ArrayList<ExploreBodySpecificDataModel> data1;
    private static RecyclerView.Adapter exploreAdapter;
    private static RecyclerView.Adapter exploreBodySpecificAdapter;
    private ImageButton backArrow;



    public ExploreFragment() {
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
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mExploresRecyclerView=view.findViewById(R.id.recyclerviewExplore);
        mExploreBodySpecificRecyclerView=view.findViewById(R.id.recyclerviewExploreBodySpecific);
        backArrow= view.findViewById(R.id.backArrow);

        //   recyclerview for first horizontal cardView

        mExploresRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mExploresRecyclerView.setLayoutManager(layoutManager);

        //   Populate data of first horizontal cardview
        data = new ArrayList<ExploreWorkoutDataModel>();
        for (int i = 0; i < ExploreWorkoutData.mainWorkoutArray.length; i++) {
            data.add(new ExploreWorkoutDataModel(
                    ExploreWorkoutData.mainWorkoutArray[i],
                    ExploreWorkoutData.numberOfWorkoutArray[i],
                    ExploreWorkoutData.imageMainWorkoutArray[i],
                    ExploreWorkoutData.imageWorkout1Array[i],
                    ExploreWorkoutData.imageWorkout2Array[i],
                    ExploreWorkoutData.imageWorkout3Array[i],
                    ExploreWorkoutData.workout1NameArray[i],
                    ExploreWorkoutData.workout2NameArray[i],
                    ExploreWorkoutData.workout3NameArray[i],
                    ExploreWorkoutData.id_[i]

            ));
        }

        exploreAdapter = new ExploreWorkoutAdapter(data);
        mExploresRecyclerView.setAdapter(exploreAdapter);



        //   recyclerview for body specific cardView

        mExploreBodySpecificRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mExploreBodySpecificRecyclerView.setLayoutManager(layoutManager);

        //   Populate data of first horizontal cardview
        data1 = new ArrayList<ExploreBodySpecificDataModel>();
        for (int i = 0; i < ExploreBodySpecificData.imageBodySpecificArray.length; i++) {
            data1.add(new ExploreBodySpecificDataModel(
                    ExploreBodySpecificData.imageBodySpecificArray[i],
                    ExploreBodySpecificData.id_[i]

            ));
        }
        exploreBodySpecificAdapter = new ExploreBodySpecificAdapter(data1);
        mExploreBodySpecificRecyclerView.setAdapter(exploreBodySpecificAdapter);


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