package com.notepad.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btn_submit;
    EditText edit_title, edit_description;
    ImageView edit_image;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseFirestore database;
    FloatingActionButton actionButton;
    RecyclerView recyclerView;
    NoteAdapter adapter;
    EditText searchView;
    ArrayList<Models> list;
    Uri selectedImage;
    String uid = "1";
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionButton = findViewById(R.id.floatingadd);
        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchView);

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        googleoptions();

        if (auth.getCurrentUser() != null) {
            uid = auth.getCurrentUser().getUid();
        }else {
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        list = new ArrayList<>();
        adapter = new NoteAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);

        database.collection(uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                list.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    Models models = snapshot.toObject(Models.class);
                    list.add(models);
                }
                adapter.notifyDataSetChanged();

            }
        });

        View v1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_layout, null);
        btn_submit = v1.findViewById(R.id.btn_submit);
        edit_title = v1.findViewById(R.id.edittitle);
        edit_description = v1.findViewById(R.id.editdescription);
        edit_image = v1.findViewById(R.id.addimageview);


        BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
        dialog.setContentView(v1);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (auth.getCurrentUser() != null) {

                    String randomkey = FirebaseDatabase.getInstance().getReference().push().getKey();

                    String title = edit_title.getText().toString();
                    String description = edit_description.getText().toString();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String time = simpleDateFormat.format(new Date());

                    if (edit_title.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();

                    } else if (edit_description.getText().toString().trim().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Enter Description", Toast.LENGTH_SHORT).show();

                    } else {
                        if (selectedImage != null) {

                            StorageReference refere = storage.getReference().child(uid).child(time);
                            refere.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        refere.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                String imagelink = uri.toString();

                                                Models mode = new Models(title, description, imagelink, time, randomkey);

                                                database.collection(uid).document(randomkey).set(mode).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        edit_title = null;
                                                        edit_description = null;
                                                        edit_image = null;
                                                        selectedImage = null;

                                                    }
                                                });


                                            }
                                        });
                                    }
                                }
                            });


                        } else {
                            // selectedimage = null
                            Models mod = new Models(title, description, null, time, randomkey);
                            database.collection(uid).document(randomkey).set(mod).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    edit_title = null;
                                    edit_description = null;
                                    edit_image = null;
                                    selectedImage = null;
                                }
                            });
                        }
                    }

                    dialog.dismiss();

                }else {
                    signIn();
                }

            }
        });

        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 10);
            }
        });

    }

    private void googleoptions() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null) {
            if (requestCode == 10) {
                selectedImage = data.getData();
                edit_image.setImageURI(selectedImage);
            }
        } else {
            if (requestCode == 20) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately

                }

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {

                        }
                    }
                });

    }

}