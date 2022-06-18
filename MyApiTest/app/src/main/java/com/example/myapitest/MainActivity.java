package com.example.myapitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<GModels> list;
    GAdapters adapters;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myrecyclerView);

        list = new ArrayList<>();
        adapters = new GAdapters(MainActivity.this,list);
        recyclerView.setAdapter(adapters);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

        getgrocery();

    }

    void getgrocery(){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://admin.codeforfree.in/api/grocery/all?X-Api-Key=DE90B49F9F8DCBAE8C78C5902215251A&filter=&field=&start=&limit=&filters[0][co][0][fl]=&filters[0][co][0][op]=equal&filters[0][co][0][vl]=&filters[0][co][0][lg]=and&sort_field=&sort_order=ASC";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("status")){
                        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("grocery");

                        for (int i = 0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            GModels models = new GModels(object.getInt("id")
                                    ,object.getString("name") , object.getString("image") ,
                                    object.getString("price"));
                            list.add(models);
                        }
                        adapters.notifyDataSetChanged();
                    }else {
                        Toast.makeText(MainActivity.this, "not status true", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

}