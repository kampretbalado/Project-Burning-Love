package com.burninglove.dma.burninglove.models;

import java.util.ArrayList;

/**
 * Created by Amalia Shaliha on 11/7/2017.
 */

public class ChatRoom {
    private int chatId;
    private boolean isPrivateChat;
    private String chatRoomName;
    private String latestChat;
    private String profilePicture;

    public ChatRoom(int chatId, boolean isPrivateChat, String chatRoomName, String latestChat) {
        this.chatId = chatId;
        this.isPrivateChat = isPrivateChat;
        this.chatRoomName = chatRoomName;
        this.latestChat = latestChat;
    }

    public ChatRoom(int chatId, boolean isPrivateChat, String chatRoomName, String latestChat, String profilePicture) {
        this.chatId = chatId;
        this.isPrivateChat = isPrivateChat;
        this.chatRoomName = chatRoomName;
        this.latestChat = latestChat;
        this.profilePicture = profilePicture;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public boolean isPrivateChat() {
        return isPrivateChat;
    }

    public void setPrivateChat(boolean privateChat) {
        isPrivateChat = privateChat;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public String getLatestChat() {
        return latestChat;
    }

    public void setLatestChat(String latestChat) {
        this.latestChat = latestChat;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

}
