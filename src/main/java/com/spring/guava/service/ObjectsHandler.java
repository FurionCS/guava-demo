package com.spring.guava.service;

import com.google.common.base.Objects;
import com.spring.guava.model.User;
import org.apache.log4j.Logger;


/**
 * Created by ErnestCheng on 2017/9/19.
 */
public class ObjectsHandler {

    private static final Logger LOGGER= Logger.getLogger(ObjectsHandler.class);

    public static void testObjects(){
        //jdk7也引用入了Objects 有相似的方法
        LOGGER.info("Objects判断是否相等"+Objects.equal("a","a"));
        LOGGER.info("Objects判断是否相等"+Objects.equal(null,"a"));
        LOGGER.info("Objects判断是否相等"+Objects.equal("a",null));
        LOGGER.info("Objects判断是否相等"+Objects.equal(null,null));
        LOGGER.info("Object生成hashCode:"+Objects.hashCode("a"));

    }
    public static void main(String [] args){
        testObjects();
    }
}
