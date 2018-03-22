package com.cdvcloud.douting.dao;

import java.util.List;
import java.util.Map;

import com.cdvcloud.rms.util.Pages;

/**
 * 首页dao层
 *
 * @author SongYuanKun
 * @date 2017/11/1
 */
public interface HomePageDao {
    /**
     * 创建首页
     *
     * @param homePageMap 首页
     * @return 首页ID
     */
    String createHomePage(Map<String, Object> homePageMap);

    /**
     * 通过Id更新首页
     *
     * @param id     更新首页的主键
     * @param update 更新数据
     * @return 更新条数
     */
    long updateHomePageById(String id, Map<String, Object> update);

    /**
     * 删除首页
     *
     * @param ids 需要删除首页的主键
     * @return 删除条数
     */
    long deleteHomePage(String id);

    /**
     * 查询首页，通过Id查询
     *
     * @param id 首页id
     * @return 首页
     */
    Map<String, Object> queryHomePageById(String id);

    /**
     * 批量更新首页
     *
     * @param ids    需要更新的主键
     * @param update 更新字段
     * @return
     */
    long updateHomePages(List<String> ids, Map<String, Object> update);

    /**
     * 分页查询
     *
     * @param sortMap
     * @param queryMap
     * @param backMap
     * @param currentPageMap
     * @param pageNumMap
     * @return
     */
    Pages queryHomePage4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap);


    /**
     * 统计数量
     *
     * @param queryMap
     * @return
     */
    long countHomePage(Map<String, Object> queryMap);

    /**
     * 发布
     *
     * @param queryMap
     * @return
     */
    long releaseHomePage(List<Map<String, Object>> map);

    /**
     * 查询要发布的临时数据
     *
     * @return
     */
    List<Map<String, Object>> queryHomePageForRelease(List<String> ids, Map<String, Object> sortMap, Map<String, Object> backMap);

    /**
     * 删除现有同类型的已发布首页
     */
    long deleteRelease(String type);

    //启动页
    String createStartUpHomePage(String type, Map<String, Object> queryMap);


    //查询启动页
    Map<String, Object> queryHomePage(Map<String, Object> queryMap);
}
