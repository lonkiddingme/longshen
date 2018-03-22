package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 
 * 粉丝任务详情管理
 * 
 */
public interface TaskProgressAppService extends BaseService{

	
	/**
	 *  根据任务标识修改任务详情
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject updateTaskProgress(CommonParameters commonParameters,Map<String, Object> param);



}
