package com.yanshen.weibo.service;


import com.yanshen.weibo.entity.Weibo;
import com.yanshen.weibo.mapper.WeiBoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WeiboService {
    @Resource
    WeiBoMapper weiBoDAO;
    public Weibo get(Weibo weibo){
        Weibo weib=new Weibo();
        weib=weiBoDAO.find1(weibo);
        if (null==weib){
            weib=weiBoDAO.find2(weibo);
        }
        if (null==weib){
            weib=weiBoDAO.find3(weibo);
        }
        if (null==weib){
            weib=weiBoDAO.find4(weibo);
        }
        if (null==weib){
            weib=weiBoDAO.find5(weibo);
        }
        if (null==weib){
            weib=weiBoDAO.find6(weibo);
        }
        if (null==weib){
            weib=weiBoDAO.find7(weibo);
        }
        if (null==weib){
            weib=weiBoDAO.find8(weibo);
        }
        if (null==weib){
            weib=weiBoDAO.find9(weibo);
        }
        if (null==weib){
            weib=weiBoDAO.find10(weibo);
        }

        return weib;
    }

    public void insert(Weibo weibo){
      int a=  weiBoDAO.insert(weibo);
        System.out.println(a);

    }

}
