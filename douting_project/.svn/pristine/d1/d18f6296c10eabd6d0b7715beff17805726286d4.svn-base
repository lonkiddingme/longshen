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
import com.cdvcloud.douting.domain.Anchor;
import com.cdvcloud.douting.service.AnchorService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.Pages;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 主播管理
 * AnchorApiController
 *
 * @author SongYuanKun
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class AnchorApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");
    @Autowired
    private AnchorService anchorService;


    /**
     * 新增主播
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
    @RequestMapping("v1/anchor/createAnchor")
    public ResponseObject createAnchor(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : createAnchor " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户校验
            boolean checkUser = anchorService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : AnchorApiController --> method : createAnchor " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : createAnchor " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            String id = anchorService.createAnchor(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>(16);
            data.put("id", id);
            resObj.setCode(GeneralStatus.insert_success.status);
            resObj.setMessage(GeneralStatus.insert_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : createAnchor处理异常！");
        }
        return resObj;
    }

    /**
     * 通过id更新主播
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
    @RequestMapping("v1/anchor/updateAnchorById")
    public ResponseObject updateAnchorById(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //用户校验
            boolean checkUser = anchorService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : AnchorApiController --> method : updateAnchorById " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : updateAnchorById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("id");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : updateAnchorById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = anchorService.updateAnchorById(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>(16);
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.update_success.status);
            resObj.setMessage(GeneralStatus.update_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : updateAnchorById处理异常！");
        }
        return resObj;
    }

    /**
     * 删除主播
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
    @RequestMapping("v1/anchor/deleteAnchor")
    public ResponseObject deleteAnchor(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //用户校验
            boolean checkUser = anchorService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : AnchorApiController --> method : deleteAnchor " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : deleteAnchor " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("ids");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : deleteAnchor " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = anchorService.deleteAnchor(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>(16);
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.delete_success.status);
            resObj.setMessage(GeneralStatus.delete_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : deleteAnchor处理异常！");
        }
        return resObj;
    }

    /**
     * 查询主播，通过ID查询
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
    @RequestMapping("v1/anchor/queryAnchorById")
    public ResponseObject queryAnchorById(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //用户校验
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : queryAnchorById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("id");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : queryAnchorById " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String, Object> data = anchorService.queryAnchorById(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : queryAnchorById处理异常！");
        }
        return resObj;
    }

    /**
     * 查询所有主播
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
    @RequestMapping("v1/anchor/queryAnchorAll")
    public ResponseObject queryAnchorAll(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //用户校验
            boolean checkUser = anchorService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : AnchorApiController --> method : queryAnchorAll " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : queryAnchorAll " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : queryAnchorAll " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            List<Map<String, Object>> data = anchorService.queryAnchorAll(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : queryAnchorAll处理异常！");
        }
        return resObj;
    }

    /**
     * 查询主播，分页查询
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
    @RequestMapping("v1/anchor/queryAnchor4Page")
    public ResponseObject queryAnchor4Page(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //用户校验
            boolean checkUser = anchorService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : AnchorApiController --> method : queryAnchor4Page " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : queryAnchor4Page " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : queryAnchor4Page " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Pages data = anchorService.queryAnchor4Page(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : queryAnchor4Page处理异常！");
        }
        return resObj;
    }

    /**
     * 批量更新主播
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
    @RequestMapping("v1/anchor/updateAnchors")
    public ResponseObject updateAnchors(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //用户校验
            boolean checkUser = anchorService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : AnchorApiController --> method : updateAnchorStatus " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : updateAnchorStatus " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("ids");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : updateAnchorStatus " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = anchorService.updateAnchors(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>(16);
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.update_success.status);
            resObj.setMessage(GeneralStatus.update_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : updateAnchorStatus处理异常！");
        }
        return resObj;
    }

    /**
     * 更新主播状态
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
    @RequestMapping("v1/anchor/updateAnchorStatus")
    public ResponseObject updateAnchorStatus(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //用户校验
            boolean checkUser = anchorService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : AnchorApiController --> method : updateAnchorStatus " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : updateAnchorStatus " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("ids");
            names.add(Anchor.STATUS);
            names.add(Anchor.STATUS_ZH);
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : updateAnchorStatus " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long influenceLine = anchorService.updateAnchorStatus(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>(16);
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.update_success.status);
            resObj.setMessage(GeneralStatus.update_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : updateAnchorStatus处理异常！");
        }
        return resObj;
    }

    /**
     * 统计主播
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
    @RequestMapping("v1/anchor/countAnchor")
    public ResponseObject countAnchor(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //用户校验
            boolean checkUser = anchorService.checkUser(commonParameters);
            if (!checkUser) {
                logger.error("class : AnchorApiController --> method : countAnchor " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : countAnchor " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorApiController --> method : countAnchor " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            long count = anchorService.countAnchor(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>(16);
            data.put("count", count);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorApiController --> method : countAnchor处理异常！");
        }
        return resObj;
    }
}
