package com.techozon.cedricfinalappdesign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.techozon.cedricfinalappdesign.Model.UpdateProfileModel;
import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


@SuppressWarnings("ALL")
public class UpdateProfileFragment extends Fragment {
    private MaterialButton mUpdateProfileButton;
    private TextInputEditText mUpdateUsername, mUpdateEmail, mUpdateHeight, mUpdateWeight, mUpdateAge;
    String updateUsername, updateEmail, updateHeight, updateWeight, updateAge, updateImage;
    private static final int PICK_IMAGE_REQUEST = 1000;

    //firebase
//    DatabaseReference databaseReference;
//    FirebaseDatabase firebaseDatabase;
//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;
    // instance for firebase storage and StorageReference
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private String uid;
   // private String imgUrl;
    private ImageView mUserProfileImage;
    // Uri indicates, where the image will be picked from
    private Uri imageUri;
    String name,email,height,weight,age;
    Bitmap bitmap;


    public UpdateProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_update_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //button id
        mUpdateProfileButton = view.findViewById(R.id.updateProfileBtn);

        //EditText id's
        uid = SharedData.id;
        mUserProfileImage = view.findViewById(R.id.userImageFromGallery);
        mUpdateUsername = view.findViewById(R.id.editTextUpdateUsername);
        mUpdateUsername.getText().toString();
        mUpdateEmail = view.findViewById(R.id.editTextUpdateEmail);
        mUpdateAge = view.findViewById(R.id.editTextUpdateAge);
        mUpdateHeight = view.findViewById(R.id.editTextHeightUpdate);
        mUpdateWeight = view.findViewById(R.id.editTextWeightUpdate);
        getUsersData();

        //firebase
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //storage = FirebaseStorage.getInstance();
        //storageReference = storage.getReference();



        //new work
        mUserProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();

            }
        });


        mUpdateProfileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name=mUpdateUsername.getText().toString();
                email=mUpdateEmail.getText().toString();
                height=mUpdateHeight.getText().toString();
                weight=mUpdateWeight.getText().toString();
                age=mUpdateAge.getText().toString();
                String  img=mUserProfileImage.toString();

                updateProfileButtonListener(name,email,height,weight,age,img);
                //updateUsername,updateEmail,updateAge,updateHeight,updateWeight,updateImage

//                Fragment fragment = new UserProfileFragment();
//                //replacing the fragment
//                if (fragment != null) {
//                    FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.navigation_container, fragment);
//                    ft.addToBackStack("UserProfileFragment");
//                    ft.commit();
//                }
            }
        });

    }

    private void SelectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);

    }

    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    // Override onActivityResult method
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            // Get the Uri of data
            imageUri = data.getData();
            try {

                // Setting image on image view using Bitmap
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),
                                imageUri);
                mUserProfileImage.setImageBitmap(bitmap);

               // uploadPicture();

            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private void uploadPicture() {
//        img = convertToString();
//        System.out.println(img +"image hy");
//
//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setTitle("Uploading Image.....");
//        progressDialog.show();
//
//        final String randomKey = UUID.randomUUID().toString();
//        StorageReference imagesRef = storageReference.child("images/" + randomKey);
//        imagesRef.putFile(imageUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                               // imgUrl = uri.toString();
//
//                            }
//                        });
//                        progressDialog.dismiss();
//                        Snackbar.make(getView(), "Image Uploaded.", Snackbar.LENGTH_LONG).show();
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext(), "Failed to Upload", Toast.LENGTH_LONG).show();
//
//                    }
//                })
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                        double progressPercent = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
//                        progressDialog.setMessage("progress: " + (int) progressPercent + "%");
//
//                    }
//                });

    }


    private void updateProfileButtonListener(String name,String email,String height,String weight,String age,String img) {


        Call<UpdateProfileModel> call = ApiClient.getService().updateProfileData(uid,weight,name,email,height,age,img);
        //

        // on below line we are executing our method.
        call.enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(@NotNull Call<UpdateProfileModel> call, @NotNull Response<UpdateProfileModel> response) {
                // this method is called when we get response from our api.


                if (response.isSuccessful()) {


                    UpdateProfileModel updateProfileModel = response.body();
                    assert updateProfileModel != null;
                    SharedData.id=updateProfileModel.getId();
                    SharedData.username=updateProfileModel.getName();
                    System.out.println("username"+SharedData.username);

                   // mUsername.setText(SharedData.username);
                    SharedData.age=updateProfileModel.getAge();
                   // mAge.setText(SharedData.age);
                    SharedData.height=updateProfileModel.getHeight();
                    //mHeight.setText(SharedData.height);
                    SharedData.weight=updateProfileModel.getWeight();
                    //mWeight.setText(SharedData.weight);
                    SharedData.email=updateProfileModel.getEmail();
                    //mGender.setText(SharedData.gender);
                    SharedData.imageUrl=updateProfileModel.getProfileImage();
                    System.out.println(SharedData.imageUrl +"image from APi");
                    try {
                       // Glide.with(getContext()).asBitmap().load(SharedData.imageUrl).into(SharedData.imageUrl);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();

                    }
                    Toast.makeText(getContext(), "Data updated.", Toast.LENGTH_SHORT).show();


                    Fragment fragment = new UserProfileFragment();
                    //replacing the fragment
                    if (fragment != null) {
                        FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.navigation_container, fragment);
                       ft.addToBackStack("DashboardFragment");

                       // ft.disallowAddToBackStack();
                       // ft.addToBackStack(null);
                        ft.commit();
                    }



//                loadingPB.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("failed Api");

            }
        });




        //  DatabaseReference updateReference = firebaseDatabase.getReference().child("Cedric").child("Users Data");
        //  updateReference.addListenerForSingleValueEvent(new ValueEventListener() {
        //  @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.hasChildren()) {
