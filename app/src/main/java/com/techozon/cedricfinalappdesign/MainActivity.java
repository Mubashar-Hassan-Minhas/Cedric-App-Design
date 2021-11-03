package com.techozon.cedricfinalappdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private MaterialButton mStarFreeTrialButton;
    private MaterialButton mLoginActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set Full Screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //set Button id's
        mStarFreeTrialButton=findViewById(R.id.btnFreeTrials);
        mLoginActivityButton=findViewById(R.id.btnLoginMainActivity);

        mStarFreeTrialButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
               // mStarFreeTrialButton.setTextColor(R.color.black);
                startActivity(intent);

            }
        });

// Listener for login Button
        mLoginActivityButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
               // mLoginActivityButton.setTextColor(R.color.black);
                startActivity(intent);
            }
        });
    }
}