package com.spring.guava.service;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.spring.guava.model.User;
import com.sun.istack.internal.logging.Logger;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 消息生产，发送,user为事件，eventbus为事件总线，向事件总线注册监听（订阅），事件总线发布消息
 * Created by Mr.Cheng on 2017/9/20.
 */
public class EventProduct {
    private static final Logger LOGGER= Logger.getLogger(EventProduct.class);

    public static void main(String [] args) throws InterruptedException {
        EventBus eventBus=new EventBus("name");
        EventListener eventListener=new EventListener();
        eventBus.register(eventListener);
        Executor executor = Executors.newFixedThreadPool(10);
        AsyncEventBus asyncEventBus=new AsyncEventBus("asyncName",executor);
        asyncEventBus.register(eventListener);

        eventBus.post(new User(1,"cs1",false));
        LOGGER.info("发送完成1");
        eventBus.post(new User(2,"cs2",false));
        LOGGER.info("发送完成2");
        eventBus.post(new User(3,"cs3",true));
        LOGGER.info("发送完成3");
        LOGGER.info("我从缓存中获得："+eventListener.getUserByCache(1));

        asyncEventBus.post(new User(4,"cs4",true));
        LOGGER.info("发送完成4");
        asyncEventBus.post(new User(5,"cs5",true));
        LOGGER.info("发送完成5");
        asyncEventBus.post(new User(6,"cs6",true));
        LOGGER.info("发送完成6");
        LOGGER.info("睡眠中ing");
        Thread.sleep(20000);
        LOGGER.info("睡眠结束");
        LOGGER.info("我从缓存中获得："+eventListener.getUserByCache(6));

    }
}
