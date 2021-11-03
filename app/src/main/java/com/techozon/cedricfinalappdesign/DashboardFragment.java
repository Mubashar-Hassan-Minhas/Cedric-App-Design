package com.techozon.cedricfinalappdesign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.techisfun.onelinecalendar.DateSelectionListener;
import com.github.techisfun.onelinecalendar.OneLineCalendarView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techozon.cedricfinalappdesign.Adapters.CoachesAdapter;
import com.techozon.cedricfinalappdesign.Adapters.MyAdapter;
import com.techozon.cedricfinalappdesign.Model.CoachesDataModel;
import com.techozon.cedricfinalappdesign.Model.NutritionDataModel;
import com.techozon.cedricfinalappdesign.Model.VideoModel;
import com.techozon.cedricfinalappdesign.Model.VisualizationResponse;
import com.techozon.cedricfinalappdesign.Retrofit.ApiClient;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("ALL")
public class DashboardFragment extends Fragment implements View.OnClickListener {
    public static View.OnClickListener myOnClickListener;
    private RecyclerView.LayoutManager layoutManager;
    FrameLayout frameLayout;
    private ImageView mAvatar;
    private static RecyclerView recyclerView;
    private static ArrayList<CoachesDataModel> dataList = new ArrayList<>();
    public static ArrayList<VideoModel> fileArrayList = new ArrayList<>();
    private static RecyclerView.Adapter adapter;
    private MaterialCardView mSleepVisualizationCardView, mBreakFastCardview, mLunchCardView, mNightCardView;
    private LinearLayout mLinearLayoutDashboard, mLinearLayoutTextViewMAE, mLinearLayoutForMorning,
            mLinearLayoutForAfternoon, mLinearLayoutForNight;
    private MaterialTextView mTextViewBreakFast, mTextViewLunch,mTextViewDinner,mTextViewMorning, mTextViewAfternoon,
            mTextViewNight, selectedDateTw, mTextViewUsername,mTextViewWelcomingMessage;
    MyAdapter myAdapter;

//    FirebaseDatabase firebaseDatabase;
//    FirebaseAuth firebaseAuth;
//    FirebaseUser firebaseUser;
//    DatabaseReference databaseReference;
    String uid, audioUrl,dayTime,currentDate,nutritionTime;
    String imgUrl;


    public DashboardFragment() {
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
       // getCoachesData();
        return inflater.inflate(R.layout.fragment_dashboard, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        //   recyclerview for first horizontal cardView coaches
        recyclerView = view.findViewById(R.id.recyclerviewForCoaches);
        recyclerView.setHasFixedSize(true);
        dataList = new ArrayList<>();
        getCoachesData();



        //selectedDateTw=(MaterialTextView) view.findViewById(R.id.selectedDate);

        OneLineCalendarView calendarView = (OneLineCalendarView) view.findViewById(R.id.calendar_view);
        calendarView.setOnDateClickListener(new DateSelectionListener() {
            @Override
            public boolean onDateSelected(@NonNull Date date) {
                //selectedDateTw.setText(SimpleDateFormat.getDateInstance().format(date));
                return true;
            }

            @Override
            public boolean onDateUnselected() {
                // selectedDateTw.setText(R.string.no_selection);
                return true;
            }
        });

        //firebase

//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //uid = firebaseUser.getUid();


        //Avatar id
        mAvatar = view.findViewById(R.id.Avatar);
        //Linear id's
        mLinearLayoutDashboard = view.findViewById(R.id.linearlayoutMain);
        mLinearLayoutTextViewMAE = view.findViewById(R.id.linearLayoutForMAEView);
        mLinearLayoutForMorning = view.findViewById(R.id.linearLayoutForMorning);
        mLinearLayoutForAfternoon = view.findViewById(R.id.linearLayoutForAfternoon);
        mLinearLayoutForNight = view.findViewById(R.id.linearLayoutForNight);
        //CardView's Id
        mSleepVisualizationCardView = view.findViewById(R.id.sleepVisualCardView);
        mBreakFastCardview = view.findViewById(R.id.breakfastCardView);
        mLunchCardView = view.findViewById(R.id.LunchCardView);
        mNightCardView = view.findViewById(R.id.DinnerCardView);
        //TextView's Id's
        mTextViewWelcomingMessage=view.findViewById(R.id.textViewWelcoming);
        mTextViewUsername = view.findViewById(R.id.textViewUsername);
        mTextViewMorning = view.findViewById(R.id.textViewMorning);
        mTextViewAfternoon = view.findViewById(R.id.textViewAfternoon);
        mTextViewNight = view.findViewById(R.id.textViewNight);
        mTextViewBreakFast=view.findViewById(R.id.textViewBreakfast);
        mTextViewLunch=view.findViewById(R.id.textViewLunch);
        mTextViewDinner=view.findViewById(R.id.textViewDinner);

       //set Name
        mTextViewUsername.setText(SharedData.username);
        SharedData.setWelcomeMessage(mTextViewWelcomingMessage);
        //getUsernameFromDatabase();
        //get name


        //listener for LinearLayout
        mLinearLayoutForMorning.setOnClickListener(this);
        mLinearLayoutForAfternoon.setOnClickListener(this);
        mLinearLayoutForNight.setOnClickListener(this);
        mBreakFastCardview.setOnClickListener(this);
        mLunchCardView.setOnClickListener(this);
        mNightCardView.setOnClickListener(this);


//        mBreakFastCardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment fragment = new ShoppingFragment();
//                FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
//                transaction.replace(R.id.navigation_container, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//
//
//            }
//        });

        //Avatar listener
        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new UserProfileFragment();
                @SuppressLint("UseRequireInsteadOfGet") FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                transaction.replace(R.id.navigation_container, fragment);
                transaction.addToBackStack(null);
              //  transaction.disallowAddToBackStack();
                transaction.commit();

            }
        });



