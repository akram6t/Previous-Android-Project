package com.example.examle2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examle2.databinding.RecycklaBinding;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class BAdapter extends RecyclerView.Adapter<BAdapter.viewHolder> {
    ArrayList<Models> list;
    Context context;

    public BAdapter(ArrayList<Models> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyckla,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BAdapter.viewHolder holder, int position) {
        Models models = list.get(position);
        holder.binding.textName.setText(models.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PdfView.class);
                intent.putExtra("link",models.getLink());
                intent.putExtra("title",models.getTitle());
                context.startActivity(intent);

            }
        });

        holder.binding.iconDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(models.getLink()));
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RecycklaBinding binding;
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding = RecycklaBinding.bind(itemView);
        }
    }
}
