package com.app.services;

import com.app.dao.MessageDao;
import com.app.entities.Message;
import com.app.entities.User;

import java.util.List;

public class MessageService {

    private final MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getMessages(User me, User you) {
        return messageDao.getMessages(me, you);
    }

    public void addMessage(User from, User to, String message) {
        messageDao.addMessage(from, to, message);
    }

}
