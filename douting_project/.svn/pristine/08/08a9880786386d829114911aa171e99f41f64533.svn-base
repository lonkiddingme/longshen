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
import com.cdvcloud.douting.service.BroadcastService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.Pages;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 逗播管理
 * BroadcastController
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class BroadcastApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");

    @Autowired
    private BroadcastService broadcastService;

    /**
     * 创建 逗播直播间
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
    @RequestMapping("v1/broadcast/createBroadcast")
    public ResponseObject createBroadcast(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : createBroadcast " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastApiController --> method : createBroadcast " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            String id = broadcastService.createBroadcast(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("id", id);
            ValidateCommonParam.executeSuccess(resObj, data);

        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastApiController --> method : createBroadcast处理异常！");
        }
        return resObj;
    }


    /**
     * 删除逗播直播间
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
    @RequestMapping("v1/broadcast/deleteBroadcast")
    public ResponseObject deleteBroadcast(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : deleteBroadcast " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastApiController --> method : deleteBroadcast " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空验证
            List<String> names = new ArrayList<String>();
            names.add("id");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : deleteBroadcast " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = broadcastService.deleteBroadcast(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.delete_success.status);
            resObj.setMessage(GeneralStatus.delete_success.detail);
            resObj.setData(data);

        } catch (Exception e) {
        	e.printStackTrace();
            // TODO Auto-generated catch block
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastApiController --> method : deleteBroadcast处理异常！");
        }

        return resObj;
    }


    /**
     * 按照ID修改逗播直播间
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
    @RequestMapping("v1/broadcast/updateBroadcastById")
    public ResponseObject updateBroadcastById(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : updateBroadcastById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastApiController --> method : updateBroadcastById " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空验证
            List<String> names = new ArrayList<String>();
            names.add("id");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : updateBroadcastById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = broadcastService.updateBroadcastById(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.update_success.status);
            resObj.setMessage(GeneralStatus.update_success.detail);
            resObj.setData(data);

        } catch (Exception e) {
        	e.printStackTrace();
            // TODO Auto-generated catch block
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastApiController --> method : updateBroadcastById处理异常！");
        }

        return resObj;
    }


    /**
     * 按照ID查询逗播直播间
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
    @RequestMapping("v1/broadcast/queryBroadcastById")
    public ResponseObject queryBroadcastById(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : queryBroadcastById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastApiController --> method : queryBroadcastById " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空验证
            List<String> names = new ArrayList<String>();
            names.add("id");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : queryBroadcastById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String, Object> data = broadcastService.queryBroadcastById(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);

        } catch (Exception e) {
        	e.printStackTrace();
            // TODO Auto-generated catch block
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastApiController --> method : queryBroadcastById处理异常！");
        }

        return resObj;
    }


    /**
     * 查询逗播直播间，分页查询
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
    @RequestMapping("v1/broadcast/queryBroadcast4Page")
    public ResponseObject queryBroadcast4Page(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : queryBroadcast4Page " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastApiController --> method : queryBroadcast4Page " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            Pages data = broadcastService.queryBroadcast4Page(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);

        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastApiController --> method : queryBroadcast4Page处理异常！");
        }
        return resObj;
    }

    /**
     * 更新直播间状态
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
    @RequestMapping("v1/broadcast/updateBroadcastStatus")
    public ResponseObject updateBroadcastStatus(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : updateBroadcastStatus " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastApiController --> method : updateBroadcastStatus " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空验证
            List<String> names = new ArrayList<String>();
            names.add("id");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : updateBroadcastStatus " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = broadcastService.updateBroadcastStatus(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.update_success.status);
            resObj.setMessage(GeneralStatus.update_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastApiController --> method : updateBroadcastStatus处理异常！");
        }
        return resObj;
    }


    /**
     * 查询所有直播间
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
    @RequestMapping("v1/broadcast/queryBroadcastAll")
    public ResponseObject queryBroadcastAll(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : queryBroadcastAll " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastApiController --> method : queryBroadcastAll " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            List<Map<String, Object>> data = broadcastService.queryBroadcastAll(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastApiController --> method : queryBroadcastAll处理异常！");
        }
        return resObj;
    }

    /**
     * 统计直播间
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
    @RequestMapping("v1/broadcast/countBroadcast")
    public ResponseObject countBroadcast(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : countBroadcast " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastApiController --> method : countBroadcast " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastApiController --> method : countBroadcast " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long count = broadcastService.countBroadcast(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>(16);
            data.put("count", count);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastApiController --> method : countBroadcast处理异常！");
        }
        return resObj;
    }
}
