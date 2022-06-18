package com.chatbook.chatbook.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chatbook.chatbook.MainChat;
import com.chatbook.chatbook.R;
import com.chatbook.chatbook.databinding.RecyclermainBinding;
import com.chatbook.chatbook.models.ProfileModels;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainAdapters extends RecyclerView.Adapter<MainAdapters.ViewHolder>{
    ArrayList<ProfileModels> list;
    Context context;
    public MainAdapters(ArrayList<ProfileModels> listar,Context contextco){
        this.list = listar;
        this.context = contextco;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclermain,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfileModels models = list.get(position);
        holder.bindi.rname.setText(models.getName());
        Glide.with(context).load(models.getProfile()).placeholder(R.drawable.rplogo).into(holder.bindi.rprofile);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainChat.class);
                intent.putExtra("receiverID",models.getUid());
                intent.putExtra("name",models.getName());
                intent.putExtra("profile",models.getProfile());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclermainBinding bindi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindi = RecyclermainBinding.bind(itemView);
        }
    }

}
