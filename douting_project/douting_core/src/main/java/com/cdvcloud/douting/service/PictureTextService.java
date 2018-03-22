package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗看图文直播管理
 *
 * @author zhangyuelong
 * @date 2017/11/01
 */
public interface PictureTextService extends BaseService {


    /**
     * 创建逗看图文直播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    String createPictureText(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 删除 逗看图文直播
     *
     * @param commonParameters
     * @param param
     * @return
     */
    long deletePictureText(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 更新逗看图文直播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    long updatePictureText(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 查询逗看图文直播，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 逗播直播间
     */
    Map<String, Object> queryPictureTextById(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 逗看图文直播，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    Pages queryPictureText4Page(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 更新逗看图文直播状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long updatePictureTextStatus(CommonParameters commonParameters, Map<String, Object> param);
}
