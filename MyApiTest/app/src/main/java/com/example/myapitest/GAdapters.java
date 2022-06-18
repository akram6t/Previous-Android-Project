package com.example.myapitest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class GAdapters extends RecyclerView.Adapter<GAdapters.viewHilder> {
    Context context;
    ArrayList<GModels> list;

    public GAdapters(Context context , ArrayList<GModels> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHilder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grocery_recycler,parent,false);
        return new viewHilder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHilder holder, int position) {
        String imgurl = "https://admin.codeforfree.in/uploads/grocery/";
        GModels models = list.get(position);
        holder.name.setText(models.getName());
        holder.price.setText(models.getPrice());
        Glide.with(context).load(imgurl+models.getImg())
                .placeholder(R.drawable.gifplaceholder)
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ImgViewActivity.class);
                intent.putExtra("img",imgurl+models.getImg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHilder extends RecyclerView.ViewHolder {
        TextView name , price;
        ImageView img;
        public viewHilder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.gname);
            price = itemView.findViewById(R.id.gprice);
            img = itemView.findViewById(R.id.gimage);
        }
    }
}
