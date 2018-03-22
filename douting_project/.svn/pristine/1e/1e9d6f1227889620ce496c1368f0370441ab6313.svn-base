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
import com.cdvcloud.douting.service.BroadcastColumnService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.Pages;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 逗播直播间-节目管理
 * BroadcastColumnApiController
 *
 * @author zhangyuelong
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class BroadcastColumnApiController {

    private static final Logger logger = Logger.getLogger("ValidateCommonParam");

    @Autowired
    private BroadcastColumnService broadcastColumnService;

    /**
     * 创建 直播间 节目
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
    @RequestMapping("v1/broadcastColumn/createBroadcastColumn")
    public ResponseObject createBroadcastColumn(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {

            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : createBroadcastColumn " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastColumnService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastColumnApiController --> method : createBroadcastColumn " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            String id = broadcastColumnService.createBroadcastColumn(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("id", id);
            ValidateCommonParam.executeSuccess(resObj, data);

        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastColumnApiController --> method : createBroadcastColumn 处理异常！");
        }
        return resObj;

    }

    /**
     * 更新栏目
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
    @RequestMapping("v1/broadcastColumn/updateBroadcastColumnById")
    public ResponseObject updateBroadcastColumnById(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : updateBroadcastColumnById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastColumnService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastColumnApiController --> method : updateBroadcastColumnById " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }

            long influenceLine = broadcastColumnService.updateBroadcastColumnById(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.update_success.status);
            resObj.setMessage(GeneralStatus.update_success.detail);
            resObj.setData(data);

        } catch (Exception e) {
        	e.printStackTrace();
            // TODO Auto-generated catch block
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastColumnController --> method : updateBroadcastColumnById处理异常！");
        }

        return resObj;
    }


    /**
     * 删除节目栏
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
    @RequestMapping("v1/broadcastColumn/deleteBroadcastColumn")
    public ResponseObject deleteBroadcastColumn(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : deleteBroadcastColumn " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastColumnService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastColumnApiController --> method : deleteBroadcastColumn " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空验证
            List<String> names = new ArrayList<String>();
            names.add("ids");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : deleteBroadcastColumn " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = broadcastColumnService.deleteBroadcastColumn(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.delete_success.status);
            resObj.setMessage(GeneralStatus.delete_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastColumnApiController --> method : deleteBroadcastColumn处理异常！");
        }
        return resObj;
    }


    /**
     * 按照ID查询直播间 栏目
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
    @RequestMapping("v1/broadcastColumn/queryBroadcastColumnById")
    public ResponseObject queryBroadcastColumnById(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : queryBroadcastColumnById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastColumnService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastColumnApiController --> method : queryBroadcastColumnById " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空验证
            List<String> names = new ArrayList<String>();
            names.add("id");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : queryBroadcastColumnById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }

            Map<String, Object> data = broadcastColumnService.queryBroadcastColumnById(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);

        } catch (Exception e) {
        	e.printStackTrace();
            // TODO Auto-generated catch block
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastColumnApiController --> method : queryBroadcastColumnById处理异常！");
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
    @RequestMapping("v1/broadcastColumn/queryBroadcastColumn4Page")
    public ResponseObject queryBroadcastColumn4Page(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : queryBroadcastColumn4Page " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastColumnService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastColumnApiController --> method : queryBroadcastColumn4Page " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            Pages data = broadcastColumnService.queryBroadcastColumn4Page(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);

        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastColumnApiController --> method : queryBroadcastColumn4Page处理异常！");
        }
        return resObj;
    }


    /**
     * 节目栏状态 上下架 更新
     * 批量
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
    @RequestMapping("v1/broadcast/updateBroadcastColumnStatus")
    public ResponseObject updateBroadcastColumnStatus(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode,@Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : updateBroadcastColumnStatus " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkUser = broadcastColumnService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : BroadcastColumnApiController --> method : updateBroadcastColumnStatus " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空验证
            List<String> names = new ArrayList<String>();
            names.add("ids");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BroadcastColumnApiController --> method : updateBroadcastColumnStatus " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = broadcastColumnService.updateBroadcastColumnStatus(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.update_success.status);
            resObj.setMessage(GeneralStatus.update_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BroadcastColumnApiController --> method : updateBroadcastColumnStatus处理异常！");
        }
        return resObj;
    }

}
