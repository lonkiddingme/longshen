package com.cdvcloud.douting.service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

import java.util.List;
import java.util.Map;

/**
 * 主播管理
 *
 * @author SongYuanKun
 * @date 2017/10/25
 */
public interface AnchorService extends BaseService {
    /**
     * 创建主播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 主播ID
     */
    String createAnchor(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 更新主播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    long updateAnchorById(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 删除主播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 删除条数
     */
    long deleteAnchor(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 查询主播，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 主播圈帖子
     */
    Map<String, Object> queryAnchorById(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 查询主播，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    Pages queryAnchor4Page(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 更新主播状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updateAnchorStatus(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 查询所有主播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    List<Map<String, Object>> queryAnchorAll(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 查询主播数量
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    long countAnchor(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 批量更新主播
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updateAnchors(CommonParameters commonParameters, Map<String, Object> param);
}
