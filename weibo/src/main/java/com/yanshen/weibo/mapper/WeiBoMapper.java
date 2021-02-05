package com.yanshen.weibo.mapper;


import com.yanshen.weibo.entity.Weibo;

public interface WeiBoMapper {
   Weibo find1(Weibo weibo);
   int insert(Weibo weibo);
}
