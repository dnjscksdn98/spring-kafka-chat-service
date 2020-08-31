package com.study.SpringBootKafkaChat.model;

public class Message {

    private String user;
    private String message;
    private String timestamp;

    protected Message() {

    }

    public Message(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public Message(String user, String message, String timestamp) {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return String.format("Message[user=%s, message=%s, timestamp=%s]", user, message, timestamp);
    }
}
