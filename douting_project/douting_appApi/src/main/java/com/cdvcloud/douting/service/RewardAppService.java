package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 
 * 打赏管理
 * 
 */
public interface RewardAppService extends BaseService{

	
	/**
	 * 打赏
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject updateMoneyByReward(CommonParameters commonParameters,Map<String, Object> param);

	/**
	 * 充值功能
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject rechargeByfanId(CommonParameters commonParameters,Map<String, Object> param);
	
	/**
	 * 充值功能
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject queryRechargeRules(CommonParameters commonParameters,Map<String, Object> param);
}
