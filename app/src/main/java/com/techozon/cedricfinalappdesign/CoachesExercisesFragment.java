package com.techozon.cedricfinalappdesign;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.techozon.cedricfinalappdesign.Adapters.MyAdapter;
import com.techozon.cedricfinalappdesign.Adapters.WorkoutAdapter;
import com.techozon.cedricfinalappdesign.Model.CoachesDataModel;
import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("ALL")
public class CoachesExercisesFragment extends Fragment {

    public static View.OnClickListener myOnClickListener;
    private MaterialTextView materialTextViewCoachName, materialTextViewCoachRole, materialTextViewCoachDescription;
    private ImageButton backArrow;
    private AppCompatImageView mCoachImage;
    String name, role, description;

    private RecyclerView.LayoutManager layoutManager;

    RecyclerView myRecyclerView, workoutRecyclerview;
    MyAdapter myAdapter;
    WorkoutAdapter workoutAdapter;
    CoachesDataModel coachData;
    public static ArrayList<CoachesDataModel> fileArrayList = new ArrayList<CoachesDataModel>();
    //private boolean bolean_permission;
    // private DatabaseReference mDatabaseRef;

    private Context context;
    //    FirebaseDatabase firebaseDatabase;
//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;
//    DatabaseReference databaseReference;
    int uid;
    String vid;
    // WarmUpAdapter adapter;


