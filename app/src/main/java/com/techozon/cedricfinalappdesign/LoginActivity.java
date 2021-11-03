package com.techozon.cedricfinalappdesign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private MaterialButton mLoginButton, mGotoSigUpActivityButton;
    Animation slideUp, slideDown;
    private TextInputEditText mEditTextLoginUsernameOrEmail, mEditTextLoginPassword;
    private MaterialTextView mForgetPasswordTextView;
    boolean isEmailValid, isPasswordValid;
    LinearLayout linearLayout;
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    Boolean profileActivate;
//    private DatabaseReference mDatabaseReference;
//    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // for Analytics
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

// code for crashlytics
//        Button crashButton = new Button(this);
//        crashButton.setText("Test Crash");
//        crashButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                throw new RuntimeException("Test Crash"); // Force a crash
//            }
//        });
//
//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));

//        profileActivate=SharedData.profileActivation;
//
//        mAuth = FirebaseAuth.getInstance();
//        firebaseUser = mAuth.getCurrentUser();


        mEditTextLoginUsernameOrEmail = findViewById(R.id.editTextLoginUsernameOrEmail);
        mEditTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        mGotoSigUpActivityButton = findViewById(R.id.btnSigUpLoginActivity);
        mForgetPasswordTextView=findViewById(R.id.textViewForgetPassword);
        mLoginButton = findViewById(R.id.btnLogin);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_out);

        //slideDown= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);

        //getIntent
//        Intent intent=getIntent();
//        loginEmail=intent.getStringExtra("Email");
//        mEditTextLoginUsernameOrEmail.setText(loginEmail);


        linearLayout = findViewById(R.id.linearLayout2);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoginValidation();
            }
        });

        mForgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        //listener for Login button
        mGotoSigUpActivityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //setValidation();
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                linearLayout.startAnimation(slideUp);
                //over5ridePendingTransition(R.anim.slides_up_in,R.anim.slide_up_out);

            }
        });
    }

    private void setLoginValidation() {

        String emailLogin = Objects.requireNonNull(mEditTextLoginUsernameOrEmail.getText()).toString();
        String passwordLogin = Objects.requireNonNull(mEditTextLoginPassword.getText()).toString();
        // Check for a valid email address.


        if (emailLogin.isEmpty()) {
            mEditTextLoginUsernameOrEmail.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailLogin).matches()) {
            mEditTextLoginUsernameOrEmail.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        }

        // Check for a valid password.
        else if (passwordLogin.isEmpty()) {

            mEditTextLoginPassword.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (mEditTextLoginPassword.getText().length() < 4) {
            mEditTextLoginPassword.setError(getResources().getString(R.string.error_weak_password));
            isPasswordValid = false;
        } else if (mEditTextLoginPassword.getText().length() < 6) {
            mEditTextLoginPassword.setError(getResources().getString(R.string.error_medium_password));
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
            isEmailValid = true;

            loginUser(emailLogin, passwordLogin);




//            mAuth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    boolean isNewUser = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getAdditionalUserInfo()).isNewUser();
//
//                    if (task.isSuccessful()) {
//                        if (isNewUser) {
//                            startActivity(new Intent(LoginActivity.this, Bottom_Navigation_Bar.class));
//                        } else {
//                            Intent intent = new Intent(LoginActivity.this, InformationActivity.class);
//                            startActivity(intent);
//                            Toast.makeText(LoginActivity.this, "Sign In , Successful...", Toast.LENGTH_SHORT).show();
//
//                        }
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Error Occurred, while Signing In... ", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            });
        }


    }

    public void loginUser(String email, String password) {
        //loadingPB.setVisibility(View.VISIBLE);
        Call<LoginResponse> call = ApiClient.getService().loginDataPost(email, password);

        // on below line we are executing our method.
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                // this method is called when we get response from our api.


                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Successfully Logged in!",
                            Toast.LENGTH_SHORT).show();


                    LoginResponse loginResponse = response.body();
                    assert loginResponse != null;
                    SharedData.id=loginResponse.id;
                    SharedData.username=loginResponse.name;
                    SharedData.profileActivation=loginResponse.isProfileActivated;
                    SharedData.gender=loginResponse.gender;
                    SharedData.age=loginResponse.age;
                    SharedData.height=loginResponse.height;
                    SharedData.weight=loginResponse.weight;
                    SharedData.email=loginResponse.email;
                    SharedData.imageUrl=loginResponse.profileImage;
                    System.out.println("hello"+SharedData.profileActivation);
                    System.out.println("hello"+SharedData.id);

                    if (loginResponse.getEmail().isEmpty() || (!loginResponse.getEmail().matches(email))) {

                        Toast.makeText(getApplicationContext(), "Email is not exist!",
                                Toast.LENGTH_SHORT).show();


                    }
                    if (SharedData.profileActivation==true) {
                        Intent intent = new Intent(LoginActivity.this, Bottom_Navigation_Bar.class);

                        Toast.makeText(getApplicationContext(), " Dashboard!",
                                Toast.LENGTH_SHORT).show();
                        System.out.println("ssdsddsasss");

                        startActivity(intent);


                    } else if (SharedData.profileActivation==false) {

                        Intent intent = new Intent(LoginActivity.this, InformationActivity.class);

                        Toast.makeText(getApplicationContext(), "Please complete your Profile data!",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }

                } else {


                    System.out.println(" Some thing not working fine========");
                    Toast.makeText(getApplicationContext(), "Either your email or password is wrong!",
                            Toast.LENGTH_SHORT).show();


                }
//                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("failed Api");

            }
        });

    }


}