package com.spring.guava.userBus.event;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
@Data
@ToString
public class UserApplicationEvent  extends ApplicationEvent{
    private String name;

    public  UserApplicationEvent(Object source,String name){
        super(source);
        this.name=name;
    }
}
