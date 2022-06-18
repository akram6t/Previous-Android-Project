package com.inotes.inotes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inotes.inotes.adapterandmodels.NotesAdapters;
import com.inotes.inotes.adapterandmodels.NotesModel;
import com.inotes.inotes.sqllitedatabase.DBHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView dclose;
    Button addbutton;
    EditText edit_title , edit_notes;
    RecyclerView recyclerView;
    ArrayList<NotesModel> arrayList;
    NotesAdapters adapters;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingActionButton);

        fab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));

        arrayList = new ArrayList<>();
        DBHandler databa = new DBHandler(MainActivity.this);
        ArrayList<NotesModel> getlist = databa.getlist();

        adapters = new NotesAdapters(MainActivity.this,getlist);
        recyclerView.setAdapter(adapters);

        View dialogView;
        LayoutInflater inflater = (LayoutInflater)   MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dialogView = inflater.inflate(R.layout.notes_dialog, null);

        addbutton = dialogView.findViewById(R.id.dcreate);
        edit_title = dialogView.findViewById(R.id.dtitle);
        edit_notes = dialogView.findViewById(R.id.dnotes);
        dclose = dialogView.findViewById(R.id.dclose);

        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setCancelable(false)
                .setView(dialogView).create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.show();

           }
       });

       DBHandler database = new DBHandler(MainActivity.this);

       addbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
               String date = format.format(new Date());
               String title = edit_title.getText().toString();
               String notes = edit_notes.getText().toString();
               if (edit_title.getText().toString().trim().isEmpty()){
                   Toast.makeText(MainActivity.this, "please Enter Title", Toast.LENGTH_SHORT).show();
               }else if (edit_notes.getText().toString().trim().isEmpty()){
                   Toast.makeText(MainActivity.this, "please Enter Notes", Toast.LENGTH_SHORT).show();
               }else {
                   // work on it
                   boolean isInsert = database.inserdata(title,notes,date);
                   if (isInsert){
                       dialog.dismiss();
                       Toast.makeText(MainActivity.this, "Notes succesfully add", Toast.LENGTH_SHORT).show();
                       edit_title.setText("");
                       edit_notes.setText("");
                   }
               }

           }
       });

       dclose.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.dismiss();
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
}