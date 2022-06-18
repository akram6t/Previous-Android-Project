package com.git.androiduigit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.git.androiduigit.adapterg.Gadapter;
import com.git.androiduigit.databinding.ActivityHomeaBinding;
import com.git.androiduigit.model.Gmodels;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.util.ArrayList;
public class Homea extends AppCompatActivity {
    ActivityHomeaBinding binding;
    FirebaseFirestore database;
    ArrayList<Gmodels> alist;
    Gadapter gadapter;
    String adid = "205988465";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseFirestore.getInstance();


        StartAppSDK.init(this,adid,true);



        alist = new ArrayList<>();

        gadapter = new Gadapter(alist,Homea.this);
        binding.recyc.setAdapter(gadapter);

        database.collection("Gits").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value,FirebaseFirestoreException error) {
                alist.clear();

                for (DocumentSnapshot snapshot: value.getDocuments()){
                    Gmodels gmodels = snapshot.toObject(Gmodels.class);
                    alist.add(gmodels);

                }
                gadapter.notifyDataSetChanged();

            }
        });

        binding.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.all.setBackgroundResource(R.drawable.topbackbaad);
                binding.textview.setBackgroundResource(R.drawable.topback);
                binding.imageview.setBackgroundResource(R.drawable.topback);
                binding.navigation.setBackgroundResource(R.drawable.topback);
                binding.scrollview.setBackgroundResource(R.drawable.topback);
                binding.layout.setBackgroundResource(R.drawable.topback);
                binding.other.setBackgroundResource(R.drawable.topback);
                binding.button.setBackgroundResource(R.drawable.topback);

                database.collection("Gits").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot value,FirebaseFirestoreException error) {
                        alist.clear();

                        for (DocumentSnapshot snapshot: value.getDocuments()){
                            Gmodels gmodels = snapshot.toObject(Gmodels.class);
                            alist.add(gmodels);

                        }
                        gadapter.notifyDataSetChanged();

                    }
                });

            }
        });

        binding.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(getApplicationContext());
                binding.button.setBackgroundResource(R.drawable.topback);
                binding.all.setBackgroundResource(R.drawable.topback);
                binding.textview.setBackgroundResource(R.drawable.topbackbaad);
                binding.imageview.setBackgroundResource(R.drawable.topback);
                binding.navigation.setBackgroundResource(R.drawable.topback);
                binding.scrollview.setBackgroundResource(R.drawable.topback);
                binding.layout.setBackgroundResource(R.drawable.topback);
                binding.other.setBackgroundResource(R.drawable.topback);
                database.collection("Gits")
                        .whereEqualTo("type", "textview")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                              alist.clear();
                                for (DocumentSnapshot snapshot: value.getDocuments()){
                                    Gmodels gmodels = snapshot.toObject(Gmodels.class);
                                    alist.add(gmodels);

                                }
                                gadapter.notifyDataSetChanged();

                            }
                        });
            }
        });

        binding.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(getApplicationContext());
                binding.button.setBackgroundResource(R.drawable.topback);
                binding.all.setBackgroundResource(R.drawable.topback);
                binding.textview.setBackgroundResource(R.drawable.topback);
                binding.imageview.setBackgroundResource(R.drawable.topbackbaad);
                binding.navigation.setBackgroundResource(R.drawable.topback);
                binding.scrollview.setBackgroundResource(R.drawable.topback);
                binding.layout.setBackgroundResource(R.drawable.topback);
                binding.other.setBackgroundResource(R.drawable.topback);
                database.collection("Gits")
                        .whereEqualTo("type", "imageview")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                                alist.clear();
                                for (DocumentSnapshot snapshot: value.getDocuments()){
                                    Gmodels gmodels = snapshot.toObject(Gmodels.class);
                                    alist.add(gmodels);

                                }
                                gadapter.notifyDataSetChanged();

                            }
                        });
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(getApplicationContext());
                binding.button.setBackgroundResource(R.drawable.topbackbaad);
                binding.all.setBackgroundResource(R.drawable.topback);
                binding.textview.setBackgroundResource(R.drawable.topback);
                binding.imageview.setBackgroundResource(R.drawable.topback);
                binding.navigation.setBackgroundResource(R.drawable.topback);
                binding.scrollview.setBackgroundResource(R.drawable.topback);
                binding.layout.setBackgroundResource(R.drawable.topback);
                binding.other.setBackgroundResource(R.drawable.topback);
                database.collection("Gits")
                        .whereEqualTo("type", "button")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                                alist.clear();
                                for (DocumentSnapshot snapshot: value.getDocuments()){
                                    Gmodels gmodels = snapshot.toObject(Gmodels.class);
                                    alist.add(gmodels);

                                }
                                gadapter.notifyDataSetChanged();

                            }
                        });

            }
        });

        binding.navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(getApplicationContext());
                binding.button.setBackgroundResource(R.drawable.topback);
                binding.all.setBackgroundResource(R.drawable.topback);
                binding.textview.setBackgroundResource(R.drawable.topback);
                binding.imageview.setBackgroundResource(R.drawable.topback);
                binding.navigation.setBackgroundResource(R.drawable.topbackbaad);
                binding.scrollview.setBackgroundResource(R.drawable.topback);
                binding.layout.setBackgroundResource(R.drawable.topback);
                binding.other.setBackgroundResource(R.drawable.topback);
                database.collection("Gits")
                        .whereEqualTo("type", "navigation")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                                alist.clear();
                                for (DocumentSnapshot snapshot: value.getDocuments()){
                                    Gmodels gmodels = snapshot.toObject(Gmodels.class);
                                    alist.add(gmodels);

                                }
                                gadapter.notifyDataSetChanged();

                            }
                        });
            }
        });

        binding.scrollview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(getApplicationContext());
                binding.button.setBackgroundResource(R.drawable.topback);
                binding.all.setBackgroundResource(R.drawable.topback);
                binding.textview.setBackgroundResource(R.drawable.topback);
                binding.imageview.setBackgroundResource(R.drawable.topback);
                binding.navigation.setBackgroundResource(R.drawable.topback);
                binding.scrollview.setBackgroundResource(R.drawable.topbackbaad);
                binding.layout.setBackgroundResource(R.drawable.topback);
                binding.other.setBackgroundResource(R.drawable.topback);
                database.collection("Gits")
                        .whereEqualTo("type", "scrollview")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                                alist.clear();
                                for (DocumentSnapshot snapshot: value.getDocuments()){
                                    Gmodels gmodels = snapshot.toObject(Gmodels.class);
                                    alist.add(gmodels);

                                }
                                gadapter.notifyDataSetChanged();

                            }
                        });
            }
        });

        binding.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(getApplicationContext());
                binding.button.setBackgroundResource(R.drawable.topback);
                binding.all.setBackgroundResource(R.drawable.topback);
                binding.textview.setBackgroundResource(R.drawable.topback);
                binding.imageview.setBackgroundResource(R.drawable.topback);
                binding.navigation.setBackgroundResource(R.drawable.topback);
                binding.scrollview.setBackgroundResource(R.drawable.topback);
                binding.layout.setBackgroundResource(R.drawable.topbackbaad);
                binding.other.setBackgroundResource(R.drawable.topback);
                database.collection("Gits")
                        .whereEqualTo("type", "layout")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                                alist.clear();
                                for (DocumentSnapshot snapshot: value.getDocuments()){
                                    Gmodels gmodels = snapshot.toObject(Gmodels.class);
                                    alist.add(gmodels);

                                }
                                gadapter.notifyDataSetChanged();

                            }
                        });
            }
        });

        binding.other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartAppAd.showAd(getApplicationContext());
                binding.button.setBackgroundResource(R.drawable.topback);
                binding.all.setBackgroundResource(R.drawable.topback);
                binding.textview.setBackgroundResource(R.drawable.topback);
                binding.imageview.setBackgroundResource(R.drawable.topback);
                binding.navigation.setBackgroundResource(R.drawable.topback);
                binding.scrollview.setBackgroundResource(R.drawable.topback);
                binding.layout.setBackgroundResource(R.drawable.topback);
                binding.other.setBackgroundResource(R.drawable.topbackbaad);
                database.collection("Gits")
                        .whereEqualTo("type", "other")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                                alist.clear();
                                for (DocumentSnapshot snapshot: value.getDocuments()){
                                    Gmodels gmodels = snapshot.toObject(Gmodels.class);
                                    alist.add(gmodels);

                                }
                                gadapter.notifyDataSetChanged();

                            }
                        });
            }
        });




    }
}