package com.example.demo;

import com.example.demo.modules.pojo.Person;
import com.example.demo.modules.redis.RedisDemo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringRedisApplicationTests {

    @Autowired
    private RedisDemo redisDemo;

    @Test
    void contextLoads() {
        System.out.println("验证测试类能否使用");
    }

    @Test
    void save(){
        redisDemo.save("key","xxx");
    }

    @Test
    void read(){
        System.out.println(redisDemo.read("key"));
    }

    @Test
    void delete(){
        redisDemo.delete("one");
    }

    @Test
    void saveHash(){
        Map<String, Person> map = new HashMap<>();
        map.put("中文",new Person("jake",1));
        map.put("2",new Person("jake",1));
        map.put("3",new Person("jake",1));
        redisDemo.saveHash("one",map);
    }

    @Test
    void readHash(){
        redisDemo.readHash("one").entrySet().forEach(v->{
            System.out.println(v.getKey()+"  "+v.getValue());
        });
    }

    @Test
    void saveList(){
        List<Person> list = new ArrayList<>();
        list.add(new Person("小明",22));
        list.add(new Person("小明1",22));
        list.add(new Person("小明2",22));
        redisDemo.saveList("two",list);
    }

    @Test
    void readList(){
        redisDemo.readList("two");
    }

}
