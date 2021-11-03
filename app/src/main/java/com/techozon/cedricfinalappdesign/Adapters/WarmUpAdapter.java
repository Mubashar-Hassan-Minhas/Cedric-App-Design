//package com.example.cedricfinalappdesign.Adapters;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.media.MediaMetadataRetriever;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cedricfinalappdesign.Model.CoachesDataModel;
//import com.example.cedricfinalappdesign.R;
//import com.example.cedricfinalappdesign.Model.VideoModel;
//import com.google.android.material.textview.MaterialTextView;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class WarmUpAdapter extends RecyclerView.Adapter<VideoHolder> {
//
//    private Context context;
//    //ArrayList<File> videoArrayList;
//    private final List<VideoModel> mUploads;
//    private ImageView imageThumbnail;
//    private Bitmap bitmap;
//    String exercise;
//
////    public WarmUpAdapter(Context context, List<VideoModel> uploads) {
////        this.context = context;
////        this.mUploads = uploads;
////    }
//    public WarmUpAdapter(ArrayList<VideoModel> uploads)
//    {
//        this.mUploads = uploads;
//    }
//
//
////    public MyAdapter(Context context, List<filemodel> videoArrayList) {
////        this.context = context;
////        this.videoArrayList = videoArrayList;
////    }
//
//    @NotNull
//    @Override
//    public VideoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.warm_up, viewGroup, false);
//        return new VideoHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final VideoHolder videoHolder, int position) {
//        VideoModel uploadCurrent = mUploads.get(position);
////
//        videoHolder.txtFileName.setText(uploadCurrent.getTitle());
//
//        try {
//            bitmap = retrieveVideoFrameFromVideo(uploadCurrent.getUrl());
//            if (bitmap != null) {
//                videoHolder.imageThumbnail.setImageBitmap(bitmap);
//            }
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//
////       try {
////
////
////            Bitmap bitmapThumbnail = ThumbnailUtils.createVideoThumbnail(uploadCurrent.getUrl(),
////                    MediaStore.Images.Thumbnails.MINI_KIND);
////            videoHolder.imageThumbnail.setImageBitmap(bitmapThumbnail);
////
////
////           //long interval = position *1000;
//////          RequestOptions requestOptions = new RequestOptions();
//////         Glide.with(context).setDefaultRequestOptions(requestOptions).load(uploadCurrent.getUrl()).into(videoHolder.imageThumbnail);
////       }
////        catch (Exception e){
////            e.printStackTrace();
////            Log.e(String.valueOf(e),"error");
////        }
//        videoHolder.mWarmUpLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                exercise = videoHolder.txtFileName.getText().toString();
////
////                Intent intent = new Intent(context, ExerciseDetails.class);
////                intent.putExtra("exercise", exercise);
////                intent.putExtra("position",uploadCurrent.getUrl());
////                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(intent);
//            }
//        });
//    }
//
//
//    public static Bitmap retrieveVideoFrameFromVideo(String videoPath) throws Throwable {
//        Bitmap bitmap;
//        MediaMetadataRetriever mediaMetadataRetriever = null;
//        try {
//            mediaMetadataRetriever = new MediaMetadataRetriever();
//            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
//            //mediaMetadataRetriever.setDataSource(videoPath);
//            bitmap = mediaMetadataRetriever.getFrameAtTime();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Throwable("Exception in retrieveVideoFrameFromVideo(String videoPath)" + e.getMessage());
//
//        } finally {
//            if (mediaMetadataRetriever != null) {
//                mediaMetadataRetriever.release();
//            }
//        }
//        return bitmap;
//    }
//
//
//    @Override
//    public int getItemCount() {
//        if (mUploads.size() > 0) {
//            return mUploads.size();
//        } else
//            return 1;
//    }
//}
//
//class VideoHolder extends RecyclerView.ViewHolder {
//
//    MaterialTextView txtFileName;
//    ImageView imageThumbnail;
//    MaterialTextView mVideoTimer;
//    LinearLayout mWarmUpLinearLayout;
//
//    VideoHolder(View view) {
//        super(view);
//
//        txtFileName = view.findViewById(R.id.textViewWarmUP);
//        mVideoTimer = view.findViewById(R.id.textViewWarmUpTime);
//        imageThumbnail = view.findViewById(R.id.imageWarmUp);
//        mWarmUpLinearLayout = view.findViewById(R.id.warmUpLinearLayout);
//
//
//    }
//}
//
//
//
