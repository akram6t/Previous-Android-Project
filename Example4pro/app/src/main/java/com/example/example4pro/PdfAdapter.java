package com.example.example4pro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.pdfHolder>{

    ArrayList<PdfModels> list;
    Context context;

    public PdfAdapter(ArrayList<PdfModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // this method only showing sample recyclerview layout
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public pdfHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler,parent,false);
        return new pdfHolder(view);
    }

    // this is main method
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull PdfAdapter.pdfHolder holder, int position) {
        PdfModels pdfModels = list.get(position);

        holder.pdfname.setSelected(true);
        holder.pdfname.setText(pdfModels.getPdfname());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // find View by ID
    public class pdfHolder extends RecyclerView.ViewHolder {
        TextView pdfname;
        public pdfHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            pdfname = itemView.findViewById(R.id.pdf_name);
        }
    }

}
