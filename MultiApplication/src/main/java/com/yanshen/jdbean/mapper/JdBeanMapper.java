package com.yanshen.jdbean.mapper;

import com.yanshen.jdbean.utils.JdBean;

import java.util.List;

public interface JdBeanMapper  {
    void addBean(JdBean code);
    JdBean find(JdBean c);
    List<JdBean> findByType(Integer type);
    List<JdBean> findByIP(String ip);
    List<JdBean> findAll();
    void update(List<JdBean> list);
}
