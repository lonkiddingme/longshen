package com.cdvcloud.douting.dao;

import com.cdvcloud.rms.util.Pages;

import java.util.List;
import java.util.Map;

/**
 * 主播管理
 *
 * @author Administrator
 */
public interface AnchorDao {
    /**
     * 创建主播
     *
     * @param anchorMap 主播
     * @return 主播ID
     */
    String createAnchor(Map<String, Object> anchorMap);

    /**
     * 更新主播
     *
     * @param id     更新主播的主键
     * @param update 更新数据
     * @return 更新条数
     */
    long updateAnchorById(String id, Map<String, Object> update);

    /**
     * 删除主播
     *
     * @param ids 需要删除主播的主键
     * @return 删除条数
     */
    long deleteAnchors(List<String> ids);

    /**
     * 查询主播，通过Id查询
     *
     * @param id 主播id
     * @return 主播圈帖子
     */
    Map<String, Object> queryAnchorById(String id);

    /**
     * 查询主播，分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    Pages queryAnchor4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap);

    /**
     * 批量更新主播
     *
     * @param ids    需要更新的主键
     * @param update 更新字段
     * @return
     */
    long updateAnchors(List<String> ids, Map<String, Object> update);


    /**
     * 查询所有主播
     *
     * @param sortMap  排序字段
     * @param queryMap 请求字段
     * @param backMap  返回字段
     * @return 主播对象列表
     */
    List<Map<String, Object>> queryAnchorAll(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap);

    /**
     * 查询主播数量
     *
     * @param queryMap 请求字段
     * @return 个数
     */
    long countAnchor(Map<String, Object> queryMap);
    
    
    /**
     * 批量查询主播
     *
     * @param queryMap 请求字段
     * @return 个数
     */
    List<Map<String, Object>> queryAnchors(List<String> ids,Map<String, Object> backMap,Map<String, Object> sortMap);
}
