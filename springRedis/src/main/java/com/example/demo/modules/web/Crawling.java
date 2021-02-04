package com.example.demo.modules.web;

import com.sun.istack.internal.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 爬取网页x
 */
public class Crawling {

    private static Logger log = Logger.getLogger(Crawling.class);

    private static Set<Body> bodyList = new HashSet<>();

    public static void main(String[] args) throws IOException {
        String url = "http://www.imomoe.ai/";
        String html = sourcePageHtml(url);
        analysisCopy(html,url);
        T t = new T();
        t.setList(new ArrayList<>(bodyList));
        new TObserver(t);
        Thread thread = new Thread(t);
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(t);
        thread.start();
        thread1.start();
        thread2.start();
    }

    /**
     * 根据网站链接获取网页源代码
     * @param url 网站链接
     * @return String 网页源代码
     * @throws IOException
     */
    private static String sourcePageHtml(String url) throws IOException {
        //定义一个字符串用来存储网页内容
        String result = "";
        //定义一个缓冲字符输入流
        BufferedReader in = null;
        //将string转换成url对象
        URL resultUrl = new URL(url);
        HttpURLConnection connection = null;
        connection = (HttpURLConnection) resultUrl.openConnection();
        //模拟浏览器访问
        // connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
        //开始实际的连接
        connection.connect();
        //初始化BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
        //用来临时存储抓取到的每一行的数据
        String line;
        while ((line = in.readLine()) != null) {
            //遍历抓取到的每一行并将其存取到result里面
            result += line;
            //log.info(line);
            //System.out.println(line);
        }
        return result;
    }

    /**
     * 分析网页数据提取有效信息
     * @param sourceData 网页源代码
     */
    private static void analysis(String sourceData,String sourceUrl){
        Document doc = Jsoup.parse(sourceData);
        Elements rows = doc.getElementsByTag("a");
        rows.forEach(v->{
            //获取标签中的属性值
            String url = v.attr("href");
            int index = url.indexOf("http");
            if(index==-1){
                url = sourceUrl+url;
            }
            Boolean b = isEffective(url);
            String title = v.text();
            if(b.compareTo(true)==0){
                bodyList.add(new Body(title,url));
                log.info("添加成功++"+title+":"+url);
            }
        });
    }

    /**
     * 分析网页数据提取有效信息
     * @param sourceData 网页源代码
     */
    private static void analysisCopy(String sourceData,String sourceUrl){
        Document doc = Jsoup.parse(sourceData);
        Elements rows = doc.getElementsByTag("a");
        rows.forEach(v->{
            //获取标签中的属性值
            String url = v.attr("href");
            int index = url.indexOf("http");
            if(index==-1){
                url = sourceUrl+url;
            }
            String title = v.text();
            bodyList.add(new Body(title,url));
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
            log.info("验证链接的有效性发生异常");
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
