package com.example.multichat.screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.multichat.R;
import com.example.multichat.objects.ChatConversation;
import com.example.multichat.recycler.ChatRecycler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

import static com.example.multichat.Utils.globalUser;

public class ChatTemplate extends AppCompatActivity {
    RecyclerView recyclerView ;
    private EditText message;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<ChatConversation>chatConversations;
    private LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_template);
        firebaseFirestore=FirebaseFirestore.getInstance();

        initChats();
        initComponents();
        liveUpdate();
    }

    private void liveUpdate() {
        firebaseFirestore.collection(getIntent().getStringExtra("collectionName"))
                .document("chats").
                collection("chats").
                addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            return;
                        }
                        chatConversations= (ArrayList<ChatConversation>) value.toObjects(ChatConversation.class);
                        setRecyclerView();

                    }
                });
    }

    private void initComponents() {
        TextView textView=findViewById(R.id.name_of_chat);
        animationView=findViewById(R.id.empty_animation);
        textView.setText(getIntent().getStringExtra("collectionName"));
        message=findViewById(R.id.message);
        Button button=findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatConversation chatConversation=new ChatConversation(globalUser.getUserName(),message.getText().toString(),new Date().toString());
                firebaseFirestore.collection(getIntent().getStringExtra("collectionName"))
                        .document("chats").
                        collection("chats").document(""+new Date().getTime()).
                        set(chatConversation);

            }
        });

    }

    private void initChats() {
        firebaseFirestore.
                collection(getIntent().getStringExtra("collectionName")).
                document("chats").
                collection("chats").
                get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        chatConversations= (ArrayList<ChatConversation>) task.getResult().toObjects(ChatConversation.class);
                        setRecyclerView();
                        animationView.setVisibility(chatConversations.size()>0? View.GONE:View.VISIBLE);

                    }
                });
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.chat_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ChatRecycler mAdapter = new ChatRecycler(chatConversations);
        recyclerView.setAdapter(mAdapter);
    }
}