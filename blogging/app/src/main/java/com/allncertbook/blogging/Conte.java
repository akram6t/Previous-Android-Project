package com.allncertbook.blogging;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.allncertbook.blogging.databinding.ActivityConteBinding;

public class Conte extends AppCompatActivity {
    ActivityConteBinding binding;
    Uri profile , image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent , 10);

            }
        });

        binding.imagevi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte  = new Intent();
                inte.setType("image/*");
                inte.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(inte , 20);

            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (profile != null && image != null){



                }else {
                    Toast.makeText(Conte.this, "please select image", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10){
            profile = data.getData();
            binding.profile.setImageURI(profile);
        }else {
            image = data.getData();
            binding.imagevi.setImageURI(image);
        }
    }
}