package com.cdvcloud.douting.dao;

import java.util.Map;

/**
 * 公共dao层
 *
 * @author SongYuanKun
 * @date 2017/11/4
 */
public interface PublicDao {

    /**
     * inc更新方法
     *
     * @return 更新条数
     */

    long incValue(Map<String, Object> jsonMap);
}