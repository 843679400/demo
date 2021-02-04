package com.example.demo.modules.work;

/**
 * 同学观察者
 */
public class ClassmateObserver extends Observer {

    public ClassmateObserver() {
    }

    public ClassmateObserver(Transcript transcript) {
        this.transcript = transcript;
        this.transcript.attach(this);
    }

    @Override
    public void read() {
        System.out.println("同学："+this.transcript.getName()+"！一会放假回家，去峡谷干翻天");
    }
}
