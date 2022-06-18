package com.example.arbazkhan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.arbazkhan.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseFirestore database;
    FirebaseStorage storage;
    Uri selectedImage;
    String username;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        database = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Uploading Proccess");



        String username = getIntent().getStringExtra("pass");
        String textname = getIntent().getStringExtra("name");


        binding.textView.setText(textname);

        final  ArrayList<ArbazModels> alist = new ArrayList<>();

        final  ArbazAdapter arbazAdapter = new ArbazAdapter(alist,MainActivity.this);


        database.collection(username).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value,FirebaseFirestoreException error) {
                alist.clear();

                for (DocumentSnapshot snapshot: value.getDocuments()){
                    ArbazModels models = snapshot.toObject(ArbazModels.class);
                    models.setRid(snapshot.getId());
                    alist.add(models);

                }
                arbazAdapter.notifyDataSetChanged();

            }
        });


        binding.recyclerView.setAdapter(arbazAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,3);
        binding.recyclerView.setLayoutManager(layoutManager);


        binding.seleimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });





        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImage != null){
                    progressDialog.show();
                    StorageReference referen = storage.getReference().child("Profils").child(username).child(selectedImage.getPath());
                    referen.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                            referen.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();
                                    ArbazModels models = new ArbazModels(url,username);
                                    database.collection(username).add(models).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            progressDialog.dismiss();
                                            Toast.makeText(MainActivity.this, "Photo Uploaded", Toast.LENGTH_SHORT).show();
                                            selectedImage = null;
                                            binding.seleimg.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                            Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();

                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }else {
                    Toast.makeText(MainActivity.this, "please select your image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (data.getData() != null){
                selectedImage = data.getData();
                binding.seleimg.setImageURI(selectedImage);
            }
        }
    }
}