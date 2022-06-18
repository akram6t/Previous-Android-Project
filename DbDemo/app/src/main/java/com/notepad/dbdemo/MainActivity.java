package com.notepad.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.notepad.dbdemo.DBHandler.DBHandler;
import com.notepad.dbdemo.SQLModels.SqlModels;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] name = {"papaya","jamun","mirchi","bhate","aalu"};
        String [] prize = {"50","60","200","10","20"};

        DBHandler db = new DBHandler(MainActivity.this);

        for (int i = 0 ; i<name.length ; i++){
            SqlModels models = new SqlModels(name[i],prize[i]);
            db.addgrocery(models);
            Log.d("dbdata", "onCreate: " + db.getReadableDatabase());
        }

        ArrayList<SqlModels> list = new ArrayList<>();

        ArrayList<SqlModels> allgrocery = db.fetchGrocery();
        for (SqlModels models:allgrocery){
            Log.d("readdata", "grocery is: " + models.getId()+" , "+
                   models.getName()+" , " + models.getPrize());

        }

    }

}