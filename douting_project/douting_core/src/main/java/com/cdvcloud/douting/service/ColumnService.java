package com.cdvcloud.douting.service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

import java.util.List;
import java.util.Map;

/**
 * 栏目管理
 *
 * @author SongYuanKun
 * @date 2017/10/25
 */
public interface ColumnService extends BaseService {
    /**
     * 创建栏目
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 栏目ID
     */
    String createColumn(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 更新栏目
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    long updateColumnById(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 删除栏目
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 删除条数
     */
    long deleteColumn(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 查询栏目，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 栏目圈帖子
     */
    Map<String, Object> queryColumnById(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 查询栏目，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    Pages queryColumn4Page(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 更新栏目状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updateColumnStatus(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 获取词标列表
     *
     * @param commonParameters commonParameters
     * @param jsonMap          请求参数
     * @return
     */
    List<Map<String, Object>> findList(CommonParameters commonParameters, Map<String, Object> jsonMap) throws Exception;

}
