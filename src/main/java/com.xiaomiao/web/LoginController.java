package com.xiaomiao.web;

import com.xiaomiao.domain.User;
import com.xiaomiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller //将任何一个POJO的类标注为Spring MVC的控制器，处理HTTP请求
public class LoginController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/index.html")  //用来指定方法如何映射请求路径
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest httpServletRequest, LoginCommand loginCommand){
        Boolean isValid = userService.hasMatchUser(loginCommand.getUserName(),loginCommand.getPassWord());
        if (!isValid){
            return new ModelAndView("login","error","用户名或密码错误。");
        }else{
            User user = userService.findUserByName(loginCommand.getUserName());
            user.setLastIp(httpServletRequest.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            httpServletRequest.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }
    }

}
