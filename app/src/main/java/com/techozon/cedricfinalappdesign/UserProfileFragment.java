
package com.techozon.cedricfinalappdesign;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;


@SuppressWarnings("ALL")
public class UserProfileFragment extends Fragment {
    private MaterialButton mEditProfileButton;
    private ImageButton backArrow;
    ImageView mUserImage;
    private FrameLayout mConstraintLayout;
    private MaterialTextView mUsername ,mGender, mHeight, mWeight, mAge;
    private String age, height, weight, gender,userName,userImage;

//    FirebaseDatabase firebaseDatabase;
//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;
//    DatabaseReference databaseReference;
    String uid;

    public UserProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_user_profile, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uid = SharedData.id;

       // getDataFromDatabase();



        //get id's
        mEditProfileButton = view.findViewById(R.id.editProfileBtn);
        backArrow = view.findViewById(R.id.backArrow);
        mConstraintLayout = view.findViewById(R.id.fragmentContainerProfile);
        mUsername=view.findViewById(R.id.userName);
        mUsername.setText(SharedData.username);
        mGender = view.findViewById(R.id.userGender);
        mGender.setText(SharedData.gender);
        mHeight = view.findViewById(R.id.userHeight);
        mHeight.setText(SharedData.height);
        mAge = view.findViewById(R.id.userAge);
        mAge.setText(SharedData.age);
        mWeight = view.findViewById(R.id.userWeight);
        mWeight.setText(SharedData.weight);
        mUserImage=view.findViewById(R.id.profileUserImage);
      mUserImage.setImageURI(Uri.parse("fdfdddf"));

//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();



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


        mEditProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mConstraintLayout.setVisibility(View.GONE);
                Fragment fragment = new UpdateProfileFragment();
                @SuppressLint("UseRequireInsteadOfGet") FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                fragmentTransaction.replace(R.id.navigation_container, fragment);
                fragmentTransaction.addToBackStack("DashboardFragment");
                //fragmentTransaction.disallowAddToBackStack();
                fragmentTransaction.commit();


            }
        });

    }

    private void getDataFromDatabase() {

//        Call<getProfileResponseModel> call = ApiClient.getService().getProfileData(uid);
//
//        // on below line we are executing our method.
//        call.enqueue(new Callback<getProfileResponseModel>() {
//            @Override
//            public void onResponse(@NotNull Call<getProfileResponseModel> call, @NotNull Response<getProfileResponseModel> response) {
//                // this method is called when we get response from our api.
//
//
//                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(), "get all data",
//                            Toast.LENGTH_SHORT).show();
//
//
//                    getProfileResponseModel getProfileResponse = response.body();
//                    assert getProfileResponse != null;
//                    SharedData.id=getProfileResponse.getId();
//                    SharedData.username=getProfileResponse.getName();
//                    mUsername.setText(SharedData.username);
//                    SharedData.age=getProfileResponse.getAge();
//                    mAge.setText(SharedData.age);
//                    SharedData.height=getProfileResponse.getHeight();
//                    mHeight.setText(SharedData.height);
//                    SharedData.weight=getProfileResponse.getWeight();
//                    mWeight.setText(SharedData.weight);
//                    SharedData.email=getProfileResponse.getEmail();
//                    mGender.setText(SharedData.gender);
//                    SharedData.imageUrl=getProfileResponse.getImageUrl();
//                    try {
//                            Glide.with(getContext()).asBitmap().load(SharedData.imageUrl).into(mUserImage);
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//
//                        }
//
//
//
//
//
////                loadingPB.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<getProfileResponseModel> call, Throwable t) {
//                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                System.out.println("failed Api");
//
//            }
//        });


        //databaseReference = firebaseDatabase.getReference().child("Cedric").child("Users Data").child(uid);
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (firebaseUser != null && snapshot.hasChildren()) {
//                    //get and save in string
//                    userName=snapshot.child("username").getValue().toString();
//                    height = snapshot.child("userHeight").getValue().toString();
//                    System.out.println(height);
//                    weight = snapshot.child("userWeight").getValue().toString();
//                    System.out.println(weight);
//                    age = snapshot.child("Age").getValue(String.class);
//                    System.out.println(age);
//                    gender = snapshot.child("gender").getValue(String.class);
//                    userImage = snapshot.child("userProfileImage").getValue(String.class);
//                    //userName=snapshot.child("username").getValue(String.class);
//                    System.out.println(userImage);
//
//                    //set to textView
//                    mUsername.setText(userName);
//                    mGender.setText(gender);
//                    mAge.setText(age);
//                    mHeight.setText(height);
//                    mWeight.setText(weight);
//
//                   // mUserImage.setImageResource(Integer.parseInt(userImage));
//                    if(userImage == null ) {
//                        addDumyImage();
//                    }
//                    else {
//
//                        try {
//                            Glide.with(getContext()).asBitmap().load(userImage).into(mUserImage);
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//
//                        }
//                    }
//
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    private void addDumyImage() {
//        DatabaseReference updateReference = firebaseDatabase.getReference().child("Cedric").child("Users Data");
//        userImage="https://firebasestorage.googleapis.com/v0/b/cedric-8cb7d.appspot.com/o/images%2Fprofile.png?alt=media&token=e350275b-5e47-4fc0-bf6f-396f67cfdfaf";
//        HashMap<String, Object> userMap = new HashMap<>();
//        userMap.put("userProfileImage", userImage);
//
//        updateReference.child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).updateChildren(userMap);

    }
}