package com.app.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public final class Message {
    private final int id;
    private final String text;
    private final User from;
    private final User to;
    private final LocalDateTime localDateTime;

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

    public String getText() {
        return text;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
