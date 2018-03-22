package com.cdvcloud.douting.web.api;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.GeneralStatus;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.BroadcastColumn;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.service.BroadcastAppService;
import com.cdvcloud.douting.service.BroadcastService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.Pages;
import com.cdvcloud.rms.util.ResponseObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SongYuanKun
 * @date 2017/11/12
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class BroadcastAppApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");

    @Autowired
    private BroadcastService broadcastService;
    @Autowired
    private BroadcastAppService broadcastAppService;

    /**
     * 分页查询逗播直播间列表
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "v1/appApi/queryBroadcastList")
    public ResponseObject queryBroadcastList(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastAppApiController --> method : queryBroadcastList " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            
            //用户非空校验
            boolean checkFans = broadcastService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : BroadcastAppApiController --> method : queryBroadcastList " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            
            
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("currentPage");
            names.add("pageNum");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastAppApiController --> method : queryBroadcastList " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String, Object> sortParam = new HashMap<String, Object>(16);
            sortParam.put(QueryBasicObject.WEIGTH, -1);
            jsonMap.put("sort", sortParam);
          //状态筛选
            Map<String, Object> conditionParam = new HashMap<String, Object>(16);
            conditionParam.put(QueryBasicObject.STATUS, 1);
            conditionParam.put(BasicObject.COMPANYID, companyId);
            jsonMap.put("conditionParam", conditionParam);
            Pages pages = broadcastService.queryBroadcast4Page(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(pages);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastAppApiController --> method : queryBroadcastList处理异常！");
        }
        return resObj;
    }

    /**
     * 通过逗播直播间id查询逗播直播间帖子栏目列表
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "v1/appApi/queryBroadcastPost")
    public ResponseObject queryBroadcastPost(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastAppApiController --> method : queryBroadcastPost " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = broadcastAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : BroadcastAppApiController --> method : queryBroadcastList " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add(BroadcastColumn.BROADCASTID);
            names.add("currentPage");
            names.add("pageNum");
            names.add("broadcastId");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastAppApiController --> method : queryBroadcastPost " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String,Object> data = broadcastAppService.queryBroadcastPost(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastAppApiController --> method : queryBroadcastPost处理异常！");
        }
        return resObj;
    }
}
