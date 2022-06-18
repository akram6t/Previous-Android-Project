package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Mmodels> list;
    ArrayList<File> mysongs;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        Dexter.withContext(MainActivity.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        mysongs = fetchsongs(Environment.getExternalStorageDirectory());

                        for (int i=0;i<mysongs.size();i++){
                            list.add(new Mmodels(mysongs.get(i).getName().replace(".mp3","")));
                        }
                        MAdapter adapter = new MAdapter(list,MainActivity.this);
                        recyclerView.setAdapter(adapter);

                        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(llm);

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

    public ArrayList<File> fetchsongs(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File [] files = file.listFiles();

        for (File myfile : files){
            if (myfile.isDirectory()){
                arrayList.addAll(fetchsongs(myfile));
            }else {
                if (myfile.getName().endsWith(".mp3")){
                    arrayList.add(myfile);
                }
            }
        }

        return arrayList;
    }
}