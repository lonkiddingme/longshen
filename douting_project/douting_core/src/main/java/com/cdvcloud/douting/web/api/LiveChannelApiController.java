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
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.service.LiveChannelService;
import com.cdvcloud.douting.service.RedEnvelopeService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 直播间直播通道管理
 *
 * @author zhangyuelong
 */

@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class LiveChannelApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");


    @Autowired
    private LiveChannelService liveChannelService;

    
    @Autowired
    private RedEnvelopeService redEnvelopeService;
    /**
     * 创建 直播间通道
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/liveChannel/createLiveChannel")
    public ResponseObject createLiveChannel(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
    	 ResponseObject resObj = new ResponseObject();
         logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
         try {
             logger.info("class : LiveChannelApiController --> method : createLiveChannel 接收到参数" + strJson);
             //数据解析
             Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
             Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : createLiveChannel " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验

            //参数非空验证
            List<String> names = new ArrayList<String>();
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : createLiveChannel " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            resObj = liveChannelService.createLiveChannel(commonParameters, jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : LiveChannelApiController --> method : createLiveChannel处理异常！");
        }
        return resObj;
    }


    /**
     * 删除直播间直播通道
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/liveChannel/deleteLiveChannel")
    public ResponseObject deleteLiveChannel(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
   	 ResponseObject resObj = new ResponseObject();
     logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
     try {
         logger.info("class : LiveChannelApiController --> method : deleteLiveChannel 接收到参数" + strJson);
         //数据解析
         Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
         Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : deleteLiveChannel " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            //参数非空验证
            List<String> names = new ArrayList<String>();
            validate = ValidateCommonParam.validateValue(names, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : deleteLiveChannel " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }

            resObj = liveChannelService.deleteLiveChannel(commonParameters, jsonMap);

        } catch (Exception e) {
            e.printStackTrace();

            ValidateCommonParam.innerError(resObj);
            logger.error("class : LiveChannelApiController --> method : deleteLiveChannel处理异常！");
        }

        return resObj;
    }

    /**
     * 开始拉流
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("v1/liveChannel/start4Json")
    public ResponseObject start4Json(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
   	 ResponseObject resObj = new ResponseObject();
     logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
     try {
         logger.info("class : LiveChannelApiController --> method : start4Json 接收到参数" + strJson);
         //数据解析
         Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
         Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : start4Json " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验
            //参数非空验证
            resObj = liveChannelService.start4Json(commonParameters, jsonMap);
            Map<String, Object> data = new HashMap<String, Object>();
            if(resObj!=null&&resObj.getCode()==0&&resObj.getData()!=null&&resObj.getData()!=""){
            	data = (Map<String, Object>) resObj.getData();
            }else{
            	return resObj;
            }
            //开始收录
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("hlsUrl",jsonMap.get("shouluUrl"));
            ResponseObject responseObject = liveChannelService.startInclude(commonParameters, param);
            
            if(responseObject!=null&&responseObject.getCode()==0){
            	param = (Map<String, Object>) responseObject.getData();
            }
            if(!data.isEmpty()){
            	//更新直播间收录任务ID
            	String includeId = String.valueOf(param.get("taskId"));
            	String liveReviewUrl = String.valueOf(param.get("playCdnUrl"));
            	data.put("includeId", includeId);
            	data.put("liveReviewUrl", liveReviewUrl);
            	//启动定时红包
            	redEnvelopeService.timedRedEnvelope(commonParameters, jsonMap);
            }
            
            
            resObj.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            ValidateCommonParam.innerError(resObj);
            logger.error("class : LiveChannelApiController --> method : start4Json处理异常！");
        }
        return resObj;
    }

    /**
     * 停止拉流
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/liveChannel/stop4Json")
    public ResponseObject stop4Json(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
   	 ResponseObject resObj = new ResponseObject();
     logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
     try {
         logger.info("class : LiveChannelApiController --> method : stop4Json 接收到参数" + strJson);
         //数据解析
         Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
         Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : stop4Json " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验

            //参数非空验证

            resObj = liveChannelService.stop4Json(commonParameters, jsonMap);

        } catch (Exception e) {
            e.printStackTrace();

            ValidateCommonParam.innerError(resObj);
            logger.error("class : LiveChannelApiController --> method : stop4Json处理异常！");
        }

        return resObj;
    }
    /**
     * 紧急停止APP播放
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/liveChannel/closeLiveChannel")
    public ResponseObject closeLiveChannel(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
   	 ResponseObject resObj = new ResponseObject();
     logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
     try {
         logger.info("class : LiveChannelApiController --> method : closeLiveChannel 接收到参数" + strJson);
         //数据解析
         Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
         Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : closeLiveChannel " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验

            resObj = liveChannelService.closeLiveChannel(commonParameters, jsonMap);

        } catch (Exception e) {
            e.printStackTrace();

            ValidateCommonParam.innerError(resObj);
            logger.error("class : LiveChannelApiController --> method : closeLiveChannel处理异常！");
        }

        return resObj;
    }
    
    /**
     * 重新开启APP直播
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/liveChannel/newchannelkaiqi")
    public ResponseObject newchannelkaiqi(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
   	 ResponseObject resObj = new ResponseObject();
     logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
     try {
         logger.info("class : LiveChannelApiController --> method : newchannelkaiqi 接收到参数" + strJson);
         //数据解析
         Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
         Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : newchannelkaiqi " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验

            resObj = liveChannelService.newchannelkaiqi(commonParameters, jsonMap);

        } catch (Exception e) {
            e.printStackTrace();

            ValidateCommonParam.innerError(resObj);
            logger.error("class : LiveChannelApiController --> method : newchannelkaiqi处理异常！");
        }

        return resObj;
    }
    
    
    /**
     * 开始收录
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/liveChannel/startInclude")
    public ResponseObject startInclude(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
   	 ResponseObject resObj = new ResponseObject();
     logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
     try {
         logger.info("class : LiveChannelApiController --> method : startInclude 接收到参数" + strJson);
         //数据解析
         Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
         Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : startInclude " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验

            resObj = liveChannelService.startInclude(commonParameters, jsonMap);

        } catch (Exception e) {
            e.printStackTrace();

            ValidateCommonParam.innerError(resObj);
            logger.error("class : LiveChannelApiController --> method : startInclude处理异常！");
        }

        return resObj;
    }
    
    /**
     * 停止收录
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @ResponseBody
    @RequestMapping("v1/liveChannel/stopInclude")
    public ResponseObject stopInclude(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
   	 ResponseObject resObj = new ResponseObject();
     logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
     try {
         logger.info("class : LiveChannelApiController --> method : stopInclude 接收到参数" + strJson);
         //数据解析
         Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
         Map<String, Object> errorMap = new HashMap<String, Object>();
            //参数校验
            boolean validate = ValidateCommonParam.validateCommonParam(commonParameters, jsonMap, errorMap);
            if (!validate) {
                logger.error("class : LiveChannelApiController --> method : stopInclude " + errorMap.get(Constants.MESSAGE));
                ValidateCommonParam.parameterError(resObj, errorMap.get(Constants.MESSAGE));
                return resObj;
            }
            //用户非空校验

            resObj = liveChannelService.stopInclude(commonParameters, jsonMap);

        } catch (Exception e) {
            e.printStackTrace();

            ValidateCommonParam.innerError(resObj);
            logger.error("class : LiveChannelApiController --> method : stopInclude处理异常！");
        }

        return resObj;
    }
}
