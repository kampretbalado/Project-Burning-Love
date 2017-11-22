package com.burninglove.dma.burninglove;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.burninglove.dma.burninglove.models.ChatMessage;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        List<ChatMessage> listChatMessage = new ArrayList<>();
        String nicknameDummy = "yeay";
        ThreadsAdapter listThreadAdapter = new ThreadsAdapter(this, listChatMessage, nicknameDummy);
        ListView listViewThread = (ListView) findViewById(R.id.thread_container);
        listViewThread.setAdapter(listThreadAdapter);
        listThreadAdapter.add(new ChatMessage("android.resource://com.burninglove.dma.burninglove/drawable/cat_sporty.jpg", "halo", new Date(), "yeay"));
        listThreadAdapter.add(new ChatMessage("android.resource://com.burninglove.dma.burninglove/drawable/cat_sporty.jpg", "halo juga", new Date(), "ulala"));
        listThreadAdapter.add(new ChatMessage("android.resource://com.burninglove.dma.burninglove/drawable/cat_sporty.jpg", "lagi apa?", new Date(), "yeay"));
        listThreadAdapter.add(new ChatMessage("android.resource://com.burninglove.dma.burninglove/drawable/cat_sporty.jpg", "lagi ngoding tekmob", new Date(), "ulala"));
        listThreadAdapter.add(new ChatMessage("android.resource://com.burninglove.dma.burninglove/drawable/cat_sporty.jpg", "wah asik bgt", new Date(), "yeay"));
    }

    private class ThreadsAdapter extends ArrayAdapter<ChatMessage>{
        private String nickname;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        public ThreadsAdapter(Context context, List<ChatMessage> chatMessageList, String nickname){
            super(context, 0, chatMessageList);
            this.nickname = nickname;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            FrameLayout view = (FrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.list_item_thread, null);
            ChatMessage chatMessage = getItem(position);
            if(chatMessage.getNickname().equals(nickname)){
                FrameLayout opponentView = (FrameLayout) view.findViewById(R.id.chat_opponent);
                opponentView.setVisibility(View.GONE);

                TextView chatContentView = (TextView) view.findViewById(R.id.chat_content_self);
                TextView chatTimeView = (TextView) view.findViewById(R.id.chat_time_self);

                chatContentView.setText(chatMessage.getContent());
                chatTimeView.setText(dateFormat.format(chatMessage.getTime()));
            }
            else{
                FrameLayout selfView = (FrameLayout) view.findViewById(R.id.chat_self);
                ImageView profilePictureView = (ImageView) view.findViewById(R.id.profile_picture_opponent);
                TextView chatContentView = (TextView) view.findViewById(R.id.chat_content_opponent);
                TextView chatTimeView = (TextView) view.findViewById(R.id.chat_time_opponent);
                selfView.setVisibility(View.GONE);

                Picasso.with(getContext()).load(chatMessage.getProfilePictureURI()).into(profilePictureView);
                chatContentView.setText(chatMessage.getContent());
                chatTimeView.setText(dateFormat.format(chatMessage.getTime()));
            }

            return view;
        }
    }

}
