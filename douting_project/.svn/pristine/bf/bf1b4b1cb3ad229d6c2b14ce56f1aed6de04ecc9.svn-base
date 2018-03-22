package com.cdvcloud.douting.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.BroadcastColumnDao;
import com.cdvcloud.douting.domain.BroadcastColumn;
import com.cdvcloud.douting.service.BroadcastColumnService;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗播------栏目管理
 *
 * @author zhangyuelong
 */

@Service
public class BroadcastColumnServiceImpl extends BaseServiceImpl implements BroadcastColumnService {


    @Autowired
    private BroadcastColumnDao broadcastColumnDao;

    /**
     * 创建直播间栏目
     */
    @Override
    public String createBroadcastColumn(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> broadcastColumnMap = new HashMap<String, Object>();
        List<String> list = Arrays.asList(BroadcastColumn.NAME, BroadcastColumn.THUMBNAILURL, BroadcastColumn.STARTTIME, BroadcastColumn.ENDTIME, BroadcastColumn.BROADCASTNAME, BroadcastColumn.STATUS,BroadcastColumn.STATUS_ZH,BroadcastColumn.BROADCASTID, BroadcastColumn.USERTYPE);
            /*更新信息*/
        broadcastColumnMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

	        /*创建信息*/
        broadcastColumnMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));
	        /* 基础信息 */

        return broadcastColumnDao.createBroadcastColumn(broadcastColumnMap);
    }

    /**
     * 删除直播间栏目
     */
    @SuppressWarnings("unchecked")
    @Override
    public long deleteBroadcastColumn(CommonParameters commonParameters, Map<String, Object> param) {
        List<String> ids = (List<String>) param.get("ids");
        return broadcastColumnDao.deleteBroadcastColumn(ids);
    }


    /**
     * 更新直播间栏目
     */
    @Override
    public long updateBroadcastColumnById(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> updateParam = new HashMap<String, Object>();

        List<String> list = Arrays.asList(BroadcastColumn.NAME, BroadcastColumn.THUMBNAILURL, BroadcastColumn.STARTTIME, BroadcastColumn.ENDTIME, BroadcastColumn.BROADCASTNAME, BroadcastColumn.BROADCASTID);
		    /*更新信息*/
        updateParam.putAll(ValidateCommonParam.getFieldsMap(list, param));

	        /*更新人信息*/
        updateParam.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));

        String id = String.valueOf(param.get("id"));
        return broadcastColumnDao.updateBroadcastColumnById(id, updateParam);
    }

    /**
     * 根据直播间栏目ID 查询
     */
    @Override
    public Map<String, Object> queryBroadcastColumnById(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return broadcastColumnDao.queryBroadcastColumnById(id);
    }

    /**
     * 分页查询直播间栏目
     */
    @Override
    public Pages queryBroadcastColumn4Page(CommonParameters commonParameters, Map<String, Object> param) {
        // 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>();
        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));
        // 排序
        Map<String, Object> sortMap = new HashMap<String, Object>();
        sortMap.put("sort", param.get("sort"));
        // 返回字段
        Map<String, Object> backMap = new HashMap<String, Object>();
        backMap.put("backParam", param.get("backParam"));
        // 当前页
        Map<String, Object> currentPageMap = new HashMap<String, Object>();
        currentPageMap.put("currentPage", param.get("currentPage"));
        // 每页条数
        Map<String, Object> pageNumMap = new HashMap<String, Object>();
        pageNumMap.put("pageNum", param.get("pageNum"));
        return broadcastColumnDao.queryBroadcastColumn4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
    }

    /**
     * 更新直播间栏目状态
     */
    @SuppressWarnings("unchecked")
    @Override
    public long updateBroadcastColumnStatus(CommonParameters commonParameters, Map<String, Object> param) {

        List<String> ids = (List<String>) param.get("ids");
        Integer status = (Integer) param.get(BroadcastColumn.STATUS);
        String status_zh = String.valueOf(param.get(BroadcastColumn.STATUS_ZH));

        Map<String, Object> update = new HashMap<String, Object>(16);
        update.put(BroadcastColumn.STATUS, status);
        update.put(BroadcastColumn.STATUS_ZH, status_zh);
	     /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));

        return broadcastColumnDao.updateBroadcastColumnStatus(ids, update);
    }

    @Override
    public List<Map<String, Object>> queryBroadcastColumnByBroadcastId(String broadcastId, Map<String, Object> backParam, Map<String, Object> sortParam) {
        return null;
    }

}
