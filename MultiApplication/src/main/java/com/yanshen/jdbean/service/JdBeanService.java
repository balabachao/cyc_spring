package com.yanshen.jdbean.service;

import com.yanshen.jdbean.mapper.JdBeanMapper;
import com.yanshen.jdbean.utils.JdBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class JdBeanService {
    @Autowired
    JdBeanMapper jdBeanMapper;
    public void addService(JdBean jdBean){
        jdBeanMapper.addBean(jdBean);
    }

    public JdBean findOne(JdBean jdBean){
       return jdBeanMapper.find(jdBean);
    }
    public List<JdBean> findByType(Integer type){
        return jdBeanMapper.findByType(type);
    }
    public List<JdBean> findAll(){
        return jdBeanMapper.findAll();
    }
    public List<JdBean> findByIP(String ip){
        List<JdBean> list =jdBeanMapper.findByIP(ip);
        if (!CollectionUtils.isEmpty(list)){
            list.forEach(jdBean -> {
                if (1==jdBean.getSubmitted()){
                    jdBean.setMsg("已提交");
                }else {
                    jdBean.setMsg("未提交");
                }
                switch (jdBean.getType()){
                    case 1:
                        jdBean.setCodeType("种豆码");
                        break;
                    case 2:
                        jdBean.setCodeType("农场码");
                        break;
                    case 3:
                        jdBean.setCodeType("宠物码");
                        break;
                }
            });
        }
        return list;
    }
    public void update(List<JdBean> list){
        jdBeanMapper.update(list);
    }
}
