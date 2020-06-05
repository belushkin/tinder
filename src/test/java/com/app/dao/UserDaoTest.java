package com.app.dao;

import com.app.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoTest {
    @InjectMocks
    private DbConnection dbConnection;
    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_find_user_by_id_with_mocked_result_set() throws Exception {
        // given
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);

        UserDao userDao = new UserDao(dbConnection);

        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.getString("name")).thenReturn("Natasha");
        Mockito.when(resultSetMock.getString("job")).thenReturn("Director");
        Mockito.when(resultSetMock.getString("username")).thenReturn("nata");

        Mockito.when(resultSetMock.next()).thenReturn(true).thenReturn(false);

        // when
        Mockito.when(mockConnection.
                createStatement().
                executeQuery((String) Mockito.any())
        ).
                thenReturn(resultSetMock);

        User user = userDao.findById(1);

        // then
        Assert.assertEquals("Natasha", user.getName());
        Assert.assertEquals("Director", user.getJob());
        Assert.assertEquals("nata", user.getUsername());
    }

    @Test
    public void test_find_user_by_id_with_mocked_result_set_when_no_user_exists() throws SQLException {
        // given
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);

        UserDao userDao = new UserDao(dbConnection);
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.when(resultSetMock.next()).thenReturn(false);

        // when
        Mockito.when(mockConnection.
                createStatement().
                executeQuery((String) Mockito.any())
        ).
                thenReturn(resultSetMock);

        User user = userDao.findById(999);

        // then
        Assert.assertNull(user);

//        DbConnection dbConnection = new DbConnection();
//        dbConnection.getConnection();
//
//        UserDao userDao = new UserDao(dbConnection);
//
//        System.out.println(userDao.findById(6));
    }

}
