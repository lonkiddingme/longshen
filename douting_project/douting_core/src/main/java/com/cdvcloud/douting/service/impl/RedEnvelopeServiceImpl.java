package com.cdvcloud.douting.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.GeneralStatus;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.dao.VideoRoomDao;
import com.cdvcloud.douting.service.RedEnvelopeService;
import com.cdvcloud.douting.service.task.TimedRedEnvelope;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 定时
 * @author zhangyuelong
 *
 */
@Service
public class RedEnvelopeServiceImpl extends BaseServiceImpl implements RedEnvelopeService {

	@Autowired
    private HttpCommonUtil httpCommonUtil;
	@Autowired
    private VideoRoomDao videoRoomdao;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    
	@Override
	public ResponseObject timedRedEnvelope(CommonParameters commonParameters, Map<String, Object> param) {
		String pid = "";
		ResponseObject responseObject = new ResponseObject();
		if(param.get("id")!=null){
			pid = String.valueOf(param.get("id"));
		}
		if(pid!=""){
			TimedRedEnvelope timedRedEnvelope = new TimedRedEnvelope(commonParameters, param, videoRoomdao, httpCommonUtil);
			threadPoolTaskExecutor.execute(timedRedEnvelope);
		}
		responseObject.setCode(GeneralStatus.success.status);
		responseObject.setMessage(GeneralStatus.success.detail);
		responseObject.setData(new HashMap<String, Object>());
		return responseObject;
	}
    

}
