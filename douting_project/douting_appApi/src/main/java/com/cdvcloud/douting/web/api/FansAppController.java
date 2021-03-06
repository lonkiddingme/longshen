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
import com.cdvcloud.douting.service.FansAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 粉丝管理
 *
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class FansAppController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");

    @Autowired
    private FansAppService fansService;

    /**
     * 粉丝注册接口
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/createFans")
    public ResponseObject createFans(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : FansAppController --> method : createFans " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = fansService.checkFans(commonParameters);
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("phone");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : FansTaskProgressAppController --> method : updateTaskProgressByFansId " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            if (!checkFans) {
                logger.error("class : FansAppController --> method : createFans " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = fansService.updateTaskProgressForNewFans(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : FansAppController --> method : createFans处理异常！");
        }
        return resObj;
    }
    
    

    /**
     * 完善粉丝资料任务
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/perfectInformation")
    public ResponseObject perfectInformation(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : FansAppController --> method : perfectInformation " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = fansService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : FansAppController --> method : perfectInformation " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = fansService.perfectInformationForNewFans(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : FansAppController --> method : perfectInformation处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 粉丝分享任务
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/fansShareTask")
    public ResponseObject fansShareTask(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : FansAppController --> method : fansShareTask " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = fansService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : FansAppController --> method : fansShareTask " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = fansService.fansShareTask(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : FansAppController --> method : fansShareTask处理异常！");
        }
        return resObj;
    }
    
    /**
     * 更新粉丝收听时间
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/updateFansListenTime")
    public ResponseObject updateFansListenTime(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : FansAppController --> method : updateFansListenTime " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = fansService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : FansAppController --> method : updateFansListenTime " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = fansService.updateFansListenTime(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : FansAppController --> method : updateFansListenTime处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 日常登录
     * @param companyId
     * @param appCode
     * @param userId
     * @param serviceCode
     * @param commonParameters
     * @param strJson
     * @return
     */
    @ResponseBody
    @RequestMapping("v1/appApi/updateDailyLogin")
    public ResponseObject updateDailyLogin(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : FansAppController --> method : updateDailyLogin " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = fansService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : FansAppController --> method : updateDailyLogin " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            resObj = fansService.dailyLogin(commonParameters, jsonMap);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : FansAppController --> method : updateDailyLogin处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 根据粉丝ID分页查询粉丝的消费情况
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/appApi/queryMoneyDetail4Page")
    public ResponseObject queryMoneyDetail4Page(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
    	 ResponseObject resObj = new ResponseObject();
         logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
         try {
             //数据解析
             Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
             Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : MoneyDetailController --> method : queryMoneyDetail4Page " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = fansService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : FansAppController --> method : queryMoneyDetail4Page " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("pageNum");
            names.add("currentPage");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : MoneyDetailController --> method : queryMoneyDetail4Page " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            resObj = fansService.queryMoneyDetail4Page(commonParameters, jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : MoneyDetailController --> method : queryMoneyDetail4Page处理异常！");
        }
        return resObj;
    }
    
    
}
