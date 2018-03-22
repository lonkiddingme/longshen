package com.cdvcloud.douting.service;

import java.util.List;
import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;

/**
 * 调用互动计数服务
 * NumCountService
 * @Description 
 * @author wlf
 * @data 2017-11-16 下午1:58:24
 */
public interface NumCountService extends BaseService{
	/**
	 * 调用计数
	 * @param userType 用户类型（user：用户，fans：APP粉丝）
	 * @param commonParameters 用户信息
	 * @param countType	计数类型:lookNum(查看数)，likeNum(点赞数)，commentNum(评论数)
	 * @param beCountId 被计数对象id
	 * @param num 数字
	 * @param type 被计数对象类型（对象表名）
	 * @return 累加后的总数
	 */
	public long doNumCount(String userType,CommonParameters commonParameters,
			String countType,String beCountId,int num,String type);
	/**
	 * 查看计数
	 * @param userType 用户类型（user：用户，fans：APP粉丝）
	 * @param commonParameters 用户信息
	 * @param countType	计数类型:lookNum(查看数)，likeNum(点赞数)，commentNum(评论数)
	 * @param beCountId 被计数对象id
	 * @return 当前总数
	 */
	public long queryNumCount(String userType,CommonParameters commonParameters,
			String countType,String beCountId);
	/**
	 * 批量查看某一类型计数
	 * @param userType 用户类型（user：用户，fans：APP粉丝）
	 * @param commonParameters 用户信息
	 * @param countType	计数类型:lookNum(查看数)，likeNum(点赞数)，commentNum(评论数)
	 * @param beCountIds 被计数对象id集合
	 * @return
	 */
	public List<Map<String, Object>> queryManyNumCount(String userType,CommonParameters commonParameters,
			String countType,List<String> beCountIds);
	/**
	 * 查看某一对象全部类型计数
	 * @param userType 用户类型（user：用户，fans：APP粉丝）
	 * @param commonParameters 用户信息
	 * @param beCountId 被计数对象id
	 * @return
	 */
	public Map<String, String> queryNumCountByObject(String userType,CommonParameters commonParameters,
			String beCountId);
}
