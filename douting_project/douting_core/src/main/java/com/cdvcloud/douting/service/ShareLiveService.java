package com.cdvcloud.douting.service;

import com.cdvcloud.douting.common.CommonParameters;

import java.util.Map;

/**
 * 公共能力
 *
 * @author SongYuanKun
 * @date 2017/11/4
 */
public interface ShareLiveService extends BaseService {

    /**
     *分享页轮询数据
     *
     * @param commonParameters
     * @param jsonMap
     * @return
     */
	Map<String, Object> pollingLiveData(CommonParameters commonParameters, Map<String, Object> jsonMap);

}
