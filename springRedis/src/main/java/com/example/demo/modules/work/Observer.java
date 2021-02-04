package com.example.demo.modules.work;

import com.example.demo.modules.web.T;

/**
 * 观察者
 */
public abstract class Observer {
    //成绩单
    protected Transcript transcript;

    protected T t;

    //阅读成绩单的姿势
    public abstract void read();
}
