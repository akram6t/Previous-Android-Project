package com.inotes.inotes.adapterandmodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.inotes.inotes.R;
import com.inotes.inotes.databinding.SampleRecyclerviewBinding;
import java.util.ArrayList;

public class NotesAdapters extends RecyclerView.Adapter<NotesAdapters.viewHolder>{

    Context context;
    ArrayList<NotesModel> list;

    public NotesAdapters(Context context , ArrayList<NotesModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclerview,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotesModel model = list.get(position);

        holder.binding.rtitle.setText(model.getTitle());
        holder.binding.rdescription.setText(model.getDescription());
        holder.binding.rdate.setText(model.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        SampleRecyclerviewBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SampleRecyclerviewBinding.bind(itemView);

        }
    }
}
