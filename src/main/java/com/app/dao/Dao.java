package com.app.dao;

import com.app.model.User;
import java.util.List;

public interface Dao {

    public List<User> readAll();

    public void update(User current, User candidate);

    public List<Message>

}
