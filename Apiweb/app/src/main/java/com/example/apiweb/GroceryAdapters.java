package com.example.apiweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class GroceryAdapters extends RecyclerView.Adapter<GroceryAdapters.viewHolder> {
    ArrayList<GroceryModels> list;
    Context context;
    public GroceryAdapters(Context context , ArrayList<GroceryModels> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grocery_recycler,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        String fisturl = "https://codeforfree.in/myadmin/uploads/grocery/";
        GroceryModels models = list.get(position);
        holder.name.setText(models.getName());
        holder.price.setText(String.valueOf("$"+models.getPrice()));
        Glide.with(context).load(fisturl+models.getImage()).placeholder(R.drawable.ic_launcher_foreground).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name , price;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.gname);
            imageView = itemView.findViewById(R.id.gimage);
            price = itemView.findViewById(R.id.gprice);
        }
    }
}
