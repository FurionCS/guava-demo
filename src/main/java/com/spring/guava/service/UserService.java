package com.spring.guava.service;

import com.spring.guava.model.User;


import com.spring.guava.shared.DomainEventPublisher;
import com.spring.guava.userBus.event.UserApplicationEvent;
import com.spring.guava.userBus.event.UserDomainEvent;
import com.spring.guava.userBus.event.UserEvent;
import com.spring.guava.userBus.event.UserEventType;
import com.spring.guava.userBus.userpublisher.UserEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Cheng on 2017/9/29.
 */
@Service
public class UserService implements ApplicationContextAware {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);

    private final UserEventPublisher userEventPublisher;

    private ApplicationContext applicationContext;
    /**
     * 注入UserService时注入UserEventPublisher
     * @param userEventPublisher
     */
    @Autowired
    public UserService(UserEventPublisher userEventPublisher){
        this.userEventPublisher=userEventPublisher;
    }
    /**
     * 注册用户,使用的是机遇guava的eventBus，支持同步和异步
     * 创建事件，发布事件，注册监听器，监听事件，处理方法
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
        userEventPublisher.asyncPublish(userEvent);
        LOGGER.info("我发布了注册事件");
    }

    /**
     * 登录,采用spring event 发布事件，默认只支持同步事件，需要异步可以在方法上加上异步注解
     * 创建事件，发布事件，绑定事件，监听事件，处理方法
     * @param user
     */
    public void login(User user){
        //做登陆工作
        LOGGER.info("我登陆了用户Id：{} 用户名称{}",user.getId(),user.getUserName());
        //定义事件
        UserApplicationEvent userEvent=new UserApplicationEvent("user_service",user.getUserName());
        //发布登录事件
        applicationContext.publishEvent(userEvent);
        LOGGER.info("我发布了登陆事件");
    }


    /**
     * 自定义方法
     * 定义事件，注册事件，发布事件，查找注册对象，调用处理方法
     * @param user
     */
    public void logout(User user){
        //做登出工作
        LOGGER.info("我登出了用户Id：{} 用户名称{}",user.getId(),user.getUserName());
        //定义事件
        UserDomainEvent userDomainEvent=new UserDomainEvent();
        userDomainEvent.setDate(user);
        DomainEventPublisher.publish(userDomainEvent);
        LOGGER.info("我发布了登出事件");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
