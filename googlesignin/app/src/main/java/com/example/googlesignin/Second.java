package com.example.googlesignin;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.googlesignin.databinding.ActivitySecondBinding;

public class Second extends AppCompatActivity {
    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}