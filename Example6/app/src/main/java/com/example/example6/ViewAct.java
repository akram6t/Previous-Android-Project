package com.example.example6;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class ViewAct extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        imageView = findViewById(R.id.imageView);
        int position = getIntent().getIntExtra("pos",0);
        ArrayList<File> myapkimg = MainActivity.myimg;
        Uri uri = Uri.parse(myapkimg.get(position).toURI().toString());
        imageView.setImageURI(uri);

    }
}