package com.cdvcloud.douting.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.service.TaskProgressAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;

/**
 * 粉丝任务详情管理
 * @author zhangyuelong
 *
 */
@Service
public class TaskProgressAppServiceImpl extends BaseServiceImpl implements TaskProgressAppService {

	@Autowired
    private HttpCommonUtil httpCommonUtil;
	
	/**
	 *  根据任务标识修改任务详情
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	@Override
	public ResponseObject updateTaskProgress(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		param.put("userType", "fans");
		param.put("accessToken", "accessToken");
        param.put("timeStamp", System.currentTimeMillis());
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("updateTaskProgressById");
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        String responseString = httpCommonUtil.doPost(url, param);
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        
        return responseObject;
	}
	
}
