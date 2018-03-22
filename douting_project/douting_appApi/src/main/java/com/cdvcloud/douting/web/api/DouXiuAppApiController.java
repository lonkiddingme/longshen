package com.cdvcloud.douting.web.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.GeneralStatus;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.service.DouXiuAppService;
import com.cdvcloud.rms.logs.LogsAnnotation;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 逗秀 管理
 * ColumnApiController
 *
 * @author SongYuanKun
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class DouXiuAppApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");
    
    @Autowired
    private DouXiuAppService douXiuAppService;

    /**
     * 为APP首页提供数据查询接口
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
    @RequestMapping("v1/appApi/douXiuHome")
    @LogsAnnotation(MethodAction = "查询", MethodAscription = "逗秀首页")
    public ResponseObject douXiuHome(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : douXiuHome " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
          //用户非空校验
            boolean checkFans = douXiuAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : DouXiuAppApiController --> method : douXiuHome " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空校验
            
            
            
            
            List<String> names = new ArrayList<String>();
            names.add("type");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : douXiuHome " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String,Object> data = douXiuAppService.douXiuHome(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : DouXiuAppApiController --> method : douXiuHome处理异常！");
        }
        return resObj;
    }

    
    

    /**
     * 为APP首页提供数据查询接口
     * 逗秀栏目下内容列表
     * @param companyId        租户ID
     * @param appCode          应用标识
     * @param userId           用户ID
     * @param serviceCode      服务标识
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/appApi/queryColumnContents")
    @LogsAnnotation(MethodAction = "查询", MethodAscription = "逗秀栏目下内容列表")
    public ResponseObject queryColumnContents(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : queryColumnContents " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = douXiuAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : DouXiuAppApiController --> method : queryColumnContents " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("pageNum");
            names.add("currentPage");
            names.add("columnId");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : queryColumnContents " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String,Object> data = douXiuAppService.queryColumnContents(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : DouXiuAppApiController --> method : queryColumnContents处理异常！");
        }
        return resObj;
    }

    
    /**
     * 根据ID 查询逗秀节目栏
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
    @RequestMapping("v1/appApi/queryColumn")
    @LogsAnnotation(MethodAction = "查询", MethodAscription = "逗秀栏目")
    public ResponseObject queryColumn(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : queryColumn " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = douXiuAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : DouXiuAppApiController --> method : queryColumn " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("columnId");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : queryColumn " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String, Object> data = douXiuAppService.queryColumn(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : DouXiuAppApiController --> method : queryColumn处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 根据DOCID 查询 秀场内容详情
     * @param companyId        租户ID
     * @param appCode          应用标识
     * @param userId           用户ID
     * @param serviceCode      服务标识
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/appApi/queryContent")
    @LogsAnnotation(MethodAction = "查询", MethodAscription = "逗秀内容")
    public ResponseObject queryContent(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : queryContent " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = douXiuAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : DouXiuAppApiController --> method : queryContent " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("docid");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : queryContent " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String, Object> data = douXiuAppService.queryContent(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : DouXiuAppApiController --> method : queryContent处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 根据逗秀栏目类型分类查询逗秀栏目
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
    @RequestMapping("v1/appApi/queryColumnByType")
    @LogsAnnotation(MethodAction = "查询", MethodAscription = "逗秀栏目")
    public ResponseObject queryColumnByType(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : queryColumnByType " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
          //用户非空校验
            boolean checkFans = douXiuAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : DouXiuAppApiController --> method : queryColumnByType " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空校验
            
            
            List<String> names = new ArrayList<String>();
            names.add("type");
            names.add("pageNum");
            names.add("currentPage");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : DouXiuAppApiController --> method : queryColumnByType " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String,Object> data = douXiuAppService.queryColumnByType(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : DouXiuAppApiController --> method : queryColumnByType处理异常！");
        }
        return resObj;
    }
}
