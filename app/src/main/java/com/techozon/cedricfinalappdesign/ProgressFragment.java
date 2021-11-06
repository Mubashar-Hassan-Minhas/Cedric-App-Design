package com.techozon.cedricfinalappdesign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techozon.cedricfinalappdesign.Adapters.ProgressAdapter;
import com.techozon.cedricfinalappdesign.Model.ProgressDataModel;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class ProgressFragment extends Fragment {
    private ProgressBar progressBar;
    private MaterialTextView mTextViewProgressbar;
    int i = 0;

    public static View.OnClickListener myOnClickListener;
    private static RecyclerView mProgressRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<ProgressDataModel> weekWiseList = new ArrayList<>();
    private static RecyclerView.Adapter adapter;
    private ConstraintLayout mConstraintLayout;
    private ImageButton backArrow;
    MaterialCardView mCardViewDay1, mCardViewDay2, mCardViewDay3, mCardViewDay4,
            mCardViewDay5, mCardViewDay6, mCardViewDay7, mCardViewDay8;
    //    private DatabaseReference mDatabaseReference;
    String programId;
    String totalWeeks;


    public ProgressFragment() {
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
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        programId = getArguments().getString("ProgramId");
        totalWeeks = getArguments().getString("noOfWeeks");
        System.out.println(totalWeeks +"weeks");
        System.out.println(programId +"ProgramId");

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        // myOnClickListener = new ProgressFragment.MyItemOnClickListener(this);
        mTextViewProgressbar = view.findViewById(R.id.text_view_progress);
        backArrow = view.findViewById(R.id.backArrow);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    mTextViewProgressbar.setText(i + "%");
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 200);


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProgramsFragment();
                //replacing the fragment
                if (fragment != null) {
                    FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.navigation_container, fragment);
                    ft.addToBackStack("ProgramsFragment");
                    ft.commit();
                }
            }
        });


        mProgressRecyclerView = view.findViewById(R.id.recyclerViewProgress);
        //   recyclerview for first vertical cardView
        mProgressRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mProgressRecyclerView.setLayoutManager(layoutManager);
        programProgress();


//        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Best Programs");
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
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
//
//                                ProgressDataModel upload = ssubSnapshot.getValue(ProgressDataModel.class);
//                                System.out.println(subSnapshot + "pklpkpkpkppkkpkpp");
//                                weekWiseList.add(upload);
//
//                            }
//                            adapter = new ProgressAdapter(getContext(), weekWiseList);
//                            mProgressRecyclerView.setAdapter(adapter);
//                            weekWiseList = new ArrayList<>();
//                        }
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
        //   Populate data of first horizontal cardview
        weekWiseList = new ArrayList<ProgressDataModel>();
        for (int i = 0; i <= Integer.valueOf(totalWeeks); i++) {
            weekWiseList.add(new ProgressDataModel(
                    ProgressData.WeekArray[i],
                    ProgressData.imageArray[i],
                    ProgressData.id_[i],
                    ProgressData.day1,
                    ProgressData.day2,
                    ProgressData.day3,
                    ProgressData.day4,
                    ProgressData.day5,
                    ProgressData.day6,
                    ProgressData.day7,
                    ProgressData.goForward

            ));
        }

        adapter = new ProgressAdapter(getContext(),weekWiseList);
        mProgressRecyclerView.setAdapter(adapter);

    }

    private void programProgress() {

    }

//    public class MyItemOnClickListener implements View.OnClickListener {
//        private final ProgressFragment context;
//        boolean isChecked = false;


