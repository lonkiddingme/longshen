package com.cdvcloud.douting.service;

import com.cdvcloud.douting.common.CommonParameters;

import java.util.Map;

/**
 * 公共能力
 *
 * @author SongYuanKun
 * @date 2017/11/4
 */
public interface PublicService extends BaseService {

    /**
     * 更新排序字段
     *
     * @param commonParameters
     * @param jsonMap
     * @return
     */
    long updateWeight(CommonParameters commonParameters, Map<String, Object> jsonMap);

}
