package com.cdvcloud.douting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.ColumnDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.Column;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.service.FollowAppService;
import com.cdvcloud.douting.service.NumCountService;
import com.cdvcloud.douting.service.TaskProgressAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;

/**
 * APP关注管理
 */
@Service
public class FollowAppServiceImpl extends BaseServiceImpl implements FollowAppService {

    @Autowired
    private HttpCommonUtil httpCommonUtil;

    @Autowired
    private NumCountService numCountService;
    @Autowired
    private ColumnDao columnDao;
    
    @Autowired
    private TaskProgressAppService taskProgressAppService; 
    /**
     * 关注验证
     */
    @Override
    public ResponseObject checkFollow(CommonParameters commonParameters, Map<String, Object> param) {
        ResponseObject responseObject = new ResponseObject();
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("checkFollow");
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        param.put(QueryBasicObject.USERTYPE, QueryBasicObject.FANS);
        String responseString = httpCommonUtil.doPost(url, param);
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
    }

    /**
     * 添加关注
     */
    @SuppressWarnings("unchecked")
	@Override
    public ResponseObject createFollowByApp(CommonParameters commonParameters, Map<String, Object> param) {
        ResponseObject responseObject = new ResponseObject();
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("createFollowByApp");
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        param.put(QueryBasicObject.USERTYPE, QueryBasicObject.FANS);
        String responseString = httpCommonUtil.doPost(url, param);
        
        
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
            //统计计数
            Map<String,Object> data = (Map<String, Object>) responseObject.getData();
            String influenceNum = String.valueOf(data.get("influenceNum"));
            if(influenceNum.equals("1")){
            	numCountService.doNumCount("fans", commonParameters, "followNum", String.valueOf(param.get("beFollowedId")), 1, String.valueOf(param.get("beFollowedType")));
            	
            	
            	//首次关注任务详情
            	param = new HashMap<String, Object>();
                param.put("identification", "firstFollow");
                param.put("type", "newbieTask");
            	taskProgressAppService.updateTaskProgress(commonParameters, param);
            
            }
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
    }

    /**
     * 取消关注
     */
    @SuppressWarnings("unchecked")
	@Override
    public ResponseObject cancelFollowByApp(CommonParameters commonParameters, Map<String, Object> param) {
        ResponseObject responseObject = new ResponseObject();
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("cancelFollowByApp");
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        param.put(QueryBasicObject.USERTYPE, QueryBasicObject.FANS);
        String responseString = httpCommonUtil.doPost(url, param);
        
       
        
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
            //统计计数
            Map<String,Object> data = (Map<String, Object>) responseObject.getData();
            String influenceNum = String.valueOf(data.get("influenceNum"));
            if(influenceNum.equals("1")){
            	numCountService.doNumCount("fans", commonParameters, "followNum", String.valueOf(param.get("beFollowedId")), -1, String.valueOf(param.get("beFollowedType")));
            }
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
    }

    /**
     * 查询粉丝关注列表
     *
     * @param commonParameters
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public ResponseObject queryFollowByFansId(CommonParameters commonParameters, Map<String, Object> param) {
        ResponseObject responseObject = new ResponseObject();
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("queryFollow4Page");
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        param.put(QueryBasicObject.USERTYPE, QueryBasicObject.FANS);
        String fansId = String.valueOf(param.get("fansId"));
        
        Map<String, Object> conditionParam = new HashMap<String, Object>(16);
        conditionParam.put(BasicObject.USERID, fansId);
        conditionParam.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        
        param.put("conditionParam", conditionParam);
        
        String responseString = httpCommonUtil.doPost(url, param);
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
            //根据被关注ID 查询关注的节目栏的图片
            if (responseObject != null && responseObject.getData() != null) {
            	List<Map<String, Object>> followList = (List<Map<String, Object>>) responseObject.getData();
            	for (Map<String, Object> map : followList) {
					String beFollowedId = String.valueOf(map.get("beFollowedId"));
					String beFollowedType = String.valueOf(map.get("beFollowedType"));
					if(beFollowedType.equals("column")){
						Map<String, Object> columnMap= columnDao.queryColumnById(beFollowedId);
						if(columnMap!=null&&!columnMap.isEmpty()){
							String thumbnailUrl = String.valueOf(columnMap.get(Column.THUMBNAILURL));
							map.put(Column.THUMBNAILURL,thumbnailUrl);
						}
					}
				}
            }
            
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
    }

}
