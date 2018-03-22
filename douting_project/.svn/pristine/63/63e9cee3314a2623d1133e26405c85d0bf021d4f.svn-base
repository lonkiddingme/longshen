package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 
 * 计数统计管理
 * 
 */
public interface NumCountAppService extends BaseService{

	
	/**
	 *  添加计数
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	long addNumCount(CommonParameters commonParameters,Map<String, Object> param);


	/**
	 *  查询计数
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	long queryNumCount(CommonParameters commonParameters,Map<String, Object> param);
	
	/**
	 *  查看某一对象全部类型计数
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String, String> queryNumCountByObject(CommonParameters commonParameters,Map<String, Object> param);
	
	/**
	 *  查看某一对象全部类型计数
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject addLivePv(CommonParameters commonParameters,Map<String, Object> param);

}
