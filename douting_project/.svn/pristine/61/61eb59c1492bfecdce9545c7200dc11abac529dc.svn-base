package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗看商品栏管理
 *
 * @author zhangyuelong
 * @date 2017/11/01
 */
public interface GoodsShowService extends BaseService {


    /**
     * 创建逗看商品栏
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    String createGoodsShow(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 删除 逗看商品栏
     *
     * @param commonParameters
     * @param param
     * @return
     */
    long deleteGoodsShow(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 更新逗看商品栏
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    long updateGoodsShow(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 查询逗看商品栏，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 逗播直播间
     */
    Map<String, Object> queryGoodsShowById(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 逗看商品栏，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    Pages queryGoodsShow4Page(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 更新逗看商品栏状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updateGoodsShowStatus(CommonParameters commonParameters, Map<String, Object> param);
}
