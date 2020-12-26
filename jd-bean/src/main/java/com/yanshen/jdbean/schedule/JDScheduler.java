package com.yanshen.jdbean.schedule;

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

@Component
public class JDScheduler {
    private static final Logger logger = LoggerFactory.getLogger(JDScheduler.class);

    @Autowired
    JdBeanService jdBeanService;
    RestTemplate restTemplate =new RestTemplate();
    ResponseEntity<String> response = null;

    public  void getbeanData(){
        List<JdBean> beans =jdBeanService.findByType(1);
        List<JdBean> farms =jdBeanService.findByType(2);
        List<JdBean> pets =jdBeanService.findByType(3);
        List<JdBean> zzs =jdBeanService.findByType(4);
        //push_update(beans,1);
        push_update(beans,2);
        //push_update(farms,2);

    }

    public  void push_update(List<JdBean> beans,Integer type){
        String url ="";
        if (type==1){
            url ="http://api.turinglabs.net/api/v1/jd/bean/create/";
        }
        if (type==2){
            url="http://api.turinglabs.net/api/v1/jd/farm/create/";
        }
        if (type==3){
            url ="http://api.turinglabs.net/api/v1/jd/pet/create/";
        }
        if (type==4){
            url ="http://api.turinglabs.net/api/v1/jd/pet/create/";
        }
        List<JdBean> update =new ArrayList<>();
        String finalUrl = url;
        beans.forEach(bean->{
            try {
                getForbeans(finalUrl,bean.getCode());
                Thread.sleep(5000);
                StringBuilder sb = new StringBuilder(bean.getCode());
               // pushQQ(sb.replace(4,7,"****").toString(),type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bean.setSubmitted(1);
            update.add(bean);
        });
        jdBeanService.update(update);
    }
    public  void  getForbeans(String url,String code){
        String urls =url+code;
        HttpHeaders header = new HttpHeaders();
        header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urls);
        response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,new HttpEntity<String>(header),String.class);
        //ResponseEntity<String> response = restTemplate.getForEntity(urls,String.class);

        logger.info("信息成功！接口返回信息为：{}",response.getBody());
    }

    public void pushQQ(String code,Integer type){
        String cls ="";
        switch (type){
            case 1 :cls="种豆得豆";
            break;
            case 2 :cls="农场互助";
            break;
            case 3 :cls="宠物";
        }
        String msg ="助力码提交成功!本次提交的'"+cls+"'助力码是:"+code;
        String url = "https://push.xuthus.cc/group/bf9ddab096f48226354ebe39fb8f7df0?c=";
        HttpHeaders header = new HttpHeaders();
        header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+msg);
        response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,new HttpEntity<String>(header),String.class);
        logger.info("QQ发送成功！接口返回信息为：{}",response.getBody());
    }
}
