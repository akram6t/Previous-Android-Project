package com.allncertbook.blogging;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.allncertbook.blogging.databinding.ActivityMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    FirebaseFirestore database;
    ArrayList<Models> list;
    RADAPter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseFirestore.getInstance();

        list = new ArrayList<>();

        adapter = new RADAPter(list, MainActivity.this);
        binding.recyc.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        binding.recyc.setLayoutManager(llm);







    }

}