package com.example.example6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class CAdapter extends RecyclerView.Adapter<CAdapter.viewHolder> {
    ArrayList<Models> list;
    Context context;

    public CAdapter(ArrayList<Models> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recycler,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CAdapter.viewHolder holder, int position) {
        Models models = list.get(position);
        holder.textView.setText(models.getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ViewAct.class);
                intent.putExtra("pos",position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public viewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
