package com.ssy.petition.dao.base;

import com.ssy.petition.entity.base.example.BaseExample;

import java.util.List;

/**
 * 基础持久层
 *
 * @param <T> 通用实体
 * @author jcm
 */
public interface BaseMapper<T> {

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加记录
     *
     * @param record 待添加记录
     * @return 添加结果
     */
    int insert(T record);

    /**
     * 添加记录 仅处理不为 null 字段
     *
     * @param record 待添加记录
     * @return 添加结果
     */
    int insertSelective(T record);

    /**
     * 根据主键查找记录
     *
     * @param id 主键
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 修改记录 仅处理不为 null 字段
     *
     * @param record 待修改记录
     * @return 修改结果
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 修改记录
     *
     * @param record 待修改记录
     * @return 修改结果
     */
    int updateByPrimaryKey(T record);

    /**
     * 根据 example 查询列表
     * @param example 查询参数
     * @return 记录列表
     */
    List<T> selectByExample(BaseExample example);

}
