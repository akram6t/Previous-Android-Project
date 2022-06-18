package com.inotes.inotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        textView.animate().translationY(-600).setDuration(1500).start();
        imageView.animate().translationY(-600).setDuration(1500).start();


        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    // splash screen timeout 1 seconds = 1000 milliSecond
                    Thread.sleep(1500);
                    // after timeOut this work
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // thread start
        thread.start();
    }
}