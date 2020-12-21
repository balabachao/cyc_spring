package com.yanshen.jdbean.service;

import com.yanshen.jdbean.mapper.JdBeanMapper;
import com.yanshen.jdbean.utils.JdBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
