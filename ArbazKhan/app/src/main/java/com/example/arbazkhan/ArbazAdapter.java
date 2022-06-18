



package com.example.arbazkhan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArbazAdapter extends RecyclerView.Adapter<ArbazAdapter.viewHolder>{

    ArrayList<ArbazModels> list;
    Context context;

    public ArbazAdapter(ArrayList<ArbazModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.arbazrecycler,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ArbazAdapter.viewHolder holder, int position) {
        ArbazModels models = list.get(position);
        Glide.with(context).load(models.getPic()).placeholder(R.drawable.status).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PhotoSee.class);
                intent.putExtra("picture",models.getPic());
                intent.putExtra("title",models.getRid());
                context.startActivity(intent);
            }
        });

        ProgressDialog progress = new ProgressDialog(context);
        progress.setMessage("Deleting...");
        progress.setCancelable(false);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "colName.getText().toString()", Toast.LENGTH_SHORT).show();


                progress.show();
                list.clear();
                FirebaseFirestore.getInstance().collection("AKRAM KHAN").whereEqualTo("pic",models.getPic())
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        WriteBatch writeBatch = FirebaseFirestore.getInstance().batch();
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot snapshot: snapshotList){
                            writeBatch.delete(snapshot.getReference());
                        }
                        writeBatch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progress.dismiss();
                                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                progress.dismiss();

                            }
                        });

                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
       ImageView imageView;
        public viewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.arimage);
        }
    }
}
