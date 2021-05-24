package com.example.multichat.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.multichat.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class AllChats extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_chats);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.chat_1:
                moveToNextActivity("class1");
                break;
            case R.id.chat_2:
                moveToNextActivity("class2");
                break;
            case R.id.chat_3:
                moveToNextActivity("class3");
                break;
        }
    }
    private void moveToNextActivity(String collectionName){
        Intent intent=new Intent(AllChats.this,ChatTemplate.class);
        intent.putExtra("collectionName",collectionName);
        startActivity(intent);

    }
}