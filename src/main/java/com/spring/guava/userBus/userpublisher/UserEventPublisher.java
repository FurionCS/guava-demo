package com.spring.guava.userBus.userpublisher;

import com.spring.guava.eventBus.publish.GuavaDomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Cheng on 2017/9/29.
 */
@Service
public class UserEventPublisher extends GuavaDomainEventPublisher {
    @Override
    public String identify() {
        return "user_event_publisher";
    }
}