//        MyItemOnClickListener(ProgressFragment context) {
//            this.context = context;
//        }
//
//
//        @SuppressLint("NonConstantResourceId")
//        @Override
//        public void onClick(View v) {
//            RecyclerView.ViewHolder viewHolder = mProgressRecyclerView.findContainingViewHolder(v);
//            MaterialTextView mTextViewDay1 = viewHolder.itemView.findViewById(R.id.textDay1);
//            MaterialTextView mTextViewDay2 = viewHolder.itemView.findViewById(R.id.textDay2);
//            MaterialTextView mTextViewDay3 = viewHolder.itemView.findViewById(R.id.textDay3);
//            MaterialTextView mTextViewDay4 = viewHolder.itemView.findViewById(R.id.textDay4);
//            MaterialTextView mTextViewDay5 = viewHolder.itemView.findViewById(R.id.textDay5);
//            MaterialTextView mTextViewDay6 = viewHolder.itemView.findViewById(R.id.textDay6);
//            MaterialTextView mTextViewDay7 = viewHolder.itemView.findViewById(R.id.textDay7);
//            ImageView mImageNextDay = viewHolder.itemView.findViewById(R.id.imgViewNext);
//            MaterialCardView mCardViewImgNext = viewHolder.itemView.findViewById(R.id.cardViewNext);
//            mTextViewDay1.setOnClickListener(this);
//            mTextViewDay2.setOnClickListener(this);
//            mTextViewDay3.setOnClickListener(this);
//            mTextViewDay4.setOnClickListener(this);
//            mTextViewDay5.setOnClickListener(this);
//            mTextViewDay6.setOnClickListener(this);
//            mTextViewDay7.setOnClickListener(this);
//            mImageNextDay.setOnClickListener(this);
//
//
//            if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay1) {
//                mCardViewImgNext.setCardBackgroundColor(Color.parseColor("#FFC153"));
//                mCardViewImgNext.setStrokeColor(Color.parseColor("#FFC153"));
//                mTextViewDay1.setBackgroundColor(Color.parseColor("#FFC153"));
//                mTextViewDay1.setBackgroundResource(textview_circular_shape_click);
//                startFragment();
//            }
//            if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay2) {
//                mCardViewImgNext.setCardBackgroundColor(Color.parseColor("#FFC153"));
//                mCardViewImgNext.setStrokeColor(Color.parseColor("#FFC153"));
//                mTextViewDay2.setBackgroundResource(textview_circular_shape_click);
//                startFragment();
//            }
//            if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay3) {
//                mCardViewImgNext.setCardBackgroundColor(Color.parseColor("#FFC153"));
//                mCardViewImgNext.setStrokeColor(Color.parseColor("#FFC153"));
//                mTextViewDay3.setBackgroundResource(textview_circular_shape_click);
//                startFragment();
//            }
//            if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay4) {
//                mCardViewImgNext.setCardBackgroundColor(Color.parseColor("#FFC153"));
//                mCardViewImgNext.setStrokeColor(Color.parseColor("#FFC153"));
//                mTextViewDay4.setBackgroundResource(textview_circular_shape_click);
//                startFragment();
//            }
//            if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay5) {
//                mCardViewImgNext.setCardBackgroundColor(Color.parseColor("#FFC153"));
//                mCardViewImgNext.setStrokeColor(Color.parseColor("#FFC153"));
//                mTextViewDay5.setBackgroundResource(textview_circular_shape_click);
//                startFragment();
//            }
//            if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay6) {
//                mCardViewImgNext.setCardBackgroundColor(Color.parseColor("#FFC153"));
//                mCardViewImgNext.setStrokeColor(Color.parseColor("#FFC153"));
//                mTextViewDay6.setBackgroundResource(textview_circular_shape_click);
//                startFragment();
//            }
//            if (v.getId() == R.id.imgViewNext || v.getId() == R.id.textDay7) {
//                mCardViewImgNext.setCardBackgroundColor(Color.parseColor("#FFC153"));
//                mCardViewImgNext.setStrokeColor(Color.parseColor("#FFC153"));
//                mTextViewDay7.setBackgroundResource(textview_circular_shape_click);
//                startFragment();
//            }
//
//
//        }
//
//        private void startFragment() {
//            Fragment fragment = new BestProgramExercises();
//            FragmentTransaction transaction = requireFragmentManager().beginTransaction();
//            transaction.replace(R.id.navigation_container, fragment);
//            transaction.commit();
//        }
//
//
//    }
}





