package com.inotes.blogspot;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {
    Button btn_Download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // download btn findViewByID
        btn_Download = findViewById(R.id.btn_download);

        String file_name = "google.png";
        String icon_url = "https://cdn-icons-png.flaticon.com/512/281/281764.png";

        btn_Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // method execute
                MyDownloadManager(icon_url,file_name);
            }
        });

    }

    // All Download Manager Code in This Method
    private void MyDownloadManager(String url , String name){
       // implement 'com.karumi:dexter:6.2.3' in build.gradle module
        Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Uri downloadUri = Uri.parse(url);
                        DownloadManager manager = (DownloadManager)getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(downloadUri);

                        request.setAllowedNetworkTypes(
                                DownloadManager.Request.NETWORK_WIFI
                                        | DownloadManager.Request.NETWORK_MOBILE)
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setAllowedOverRoaming(false).setTitle(name)
                                .setDestinationInExternalPublicDir("MyFolderName", name);

                        manager.enqueue(request);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();

    }

}


