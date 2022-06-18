package com.example.example3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;
import com.example.example3.databinding.ActivityMainBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RecyclerView recyclerView;
    public static VideoView videoView;
    public static ArrayList<File> myvideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = findViewById(R.id.recycler);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(MainActivity.this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"red\">" + "Video X" + "</font>"));

        ArrayList<Models> list = new ArrayList<>();

        Dexter.withContext(MainActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        myvideos = fetchVideos(Environment.getExternalStorageDirectory());

                        for (int i = 0 ; i<myvideos.size() ; i++){
                            list.add(new Models(myvideos.get(i).getName().replace(".mp4","")));

                        }

                        CustomAdapter adapter = new CustomAdapter(list,MainActivity.this);
                        recyclerView.setAdapter(adapter);

                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();

    }

    public ArrayList<File> fetchVideos(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File [] files = file.listFiles();
        if (files != null){
            for (File myfile : files){
                if (myfile.isDirectory()){
                    arrayList.addAll(fetchVideos(myfile));
                }else {
                    if (myfile.getName().endsWith(".mp4") || myfile.getName().endsWith(".MP4")){
                        arrayList.add(myfile);
                    }
                }
            }
        }
        return arrayList;
    }

}