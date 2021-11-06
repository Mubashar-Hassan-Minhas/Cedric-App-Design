package com.techozon.cedricfinalappdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.techozon.cedricfinalappdesign.Adapters.ProgramsAdapter;
import com.techozon.cedricfinalappdesign.Model.ProgramsDataModel;
import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class ProgramsFragment extends Fragment {

    public static View.OnClickListener myOnClickListener;
    private static RecyclerView mProgramsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<ProgramsDataModel> programsList = new ArrayList<>();
    private static RecyclerView.Adapter programsAdapter;
    private ConstraintLayout mConstraintLayout;
    private ImageButton backArrow;
    private DatabaseReference mDatabaseReference;


    public ProgramsFragment() {
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
        return inflater.inflate(R.layout.fragment_programs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mConstraintLayout = view.findViewById(R.id.program_fragment);
        //myOnClickListener = new MyItemOnClickListener(this);

        backArrow = view.findViewById(R.id.backArrow);

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


        mProgramsRecyclerView = view.findViewById(R.id.recyclerViewPrograms);

        //   recyclerview for first horizontal cardView

        mProgramsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mProgramsRecyclerView.setLayoutManager(layoutManager);
       // programsList = new ArrayList<>();

       
        
        getAllPrograms();

//        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Best Programs");
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    // vid = postSnapshot.getKey();
////                    System.out.println(vid + "=====mm======[[[[]]]]]");
//
////                    for (DataSnapshot subSnapshot : postSnapshot.getChildren()) {
////                        System.out.println("=====innner loop======[[[[]]]]]");
//
//                    ProgramsDataModel upload = postSnapshot.getValue(ProgramsDataModel.class);
//                    System.out.println(postSnapshot + "pklpkpkpkppkkpkpp");
//                    programsList.add(upload);
//
//
//                }
//                adapter = new ProgramsAdapter(getContext(), programsList);
//                mProgramsRecyclerView.setAdapter(adapter);
//                programsList = new ArrayList<>();
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


    }

    private void getAllPrograms() {
        // on below line we are calling a method to get all the courses from API.
        Call<ArrayList<ProgramsDataModel>> call = ApiClient.getService().getAllPrograms();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<ArrayList<ProgramsDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProgramsDataModel>> call, Response<ArrayList<ProgramsDataModel>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {

                    // on successful we are hiding our progressbar.
                    // progressBar.setVisibility(View.GONE);

                    // below line is to add our data from api to our array list.
                    programsList = response.body();


                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < programsList.size(); i++) {

                        // below line is to set adapter to our recycler view.
                        programsAdapter = new ProgramsAdapter(getContext(),programsList);
                        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        mProgramsRecyclerView.setLayoutManager(layoutManager);
                        mProgramsRecyclerView.setAdapter(programsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProgramsDataModel>> call, Throwable t) {
                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(getContext(), "Fail to get programs data", Toast.LENGTH_SHORT).show();
            }
        });
        
    }


//    public class MyItemOnClickListener implements View.OnClickListener {
//        private final ProgramsFragment context;


//        MyItemOnClickListener(ProgramsFragment context) {
//            this.context = context;
//        }
//
//
//        @Override
//        public void onClick(View v) {
//
//
//            Fragment fragment = new ProgressFragment();
//            @SuppressLint("UseRequireInsteadOfGet") FragmentTransaction transaction = requireFragmentManager().beginTransaction();
//            transaction.replace(R.id.navigation_container, fragment);
//            transaction.commit();
//
//
//
//
//        }
//    }


}
