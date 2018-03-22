package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 逗看视频直播管理
 *
 * @author zhangyuelong
 * @date 2017/10/31
 */
public interface LiveChannelService extends BaseService {


    /**
     * 创建直播間直播通道
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    ResponseObject createLiveChannel(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 删除 直播通道
     *
     * @param commonParameters
     * @param param
     * @return
     */
    ResponseObject deleteLiveChannel(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 开始拉流
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    ResponseObject start4Json(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 停止拉流
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 
     */
    ResponseObject stop4Json(CommonParameters commonParameters, Map<String, Object> param);
    /**
     * APP紧急停播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 
     */
    ResponseObject closeLiveChannel(CommonParameters commonParameters, Map<String, Object> param);
    /**
     * APP恢复直播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 
     */
    ResponseObject newchannelkaiqi(CommonParameters commonParameters, Map<String, Object> param);
    /**
     * 开始收录
     */
    ResponseObject startInclude(CommonParameters commonParameters, Map<String, Object> param);
    
    /**
     * 停止收录
     * @param commonParameters
     * @param param
     * @return
     */
    ResponseObject stopInclude(CommonParameters commonParameters, Map<String, Object> param);
}
