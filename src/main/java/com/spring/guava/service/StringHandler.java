package com.spring.guava.service;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.sun.istack.internal.logging.Logger;

/**
 * Created by Mr.Cheng on 2017/9/19.
 */
public class StringHandler {
    private static final Logger LOGGER= Logger.getLogger(StringHandler.class);

    public static void testString(){
        //通过；连接，跳过null,joiner 是线程安全的，可以定义成static final常量
        Joiner joiner=Joiner.on(";").skipNulls();
        LOGGER.info("连接符："+joiner.join("cs",null,"robin","moore"));

        Joiner joiner2=Joiner.on(";").useForNull("oo");
        LOGGER.info("连接符："+joiner2.join("cs",null,"robin","moore"));
        //先定义规则：通过，分割，去除首尾空格，忽略结果中的空格,限制3个结果(如果小于实际能分割出来的，将保持原样），分割字符串
        LOGGER.info(Splitter.on(",").trimResults().omitEmptyStrings().limit(3).split("cs,,moore,,,robin").toString());

        LOGGER.info("判断字符串是否为nullorempty:"+Strings.isNullOrEmpty(""));
        LOGGER.info("字符串为空就设置成null:"+Strings.emptyToNull(""));
        LOGGER.info("字符串为null就设置成'':"+Strings.nullToEmpty(null));


        LOGGER.info("在字符串前加上某一个字符达到长度："+Strings.padStart("7", 3, '0'));//"007"
        LOGGER.info("在字符串前加上某一个字符达到长度："+Strings.padStart("2010", 3, '0'));//"2010"
        LOGGER.info("在字符串后加上某一个字符达到长度:"+Strings.padEnd("4.", 5, '0'));//"4.000"
        LOGGER.info("在字符串后加上某一个字符达到长度:"+Strings.padEnd("2010", 3, '!'));//"2010
        LOGGER.info("重复某个字符串多少次:"+Strings.repeat("hey", 3));//"heyheyhey"

        LOGGER.info("两个字符串前面相同部分："+Strings.commonPrefix("aaab", "aac"));//"aa"
        LOGGER.info("两个字符串后面相同的部分："+Strings.commonSuffix("aaac", "aac"));//"aac"
    }


    public static void main(String [] args){
        testString();
    }

}
