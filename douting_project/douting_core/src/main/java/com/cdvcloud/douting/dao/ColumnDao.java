package com.cdvcloud.douting.dao;

import com.cdvcloud.rms.util.Pages;

import java.util.List;
import java.util.Map;

/**
 * 栏目管理
 *
 * @author SongYuanKun
 * @date 2017/10/31
 */
public interface ColumnDao {
    /**
     * 创建栏目
     *
     * @param columnMap 栏目
     * @return 栏目ID
     */
    String createColumn(Map<String, Object> columnMap);

    /**
     * 更新栏目
     *
     * @param id     栏目主键
     * @param update 更新数据
     * @return 更新条数
     */
    long updateColumnById(String id, Map<String, Object> update);

    /**
     * 删除栏目
     *
     * @param ids 需要删除栏目的主键
     * @return 删除条数
     */
    long deleteColumns(List<String> ids);

    /**
     * 查询栏目，通过Id查询
     *
     * @param id 栏目id
     * @return 栏目圈帖子
     */
    Map<String, Object> queryColumnById(String id);

    /**
     * 查询栏目，分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    Pages queryColumn4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap);

    /**
     * 批量更新栏目
     *
     * @param ids    需要更新的主键
     * @param update 更新字段
     * @return 更新条数
     */
    long updateColumns(List<String> ids, Map<String, Object> update);
    /**
     * 根据条件查询栏目
     * @param filter
     * @return
     */
    public Map<String, Object> getColumnByJson(Map<String, Object> filter);
}
