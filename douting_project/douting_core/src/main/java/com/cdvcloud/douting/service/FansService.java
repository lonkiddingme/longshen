package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;

/**
 * 粉丝缓存管理
 *
 * @author SongYuanKun
 * @date 2017/10/25
 */
public interface FansService extends BaseService {
	
	public long deleteFansRedisById (CommonParameters commonParameters,Map<String, Object> jsonMap);
}
