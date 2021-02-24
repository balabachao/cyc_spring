package com.yanshen.weibo.mapper;


import com.yanshen.weibo.entity.Weibo;

public interface WeiBoMapper {
   Weibo find(Weibo weibo);
   int insert(Weibo weibo);
}
