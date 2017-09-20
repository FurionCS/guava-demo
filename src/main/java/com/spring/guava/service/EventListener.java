package com.spring.guava.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.eventbus.Subscribe;
import com.spring.guava.model.User;
import org.apache.log4j.Logger;


/**
 * 消息监听,消费，处理方法
 * Created by Mr.Cheng on 2017/9/20.
 */
public class EventListener {

    private static final Logger LOGGER= Logger.getLogger(EventListener.class);

    LoadingCache cacheUser= CacheBuilder.newBuilder().build(new CacheLoader<Integer, User>() {
        @Override
        public User load(Integer id) throws Exception {
            return null;
        }
    });

    @Subscribe
    public void listenAddUser(User user) throws InterruptedException {
        Thread.sleep(4000);
        cacheUser.put(user.getId(),user);
        LOGGER.info("我插入数据---"+user);
    }

    public User getUserByCache(Integer id) {
       return (User)cacheUser.getUnchecked(id);
    }
}
