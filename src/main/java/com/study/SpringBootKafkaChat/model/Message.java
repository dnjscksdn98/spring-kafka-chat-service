package com.study.SpringBootKafkaChat.model;

import javax.persistence.*;

@Entity
public class Message {

    // TODO: 사용자 연관 관계 추가 - ManyToOne, userId

    @Id
    @GeneratedValue
    private Long id;
    private String user;
    private String message;
    private String timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    protected Message() {

    }

    public Message(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    @Override
    public String toString() {
        return String.format("Message[user=%s, message=%s, timestamp=%s]", user, message, timestamp);
    }
}
