package com.app.connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionFactoryTest {
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection mockConnection;

    @Test
    public void test_generating_connection() {
        Mockito.doReturn(mockConnection).when(connectionFactory).createConnection();
        connectionFactory.createConnection();
        verify(connectionFactory).createConnection();
    }
}
