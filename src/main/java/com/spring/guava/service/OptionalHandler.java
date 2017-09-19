package com.spring.guava.service;

import com.google.common.base.Optional;
import org.apache.log4j.Logger;


/**
 * Created by ErnestCheng on 2017/9/19.
 */
public class OptionalHandler {
    private static final Logger LOGGER= Logger.getLogger(OptionalHandler.class);

    public static  void testOptional(){
        Optional possible=Optional.of(5);
        LOGGER.info("potional.of:"+possible);
        LOGGER.info("判断是否引用缺失isPresent:"+possible.isPresent());
        LOGGER.info("得到possible里面的值get:"+possible.get());
        Optional optional1=Optional.fromNullable(null);
        Integer num=(Integer)optional1.or(1);
        LOGGER.error("引用缺少使用默认结果："+num);
        num=(Integer)optional1.orNull();
        LOGGER.error("引用缺失使用null:"+num);
    }


    public static void main(String [] arg){
        testOptional();
    }
}
