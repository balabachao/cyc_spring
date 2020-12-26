package com.yanshen.jdbean.controller;

import com.yanshen.jdbean.schedule.JDScheduler;
import com.yanshen.jdbean.service.JdBeanService;
import com.yanshen.jdbean.utils.DateUtil;
import com.yanshen.jdbean.utils.JdBean;
import com.yanshen.jdbean.utils.MsgInfo;
import com.yanshen.jdbean.exception.TipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JdBeanController {

    @Autowired
    JdBeanService addCodeService;
    @Autowired
    JDScheduler jdScheduler;


    @GetMapping("/bean/{code}")
    public MsgInfo bean(@PathVariable("code") String code, HttpServletRequest request) throws UnsupportedEncodingException {
        JdBean bean = new JdBean();
        MsgInfo msgInfo = new MsgInfo();
        bean.setType(1);
        bean.setCode(code);
        bean.setIp(getIP(request));
        checkBean(bean);
        addCodeService.addService(bean);
        msgInfo.setMessage("添加种豆得豆成功!," + nextTime());
        return msgInfo;

    }

    @GetMapping("/farm/{code}")
    public MsgInfo farm(@PathVariable("code") String code, HttpServletRequest request) throws UnsupportedEncodingException {
        JdBean bean = new JdBean();
        MsgInfo msgInfo = new MsgInfo();
        checkBean(bean);
        bean.setType(2);
        bean.setCode(code);
        bean.setIp(getIP(request));
        addCodeService.addService(bean);
        msgInfo.setMessage("添加农场互助码成功!," + nextTime());
        return msgInfo;

    }

    @GetMapping("/pet/{code}")
    public MsgInfo pet(@PathVariable("code") String code, HttpServletRequest request) throws UnsupportedEncodingException {
        isChinese(code);
        JdBean bean = new JdBean();
        MsgInfo msgInfo = new MsgInfo();
        bean.setType(3);
        bean.setCode(code);
        bean.setIp(getIP(request));
        checkBean(bean);
        addCodeService.addService(bean);
        msgInfo.setMessage("添加宠物码成功!," + nextTime());
        return msgInfo;
    }
    @GetMapping("/zz/{code}")
    public MsgInfo zz(@PathVariable("code") String code, HttpServletRequest request) throws UnsupportedEncodingException {
        isChinese(code);
        JdBean bean = new JdBean();
        MsgInfo msgInfo = new MsgInfo();
        bean.setType(4);
        bean.setCode(code);
        bean.setIp(getIP(request));
        checkBean(bean);
        addCodeService.addService(bean);
        msgInfo.setMessage("添加赚赚码成功!," + nextTime());
        return msgInfo;
    }
    @GetMapping("/get")
    public MsgInfo getByip(HttpServletRequest request) throws UnsupportedEncodingException {
        JdBean bean = new JdBean();
        MsgInfo msgInfo = new MsgInfo();
        bean.setIp(request.getRemoteAddr());
        List<JdBean> list= addCodeService.findByIP(getIP(request));
        msgInfo.setData(list);
        return msgInfo;
    }
    @GetMapping("/push")
    public MsgInfo push(){
        MsgInfo msgInfo =new MsgInfo();
        jdScheduler.getbeanData();
        return msgInfo;
    }

    public String nextTime() {
        String msg = "";
        String day = DateUtil.dateToFormatString(Calendar.getInstance().getTime(), "dd");
        int dd = Integer.valueOf(day);
        if (dd <= 10 && dd > 1) {
            msg = "下次自动提交时间是本月10号凌晨2:01分";
        }
        if (dd <= 20 && dd > 10) {
            msg = "下次自动提交时间是本月20号凌晨2:01分";
        } else {
            msg = "下次自动提交时间是下月1号凌晨2:01分";
        }
        return msg;

    }

    public void checkBean(JdBean jdBean) throws UnsupportedEncodingException {
        //检查是否包含中文
        isChinese(jdBean.getCode());
        JdBean data = addCodeService.findOne(jdBean);
        if (data != null) {
            throw new TipException("已经提交过了!");
        }
    }

    public void isChinese(String str) throws UnsupportedEncodingException {
        if (str.length() < 8 || str.length() > 50) {
            throw new TipException("助力码异常");
        }
        int len = str.length();
        for (int i = 0; i < len; i++) {
            String temp = URLEncoder.encode(str.charAt(i) + "", "utf-8");
            if (temp.equals(str.charAt(i) + ""))
                continue;
            String[] codes = temp.split("%");
            //判断是中文还是字符(下面判断不精确，部分字符没有包括)
            for (String code : codes) {
                if (code.compareTo("40") > 0)
                    throw new TipException("不能提交中文!甘霖娘啊!");
            }
        }
    }
    public String getIP(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
