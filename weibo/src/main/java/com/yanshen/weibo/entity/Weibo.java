package com.yanshen.weibo.entity;

public class Weibo {
    private String tel;
    private String uid;
    private String url;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Weibo{" +
                "tel='" + tel + '\'' +
                ", uid='" + uid + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
