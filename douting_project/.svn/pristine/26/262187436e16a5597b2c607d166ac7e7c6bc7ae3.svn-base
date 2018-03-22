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
import com.cdvcloud.douting.dao.BroadcastDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.Broadcast;
import com.cdvcloud.rms.dao.BasicDao;
import com.cdvcloud.rms.dao.mongodb.QueryOperators;
import com.cdvcloud.rms.util.Pages;


/**
 * 逗播管理
 * @author Administrator
 */
@Repository
public class BroadcastDaoImpl implements BroadcastDao {

    @Autowired
    private BasicDao basicDao;

    /**
     * 创建逗播直播间
     */
    @Override
    public String createBroadcast(Map<String, Object> broadcastMap) {
        // TODO Auto-generated method stub
        return basicDao.insert(Broadcast.BROADCAST, broadcastMap);
    }

    /**
     * 更新直播间
     */
    @Override
    public long updateBroadcastById(String id, Map<String, Object> update) {
         /*查询条件*/
        Map<String, Object> queryFilter = new HashMap<String, Object>();
        queryFilter.put(Broadcast.ID, new ObjectId(id));
        return basicDao.updateOneBySet(Broadcast.BROADCAST, queryFilter, update);
    }

    /**
     * 删除直播间
     */
    @Override
    public long deleteBroadcast(String id) {
        // TODO Auto-generated method stub
        Map<String, Object> deleteFilter = new HashMap<String, Object>();
        deleteFilter.put(Broadcast.ID, new ObjectId(id));
        return basicDao.deleteOne(Broadcast.BROADCAST, deleteFilter);
    }

    /**
     * 根据ID查询直播间
     */
    @Override
    public Map<String, Object> queryBroadcastById(String id) {
        return basicDao.findOne(Broadcast.BROADCAST, id);
    }

    /**
     * 批量查询直播间
     *
     * @param ids     id数组
     * @param backMap 返回字段
     * @param sortMap 排序字段
     * @return 直播间数据
     */
    @Override
    public List<Map<String, Object>> queryBroadcastByIds(List<String> ids, Map<String, Object> backMap, Map<String, Object> sortMap) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> queryFilter = new HashMap<String, Object>();
        queryFilter.put(BasicObject.ID, new Document(QueryOperators.IN, objectIds));
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        return basicDao.findAllObject(Broadcast.BROADCAST, queryFilter, backFilter, sortFilter);
    }
    /**
     * 逗播直播间分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    @Override
    public Pages queryBroadcast4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap) {
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

        List<Map<String, Object>> results = basicDao.find(Broadcast.BROADCAST, sortFilter, queryFilter, backFilter, currentPage, pageNum);
        long totalRecord = basicDao.count(Broadcast.BROADCAST, queryFilter);
        return new Pages(pageNum, totalRecord, currentPage, results);

    }

    /**
     * 根据ID修改直播间状态
     */
    @Override
    public long updateBroadcastStatus(String id, Map<String, Object> param) {
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put(Broadcast.ID, new ObjectId(id));
        return basicDao.updateOneBySet(Broadcast.BROADCAST, filter, param, true);
    }

    /**
     * 查询所有直播间
     */
    @Override
    public List<Map<String, Object>> queryBroadcastAll(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap) {
        // 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        return basicDao.findAllObject(Broadcast.BROADCAST, queryFilter, backFilter, sortFilter);
    }

    /**
     * 统计直播间数量
     */
    @Override
    public long countBroadcast(Map<String, Object> queryMap) {
        /// 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        return basicDao.count(Broadcast.BROADCAST, queryFilter);
    }

}
