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
import com.cdvcloud.douting.service.VideoRoomAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 逗看视频直播间 管理
 *
 * @author zhangyuelong
 */

@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class VideoRoomAppApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");


    @Autowired
    private VideoRoomAppService videoRoomAppService;
    /**
     * 逗看直播间首页
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
    @RequestMapping("v1/appApi/doukanHome")
    public ResponseObject doukanHome(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : VideoRoomAppApiController --> method : doukanHome " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = videoRoomAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : VideoRoomAppApiController --> method : doukanHome " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("type");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : VideoRoomAppApiController --> method : doukanHome " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String,Object> data = videoRoomAppService.doukanHome(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : VideoRoomAppApiController --> method : doukanHome处理异常！");
        }
        return resObj;
    }

    
    /**
     * 查询图文直播间
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
    @RequestMapping("v1/appApi/queryPictureTexts")
    public ResponseObject queryPictureTexts(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : VideoRoomAppApiController --> method : queryPictureTexts " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = videoRoomAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : VideoRoomAppApiController --> method : queryPictureTexts " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("pageNum");
            names.add("currentPage");
            names.add("videoRoomId");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : VideoRoomAppApiController --> method : queryPictureTexts " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String,Object> data = videoRoomAppService.queryPictureTexts(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : VideoRoomAppApiController --> method : queryPictureTexts处理异常！");
        }
        return resObj;
    }
    
    
    /**
     * 查询商品列表
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
    @RequestMapping("v1/appApi/queryGoodsShows")
    public ResponseObject queryGoodsShows(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : VideoRoomAppApiController --> method : queryGoodsShows " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
           //用户非空校验
            boolean checkFans = videoRoomAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : VideoRoomAppApiController --> method : queryGoodsShows " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
            
            
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("pageNum");
            names.add("currentPage");
            names.add("videoRoomId");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : VideoRoomAppApiController --> method : queryGoodsShows " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String, Object> data = videoRoomAppService.queryGoodsShows(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : VideoRoomAppApiController --> method : queryGoodsShows处理异常！");
        }
        return resObj;
    }
}
