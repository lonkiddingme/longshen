package com.cdvcloud.douting.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.BroadcastDao;
import com.cdvcloud.douting.domain.Broadcast;
import com.cdvcloud.douting.service.BroadcastService;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗播管理
 *
 * @author zhangyuelong
 */

@Service
public class BroadcastServiceImpl extends BaseServiceImpl implements BroadcastService {

    @Autowired
    private BroadcastDao broadcastDao;

    /**
     * 创建逗播直播间
     */

    @Override
    public String createBroadcast(CommonParameters commonParameters, Map<String, Object> param) {

        Map<String, Object> broadcastMap = new HashMap<String, Object>();
        List<String> list = Arrays.asList(Broadcast.NAME, Broadcast.URL,Broadcast.STATUS, Broadcast.THUMBNAILURL, Broadcast.STARTTIME, Broadcast.REMARK,Broadcast.ENDTIME, 

        		Broadcast.USERTYPE, Broadcast.WEIGHT);
             /*更新信息*/
        broadcastMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

	        /*创建信息*/
        broadcastMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));
	        /* 基础信息 */
        broadcastMap.put(Broadcast.LOOKNUM, Constants.ZERO);
        broadcastMap.put(Broadcast.COMMENTNUM, Constants.ZERO);

        String status = String.valueOf(param.get(Broadcast.STATUS));
        
        if(status.equals("0")){
        	broadcastMap.put(Broadcast.STATUS_ZH, "下线");

        }else{
        	broadcastMap.put(Broadcast.STATUS_ZH, "上线");
        	
        }
        
        return broadcastDao.createBroadcast(broadcastMap);
    }

    /**
     * 删除逗播直播间
     */
    @Override
    public long deleteBroadcast(CommonParameters commonParameters, Map<String, Object> param) {
        String id = (String) param.get("id");
        return broadcastDao.deleteBroadcast(id);
    }

    /**
     * 更新逗播直播间
     */
    @Override
    public long updateBroadcastById(CommonParameters commonParameters, Map<String, Object> param) {
        // TODO Auto-generated method stub

        Map<String, Object> update = new HashMap<String, Object>();
        List<String> list = Arrays.asList(Broadcast.NAME, Broadcast.URL, Broadcast.REMARK,Broadcast.THUMBNAILURL, Broadcast.STARTTIME, Broadcast.ENDTIME,Broadcast.STATUS_ZH, Broadcast.STATUS);
        /*更新信息*/
        update.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));
        String status = String.valueOf(param.get(Broadcast.STATUS));
        
        if(status.equals("0")){
        	update.put(Broadcast.STATUS_ZH, "下线");

        }else{
        	update.put(Broadcast.STATUS_ZH, "上线");
        	
        }
        String id = String.valueOf(param.get("id"));
        return broadcastDao.updateBroadcastById(id, update);
    }

    /**
     * 根据ID查询逗播直播间
     */
    @Override
    public Map<String, Object> queryBroadcastById(CommonParameters commonParameters, Map<String, Object> param) {
        // TODO Auto-generated method stub
        String id = String.valueOf(param.get("id"));
        return broadcastDao.queryBroadcastById(id);
    }

    /**
     * 分页查询逗播直播间
     */
    @Override
    public Pages queryBroadcast4Page(CommonParameters commonParameters, Map<String, Object> param) {
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


        return broadcastDao.queryBroadcast4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
    }

    /**
     * 根据ID更新直播间状态
     */
    @Override
    public long updateBroadcastStatus(CommonParameters commonParameters, Map<String, Object> param) {
        String id = (String) param.get("id");
        Integer status = (Integer) param.get(Broadcast.STATUS);
        String status_zh = String.valueOf(param.get(Broadcast.STATUS_ZH));

        Map<String, Object> update = new HashMap<String, Object>(16);
        update.put(Broadcast.STATUS, status);
        update.put(Broadcast.STATUS_ZH, status_zh);

         /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));
        return broadcastDao.updateBroadcastStatus(id, update);
    }

    /**
     * 查询所有直播间
     */
    @Override
    public List<Map<String, Object>> queryBroadcastAll(CommonParameters commonParameters, Map<String, Object> param) {
        // 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>(16);

        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));
        // 排序
        Map<String, Object> sortMap = new HashMap<String, Object>(16);
        sortMap.put("sort", param.get("sort"));
        // 返回字段
        Map<String, Object> backMap = new HashMap<String, Object>(16);
        backMap.put("backParam", param.get("backParam"));

        return broadcastDao.queryBroadcastAll(sortMap, queryMap, backMap);
    }

    /**
     * 统计直播间数量
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public long countBroadcast(CommonParameters commonParameters, Map<String, Object> param) {
        // 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>();

        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));
        return broadcastDao.countBroadcast(queryMap);
    }

}
