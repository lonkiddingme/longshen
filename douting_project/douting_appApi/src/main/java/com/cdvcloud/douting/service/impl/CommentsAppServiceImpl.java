package com.cdvcloud.douting.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.GeneralStatus;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.ContentDao;
import com.cdvcloud.douting.dao.RedisUtilDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.service.CommentsAppService;
import com.cdvcloud.douting.service.NumCountService;
import com.cdvcloud.douting.service.TaskProgressAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;
/**
 * APP评论管理
 *
 */
@Service
public class CommentsAppServiceImpl extends BaseServiceImpl implements CommentsAppService {

	@Autowired
    private HttpCommonUtil httpCommonUtil;
	
	@Autowired
	private NumCountService numCountService;
	
	@Autowired
	private  RedisUtilDao redisUtilDao;
	  
    @Autowired
    private ContentDao contentDao;
    
    @Autowired
    private TaskProgressAppService taskProgressAppService; 
	/**
	 * 根据条件分页查询评论列表
	 * 
	 *  
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResponseObject queryCommentsByApp(CommonParameters commonParameters, Map<String, Object> param) {

		ResponseObject responseObject = new ResponseObject();
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("queryCommentsByApp");
        //补全查询状态信息
        if(param.get(QueryBasicObject.CONDITIONPARAM)!=null){
        	Map<String, Object> map = (Map<String, Object>) param.get("conditionParam");
        	map.put(QueryBasicObject.STATUS, 1);
        	map.put(QueryBasicObject.STATUS_ZH, "已发布");
        	map.put(QueryBasicObject.ISCACHE, "yes");
        	map.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        	param.put(QueryBasicObject.ISCONCURRENT, true);
        	param.put(QueryBasicObject.CONDITIONPARAM, map);
        }
        
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        String responseString = httpCommonUtil.doPost(url, param);
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
	}

	/**
	 * 创建评论
	 */
	@Override
	public ResponseObject createCommentByApp(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("createCommentByApp");
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        String stype = String.valueOf(param.get("stype"));
        
        if(stype.equals("content")){
        	String docid = String.valueOf(param.get("sid"));
        	redisUtilDao.NewestCommentedContent(docid);
        	
        	Map<String, Object> contentMap = contentDao.queryContentByDocId(docid);
        	String isComment = String.valueOf(contentMap.get("isComment"));
        	if(isComment.equals("0")){
        		responseObject.setCode(GeneralStatus.comment_error.status);
        		responseObject.setMessage(GeneralStatus.comment_error.detail);
        		responseObject.setData(null);
        		return responseObject;
        	}
        }
        //补全 状态信息
        param.put(QueryBasicObject.STATUS, 0);
        param.put(QueryBasicObject.STATUS_ZH, "未审核");
        
        String responseString = httpCommonUtil.doPost(url, param);
        
        if (!StringUtil.isEmpty(responseString)) {
           responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
          //统计计数
           if(param.get("pid")!=null&&param.get("sid")!=null){
        	    String sid = String.valueOf(param.get("sid"));
        	    numCountService.doNumCount("fans", commonParameters, "commentNum", sid, 1, "comment");
          
	        	 //创建评论任务详情
	           	param = new HashMap<String, Object>();
	           	Map<String, Object> data = new HashMap<String,Object>();
	           	data.put("firstComment", "newbieTask");
	           	data.put("dailyComment", "dailyTask");
	            param.put("conditionParam", data);
	            param.put("others",1);
	           	taskProgressAppService.updateTaskProgress(commonParameters, param);
           
           }
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
	}
	/**
	 * 删除评论
	 */
	@Override
	public ResponseObject deleteComment(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("deleteComment");
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        param.put(QueryBasicObject.ISCACHE, "yes");
        String responseString = httpCommonUtil.doPost(url, param);
        if (!StringUtil.isEmpty(responseString)) {
            responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
        } else {
            ValidateCommonParam.innerError(responseObject);
        }
        return responseObject;
	}
	
	
	
}
