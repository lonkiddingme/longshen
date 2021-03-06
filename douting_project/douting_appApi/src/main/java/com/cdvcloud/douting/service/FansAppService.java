package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 
 * 粉丝管理
 * 
 */
public interface FansAppService extends BaseService{

	
	/**
	 * 新增粉丝任务
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject updateTaskProgressForNewFans(CommonParameters commonParameters,Map<String, Object> param);


	/**
	 * 完善粉丝资料任务
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject perfectInformationForNewFans(CommonParameters commonParameters,Map<String, Object> param);

	/**
	 * 分享任务
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject fansShareTask(CommonParameters commonParameters,Map<String, Object> param);
	
	/**
	 * 更新粉丝收听时间
	 */
	ResponseObject updateFansListenTime(CommonParameters commonParameters,Map<String, Object> param);
	
	/**
	 * 日常登录
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	public ResponseObject dailyLogin(CommonParameters commonParameters, Map<String, Object> param);
	
	
	public ResponseObject queryMoneyDetail4Page(CommonParameters commonParameters, Map<String, Object> param);
	
}
