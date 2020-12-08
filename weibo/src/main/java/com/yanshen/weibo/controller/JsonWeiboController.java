package com.yanshen.weibo.controller;


import com.yanshen.weibo.Util.RedisUtil;
import com.yanshen.weibo.entity.Weibo;
import com.yanshen.weibo.service.TelAreaService;
import com.yanshen.weibo.service.WeiboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @Resource
    TelAreaService telAreaService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisUtil redisUtil;
    private Logger logger = LoggerFactory.getLogger(WeiboController.class);

    @GetMapping("/tel/{tel}")
    public Weibo find(@PathVariable("tel") String tel, HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        if (!ip.equals("60.191.75.18")&&!ip.equals("122.224.174.250")){
            Weibo weibo = checklimit(ip, tel);
            if (weibo.getMessage() != "Success") {
                return weibo;
            }
        }
        Weibo weibo =new Weibo();
        if (tel.length() < 10) {
            weibo.setMessage("当前用户无信息");
            weibo.setTel(tel);
            return weibo;
        }
        verify(tel, weibo);
        Weibo query = weiboService.get(weibo);
        if (null == query) {
            weibo.setMessage("当前用户无信息");
            weibo.setTel(tel);
            weiboService.insert(weibo);
            return weibo;
        } else {
            query.setMessage("Success");
        }
        String area = telAreaService.postTest(query.getTel());
        String ipaddress = telAreaService.getIpurl(ip);
        //记录查询人ip
        Weibo ins = new Weibo();
        ins.setIp(ip);
        ins.setTel(query.getTel());
        ins.setUrl(query.getUrl());
        ins.setIpaddress(ipaddress);
        ins.setArea(area);
        ins.setUid(query.getUid());

        if (tel.length() < 11) {
            query.setUrl(area);
            ins.setArea(query.getTel());
        }
        weiboService.insert(ins);
        return query;
    }

    public void verify(String tel, Weibo weibo) {
        Weibo message = new Weibo();

        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        boolean flag = tel.matches(regex);
        if (tel.length() < 11) {
            weibo.setUid(tel);
        } else {
            weibo.setTel(tel);
        }


    }

    public Weibo checklimit(String ip, String tel) {
        Weibo weibo = new Weibo();
        String islimit = redisUtil.get("islimit");
        if ("1".equals(islimit)){
            String value = redisUtil.get(ip + "MinLimte");
            String dayValue = redisUtil.get(ip + "DayLimte");
            if (value == null) {
                redisUtil.set(ip + "MinLimte", 1);
                redisUtil.expire(ip + "MinLimte", 60);//设置过期时间60秒
                if (dayValue == null) {
                    redisUtil.set(ip + "DayLimte", 1);
                    redisUtil.expire(ip + "DayLimte", 86400);//设置过期时间24hours
                } else {
                    redisUtil.incr(ip + "DayLimte", 1);  //加一次
                    int parseIntDay = Integer.parseInt(dayValue);
                    if (parseIntDay > 5) {
                        weibo.setMessage("一天内只有5次机会哦");
                        return weibo;
                    }
                }
            } else {
                int parseInt = Integer.parseInt(value);
                if (dayValue != null) {
                    int daylimit = Integer.parseInt(dayValue);
                    if (daylimit > 5) {
                        weibo.setMessage("一天内只有5次机会哦");
                        weibo.setTel(tel);
                        weibo.setIp(ip);
                        return weibo;
                    }
                }
                if (parseInt >= 1) {
                    weibo.setMessage("1分钟内只能查询一次");
                    weibo.setTel(tel);
                    weibo.setIp(ip);
                    return weibo;
                }
                redisUtil.incr(ip + "DayLimte", 1);
            }
            //
            weibo.setMessage("Success");
            weibo.setIp(ip);
            weibo.setTel(tel);
        }else {
            weibo.setMessage("Success");
            weibo.setIp(ip);
            weibo.setTel(tel);
        }
        return weibo;
    }
}
