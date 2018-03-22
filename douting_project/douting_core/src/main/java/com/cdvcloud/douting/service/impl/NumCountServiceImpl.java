package com.cdvcloud.douting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.domain.Global;
import com.cdvcloud.douting.domain.NumCount;
import com.cdvcloud.douting.service.NumCountService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
/**
 * 调用互动计数服务
 * NumCountServiceImpl
 * @Description 
 * @author wlf
 * @data 2017-11-16 下午1:59:19
 */
@Service
public class NumCountServiceImpl extends BaseServiceImpl implements NumCountService {
	@Autowired
	private HttpCommonUtil httpCommonUtil;
	/**
	 * 计数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long doNumCount(String userType, CommonParameters commonParameters,
			String countType, String beCountId, int num, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(NumCount.BECOUNTID, beCountId);
		map.put(NumCount.COUNTTYPE, countType);
		map.put(NumCount.NUM, num);
		map.put(NumCount.TYPE, type);
		map.put(NumCount.USERTYPE, userType);
		map.put(CommonParameters.ACCESSTOKEN, "numCount");
		map.put(CommonParameters.TIMESTAMP, System.currentTimeMillis());
		String ip = Configuration.getConfigValue(Global.INTERACTIVE_URL);
		String api = Configuration.getConfigValue("doNumCount");
		String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "interactive")+api;
		String doPost = httpCommonUtil.doPost(url, map);
		ResponseObject object = JSONUtils.toObject(doPost, ResponseObject.class);
		if(object.getCode() == 0){
			Object data = object.getData();
			if(data instanceof Map){
				Map<String, Object> dataMap = (Map<String, Object>) data;
				long count = Long.parseLong(String.valueOf(dataMap.get(NumCount.NUM)));
				return count;
			}
		}
		return 0;
	}
	/**
	 * 查询计数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long queryNumCount(String userType,
			CommonParameters commonParameters, String countType,
			String beCountId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(NumCount.BECOUNTID, beCountId);
		map.put(NumCount.COUNTTYPE, countType);
		map.put(NumCount.USERTYPE, userType);
		map.put(CommonParameters.ACCESSTOKEN, "numCount");
		map.put(CommonParameters.TIMESTAMP, System.currentTimeMillis());
		String ip = Configuration.getConfigValue(Global.INTERACTIVE_URL);
		String api = Configuration.getConfigValue("queryNumCount");
		String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "interactive")+api;
		String doPost = httpCommonUtil.doPost(url, map);
		ResponseObject object = JSONUtils.toObject(doPost, ResponseObject.class);
		if(object.getCode() == 0){
			Object data = object.getData();
			if(data instanceof Map){
				Map<String, Object> dataMap = (Map<String, Object>) data;
				long count = Long.parseLong(String.valueOf(dataMap.get(NumCount.NUM)));
				return count;
			}
		}
		return 0;
	}
	/**
	 * 批量查看某一类型计数
	 * @param userType 用户类型（user：用户，fans：APP粉丝）
	 * @param commonParameters 用户信息
	 * @param countType	计数类型:lookNum(查看数)，likeNum(点赞数)，commentNum(评论数)
	 * @param beCountIds 被计数对象id集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryManyNumCount(String userType,
			CommonParameters commonParameters, String countType,
			List<String> beCountIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.IDS, beCountIds);
		map.put(NumCount.COUNTTYPE, countType);
		map.put(NumCount.USERTYPE, userType);
		map.put(CommonParameters.ACCESSTOKEN, "numCount");
		map.put(CommonParameters.TIMESTAMP, System.currentTimeMillis());
		String ip = Configuration.getConfigValue(Global.INTERACTIVE_URL);
		String api = Configuration.getConfigValue("queryManyNumCount");
		String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "interactive")+api;
		String doPost = httpCommonUtil.doPost(url, map);
		ResponseObject object = JSONUtils.toObject(doPost, ResponseObject.class);
		if(object.getCode() == 0){
			Object data = object.getData();
			if(data instanceof List){
				List<Map<String, Object>> dataList = (List<Map<String, Object>>) data;
				return dataList;
			}
		}
		return new ArrayList<Map<String,Object>>();
	}
	/**
	 * 查看某一对象全部类型计数
	 * @param userType 用户类型（user：用户，fans：APP粉丝）
	 * @param commonParameters 用户信息
	 * @param beCountId 被计数对象id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> queryNumCountByObject(String userType,
			CommonParameters commonParameters, String beCountId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(NumCount.BECOUNTID, beCountId);
		map.put(NumCount.USERTYPE, userType);
		map.put(CommonParameters.ACCESSTOKEN, "numCount");
		map.put(CommonParameters.TIMESTAMP, System.currentTimeMillis());
		String ip = Configuration.getConfigValue(Global.INTERACTIVE_URL);
		String api = Configuration.getConfigValue("queryNumCountByObject");
		String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "interactive")+api;
		String doPost = httpCommonUtil.doPost(url, map);
		ResponseObject object = JSONUtils.toObject(doPost, ResponseObject.class);
		if(object.getCode() == 0){
			Object data = object.getData();
			if(data instanceof Map){
				Map<String, String> dataMap = (Map<String, String>) data;
				return dataMap;
			}
		}
		return new HashMap<String, String>();
	}
}
