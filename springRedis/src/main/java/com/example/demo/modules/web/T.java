package com.example.demo.modules.web;

import com.example.demo.modules.work.Observer;
import lombok.Data;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class T implements Runnable{

    private List<Body> list = new ArrayList<>();

    private List<Body> newList = new ArrayList<>();

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void run() {
        while (list.size()>0){
            Body body = list.get(0);
            list.remove(0);
            boolean b = isEffective(body.getUrl());
            if(b==true){
                newList.add(body);
            }
            if(list.size()==0){
                notifyAllObservers();
            }
            //System.out.println(Thread.currentThread().getName()+"--"+body);
        }
    }

    //加入观察者
    public void attach(Observer observer){
        observers.add(observer);
    }

    //通知所有观察者
    public void notifyAllObservers(){
        observers.forEach(v->{
            v.read();
        });
    }

    /**
     * 验证链接的有效性
     * @param url
     * @return true 有效;false无效
     * @throws IOException
     */
    private static Boolean isEffective(String url){
        HttpURLConnection connection = null;
        String strMessage = "";
        try {
            URL resultUrl = new URL(url);
            connection = (HttpURLConnection) resultUrl.openConnection();
            connection.setRequestMethod("HEAD");
            //开始实际的连接
            connection.connect();
            strMessage = connection.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(strMessage == null){
            return false;
        }
        if (strMessage.compareTo("Not Found") == 0) {
            return false;
        }
        connection.disconnect();
        return true;
    }
}
