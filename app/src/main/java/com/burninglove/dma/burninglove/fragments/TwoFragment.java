package com.burninglove.dma.burninglove.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.burninglove.dma.burninglove.ChatRoomActivity;
import com.burninglove.dma.burninglove.R;
import com.burninglove.dma.burninglove.helper.DatabaseHelper;
import com.burninglove.dma.burninglove.models.ChatMessage;
import com.burninglove.dma.burninglove.models.ChatRoom;
import com.burninglove.dma.burninglove.util.ImageUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends Fragment{

    DatabaseHelper db;

    private FrameLayout frameLayoutChat;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DatabaseHelper(getActivity().getApplicationContext());

        List<ChatRoom> rooms = new ArrayList<ChatRoom>();

        frameLayoutChat = (FrameLayout) getView().findViewById(R.id.frameLayoutChatFriend);

        frameLayoutChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatRoomActivity.class);
                
                startActivity(intent);
            }
        });
    }

}