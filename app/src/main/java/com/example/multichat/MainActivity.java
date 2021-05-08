package com.example.multichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.multichat.objects.User;
import com.example.multichat.screens.AllChats;
import com.example.multichat.screens.Login;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        sharedPreferences=getSharedPreferences(Utils.SP_NAME,MODE_PRIVATE);
        checkIfUserIsConnected();
    }

    private void checkIfUserIsConnected() {
        if(sharedPreferences.getBoolean(Utils.IS_CONNECTED,false)){
            db.
                    collection("users").
                    document(sharedPreferences.getString(Utils.USER_NAME,"")).
                    get().
                    addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Utils.globalUser=documentSnapshot.toObject(User.class);
                            startActivity(new Intent(MainActivity.this, AllChats.class));
                        }
                    });
        }else{
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }
}