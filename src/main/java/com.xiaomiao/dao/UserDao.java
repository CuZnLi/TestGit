package com.xiaomiao.dao;

import com.xiaomiao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository  //通过Spring注解定义一个DAO
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL = "SELECT COUNT(*) FROM t_user WHERE user_name=? AND password=?";

    private final static String MATCH_COUNT_SQL1 = "SELECT * FROM t_user WHERE user_name=?";

    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET "+
            "last_visit=?,last_ip=?,credits=? WHERE user_id=?";

    @Autowired  //自动注入JdbcTemplate的Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String passWord){
//        Integer integer = jdbcTemplate.queryForObject(MATCH_COUNT_SQL, Integer.class, new Object[]{userName, passWord});
//        return integer
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,passWord},Integer.class);

    }

    public User findUserByName( final String userName){
        final User user = new User();
        //final 是因为后面匿名类中用到
        jdbcTemplate.query(MATCH_COUNT_SQL1, new Object[]{userName},
                new RowCallbackHandler() {
                    //使用匿名类方式实现回调函数
                    @Override
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
                });
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),
        user.getLastIp(),user.getCredits(),user.getUserId()});
    }


}
