package com.cdvcloud.douting.service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

import java.util.Map;

/**
 * @author SongYuanKun
 * @date 2017/10/31
 */
public interface BigDataService extends BaseService {
    /**
     * 通过docId查询大数据的数据
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return 单个文稿数据
     * @throws Exception 字符串转换成Map异常
     */
    Map<String, Object> queryBigDataByDocId(CommonParameters commonParameters, Map<String, Object> param) throws Exception;

    /**
     * 通过词标查询大数据的数据
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return 分页数据
     * @throws Exception 字符串转换成Map异常
     */
    Pages queryBigDataByWordMarkId(CommonParameters commonParameters, Map<String, Object> param) throws Exception;
}
