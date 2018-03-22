package com.cdvcloud.douting.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.domain.Global;
import com.cdvcloud.douting.service.LiveChannelService;
import com.cdvcloud.rms.dao.BasicDao;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;

/**
 * 调用其他服务
 *
 * @author SongYuanKun
 * @date 2017/11/9
 */
@Service
public class LiveChannelServiceImpl extends BaseServiceImpl implements LiveChannelService {
    @Autowired
    private HttpCommonUtil httpCommonUtil;
    
    @Autowired
	private BasicDao basicdao;
    
    

    /**
     * 创建直播通道
     */
	@Override
	public ResponseObject createLiveChannel(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		Map<String, Object> glob = basicdao.findOne(Global.GLOBAL);
		String ip = String.valueOf(glob.get("passapiUrl"));
		String urlToken = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "ONZHIB") +"v1/onsiteLive/task/create4Json/";
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("accessToken", String.valueOf(glob.get("accessToken")));
		//实体地址和虚拟地址绑定后的回调地址
//		String callBack = String.valueOf(glob.get("virtualCallback_Url"))+ ValidateCommonParam.getCommonParametersUrl(commonParameters, "core")+	"v1/liveChannel/callback";
		tokenMap.put("callbackUrl", "");
		tokenMap.put("timeStamp", System.currentTimeMillis());
		 String responseString = httpCommonUtil.doPost(urlToken, tokenMap);
	        if (!StringUtil.isEmpty(responseString)) {
	            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
	        } else {
	            ValidateCommonParam.innerError(responseObject);
	        }
	        return responseObject;
	}


	/**
	 * 删除直播通道
	 */
	@Override
	public ResponseObject deleteLiveChannel(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		Map<String, Object> glob = basicdao.findOne(Global.GLOBAL);
		String ip = String.valueOf(glob.get("passapiUrl"));
		String urlToken = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "ONZHIB") +"v1/onsiteLive/task/delete4Json/";
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("accessToken", String.valueOf(glob.get("accessToken")));
		tokenMap.put("virtualId", param.get("virtualId"));
		tokenMap.put("timeStamp", System.currentTimeMillis());
		String responseString = httpCommonUtil.doPost(urlToken, tokenMap);
	        if (!StringUtil.isEmpty(responseString)) {
	            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
	        } else {
	            ValidateCommonParam.innerError(responseObject);
	        }
	        return responseObject;
	}

	/**
	 * 开始拉流
	 */
	@Override
	public ResponseObject start4Json(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		Map<String, Object> glob = basicdao.findOne(Global.GLOBAL);
		String ip = String.valueOf(glob.get("passapiUrl"));
		String urlToken = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "ONZHIB") +"v1/onsiteLive/task/start4Json/";
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("accessToken", String.valueOf(glob.get("accessToken")));
		tokenMap.put("pushUrl", param.get("pushUrl"));
		tokenMap.put("srcUrl", param.get("srcStreamUrl"));
		tokenMap.put("output", "");
		tokenMap.put("timeStamp", System.currentTimeMillis());
		 String responseString = httpCommonUtil.doPost(urlToken, tokenMap);
	        if (!StringUtil.isEmpty(responseString)) {
	            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
	        } else {
	            ValidateCommonParam.innerError(responseObject);
	        }
	        return responseObject;
	}

	/**
	 * 停止拉流
	 * demo的样例停止字段 和 文档不一样 先暂定
	 */
	@Override
	public ResponseObject stop4Json(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		Map<String, Object> glob = basicdao.findOne(Global.GLOBAL);
		String ip = String.valueOf(glob.get("passapiUrl"));
		String urlToken = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "ONZHIB") +"v1/onsiteLive/task/stop4Json/";
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("accessToken", String.valueOf(glob.get("accessToken")));
		tokenMap.put("timeStamp", System.currentTimeMillis());
//		tokenMap.put("virtualId", param.get("virtualId"));
		tokenMap.put("taskId", param.get("taskId"));
		 String responseString = httpCommonUtil.doPost(urlToken, tokenMap);
	        if (!StringUtil.isEmpty(responseString)) {
	            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
	        } else {
	            ValidateCommonParam.innerError(responseObject);
	        }
	        return responseObject;
	}


	/**
	 * APP端紧急停播
	 */
	@Override
	public ResponseObject closeLiveChannel(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		Map<String, Object> glob = basicdao.findOne(Global.GLOBAL);
		String ip = String.valueOf(glob.get("passapiUrl"));
		String urlToken = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "ONZHIB") +"v1/onsiteLive/task/hlsStop4Json/";
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("accessToken", String.valueOf(glob.get("accessToken")));
		tokenMap.put("timeStamp", System.currentTimeMillis());
		tokenMap.put("virtualId", param.get("virtualId"));
		 String responseString = httpCommonUtil.doPost(urlToken, tokenMap);
	        if (!StringUtil.isEmpty(responseString)) {
	            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
	        } else {
	            ValidateCommonParam.innerError(responseObject);
	        }
	        return responseObject;
	}
	/**
	 * 恢复APP直播
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	public ResponseObject newchannelkaiqi(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		Map<String, Object> glob = basicdao.findOne(Global.GLOBAL);
		String ip = String.valueOf(glob.get("passapiUrl"));
		String urlToken = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "ONZHIB") +"v1/onsiteLive/task/hlsStart4Json/";
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("accessToken", String.valueOf(glob.get("accessToken")));
		tokenMap.put("timeStamp",  System.currentTimeMillis());
		tokenMap.put("virtualId", param.get("virtualId"));
		String responseString = httpCommonUtil.doPost(urlToken, tokenMap);
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
	}

	/**
	 * 开始收录
	 */
	@Override
	public ResponseObject startInclude(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		Map<String, Object> glob = basicdao.findOne(Global.GLOBAL);
		String ip = String.valueOf(glob.get("passapiUrl"));
		String urlToken = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "LHC") +"v1/livecache/task/create4Json/";
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("accessToken", String.valueOf(glob.get("accessToken")));
		tokenMap.put("timeStamp",  System.currentTimeMillis());
		tokenMap.put("srcUrl", param.get("hlsUrl"));
		tokenMap.put("priority", 0);
		
		//回调地址后期添加
		String callBack = String.valueOf(glob.get("virtualCallback_Url"))+ ValidateCommonParam.getCommonParametersUrl(commonParameters, "LIUCT")+"v1/liveChannel/reviewBack";
		tokenMap.put("callbackUrl", callBack);
		String responseString = httpCommonUtil.doPost(urlToken, tokenMap);
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
	}
	/**
	 * 停止收录
	 */
	@Override
	public ResponseObject stopInclude(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		Map<String, Object> glob = basicdao.findOne(Global.GLOBAL);
		String ip = String.valueOf(glob.get("passapiUrl"));
		String urlToken = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "LHC") +"v1/livecache/stop4Json/";
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("accessToken", String.valueOf(glob.get("accessToken")));
		tokenMap.put("timeStamp",  System.currentTimeMillis());
		tokenMap.put("taskId", param.get("taskId"));
		String responseString = httpCommonUtil.doPost(urlToken, tokenMap);
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
	}
	
}
