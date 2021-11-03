package com.techozon.cedricfinalappdesign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.techozon.cedricfinalappdesign.R.string.name_email_error;
import static com.techozon.cedricfinalappdesign.R.string.username_error;


public class SignupActivity extends AppCompatActivity {

    private MaterialButton mSignUpButton, mGotoLoginActivityButton;
    Animation slideDown;
    LinearLayout linearLayout;
    private TextInputEditText mEditTextUsername, mEditTextEmail, mEditTextPassword, mEditTextConfirmPassword;
    boolean isEmailValid, isPasswordValid;
    private ProgressBar loadingPB;

    //FirebaseAuth mAuth;
    //private DatabaseReference mDatabaseReference;

    String status = "",id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //For firebase instance
//        mAuth = FirebaseAuth.getInstance();

        mSignUpButton = findViewById(R.id.btnSigUp);
        linearLayout = findViewById(R.id.linearLayout1);
        loadingPB = findViewById(R.id.idLoadingPB);

        mEditTextUsername = findViewById(R.id.editTextUsername);
        mEditTextEmail = findViewById(R.id.editTextEmail);
        mEditTextPassword = findViewById(R.id.editTextPassword);
        mEditTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        mGotoLoginActivityButton = findViewById(R.id.btnLoginSignUpActivity);
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        //listener for signup button
        mSignUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setValidation();
//                Intent intent = new Intent(SignupActivity.this, InformationActivity.class);
//                startActivity(intent);
            }
        });


        //listener for Login button
        mGotoLoginActivityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //setValidation();

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                linearLayout.startAnimation(slideDown);
                // overridePendingTransition(R.anim.slide_down);

            }
        });
    }

    private void setValidation() {


        String name = mEditTextUsername.getText().toString();
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();


        // Check for a valid email address.
        if (Objects.requireNonNull(email.isEmpty())) {
            mEditTextEmail.setError(getResources().getString(name_email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEditTextEmail.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else if (Objects.requireNonNull(name.isEmpty())) {

            mEditTextUsername.setError(getResources().getString(username_error));

        }

        // Check for a valid password.
        else if (Objects.requireNonNull(password.isEmpty())) {

            mEditTextPassword.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (mEditTextPassword.getText().length() < 4) {
            mEditTextPassword.setError(getResources().getString(R.string.error_weak_password));
            isPasswordValid = false;
        } else if (mEditTextPassword.getText().length() < 6) {
            mEditTextPassword.setError(getResources().getString(R.string.error_medium_password));
            isPasswordValid = false;
        } else if (!(password.equals(mEditTextConfirmPassword.getText().toString()))) {
            mEditTextConfirmPassword.setError(getResources().getString(R.string.error_match_password));
            Toast.makeText(SignupActivity.this, "Password did not match", Toast.LENGTH_LONG).show();
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
            isEmailValid = true;
            registerUser(name, email, password);

        }
    }

    public void registerUser(String name, String email, String password) {
        loadingPB.setVisibility(View.VISIBLE);
        Call<SignupResponse> call = ApiClient.getService().createPost(name, email, password);

        // on below line we are executing our method.
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(@NotNull Call<SignupResponse> call, @NotNull Response<SignupResponse> response) {
                // this method is called when we get response from our api.


                if (response.isSuccessful()) {
//                    Toast.makeText(SignupActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();


                    // below line is for hiding our progress bar.
                    SignupResponse signupResponse = response.body();
                    try {
                        assert signupResponse != null;
                        status = signupResponse.getStatus();
                        SharedData.id=signupResponse.getId();
                        SharedData.profileActivation=signupResponse.isProfileActivated;
                        System.out.println("hello"+SharedData.profileActivation);
                        System.out.println(signupResponse.getStatus() + "lllll");
                        System.out.println(status + "uuuuu");
                        System.out.println(SharedData.id + "id");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(status + "mmmm");
                        System.out.println(id + "nnid");
                    }

                    if (signupResponse.getStatus().equals("Users created Successfully")) {

                        Intent intent = new Intent(SignupActivity.this, InformationActivity.class);
                        intent.putExtra("Email", email);
                        intent.putExtra("Id", SharedData.id);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Successfully Register!",
                                Toast.LENGTH_SHORT).show();


                    } else if (signupResponse.getStatus().equals("Email already exists")) {
                        Toast.makeText(getApplicationContext(), "Email already registered!",
                                Toast.LENGTH_SHORT).show();


                    }


                    //  System.out.println(responseFromAPI.toString() + "ppp");

                    // on below line we are getting our data from modal class and adding it to our string.
//                    String responseString = "Response Code : " + response.code() + "\nName : " + signupResponse.getId()
//                            + "\n" + "Status : " + signupResponse.getStatus() + "\nPassword"+signupResponse.isProfileActivated();


                } else {
                    System.out.println(" Some thing not working fine========");
                    Toast.makeText(getApplicationContext(), "Email already registered!",
                            Toast.LENGTH_SHORT).show();
                    mEditTextUsername.setText("");
                    mEditTextPassword.setText("");
                    mEditTextConfirmPassword.setText("");

                }
                loadingPB.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                //responseTV.setText("Error found is : " + t.getMessage());
                Toast.makeText(SignupActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("fail Api");
            }
        });

    }

//            mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Users user = new Users(username, email, password);
//                                FirebaseDatabase.getInstance().getReference().child("Cedric").child("Users Data")
//                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//
//                                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                                            intent.putExtra("Email", email);
//                                            startActivity(intent);
//                                            Toast.makeText(getApplicationContext(), "Successfully Register!",
//                                                    Toast.LENGTH_SHORT).show();
//
//                                        } else {
//                                            Toast.makeText(SignupActivity.this, "Please Try Again. Error Occurred, while registering... ", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//                            } else {
//                                Toast.makeText(SignupActivity.this, "Another user is using this Email!Please Try another!", Toast.LENGTH_LONG)
//                                        .show();
//                            }
//                        }
//                    });


}

