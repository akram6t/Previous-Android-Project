package com.example.example1;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ImageView imageView;
    TextView prev,next;
    int posit = 0;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        imageView = findViewById(R.id.imageView);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);

        Dexter.withContext(MainActivity.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        ArrayList<File> mySongs = fetchFile(Environment.getExternalStorageDirectory());
                        String [] item = new String[mySongs.size()];
                        for (int i = 0 ;i<mySongs.size();i++){

                            item[i] = mySongs.get(i).getName();

                        }

                                prev.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (posit != 0){
                                            posit  = posit - 1 ;
                                        }else {
                                            posit = mySongs.size() - 1 ;
                                        }
                                        Uri uri = Uri.parse(mySongs.get(posit).toString());
                                        imageView.setImageURI(uri);

                                    }
                                });
                                next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (posit != mySongs.size() - 1){
                                            posit  = posit + 1 ;

                                        }else {
                                            posit = 0 ;

                                        }

                                        Uri uri = Uri.parse(mySongs.get(posit).toString());
                                        imageView.setImageURI(uri);

                                    }
                                });

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,item);
                        listView.setAdapter(arrayAdapter);

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


    public ArrayList<File> fetchFile(File file) {
        ArrayList arrayList = new ArrayList();
        File[] files = file.listFiles();

        if (files != null) {
            for (File myfile : files) {
                if (myfile.isDirectory()) {
                    arrayList.addAll(fetchFile(myfile));
                }else {
                    if (myfile.getPath().contains("lachh")){
                        arrayList.add(myfile);

                    }

                }
            }
        }
        return arrayList;
    }


}