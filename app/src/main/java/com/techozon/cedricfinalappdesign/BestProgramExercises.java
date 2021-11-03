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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techozon.cedricfinalappdesign.Adapters.BestProgramAdapter;
import com.techozon.cedricfinalappdesign.Model.WarmupDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


@SuppressWarnings("ALL")
public class BestProgramExercises extends Fragment {

    public static View.OnClickListener myOnClickListener;
    private ImageButton backArrow;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    BestProgramAdapter myAdapter;
    public static ArrayList<WarmupDataModel> dayVideoArrayList= new ArrayList<>();

    private DatabaseReference mDatabaseRef;


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
        //   recyclerview for first Vertical cardView

        //myOnClickListener = new MyItemOnClickListener(this);

        backArrow = view.findViewById(R.id.backArrow);
        recyclerView =view.findViewById(R.id.recyclerviewBestWarmUp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Best Programs");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String uid = postSnapshot.getKey();
                    System.out.println(uid + "=====om======[[[[]]]]]");

                    for (DataSnapshot subSnapshot : postSnapshot.getChildren()) {
                        String vid = subSnapshot.getKey();
                        System.out.println(vid + "=====8m======[[[[]]]]]");
                        if (vid.equals("Massive Upper Body")) {

                            for (DataSnapshot ssubSnapshot : subSnapshot.getChildren()) {
                                String cid = subSnapshot.getKey();
                                System.out.println(cid + "=====cm======[[[[]]]]]");
                                for (DataSnapshot subChildSnapshot : ssubSnapshot.getChildren()) {
                                    String ccid = subSnapshot.getKey();
                                    if (ccid.equals("dayOne")) {

                                        WarmupDataModel upload = subChildSnapshot.getValue(WarmupDataModel.class);
                                        dayVideoArrayList.add(upload);
                                    }
                                    myAdapter = new BestProgramAdapter(getContext(), dayVideoArrayList);
                                    recyclerView.setAdapter(myAdapter);
                                    dayVideoArrayList = new ArrayList<>();

                                }

                            }
                        }
                    }

                }


//                    else {
//                        Toast.makeText(getContext(), "No Videos Available", Toast.LENGTH_LONG).show();
//                        System.out.println("not working...");
//                    }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProgressFragment();
                //replacing the fragment
                if (fragment != null) {
                    FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.navigation_container, fragment);
                    ft.addToBackStack("ProgressFragment");
                    ft.commit();
                }
            }

        });
    }



}