/*
package com.yanshen.jdbean.mapper;



import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;

*/
/**
 * @Auther: taohaifeng
 * @Date: 2018/9/29 8:56
 * @Description: Mybatis Dao 层基础功能: 定义了基础查询, 分页, 新增, 修改, 删除 Dao 方法
 *				如有基础 DAO 无法实现的, 需自行实现
 *//*

public interface BaseDAO<E,T> extends BaseMapper<E> {
    */
/**
     * 查找全部: 无传入参数
     * @return
     *//*

    List<T> find();

    */
/**
     * 查找: 根据条件查找
     * @param entity
     * @return
     *//*

    List<T> find(T entity);
    */
/**
     * 分页查找
     * @return
     *//*

    Page<T> page();

    */
/**
     * 分页查找: 根据条件查找
     * @param entity
     * @return
     *//*

    Page<T> page(T entity);

    */
/**
     * 查找单条数据
     * @param id
     * @return
     *//*

    E findOne(String id);
    E findOne(Integer id);
    E findOne(Long id);
    E findOne(E entity);

    */
/**
     * 查询一条 DTO
     * @param entity
     * @return
     *//*

    @DS(Constances.WR.READ)
    T findDTOOne(T entity);
    T findDTOOne(String id);
    T findDTOOne(Long id);
    T findDTOOne(Integer id);

    */
/**
     * 新增/保存
     * 传入参数: 具体实体类
     * @param entity
     * @return
     *//*

    int insert(E entity);

    */
/**
     * 更新/修改
     * 传入参数: 具体实体类
     * @param entity
     * @return
     *//*

    int update(E entity);

    */
/**
     * 删除
     * @param id
     * @return
     *//*

    int delete(String id);
    int delete(Integer id);
    int delete(Long id);
    int delete(E entity);

}
*/
