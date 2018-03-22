package com.cdvcloud.douting.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.dao.RedisUtilDao;
import com.cdvcloud.douting.service.ShareLiveService;

/**
 * 基础service
 *
 * @author Administrator
 */
@Service
public class ShareLiveServiceImpl extends BaseServiceImpl implements ShareLiveService {
	@Autowired
	private  RedisUtilDao redisUtilDao;
	@Override
	public Map<String, Object> pollingLiveData(CommonParameters commonParameters, Map<String, Object> param) {

		Map<String, Object> data = new HashMap<String, Object>();
		String pid = String.valueOf(param.get("id"));
		
		Map<String, Object> deleteCommentIdsList = redisUtilDao.getDeleteCommentIds(pid,param);
		Map<String, Object> commentList = redisUtilDao.queryCommentEfficient(pid,param);
		//公告
		data.put("commentList", commentList);
		data.put("deleteCommentIdsList", deleteCommentIdsList);
		return data;
	
	}
	
}
