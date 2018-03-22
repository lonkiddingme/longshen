package com.cdvcloud.douting.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.ColumnDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.Column;
import com.cdvcloud.rms.dao.BasicDao;
import com.cdvcloud.rms.dao.mongodb.QueryOperators;
import com.cdvcloud.rms.util.Pages;

/**
 * 栏目管理
 *
 * @author SongYuanKun
 * @date 2017/10/31
 */
@Repository
public class ColumnDaoImpl implements ColumnDao {
    @Autowired
    private BasicDao basicDao;

    /**
     * 创建栏目
     *
     * @param columnMap 栏目
     * @return 栏目ID
     */
    @Override
    public String createColumn(Map<String, Object> columnMap) {
        return basicDao.insert(Column.COLUMN, columnMap);
    }

    /**
     * 更新栏目
     *
     * @param id     栏目主键
     * @param update 更新数据
     * @return 更新条数
     */
    @Override
    public long updateColumnById(String id, Map<String, Object> update) {
        Map<String, Object> queryFilter = new HashMap<String, Object>(16);
        queryFilter.put(BasicObject.ID, new ObjectId(id));
        return basicDao.updateOneBySet(Column.COLUMN, queryFilter, update);
    }

    /**
     * 删除栏目
     *
     * @param ids 需要删除栏目的主键
     * @return 删除条数
     */
    @Override
    public long deleteColumns(List<String> ids) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>(16);
        filter.put(BasicObject.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.deleteMany(Column.COLUMN, filter);
    }

    /**
     * 查询栏目，通过Id查询
     *
     * @param id 栏目id
     * @return 栏目圈帖子
     */
    @Override
    public Map<String, Object> queryColumnById(String id) {
        return basicDao.findOne(Column.COLUMN, id);
    }

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
    @Override
    public Pages queryColumn4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap) {
        // 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        // 当前页
        Integer currentPage = ValidateCommonParam.getCurrentPage(currentPageMap);
        // 每页条数
        Integer pageNum = ValidateCommonParam.getPageNum(pageNumMap);
        List<Map<String, Object>> results = basicDao.find(Column.COLUMN, sortFilter, queryFilter, backFilter, currentPage, pageNum);
        long totalRecord = basicDao.count(Column.COLUMN, queryFilter);
        return new Pages(pageNum, totalRecord, currentPage, results);
    }

    /**
     * 批量更新栏目
     *
     * @param ids    需要更新的主键
     * @param update 更新字段
     * @return 更新条数
     */
    @Override
    public long updateColumns(List<String> ids, Map<String, Object> update) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>(16);
        filter.put(BasicObject.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.updateManyBySet(Column.COLUMN, filter, update, true);
    }
    
    public Map<String, Object> getColumnByJson(Map<String, Object> filter){
    	return basicDao.findOne(Column.COLUMN, filter);
    }
}
