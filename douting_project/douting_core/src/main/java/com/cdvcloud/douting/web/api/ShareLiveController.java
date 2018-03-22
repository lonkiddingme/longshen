package com.cdvcloud.douting.web.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.GeneralStatus;
import com.cdvcloud.douting.service.ShareLiveService;
import com.cdvcloud.douting.service.VideoRoomService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 分享页接口
 *
 * @author SongYuanKun
 * @date 2017/11/17
 */

@Controller
@RequestMapping(value = "/shareLive/")
public class ShareLiveController {
    @Autowired
    private VideoRoomService videoRoomService;
    
    @Autowired
    private ShareLiveService shareLiveService;
    
	@RequestMapping(value = "{id}")
    public ModelAndView shareLive(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("hello");
        String apkDownLoadUrl = Configuration.getConfigValue("apkDownLoadUrl");
        String ipaDownLoadUrl = Configuration.getConfigValue("ipaDownLoadUrl");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        CommonParameters commonParameters =new CommonParameters();
        Map<String, Object> map =   videoRoomService.queryVideoRoomById(commonParameters, param);
        mav.addObject("lookNum", map.get("lookNum")==null?0:map.get("lookNum"));
        mav.addObject("likeNum", map.get("likeNum")==null?0:map.get("likeNum"));
        mav.addObject("name", map.get("name"));
        Integer  isLive = 0;
        if(map.get("isLive")!=null){
        	isLive = (Integer) map.get("isLive");
        }
        if(isLive==3){
        	mav.addObject("url", map.get("liveReviewUrl"));
        }else{
        	mav.addObject("url", map.get("vPlayCdnAddress"));
        }
//        mav.addObject("url", map.get("vPlayHlsAddress"));
        mav.addObject("apkDownLoadUrl", apkDownLoadUrl);
        mav.addObject("ipaDownLoadUrl", ipaDownLoadUrl);
        mav.addObject("id", id);
        mav.setViewName("share/share_live");
        return mav;
    }
	
	@RequestMapping(value = "queryPollingData")
	@ResponseBody
    public ResponseObject queryContent(@RequestBody String strJson) {
		ResponseObject resObj = new ResponseObject();
		try {
			Map<String, Object> dataMap = JSONUtils.json2map(strJson);
			if(dataMap!=null && !dataMap.isEmpty()){
				CommonParameters commonParameters =new CommonParameters();
				Map<String, Object> data = shareLiveService.pollingLiveData(commonParameters, dataMap);
				
				resObj.setCode(GeneralStatus.success.status);
				resObj.setMessage(GeneralStatus.success.detail);
				resObj.setData(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return resObj;
    }
}
