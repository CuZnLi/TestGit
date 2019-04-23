package com.xiaomiao.service;

import com.xiaomiao.dao.LoginLogDao;
import com.xiaomiao.dao.UserDao;
import com.xiaomiao.domain.LoginLog;
import com.xiaomiao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao){
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String userName, String password){
        int matchCount = userDao.getMatchCount(userName,password);
        return matchCount>0;
    }

    public User findUserByName(String userName){
        return userDao.findUserByName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(user.getCredits()+5);
        user.setLastVisit(new Date());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
