package com.cdvcloud.douting.web.api;

import java.util.HashMap;
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
import com.cdvcloud.douting.service.VideoRoomService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;

/**
 * 直播间直播通道 --  回调管理
 *
 * @author zhangyuelong
 */

@Controller
@RequestMapping(value = "/{companyId}/{appCode}/{userId}/{serviceCode}/")
public class CallBackApiController {
    private static final Logger logger = Logger.getLogger("ValidateCommonParam");


    @Autowired
    private VideoRoomService videoRoomService;

    /**
     * 回调
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("v1/liveChannel/callback")
    public ResponseObject callback(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
      	 ResponseObject resObj = new ResponseObject();
         logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
         try {
             //数据解析
             Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
			if(!StringUtil.isEmpty(jsonMap)){
				if(String.valueOf(jsonMap.get("code")).equals(Constants.SZERO)){
					Map<String,Object> mapData=(Map<String, Object>) jsonMap.get("data");
					String type=String.valueOf(mapData.get("type"));
					if("bind".equals(type)){
						System.out.println("绑定回调开始");
						videoRoomService.bind(mapData);
					}else{
						System.out.println("解绑回调开始");
						videoRoomService.unbind(mapData);
					}
				}
			}
			ValidateCommonParam.executeSuccess(resObj, "");
		} catch (Exception e) {
			e.printStackTrace();
			ValidateCommonParam.innerError(resObj);
            logger.error("class : ColumnApiController --> method : createColumn处理异常！");
		}
    	return resObj;
    }
    
    /**
     * 收录回看回调
     *
     * @param session          session信息
     * @param commonParameters commonParameters
     * @param strJson          请求参数
     * @return 响应对象
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("v1/liveChannel/reviewBack")
    public ResponseObject reviewBack(@PathVariable String companyId, @PathVariable String appCode, @PathVariable String userId, @PathVariable String serviceCode, @Validated CommonParameters commonParameters, @RequestBody String strJson) {
      	 ResponseObject resObj = new ResponseObject();
         logger.info(ValidateCommonParam.getRequestInfo(companyId, appCode, userId, serviceCode, strJson));
         try {
             //数据解析
             Map<String, Object> jsonMap = JSONUtils.json2map(strJson);
             if(!StringUtil.isEmpty(jsonMap)){
     			String code=String.valueOf(jsonMap.get("code"));
     			String type=String.valueOf(jsonMap.get("type"));
     			System.out.println("回调开始1:reviewBack");
     			System.out.println(jsonMap.toString());
     			if("0".equals(code)&&"2".equals(type)){
     				System.out.println("回调开始2:reviewBack");
					Map<String,Object> mapData=(Map<String, Object>) jsonMap.get("data");
					Map<String,Object> map= new HashMap<String, Object>();
					String apiUrl=String.valueOf(mapData.get("apiUrl"));
					String taskId=String.valueOf(mapData.get("taskId"));
					map.put("apiUrl", apiUrl);
					map.put("taskId", taskId);
					if(mapData.get("taskId")!=null){
						videoRoomService.reviewBack(map);
					}
				}
			}
			ValidateCommonParam.executeSuccess(resObj, "");
		} catch (Exception e) {
			e.printStackTrace();
			ValidateCommonParam.innerError(resObj);
            logger.error("class : ColumnApiController --> method : createColumn处理异常！");
		}
    	return resObj;
    }
}
