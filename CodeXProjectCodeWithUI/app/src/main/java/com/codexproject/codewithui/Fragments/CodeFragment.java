package com.codexproject.codewithui.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.codexproject.codewithui.R;

public class CodeFragment extends Fragment {

    public CodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cview = inflater.inflate(R.layout.fragment_code,container,false);
        RecyclerView recyclerView = cview.findViewById(R.id.searchrecycler);

        return cview;
    }
}