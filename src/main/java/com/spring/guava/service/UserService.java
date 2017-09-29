package com.spring.guava.service;

import com.spring.guava.model.User;


import com.spring.guava.userBus.event.UserEvent;
import com.spring.guava.userBus.event.UserEventType;
import com.spring.guava.userBus.userpublisher.UserEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Cheng on 2017/9/29.
 */
@Service
public class UserService {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);

    private final UserEventPublisher userEventPublisher;
    /**
     * 注入UserService时注入UserEventPublisher
     * @param userEventPublisher
     */
    @Autowired
    public UserService(UserEventPublisher userEventPublisher){
        this.userEventPublisher=userEventPublisher;
    }
    /**
     * 注册用户
     * @param user
     */
    public void reg(User user){
        //做注册工作
        LOGGER.info("我注册了用户Id：{} 用户名称{}",user.getId(),user.getUserName());
        //定义事件
        UserEvent userEvent=new UserEvent();
        userEvent.setUserId(user.getId());
        userEvent.setUserName(user.getUserName());
        userEvent.setType(UserEventType.reg);
        //发布注册事件
        userEventPublisher.publish(userEvent);
    }
}
