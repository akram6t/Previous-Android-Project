package com.example.example6;

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
    RecyclerView recyclerView;
    public static ArrayList<File> myimg;
    public static ArrayList<Models> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);

        Dexter.withContext(MainActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        myimg = fetchimage(Environment.getExternalStorageDirectory());
                        list = new ArrayList<>();
                        for (int i = 0 ; i<myimg.size() ; i++){
                            list.add(new Models(myimg.get(i).getName()));
                        }
                        CAdapter adapter = new CAdapter(list,MainActivity.this);
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

    public ArrayList<File> fetchimage(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File [] files = file.listFiles();
        for (File myfiles : files){
            if (myfiles.isDirectory()){
                arrayList.addAll(fetchimage(myfiles));
            }else {
                if (myfiles.getName().contains(".apk")){
                    arrayList.add(myfiles);
                }
            }
        }

        return arrayList;

    }
}