package com.spring.guava.userBus.event;

import com.spring.guava.shared.DomainEvent;
import lombok.Data;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
@Data
public class UserDomainEvent<T> extends DomainEvent {
    private T Date;
}
