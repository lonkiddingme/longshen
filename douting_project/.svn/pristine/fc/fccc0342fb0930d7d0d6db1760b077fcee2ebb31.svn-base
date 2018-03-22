package com.cdvcloud.douting.service;

import java.util.List;
import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗播直播间 节目管理
 *
 * @author zhangyuelong
 * @date 2017/10/30
 */
public interface BroadcastColumnService extends BaseService {


    /**
     * 创建逗播直播节目
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    String createBroadcastColumn(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 删除 节目
     *
     * @param commonParameters
     * @param param
     * @return
     */
    long deleteBroadcastColumn(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 更新逗播直播节目
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    long updateBroadcastColumnById(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 查询逗播节目，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 逗播直播间
     */
    Map<String, Object> queryBroadcastColumnById(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 逗播直播间节目，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    Pages queryBroadcastColumn4Page(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 更新节目状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updateBroadcastColumnStatus(CommonParameters commonParameters, Map<String, Object> param);

    List<Map<String,Object>> queryBroadcastColumnByBroadcastId(String broadcastId, Map<String, Object> backParam, Map<String, Object> sortParam);
}
