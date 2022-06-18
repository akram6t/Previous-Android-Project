package com.example.myapitest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImgViewActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_view);
        getSupportActionBar().hide();
        imageView = findViewById(R.id.imageView);

        String getimg = getIntent().getStringExtra("img");
        Glide.with(ImgViewActivity.this)
                .load(getimg).into(imageView);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}