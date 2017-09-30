package com.spring.guava.userBus.userListener;

import com.spring.guava.userBus.event.UserApplicationEvent;
import com.spring.guava.userBus.event.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
@Service
public class UserLoginListener implements ApplicationListener<UserApplicationEvent> {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserLoginListener.class);

    @Override
    @Async
    public void onApplicationEvent(UserApplicationEvent userApplicationEvent) {
        try {
            Thread.sleep(5000);
            LOGGER.info("我收到用户登录事件，我做发送了邮件");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
