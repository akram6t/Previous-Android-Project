package com.chatbook.chatbook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.chatbook.chatbook.databinding.ActivityPhoneVerifyBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneVerify extends AppCompatActivity {
    ActivityPhoneVerifyBinding binding;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fauth = FirebaseAuth.getInstance();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding.prosub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.pname.getText().toString().length()!=10){
                    Toast.makeText(PhoneVerify.this, "You entered "+binding.pname.getText().toString().length()+" degit", Toast.LENGTH_SHORT).show();
                }else if (binding.pname.getText().toString().isEmpty()){
                    Toast.makeText(PhoneVerify.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                }else if (binding.pname.getText().length()!=10){
                    Toast.makeText(PhoneVerify.this, "Please Enter 10 Degit Numbers", Toast.LENGTH_SHORT).show();
                }else if (binding.countrycode.getText().toString().isEmpty()){
                    Toast.makeText(PhoneVerify.this, "Please Enter Country Code", Toast.LENGTH_SHORT).show();
                }else {
                    String phonenum = "+"+binding.countrycode.getText().toString()+binding.pname.getText().toString();
                    Intent intent = new Intent(PhoneVerify.this,OtpVerify.class);
                    intent.putExtra("phonenumber",phonenum);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (fauth.getCurrentUser()!=null){
            Intent in = new Intent(PhoneVerify.this,MainActivity.class);
            startActivity(in);
            finish();

        }
    }
}