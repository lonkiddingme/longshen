package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

public interface CommentsAppService extends BaseService{

	/**
	 *  根据条件分页查询评论列表
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject queryCommentsByApp(CommonParameters commonParameters,Map<String, Object> param);


	/**
	 * 创建评论
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject createCommentByApp(CommonParameters commonParameters,Map<String, Object> param);


	/**
	 * 删除评论
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	ResponseObject deleteComment(CommonParameters commonParameters,Map<String, Object> param);

}