//        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);

//        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Coaches");
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    CoachesDataModel upload = postSnapshot.getValue(CoachesDataModel.class);
//                    dataList.add(upload);
//                }
//                adapter = new CoachesAdapter(getContext(), dataList);
//                recyclerView.setAdapter(adapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

        //   Populate data of first horizontal cardview


//        data = new ArrayList<CoachesDataModel>();
//        for (int i = 0; i < CoachesData.nameArray.length; i++) {
//            data.add(new CoachesDataModel(
//                    CoachesData.nameArray[i],
//                    CoachesData.imageArray[i],
//                    CoachesData.id_[i]
//
//            ));
//        }
//
//        adapter = new CoachesAdapter(data);
//        recyclerView.setAdapter(adapter);
    }

    private void getCoachesData() {
        Call<ArrayList<CoachesDataModel>> call = ApiClient.getService().getAllCoachesData();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<ArrayList<CoachesDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CoachesDataModel>> call, Response<ArrayList<CoachesDataModel>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {


                    dataList = response.body();
                    System.out.println(dataList+"sdsddsdd");


                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < dataList.size(); i++) {


                        adapter = new CoachesAdapter(getContext(),dataList);
                        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);

                    }



                }
            }

            @Override
            public void onFailure(Call<ArrayList<CoachesDataModel>> call, Throwable t) {
                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(getContext(), "Fail to get  Coaches data", Toast.LENGTH_SHORT).show();
            }
        });
    }

  //  private void getUsernameFromDatabase() {
        //   databaseReference = firebaseDatabase.getReference().child("Cedric").child("Users Data").child(uid);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (firebaseUser != null && snapshot.hasChildren()) {
//                    //get and save in string
//                    mTextViewUsername.setText(snapshot.child("username").getValue().toString());
//                   // mAvatar.setText(snapshot.child("userProfileImage").getValue().toString());
//                    try {
//                        Glide.with(getContext()).asBitmap()
//                                .load(snapshot.child("userProfileImage").getValue().toString()).into(mAvatar);
//                    } catch (Throwable throwable) {
//                        throwable.printStackTrace();
//
//                    }
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
  //  }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.linearLayoutForMorning) {
            dayTime="night";
            currentDate="2021-10-17";

            postVisualizationData(dayTime,currentDate);

