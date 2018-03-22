package com.cdvcloud.douting.web.api;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.GeneralStatus;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.service.PublicService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 公共接口能力
 *
 * @author SongYuanKun
 * @date 2017/11/4
 */
@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")

public class PublicApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");
    @Autowired
    private PublicService publicService;

    @ResponseBody
    @RequestMapping("v1/public/updateWeight")
    public ResponseObject updateWeight(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>(16);
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : PublicApiController --> method : updateWeight " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //参数非空校验
            List<String> names = Arrays.asList("document", "key", "value", "ids");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : PublicApiController --> method : updateWeight " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            long influenceLine = publicService.updateWeight(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>(16);
            data.put("influenceLine", influenceLine);
            resObj.setCode(GeneralStatus.update_success.status);
            resObj.setMessage(GeneralStatus.update_success.detail);
            resObj.setData(data);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : PublicApiController --> method : updateWeight处理异常！");
        }
        return resObj;
    }
}
