package com.burninglove.dma.burninglove.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.burninglove.dma.burninglove.ChatRoomActivity;
import com.burninglove.dma.burninglove.R;
import com.burninglove.dma.burninglove.helper.DatabaseHelper;
import com.burninglove.dma.burninglove.models.ChatRoom;
import com.burninglove.dma.burninglove.util.ImageUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TwoFragment extends Fragment{
    DatabaseHelper db;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_two, container, false);

        List<ChatRoom> listChatRoom = new ArrayList<>();
        ChatRoomAdapter listChatRoomAdapter = new ChatRoomAdapter(getContext(), listChatRoom);
        ListView listViewThread = (ListView) v.findViewById(R.id.chatrooms_container);
        listViewThread.setAdapter(listChatRoomAdapter);

        listChatRoomAdapter.add(new ChatRoom(2, true, "Demia", "ok", ""+R.drawable.profile_pictures, new Date()));
        listChatRoomAdapter.add(new ChatRoom(1, false, "Group Olahraga(30)", "hahaha", ""+R.drawable.group_profile, new Date()));
//        listChatRoomAdapter.add(new ChatRoom(1, true, "Luna", "halo halo halo", ""+R.drawable.cat_sporty, new Date()));
//        listChatRoomAdapter.add(new ChatRoom(2, false, "Group Olahraga(30)", "bakikuk", ""+R.drawable.cat_sporty, new Date()));

        return v;
    }

    private class ChatRoomAdapter extends ArrayAdapter<ChatRoom> {
        private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        public ChatRoomAdapter(Context context, List<ChatRoom> chatRoomList){
            super(context, 0, chatRoomList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            FrameLayout view = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.list_item_chatrooms, null);
            ChatRoom chatRoom = getItem(position);

            FrameLayout frameLayoutChat = (FrameLayout) view.findViewById(R.id.frameLayoutChatRoom);

            frameLayoutChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ChatRoomActivity.class);

                    startActivity(intent);
                }
            });

            ImageView profilePictureView = (ImageView) view.findViewById(R.id.profile_picture_chatroom);
            TextView chatRoomNameView = (TextView) view.findViewById(R.id.username_chatroom);
            TextView lastChatView = (TextView) view.findViewById(R.id.lastchat_chatroom);
            TextView chatRoomTimeView = (TextView) view.findViewById(R.id.timestamp_chat);

            profilePictureView.setImageBitmap(ImageUtility.decodeSampledBitmapFromResource(getResources(), Integer.parseInt(chatRoom.getProfilePictureURI()), 100, 100));
            chatRoomNameView.setText(chatRoom.getChatRoomName());
            lastChatView.setText(chatRoom.getLatestChat());
            chatRoomTimeView.setText(dateFormat.format(chatRoom.getTime()));

            return view;
        }
    }

}