package com.example.multichat.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.multichat.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class AllChats extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chats);

    }
}