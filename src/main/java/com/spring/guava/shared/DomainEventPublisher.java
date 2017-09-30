package com.spring.guava.shared;

import com.google.common.collect.Lists;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
public class DomainEventPublisher {
    //定义事件注册中心
    private static ConcurrentHashMap<Class<? extends DomainEvent>,List<DomainEventSubscirber<? extends DomainEvent>>> subscriberMap=new ConcurrentHashMap<>();

    /**
     * 定义事件类型为DomainEvent的注册器
     * @param domainEventClass  事件类
     * @param domainEventSubscirber 注册对象
     * @param <T>
     */
    public synchronized  static <T extends  DomainEvent> void subscribe(Class<T> domainEventClass,DomainEventSubscirber<T> domainEventSubscirber){
        //从注册事件中心获得 事件类为domainEventClass 的注册对象
        List<DomainEventSubscirber<? extends DomainEvent>> domainEventSubscirberList=subscriberMap.get(domainEventClass);
        //判断注册对象中是否有domainEventSubscirber
        if (domainEventSubscirberList == null) {
            domainEventSubscirberList = Lists.newArrayList();
        }
        if(!domainEventSubscirberList.contains(domainEventSubscirber)){
            domainEventSubscirberList.add(domainEventSubscirber);
            subscriberMap.put(domainEventClass, domainEventSubscirberList);
        }
    }

    /**
     * 定义事件类型为DomainEvent的发布器
     * @param domainEvent
     * @param <T>
     */
    public static <T extends DomainEvent> void publish(final T  domainEvent){
        if(domainEvent == null){
            throw new IllegalArgumentException("domain event is null");
        }
        List<DomainEventSubscirber<? extends DomainEvent>> subscirbers=subscriberMap.get(domainEvent.getClass());
        if (subscirbers != null && !subscirbers.isEmpty()) {
            for (DomainEventSubscirber subscriber : subscirbers) {
                subscriber.handler(domainEvent);
            }
        }
    }
}
