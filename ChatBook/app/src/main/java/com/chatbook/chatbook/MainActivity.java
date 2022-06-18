package com.chatbook.chatbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.chatbook.chatbook.adapters.MainAdapters;
import com.chatbook.chatbook.models.ProfileModels;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MainAdapters adapters;
    ArrayList<ProfileModels> list = new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseReference reference;
    FirebaseDatabase database;
    CircularProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainrecycler);
        circularProgressBar = findViewById(R.id.circularProgressBar);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");

        circularProgressBar.setProgressWithAnimation(100f, 3000L);

        MainAdapters adapters = new MainAdapters(list,MainActivity.this);

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
             for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                 String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                 ProfileModels models = dataSnapshot.getValue(ProfileModels.class);
                 if (!uid.equals(models.getUid())) {
                     list.add(models);
                 }
             }
             circularProgressBar.setVisibility(View.GONE);
             adapters.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenubar,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}