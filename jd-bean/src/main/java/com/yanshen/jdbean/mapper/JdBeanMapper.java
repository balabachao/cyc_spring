package com.yanshen.jdbean.mapper;

import com.yanshen.jdbean.utils.JdBean;

public interface JdBeanMapper {
    void addBean(JdBean code);
    JdBean find(JdBean c);
}
