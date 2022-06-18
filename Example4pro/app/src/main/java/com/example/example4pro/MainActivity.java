package com.example.example4pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<PdfModels> list;
    PdfAdapter pdfAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        list.add(new PdfModels("margiues dnfkdn dnkfdkf"));
        list.add(new PdfModels("margiues dkvkdsngkvsdgnvsd dnkfdkf"));
        list.add(new PdfModels("margiues dnkn dnkfdkf"));
        list.add(new PdfModels(" dnfkdn dnkfdkf"));
        list.add(new PdfModels("margiues"));
        list.add(new PdfModels("margiues dnfkdn "));
        list.add(new PdfModels("dmf dnfkdn dnkfdkf"));
        list.add(new PdfModels("all pdf dnfkdn dnkfdkf"));

        pdfAdapter = new PdfAdapter(list,MainActivity.this);
        recyclerView.setAdapter(pdfAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,3);
        recyclerView.setLayoutManager(layoutManager);


    }
}