package com.spring.guava.userBus.userListener;

import com.google.common.eventbus.Subscribe;
import com.spring.guava.userBus.event.UserEvent;
import com.spring.guava.userBus.event.UserEventType;
import com.spring.guava.userBus.userpublisher.UserEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 监听器，消费者
 * Created by Mr.Cheng on 2017/9/29.
 */
@Service
public class UserRegisterListenr {


    private  static final Logger LOGGER= LoggerFactory.getLogger(UserRegisterListenr.class);

    /**
     * 监听器
     * @param userEventPublisher
     */
    @Autowired
    public UserRegisterListenr(UserEventPublisher userEventPublisher){
        userEventPublisher.register(this);
    }

    /**
     * 处理方法
     */
    @Subscribe
    public void regHandler(UserEvent userEvent) throws InterruptedException {
        if(Objects.equals(userEvent.getType(), UserEventType.reg)){
            Thread.sleep(5000);
            //发送邮件
            LOGGER.info("send emial:welcome to,join us ! {}",userEvent.getUserName());
        }
    }
}
