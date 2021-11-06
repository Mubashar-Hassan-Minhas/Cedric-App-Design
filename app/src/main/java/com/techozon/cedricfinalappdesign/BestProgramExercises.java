package com.techozon.cedricfinalappdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.techozon.cedricfinalappdesign.Adapters.BestProgramAdapter;
import com.techozon.cedricfinalappdesign.Adapters.BestProgramWorkoutAdapter;
import com.techozon.cedricfinalappdesign.Model.BestProgramModel;
import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("ALL")
public class BestProgramExercises extends Fragment {

    public static View.OnClickListener myOnClickListener;
    private ImageButton backArrow;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView bestProgramWarmuprecyclerView;
    private static RecyclerView bestProgramWorkoutrecyclerView;
    private MaterialTextView programTitle;

    BestProgramAdapter myAdapter;
    BestProgramWorkoutAdapter bestProgramWorkoutAdapter;
    BestProgramModel bestProgramModel = new BestProgramModel();

    //    private DatabaseReference mDatabaseRef;
    String day;
    int week, programId;


    public BestProgramExercises() {
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
        return inflater.inflate(R.layout.fragment_best_program_exercises, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        programTitle = view.findViewById(R.id.textViewProgramNameProgress);
        programTitle.setText(SharedData.programName);
        programId = SharedData.bestProgramId;


        //myOnClickListener = new MyItemOnClickListener(this);
        day = getArguments().getString("selectedDay");
        week = getArguments().getInt("selectedWeek");
        System.out.println(day + "day selected");
        System.out.println(week + "week selected");
        System.out.println(programId + "e PID");

        backArrow = view.findViewById(R.id.backArrow);
        //for warmup Exercises
        bestProgramWarmuprecyclerView = view.findViewById(R.id.recyclerviewBestProgramWarmUp);
        bestProgramWarmuprecyclerView.setHasFixedSize(true);


        //for workout Exercises
        bestProgramWorkoutrecyclerView = view.findViewById(R.id.recyclerviewBestProgramWorkout);
        bestProgramWorkoutrecyclerView.setHasFixedSize(true);


        bestProgramExerciseDataPost();

        // mUploads = new ArrayList<>();
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Best Programs");
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    String uid = postSnapshot.getKey();
//                    System.out.println(uid + "=====om======[[[[]]]]]");
//
//                    for (DataSnapshot subSnapshot : postSnapshot.getChildren()) {
//                        String vid = subSnapshot.getKey();
//                        System.out.println(vid + "=====8m======[[[[]]]]]");
//                        if (vid.equals("Massive Upper Body")) {
//
//                            for (DataSnapshot ssubSnapshot : subSnapshot.getChildren()) {
//                                String cid = subSnapshot.getKey();
//                                System.out.println(cid + "=====cm======[[[[]]]]]");
//                                for (DataSnapshot subChildSnapshot : ssubSnapshot.getChildren()) {
//                                    String ccid = subSnapshot.getKey();
//                                    if (ccid.equals("dayOne")) {
//
//                                        WarmupDataModel upload = subChildSnapshot.getValue(WarmupDataModel.class);
//                                        dayVideoArrayList.add(upload);
//                                    }
//                                    myAdapter = new BestProgramAdapter(getContext(), dayVideoArrayList);
//                                    recyclerView.setAdapter(myAdapter);
//                                    dayVideoArrayList = new ArrayList<>();
//
//                                }
//
//                            }
//                        }
//                    }
//
//                }
//
//
////                    else {
////                        Toast.makeText(getContext(), "No Videos Available", Toast.LENGTH_LONG).show();
////                        System.out.println("not working...");
////                    }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fragment = new ProgressFragment();
//                //replacing the fragment
//                if (fragment != null) {
//                    FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.progress_fragment, fragment);
//                    ft.addToBackStack(null);
//                    ft.commit();
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
    }

    private void bestProgramExerciseDataPost() {
        Call<BestProgramModel> call = ApiClient.getService().programProgressDataPost(programId, week, day);

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<BestProgramModel>() {
            @Override
            public void onResponse(Call<BestProgramModel> call, Response<BestProgramModel> response) {
                if (response.isSuccessful()) {

                    bestProgramModel = response.body();


                }
                myAdapter = new BestProgramAdapter(getContext(), bestProgramModel);
                bestProgramWarmuprecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                bestProgramWarmuprecyclerView.setAdapter(myAdapter);

                bestProgramWorkoutAdapter = new BestProgramWorkoutAdapter(getContext(), bestProgramModel);
                bestProgramWorkoutrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                bestProgramWorkoutrecyclerView.setAdapter(bestProgramWorkoutAdapter);

            }

            @Override
            public void onFailure(Call<BestProgramModel> call, Throwable t) {
                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(getContext(), "Fail to get  Best Program  data", Toast.LENGTH_SHORT).show();
            }
        });

    }


}