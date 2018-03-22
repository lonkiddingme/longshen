package com.cdvcloud.douting.service.impl;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.PublicDao;
import com.cdvcloud.douting.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author SongYuanKun
 * @date 2017/11/4
 */
@Service
public class PublicServiceImpl extends BaseServiceImpl implements PublicService {
    @Autowired
    private PublicDao publicDao;

    /**
     * 更新排序字段
     *
     * @param commonParameters
     * @param jsonMap
     * @return
     */
    @Override
    public long updateWeight(CommonParameters commonParameters, Map<String, Object> jsonMap) {

        Map<String, Object> updateMap = new HashMap<String, Object>(16);
        updateMap.putAll(ValidateCommonParam.getFieldsMap(Arrays.asList("document", "key", "value", "ids"), jsonMap));

        return publicDao.incValue(jsonMap);
    }
}
