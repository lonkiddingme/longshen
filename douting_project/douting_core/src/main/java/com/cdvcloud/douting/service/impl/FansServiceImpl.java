package com.cdvcloud.douting.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.dao.RedisUtilDao;
import com.cdvcloud.douting.service.FansService;

/**
 * 主播管理
 *
 * @author SongYuanKun
 */
@Service
public class FansServiceImpl extends BaseServiceImpl implements FansService {
	
	@Autowired
	private RedisUtilDao redisUtilDao;
	

	@Override
	public long deleteFansRedisById(CommonParameters commonParameters, Map<String, Object> jsonMap) {
		String id = String.valueOf(jsonMap.get("id"));
		return redisUtilDao.deleteFansMsgById(id);
	}
	
}
