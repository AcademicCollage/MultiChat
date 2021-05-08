package com.example.multichat.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.multichat.R;

public class ChatTemplate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_template);
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler_chat);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerChat mAdapter = new RecyclerChat(chats);
        recyclerView.setAdapter(mAdapter);
    }
}