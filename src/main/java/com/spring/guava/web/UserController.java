package com.spring.guava.web;

import com.spring.guava.model.User;
import com.spring.guava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mr.Cheng on 2017/9/29.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/reg")
    public void reg(){
        User user=new User();
        user.setId(1);
        user.setUserName("cs");
        userService.reg(user);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public void login(){
        User user=new User();
        user.setId(2);
        user.setUserName("dbb");
        userService.login(user);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public void logout(){
        User user=new User();
        user.setId(2);
        user.setUserName("zss");
        userService.logout(user);
    }
}
