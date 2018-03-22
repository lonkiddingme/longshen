package com.cdvcloud.douting.dao;

import com.cdvcloud.rms.util.Pages;

import java.util.List;
import java.util.Map;

/**
 * 内容管理
 *
 * @author Administrator
 */
public interface ContentDao {
    /**
     * 创建内容
     *
     * @param contentMap 内容
     * @return 内容ID
     */
    String createContent(Map<String, Object> contentMap);

    /**
     * 更新内容
     *
     * @param id     更新内容的主键
     * @param update 更新数据
     * @return 更新条数
     */
    long updateContentById(String id, Map<String, Object> update);

    /**
     * 删除内容
     *
     * @param ids 需要删除内容的主键
     * @return 删除条数
     */
    long deleteContents(List<String> ids);

    /**
     * 查询内容，通过Id查询
     *
     * @param id 内容id
     * @return 内容圈帖子
     */
    Map<String, Object> queryContentById(String id);
    /**
     * 查询内容，通过DOCId查询
     *
     * @param id 栏目id
     * @return 栏目圈帖子
     */
    Map<String, Object> queryContentByDocId(String docid);


    /**
     * 根据DCOID 删除内容
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    long deleteContentByDocId(String docid);
    /**
     * 查询内容，分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    Pages queryContent4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap);

    /**
     * 批量更新内容
     *
     * @param ids    需要更新的主键
     * @param update 更新字段
     * @return
     */
    long updateContents(List<String> ids, Map<String, Object> update);


    /**
     * 查询所有内容
     *
     * @param sortMap  排序字段
     * @param queryMap 请求字段
     * @param backMap  返回字段
     * @return 内容对象列表
     */
    List<Map<String, Object>> queryContentAll(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap);

    /**
     * 查询内容数量
     *
     * @param queryMap 请求字段
     * @return 个数
     */
    long countContent(Map<String, Object> queryMap);


    /**
     * 批量查询内容
     *
     * @param ids     主键ids
     * @param backMap 返回参数
     * @param sortMap 排序参数
     * @return 个数
     */
    List<Map<String, Object>> queryContents(List<String> ids, Map<String, Object> backMap, Map<String, Object> sortMap);
}
