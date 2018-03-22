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
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.service.AnchorAppService;
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
public class AnchorAppController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");
    @Autowired
    private AnchorAppService anchorAppService;


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
    @RequestMapping("v1/appApi/queryAnchorByApp")
    public ResponseObject queryAnchorByApp(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
        ResponseObject resObj = new ResponseObject();
        logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
        try {
            //数据解析
            Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
            Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorAppController --> method : queryAnchorByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            boolean checkFans = anchorAppService.checkFans(commonParameters);
            if (!checkFans) {
                logger.error("class : AnchorApiController --> method : queryAnchorByApp " + GeneralStatus.user_error.enDetail);
                ValidateCommonParam.userError(resObj);
                return resObj;
            }
            
          //参数非空校验
            List<String> names = new ArrayList<String>();
            names.add("pageNum");
            names.add("currentPage");
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : AnchorAppController --> method : queryAnchorByApp " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //排序呢
            Map<String, Object> sortParam = new HashMap<String, Object>(16);
            sortParam.put(QueryBasicObject.WEIGTH, -1);
            jsonMap.put("sort", sortParam);
            //状态筛选
            Map<String, Object> conditionParam = new HashMap<String, Object>(16);
            conditionParam.put(QueryBasicObject.STATUS, 1);
            conditionParam.put(BasicObject.COMPANYID,companyId);
            jsonMap.put("conditionParam", conditionParam);
            
            Pages pages = anchorAppService.queryAnchorByApp(commonParameters, jsonMap);
            resObj.setCode(GeneralStatus.query_success.status);
            resObj.setMessage(GeneralStatus.query_success.detail);
            resObj.setData(pages);
        } catch (Exception e) {
        	e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : AnchorAppController --> method : queryAnchorByApp处理异常！");
        }
        return resObj;
    }



}
