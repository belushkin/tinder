package com.app.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Message {
    private int id;
    private String text;
    private User from;
    private User to;
    private LocalDateTime localDateTime;

    public Message(int id, String text, User from, User to, LocalDateTime localDateTime) {
        this.id = id;
        this.text = text;
        this.from = from;
        this.to = to;
        this.localDateTime = localDateTime;
    }

    public String timePassed() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        long minutes = ChronoUnit.MINUTES.between(localDateTime, LocalDateTime.now());

        if (minutes < 60) {
            return minutes + " min";
        }

        return localDateTime.format(formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
