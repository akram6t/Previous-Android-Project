package com.call.freevideocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.URL;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText edit_secretCode;
    Button btn_join, btn_share;
    String message;
    String AppID = "205169457";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_join = findViewById(R.id.btn_join);
        btn_share = findViewById(R.id.btn_share);
        edit_secretCode = findViewById(R.id.editTextTextPersonName);

        StartAppSDK.init(this, AppID, false);



        URL server_url;

        try {
            server_url = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(server_url)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(options);

        }catch (Exception e){
            e.printStackTrace();
        }

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_secretCode.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter secret code", Toast.LENGTH_SHORT).show();
                }else {


                    JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                            .setRoom(edit_secretCode.getText().toString())
                            .setWelcomePageEnabled(false)
                            .build();

                    JitsiMeetActivity.launch(MainActivity.this,defaultOptions);
                }
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_secretCode.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter your secret code", Toast.LENGTH_SHORT).show();
                }else {
                    StartAppAd.showAd(MainActivity.this);

                    message = ". \n  FREE VIDEO ROOM \n \n Join this Meeting Password \n \n Meeting Code - " + edit_secretCode.getText().toString() +" \n .";
                    sendMessage(message);

                }
            }
        });






    }

    private void sendMessage(String message){

        // Creating new intent
        Intent intent
                = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");

        // Give your message here
        intent.putExtra(
                Intent.EXTRA_TEXT,
                message);

        // Checking whether Whatsapp
        // is installed or not
        if (intent
                .resolveActivity(
                        getPackageManager())
                == null) {
            Toast.makeText(
                    this,
                    "Please install whatsapp first.",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // Starting Whatsapp
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }
}