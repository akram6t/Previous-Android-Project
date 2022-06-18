package com.chatbook.chatbook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        doinbackround();
    }

    private void doinbackround() {
        Thread thread = new Thread(){
            public void run(){
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashAct.this,PhoneVerify.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}