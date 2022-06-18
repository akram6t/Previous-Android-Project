package com.notepad.dbdemo.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.notepad.dbdemo.SQLModels.SqlModels;
import com.notepad.dbdemo.SQLParams.SqlParams;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context) {
        super(context, SqlParams.DB_NAME, null, SqlParams.DB_Virsion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create = "CREATE TABLE " + SqlParams.TABLE_NAME + "( "
                +SqlParams.KEY_ID + "INTEGER PRIMARY KEY, " + SqlParams.ITEM
                + "TEXT," + SqlParams.ITEM_PRIZE + "TEXT" + " )";

        sqLiteDatabase.execSQL(create);
        Log.d("sqllitedatabase", "query being runing: " + create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addgrocery(SqlModels models){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SqlParams.ITEM,models.getName());
        values.put(SqlParams.ITEM_PRIZE,models.getPrize());

        db.insert(SqlParams.TABLE_NAME,null,values);
        Log.d("dbakram", "addgrocery: " + "successfully Insert");
        db.close();
    }

    public ArrayList<SqlModels> fetchGrocery(){
        ArrayList<SqlModels> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + SqlParams.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);

        if (cursor.moveToFirst()){
            do {
                SqlModels models = new SqlModels();
                models.setId(Integer.parseInt(cursor.getString(0)));
                models.setName(cursor.getString(1));
                models.setPrize(cursor.getString(2));
                arrayList.add(models);
            }while (cursor.moveToNext());
        }

        return arrayList;
    }

}
