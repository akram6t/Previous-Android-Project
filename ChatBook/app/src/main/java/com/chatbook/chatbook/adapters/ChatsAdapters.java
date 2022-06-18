package com.chatbook.chatbook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chatbook.chatbook.R;
import com.chatbook.chatbook.databinding.ItemReceiveBinding;
import com.chatbook.chatbook.databinding.ItemSendBinding;
import com.chatbook.chatbook.models.Messages;
import com.github.pgreze.reactions.ReactionPopup;
import com.github.pgreze.reactions.ReactionsConfig;
import com.github.pgreze.reactions.ReactionsConfigBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatsAdapters extends RecyclerView.Adapter{
    int ITEM_SENT = 1;
    int ITEM_RECEIVE = 2;
    ArrayList<Messages> message;
    Context context;
    public ChatsAdapters(Context context,ArrayList<Messages> messages){
        this.message = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==ITEM_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_send,parent,false);
            return new SendHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return new ReceiveHolder(view);

        }

    }

    @Override
    public int getItemViewType(int position) {
        Messages messages = message.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderID())){
            return ITEM_SENT;
        }else {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Messages me = message.get(position);

        int [] reaction = new int[]{
                R.drawable.ic_fb_like,
                R.drawable.ic_fb_love,
                R.drawable.ic_fb_laugh,
                R.drawable.ic_fb_wow,
                R.drawable.ic_fb_sad,
                R.drawable.ic_fb_angry
        };
        ReactionsConfig config = new ReactionsConfigBuilder(context)
                .withReactions(reaction)
                .build();

        ReactionPopup popup = new ReactionPopup(context, config, (pos) -> {
            if (pos<0){
                return true;
            }

            HashMap<String,Object> map = new HashMap<>();
            map.put("feelings",pos);
            FirebaseDatabase.getInstance().getReference().child("Chats").child(me.getReceiverRoom())
                    .child("message").child(me.getMessageID()).updateChildren(map);
            FirebaseDatabase.getInstance().getReference().child("Chats").child(me.getSenderRoom())
                    .child("message").child(me.getMessageID()).updateChildren(map);

            if (holder.getClass() == SendHolder.class){
                SendHolder sendHolder = (SendHolder) holder;
                sendHolder.sendBind.feelings.setImageResource(reaction[pos]);
                sendHolder.sendBind.feelings.setVisibility(View.VISIBLE);

            }else {
                ReceiveHolder receiveHolder = (ReceiveHolder) holder;
                receiveHolder.recebind.feelings.setImageResource(reaction[pos]);
                receiveHolder.recebind.feelings.setVisibility(View.VISIBLE);
            }

            return true;
            // true is closing popup, false is requesting a new selection
        });


        if (holder.getClass() == SendHolder.class){
            SendHolder sendHolder = (SendHolder) holder;
            sendHolder.sendBind.message.setText(me.getMessage());

            switch (me.getFeelings()){
                case 0:
                    sendHolder.sendBind.feelings.setImageResource(reaction[0]);
                    sendHolder.sendBind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    sendHolder.sendBind.feelings.setImageResource(reaction[1]);
                    sendHolder.sendBind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    sendHolder.sendBind.feelings.setImageResource(reaction[2]);
                    sendHolder.sendBind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    sendHolder.sendBind.feelings.setImageResource(reaction[3]);
                    sendHolder.sendBind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    sendHolder.sendBind.feelings.setImageResource(reaction[4]);
                    sendHolder.sendBind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    sendHolder.sendBind.feelings.setImageResource(reaction[5]);
                    sendHolder.sendBind.feelings.setVisibility(View.VISIBLE);
                    break;
            }


            ((SendHolder) holder).sendBind.message.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    popup.onTouch(view,motionEvent);
                    return false;
                }
            });

        }else {
            ReceiveHolder receiveHolder = (ReceiveHolder) holder;
            receiveHolder.recebind.remessage.setText(me.getMessage());

            switch (me.getFeelings()){
                case 0:
                    receiveHolder.recebind.feelings.setImageResource(reaction[0]);
                    receiveHolder.recebind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    receiveHolder.recebind.feelings.setImageResource(reaction[1]);
                    receiveHolder.recebind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    receiveHolder.recebind.feelings.setImageResource(reaction[2]);
                    receiveHolder.recebind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    receiveHolder.recebind.feelings.setImageResource(reaction[3]);
                    receiveHolder.recebind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    receiveHolder.recebind.feelings.setImageResource(reaction[4]);
                    receiveHolder.recebind.feelings.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    receiveHolder.recebind.feelings.setImageResource(reaction[5]);
                    receiveHolder.recebind.feelings.setVisibility(View.VISIBLE);
                    break;
            }



            ((ReceiveHolder) holder).recebind.remessage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent motion) {
                    popup.onTouch(v,motion);
                    return false;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    public class SendHolder extends RecyclerView.ViewHolder {
        ItemSendBinding sendBind;
        public SendHolder(@NonNull View itemView) {
            super(itemView);
            sendBind = ItemSendBinding.bind(itemView);
        }
    }

    public class ReceiveHolder extends RecyclerView.ViewHolder {
        ItemReceiveBinding recebind;
        public ReceiveHolder(@NonNull View itemView) {
            super(itemView);
            recebind = ItemReceiveBinding.bind(itemView);
        }
    }
}
