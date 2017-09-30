package com.spring.guava.shared;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
public interface DomainEventSubscirber<T extends DomainEvent> {
    public void handler(T event);
}
