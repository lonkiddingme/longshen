package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 
 * 红包管理
 * 
 */
public interface RedEnvelopeAppService extends BaseService{

	
	/**
	 * 定时红包
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject timedRedEnvelope(CommonParameters commonParameters,Map<String, Object> param);

	/**
	 * 主动发红包
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject creatRedEnvelopeByanchor(CommonParameters commonParameters,Map<String, Object> param);
	
	
	/**
	 * 根据pid获取红包列表
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject getRedEnvelope(CommonParameters commonParameters,Map<String, Object> param);
	/**
	 * 根据红包Id抢红包
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject grabRedEnvelope(CommonParameters commonParameters,Map<String, Object> param);

}
