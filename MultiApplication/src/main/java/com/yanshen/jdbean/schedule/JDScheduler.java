package com.yanshen.jdbean.schedule;

import com.alibaba.fastjson.JSONObject;
import com.yanshen.jdbean.service.JdBeanService;
import com.yanshen.jdbean.utils.JdBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import java.util.stream.Collectors;

@Component
public class JDScheduler {
    private static final Logger logger = LoggerFactory.getLogger(JDScheduler.class);

    @Autowired
    JdBeanService jdBeanService;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = null;

    public void getbeanData() {
        long starttime =System.currentTimeMillis()/1000;
        pushBark("推送助力码","开始推送");
        List<JdBean> beans = jdBeanService.findAll();
        Map<Integer,List<JdBean>> map =beans.stream().collect(Collectors.groupingBy(JdBean::getType));
        for(Map.Entry<Integer, List<JdBean>> entry : map.entrySet()){
            Integer type =entry.getKey();
            List<JdBean> mapValue = entry.getValue();
            push_update(mapValue,type);
        }
        long end =System.currentTimeMillis()/1000;
        pushBark("推送助力码","结束推送耗时:"+(end-starttime)+"秒");


    }

    public void push_update(List<JdBean> beans, Integer type) {
        String url = "";
        if (type == 1) {
            url = "http://api.turinglabs.net/api/v1/jd/bean/create/";//种豆
        }
        if (type == 2) {
            url = "http://api.turinglabs.net/api/v1/jd/farm/create/";//农场
        }
        if (type == 3) {
            url = "http://api.turinglabs.net/api/v1/jd/pet/create/";//萌宠
        }
        if (type == 4) {
            url = "http://api.turinglabs.net/api/v1/jd/pet/create/";//赚赚
        }
        if (type == 5) {
            url = "https://code.chiang.fun/api/v1/jd/jdcrazyjoy/create/";//joy
        }
        if (type == 6) {
            url = "https://code.chiang.fun/api/v1/jd/jdcash/create/";//joy
        }
        List<JdBean> update = new ArrayList<>();
        String finalUrl = url;
        AtomicReference<String> pushType = new AtomicReference<>();
        beans.forEach(bean -> {
            try {
                logger.info("开始提交!");
                String msg = getForbeans(finalUrl, bean.getCode());
                Thread.sleep(1000);
                StringBuilder sb = new StringBuilder(bean.getCode());
                if (bean.getIp().equals("qqgroup")) {
                    pushType.set("group");
                } else {
                    pushType.set("send");
                }
                logger.info("MSG:{}",msg);
                //pushQQ(msg + ":" + sb.toString(), type, pushType.get());
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("Error:{}:",e.getMessage());
            }
            bean.setSubmitted(1);
            update.add(bean);
        });
        jdBeanService.update(update);
    }

    public String getForbeans(String url, String code) {
        String urls = url + code;
        HttpHeaders header = new HttpHeaders();
        header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urls);
        response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<String>(header), String.class);
        //ResponseEntity<String> response = restTemplate.getForEntity(urls,String.class);
        JSONObject object = JSONObject.parseObject(response.getBody());
        String msg = object.getString("message");
        return msg;
    }

    public void pushQQ(String code, Integer type, String pu) {
        String cls = "";
        switch (type) {
            case 1:
                cls = "种豆得豆";
                break;
            case 2:
                cls = "农场互助";
                break;
            case 3:
                cls = "宠物";
        }
        String msg = "本次提交的'" + cls + "'结果是:" + code;
        String url = "https://push.xuthus.cc/" + pu + "/bf9ddab096f48226354ebe39fb8f7df0?c=";
        HttpHeaders header = new HttpHeaders();
        header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + msg);
        response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<String>(header), String.class);
        logger.info("QQ发送成功！接口返回信息为：{}", response.getBody());
    }
    public void pushBark(String t,String m){
        String url = "https://api.day.app/4ppVFBUZxhEnxPzumvtsdF/"+t+"/"+m;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        response =restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class);
        logger.info("Bark发送成功！接口返回信息为：{}", response.getBody());
    }
}
