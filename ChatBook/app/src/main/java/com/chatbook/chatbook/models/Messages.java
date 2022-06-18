package com.chatbook.chatbook.models;

public class Messages {
   private String messageID,message,senderID,senderRoom,receiverRoom;
   private long timeStamp;
   private int feelings;

    public Messages(String message,String senderID,long timeStamp,int feelings,String senderRoom,String receiverRoom,String messageID){
        this.message = message;
        this.senderID = senderID;
        this.timeStamp = timeStamp;
        this.feelings = feelings;
        this.senderRoom = senderRoom;
        this.receiverRoom = receiverRoom;
        this.messageID = messageID;

    }
    public Messages(){

    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getFeelings() {
        return feelings;
    }

    public void setFeelings(int feelings) {
        this.feelings = feelings;
    }

    public String getSenderRoom() {
        return senderRoom;
    }

    public void setSenderRoom(String senderRoom) {
        this.senderRoom = senderRoom;
    }

    public String getReceiverRoom() {
        return receiverRoom;
    }

    public void setReceiverRoom(String receiverRoom) {
        this.receiverRoom = receiverRoom;
    }
}
