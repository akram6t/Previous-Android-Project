package com.allncertbook.blogging;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.allncertbook.blogging.databinding.RecycleBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.microedition.khronos.opengles.GL;

import static android.content.ContentValues.TAG;

public class RADAPter extends RecyclerView.Adapter<RADAPter.viewHolder>{
    ArrayList<Models> list;
    Context context;

    public RADAPter(ArrayList<Models> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RADAPter.viewHolder holder, int position) {

        Models models = list.get(position);
        Glide.with(context).load(models.getProfile()).placeholder(R.drawable.placcc).into(holder.binding.profile);
        Glide.with(context).load(models.getImage()).placeholder(R.drawable.place).into(holder.binding.imagevi);

        holder.binding.name.setText(models.getName());

        holder.binding.likeText.setText(String.valueOf(models.getLike()));
        holder.binding.textComment.setText(String.valueOf(models.getComment()));

        FirebaseFirestore.getInstance().collection("Like").document(models.image).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d(TAG, "onSuccess: " + documentSnapshot);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RecycleBinding binding;
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding = RecycleBinding.bind(itemView);
        }
    }
}
