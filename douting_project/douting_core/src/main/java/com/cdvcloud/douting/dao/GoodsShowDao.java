package com.cdvcloud.douting.dao;

import java.util.Map;

import com.cdvcloud.rms.util.Pages;

/**
 * 逗看商品栏管理
 *
 * @author Administrator
 */
public interface GoodsShowDao {

    String createGoodsShow(Map<String, Object> goodsShowMap);

    long updateGoodsShow(String id, Map<String, Object> update);

    long deleteGoodsShow(String id);

    /**
     * 通过Id查询逗看商品栏
     *
     * @param id 直播间id
     * @return 直播间
     */
    Map<String, Object> queryGoodsShowById(String id);


    /**
     * 逗看商品栏分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    Pages queryGoodsShow4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap);

    /**
     * 更新逗看商品栏
     * 目前没有此功能
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updateGoodsShowStatus(String id, Map<String, Object> param);

}
