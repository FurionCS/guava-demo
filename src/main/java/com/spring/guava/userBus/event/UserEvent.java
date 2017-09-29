package com.spring.guava.userBus.event;

import com.spring.guava.eventBus.publish.DomainEvent;
import lombok.Data;

/**
 * Created by Mr.Cheng on 2017/9/29.
 */
@Data
public class UserEvent extends DomainEvent {
    private int userId;
    private String userName;
    private UserEventType type;

    @Override
    protected String identify() {
        return "user_event_publisher";
    }
}
