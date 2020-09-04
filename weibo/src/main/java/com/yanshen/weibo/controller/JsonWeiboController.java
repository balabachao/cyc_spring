package com.yanshen.weibo.controller;

import com.yanshen.weibo.entity.Weibo;
import com.yanshen.weibo.service.WeiboService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>cyc_spring</h3>
 * <p>RestFul</p>
 *
 * @author : YanChao
 * @date : 2020-09-02 10:33
 **/

@CrossOrigin
@RestController
@RequestMapping("/weibo")
public class JsonWeiboController {
    @Autowired
    WeiboService weiboService;
    private Logger logger = LoggerFactory.getLogger(WeiboController.class);

    @GetMapping("/tel/{tel}")
    public Weibo find(@PathVariable("tel") String tel) {
        verify(tel);
        Weibo weibo = new Weibo();
        if (tel.length() < 11) {
            weibo.setUid(tel);
            logger.info(" 查询的uid是：{}", tel);
        } else {
            weibo.setTel(tel);
            logger.info(" 查询的手机号是：{}", tel);
        }
        Weibo o = weiboService.get(weibo);
        if (null == o) {
            weibo.setMessage("当前用户无信息");
            weibo.setTel(tel);
            weiboService.insert(weibo);
            return weibo;
        } else {
            o.setMessage("Success");
        }
        if (tel.length() < 11) {
            o.setUrl("反查的手机号是：" + o.getTel());
        }
        weiboService.insert(o);
        return o;
    }

    public Weibo verify(String tel) {
        Weibo message = new Weibo();

        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        boolean flag = tel.matches(regex);
        if (!flag) {
            message.setMessage("数据格式不正确");
            return message;
        } else {
            message.setMessage("Success");
        }
        if (tel.length() < 10) {
            message.setMessage("长度异常");
        }


        return message;
    }
}
