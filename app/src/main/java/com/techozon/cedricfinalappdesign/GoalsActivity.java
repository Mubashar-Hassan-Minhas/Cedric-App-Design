package com.techozon.cedricfinalappdesign;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class GoalsActivity extends AppCompatActivity {
    private MaterialButton mGoalsButton;
    private MaterialTextView mLoseWgt, mBuildMuscle, mFittedToned;
    private Boolean mStateChanged = true;
    String userLevel,userGoals, height, weight, gender, age;


//    private FirebaseAuth mAuth;
//    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        //getIntent
        Intent intent = getIntent();
        userLevel = intent.getStringExtra("Level");
        height = intent.getStringExtra("userHeight");
        weight = intent.getStringExtra("userWeight");
        age = intent.getStringExtra("Age");
        gender = intent.getStringExtra("gender");

        System.out.println(userLevel);
        mLoseWgt = findViewById(R.id.textViewLoseWeightSubtitle);
        mBuildMuscle = findViewById(R.id.textViewBuildMusclesSubtitle);
        mFittedToned = findViewById(R.id.textViewFittedAndTonedSubtitle);
        mGoalsButton = findViewById(R.id.btnGoalsNext);

//        mAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Users Data");


        //listener for textViews
        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.textViewLoseWeightSubtitle) {
                    mLoseWgt.setTextColor(Color.BLACK);
                    v.setBackgroundResource(R.drawable.textview_after_click);

                    mFittedToned.setTextColor(Color.WHITE);
                    mFittedToned.setBackgroundResource(R.drawable.textview_outline_style);
                    mBuildMuscle.setTextColor(Color.WHITE);
                    mBuildMuscle.setBackgroundResource(R.drawable.textview_outline_style);

                    mGoalsButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            userGoals = mLoseWgt.getText().toString();
                            saveDataInDatabase();
                        }
                    });

                }
                if (v.getId() == R.id.textViewBuildMusclesSubtitle) {
                    mBuildMuscle.setTextColor(Color.BLACK);
                    v.setBackgroundResource(R.drawable.textview_after_click);


                    mLoseWgt.setTextColor(Color.WHITE);
                    mLoseWgt.setBackgroundResource(R.drawable.textview_outline_style);
                    mFittedToned.setTextColor(Color.WHITE);
                    mFittedToned.setBackgroundResource(R.drawable.textview_outline_style);


                    mGoalsButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            userGoals = mBuildMuscle.getText().toString();
                            saveDataInDatabase();

                        }
                    });
                }
                if (v.getId() == R.id.textViewFittedAndTonedSubtitle) {
                    mFittedToned.setTextColor(Color.BLACK);
                    v.setBackgroundResource(R.drawable.textview_after_click);

                    mLoseWgt.setTextColor(Color.WHITE);
                    mLoseWgt.setBackgroundResource(R.drawable.textview_outline_style);
                    mBuildMuscle.setTextColor(Color.WHITE);
                    mBuildMuscle.setBackgroundResource(R.drawable.textview_outline_style);

                    mGoalsButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            userGoals = mFittedToned.getText().toString();
                            saveDataInDatabase();
                        }
                    });
                }
            }

            private void saveDataInDatabase() {
                //HashMap<String, Object> userDetailsMap = new HashMap<>();
               // userDetailsMap.put("uid", Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
//                userDetailsMap.put("level", userLevel);
//                userDetailsMap.put("goals", userGoals);
//                databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userDetailsMap);
              //  Toast.makeText(getApplicationContext(), "Data added to database.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(com.techozon.cedricfinalappdesign.GoalsActivity.this,
                        PaymentCategory.class);

                intent.putExtra("level", userLevel);
                intent.putExtra("goals", userGoals);
                intent.putExtra("userHeight", height);
                intent.putExtra("userWeight", weight);
                intent.putExtra("Age", age);
                intent.putExtra("gender", gender);
                startActivity(intent);


            }
        };
        mLoseWgt.setOnClickListener(listener);
        mBuildMuscle.setOnClickListener(listener);
        mFittedToned.setOnClickListener(listener);
    }

}


//                if (v.getId() == R.id.textViewLoseWeightSubtitle) {
//
//                    if (mStateChanged) {
//                        v.setBackgroundResource(R.drawable.textview_after_click);
//                        // mLoseWgt.setTextColor(Color.WHITE);
//                        mStateChanged = false;
//                        mFittedToned.setClickable(false);
//                        mBuildMuscle.setClickable(false);
//
//                    } else {
//                        v.setBackgroundResource(R.drawable.textview_outline_style);
//                        //mLoseWgt.setTextColor(Color.parseColor("#363C60"));
//                        mStateChanged = true;
//                        mFittedToned.setClickable(true);
//                        mBuildMuscle.setClickable(true);
//                    }
//                }
//                if (v.getId() == R.id.textViewBuildMusclesSubtitle) {
//                    if (mStateChanged) {
//                        v.setBackgroundResource(R.drawable.textview_after_click);
//                        // mLoseWgt.setTextColor(Color.WHITE);
//                        mStateChanged = false;
//                        mLoseWgt.setClickable(false);
//                        mFittedToned.setClickable(false);
//
//                    } else {
//                        v.setBackgroundResource(R.drawable.textview_outline_style);
//                        //mLoseWgt.setTextColor(Color.parseColor("#363C60"));
//                        mStateChanged = true;
//                        mLoseWgt.setClickable(true);
//                        mFittedToned.setClickable(true);
//                    }
//                }
//                if (v.getId() == R.id.textViewFittedAndTonedSubtitle) {
//                    if (mStateChanged) {
//                        v.setBackgroundResource(R.drawable.textview_after_click);
//                        // mLoseWgt.setTextColor(Color.WHITE);
//                        mStateChanged = false;
//                        mLoseWgt.setClickable(false);
//                        mBuildMuscle.setClickable(false);
//
//                    } else {
//                        v.setBackgroundResource(R.drawable.textview_outline_style);
//                        //mLoseWgt.setTextColor(Color.parseColor("#363C60"));
//                        mStateChanged = true;
//                        mLoseWgt.setClickable(true);
//                        mBuildMuscle.setClickable(true);
//                    }
//                }
//            }



