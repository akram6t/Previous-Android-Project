package com.chatbook.chatbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;
import com.chatbook.chatbook.adapters.ChatsAdapters;
import com.chatbook.chatbook.databinding.ActivityMainChatBinding;
import com.chatbook.chatbook.models.Messages;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Date;

public class MainChat extends AppCompatActivity {
    ActivityMainChatBinding binding;
    Toolbar toolbar;
    ArrayList<Messages> messagesArrayList;
    ChatsAdapters chatsAdapters;
    String senderRoom,receiverRoom;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        String receiverName = getIntent().getStringExtra("name");
        String receiverID = getIntent().getStringExtra("receiverID");
        String profileLink = getIntent().getStringExtra("profile");
        String senderID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        messagesArrayList  = new ArrayList<>();
        chatsAdapters = new ChatsAdapters(MainChat.this,messagesArrayList);
        binding.recycler.setAdapter(chatsAdapters);

        senderRoom = senderID+receiverID;
        receiverRoom = receiverID+senderID;

        database.getReference().child("Chats").child(senderRoom)
                .child("message").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesArrayList.clear();
                for (DataSnapshot mysnapshot:snapshot.getChildren()){
                    Messages messages = mysnapshot.getValue(Messages.class);
                    messagesArrayList.add(messages);
                }
                chatsAdapters.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (binding.massagebox.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainChat.this, "Empty Message", Toast.LENGTH_SHORT).show();
                }else {
                    Date date = new Date();
                    String mess = binding.massagebox.getText().toString();
                    String randomkey = database.getReference().push().getKey();
                    Messages messages = new Messages(mess, senderID,date.getTime(),10,senderRoom,receiverRoom,randomkey);

                    database.getReference().child("Chats").child(senderRoom).child("message").child(randomkey)
                            .setValue(messages)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    binding.massagebox.setText("");
                                    database.getReference().child("Chats").child(receiverRoom).child("message")
                                            .child(randomkey)
                                            .setValue(messages)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                }
                                            });
                                }
                            });
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chatmenu,menu);
        return true;
    }
}