package com.example.examle2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.examle2.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    ArrayList<Models> list;
    BAdapter bAdapter;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("BookApp");
        database = FirebaseFirestore.getInstance();

        list = new ArrayList<>();

        bAdapter = new BAdapter(list,MainActivity.this);
        binding.recyclerView.setAdapter(bAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        binding.recyclerView.setLayoutManager(llm);

        database.collection("PDF").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                list.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()){
                    Models models = snapshot.toObject(Models.class);
                    list.add(models);
                }
                bAdapter.notifyDataSetChanged();

            }
        });

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PostAc.class);
                startActivity(intent);

            }
        });

    }
}