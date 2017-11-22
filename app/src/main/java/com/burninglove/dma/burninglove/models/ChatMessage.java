package com.burninglove.dma.burninglove.models;

import java.util.Date;

/**
 * Created by Amalia Shaliha on 11/7/2017.
 */

public class ChatMessage {
    private int chatId;
    private String profilePictureURI;
    private String content;
    private Date time;
    private String nickname;

    public ChatMessage(int chatId, String profilePictureURI, String content, Date time, String nickname) {
        this.profilePictureURI = profilePictureURI;
        this.content = content;
        this.time = time;
        this.nickname = nickname;
        this.chatId = chatId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfilePictureURI() {
        return profilePictureURI;
    }

    public void setProfilePictureURI(String profilePictureURI) {
        this.profilePictureURI = profilePictureURI;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

}
