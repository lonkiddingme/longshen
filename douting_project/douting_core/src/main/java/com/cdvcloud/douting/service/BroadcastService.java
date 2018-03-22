package com.cdvcloud.douting.service;

import java.util.List;
import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗播管理
 *
 * @author zhangyuelong
 * @date 2017/10/30
 */
public interface BroadcastService extends BaseService {


    /**
     * 创建逗播直播間
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    String createBroadcast(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 删除 逗播直播间
     *
     * @param commonParameters
     * @param param
     * @return
     */
    long deleteBroadcast(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 更新逗播直播間
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    long updateBroadcastById(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 查询逗播直播间，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 逗播直播间
     */
    Map<String, Object> queryBroadcastById(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 逗播直播间，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    Pages queryBroadcast4Page(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 更新逗播直播间状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updateBroadcastStatus(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 查询所有直播间
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    public List<Map<String, Object>> queryBroadcastAll(CommonParameters commonParameters, Map<String, Object> param);

    long countBroadcast(CommonParameters commonParameters, Map<String, Object> param);

}
