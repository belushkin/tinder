package com.app.dao;

import com.app.connection.DB;
import com.app.entities.Message;
import com.app.entities.User;
import com.app.utils.MyLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao implements Dao<Message> {

    private final DB db;

    public MessageDao(DB db) {
        this.db = db;
    }

    public List<Message> getMessages(User me, User you) {
        MyLogger.info("Find all messages between 2 users: " + me.getUsername() + " and " + you.getUsername());

        List<Message> messages = new ArrayList<>();
        String sql = String.format("SELECT " +
                "m.*, uf.id as ufid, uf.name as ufname, uf.picture as ufpicture, ut.id as utid, ut.name as utname, ut.picture as utpicture " +
                "FROM messages m " +
                "JOIN users uf ON m.from_user_id = uf.id " +
                "JOIN users ut ON m.to_user_id = ut.id " +
                "WHERE m.from_user_id = %s AND m.to_user_id = %s OR m.from_user_id = %s AND m.to_user_id = %s " +
                "ORDER BY m.timestamp ASC", me.getId(), you.getId(), you.getId(), me.getId());
        try {
            ResultSet resultSet = this.db.executeQuery(sql);
            while (resultSet.next()) {
                messages.add(hydrate(resultSet));
            }
            return messages;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Message findById(int id) {
        MyLogger.info("Find message by id: " + id);

        String sql = String.format("SELECT " +
                "m.*, uf.id as ufid, uf.name as ufname, uf.picture as ufpicture, ut.id as utid, ut.name as utname, ut.picture as utpicture" +
                "FROM messages m " +
                "JOIN users uf ON m.from_user_id = uf.id " +
                "JOIN users ut ON m.to_user_id = ut.id " +
                "WHERE m.id = %s" +
                "ORDER BY m.timestamp DESC", id);
        try {
            ResultSet resultSet = this.db.executeQuery(sql);
            if (resultSet.next()) {
                return hydrate(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addMessage(User from, User to, String message) {
        MyLogger.info("User " + from.getName() + " messaged to " + to.getName());

        String insert = String.format(
                "INSERT INTO messages (text, from_user_id, to_user_id) VALUES ('%s', '%s', '%s')",
                message,
                from.getId(),
                to.getId()
        );
        this.db.execute(insert);
    }

    @Override
    public Message hydrate(ResultSet resultSet) throws SQLException {
        return new Message(
                resultSet.getInt("id"),
                resultSet.getString("text"),
                new User(
                        resultSet.getInt("ufid"),
                        resultSet.getString("ufname"),
                        resultSet.getString("ufpicture")
                ),
                new User(
                        resultSet.getInt("utid"),
                        resultSet.getString("utname"),
                        resultSet.getString("utpicture")
                ),
                resultSet.getTimestamp("timestamp").toLocalDateTime()
        );
    }
}
