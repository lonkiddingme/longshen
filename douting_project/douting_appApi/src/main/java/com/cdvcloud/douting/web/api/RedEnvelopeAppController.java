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
import com.cdvcloud.douting.service.RedEnvelopeAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 红包管理
 *
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class RedEnvelopeAppController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");

    @Autowired
    private RedEnvelopeAppService redEnvelopeAppService;

    /**
     * 定时红包接口
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/timedRedEnvelope")
    public ResponseObject timedRedEnvelope(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : RedEnvelopeAppController --> method : timedRedEnvelope " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = redEnvelopeAppService.checkFans(commonParameters);
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("pid");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : RedEnvelopeAppController --> method : timedRedEnvelope " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            if (!checkFans) {
                logger.error("class : RedEnvelopeAppController --> method : timedRedEnvelope " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = redEnvelopeAppService.timedRedEnvelope(commonParameters, jsonMap);
            
            
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : RedEnvelopeAppController --> method : timedRedEnvelope处理异常！");
        }
        return resObj;
    }
    
    /**
     * 主动发送红包接口
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/creatRedEnvelope")
    public ResponseObject creatRedEnvelope(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : RedEnvelopeAppController --> method : creatRedEnvelope " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = redEnvelopeAppService.checkFans(commonParameters);
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("partition");
            names.add("money");
            names.add("pid");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : RedEnvelopeAppController --> method : creatRedEnvelope " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            if (!checkFans) {
                logger.error("class : RedEnvelopeAppController --> method : creatRedEnvelope " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = redEnvelopeAppService.creatRedEnvelopeByanchor(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : RedEnvelopeAppController --> method : creatRedEnvelope处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 根据pid查询红包列表
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/getRedEnvelope")
    public ResponseObject getRedEnvelope(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : RedEnvelopeAppController --> method : getRedEnvelope " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = redEnvelopeAppService.checkFans(commonParameters);
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("pid");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : RedEnvelopeAppController --> method : getRedEnvelope " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            if (!checkFans) {
                logger.error("class : RedEnvelopeAppController --> method : getRedEnvelope " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = redEnvelopeAppService.getRedEnvelope(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : RedEnvelopeAppController --> method : getRedEnvelope处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 根据红包Id抢红包
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/grabRedEnvelope")
    public ResponseObject grabRedEnvelope(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : RedEnvelopeAppController --> method : grabRedEnvelope " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = redEnvelopeAppService.checkFans(commonParameters);
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("redEnvelopeId");
            names.add("pid");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : RedEnvelopeAppController --> method : grabRedEnvelope " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            if (!checkFans) {
                logger.error("class : RedEnvelopeAppController --> method : grabRedEnvelope " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = redEnvelopeAppService.grabRedEnvelope(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : RedEnvelopeAppController --> method : grabRedEnvelope处理异常！");
        }
        return resObj;
    }
}
