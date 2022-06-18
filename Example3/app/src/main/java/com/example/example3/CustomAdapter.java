package com.example.example3;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.example3.databinding.RecyclervBinding;

import java.io.File;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {
    ArrayList<Models> list;
    Context context;

    public CustomAdapter(ArrayList<Models> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerv,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CustomAdapter.viewHolder holder, int position) {
        Models models = list.get(position);
        holder.binding.rtextview.setText(models.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView myVideoView = MainActivity.videoView;
                ArrayList<File> myVideos = MainActivity.myvideos;
                Uri uri = Uri.parse(myVideos.get(position).toString());

                myVideoView.setVideoURI(uri);
                myVideoView.start();

                if (myVideoView.isPlaying()){
                    myVideoView.pause();
                    myVideoView.stopPlayback();
                    myVideoView.setVideoURI(uri);
                    myVideoView.start();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RecyclervBinding binding;
        public viewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            binding = RecyclervBinding.bind(itemView);
        }
    }
}
