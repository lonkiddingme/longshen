package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 
 * 红包管理
 * 
 */
public interface RedEnvelopeService extends BaseService{

	
	/**
	 * 定时红包
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject timedRedEnvelope(CommonParameters commonParameters,Map<String, Object> param);
}
