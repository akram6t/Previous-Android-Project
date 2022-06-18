package com.example.pdfviewwebview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pdfviewwebview.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String url = "https://admin.codeforfree.in/shayari/attitudeshayari.json";

        arrayList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });




        try {
            InputStream inputStream = getAssets().open("attitudeshayari.json");
            int size = inputStream.available();
            byte [] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            String json = new String(bytes , "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("atshayari");

            for (int i = 0 ; i<jsonArray.length() ; i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                arrayList.add(obj.getString("id") + "\n" + obj.getString("p"));
            }
            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this , android.R.layout.simple_list_item_1 , arrayList);
            binding.listView.setAdapter(adapter);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}