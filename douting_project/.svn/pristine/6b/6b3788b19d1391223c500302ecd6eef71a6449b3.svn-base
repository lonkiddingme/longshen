package com.cdvcloud.douting.service;

import java.util.List;
import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;

/**
 * 逗看视频直播管理
 *
 * @author zhangyuelong
 * @date 2017/10/31
 */
public interface LiveRoomService extends BaseService {


    /**
     * 创建直播間并创建直播通道
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
	String createLiveRoom(CommonParameters commonParameters, Map<String, Object> param);
	/**
	 * 通过直播间ID查询直播间信息
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String, Object> queryLiveRoomById(CommonParameters commonParameters, Map<String, Object> param);
	
	
	/**
	 * 删除直播间直播通道
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	long deleteLiveRoomChannel(CommonParameters commonParameters, Map<String, Object> param);
	/**
	 * 重制直播间直播通道
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	long resetLiveChannelById(CommonParameters commonParameters, Map<String, Object> param);
	
	/**
	 * 结束直播并删除直播通道释放资源
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	long stopLive(CommonParameters commonParameters, Map<String, Object> param);
	
	/**
	 * 创建网红直播公告
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	long createPictureText(CommonParameters commonParameters, Map<String, Object> param);
	
	/**
	 * 查询网红直播公告
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	String queryAnnouncement(CommonParameters commonParameters, Map<String, Object> param);
	
	
	/**
	 * 直播间粉丝禁言
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	long liveRoomFansGag(CommonParameters commonParameters, Map<String, Object> param);
	/**
	 * 解除直播间粉丝禁言
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	long relieveProhibit(CommonParameters commonParameters, Map<String, Object> param);
	/**
	 * 查询直播间粉丝禁言列表
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	
	List<Map<String, Object>> queryProhibitFansList(CommonParameters commonParameters, Map<String, Object> param);
	/**
	 * 添加直播间商品
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	String createLiveGoods(CommonParameters commonParameters, Map<String, Object> param);
	
	/**
	 * 删除直播间商品
	 */
	long deleteLiveGoods(CommonParameters commonParameters, Map<String, Object> param);
	
	/**
	 * 查询直播间粉丝禁言列表
	 */
	public List<Map<String, Object>> queryLiveGoodsList(CommonParameters commonParameters,
			Map<String, Object> param);
}
