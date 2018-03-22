package com.cdvcloud.douting.dao;

import java.util.Map;

import com.cdvcloud.rms.util.Pages;

/**
 * 逗看图文直播管理
 *
 * @author Administrator
 */
public interface PictureTextDao {

    String createPictureText(Map<String, Object> pictureTextMap);

    long updatePictureText( Map<String, Object> update);

    long deletePictureText(String id);

    /**
     * 通过Id查询逗看图文直播
     *
     * @param id 直播间id
     * @return 直播间
     */
    Map<String, Object> queryPictureTextById(String id);


    /**
     * 逗看图文直播分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    Pages queryPictureText4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap);

    /**
     * 更新逗逗看图文直播
     * 目前没有此功能
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updatePictureTextStatus(String id, Map<String, Object> param);

}
