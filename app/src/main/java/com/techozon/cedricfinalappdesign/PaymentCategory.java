package com.techozon.cedricfinalappdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techozon.cedricfinalappdesign.Adapters.PlansAdapter;
import com.techozon.cedricfinalappdesign.Model.PlansDataModel;
import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentCategory extends AppCompatActivity {
    private MaterialCardView mCard1, mCard2, mCard3;
    private RecyclerView mPlansRecyclerView;
    private ArrayList<PlansDataModel> plansDataList;
    private PlansAdapter plansAdapter;
    MaterialButton mPaymentCategoryNextButton;

    String cardType, cardNumber, exp_Date, cardCvv;

    double monthPlan;
    String startDate;
    LocalDate endDate;
    String userLevel,userGoals, height, weight, gender, age;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_category);

        //getIntent
        Intent intent = getIntent();
        userLevel = intent.getStringExtra("level");
        height = intent.getStringExtra("userHeight");
        weight = intent.getStringExtra("userWeight");
        age = intent.getStringExtra("Age");
        gender = intent.getStringExtra("gender");
        userGoals = intent.getStringExtra("goals");







//        mAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Payment Details");

        //getIntent
//        Intent intent1 = getIntent();
//        cardType = intent.getStringExtra("cardType");
//        cardNumber = intent.getStringExtra("cardNumber");
//        cardCvv = intent.getStringExtra("cardCvv");
//        exp_Date = intent.getStringExtra("expireDate");


        //get id's
        mPlansRecyclerView = findViewById(R.id.plansRecyclerview);
        ;
        //button id
        mPaymentCategoryNextButton = findViewById(R.id.btnPaymentMethodNext);

        //get date
        startDate = SimpleDateFormat.getDateInstance().format(new Date());
        System.out.println(startDate + "===date");

        plansDataList = new ArrayList<>();

        // calling a method to
        // get all the courses.
        getAllPackages();


        mPaymentCategoryNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.techozon.cedricfinalappdesign.PaymentCategory.this, CheckoutActivityJava.class);

                System.out.println(SharedData.dPlanPrice +"Share price");
                intent.putExtra("level", userLevel);
                intent.putExtra("goals", userGoals);
                intent.putExtra("userHeight", height);
                intent.putExtra("userWeight", weight);
                intent.putExtra("Age", age);
                intent.putExtra("gender", gender);
                startActivity(intent);
            }
        });


    }

    private void getAllPackages() {
        // on below line we are calling a method to get all the courses from API.
        Call<ArrayList<PlansDataModel>> call = ApiClient.getService().getAllPackages();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<ArrayList<PlansDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PlansDataModel>> call, Response<ArrayList<PlansDataModel>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {

                    // on successful we are hiding our progressbar.
                    // progressBar.setVisibility(View.GONE);

                    // below line is to add our data from api to our array list.
                    plansDataList = response.body();


                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < plansDataList.size(); i++) {
                        plansAdapter = new PlansAdapter(plansDataList, PaymentCategory.this);

                        // below line is to set layout manager for our recycler view.
                        LinearLayoutManager manager = new LinearLayoutManager(PaymentCategory.this);

                        // setting layout manager for our recycler view.
                        mPlansRecyclerView.setLayoutManager(manager);

                        // below line is to set adapter to our recycler view.
                        mPlansRecyclerView.setAdapter(plansAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PlansDataModel>> call, Throwable t) {
                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(PaymentCategory.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }


//        View.OnClickListener listener = new View.OnClickListener() {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.cardViewPayMethod1) {
//
//                    mMonthsCardView1.setTextColor(Color.BLACK);
//                    mAedCardView1.setTextColor(Color.BLACK);
//                    mCard1.setCardBackgroundColor(Color.parseColor("#FFC153"));
//
//
//                    //cardview2
//                    mMonthsCardView2.setTextColor(Color.WHITE);
//                    mAedCardView2.setTextColor(Color.WHITE);
//                    mCard2.setCardBackgroundColor(Color.TRANSPARENT);
//                    //cardview3
//                    mMonthsCardView3.setTextColor(Color.WHITE);
//                    mAedCardView3.setTextColor(Color.WHITE);
//                    mCard3.setCardBackgroundColor(Color.TRANSPARENT);
//
//                    mPaymentCategoryNextButton.setOnClickListener(new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            perMonthCharges = mPerMonthPlanCard1.getText().toString();
//                            offPercentage = mOffPercentageCard1.getText().toString();
//                            plan = mMonthsCardView1.getText().toString();
//                            planCharges = mAedCardView1.getText().toString();
//                            try {
//                                monthPlan = ((Number) NumberFormat.getInstance().parse(plan)).intValue();
//
//
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//
//                            endDate = LocalDate.now().plusMonths(monthPlan);
//
//                            System.out.println(endDate + "pack");
//
//                            savePaymentInfoInDatabase();
//
//                        }
//                    });
//                }
//                if (v.getId() == R.id.cardViewPayMethod2) {
//                    mMonthsCardView2.setTextColor(Color.BLACK);
//                    mAedCardView2.setTextColor(Color.BLACK);
//                    mCard2.setCardBackgroundColor(Color.parseColor("#FFC153"));
//
//                    //set other two normal
//                    mMonthsCardView1.setTextColor(Color.WHITE);
//                    mAedCardView1.setTextColor(Color.WHITE);
//                    mCard1.setCardBackgroundColor(Color.TRANSPARENT);
//
//                    mMonthsCardView3.setTextColor(Color.WHITE);
//                    mAedCardView3.setTextColor(Color.WHITE);
//                    mCard3.setCardBackgroundColor(Color.TRANSPARENT);
//
//                    mPaymentCategoryNextButton.setOnClickListener(new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            perMonthCharges = mPerMonthPlanCard2.getText().toString();
//                            offPercentage = mOffPercentageCard2.getText().toString();
//                            plan = mMonthsCardView2.getText().toString();
//                            planCharges = mAedCardView2.getText().toString();
//
//                            try {
//                                monthPlan= ((Number) NumberFormat.getInstance().parse(plan)).intValue();
//
//
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//
//                            endDate = LocalDate.now().plusMonths(monthPlan);
//
//                            System.out.println(endDate +"pack");
//
//                            savePaymentInfoInDatabase();
//
//                        }
//                    });
//                }
//                if (v.getId() == R.id.cardViewPayMethod3) {
//
//                    mMonthsCardView3.setTextColor(Color.BLACK);
//                    mAedCardView3.setTextColor(Color.BLACK);
//                    mCard3.setCardBackgroundColor(Color.parseColor("#FFC153"));
//
//
//                    mMonthsCardView2.setTextColor(Color.WHITE);
//                    mAedCardView2.setTextColor(Color.WHITE);
//                    mCard2.setCardBackgroundColor(Color.TRANSPARENT);
//
//                    mMonthsCardView1.setTextColor(Color.WHITE);
//                    mAedCardView1.setTextColor(Color.WHITE);
//                    mCard1.setCardBackgroundColor(Color.TRANSPARENT);
//
//                    mPaymentCategoryNextButton.setOnClickListener(new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            perMonthCharges = mPerMonthPlanCard3.getText().toString();
//                            offPercentage = mOffPercentageCard3.getText().toString();
//                            plan = mMonthsCardView3.getText().toString();
//                            planCharges = mAedCardView3.getText().toString();
//                            try {
//                                monthPlan= ((Number) NumberFormat.getInstance().parse(plan)).intValue();
//
//
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//
//                            endDate = LocalDate.now().plusMonths(monthPlan);
//
//                            System.out.println(endDate +"pack");
//
//                            savePaymentInfoInDatabase();
//
//                        }
//                    });
//                }
//            }
//        };
//        mCard1.setOnClickListener(listener);
//        mCard2.setOnClickListener(listener);
//        mCard3.setOnClickListener(listener);


    private void savePaymentInfoInDatabase() {
//        HashMap<String, Object> userPaymentDetailsMap = new HashMap<>();
//        userPaymentDetailsMap.put("uid", Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
//        userPaymentDetailsMap.put("CardType", cardType);
//        userPaymentDetailsMap.put("Card Number", cardNumber);
//        userPaymentDetailsMap.put("Card CVV", cardCvv);
//        userPaymentDetailsMap.put("Expire Date", exp_Date);
//        userPaymentDetailsMap.put("Per Month Charges", perMonthCharges);
//        userPaymentDetailsMap.put("Off Percentage", offPercentage);
//        userPaymentDetailsMap.put("Plan", plan);
//        userPaymentDetailsMap.put("Plan Charges", planCharges);
//        userPaymentDetailsMap.put("Start Date", startDate);
//        userPaymentDetailsMap.put("End Date", endDate.toString());

//        databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(userPaymentDetailsMap);
        Toast.makeText(getApplicationContext(), "Data added to database.", Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(com.techozon.cedricfinalappdesign.PaymentCategory.this, CheckoutActivityJava.class);
        startActivity(intent1);

    }

}


//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.cardViewPayMethod1) {
//
//            mMonthsCardView1.setTextColor(Color.BLACK);
//            mAedCardView1.setTextColor(Color.BLACK);
//            mCard1.setCardBackgroundColor(Color.parseColor("#FFC153"));
//
//
//            //cardview2
//            mMonthsCardView2.setTextColor(Color.WHITE);
//            mAedCardView2.setTextColor(Color.WHITE);
//            mCard2.setCardBackgroundColor(Color.TRANSPARENT);
//            //cardview3
//            mMonthsCardView3.setTextColor(Color.WHITE);
//            mAedCardView3.setTextColor(Color.WHITE);
//            mCard3.setCardBackgroundColor(Color.TRANSPARENT);
//        }
//        if (v.getId() == R.id.cardViewPayMethod2) {
//            mMonthsCardView2.setTextColor(Color.BLACK);
//            mAedCardView2.setTextColor(Color.BLACK);
//            mCard2.setCardBackgroundColor(Color.parseColor("#FFC153"));
//
//            //set other two normal
//            mMonthsCardView1.setTextColor(Color.WHITE);
//            mAedCardView1.setTextColor(Color.WHITE);
//            mCard1.setCardBackgroundColor(Color.TRANSPARENT);
//
//            mMonthsCardView3.setTextColor(Color.WHITE);
//            mAedCardView3.setTextColor(Color.WHITE);
//            mCard3.setCardBackgroundColor(Color.TRANSPARENT);
//        }
//        if (v.getId() == R.id.cardViewPayMethod3) {
//
//            mMonthsCardView3.setTextColor(Color.BLACK);
//            mAedCardView3.setTextColor(Color.BLACK);
//            mCard3.setCardBackgroundColor(Color.parseColor("#FFC153"));
//
//
//            mMonthsCardView2.setTextColor(Color.WHITE);
//            mAedCardView2.setTextColor(Color.WHITE);
//            mCard2.setCardBackgroundColor(Color.TRANSPARENT);
//
//            mMonthsCardView1.setTextColor(Color.WHITE);
//            mAedCardView1.setTextColor(Color.WHITE);
//            mCard1.setCardBackgroundColor(Color.TRANSPARENT);
//        }
//
//    }


