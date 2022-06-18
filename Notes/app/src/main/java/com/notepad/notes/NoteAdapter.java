package com.notepad.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.viewHOlder>{

    ArrayList<Models> list;
    Context context;
    boolean isDown;

    public NoteAdapter(ArrayList<Models> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_layout,parent,false);
        return new viewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHOlder holder, int position) {
        String uid = FirebaseAuth.getInstance().getUid();
        Models models = list.get(position);

        isDown = true;

        holder.title.setText(models.getTitle());
        holder.description.setText(models.getDescription());
        Glide.with(context).load(models.getImage()).placeholder(android.R.drawable.ic_menu_report_image).into(holder.imageView);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDown){
                    holder.description.setVisibility(View.VISIBLE);
                    holder.delete.setVisibility(View.VISIBLE);
                    if (models.getImage() != null){
                        holder.imageView.setVisibility(View.VISIBLE);
                    }
                    isDown = false;
                }else {
                    holder.description.setVisibility(View.GONE);
                    holder.delete.setVisibility(View.GONE);
                    holder.imageView.setVisibility(View.GONE);
                    isDown = true;
                }

            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_delete)
                .setTitle("Delete Notes").setMessage("delete "+models.getTitle())
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String uid = FirebaseAuth.getInstance().getUid();
                        FirebaseFirestore.getInstance().collection(uid).document(models.documentID).delete();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();

            }
        });


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHOlder extends RecyclerView.ViewHolder {
        TextView title,description;
        ImageView imageView,delete;
        public viewHOlder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rtitle);
            description = itemView.findViewById(R.id.rdescription);
            imageView = itemView.findViewById(R.id.rimage);
            delete = itemView.findViewById(R.id.rdelete);
        }
    }
}
