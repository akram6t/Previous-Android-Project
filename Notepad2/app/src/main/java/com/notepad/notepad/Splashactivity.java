package com.notepad.notepad;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashactivity extends AppCompatActivity {
    TextView appname ;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);
        appname = findViewById(R.id.textView);
        logo = findViewById(R.id.imageView);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        , WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        appname.animate().translationY(-500f).setDuration(1500).start();
        logo.animate().translationY(-500f).setDuration(1500).start();

        splash();

    }

    private void splash () {

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1500);
                    Intent intent = new Intent(Splashactivity.this, AuthActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}