package com.example.apiweb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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
    ArrayList<GroceryModels> list;
    GroceryAdapters adapters;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        list = new ArrayList<>();
        GridLayoutManager glm = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(glm);
        adapters = new GroceryAdapters(MainActivity.this,list);
        recyclerView.setAdapter(adapters);
        getgrocery();
    }

    void getgrocery(){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://codeforfree.in:443/myadmin/api/grocery/all?X-Api-Key=DE90B49F9F8DCBAE8C78C5902215251A&filter=&field=&start=&limit=&filters[0][co][0][fl]=&filters[0][co][0][op]=equal&filters[0][co][0][vl]=&filters[0][co][0][lg]=and&sort_field=&sort_order=ASC";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("status")){
                        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("grocery");

                        for (int i = 0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            GroceryModels models = new GroceryModels(object.getInt("id")
                            ,object.getString("name") , object.getString("image") ,
                                    object.getDouble("price"));
                            list.add(models);
                        }
                        adapters.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
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
                if (error != null){
                    Toast.makeText(MainActivity.this,error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        queue.add(stringRequest);
    }
}