//            DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric")
//                    .child("SleepVisualization").child("morning");
//
//            mDatabaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    // SleepVisualizationDataModel upload = postSnapshot.getValue(SleepVisualizationDataModel.class);
////                        dataList.add(upload);
//                    // audioUrl = postSnapshot.getValue(String.class);
//                    //imgUrl=postSnapshot.child("img").getValue(String.class);
//                    // imgUrl=upload.getImgUrl()
//                    //imgUrl= String.valueOf(Glide.with(getContext()).asBitmap().load(upload.getImgUrl()));
//                    // System.out.println(imgUrl  +"  hello by by");
//                    audioUrl = dataSnapshot.child("sngurl").getValue(String.class);
//                    System.out.println(audioUrl + "  hello hy by by");
//
//                    String textViewMorning = mTextViewMorning.getText().toString();
//                    mLinearLayoutDashboard.setVisibility(View.GONE);
//
//                    Fragment fragment = new SleepVisualizationFragment();
//                    FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Morning", textViewMorning); //key and value
//                    // bundle.putString("img", imgUrl);
//                    bundle.putString("audioUrl", audioUrl);
//                    fragment.setArguments(bundle);
//                    transaction.replace(R.id.fragmentContainer1, fragment);
//                    transaction.commit();
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });


        }
        if (v.getId() == R.id.linearLayoutForAfternoon) {
            dayTime="night";
            currentDate="2021-10-17";

            postVisualizationData(dayTime,currentDate);

//            DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric")
//                    .child("SleepVisualization").child("afternoon");
//
//            mDatabaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    audioUrl = dataSnapshot.child("sngurl").getValue(String.class);
//                    String textViewAfternoon = mTextViewAfternoon.getText().toString();
//                    mLinearLayoutDashboard.setVisibility(View.GONE);
//                    Fragment fragment = new SleepVisualizationFragment();
//                    FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Morning", textViewAfternoon); //key and value
//                    bundle.putString("audioUrl", audioUrl);
//                    fragment.setArguments(bundle);
//                    transaction.replace(R.id.fragmentContainer1, fragment);
//                    transaction.commit();
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
        }
        if (v.getId() == R.id.linearLayoutForNight) {
            dayTime="night";
            currentDate="2021-10-17";

            postVisualizationData(dayTime,currentDate);

//            DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric")
//                    .child("SleepVisualization").child("evening");
//
//            mDatabaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//
//                    audioUrl = dataSnapshot.child("sngurl").getValue(String.class);
//
//                    String textViewNight = mTextViewNight.getText().toString();
//                    mLinearLayoutDashboard.setVisibility(View.GONE);
//                    Fragment fragment = new SleepVisualizationFragment();
//                    FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Morning", textViewNight); //key and value
//                    bundle.putString("audioUrl", audioUrl);
//                    fragment.setArguments(bundle);
//                    transaction.replace(R.id.fragmentContainer1, fragment);
//                    transaction.commit();
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });


        }

        if (v.getId() == R.id.breakfastCardView) {

            nutritionTime=mTextViewBreakFast.getText().toString();
            String day="Monday";
            loadNutritionData(day,nutritionTime);
        }
        if (v.getId() == R.id.LunchCardView) {
            nutritionTime=mTextViewBreakFast.getText().toString();
            String day="Monday";
            loadNutritionData(day,nutritionTime);
        }
        if (v.getId() == R.id.DinnerCardView) {
            nutritionTime=mTextViewBreakFast.getText().toString();
            String day="Monday";
            loadNutritionData(day,nutritionTime);
        }
    }

    private void postVisualizationData(String dayTime, String currentDate) {

        Call<VisualizationResponse > call = ApiClient.getService().VisualizationDataPost(dayTime,currentDate);

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<VisualizationResponse>() {
            @Override
            public void onResponse(Call<VisualizationResponse> call, Response<VisualizationResponse> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {

                    VisualizationResponse visualizationResponse = response.body();

                    System.out.println(visualizationResponse.getDate()+"visuali");

                    mLinearLayoutDashboard.setVisibility(View.GONE);
                    Fragment fragment = new SleepVisualizationFragment();
                    FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("dayTime", visualizationResponse.getTime()); //key and value
                    bundle.putString("audioUrl", visualizationResponse.getMusicURL());
                    bundle.putString("imgUrl", visualizationResponse.getImageURL());
                    bundle.putString("visualizationTitle", visualizationResponse.getName());
                    bundle.putString("audioDescription", visualizationResponse.getDescription());
                    bundle.putString("audioDuration", visualizationResponse.getDuration());
                    bundle.putString("visualizationDate", visualizationResponse.getDate());
                    fragment.setArguments(bundle);
                    transaction.replace(R.id.navigation_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();




                    // below line we are running a loop to add data to our adapter class.




                }
            }

            @Override
            public void onFailure(Call<VisualizationResponse> call, Throwable t) {
                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(getContext(), "Fail to get  visualization data", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadNutritionData(String day ,String nutritionTime) {

        Call<NutritionDataModel> call = ApiClient.getService().nutritionDataPost(day,nutritionTime);

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<NutritionDataModel> () {
            @Override
            public void onResponse(Call<NutritionDataModel>  call, Response<NutritionDataModel>  response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {


                    NutritionDataModel nutritionDataModel=response.body();
                    List<String> arrayMethod=nutritionDataModel.method;

//                    List<NutritionDataModel.Ingredients> ingredsList= (nutritionDataModel.ingredients);
//                    System.out.println(ingredsList+"hello");

                    System.out.println(nutritionDataModel+"Are you there in nutrition");
                    Fragment fragment = new Nutrition_Fragment();
                    FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putString("nutritionName", nutritionDataModel.name);
                   // bundle.putString("cook", nCookStatus);
                    //bundle.putString("prepareTime", nPrepTime);
                    bundle.putString("nutritionImage", nutritionDataModel.imageURL);

                   bundle.putStringArrayList("method", (ArrayList<String>) arrayMethod);
                   bundle.putParcelable("ingredients",  nutritionDataModel);
                    //System.out.println(ingredsList +"llll");
                   // bundle.putParcelable("methods", (nutritionDataModel.method)); //key and value
                    fragment.setArguments(bundle);
                    transaction.replace(R.id.navigation_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            }

            @Override
            public void onFailure(Call<NutritionDataModel>  call, Throwable t) {
                // in the method of on failure we are displaying a
                // toast message for fail to get data.
                Toast.makeText(getContext(), "Fail to load  nutrition data", Toast.LENGTH_SHORT).show();
            }
        });



//        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Nutrition").child("BreakFast");
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//
//                    String nImage = postSnapshot.child("nImageUrl").getValue(String.class);
//                    String nname = postSnapshot.child("nName").getValue(String.class);
//                    String nCookStatus = postSnapshot.child("cook").getValue(String.class);
//                    String nPrepTime = postSnapshot.child("preparation").getValue(String.class);
//
//
//                    Fragment fragment = new Nutrition_Fragment();
//                    FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("nutritionName", nname);
//                    bundle.putString("cook", nCookStatus);
//                    bundle.putString("prepareTime", nPrepTime);
//                    bundle.putString("nutritionImage", nImage);//key and value
//
//
//                    System.out.println(nname + "==========");
//                    System.out.println(nPrepTime + "==========");
//                    System.out.println(nImage + "==========");
//                    //bundle.putString("audioUrl", audioUrl);
//                    fragment.setArguments(bundle);
//                    transaction.replace(R.id.navigation_container, fragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                    // mNutritionName.setText(nName);
//
//                    //CoachesDataModel upload = postSnapshot.getValue(CoachesDataModel.class);
//                    //dataList.add(upload);
//                }
////                adapter = new CoachesAdapter(getContext(), dataList);
////                recyclerView.setAdapter(adapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

//    class MyItemOnClickListener implements View.OnClickListener {
//        private final DashboardFragment context;
//
//
//        MyItemOnClickListener(DashboardFragment context) {
//            this.context = context;
//        }
//
//
//        @Override
//        public void onClick(View v) {
//            RecyclerView.ViewHolder viewHolder = recyclerView.findContainingViewHolder(v);
//            MaterialTextView textViewName
//                    = (MaterialTextView) viewHolder.itemView.findViewById(R.id.coachesName);
//            String name = textViewName.getText().toString();
//            mLinearLayoutDashboard.setVisibility(View.GONE);
//           // Intent intent = new Intent(context, VideoPlayerActivity.class);
//            //intent.putExtra("position",uploadCurrent.url);
////            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            context.startActivity(intent);
//
//            Fragment fragment = new CoachesExercisesFragment();
//
//            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
//            Bundle bundle = new Bundle();
//            bundle.putString("CoachName", name); //key and value
//            bundle.putInt("Image", data.get(viewHolder.getAdapterPosition()).getProfile_img());
//            //set Fragmentclass Arguments
//            fragment.setArguments(bundle);
//            fragmentTransaction.replace(R.id.fragmentContainer0, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//
//        }
}


