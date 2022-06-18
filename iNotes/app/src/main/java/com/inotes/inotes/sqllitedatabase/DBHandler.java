package com.inotes.inotes.sqllitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.inotes.inotes.adapterandmodels.NotesModel;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    final static String dbname =  "mynotes.db";
    final static int dbvirsion = 1;

    public DBHandler(@Nullable Context context) {
        super(context, dbname, null, dbvirsion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table notes" +
                        "(id integer primary key autoincrement," +
                        "title text," +
                        "notes text," +
                        "date text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists notes");
        onCreate(sqLiteDatabase);
    }
    public boolean inserdata(String title , String notes , String date){
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        values.put("notes",notes);
        values.put("date",date);
        long id = database.insert("notes",null,values);

        if (id<=0){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<NotesModel> getlist(){
        ArrayList<NotesModel> arrayList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select id,title,notes,date from notes",null);

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                NotesModel model = new NotesModel();
                model.setId(cursor.getInt(0));
                model.setTitle(cursor.getString(1));
                model.setDescription(cursor.getString(2));
                model.setDate(cursor.getString(3));
                arrayList.add(model);
            }
        }
            cursor.close();
            database.close();
            return arrayList;
    }
}
