package com.app.services;

import com.app.dao.UserDao;
import com.app.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_find_by_id_user_service_method() {
        // given
        UserService userService = new UserService(userDao);

        // when
        userService.findById(1);

        // then
        Mockito.verify(userDao).findById(1);
    }

    @Test
    public void test_find_by_id_of_dao_inside_service() {
        // given
        UserService userService = new UserService(userDao);

        // when
        Mockito.when(userDao.findById(1)).thenReturn(createTestEntity());
        User actual = userService.findById(1);

        // then
        Assert.assertEquals("Natasha", actual.getName());
        Assert.assertEquals("Director", actual.getJob());
        Mockito.verify(userDao).findById(1);
    }

    private User createTestEntity() {
        User user = new User();
        user.setName("Natasha");
        user.setJob("Director");
        return user;
    }

}
