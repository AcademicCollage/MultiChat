package com.example.multichat.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multichat.R;
import com.example.multichat.objects.ChatConversation;

import java.util.ArrayList;

public class ChatRecycler extends RecyclerView.Adapter<ChatRecycler.ViewHolder> {
    private ArrayList<ChatConversation> chats = new ArrayList<>();



    public ChatRecycler( ArrayList<ChatConversation> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat,parent,false);

        return new ChatRecycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameOfSender.setText(chats.get(position).getNameOfSender());
        holder.date.setText(chats.get(position).getDate());
        holder.text.setText(chats.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameOfSender,date,text;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfSender=itemView.findViewById(R.id.name_of_sender);
            date=itemView.findViewById(R.id.date_on_chat);
            text=itemView.findViewById(R.id.text_on_chat);

        }
    }



}