    public CoachesExercisesFragment() {
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
        View v = inflater.inflate(R.layout.fragment_coaches_exercises, container, false);

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //FirebaseApp.initializeApp(context);
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        backArrow = view.findViewById(R.id.backArrow);
        materialTextViewCoachName = view.findViewById(R.id.coachName);
        materialTextViewCoachDescription = view.findViewById(R.id.textViewDescription);
        materialTextViewCoachRole = view.findViewById(R.id.textViewCoachRole);
        mCoachImage = view.findViewById(R.id.coachDp);
        coachData = getArguments().getParcelable("coach");
        System.out.println(coachData.name);
        name = getArguments().getString("CoachName");
        role = getArguments().getString("role");
        description = getArguments().getString("description");

       // uid = getArguments().getInt("warmupId");
        //set data
        materialTextViewCoachName.setText(name);
        materialTextViewCoachRole.setText(role);
        materialTextViewCoachDescription.setText(description);
        //mCoachImage.setImageResource(Integer.parseInt(String.valueOf((getArguments().getInt("profileImage")))));
        try {
            String img = getArguments().getString("profileImage");
            Glide.with(getContext()).load(img).into(mCoachImage);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("not working...");

        }
        //   myOnClickListener = new MyItemOnClickListener(this);

//      recyclerview for warmup
        myRecyclerView = view.findViewById(R.id.recyclerviewWarmUp);
        myRecyclerView.setHasFixedSize(true);

        workoutRecyclerview = view.findViewById(R.id.recyclerviewWorkout);
        workoutRecyclerview.setHasFixedSize(true);
        getWarmUpData();

        //workoutRecyclerview=view.findViewById(R.id.recyclerviewWorkout);


//        if( uid.equals("c1"))
//        {
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Workouts Videos");
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                   vid = postSnapshot.getKey();
//                    System.out.println(vid + "=====mm======[[[[]]]]]");
//                    if((uid.equals(vid))){
//                    for (DataSnapshot subSnapshot : postSnapshot.getChildren()) {
//                        System.out.println("=====innner loop======[[[[]]]]]");
//
//                        VideoModel upload = subSnapshot.getValue(VideoModel.class);
//                        fileArrayList.add(upload);
//
//                    }
//                        myAdapter = new MyAdapter(getContext(), fileArrayList);
//                        myRecyclerView.setAdapter(myAdapter);
//                        fileArrayList = new ArrayList<>();
//
//
//                    }
//                    else {
//                        Toast.makeText(getContext(), "No Videos Available", Toast.LENGTH_LONG).show();
//                        System.out.println("not working...");
//                       }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
        //}
//        else {
//            System.out.println("done dona done nothing done");
//        }


        //   Populate data
//        data = new ArrayList<WarmupDataModel>();
//        for (int i = 0; i < WarmUpDataArrays.exerciseNameArray.length; i++) {
//            data.add(new WarmupDataModel(
//                    WarmUpDataArrays.exerciseNameArray[i],
//                    WarmUpDataArrays.exerciseTimerArray[i],
//                    WarmUpDataArrays.imageArray[i],
//                    WarmUpDataArrays.id_[i]
//
//            ));
//        }

        // adapter = new WarmUpAdapter(data);
        // recyclerView.setAdapter(adapter);


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DashboardFragment();
                //replacing the fragment
                if (fragment != null) {
                    FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.navigation_container, fragment);
                    ft.addToBackStack("CoachesExercisesFragment");
                    ft.commit();

                }
            }


        });
    }

    private void getWarmUpData() {


        myAdapter = new MyAdapter(getContext(), coachData);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(myAdapter);


        workoutAdapter = new WorkoutAdapter(getContext(), coachData);
        workoutRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        workoutRecyclerview.setAdapter(workoutAdapter);



//        Call<ArrayList<CoachesDataModel>> call = ApiClient.getService().getAllCoachesData();
//
//        // on below line we are calling method to enqueue and calling
//        // all the data from array list.
//        call.enqueue(new Callback<ArrayList<CoachesDataModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<CoachesDataModel>> call, Response<ArrayList<CoachesDataModel>> response) {
//                // inside on response method we are checking
//                // if the response is success or not.
//                if (response.isSuccessful()) {
//
//
//                    fileArrayList = response.body();
//                    System.out.println(fileArrayList + "sdsddsdd");
//
//
//                    // below line we are running a loop to add data to our adapter class.
//                    for (int i = 0; i < fileArrayList.size(); i++) {
//
//
//                        myAdapter = new MyAdapter(getContext(), fileArrayList);
//                        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                        myRecyclerView.setAdapter(myAdapter);
//
//
//                        workoutAdapter = new WorkoutAdapter(getContext(), fileArrayList);
//                        workoutRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//                        workoutRecyclerview.setAdapter(workoutAdapter);
//
//
//                    }
////                        for (VideoModel model:fileArrayList)
////                              {
////                                  fileArrayList.add(model);
////
////
////                              }
//
//
//
//
//
//            }
//        }
//
//        @Override
//        public void onFailure (Call < ArrayList < CoachesDataModel >> call, Throwable t){
//            // in the method of on failure we are displaying a
//            // toast message for fail to get data.
//            Toast.makeText(getContext(), "Fail to get  Coaches data", Toast.LENGTH_SHORT).show();
//        }
//    });







}


//    class MyItemOnClickListener implements View.OnClickListener {
//
//         private final CoachesExercisesFragment context;
//        public MyItemOnClickListener(CoachesExercisesFragment context) {
//
//            this.context = context;
//        }
//
//        @Override
//        public void onClick(View v) {
//            RecyclerView.ViewHolder viewHolder = myRecyclerView.findContainingViewHolder(v);
//            MaterialTextView textViewExerciseName
//                    = (MaterialTextView) viewHolder.itemView.findViewById(R.id.textViewWarmUP);
//            ImageView imageExerciseFile
//                    = (ImageView) viewHolder.itemView.findViewById(R.id.imageWarmUp);
//
//            String exercise = textViewExerciseName.getText().toString();
//           // mLinearLayoutDashboard.setVisibility(View.GONE);
//            Fragment fragment = new ExerciseDetailsFragment();
//            @SuppressLint("UseRequireInsteadOfGet") FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
//            Bundle bundle = new Bundle();
//            bundle.putString("position",uploadCurrent.getUrl());
//            bundle.putString("exercise", exercise); //key and value
//            fragment.setArguments(bundle);
//            fragmentTransaction.replace(R.id.fragment_coaches_exercises, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//
//        }
//    }


}



