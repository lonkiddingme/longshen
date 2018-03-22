package com.cdvcloud.douting.service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

import java.util.List;
import java.util.Map;

/**
 * 轮播图APP接口
 *
 * @author SongYuanKun
 * @date 2017/11/10
 */
public interface HomePageAppApiService extends BaseService{

    /**
     * 查询轮播图
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 轮播图
     */
    List<Map<String, Object>> queryCarousels(CommonParameters commonParameters, Map<String, Object> jsonMap);

    /**
     * 查询推荐内容
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 推荐
     */
    Pages queryRecommendContents(CommonParameters commonParameters, Map<String, Object> jsonMap);

    /**
     * 查询推荐直播
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 推荐
     */
    List<Map<String, Object>> queryRecommendBroadcasts(CommonParameters commonParameters, Map<String, Object> jsonMap);

    List<Map<String, Object>> queryRecommendVideoRooms(CommonParameters commonParameters, Map<String, Object> jsonMap);

    List<Map<String, Object>> queryRecommendBroadcastColumnsByBroadCastIds(CommonParameters commonParameters, Map<String, Object> jsonMap);

    List<Map<String, Object>> queryRecommendBroadcastColumns(CommonParameters commonParameters, Map<String, Object> jsonMap);

    Map<String, Object> getSystem(CommonParameters commonParameters, Map<String, Object> jsonMap);
}
