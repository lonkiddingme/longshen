package com.cdvcloud.douting.dao.impl;

import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.BroadcastColumnDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.BroadcastColumn;
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
 * 直播间管理
 */
@Repository
public class BroadcastColumnDaoImpl implements BroadcastColumnDao {

    @Autowired
    private BasicDao basicDao;

    /**
     * 创建栏目
     */
    @Override
    public String createBroadcastColumn(Map<String, Object> paramMap) {
        String id = basicDao.insert(BroadcastColumn.BROADCASTCOLUMN, paramMap);
        /*查询直播间并修改 其 栏目数*/
        //		if(id!=null&&id.length()>0){
        //			String broadcastId = (String) paramMap.get(BroadcastColumn.BROADCASTID);
        //			Map<String, Object> queryFilter = new HashMap<String,Object>();
        //			Map<String, Object> param = new HashMap<String,Object>();
        //			queryFilter.put(Broadcast.ID,new ObjectId(broadcastId));
        //			Map<String, Object> addMap = new HashMap<String, Object>();
        //			addMap.put(Broadcast.COLUNMNUM, 1);
        //			param.put(QueryOperators.INC, addMap);
        //			basicDao.updateOneBySet(Broadcast.BROADCAST, queryFilter, param, true);
        //		}

        return id;
    }

    /**
     * 根据主键ID编辑栏目
     */
    @Override
    public long updateBroadcastColumnById(String id, Map<String, Object> update) {
        Map<String, Object> queryFilter = new HashMap<String, Object>();
        queryFilter.put(BroadcastColumn.ID, new ObjectId(id));
        return basicDao.updateOneBySet(BroadcastColumn.BROADCASTCOLUMN, queryFilter, update);
    }

    /**
     * 批量删除节目栏
     */
    @Override
    public long deleteBroadcastColumn(List<String> ids) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> queryFilter = new HashMap<String, Object>();
        queryFilter.put(BroadcastColumn.ID, new Document(QueryOperators.IN, objectIds));
        long count = basicDao.deleteMany(BroadcastColumn.BROADCASTCOLUMN, queryFilter);

        //        /*查询直播间并修改 其 栏目数*/
        //        if(count>0){
        //
        //        	String broadcastId = (String) deleteMap.get(BroadcastColumn.BROADCASTID);
        //    		Map<String, Object> filter = new HashMap<String,Object>();
        //    		Map<String, Object> param = new HashMap<String,Object>();
        //    		filter.put(Broadcast.ID,new ObjectId(broadcastId));
        //    		Map<String, Object> addMap = new HashMap<String, Object>();
        //    		addMap.put(Broadcast.COLUNMNUM, -count);
        //    		param.put(QueryOperators.INC, addMap);
        //    		basicDao.updateOneBySet(Broadcast.BROADCAST, filter, param, true);
        //        }


        return count;
    }

    @Override
    public Map<String, Object> queryBroadcastColumnById(String id) {
        return basicDao.findOne(BroadcastColumn.BROADCASTCOLUMN, id);
    }

    /**
     * @param ids     id数组
     * @param backMap 返回字段
     * @param sortMap 排序字段
     * @return 直播间数据
     */
    @Override
    public List<Map<String, Object>> queryBroadcastColumnByIds(List<String> ids, Map<String, Object> backMap, Map<String, Object> sortMap) {
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
        return basicDao.findAllObject(BroadcastColumn.BROADCASTCOLUMN, queryFilter, backFilter, sortFilter);
    }

    @Override
    public Pages queryBroadcastColumn4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap) {
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

        List<Map<String, Object>> results = basicDao.find(BroadcastColumn.BROADCASTCOLUMN, sortFilter, queryFilter, backFilter, currentPage, pageNum);
        long totalRecord = basicDao.count(BroadcastColumn.BROADCASTCOLUMN, queryFilter);
        return new Pages(pageNum, totalRecord, currentPage, results);
    }

    @Override
    public long updateBroadcastColumnStatus(List<String> ids, Map<String, Object> param) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put(BroadcastColumn.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.updateManyBySet(BroadcastColumn.BROADCASTCOLUMN, filter, param, true);
    }

    @Override
    public List<Map<String, Object>> queryRecommendBroadcastColumnsByBroadCastIds(List<String> broadcastIds, Map<String, Object> backMap, Map<String, Object> sortMap,Map<String, Object> queryFilter) {
        queryFilter.put(BroadcastColumn.BROADCASTID, new Document(QueryOperators.IN, broadcastIds));
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        return basicDao.findAllObject(BroadcastColumn.BROADCASTCOLUMN, queryFilter, backFilter, sortFilter);

    }

    @Override
    public List<Map<String, Object>> queryRecommendBroadcastColumnsByIds(List<String> broadcastIds, Map<String, Object> backMap, Map<String, Object> sortMap) {
        Map<String, Object> queryFilter = new HashMap<String, Object>();
        List<ObjectId> broadcastObjIds = new ArrayList<ObjectId>();
        for (String id : broadcastIds) {
            broadcastObjIds.add(new ObjectId(id));
        }
        queryFilter.put(BroadcastColumn.ID, new Document(QueryOperators.IN, broadcastObjIds));
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        return basicDao.findAllObject(BroadcastColumn.BROADCASTCOLUMN, queryFilter, backFilter, sortFilter);

    }

}
