package com.xiaomiao.service;

import com.xiaomiao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Date;

@ContextConfiguration("classpath*:/smart-context.xml")
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    private  UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Test
    public void hasMatchUser(){
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","123");
        Assert.assertTrue(b1);
        Assert.assertTrue(!b2);
    }

    @Test
    public void findUserByName(){
        User user = userService.findUserByName("admin");
        Assert.assertEquals(user.getUserName(),"admin");
    }

    @Test
    public void loginSuccess(){
        User user = userService.findUserByName("admin");
        userService.loginSuccess(user);

    }
}
