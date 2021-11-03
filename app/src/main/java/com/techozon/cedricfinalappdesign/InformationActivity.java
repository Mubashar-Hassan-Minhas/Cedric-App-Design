package com.techozon.cedricfinalappdesign;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.techozon.cedricfinalappdesign.R.string.weight_error;

public class InformationActivity extends AppCompatActivity {

    private MaterialButton mInformationButton;
    private TextInputEditText mEditTextWeight, mEditTextAge;
    private AutoCompleteTextView gender;
    private static TextInputEditText mEditTextHeight;
    ArrayList<String> arrayList_gender;
    ArrayAdapter<String> arrayAdapter_gender;
    private TextInputLayout mTextInputLayoutGender;
    private MaterialCardView cardView;

//    private FirebaseAuth mAuth;
//    private DatabaseReference databaseReference;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //getIntent
        Intent intent = getIntent();
        id = intent.getStringExtra("Id");
        System.out.println(id + "user in Info");

//        mAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Users Data");

        mEditTextHeight = findViewById(R.id.editTextHeight);
        mEditTextWeight = findViewById(R.id.editTextWeight);
        mEditTextAge = findViewById(R.id.editTextAge);
        mInformationButton = findViewById(R.id.btnInformationNext);
        mEditTextHeight.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3, 2)});


//        mEditTextHeight.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                String height = mEditTextHeight.getText().toString();
////                if(height.length() == 1) {
////                    mEditTextHeight.setText(height + "'");
////                }
////
////                if (before == 1 && s.length() == start) {
////                    return; // allows delete
////                }else{
////                    mEditTextHeight.setSelection(height.length());
////                }
//               // mEditTextHeight.setText(" ");
////                if (s.length() > 0) {
////                    //Here it means back button is pressed and edit text is now empty
////                    mEditTextHeight.setText(" ");
////                } else {
////                    //Here edit text has some text
////                }
//
//            }



        //For Dropdown icon
        mTextInputLayoutGender = findViewById(R.id.textInputLayoutGender);
        gender = findViewById(R.id.gender);
        gender.setDropDownBackgroundDrawable(Drawable.createFromPath("#FFC153"));
        arrayList_gender = new ArrayList<>();
        arrayList_gender.add("Male");
        arrayList_gender.add("Female");
        arrayAdapter_gender = new ArrayAdapter<>(getApplicationContext(),
                R.layout.dropdown_item, arrayList_gender);
        gender.setAdapter(arrayAdapter_gender);
        gender.setThreshold(1);


        mInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });
    }



    private void saveUserData() {
        // Check for a valid email address.
        if (mEditTextWeight.getText().toString().isEmpty()) {
            mEditTextWeight.setError(getResources().getString(weight_error));

        } else if (mEditTextHeight.getText().toString().isEmpty()) {
            mEditTextHeight.setError(getResources().getString(R.string.height_error));

        } else if (mEditTextAge.getText().toString().isEmpty()) {
            mEditTextAge.setError(getResources().getString(R.string.Age_error));

        } else if (gender.getText().toString().isEmpty()) {
            gender.setError(getResources().getString(R.string.gender_error));
        } else {

            //HashMap<String, Object> userDetailsMap = new HashMap<>();
            //  userDetailsMap.put("uid", Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
//            userDetailsMap.put("userHeight", mEditTextHeight.getText().toString());
//            userDetailsMap.put("userWeight", mEditTextWeight.getText().toString());
//            userDetailsMap.put("Age", mEditTextAge.getText().toString());
//            userDetailsMap.put("gender", gender.getText().toString());

//            databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userDetailsMap);
//            Toast.makeText(this, "Data added to database.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(com.techozon.cedricfinalappdesign.InformationActivity.this,
                    FitnessLevelActivity.class);
            intent.putExtra("userHeight", mEditTextHeight.getText().toString());
            intent.putExtra("userWeight",  mEditTextWeight.getText().toString());
            intent.putExtra("Age", mEditTextAge.getText().toString());
            intent.putExtra("gender", gender.getText().toString());
            startActivity(intent);
        }
    }

    public static class DecimalDigitsInputFilter implements InputFilter {
        Pattern pattern;

        public DecimalDigitsInputFilter(int digitsBeforeDecimal, int digitsAfterDecimal) {
            pattern = Pattern.compile("(([1-9]{1}[0-9]{0," + (digitsBeforeDecimal - 1) + "})?||[0]{1})((\\.[0-9]{0,"
                    + digitsAfterDecimal + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int sourceStart, int sourceEnd, Spanned destination, int destinationStart, int destinationEnd) {
            // Remove the string out of destination that is to be replaced.
            String newString = destination.toString().substring(0, destinationStart) + destination.toString().substring(destinationEnd, destination.toString().length());

            // Add the new string in.
            newString = newString.substring(0, destinationStart) + source.toString() + newString.substring(destinationStart, newString.length());

            // Now check if the new string is valid.
            Matcher matcher = pattern.matcher(newString);

            if (matcher.matches()) {
                // Returning null indicates that the input is valid.
                return null;
            }

            // Returning the empty string indicates the input is invalid.
            return "";
        }
    }

    // To use this InputFilter, attach it to your EditText like so:
    //  mEditTextHeight =  findViewById(R.id.editTextHeight);


}