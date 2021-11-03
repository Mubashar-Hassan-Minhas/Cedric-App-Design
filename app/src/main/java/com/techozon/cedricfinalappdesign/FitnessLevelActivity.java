package com.techozon.cedricfinalappdesign;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

@SuppressWarnings("ALL")
public class FitnessLevelActivity extends AppCompatActivity {
    private MaterialTextView mTextViewBeginner, mTextViewIntermediate, mTextViewAdvance;
    private Boolean mStateChanged = true;
    private MaterialButton mFitnessLevelButton;
    String level, height, weight, gender, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_level);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mTextViewBeginner = findViewById(R.id.textViewBeginner);
        mTextViewIntermediate = findViewById(R.id.textViewIntermediate);
        mTextViewAdvance = findViewById(R.id.textViewAdvance);
        mFitnessLevelButton = findViewById(R.id.btnFitnessNext);

        Intent intent = getIntent();
        height = intent.getStringExtra("userHeight");
        weight = intent.getStringExtra("userWeight");
        age = intent.getStringExtra("Age");
        gender = intent.getStringExtra("gender");




//Listener for button
//        mFitnessLevelButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(FitnessLevelActivity.this,"kuch ni hua",Toast.LENGTH_LONG).show();
//
//                setLevelValidation();
////                Intent intent = new Intent(com.example.cedricfinalappdesign.FitnessLevelActivity.this, com.example.cedricfinalappdesign.GoalsActivity.class);
////                startActivity(intent);
//            }
//
//
//        });

        //listener for textViews
        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.textViewBeginner) {
                    if (mStateChanged) {
                        mTextViewBeginner.setTextColor(Color.BLACK);
                        v.setBackgroundResource(R.drawable.textview_after_click);

                        mTextViewAdvance.setTextColor(Color.WHITE);
                        mTextViewAdvance.setBackgroundResource(R.drawable.textview_outline_style);
                        mTextViewIntermediate.setTextColor(Color.WHITE);
                        mTextViewIntermediate.setBackgroundResource(R.drawable.textview_outline_style);
                        mFitnessLevelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                level = mTextViewBeginner.getText().toString();
                                gotoNextActivity();


//                                Intent intent = new Intent(com.example.cedricfinalappdesign.FitnessLevelActivity.this,
//                                        com.example.cedricfinalappdesign.GoalsActivity.class);
//                                intent.putExtra("Level", mTextViewBeginner.getText().toString());
//                                startActivity(intent);
                            }
                        });
                    }
                }
                if (v.getId() == R.id.textViewIntermediate) {
                    if (mStateChanged) {

                        mTextViewIntermediate.setTextColor(Color.BLACK);
                        v.setBackgroundResource(R.drawable.textview_after_click);

                        mTextViewBeginner.setTextColor(Color.WHITE);
                        mTextViewBeginner.setBackgroundResource(R.drawable.textview_outline_style);
                        mTextViewAdvance.setTextColor(Color.WHITE);
                        mTextViewAdvance.setBackgroundResource(R.drawable.textview_outline_style);
                        mFitnessLevelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                level = mTextViewIntermediate.getText().toString();
                                gotoNextActivity();

//                                Intent intent = new Intent(com.example.cedricfinalappdesign.FitnessLevelActivity.this,
//                                        com.example.cedricfinalappdesign.GoalsActivity.class);
//                                intent.putExtra("Level", mTextViewIntermediate.getText().toString());
//                                startActivity(intent);
                            }
                        });

                    }
                }
                if (v.getId() == R.id.textViewAdvance) {
                    if (mStateChanged) {
                        mTextViewAdvance.setTextColor(Color.BLACK);
                        v.setBackgroundResource(R.drawable.textview_after_click);

                        mTextViewBeginner.setTextColor(Color.WHITE);
                        mTextViewBeginner.setBackgroundResource(R.drawable.textview_outline_style);
                        mTextViewIntermediate.setTextColor(Color.WHITE);
                        mTextViewIntermediate.setBackgroundResource(R.drawable.textview_outline_style);
                        mFitnessLevelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                level = mTextViewAdvance.getText().toString();
                                gotoNextActivity();

//                                Intent intent = new Intent(com.example.cedricfinalappdesign.FitnessLevelActivity.this,
//                                        com.example.cedricfinalappdesign.GoalsActivity.class);
//
//                                intent.putExtra("Level", mTextViewAdvance.getText().toString());
//
//                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        };
        mTextViewBeginner.setOnClickListener(listener);
        mTextViewIntermediate.setOnClickListener(listener);
        mTextViewAdvance.setOnClickListener(listener);

    }

    private void gotoNextActivity() {

        Intent intent = new Intent(com.techozon.cedricfinalappdesign.FitnessLevelActivity.this,
                com.techozon.cedricfinalappdesign.GoalsActivity.class);
        intent.putExtra("Level", level);
        intent.putExtra("userHeight", height);
        intent.putExtra("userWeight", weight);
        intent.putExtra("Age", age);
        intent.putExtra("gender", gender);
        startActivity(intent);


    }
}