//                    //update and save in string
//                    HashMap<String, Object> updateUserMap = new HashMap<>();
//                    //  userDetailsMap.put("uid", Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
//                    updateUserMap.put("username", mUpdateUsername.getText().toString());
//                    updateUserMap.put("email", mUpdateEmail.getText().toString());
//                    updateUserMap.put("Age", mUpdateAge.getText().toString());
//                    updateUserMap.put("userHeight", mUpdateHeight.getText().toString());
//                    updateUserMap.put("userWeight", mUpdateWeight.getText().toString());
//
//
//                    if (imgUrl==null) {
//                        try {
//                            updateUserMap.put("userProfileImage", updateImage);
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//
//                        }
//
//
//                    } else if (imgUrl.equals(updateImage) || imgUrl.matches(updateImage)) {
//                        try {
//                            updateUserMap.put("userProfileImage", updateImage);
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//
//                        }
//
//                    } else {
//                        try {
//                            updateUserMap.put("userProfileImage", imgUrl);
//
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//
//                        }
//
//                    }


        //uploadImage();

//
//                  //  updateReference.child(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).updateChildren(updateUserMap);
//                    Toast.makeText(getContext(), "Data updated.", Toast.LENGTH_SHORT).show();
//
//                    Fragment fragment = new UserProfileFragment();
//                    //replacing the fragment
//                    if (fragment != null) {
//                        FragmentTransaction ft = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
//                        ft.replace(R.id.navigation_container, fragment);
//                        ft.addToBackStack("UserProfileFragment");
//                        ft.commit();
//                    }
//
//
//                    }
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


    private void getUsersData() {

        mUpdateUsername.setText(SharedData.username);
        updateUsername=mUpdateUsername.getText().toString();
        System.out.println(updateUsername +"kkkkk");
        mUpdateEmail.setText(SharedData.email);
        updateEmail=mUpdateEmail.getText().toString();
        mUpdateHeight.setText(SharedData.height);
        updateHeight=mUpdateHeight.getText().toString();
        mUpdateWeight.setText(SharedData.weight);
        updateWeight=mUpdateWeight.getText().toString();
        mUpdateAge.setText(SharedData.age);
        updateAge=mUpdateAge.getText().toString();
        mUserProfileImage.setImageURI(Uri.parse("jkkkkk"));
        updateImage=mUserProfileImage.toString();

//                    if (updateImage == null) {
//                        updateImage="";
//
//
//                    } else {
//        try {
//            Glide.with(getContext()).asBitmap()
//                    .load(updateImage).into(mUserProfileImage);
//
//
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//
//        }

    }

}







