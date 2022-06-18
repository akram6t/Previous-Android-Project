package com.chatbook.chatbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.chatbook.chatbook.databinding.ActivityProfileVerifyBinding;
import com.chatbook.chatbook.models.ProfileModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileVerify extends AppCompatActivity {
    ActivityProfileVerifyBinding binding;
    Uri selectedImage;
    ProgressDialog dialog;
    FirebaseAuth fauth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        storage = FirebaseStorage.getInstance();
        fauth = FirebaseAuth.getInstance();
        phonenumber = getIntent().getStringExtra("number");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        dialog = new ProgressDialog(ProfileVerify.this);
        dialog.setCancelable(false);
        binding.rprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photointent = new Intent();
                photointent.setAction(Intent.ACTION_GET_CONTENT);
                photointent.setType("image/*");
                startActivityForResult(photointent,10);
            }
        });

        binding.prosub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImage != null){
                    if (!binding.pname.getText().toString().isEmpty()){
                        dialog.setMessage("Profile Uploading...");
                        dialog.show();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHddmm");
                        String time = simpleDateFormat.format(new Date());
                        StorageReference storageReference = storage.getReference("Users").child(fauth.getCurrentUser().getUid()).child(time);
                        storageReference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()){
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imagelink = uri.toString();
                                            ProfileModels profileModels = new ProfileModels(binding.pname.getText().toString(),imagelink,fauth.getCurrentUser().getUid(),phonenumber);
                                            reference.child(fauth.getCurrentUser().getUid()).setValue(profileModels).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    dialog.dismiss();
                                                    Intent intent = new Intent(ProfileVerify.this,MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });

                                        }
                                    });
                                }
                            }
                        });
                    }
                }else {
                    Intent vesehi = new Intent(ProfileVerify.this,MainActivity.class);
                    startActivity(vesehi);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10){
            if (data.getData()!= null){
                selectedImage = data.getData();
                binding.rprofile.setImageURI(selectedImage);
            }
        }
    }
}