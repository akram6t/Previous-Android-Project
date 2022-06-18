package com.salman.moterspart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.salman.moterspart.R;
import com.salman.moterspart.models.ImageModels;
import java.util.ArrayList;

public class ImageAdapters extends RecyclerView.Adapter<ImageAdapters.viewHolder>{

    ArrayList<ImageModels> list;
    Context context;

    public ImageAdapters(ArrayList<ImageModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iview = LayoutInflater.from(context).inflate(R.layout.image_recycler_item,parent,false);
        return new viewHolder(iview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ImageModels models = list.get(position);
        holder.title.setText(models.getTitle());
        Glide.with(context).load(models.getUrl()).placeholder(R.drawable.placeholder)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.riimage);
            title = itemView.findViewById(R.id.rititle);
        }
    }
}
