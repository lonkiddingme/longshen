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
import com.cdvcloud.douting.service.CheckLikeAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 点赞 管理
 *
 * @author zhangyuelong
 */

@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class CheckLikeAppApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");


    @Autowired
    private CheckLikeAppService checkLikeAppService;

    /**
     * 验证当前登录用户是否点赞了该内容
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
    @RequestMapping("v1/appApi/checkLike")
    public ResponseObject checkLike(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CheckLikeAppApiController --> method : checkLike " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = checkLikeAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : CheckLikeAppApiController --> method : checkLike " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("beLikeId");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CheckLikeAppApiController --> method : checkLike " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            resObj = checkLikeAppService.checkLike(commonParameters, jsonMap);
        } catch (Exception e) {
            ValidateCommonParam.innerError(resObj);
            logger.error("class : CheckLikeAppApiController --> method : checkLike处理异常！");
        }
        return resObj;
    }
    
    /**
     * 添加点赞
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
    @RequestMapping("v1/appApi/createLikeByApp")
    public ResponseObject createLikeByApp(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CheckLikeAppApiController --> method : createLikeByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
          //用户非空校验
            boolean checkFans = checkLikeAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : CheckLikeAppApiController --> method : createLikeByApp " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
          //用户状态验证
            boolean checkFansStatus = checkLikeAppService.checkFansStatus(commonParameters);
            if (!checkFansStatus) {
                logger.error("class : CommentsAppApiController --> method : createLikeByApp " + GeneralStatus.user_status_error.enDetail);
                ValidateCommonParam.userStatusError(resObj);
                return resObj;
            }
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("beLikeId");
            names.add("beLikeName");
            names.add("beLikeType");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CheckLikeAppApiController --> method : createLikeByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            resObj = checkLikeAppService.createLikeByApp(commonParameters, jsonMap);
        } catch (Exception e) {
            ValidateCommonParam.innerError(resObj);
            logger.error("class : CheckLikeAppApiController --> method : createLikeByApp处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 取消点赞
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
    @RequestMapping("v1/appApi/cancelLikeByApp")
    public ResponseObject cancelLikeByApp(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CheckLikeAppApiController --> method : cancelLikeByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
          //用户非空校验
            boolean checkFans = checkLikeAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : CheckLikeAppApiController --> method : cancelLikeByApp " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
          //用户状态验证
            boolean checkFansStatus = checkLikeAppService.checkFansStatus(commonParameters);
            if (!checkFansStatus) {
                logger.error("class : CommentsAppApiController --> method : cancelLikeByApp " + GeneralStatus.user_status_error.enDetail);
                ValidateCommonParam.userStatusError(resObj);
                return resObj;
            }
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("beLikeId");
            
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : CheckLikeAppApiController --> method : cancelLikeByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            resObj = checkLikeAppService.cancelLikeByApp(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : CheckLikeAppApiController --> method : cancelLikeByApp处理异常！");
        }
        return resObj;
    }
}
