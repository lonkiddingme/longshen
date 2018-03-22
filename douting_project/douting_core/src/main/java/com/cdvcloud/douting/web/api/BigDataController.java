package com.cdvcloud.douting.web.api;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.domain.Content;
import com.cdvcloud.douting.service.BigDataService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.Pages;
import com.cdvcloud.rms.util.ResponseObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从大数据获取数据
 *
 * @author SongYuanKun
 * @date 2017/10/31
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class BigDataController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");
    @Autowired
    private BigDataService bigDataService;

    @ResponseBody
    @RequestMapping("v1/bigData/queryBigDataByDocId")
    public ResponseObject queryBigDataByDocId(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BigDataController --> method : queryBigDataByDocId " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验

            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add(Content.DOCID);
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BigDataController --> method : queryBigDataByDocId " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Map<String, Object> data = bigDataService.queryBigDataByDocId(commonParameters, jsonMap);
            ValidateCommonParam.executeSuccess(resObj, data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BigDataController --> method : queryBigDataByDocId处理异常！");
        }
        return resObj;
    }

    @ResponseBody
    @RequestMapping("v1/bigData/queryBigDataByWordMarkId")
    public ResponseObject queryBigDataByWordMarkId(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BigDataController --> method : queryBigDataByWordMarkId " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验

            //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("cbid");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : BigDataController --> method : queryBigDataByWordMarkId " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            Pages data = bigDataService.queryBigDataByWordMarkId(commonParameters, jsonMap);
            ValidateCommonParam.executeSuccess(resObj, data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : BigDataController --> method : queryBigDataByWordMarkId处理异常！");
        }
        return resObj;
    }
}
