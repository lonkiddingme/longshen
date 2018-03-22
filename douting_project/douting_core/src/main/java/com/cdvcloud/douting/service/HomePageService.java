package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

/**
 * 首页直播间 节目管理
 *
 * @author zhangyuelong
 * @date 2017/11/06
 */
public interface HomePageService extends BaseService {
    /**
     * 创建首页
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 首页ID
     */
    String createHomePage(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 更新首页
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    long updateHomePageById(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 删除 逗播直播间
     *
     * @param commonParameters
     * @param param
     * @return
     */
    long deleteHomePage(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 查询首页，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 逗播直播间
     */
    Map<String, Object> queryHomePageById(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 首页分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    Pages queryHomePage4Page(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 统计总数
     *
     * @param commonParameters
     * @param param
     * @return
     */
    long countHomePage(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 发布
     *
     * @param commonParameters
     * @param param
     * @return
     */
    long releaseHomePage(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 查询启动页
     */
    Map<String, Object> queryHomePage(CommonParameters commonParameters, Map<String, Object> param);
}
