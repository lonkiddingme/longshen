package com.cdvcloud.douting.dao;

import java.util.List;
import java.util.Map;

/**
 * 用于redis操作
 * RedisUtilDao
 * @Description 
 * @author wlf
 * @data 2017-11-16 下午1:55:41
 */
public interface RedisUtilDao {
	/**
	 * 记录内容最新评论时间
	 * @param docid
	 */
	public void NewestCommentedContent(String docid);
	/**
	 * 添加公告
	 * @param pid	
	 * @param content
	 */
	public void publishNotice(String pid,String content);
	/**
	 * 查询公告
	 * @param pid
	 * @return
	 */
	public String getNotice(String pid);
	
	/**
	 * 禁言直播间粉丝
	 * @param pid
	 * @return
	 */
	public void prohibitFans(String pid,String fansId,String name,String phone);
	
	/**
	 * 解除直播间粉丝禁言
	 * @param pid
	 * @return
	 */
	public void relieveProhibit(String pid,String fansId);
	/**
	 * 查询直播间粉丝禁言列表(后台)
	 * @param pid
	 * @return
	 */
	public List<Map<String, Object>> queryProhibitFansList(String pid);
	
	/**
	 * 验证直播间粉丝禁言
	 * @param pid
	 * @return
	 */
	public List<String> getProhibitFansList(String pid);
	
	/**
	 * 添加直播间商品
	 * @param pid
	 * @return
	 */
	public void createLiveGoods(String pid,String goodsId,Map<String, Object> map);
	/**
	 * 删除直播间商品
	 * @param pid
	 * @return
	 */
	public void deleteLiveGoods(String pid,String goodsId);
	
	/**
	 * 查询直播间商品列表（APP端缓存）
	 */
	public List<Map<String, Object>> queryLiveGoodsList(String pid); 
	/**
	 * 根据pid获取红包列表
	 * @param params
	 * @return
	 */
	public List<String> getRedEnvelope(String pid);
	/**
	 * 通过sid查询评论支持高并发
	 * @param jsonMap
	 * @return
	 */
	public Map<String, Object> queryCommentEfficient(String sid,Map<String, Object> jsonMap);
	/**
	 * 根据sid查询已删除评论id(先发后删策略)
	 * @param jsonMap
	 * @return
	 */
	public Map<String, Object> getDeleteCommentIds(String sid,Map<String, Object> jsonMap);
	/**
	 * 直播间粉丝观看列表
	 */
	public void createLiveFansMsgList(String pid,Map<String, Object> fansMap);
	/**
	 * 查询直播间粉丝观看列表
	 */
	public List<Map<String, Object>> queryLiveFansMsgList(String pid);
	/**
	 * 直播间粉丝禁言列表（APP端）
	 */
	public Map<String, Object> queryProhibitFansListForApp(String sid,Map<String, Object> jsonMap);
	
	
	/**
	 *粉丝验证时添加粉丝数据
	 */
	public void addFansMsgList(Map<String, Object> fansMap,String id);
	
	
	/**
	 *验证粉丝登陆信息是否存在
	 */
	public Map<String, Object> queryFansMsgList(String id);
	
	/**
	 *删除粉丝登陆信息
	 */
	public Long deleteFansMsgById(String id);
	
	
	/**
	 *添加打赏信息
	 */
	public void addRewardMsgByVideoRoomId(String id,String msg);
	
	/**
	 *查询打赏信息
	 */
	public Map<String, Object> queryRewardMsgByVideoRoomId(String id,Map<String, Object> jsonMap);
	
}
