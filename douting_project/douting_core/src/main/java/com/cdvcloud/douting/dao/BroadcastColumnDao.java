package com.cdvcloud.douting.dao;

import java.util.List;
import java.util.Map;

import com.cdvcloud.rms.util.Pages;

/**
 * 逗播管理
 */
public interface BroadcastColumnDao {

    String createBroadcastColumn(Map<String, Object> paramMap);

    long updateBroadcastColumnById(String id, Map<String, Object> update);

    long deleteBroadcastColumn(List<String> ids);

    /**
     * 通过Id查询查询栏目
     *
     * @return 栏目
     */
    Map<String, Object> queryBroadcastColumnById(String id);


    /**
     * @param ids
     * @param backMap
     * @param sortMap
     * @return
     */
    List<Map<String, Object>> queryBroadcastColumnByIds(List<String> ids, Map<String, Object> backMap, Map<String, Object> sortMap);

    /**
     * 栏目分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    Pages queryBroadcastColumn4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap);

    /**
     * 更新逗播直播间栏目状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updateBroadcastColumnStatus(List<String> ids, Map<String, Object> param);

    List<Map<String,Object>> queryRecommendBroadcastColumnsByBroadCastIds(List<String> broadcastIds, Map<String, Object> backMap, Map<String, Object> sortMap, Map<String, Object> queryMap);

    List<Map<String, Object>> queryRecommendBroadcastColumnsByIds(List<String> broadcastIds, Map<String, Object> backMap, Map<String, Object> sortMap);
}
