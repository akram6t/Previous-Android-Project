package com.chatbook.chatbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Toast;

import com.chatbook.chatbook.databinding.ActivityOtpVerifyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class OtpVerify extends AppCompatActivity {
    ActivityOtpVerifyBinding binding;
    FirebaseAuth fauth;
    String verificationID;
    String otpNumber;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fauth = FirebaseAuth.getInstance();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new ProgressDialog(OtpVerify.this);
        dialog.setCancelable(false);
        dialog.setMessage("Verifying...");
        binding.one.requestFocus();

        String phoneNumber = getIntent().getStringExtra("phonenumber");
        binding.top.setText("Verify Number "+phoneNumber);
        dialog.setMessage("Verifying Number...");
        dialog.show();

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder()
                .setPhoneNumber(phoneNumber)
                .setTimeout(30L, TimeUnit.SECONDS)
                .setActivity(OtpVerify.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        dialog.setMessage("Code Sending...");

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        dialog.dismiss();
                        Toast.makeText(OtpVerify.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OtpVerify.this,PhoneVerify.class));
                        finish();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyID, forceResendingToken);
                        Toast.makeText(OtpVerify.this, "Send Otp", Toast.LENGTH_SHORT).show();
                        verificationID = verifyID;
                        dialog.dismiss();

                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        binding.one.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.two.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        binding.two.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.three.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        binding.three.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.four.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        binding.four.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.five.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        binding.five.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()){
                    binding.six.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });
        binding.six.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.six.getText().toString().trim().isEmpty()){
                }else if (!binding.one.getText().toString().isEmpty() &&
                        !binding.two.getText().toString().isEmpty() &&
                        !binding.three.getText().toString().isEmpty() &&
                        !binding.four.getText().toString().isEmpty() &&
                        !binding.five.getText().toString().isEmpty()){
                    otpNumber  = binding.one.getText().toString()+
                            binding.two.getText().toString()+
                            binding.three.getText().toString()+
                            binding.four.getText().toString()+
                            binding.five.getText().toString()+
                            binding.six.getText().toString();
                    dialog.setMessage("Verifying OTP...");
                    dialog.show();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,otpNumber);
                    fauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                dialog.dismiss();
                                Intent i = new Intent(OtpVerify.this,ProfileVerify.class);
                                i.putExtra("number",task.getResult().getUser().getPhoneNumber());
                                startActivity(i);
                                finish();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(OtpVerify.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }


        });

    }
}