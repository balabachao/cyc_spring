package com.yanshen.colorsize.controller;

import com.baidu.aip.imageprocess.AipImageProcess;
import org.json.JSONObject;

import java.util.HashMap;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "你的 App ID";
    public static final String API_KEY = "你的 Api Key";
    public static final String SECRET_KEY = "你的 Secret Key";

    public static void main(String[] args) {
        // 初始化一个AipImageProcess
        AipImageProcess client = new AipImageProcess(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String path = "test.jpg";
        JSONObject res = client.colourize(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

    }

/*    public void sample(AipBodyAnalysis client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();


        // 参数为本地路径
        String image = "test.jpg";
        JSONObject res = client.imageQualityEnhance(image, options);
        System.out.println(res.toString(2));

        // 参数为二进制数组
        byte[] file = readFile("test.jpg");
        res = client.imageQualityEnhance(file, options);
        System.out.println(res.toString(2));
    }*/
}
