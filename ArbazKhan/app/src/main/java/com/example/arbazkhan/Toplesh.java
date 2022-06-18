package com.example.arbazkhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.arbazkhan.databinding.ActivityTopleshBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Toplesh extends AppCompatActivity {
    ActivityTopleshBinding binding;
    FirebaseFirestore database;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopleshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();


        getSupportActionBar().hide();

        binding.textView4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                binding.editname.setVisibility(View.VISIBLE);
                binding.editpassword.setVisibility(View.VISIBLE);
                binding.button.setVisibility(View.VISIBLE);
                binding.textView4.setText("Enter Name And Password \n  this password  help you open App \n your data is in Password");
                return false;
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editname.getText().toString().isEmpty() || binding.editpassword.getText().toString().isEmpty()){

                    Toast.makeText(Toplesh.this, "enter Name and Password", Toast.LENGTH_SHORT).show();


                }else {
                    PassModels passModels = new PassModels(binding.editname.getText().toString(),binding.editpassword.getText().toString());

                    firebaseDatabase.getReference().child("Username").child(binding.editpassword.getText().toString()).setValue(passModels).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Toplesh.this, "WelCome", Toast.LENGTH_SHORT).show();

                        }
                    });

                    Intent intent = new Intent(Toplesh.this,MainActivity.class);
                    intent.putExtra("name",binding.editname.getText().toString());
                    intent.putExtra("pass",binding.editpassword.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}