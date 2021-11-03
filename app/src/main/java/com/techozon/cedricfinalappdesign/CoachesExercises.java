//package com.techozon.cedricfinalappdesign;
//
//
//import android.content.Context;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.techozon.cedricfinalappdesign.Adapters.MyAdapter;
//import com.techozon.cedricfinalappdesign.Model.VideoModel;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class CoachesExercises extends AppCompatActivity {
//
//    RecyclerView myRecyclerView;
//    MyAdapter myAdapter;
//    public static int REQUEST_PERMISSION = 1;
//    //File directory;
//    //boolean aBoolean_permission;
//    public static ArrayList<VideoModel> fileArrayList= new ArrayList<>();
//    //private boolean bolean_permission;
//    private DatabaseReference mDatabaseRef;
//
//    private Context context;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_coaches_exercises);
//        FirebaseApp.initializeApp(context);
//
//
//
//        myRecyclerView = findViewById(R.id.recyclerviewWarmUp);
//        myRecyclerView.setHasFixedSize(true);
//        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        // mUploads = new ArrayList<>();
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Cedric").child("Workouts Videos");
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    VideoModel upload = postSnapshot.getValue(VideoModel.class);
//                    fileArrayList.add(upload);
//                }
//                //myAdapter = new MyAdapter(CoachesExercises.this, fileArrayList);
//               // myRecyclerView.setAdapter(myAdapter);
//
//
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(CoachesExercises.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//        //directory = new File("/mnt/"); //phone and SD card
//
//
//
//        //GridLayoutManager manager = new GridLayoutManager(this,2);
////        LinearLayoutManager manager = new LinearLayoutManager(this);
////        myRecyclerView.setLayoutManager(manager);
//
//
//        //PermissionForVideo();
//
//
//    }
//
////    private void PermissionForVideo()
////    {
////     if (ContextCompat.checkSelfPermission(getApplicationContext(),
////             Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
////     {
////         if ((ActivityCompat.shouldShowRequestPermissionRationale(this,
////                 Manifest.permission.READ_EXTERNAL_STORAGE))){
////
////         }
////         else {
////             ActivityCompat.requestPermissions(MainActivity.this, new String[] {
////                     Manifest.permission.READ_EXTERNAL_STORAGE
////             },REQUEST_PERMISSION);
////         }
////     }
////     else {
////         aBoolean_permission = true;
////         getFile(directory);
////         myAdapter = new MyAdapter(getApplicationContext(),fileArrayList);
////         myRecyclerView.setAdapter(myAdapter);
////     }
////    }
//
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////
////        if (requestCode == REQUEST_PERMISSION)
////        {
////            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
////            {
////                aBoolean_permission = true;
////                getFile(directory);
////                myAdapter = new MyAdapter(getApplicationContext(),fileArrayList);
////                myRecyclerView.setAdapter(myAdapter);
////            }
////            else {
////                Toast.makeText(this, "Need Permissions", Toast.LENGTH_SHORT).show();
////            }
////        }
////    }
//
////    public ArrayList<File> getFile(File directory){
////
////        File listFile[] = directory.listFiles();
////        if(listFile!=null && listFile.length>0){
////
////            for(int i = 0;i<listFile.length;i++){
////
////                if(listFile[i].isDirectory()){
////
////                    getFile(listFile[i]);
////
////                }
////
////                else{
////
////                    bolean_permission = false;
////                    if(listFile[i].getName().endsWith(".mp4")){
////
////                        for(int j=0;j<fileArrayList.size();j++){
////
////                            if(fileArrayList.get(j).getName().equals(listFile[i].getName())){
////
////                                bolean_permission = true;
////
////
////                            }else{
////
////                            }
////
////                        }
////
////                        if(bolean_permission){
////                            bolean_permission =false;
////                        }
////                        else{
////                            fileArrayList.add(listFile[i]);
////                        }
////
////                    }
////
////
////                }
////
////
////            }
////
////
////        }
////        return fileArrayList;
////    }
//}
