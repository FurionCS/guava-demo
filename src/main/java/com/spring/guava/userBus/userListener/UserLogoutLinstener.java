package com.spring.guava.userBus.userListener;

import com.spring.guava.model.User;
import com.spring.guava.shared.DomainEventPublisher;
import com.spring.guava.shared.DomainEventSubscirber;
import com.spring.guava.userBus.event.UserDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
@Service
public class UserLogoutLinstener  implements DomainEventSubscirber<UserDomainEvent> {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserLogoutLinstener.class);

    @PostConstruct
    public void init(){
        DomainEventPublisher.subscribe(UserDomainEvent.class,this);
    }

    @Override
    @Async
    public void handler(UserDomainEvent event) {
        try {
            Thread.sleep(5000);
            LOGGER.info("我接收了用户登出事件，{}",((User)event.getDate()).getUserName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
