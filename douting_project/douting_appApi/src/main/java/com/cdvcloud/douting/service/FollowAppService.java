package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.ResponseObject;

public interface FollowAppService extends BaseService{

    /**
     * 验证当前登录用户是否关注了该内容
     *
     * @param commonParameters
     * @param param
     * @return
     */
    ResponseObject checkFollow(CommonParameters commonParameters, Map<String, Object> param);


    /**
     * 添加关注
     *
     * @param commonParameters
     * @param param
     * @return
     */
    ResponseObject createFollowByApp(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 取消关注
     *
     * @param commonParameters
     * @param param
     * @return
     */
    ResponseObject cancelFollowByApp(CommonParameters commonParameters, Map<String, Object> param);

    /**
     * 查询粉丝关注列表
     *
     * @param commonParameters
     * @param jsonMap
     * @return
     */
    ResponseObject queryFollowByFansId(CommonParameters commonParameters, Map<String, Object> jsonMap);
}
