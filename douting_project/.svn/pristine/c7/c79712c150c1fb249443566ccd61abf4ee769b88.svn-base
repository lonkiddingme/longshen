package com.cdvcloud.douting.dao.impl;

import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.ContentDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.Content;
import com.cdvcloud.rms.dao.BasicDao;
import com.cdvcloud.rms.dao.mongodb.QueryOperators;
import com.cdvcloud.rms.util.Pages;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容管理
 *
 * @author SongYuanKun
 * @date 2017/11/9
 */
@Repository
public class ContentDaoImpl implements ContentDao {
    @Autowired
    private BasicDao basicDao;

    /**
     * 创建内容
     *
     * @param anchorMap 内容
     * @return 内容ID
     */
    @Override
    public String createContent(Map<String, Object> anchorMap) {
        return basicDao.insert(Content.CONTENT, anchorMap);
    }

    /**
     * 更新内容
     *
     * @param id     更新内容的主键
     * @param update 更新数据
     * @return 更新条数
     */
    @Override
    public long updateContentById(String id, Map<String, Object> update) {
        /*查询条件*/
        Map<String, Object> queryFilter = new HashMap<String, Object>(16);
        queryFilter.put(Content.ID, new ObjectId(id));
        return basicDao.updateOneBySet(Content.CONTENT, queryFilter, update);
    }

    /**
     * 删除内容
     *
     * @param ids 需要删除内容的主键
     * @return 删除条数
     */
    @Override
    public long deleteContents(List<String> ids) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>(16);
        filter.put(Content.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.deleteMany(Content.CONTENT, filter);
    }

    /**
     * 查询内容，通过Id查询
     *
     * @param id 内容id
     * @return 内容圈帖子
     */
    @Override
    public Map<String, Object> queryContentById(String id) {

        return basicDao.findOne(Content.CONTENT, id);
    }


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
    @Override
    public Pages queryContent4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap) {
        // 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        queryFilter.remove(BasicObject.ISDEL);
        // 排序
        
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap) ; 
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        // 当前页
        Integer currentPage = ValidateCommonParam.getCurrentPage(currentPageMap);
        // 每页条数
        Integer pageNum = ValidateCommonParam.getPageNum(pageNumMap);
        List<Map<String, Object>> results = basicDao.find(Content.CONTENT, sortFilter, queryFilter, backFilter, currentPage, pageNum);
        
        long totalRecord = basicDao.count(Content.CONTENT, queryFilter);
        return new Pages(pageNum, totalRecord, currentPage, results);
    }

    /**
     * 批量更新内容
     *
     * @param ids    需要更新的主键
     * @param update 更新字段
     * @return
     */
    @Override
    public long updateContents(List<String> ids, Map<String, Object> update) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>(16);
        filter.put(Content.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.updateManyBySet(Content.CONTENT, filter, update, true);
    }

    /**
     * 查询所有内容
     *
     * @param sortMap  排序字段
     * @param queryMap 请求字段
     * @param backMap  返回字段
     * @return 内容对象列表
     */
    @Override
    public List<Map<String, Object>> queryContentAll(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap) {
        // 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        queryFilter.remove(BasicObject.ISDEL);
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        return basicDao.findAllObject(Content.CONTENT, queryFilter, backFilter, sortFilter);
    }

    /**
     * 查询内容数量
     *
     * @param queryMap 请求字段
     * @return 个数
     */
    @Override
    public long countContent(Map<String, Object> queryMap) {
        // 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        return basicDao.count(Content.CONTENT, queryFilter);
    }

    /**
     * 批量查询内容
     */
    @Override
    public List<Map<String, Object>> queryContents(List<String> ids, Map<String, Object> backMap, Map<String, Object> sortMap) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> queryFilter = new HashMap<String, Object>();
        queryFilter.put(Content.ID, new Document(QueryOperators.IN, objectIds));
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        return basicDao.findAllObject(Content.CONTENT, queryFilter, backFilter, sortFilter);
    }

    /**
     * 通过docId  查询内容详情
     */
    @Override
    public Map<String, Object> queryContentByDocId(String docid) {

        Map<String, Object> queryFilter = new HashMap<String, Object>(16);
        queryFilter.put(Content.DOCID, docid);
        return basicDao.findOne(Content.CONTENT, queryFilter);

    }
    
    
    /**
     * 通过docId  删除内容详情
     */
    @Override
    public long deleteContentByDocId(String docid) {

        Map<String, Object> queryFilter = new HashMap<String, Object>(16);
        queryFilter.put(Content.DOCID, docid);
        return basicDao.deleteOne(Content.CONTENT, queryFilter);

    }
}
