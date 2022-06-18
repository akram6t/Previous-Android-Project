package com.example.viewexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    String apiKey;
    ArrayList<Models> list;
    Adapters adapters;
    RecyclerView recyclerView;
    LinearLayout loadMore;
    int totalitemcount ;
    boolean isscrollchange = false;
    int currentItemcount, totalItem, scrolledItem;
    LinearLayoutManager manager;

    String load1 = "https://admin.codeforfree.in:443/api/userinfo/all?X-Api-Key=4984C1E79CC23D9A95A6D81C46F85878&filter=&field=&start=&limit=5&filters[0][co][3][fl]=id&filters[0][co][3][op]=greather&filters[0][co][3][vl]=0&filters[0][co][3][lg]=and&sort_field=&sort_order=ASC";
    String load2 = "https://admin.codeforfree.in:443/api/userinfo/all?X-Api-Key=4984C1E79CC23D9A95A6D81C46F85878&filter=&field=&start=&limit=5&filters[0][co][3][fl]=id&filters[0][co][3][op]=greather&filters[0][co][3][vl]=5&filters[0][co][3][lg]=and&sort_field=&sort_order=ASC";
    String load3 = "https://admin.codeforfree.in:443/api/userinfo/all?X-Api-Key=4984C1E79CC23D9A95A6D81C46F85878&filter=&field=&start=&limit=5&filters[0][co][3][fl]=id&filters[0][co][3][op]=greather&filters[0][co][3][vl]=10&filters[0][co][3][lg]=and&sort_field=&sort_order=ASC";
    String load4 = "https://admin.codeforfree.in:443/api/userinfo/all?X-Api-Key=4984C1E79CC23D9A95A6D81C46F85878&filter=&field=&start=&limit=5&filters[0][co][3][fl]=id&filters[0][co][3][op]=greather&filters[0][co][3][vl]=15&filters[0][co][3][lg]=and&sort_field=&sort_order=ASC";
    String load5 = "https://admin.codeforfree.in:443/api/userinfo/all?X-Api-Key=4984C1E79CC23D9A95A6D81C46F85878&filter=&field=&start=&limit=5&filters[0][co][3][fl]=id&filters[0][co][3][op]=greather&filters[0][co][3][vl]=20&filters[0][co][3][lg]=and&sort_field=&sort_order=ASC";
    String load6 = "https://admin.codeforfree.in:443/api/userinfo/all?X-Api-Key=4984C1E79CC23D9A95A6D81C46F85878&filter=&field=&start=&limit=5&filters[0][co][3][fl]=id&filters[0][co][3][op]=greather&filters[0][co][3][vl]=25&filters[0][co][3][lg]=and&sort_field=&sort_order=ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        loadMore = findViewById(R.id.loadinglayout);
        list = new ArrayList<>();
        totalitemcount = 5;

        adapters = new Adapters(list,MainActivity.this);
        recyclerView.setAdapter(adapters);
        manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);
        getData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isscrollchange = true;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItemcount = manager.getChildCount();
                scrolledItem = manager.findFirstVisibleItemPosition();
                totalItem = manager.getItemCount();

                if (isscrollchange && currentItemcount + scrolledItem == totalItem ){
                    loadMore.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,totalItem+"", Toast.LENGTH_SHORT).show();
                    getMoreData();
                }
            }
        });


    }

    private void getData(){

        String api = "https://admin.codeforfree.in:443/api/userinfo/all?X-Api-Key=4984C1E79CC23D9A95A6D81C46F85878&filter=&field=&start=&limit=5&filters[0][co][3][fl]=id&filters[0][co][3][op]=greather&filters[0][co][3][vl]=0&filters[0][co][3][lg]=and&sort_field=&sort_order=ASC";

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,api,
                new Response.Listener<String>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("status")){
                                JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("userinfo");

                                for (int i = 0; i<jsonArray.length();i++){
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    Models models = new Models(
                                            obj.getInt("id"),obj.getString("name"),obj.getString("email"),
                                            obj.getString("mobile"),obj.getString("password"),
                                            obj.getDouble("point"));
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
                Toast.makeText(MainActivity.this,error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

    }



    private void getLoadMoreData(){

        String api2 = "https://admin.codeforfree.in:443/api/userinfo/all?X-Api-Key=4984C1E79CC23D9A95A6D81C46F85878&filter=&field=&start=&limit=5&filters[0][co][3][fl]=id&filters[0][co][3][op]=greather&filters[0][co][3][vl]="+totalitemcount+"&filters[0][co][3][lg]=and&sort_field=&sort_order=ASC";

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api2,
                new Response.Listener<String>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("status")){
                                JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("userinfo");

                                for (int i = 0; i<jsonArray.length();i++){
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    Models models = new Models(
                                            obj.getInt("id"),obj.getString("name"),obj.getString("email"),
                                            obj.getString("mobile"),obj.getString("password"),
                                            obj.getDouble("point"));
                                    list.add(models);
                                    totalitemcount = totalitemcount + 5 ;
                                }
                                adapters.notifyDataSetChanged();
                                loadMore.setVisibility(View.GONE);
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
                Toast.makeText(MainActivity.this,error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

    }

    private String geturl() {
        if (totalItem == 1) {
            return load1;
        } else if (totalItem == 2) {
            return load2;
        } else if (totalItem == 3) {
            return load3;
        } else if (totalItem == 4) {
            return load4;
        } else if (totalItem == 5) {
            return load5;
        } else {
            return load6;
        }

    }

    private void getMoreData(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getLoadMoreData();
            }
        }, 1000);
    }

}