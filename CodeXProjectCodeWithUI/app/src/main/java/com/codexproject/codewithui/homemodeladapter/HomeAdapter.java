package com.codexproject.codewithui.homemodeladapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codexproject.codewithui.R;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewHolder> {
    ArrayList<HomeModels> list;
    Context context;

    public HomeAdapter(Context context , ArrayList<HomeModels> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recyclerview,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        HomeModels models = list.get(position);
        holder.title.setText(models.getTitle());
        Glide.with(context).load(models.getImage1()).placeholder(R.drawable.placeholder)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image , saved;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.hrtitle);
            image = itemView.findViewById(R.id.hrfimage);
            saved = itemView.findViewById(R.id.hrsaved);
        }
    }
}
