package com.example.multichat.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.multichat.R;
import com.example.multichat.Utils;
import com.example.multichat.objects.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private EditText userName, password;
    private Button signIn;
    private User globalUser;
    private SharedPreferences sharedPreferences;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        sharedPreferences=getSharedPreferences(Utils.SP_NAME,MODE_PRIVATE);

        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.login_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 globalUser=new User(userName.getText().toString(),password.getText().toString());
                if (globalUser.getUserName().length() >= 3 && globalUser.getPassword().length() >= 6) {
                    db.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<User> users = (ArrayList<User>) queryDocumentSnapshots.toObjects(User.class);
                            if (users.contains(globalUser)) {

                            } else {
                                boolean exist = false;
                                for (User user : users) {
                                    exist = user.getUserName().contains(globalUser.getUserName());
                                    if (exist) {
                                        break;
                                    }
                                }
                                if (exist) {
                                    Toast.makeText(Login.this, "יש לוודא שהפרטים שהוזנו תקינים", Toast.LENGTH_SHORT).show();
                                } else {
                                    db.
                                            collection("users").
                                            document(globalUser.getUserName()).
                                            set(globalUser).
                                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                    moveToChatsActivity();
                                                }
                                            });
                                }

                            }
                        }
                    });
                } else if (userName.getText().toString().length() < 3) {
                    userName.setError("יש להזין שם משתמש עם שלושה תווים ומעלה");
                } else {
                    password.setError("יש למלא סיסמה בעלת שישה תווים ומעלה");
                }
            }
        });
    }

    private void moveToChatsActivity() {
        Utils.globalUser=globalUser;
        sharedPreferences.edit().putBoolean(Utils.IS_CONNECTED,true).apply();
        sharedPreferences.edit().putString(Utils.USER_NAME,globalUser.getUserName()).apply();
        startActivity(new Intent(Login.this,AllChats.class));
    }
}