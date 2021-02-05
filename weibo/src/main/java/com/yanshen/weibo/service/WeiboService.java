package com.yanshen.weibo.service;


import com.yanshen.weibo.entity.Weibo;
import com.yanshen.weibo.mapper.WeiBoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WeiboService {
    @Autowired
    WeiBoMapper weiBoDAO;
    public Weibo get(Weibo weibo){
        Weibo weib =weiBoDAO.find1(weibo);
        return weib;
    }

    public void insert(Weibo weibo){
        try {
            int a=  weiBoDAO.insert(weibo);
            //System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
