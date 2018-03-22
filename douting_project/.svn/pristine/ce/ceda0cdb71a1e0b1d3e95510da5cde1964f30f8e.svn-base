package com.cdvcloud.douting.web.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.GeneralStatus;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.service.CommentsAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 评论 管理
 *
 * @author zhangyuelong
 */

@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class CommentsAppApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");


    @Autowired
    private CommentsAppService commentsAppService;

    /**
     * 根据条件分页查询评论列表
     *
     * @param companyId        租户ID
     * @param appCode          应用标识
     * @param userId           用户ID
     * @param serviceCode      服务标识
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/appApi/queryCommentsByApp")
    public ResponseObject queryCommentsByApp(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CommentsAppApiController --> method : queryCommentsByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
          //用户非空校验
            boolean checkFans = commentsAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : CommentsAppApiController --> method : queryCommentsByApp " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("userType");
            names.add("currentPage");
            names.add("pageNum");
            names.add("conditionParam");
            names.add("sort");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CommentsAppApiController --> method : queryCommentsByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            resObj = commentsAppService.queryCommentsByApp(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : CommentsAppApiController --> method : queryCommentsByApp处理异常！");
        }
        return resObj;
    }


    /**
     * 创建评论
     *
     * @param companyId        租户ID
     * @param appCode          应用标识
     * @param userId           用户ID
     * @param serviceCode      服务标识
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/appApi/createCommentByApp")
    public ResponseObject createCommentByApp(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CommentsAppApiController --> method : createCommentByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = commentsAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : CommentsAppApiController --> method : createCommentByApp " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
          //用户状态验证
            boolean checkFansStatus = commentsAppService.checkFansStatus(commonParameters);
            if (!checkFansStatus) {
                logger.error("class : CommentsAppApiController --> method : createCommentByApp " + GeneralStatus.user_status_error.enDetail);
                ValidateCommonParam.userStatusError(resObj);
                return resObj;
            }
            
            
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("userType");
            names.add("sid");
            names.add("stype");
            names.add("pid");
            names.add("ptype");
            names.add("beCommentedId");
            names.add("beCommentedName");
            names.add("doCommentId");
            names.add("doCommentName");
            names.add("content");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CommentsAppApiController --> method : createCommentByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            resObj = commentsAppService.createCommentByApp(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : CommentsAppApiController --> method : createCommentByApp处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 删除评论
     *
     * @param companyId        租户ID
     * @param appCode          应用标识
     * @param userId           用户ID
     * @param serviceCode      服务标识
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/appApi/deleteComment")
    public ResponseObject deleteComment(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CommentsAppApiController --> method : deleteComment " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = commentsAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : CommentsAppApiController --> method : deleteComment " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
          //用户状态验证
            boolean checkFansStatus = commentsAppService.checkFansStatus(commonParameters);
            if (!checkFansStatus) {
                logger.error("class : CommentsAppApiController --> method : deleteComment " + GeneralStatus.user_status_error.enDetail);
                ValidateCommonParam.userStatusError(resObj);
                return resObj;
            }
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("userType");
            names.add("commentId");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CommentsAppApiController --> method : deleteComment " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            resObj = commentsAppService.deleteComment(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : CommentsAppApiController --> method : deleteComment处理异常！");
        }
        return resObj;
    }
}
