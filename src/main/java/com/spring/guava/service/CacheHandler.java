package com.spring.guava.service;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Guava Cache两种创建方式
 * 1:cacheLoader
 * 2:callable
 * 两种方式都遵守同一个逻辑：get-if-absent-compute   获取缓存-如果没有-则计算
 * 但不同的在于cacheloader的定义比较宽泛，是针对整个cache定义的，可以认为是统一的根据key值load value的方法。而callable的方式较为灵活，允许你在get的时候指定。
 * Created by ErnestCheng on 2017/9/20.
 */
public class CacheHandler {

    public void testLoadingCache() throws ExecutionException {
        LoadingCache<String,List<String>> cahceBuilder=CacheBuilder
                .newBuilder()
                .build(new CacheLoader<String, List<String>>(){
                    @Override
                    public List<String> load(String key) throws Exception {
                        return getPOIListFromDB(key);
                    }
                });

        System.out.println("jerry value:"+cahceBuilder.apply("0101"));
        System.out.println("jerry value:"+cahceBuilder.get("0101"));
        System.out.println("peida value:"+cahceBuilder.get("0102"));
        System.out.println("peida value:"+cahceBuilder.apply("0102"));
    }


    Cache<String, List<String>> poiCache = CacheBuilder.newBuilder().build();
    @SuppressWarnings("unchecked")
    public List<String> getPOIList(final String cityId) {
        List returnList = null;
        try {
            returnList = poiCache.get(cityId, new Callable<List<String>>() {
                @Override
                public List<String> call(){
                    return getPOIListFromDB(cityId);
                }
            });
        } catch (ExecutionException e) {
            // 记日志
        }
        return Optional.fromNullable(returnList).or(Collections.EMPTY_LIST);
    }




    @SuppressWarnings("unchecked")
    private List<String> getPOIListFromDB(String cityId){
        System.out.println("getting from DB, please wait...");
        List<String> returnList = null;
        // 模仿从数据库中取数据
        try {
            Thread.sleep(1000);
            switch (cityId){
                case "0101" :
                    returnList = ImmutableList.of("望京", "望京西", "望京南", "望京北"); break;
                case "0102" :
                    returnList = ImmutableList.of("a", "b", "c", "d"); break;
            }
        } catch (Exception e) {
            // 记日志
        }
        return Optional.fromNullable(returnList).or(Collections.EMPTY_LIST);
    }


    public static void main(String [] args) throws ExecutionException {

        CacheHandler cacheHandler=new CacheHandler();
        for (int i = 0; i < 3; i++) {
            System.out.println("--- " + i + " ---");
            System.out.println(Arrays.toString(cacheHandler.getPOIList("0101").toArray()));
            System.out.println(Arrays.toString(cacheHandler.getPOIList("0102").toArray()));
            System.out.println(Arrays.toString(cacheHandler.getPOIList("0103").toArray()));
            System.out.println();
        }

        cacheHandler.testLoadingCache();

    }
}
