package com.example.arbazkhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.arbazkhan.databinding.ActivityPhotoSeeBinding;

public class PhotoSee extends AppCompatActivity {
    ActivityPhotoSeeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoSeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String picture = getIntent().getStringExtra("picture");

        Glide.with(PhotoSee.this).load(picture).placeholder(R.drawable.status).into(binding.zoom);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}