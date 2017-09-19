package com.spring.guava.service;

import com.google.common.base.Preconditions;
import com.spring.guava.model.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ErnestCheng on 2017/9/19.
 */
public class PreconditionsHandler {
    private static final Logger LOGGER= Logger.getLogger(OptionalHandler.class);

    public static void testPreconitions(){
        try {
            //用法：用于检测boolean是否为null,写在检查传递给方法的参数
            Preconditions.checkArgument(1 > 2);
        }catch (IllegalArgumentException e){
            LOGGER.error("检查boolean是否为true："+e);
        }
        try{
            LOGGER.info("检查value是否为null："+Preconditions.checkNotNull(5));
            Preconditions.checkNotNull(null);
        } catch (NullPointerException e){
            LOGGER.error("检查value是否为null:"+e);
        }

        try{
            User user=new User();
            Preconditions.checkState(user.isSuperMan());
        }catch (IllegalStateException e){
            LOGGER.error("检查对象的某些状态："+e);
        }

        try{
            List<User> userList=new ArrayList<>();
            //用于判断index作为索引值对列表，字符串，数组是否有效 index>=0 && index<size
            Preconditions.checkElementIndex(0,userList.size());
        }catch (IndexOutOfBoundsException e){
            LOGGER.error("检查index对于列表是否有效:"+e);
        }
        try{
            List<User> userList=new ArrayList<>();
            //用于判断index作为索引值对列表，字符串，数组是否有效 index>=0 && index<=size
            Preconditions.checkPositionIndex(0,userList.size());
        }catch (IndexOutOfBoundsException e){
            LOGGER.error("检查index对于列表是否有效:"+e);
        }

        try{
            List<User> userList=new ArrayList<>();
            //用于判断index作为索引值对列表，字符串，数组是否有效 index>=0 && index<=size
            Preconditions.checkPositionIndexes(0,0,userList.size());
        }catch (IndexOutOfBoundsException e){
            LOGGER.error("检查index对于列表是否有效:"+e);
        }

    }


    public static void main(String [] args){
        testPreconitions();
    }
}
