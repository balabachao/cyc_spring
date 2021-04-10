package com.yanshen.jdbean.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yanshen.jdbean.schedule.JDScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/send")
public class QQReceiveMessageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = null;

    @PostMapping("/msg")
    public void getMessage(@RequestBody JSONObject jsonObject) {
       logger.warn("原始消息:{}",jsonObject);
        String group_id = jsonObject.getString("group_id");
        String question="";
/*        if (group_id.equals("127949740")) {
            // System.out.println(jsonObject);
             question = jsonObject.getString("raw_message");
            if (!question.startsWith("[CQ")) {
                logger.warn("收到消息:{}", question);
            }
        }*/
        String subType = jsonObject.getString("message_type");
        //logger.warn("消息类型:{}",subType);
        if (subType.equals("private")){
            question = jsonObject.getString("raw_message");
            JSONObject user = jsonObject.getJSONObject("sender");
            String userid = user.getString("user_id");

            logger.warn("收到消息:{}", question);
            pushQQ(question,userid);
        }

        // System.out.println(question);
        //pushQQ(question);
    }


    @PostMapping("/wt/{tel}")
    public void pushQQ(@PathVariable("tel") String qusetion,String qq) {
        String answer = QuarkAi(qusetion);
        String url = "http://127.0.0.1:5700/send_private_msg?user_id="+qq+"&message=";
        String group = "http://127.0.0.1:5700/send_group_msg?group_id=127949740&message=" + qusetion + "?&auto_escape=false";
        HttpHeaders header = new HttpHeaders();
        header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + answer);
        response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<String>(header), String.class);
        logger.info("发送个人QQ信息！接口返回信息为：{}", response.getBody());
    }

    @PostMapping("/quark")
    public String QuarkAi(String msg) {
        String answer = "";
        String url = "https://ai.sm.cn/quark/1/ai?format=json&q=";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + msg);
        HttpHeaders header = new HttpHeaders();
        header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<String>(header), String.class);
        //logger.info("夸克回答成功！{}", response.getBody());
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            JSONObject value = object.getJSONObject("value");
            answer = value.getString("answer");
            if (answer != null) {
                logger.warn("回答:{}", answer);
            }
        }
        return answer;
    }
}
