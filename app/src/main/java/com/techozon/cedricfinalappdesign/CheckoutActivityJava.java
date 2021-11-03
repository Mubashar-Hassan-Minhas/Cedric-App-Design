package com.techozon.cedricfinalappdesign;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.techozon.cedricfinalappdesign.Model.ProfileActivationResponse;
import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CheckoutActivityJava extends AppCompatActivity {

    // 10.0.2.2 is the Android emulator's alias to localhost
    private static final String BACKEND_URL = "https://obscure-refuge-36000.herokuapp.com/";
    private OkHttpClient httpClient = new OkHttpClient();
    private String paymentIntentClientSecret;
    private Stripe stripe;
    private TextView amountTextView;

    private static String height,price,weight,gender,age,goals,duration,orderId,orderRef,level,
    orderStatus,profileImage,paymentMethod,transactionDate,comments;


    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_java);

        Intent intent = getIntent();
        level = intent.getStringExtra("level");
        height = intent.getStringExtra("userHeight");
        weight = intent.getStringExtra("userWeight");
        age = intent.getStringExtra("Age");
        gender = intent.getStringExtra("gender");
        goals = intent.getStringExtra("goals");
        duration=SharedData.plan;
        price=SharedData.planPrice;
        id=SharedData.id;


        amountTextView = findViewById(R.id.amountTextView);

        // Configure the SDK with your Stripe publishable key so it can make requests to Stripe
        stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull("pk_test_51JjIAXLx3yBYLT8ENQmVoSrg7XT4lEfVofVWkdkMjd91CqNowWApV9vBtAFpIiCgiF2vM7gl79XK4QcSzV6exicv00L2yjglv0")
        );
        startCheckout();
    }

    private void startCheckout() {
        // Create a PaymentIntent by calling the server's endpoint.
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        double amount = Double.valueOf(amountTextView.getText().toString()) * 100;

        Map<String, Object> payMap = new HashMap<>();
        Map<String, Object> itemMap = new HashMap<>();
        List<Map<String, Object>> itemList = new ArrayList<>();
        payMap.put("currency", "AED");  //dont change currency in testing phase otherwise it won't work
        itemMap.put("id", "photo_subscription");
        itemMap.put("amount", amount);
        itemList.add(itemMap);
        System.out.println(itemList + "==ssssssssssss===");
        payMap.put("items", itemList);
        String json = new Gson().toJson(payMap);

        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(BACKEND_URL + "create-payment-intent")
                .post(body)
                .build();
        httpClient.newCall(request)
                .enqueue(new PayCallback(this));
        // Hook up the pay button to the card widget and stripe instance
        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener((View view) -> {
            CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);

            PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
            if (params != null) {
                ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                        .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret);
                stripe.confirmPayment(this, confirmParams);
            }
        });
    }

    private void displayAlert(@NonNull String title, @Nullable String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//
                activateProfile(weight, height, age,gender,goals,level,profileImage,paymentMethod,comments,
                        orderId,orderRef,orderStatus,transactionDate,price,duration);

                Intent intent1 = new Intent(com.techozon.cedricfinalappdesign.CheckoutActivityJava.this,
                        Bottom_Navigation_Bar.class);
                startActivity(intent1);

            }
        });
        builder.create().show();
    }

    private void activateProfile(String weight, String height, String age, String gender, String goals, String level, String profileImage, String paymentMethod, String comments, String orderId, String orderRef, String orderStatus, String transactionDate, String price, String duration) {

        retrofit2.Call<ProfileActivationResponse> call = ApiClient.getService().profileActivate(id,weight, height, age,gender,goals,level,profileImage,paymentMethod,comments,
                orderId,orderRef,orderStatus,transactionDate,price,duration);

        // on below line we are executing our method.
        call.enqueue(new retrofit2.Callback<ProfileActivationResponse>() {
            @Override
            public void onResponse(@NotNull retrofit2.Call<ProfileActivationResponse> call, @NotNull retrofit2.Response<ProfileActivationResponse> response) {
                // this method is called when we get response from our api.


                if (response.isSuccessful()) {
                    Toast.makeText(CheckoutActivityJava.this, "Data added to API", Toast.LENGTH_SHORT).show();


                    // below line is for hiding our progress bar.
                    ProfileActivationResponse profileResponse = response.body();
                    assert profileResponse != null;
                    SharedData.profileActivation=profileResponse.getProfileActivated();
                    SharedData.id=profileResponse.getId();
                    System.out.println("Profile Activated"+SharedData.profileActivation);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ProfileActivationResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("fail Api");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle the result of stripe.confirmPayment
        stripe.onPaymentResult(requestCode, data, new PaymentResultCallback(this));
    }

    private void onPaymentSuccess(@NonNull final Response response) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> responseMap = gson.fromJson(
                Objects.requireNonNull(response.body()).string(),
                type
        );
        paymentIntentClientSecret = responseMap.get("clientSecret");
    }

    private static final class PayCallback implements Callback {
        @NonNull
        private final WeakReference<CheckoutActivityJava> activityRef;

        PayCallback(@NonNull CheckoutActivityJava activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            final CheckoutActivityJava activity = activityRef.get();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(() ->
                    Toast.makeText(
                            activity, "Error: " + e.toString(), Toast.LENGTH_LONG
                    ).show()
            );
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull final Response response)
                throws IOException {
            final CheckoutActivityJava activity = activityRef.get();
            if (activity == null) {
                return;
            }
            if (!response.isSuccessful()) {
                activity.runOnUiThread(() ->
                        Toast.makeText(
                                activity, "Error: " + response.toString(), Toast.LENGTH_LONG
                        ).show()
                );
            } else {
                activity.onPaymentSuccess(response);
            }
        }
    }

    private  static final class PaymentResultCallback
            implements ApiResultCallback<PaymentIntentResult> {
        @NonNull
        private final WeakReference<CheckoutActivityJava> activityRef;

        PaymentResultCallback(@NonNull CheckoutActivityJava activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            final CheckoutActivityJava activity = activityRef.get();
            if (activity == null) {
                return;
            }
            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();

            if (status == PaymentIntent.Status.Succeeded) {
                // Payment completed successfully
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                activity.displayAlert(
//                        "Payment completed",
//                        gson.toJson(paymentIntent)
//
//                );
                //for new dialog
             activity.displayAlert("Payment Completed","Payment completed successfully");
                orderId=paymentIntent.getId();
                orderRef=paymentIntent.getPaymentMethodId();
                orderStatus= String.valueOf(paymentIntent.getStatus());
               comments=paymentIntent.getDescription();
               transactionDate=SimpleDateFormat.getDateInstance().format(new Date());
               paymentMethod= String.valueOf(paymentIntent.getPaymentMethod());



            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed – allow retrying using a different payment method
                activity.displayAlert(
                        "Payment failed",
                        Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage()
                );
            }
        }



        @Override
        public void onError(@NonNull Exception e) {
            final CheckoutActivityJava activity = activityRef.get();
            if (activity == null) {
                return;
            }
            // Payment request failed – allow retrying using the same payment method
            activity.displayAlert("Error", e.toString());
        }
    }
}