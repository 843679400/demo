package com.example.demo.modules.work;

import java.util.ArrayList;
import java.util.List;

/**
 * 成绩单
 */
public class Transcript{
    //姓名
    private String name;
    //分数
    private int fraction;
    //学科
    private String subject;

    //一个用来存放观察者的容器
    //当有新的观察者来观察成绩单时，都会被存入到该集合中
    private List<Observer> list = new ArrayList<>();


    public String getName() {
        return name;
    }

    public int getFraction() {
        return fraction;
    }

    public String getSubject() {
        return subject;
    }


    //更改成绩单内容
    public void setTranscript(String name,int fraction,String subject){
        this.name = name;
        this.fraction = fraction;
        this.subject = subject;
        notifyAllObservers();
    }


    //加入观察者
    public void attach(Observer observer){
        list.add(observer);
    }

    //通知所有观察者
    public void notifyAllObservers(){
        list.forEach(v->{
            v.read();
        });
    }
}
