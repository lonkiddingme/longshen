package com.cdvcloud.douting.service;

import com.cdvcloud.douting.common.CommonParameters;

/**
 * 基础service
 *
 * @author Administrator
 */
public interface BaseService {
    /**
     * 获取global表中的数据
     *
     * @param key 键
     * @return 值
     */
	public String getConfigurationValue(String key);

    /**
     * 验证用户
     *
     * @param commonParameters commonParameters
     * @return true存在、false不存在
     */
    public boolean checkUser(CommonParameters commonParameters);
    /**
     * 验证粉丝
     * @param commonParameters
     * @return
     */
    public boolean checkFans(CommonParameters commonParameters);
    
    /**
     * 验证粉丝状态是否能评论
     * status 0 不能评论 
     * @param commonParameters
     * @return
     */
    public boolean checkFansStatus(CommonParameters commonParameters);
    
}
