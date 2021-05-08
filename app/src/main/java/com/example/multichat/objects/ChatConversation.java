package com.example.multichat.objects;

public class ChatConversation {
    private String nameOfSender,text,date;

    public ChatConversation() {
    }

    public ChatConversation(String nameOfSender, String text, String date) {
        this.nameOfSender = nameOfSender;
        this.text = text;
        this.date = date;
    }

    public String getNameOfSender() {
        return nameOfSender;
    }

    public void setNameOfSender(String nameOfSender) {
        this.nameOfSender = nameOfSender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
