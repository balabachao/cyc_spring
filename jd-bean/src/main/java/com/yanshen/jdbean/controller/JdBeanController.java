package com.yanshen.jdbean.controller;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

@RestController
@RequestMapping("/api")
public class JdBeanController {

    @Autowired
    JdBeanService addCodeService;


    @GetMapping("/bean/{code}")
    public MsgInfo bean(@PathVariable("code") String code) throws UnsupportedEncodingException {
        MsgInfo msgInfo =new MsgInfo();
        JdBean bean =new JdBean();
        if(isChinese(code)){
            msgInfo.setMessage("不能够输入中文! 甘霖娘");
            return msgInfo;
        };
        bean.setType(1);
        bean.setCode(code);
        checkCommit(bean);
        addCodeService.addService(bean);
        msgInfo.setMessage("添加种豆得豆成功!,"+nextTime());
        return msgInfo;

    }
    @GetMapping("/farm/{code}")
    public MsgInfo farm(@PathVariable("code") String code){
        MsgInfo msgInfo =new MsgInfo();
        JdBean bean =new JdBean();
        bean.setType(2);
        bean.setCode(code);
        checkCommit(bean);
        addCodeService.addService(bean);
        msgInfo.setMessage("添加农场互助码成功!,"+nextTime());
        return msgInfo;

    }
    @GetMapping("/pet/{code}")
    public MsgInfo pet(@PathVariable("code") String code){
        MsgInfo msgInfo =new MsgInfo();
        JdBean bean =new JdBean();
        bean.setType(3);
        bean.setCode(code);
        checkCommit(bean);
        addCodeService.addService(bean);
        msgInfo.setMessage("添加宠物码成功!,"+nextTime());
        return msgInfo;
    }

    public String nextTime(){
        String msg ="";
        String day = DateUtil.dateToFormatString( Calendar.getInstance().getTime(),"dd");
        int dd =Integer.valueOf(day);
        if (dd<=10&&dd>1){
            msg ="下次自动提交时间是本月10号凌晨2:01分";
        }
        if (dd<=20&&dd>10){
            msg ="下次自动提交时间是本月20号凌晨2:01分";
        }else {
            msg ="下次自动提交时间是下月1号凌晨2:01分";
        }
        return msg;

    }
    public void checkCommit(JdBean jdBean){
        JdBean data =addCodeService.findOne(jdBean);
        if (data!=null){
            throw new TipException("已经提交过了!");
        }
    }
    public  boolean isChinese(String str) throws UnsupportedEncodingException
    {
        int len = str.length();
        for(int i = 0;i < len;i ++)
        {
            String temp = URLEncoder.encode(str.charAt(i) + "", "utf-8");
            if(temp.equals(str.charAt(i) + ""))
                continue;
            String[] codes = temp.split("%");
//判断是中文还是字符(下面判断不精确，部分字符没有包括)
            for(String code:codes)
            {
                if(code.compareTo("40") > 0)
                    return true;
            }
        }
        return false;
    }

}
