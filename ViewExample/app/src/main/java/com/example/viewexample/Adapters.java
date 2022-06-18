package com.example.viewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapters extends RecyclerView.Adapter<Adapters.viewHolder>{
    ArrayList<Models> list;
    Context context;

    public Adapters(ArrayList<Models> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_recyclerview,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Models models = list.get(position);

        holder.id.setText(String.valueOf(models.getId()));
        holder.name.setText(models.getName());
        holder.email.setText(models.getEmail());
        holder.mobile.setText(models.getMobile());
        holder.password.setText(models.getPassword());
        holder.point.setText(String.valueOf(models.getPoint()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView id, name, email, mobile, password, point;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tid);
            name = itemView.findViewById(R.id.tname);
            email = itemView.findViewById(R.id.temail);
            mobile = itemView.findViewById(R.id.tmobile);
            password = itemView.findViewById(R.id.tpassword);
            point = itemView.findViewById(R.id.tpoint);
        }
    }
}
