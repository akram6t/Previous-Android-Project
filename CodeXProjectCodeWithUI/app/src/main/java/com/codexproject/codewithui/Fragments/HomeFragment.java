package com.codexproject.codewithui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.codexproject.codewithui.ChoiceActivity;
import com.codexproject.codewithui.R;
import com.codexproject.codewithui.homemodeladapter.HomeAdapter;
import com.codexproject.codewithui.homemodeladapter.HomeModels;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vview = inflater.inflate(R.layout.fragment_home,container,false);
        RecyclerView recyclerView = vview.findViewById(R.id.hrrecycler);
        Chip chip = vview.findViewById(R.id.chip11);

        String ecommerse = "https://lvivity.com/wp-content/uploads/2018/08/ecommerce-app.jpg";
        String cleaning = "https://camo.envatousercontent.com/6d7bfaba5b31a1cb6a32fb1c93853c32263cd792/68747470733a2f2f696e69746170707a2e636f6d2f77702d636f6e74656e742f75706c6f6164732f323032302f30342f312d31302e706e67";
        String kapdeshell = "https://graphicriver.img.customer.envatousercontent.com/files/293949459/preview.jpg?auto=compress%2Cformat&fit=crop&crop=top&w=590&h=590&s=9242daa03027c7b13acc72d05d6cd118";
        String symtomchacker = "https://blog.infermedica.com/content/images/2018/08/scrns2-3.png";
        String googleuploadder = "https://38o8pangdlulo00j385dzt43-wpengine.netdna-ssl.com/wp-content/uploads/2019/03/Feature-Graphic-Before-With-vs-Without-Video.jpg";
        String googleplay = "https://res.cloudinary.com/storemaven/image/upload/f_auto,q_auto/v1597306706/appstore_video.png";

        ArrayList<HomeModels> list = new ArrayList<>();

        for (int i = 0 ; i<5; i++){
            list.add(new HomeModels("this is best shopping App",ecommerse));
            list.add(new HomeModels("cleaning home App",cleaning));
            list.add(new HomeModels("Dress shell ecommerse best app",kapdeshell));
            list.add(new HomeModels("Shymtomchacker doctor App",symtomchacker));
            list.add(new HomeModels("Google Play Store UPloader App",googleuploadder));
            list.add(new HomeModels("Uploader for google play",googleplay));
            list.add(new HomeModels("Shopping App for me",ecommerse));
            list.add(new HomeModels("cleaning home App",cleaning));
            list.add(new HomeModels("Dress shell ecommerse best app",kapdeshell));
            list.add(new HomeModels("Shymtomchacker doctor App",symtomchacker));
            list.add(new HomeModels("Google Play Store UPloader App",googleuploadder));
            list.add(new HomeModels("Uploader for google play",googleplay));
        }

        HomeAdapter homeAdapter = new HomeAdapter(getContext(),list);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return vview;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.chip11:
                Intent choice = new Intent(getContext(), ChoiceActivity.class);
                startActivity(choice);
        }
    }

}