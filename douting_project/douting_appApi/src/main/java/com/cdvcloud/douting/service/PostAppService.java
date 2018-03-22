package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

public interface PostAppService extends BaseService{

	/**
	 *  创建帖子
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject createPostByApp(CommonParameters commonParameters,Map<String, Object> param);


	/**
	 * 分页查询帖子
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject queryPostsByApp(CommonParameters commonParameters,Map<String, Object> param);


	/**
	 * 删除帖子
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject deletePost(CommonParameters commonParameters,Map<String, Object> param);

	/**
	 * 参与活动
	 * @param commonParameters
	 * @param jsonMap
	 * @return
	 */
	ResponseObject createActivity(CommonParameters commonParameters,
			Map<String, Object> jsonMap);

	/**
	 * 查看活动参与情况
	 * @param commonParameters
	 * @param jsonMap
	 * @return
	 */
	ResponseObject queryActivity(CommonParameters commonParameters,
			Map<String, Object> jsonMap);

}
