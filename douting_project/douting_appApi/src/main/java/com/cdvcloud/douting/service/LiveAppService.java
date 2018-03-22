package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

public interface LiveAppService extends BaseService{

	/**
	 *  开启直播
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,	Object> startLive(CommonParameters commonParameters,Map<String, Object> param);
	/**
	 *  关闭直播
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Boolean stopLive(CommonParameters commonParameters,Map<String, Object> param);
	
	/**
	 *  查看直播地址
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> queryLiveRoomById(CommonParameters commonParameters,Map<String, Object> param);
	
	
	/**
	 *  开始收录
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> startIncludeByApp(CommonParameters commonParameters,Map<String, Object> param);
	/**
     * 创建评论
     *
     * @param companyId        租户ID
     * @param appCode          应用标识
     * @param userId           用户ID
     * @param serviceCode      服务标识
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
	ResponseObject createLiveComment(CommonParameters commonParameters,Map<String, Object> param);
	/**
     * 网红直播间轮询接口
     *
     */
	Map<String, Object> pollingLiveData(CommonParameters commonParameters,Map<String, Object> param);
}
