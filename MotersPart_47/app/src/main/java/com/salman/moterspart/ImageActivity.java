package com.salman.moterspart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.salman.moterspart.adapters.ImageAdapters;
import com.salman.moterspart.models.ImageModels;
import com.salman.moterspart.other.UniqeDeviceID;
import java.util.ArrayList;
import java.util.Date;

public class ImageActivity extends AppCompatActivity {
    LinearLayout addImage;
    ImageView selectimage, bottomclose;
    EditText posttitle;
    Button btn_upload;
    Uri selectedImage;
    FirebaseFirestore database;
    FirebaseStorage storage;
    TextView dialogTitle;
    ArrayList<ImageModels> list;
    ImageAdapters adapters;
    ImageModels models;
    RecyclerView imgrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        addImage = findViewById(R.id.idaddimage);
        imgrecycler = findViewById(R.id.imagerecycler);
        database = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        UniqeDeviceID uniqeID = new UniqeDeviceID(ImageActivity.this);

        list = new ArrayList<>();
        adapters = new ImageAdapters(list,this);
        imgrecycler.setAdapter(adapters);
        GridLayoutManager glm = new GridLayoutManager(ImageActivity.this,2);
        imgrecycler.setLayoutManager(glm);

        View dialogview = LayoutInflater.from(ImageActivity.this).inflate(R.layout.simple_dialog_box,null);
        dialogTitle = findViewById(R.id.dialogtitle);

        AlertDialog dialog = new AlertDialog.Builder(ImageActivity.this)
                .setView(dialogview)
                .setCancelable(false)
                .create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setTitle("Loading Images...");

        database.collection(uniqeID.getUID()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                list.clear();
                dialog.show();
                for (DocumentSnapshot snapshot: value.getDocuments()){
                    models = snapshot.toObject(ImageModels.class);
                    if (models.getId()== uniqeID.getUID()){
                        list.add(models);
                    }
                }
                adapters.notifyDataSetChanged();
                dialog.dismiss();
                Toast.makeText(ImageActivity.this, "data load", Toast.LENGTH_SHORT).show();
            }
        });

        View seetview = getLayoutInflater().inflate(R.layout.bottom_sheet_imageadd,null);
        selectimage = seetview.findViewById(R.id.idsimage);
        bottomclose = seetview.findViewById(R.id.idclosebootomseet);
        posttitle = seetview.findViewById(R.id.idposttitle);
        btn_upload = seetview.findViewById(R.id.iduploadimage);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ImageActivity.this);
        bottomSheetDialog.setContentView(seetview);
        bottomSheetDialog.create();


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });

        bottomclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!posttitle.getText().toString().trim().isEmpty()){
                    if (selectedImage != null){
                        dialogTitle.setText("Uploading...");
                        dialog.show();
                        String edittitle = posttitle.getText().toString();
                        // upload image in firebase storage
                        StorageReference reference = storage.getReference().child("Uploads")
                                .child(uniqeID.getUID());
                        reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()){
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Date date = new Date();
                                            String d = String.valueOf(date);
                                            String url = uri.toString();
                                            ImageModels imageModels = new ImageModels(uniqeID.getUID(),
                                                    edittitle,url,d);

                                            database.collection(uniqeID.getUID()).add(imageModels).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                    Toast.makeText(ImageActivity.this, "Image Upload Successfully", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==45){
            if (data.getData() != null){
                selectedImage = data.getData();
                selectimage.setImageURI(selectedImage);
            }
        }
    }
}