package com.git.androiduigit.adapterg;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.git.androiduigit.R;
import com.git.androiduigit.databinding.MainrecyclerBinding;
import com.git.androiduigit.model.Gmodels;
import com.startapp.sdk.adsbase.StartAppSDK;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class Gadapter extends RecyclerView.Adapter<Gadapter.viewHolder> {
    ArrayList<Gmodels> list;
    ArrayList<Gmodels> backup;
    Context context;

    public Gadapter(ArrayList<Gmodels> list, Context context) {
        this.list = list;
        this.context = context;
        backup = new ArrayList<>(list);
    }

    @Override
    public @NotNull viewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.mainrecycler,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  Gadapter.viewHolder holder, int position) {

        Gmodels gmodels = list.get(position);
        holder.binding.rtitle.setText(gmodels.getT());
        holder.binding.subtitle.setText(gmodels.getSt());
        holder.binding.rlink.setText(gmodels.getL());
        Glide.with(context).load(gmodels.getImg()).placeholder(R.drawable.placeh).into(holder.binding.rimage);
        holder.binding.rcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)
                        context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", "Hello, World!");
                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        holder.binding.rgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(gmodels.getL()));
                context.startActivity(intent);
            }
        });

    }

    public class viewHolder extends RecyclerView.ViewHolder {
        MainrecyclerBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MainrecyclerBinding.bind(itemView);
        }
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
