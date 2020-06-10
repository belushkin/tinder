package com.app.dao;

import com.app.connection.ConnectionFactory;
import com.app.connection.DB;
import com.app.entities.User;
import com.app.utils.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @InjectMocks
    private DB db;
    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;
    @Mock
    private ConnectionFactory connectionFactory;

    @Test
    public void test_find_user_by_id_with_mocked_result_set() throws Exception {
        // given
        Mockito.when(connectionFactory.createConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);

        UserDao userDao = new UserDao(db);

        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getString("name")).thenReturn("Natasha");
        Mockito.when(resultSetMock.getString("job")).thenReturn("Director");
        Mockito.when(resultSetMock.getString("username")).thenReturn("nata");

        Mockito.when(resultSetMock.next()).thenReturn(true).thenReturn(false);

        // when
        Mockito.when(mockConnection.
                createStatement().
                executeQuery(Mockito.any())
        ).
                thenReturn(resultSetMock);

        User user = userDao.findById(1);

        // then
        Assert.assertEquals("Natasha", user.getName());
        Assert.assertEquals("Director", user.getJob());
        Assert.assertEquals("nata", user.getUsername());
    }

    @Test
    public void test_find_user_by_id_with_mocked_result_set_when_no_user_exists() throws SQLException, IOException {
        // given
        Mockito.when(connectionFactory.createConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);

        UserDao userDao = new UserDao(db);
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.next()).thenReturn(false);

        // when
        Mockito.when(mockConnection.
                createStatement().
                executeQuery(Mockito.any())
        ).
                thenReturn(resultSetMock);

        User user = userDao.findById(999);

        // then
        Assert.assertNull(user);

//        Properties properties = Config.INSTANCE.getProperties("/config.ini");
//        DB db = new DB(new ConnectionFactory(properties));
//        UserDao userDao = new UserDao(db);
//
//        System.out.println(userDao.findById(13));
    }

    @Test
    public void test_find_all_users_with_mocked_result_set() throws Exception {
        // given
        Mockito.when(connectionFactory.createConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);

        UserDao userDao = new UserDao(db);

        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getString("name")).thenReturn("Natasha");
        Mockito.when(resultSetMock.getString("job")).thenReturn("Director");
        Mockito.when(resultSetMock.getString("username")).thenReturn("nata");

        Mockito.when(resultSetMock.next()).thenReturn(true).thenReturn(false);

        // when
        Mockito.when(mockConnection.
                createStatement().
                executeQuery(Mockito.any())
        ).
                thenReturn(resultSetMock);

        List<User> users = userDao.getAll();

        // then
        Assert.assertEquals("Natasha", users.get(0).getName());
        Assert.assertEquals("Director", users.get(0).getJob());
        Assert.assertEquals("nata", users.get(0).getUsername());
    }
}
