package com.example.examle2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.examle2.databinding.ActivityPostBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import org.jetbrains.annotations.NotNull;

public class PostAc extends AppCompatActivity {
    Uri selectedPdf ;
    ActivityPostBinding binding;
    FirebaseFirestore database;
    FirebaseStorage storage ;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        dialog = new ProgressDialog(PostAc.this);
        dialog.setCancelable(false);
        dialog.setMessage("Uloading...");

        binding.iconPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,50);

            }
        });



        binding.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPdf == null){
                    Toast.makeText(PostAc.this, "please select pdf file", Toast.LENGTH_SHORT).show();

                }else if (binding.editTitle.getText().toString().isEmpty()){
                    Toast.makeText(PostAc.this, "please enter pdf name", Toast.LENGTH_SHORT).show();

                }else {
                    //main work
                    dialog.show();
                    StorageReference refere = storage.getReference().child(binding.editTitle.getText().toString().replace(".pdf","") + System.currentTimeMillis() + ".pdf");
                    refere.putFile(selectedPdf).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            refere.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();

                                    Models models = new Models(binding.editTitle.getText().toString(),url);
                                    database.collection("PDF").add(models).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            selectedPdf = null ;
                                            binding.iconClear.setVisibility(View.GONE);
                                            binding.iconPdf.setImageResource(R.drawable.ic_baseline_cloud_upload_24);
                                            binding.editTitle.setText("");
                                            Toast.makeText(PostAc.this, "upload Succesfull", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });

                                }
                            });

                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {
                            float perchant = (100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                            dialog.setMessage("Upload : " + (int) perchant + " %");


                        }
                    });

                }

            }
        });

        binding.iconClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPdf = null;
                binding.iconPdf.setImageResource(R.drawable.ic_baseline_cloud_upload_24);
                binding.iconClear.setVisibility(View.GONE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 50){
            if (data.getData() != null){
                selectedPdf = data.getData();
                binding.iconPdf.setImageResource(R.drawable.pdlogo);
                binding.iconClear.setVisibility(View.VISIBLE);

            }
        }
    }
}