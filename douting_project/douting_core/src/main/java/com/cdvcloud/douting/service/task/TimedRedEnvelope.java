package com.cdvcloud.douting.service.task;

import java.util.HashMap;
import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.VideoRoomDao;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;

/**
 * 定时红包
 * Activity
 * @Description 
 * @author wlf
 * @data 2017-11-15 下午3:32:06
 */
public class TimedRedEnvelope implements Runnable {

	  
	
	 private CommonParameters commonParameters;  
	 private VideoRoomDao videoRoomDao;
	 private HttpCommonUtil httpCommonUtil;
	 private Map<String, Object> param;
	 public TimedRedEnvelope(CommonParameters commonParameters,Map<String, Object> param
			 ,VideoRoomDao videoRoomdao,HttpCommonUtil httpCommonUtil) {  
	        this.param = param;  
	        this.commonParameters = commonParameters;
	        this.videoRoomDao = videoRoomdao;  
	        this.httpCommonUtil = httpCommonUtil;
	 }
	private boolean flag = true;
	
	private int time = 1;
	@Override
	public void run() {
			setTime();
			while(flag){
				try {
					Thread.sleep(time*1000*60);
					task();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	@SuppressWarnings("unchecked")
	public void task(){
		    ResponseObject responseObject = new ResponseObject();
			Map<String, Object> videoMap = new HashMap<>();
			String id = String.valueOf(param.get("pid"));
			videoMap = videoRoomDao.queryVideoRoomById(id);
			if(videoMap==null||videoMap.isEmpty()){
				return;
			}
			//直播间金额
			Integer balance = (Integer) videoMap.get("money");
			//从直播间里面取状态判断是否能继续发红包
			Integer isLive = 1;
			if(videoMap.get("isLive")!=null){
				isLive = (Integer) videoMap.get("isLive");
			}
			//isLive为直播间状态  1为正在直播 0为未直播,2紧急停播，3结束直播
			if(isLive==0 || isLive==3 ){
				flag = false;
				return;
			}
			//2为紧急停播， 定时红包也停止
			if(isLive==2){
				return;
			}
			//isRedEnvelope 0为停止发红包 1为开始发红包
			Integer isRedEnvelope = 1;
			if(videoMap.get("isRedEnvelope")!=null){
				isRedEnvelope = (Integer) videoMap.get("isRedEnvelope");
			}
			if(isRedEnvelope==0){
//				flag = false;
				return;
			}
			//定时红包信息
			Map<String, Object> redEnvelope = new HashMap<>();
			if(videoMap.get("redEnvelope")!=null){
				redEnvelope = (Map<String, Object>) videoMap.get("redEnvelope");
			}
			if(redEnvelope==null||redEnvelope.isEmpty()){
				return;
			}
			
			Integer money = (Integer) redEnvelope.get("money");
			Integer partition = (Integer) redEnvelope.get("partition");
			//时间从直播间数据中获取（目前为假数据）;
			time = (Integer) redEnvelope.get("time");
			//判断余额是否满足
			if(balance>=(money*partition)){
				String ip = Configuration.getConfigValue("interactiveUrl");
			    String api = Configuration.getConfigValue("averageRedEnvelope");
			    String token = String.valueOf(param.get("accessToken"));
			    String url = ip+api+ ValidateCommonParam.getCommonParametersUrl2(commonParameters, token);
			    url = url+"&pid="+id+"&partition="+partition+"&money="+money;
			    
			    String responseString = httpCommonUtil.doPost(url, param);
			    if (!StringUtil.isEmpty(responseString)) {
			        responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
			        
			      //发包成功后修改直播间余额
			      if(responseObject.getCode()==0&&responseObject.getData()!=null&&responseObject.getData()!=""){
			    	    Integer totalAmount = 0;
		        	    if(videoMap.get("totalAmount")!=null){
		        		   totalAmount = (Integer) videoMap.get("totalAmount");
		        	    }
			        	videoMap = new HashMap<>();
			            balance = balance - (money*partition);
			            videoMap.put("totalAmount", totalAmount+(money*partition));
			            videoMap.put("money", balance);
			            videoRoomDao.updateVideoRoomById(id, videoMap);
			      }
			    } 	
		} 
	
	}
	
	
	@SuppressWarnings("unchecked")
	public void setTime(){
		Map<String, Object> videoMap = new HashMap<>();
		String id = String.valueOf(param.get("pid"));
		videoMap = videoRoomDao.queryVideoRoomById(id);
		if(videoMap==null||videoMap.isEmpty()){
			return;
		}
		//定时红包信息
		Map<String, Object> redEnvelope = new HashMap<>();
		if(videoMap.get("redEnvelope")!=null){
			redEnvelope = (Map<String, Object>) videoMap.get("redEnvelope");
		}
		if(redEnvelope.isEmpty()){
			return;
		}
		//时间从直播间数据中获取;
		time = (Integer) redEnvelope.get("time");
		System.out.println("定时红包时间："+time+"   秒："+(time*1000*60));
	}
}