package com.mynotes.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.mynotes.mynotes.modelsadapter.NotesAdapters;
import com.mynotes.mynotes.modelsadapter.NotesModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<NotesModel> arrayList;
    NotesAdapters adapters;
    FloatingActionButton fab;
    View profileview;
    ImageView pimage , pclear;
    TextView ptextView;
    AlertDialog pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingActionButton);

        String img1 = "https://cdn.vox-cdn.com/thumbor/Pkmq1nm3skO0-j693JTMd7RL0Zk=/0x0:2012x1341/1200x800/filters:focal(0x0:2012x1341)/cdn.vox-cdn.com/uploads/chorus_image/image/47070706/google2.0.0.jpg";
        String img2 = "https://media.istockphoto.com/vectors/abstract-business-arrow-up-logo-icon-vector-design-template-vector-id1140553971?k=20&m=1140553971&s=612x612&w=0&h=7-a1JjWAf8c5qKF46FRgdFg-3jva9qo80hqh0z_Y34A=";

        fab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

        arrayList = new ArrayList<>();
        adapters = new NotesAdapters(arrayList,MainActivity.this);
        recyclerView.setAdapter(adapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        for (int i = 0 ; i<5 ; i++) {
            arrayList.add(new NotesModel("THis is Title", img1, img1));
            arrayList.add(new NotesModel("Github Best Libraries", img1));
            arrayList.add(new NotesModel("THis is Title", img2, img2));
            arrayList.add(new NotesModel("Github Best Libraries", "are ypu thin about me foe the next month so sad are very important no more than so lets go agead"));
            arrayList.add(new NotesModel("Gugietest.com username ansd password", "are ypu thin about me foe the next month so sad are very important no more than so lets go agead", img2));
            arrayList.add(new NotesModel("All github reporesitory", "are ypu thin about me foe the next month so sad are very important no more than so lets go agead", img1));
        }
        adapters.notifyDataSetChanged();

        LayoutInflater inflater = (LayoutInflater)   MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        profileview = inflater.inflate(R.layout.sample_profile, null);

         pimage = profileview.findViewById(R.id.idProfile);
         ptextView = profileview.findViewById(R.id.idName);
         pclear = profileview.findViewById(R.id.idClear);
         if (FirebaseAuth.getInstance().getCurrentUser() != null){
             String purl = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
             String pname = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
             Glide.with(MainActivity.this).load(purl)
                     .into(pimage);
             ptextView.setText(pname);
         }
         pdialog = new AlertDialog.Builder(MainActivity.this)
                 .setView(profileview).create();
         pdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

         pclear.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 pdialog.dismiss();
             }
         });

         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(MainActivity.this,AddNotes.class));
             }
         });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_menu,menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.app_bar_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), "submit", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String key) {
                Toast.makeText(getApplicationContext(),key, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuprofile:
                pdialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}