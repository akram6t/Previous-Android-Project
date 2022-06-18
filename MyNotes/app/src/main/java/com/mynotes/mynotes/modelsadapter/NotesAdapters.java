package com.mynotes.mynotes.modelsadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mynotes.mynotes.R;

import java.util.ArrayList;

public class NotesAdapters extends RecyclerView.Adapter<NotesAdapters.notesHolder> {

    ArrayList<NotesModel> list;
    Context context;

    public NotesAdapters(ArrayList<NotesModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public notesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclerview,parent,false);
        return new notesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notesHolder holder, int position) {
        NotesModel model = list.get(position);
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getNotes());

        if (model.getImage() == null){
            holder.imageView.setImageResource(R.drawable.notes);
        }else {
            Glide.with(context).load(model.getImage()).placeholder(R.drawable.notes).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class notesHolder extends RecyclerView.ViewHolder {
        TextView title , description;
        ImageView imageView;
        public notesHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.ntitle);
            description = itemView.findViewById(R.id.ndescription);
            imageView = itemView.findViewById(R.id.nimageview);
        }
    }
}
