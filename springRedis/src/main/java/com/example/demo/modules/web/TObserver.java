package com.example.demo.modules.web;

import com.example.demo.modules.work.Observer;
import com.example.demo.modules.work.Transcript;

public class TObserver extends Observer {


    public TObserver() {

    }

    public TObserver(T t) {
        this.t = t;
        this.t.attach(this);
    }
    @Override
    public void read() {
        this.t.getNewList().forEach(v->{
            System.out.println(v);
        });
    }
}
