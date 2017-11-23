package com.burninglove.dma.burninglove;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.burninglove.dma.burninglove.helper.DatabaseHelper;
import com.burninglove.dma.burninglove.models.ChatMessage;
import com.burninglove.dma.burninglove.util.ImageUtility;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        setTitle("kasel");
        List<ChatMessage> listChatMessage = new ArrayList<>();
        String nicknameDummy = "kasel";
        ThreadsAdapter listThreadAdapter = new ThreadsAdapter(this, listChatMessage, nicknameDummy);
        ListView listViewThread = (ListView) findViewById(R.id.thread_container);
        listViewThread.setAdapter(listThreadAdapter);

        int chatId = 2;

        db = new DatabaseHelper(getApplicationContext());
        List<ChatMessage> cms = db.getAllChatsFromId(chatId);
        Log.e("testDB", cms.toString());
        for (ChatMessage cm : cms) {
            listThreadAdapter.add(new ChatMessage(cm.getChatId(), ""+R.drawable.cat_sporty, cm.getContent(), cm.getTime(), cm.getNickname()));
        }
       /*
        listThreadAdapter.add(new ChatMessage(2, ""+R.drawable.cat_sporty, "halo juga", new Date(), "watashi"));
        listThreadAdapter.add(new ChatMessage(2, ""+R.drawable.cat_sporty, "lagi apa?", new Date(), "demia"));
        listThreadAdapter.add(new ChatMessage(2, ""+R.drawable.cat_sporty, "lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob", new Date(), "Me"));
        listThreadAdapter.add(new ChatMessage(2, ""+R.drawable.cat_sporty, "wah asik bgt lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob lagi ngoding tekmob", new Date(), "demia"));
        */
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
                RelativeLayout opponentView = (RelativeLayout) view.findViewById(R.id.chat_opponent);
                opponentView.setVisibility(View.GONE);

                TextView chatContentView = (TextView) view.findViewById(R.id.chat_content_self);
                TextView chatTimeView = (TextView) view.findViewById(R.id.chat_time_self);

                chatContentView.setText(chatMessage.getContent());
                chatTimeView.setText(dateFormat.format(chatMessage.getTime()));
            }
            else{
                RelativeLayout selfView = (RelativeLayout) view.findViewById(R.id.chat_self);
                ImageView profilePictureView = (ImageView) view.findViewById(R.id.profile_picture_opponent);
                TextView chatContentView = (TextView) view.findViewById(R.id.chat_content_opponent);
                TextView chatTimeView = (TextView) view.findViewById(R.id.chat_time_opponent);
                selfView.setVisibility(View.GONE);

                //Picasso.with(getContext()).load(chatMessage.getProfilePictureURI()).error(Color.GRAY).into(profilePictureView);
                profilePictureView.setImageBitmap(ImageUtility.decodeSampledBitmapFromResource(getResources(), Integer.parseInt(chatMessage.getProfilePictureURI()), 100, 100));
                chatContentView.setText(chatMessage.getContent());
                chatTimeView.setText(dateFormat.format(chatMessage.getTime()));
            }

            return view;
        }
    }

}
