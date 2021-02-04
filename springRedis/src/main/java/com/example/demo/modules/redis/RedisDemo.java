package com.example.demo.modules.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RedisDemo {

    @Autowired
    private RedisTemplate redisTemplate;

    public void save(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public String read(String key){
        return (String)redisTemplate.opsForValue().get(key);
    }

    public boolean delete(String key){
        return redisTemplate.delete(key);
    }

    public <T> HashOperations<String,String,T> saveHash(String key,Map<String,T> map){
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key,map);
        return hashOperations;
    }

    public <T> Map<String,T> readHash(String key){
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    public <T> Long saveList(String key, List<T> list){
        ListOperations listOperations = redisTemplate.opsForList();
        return listOperations.leftPushAll(key,list);
    }

    public <T> List<T> readList(String key){
        ListOperations listOperations = redisTemplate.opsForList();
        Long size = listOperations.size(key);
        List<T> list = new ArrayList<>();
        for(int i=0;i<size;i++){
            list.add((T)listOperations.index(key,i));
        }
        return list;
    }
}
