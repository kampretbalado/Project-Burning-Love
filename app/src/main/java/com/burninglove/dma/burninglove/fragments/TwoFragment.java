package com.burninglove.dma.burninglove.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.burninglove.dma.burninglove.ChatRoomActivity;
import com.burninglove.dma.burninglove.R;

public class TwoFragment extends Fragment{

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