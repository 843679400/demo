package com.example.demo.modules.web;

public class Demo {
    public static void main(String[] args) {
        T t = new T();
        Thread thread = new Thread(t);
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(t);
        thread.start();
        thread1.start();
        thread2.start();


    }
}
