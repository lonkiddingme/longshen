package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;
/**
 * 粉丝用户 积分任务管理
 * @author zhangyuelong
 *
 */
public interface FansTaskProgressAppService extends BaseService{

	/**
	 * 查询粉丝任务详情
	 * @param commonParameters
	 * @param jsonMap
	 * @return
	 */
	Map<String,Object> queryFansTaskProgress(CommonParameters commonParameters, Map<String, Object> jsonMap);
	
	/**
     * 更改粉丝积分
     *
     * @param commonParameters
     * @param param
     * @return
     */
    ResponseObject updateFansMoneyById(CommonParameters commonParameters, Map<String, Object> param);
    /**
     * 查询粉丝积分
     * @param commonParameters
     * @param param
     * @return
     */
    ResponseObject queryFansMoneyById(CommonParameters commonParameters, Map<String, Object> param);
    
    /**
     * 初始换任务详情
     * @param commonParameters
     * @param param
     * @return
     */
    ResponseObject updateTaskProgressByFansId(CommonParameters commonParameters, Map<String, Object> param);



}
