package com.codexproject.codewithui;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;
import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;
import com.codexproject.codewithui.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                                ,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding.mainlogo.animate().translationY(-200f).setDuration(2000).start();
        binding.imageView3.animate().rotationY(360f).setDuration(2000).start();

        AlertDialog dialog = new AlertDialog.Builder(SplashActivity.this)
                .setMessage("Please Turn ON your Internet Connection")
                        .setIcon(R.drawable.icon_offdata).setCancelable(false)
                .setTitle("Turn ON Internet").setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();



        Thread splash = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    Tovuti.from(SplashActivity.this).monitor(new Monitor.ConnectivityListener() {
                        @Override
                        public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast) {
                            if (isConnected){
                                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                dialog.show();
                            }
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        splash.start();

    }
}