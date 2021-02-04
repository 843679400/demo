package com.example.demo.modules.work;

/**
 * 父母观察者
 */
public class ParentsObserver extends Observer {

    public ParentsObserver() {

    }

    public ParentsObserver(Transcript transcript) {
        this.transcript = transcript;
        this.transcript.attach(this);
    }


    @Override
    public void read() {
        System.out.println("父母："+this.transcript.getName()+"在学校过得咋样？");
    }

}
