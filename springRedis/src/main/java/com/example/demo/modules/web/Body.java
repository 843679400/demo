package com.example.demo.modules.web;

import lombok.Data;

import java.util.Objects;

/**
 * 链接实体
 */
@Data
public class Body {
    /** 标题 */
    private String title;
    /** 标题链接 */
    private String url;

    public Body(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public Body() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Body body = (Body) o;
        if(Objects.equals(url, body.url)){
            body.title = title + "-" +body.title;
        }
        return Objects.equals(url, body.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
