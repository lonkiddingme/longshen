package com.cdvcloud.douting.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.domain.Global;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.service.NumCountAppService;
import com.cdvcloud.douting.service.NumCountService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;
/**
 * 统计计数APP接口
 *
 * @author SongYuanKun
 * @date 2017/11/10
 */
@Service
public class NumCountAppServiceImpl extends BaseServiceImpl implements NumCountAppService {

	@Autowired
	private NumCountService numCountService;

	@Autowired
    private HttpCommonUtil httpCommonUtil;
	
	/**
	 * 添加计数
	 */
	@Override
	public long addNumCount(CommonParameters commonParameters, Map<String, Object> param) {
		
		String type = String.valueOf(param.get(QueryBasicObject.TYPE));
		String beCountId = String.valueOf(param.get(QueryBasicObject.BECOUNTID));
		String countType = String.valueOf(param.get(QueryBasicObject.COUNTTYPE));
		Integer num = 0;
		if(param.get(QueryBasicObject.NUM)!=null){
			num= (Integer) param.get(QueryBasicObject.NUM);
		}
		
		return numCountService.doNumCount(QueryBasicObject.FANS, commonParameters, countType, beCountId, num, type);
	}
	/**
	 * 查询计数
	 */
	@Override
	public long queryNumCount(CommonParameters commonParameters, Map<String, Object> param) {
		String beCountId = String.valueOf(param.get(QueryBasicObject.BECOUNTID));
		String countType = String.valueOf(param.get(QueryBasicObject.COUNTTYPE));
		
		
		return numCountService.queryNumCount(QueryBasicObject.FANS, commonParameters, countType, beCountId);
	}
	/**
	 * 查看某一对象全部类型计数
	 */
	@Override
	public Map<String, String> queryNumCountByObject(CommonParameters commonParameters, Map<String, Object> param) {
		String beCountId = String.valueOf(param.get(QueryBasicObject.BECOUNTID));
		return numCountService.queryNumCountByObject(QueryBasicObject.FANS, commonParameters, beCountId);
		
	}
	/**
	 * 直播间PV数（粉丝数）
	 */
	@Override
	public ResponseObject addLivePv(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
		String ip = Configuration.getConfigValue(Global.INTERACTIVE_URL);
		String api = ip+"api/statistic/v1/addPv?userId=" + commonParameters.getUserId() +"&companyId="+commonParameters.getCompanyId() + "&appCode=" + commonParameters.getAppCode() +
				"&type=videoRoom&beCountName=livePvNum&beCountId="+param.get("beCountId")+"&fingerprint="+
				param.get("beCountId")+"_"+commonParameters.getUserId()+"&token="+param.get("accessToken");
		String doPost = httpCommonUtil.doGet(api, param);
		if (!StringUtil.isEmpty(doPost)) {
	            responseObject = JSONUtils.toObject(doPost, ResponseObject.class);
	    } else {
	         ValidateCommonParam.innerError(responseObject);
	   }
	   return responseObject;
	}
}
