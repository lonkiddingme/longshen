package com.cdvcloud.douting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.GeneralStatus;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.RedisUtilDao;
import com.cdvcloud.douting.dao.VideoRoomDao;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.domain.VideoRoom;
import com.cdvcloud.douting.service.LiveAppService;
import com.cdvcloud.douting.service.LiveChannelService;
import com.cdvcloud.douting.service.NumCountService;
import com.cdvcloud.douting.service.RedEnvelopeAppService;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;
/**
 * APP直播管理
 *
 */
@Service
public class LiveAppServiceImpl extends BaseServiceImpl implements LiveAppService {
	
	@Autowired
	private LiveChannelService liveChannelService;

	@Autowired
	private VideoRoomDao videoRoomDao;
	@Autowired
	private  RedisUtilDao redisUtilDao;
	@Autowired
    private HttpCommonUtil httpCommonUtil;
	
	@Autowired
    private RedEnvelopeAppService redEnvelopeAppService; 
	
	
//	@Autowired
//    private TaskProgressAppService taskProgressAppService; 
	@Autowired
	private NumCountService numCountService;
	/**
	 * 开启直播
	 * isLive 为直播状态 0 为未直播 1为正在直播
	 */
	@Override
	public Map<String,	Object> startLive(CommonParameters commonParameters, Map<String, Object> param) {
		String id = String.valueOf(param.get("virtualId"));
		Map<String,	Object> query = new HashMap<String,	Object>();
		query.put("virtualId", id);
		
		Map<String,	Object> videoRoomMap = videoRoomDao.queryVideoRoom(query);
		if(videoRoomMap==null){
			return null;
		}
		//判断直播间状态是否有人在直播
		Integer isLive = 0;
		if(videoRoomMap.get("isLive")!= null){
			isLive = (Integer) videoRoomMap.get("isLive");
		}
		if(isLive==1 || isLive==2){
			return null;
		}
		Map<String, Object> data = new HashMap<>();
		data.put("pushUrl", videoRoomMap.get("vPushAddress"));
		data.put("id", videoRoomMap.get("_id"));
		data.put("name", videoRoomMap.get("name"));
		data.put("thumbnailUrl", videoRoomMap.get("thumbnailUrl"));
		return data;
	}
	/**
	 * 关闭直播
	 * isLive为直播间状态  1为正在直播 0为未直播,2紧急停播，3结束直播
	 */
	@SuppressWarnings("unused")
	@Override
	public Boolean stopLive(CommonParameters commonParameters, Map<String, Object> param) {
		String id = String.valueOf(param.get("virtualId"));
		Map<String,	Object> query = new HashMap<String,	Object>();
		query.put("virtualId", id);
		
		Map<String,	Object> videoRoomMap = videoRoomDao.queryVideoRoom(query);
		if(videoRoomMap==null){
			return null;
		}
		ResponseObject responseObject = null;
		//停止收录
		String includeId = "";
		if(videoRoomMap.get("includeId")!=null){
				includeId = String.valueOf(videoRoomMap.get("includeId"));
		}
		if(includeId!=""){
				param.put("taskId", includeId);
				responseObject = liveChannelService.stopInclude(commonParameters, param);
		}	
		
		//删除通道并释放资源
		String virtualId = null;
		if(videoRoomMap.get("virtualId")!=null){
			virtualId = String.valueOf(videoRoomMap.get("virtualId"));
		}
		if(virtualId!=null){
				param.put("virtualId", virtualId);
				liveChannelService.deleteLiveChannel(commonParameters, param);
		}
		//更新直播间信息 修改为以有人直播
		id = String.valueOf(videoRoomMap.get("_id"));
		Map<String, Object> update = new HashMap<>();
		update.put("isLive", 3);
		update.put("isRedEnvelope", 0);
		update.put("anchorId", "");
		update.put("virtualId", "");
		long temp = videoRoomDao.updateVideoRoomById(id, update);
		if(temp<=0){
			return false;
		}
		return true;
	}
	/**
	 * 查询直播间直播地址（结束直播是查询回看地址）
	 * vPlayHlsAddress APP端直播地址
	 * apiurl 回看地址
	 */
	@Override
	public Map<String, Object> queryLiveRoomById(CommonParameters commonParameters, Map<String, Object> param) {
		String id = String.valueOf(param.get("videoRoomId"));
		Map<String,	Object> data = new HashMap<String,	Object>();
		
		
		Map<String,	Object> videoRoomMap = videoRoomDao.queryVideoRoomById(id);
		Integer isLive = 0;
		if(videoRoomMap.get("isLive")!=null){
			isLive = (Integer) videoRoomMap.get("isLive");
		}
		//isLive = 1时为正在直播。0时为结束直播,2为紧急停播
		if(isLive==1 || isLive==2){
			data.put("url", videoRoomMap.get("vPlayRtmpAddress")==null?"":videoRoomMap.get("vPlayRtmpAddress"));
		}
		if(isLive==3 || isLive==0 ){
			data.put("url", videoRoomMap.get("liveReviewUrl")==null?"":videoRoomMap.get("liveReviewUrl"));
		}
		data.put("isLive",isLive);
		data.put("type", videoRoomMap.get("type")==null?"":videoRoomMap.get("type"));
		Map<String, String> numCount = numCountService.queryNumCountByObject("user", commonParameters, String.valueOf(videoRoomMap.get(VideoRoom.ID)));
		data.putAll(numCount);
		
		//添加直播间观看粉丝信息列表
		String isFans = null;
		if(param.get("isFans")!=null){
			isFans = String.valueOf(param.get("isFans"));
		}
		if(isFans!=null&&isFans.equals("yes")){
			Map<String, Object> fansMap = new HashMap<String, Object>();
			fansMap.put("fansId", param.get("fansId"));
			fansMap.put("fansName", param.get("fansName"));
			fansMap.put("thumbnailUrl", param.get("thumbnailUrl"));
			redisUtilDao.createLiveFansMsgList(id, fansMap);
		}
		return data;
	}

	//推流成功后开始收录
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> startIncludeByApp(CommonParameters commonParameters, Map<String, Object> param) {
		Map<String,	Object> data = new HashMap<String,	Object>();
		String id = String.valueOf(param.get("virtualId"));
		Map<String,	Object> query = new HashMap<String,	Object>();
		query.put("virtualId", id);
		
		Map<String,	Object> videoRoomMap = videoRoomDao.queryVideoRoom(query);
		Integer isLive = 0;
		if(videoRoomMap.get("isLive")!= null){
			isLive = (Integer) videoRoomMap.get("isLive");
		}
		String includeId = "";
    	if(videoRoomMap.get("includeId")!=null){
    		includeId = String.valueOf(videoRoomMap.get("includeId"));
    	}
    	long temp = 0;
    	Map<String, Object> update = new HashMap<>();
		ResponseObject responseObject = null;
    	//没有收录任务开启收录，结束直播和重置时 会停止收录，其他时候收录任务正常不需重新开启收录
		//收录地址   收录地址 目前http格式的地址 rtmp可能有音话不同步问题
    	if(includeId == ""){
    		String hlsUrl = null;
    		if(videoRoomMap.get("vPlayHlsAddress")!=null){
    			hlsUrl = String.valueOf(videoRoomMap.get("vPlayHlsAddress"));
    		}
    		if(hlsUrl!=null){
    			param = new HashMap<>();
    			param.put("hlsUrl", hlsUrl);
    			responseObject = liveChannelService.startInclude(commonParameters, param);
    		}
    		if(responseObject!=null&&responseObject.getCode()==0){
    			data = (Map<String, Object>) responseObject.getData();
    			//更新直播间收录任务ID
    			includeId = String.valueOf(data.get("taskId"));
    			id = String.valueOf(videoRoomMap.get("_id"));
    			update = new HashMap<>();
    			update.put("includeId", includeId);
    			update.put("liveReviewUrl", "");
    			temp = videoRoomDao.updateVideoRoomById(id, update);
    			if(temp<=0){
    				return null;
    			}
    		}
    		
    	}
		//1或者2 说明后台没有关闭直播
		if(isLive==1 || isLive==2){
			return new HashMap<String,	Object>();
		}
		
		//更新直播间信息
		id = String.valueOf(videoRoomMap.get("_id"));
		String fansId = commonParameters.getUserId();
		update = new HashMap<>();
		update.put("isLive", 1);
		update.put("anchorId", fansId);
		temp = videoRoomDao.updateVideoRoomById(id, update);
		if(temp<=0){
			return null;
		}		
		
		id = String.valueOf(videoRoomMap.get("_id"));
		//启动定时红包
		param = new HashMap<String, Object>();
		param.put("accessToken", "accessToken");
		param.put("timeStamp",  System.currentTimeMillis());
		param.put("pid", id);
		redEnvelopeAppService.timedRedEnvelope(commonParameters, param);
		return data;
	}
	/**
     * 创建评论
     *
     */
	@Override
	public ResponseObject createLiveComment(CommonParameters commonParameters, Map<String, Object> param) {
		ResponseObject responseObject = new ResponseObject();
        String ip = Configuration.getConfigValue(QueryBasicObject.INTERACTIVEURL);
        String api = Configuration.getConfigValue("createCommentEfficient");
        String url = ip + ValidateCommonParam.getCommonParametersUrl(commonParameters, "core") + api;
        
        String videoRoomId = String.valueOf(param.get("sid"));
        String fansId = String.valueOf(param.get("doCommentId"));
        List<String> result = redisUtilDao.getProhibitFansList(videoRoomId);
        boolean flag = false;
		for (String string : result) {
			if(string.indexOf(fansId)>=0){
					flag = true;
					break;
			}
		}
		if(flag){
			Map<String, Object> msg = new HashMap<>();
			msg.put("msg", "粉丝已禁言");
			responseObject.setCode(GeneralStatus.failure.status);
			responseObject.setMessage(GeneralStatus.failure.detail);
			responseObject.setData(msg);
			return responseObject;
		}
		 //补全 状态信息
        param.put(QueryBasicObject.STATUS, 1);
        param.put(QueryBasicObject.STATUS_ZH, "已审核");
        param.put(QueryBasicObject.ISCACHE, "yes");
		String responseString = httpCommonUtil.doPost(url, param);
		  if (!StringUtil.isEmpty(responseString)) {
	           responseObject = JSONUtils.toObject(responseString, ResponseObject.class);
	          //统计计数
	           if(param.get("pid")!=null&&param.get("sid")!=null){
	        	   String sid = String.valueOf(param.get("sid"));
	        	   numCountService.doNumCount("fans", commonParameters, "commentNum", sid, 1, "comment");
//	          
//	        	 //创建评论任务详情
//	           	param = new HashMap<String, Object>();
//	           	Map<String, Object> data = new HashMap<String,Object>();
//	           	data.put("firstComment", "newbieTask");
//	           	data.put("dailyComment", "dailyTask");
//	            param.put("conditionParam", data);
//	            param.put("others",1);
//	           	taskProgressAppService.updateTaskProgress(commonParameters, param);
	           }
	        } else {
	            ValidateCommonParam.innerError(responseObject);
	        }
	        return responseObject;
	}
	/**
     * 网红直播间轮询接口
     *
     */
	public Map<String, Object> pollingLiveData(CommonParameters commonParameters, Map<String, Object> param) {
		Map<String, Object> data = new HashMap<String, Object>();
		String pid = String.valueOf(param.get("id"));
		List<Map<String, Object>> goodsList = redisUtilDao.queryLiveGoodsList(pid);
		Map<String, Object>prohibitFansList = redisUtilDao.queryProhibitFansListForApp(pid,param);
		
		String notice = redisUtilDao.getNotice(pid);
		List<String> redEnvelopeList = redisUtilDao.getRedEnvelope(pid);
		Map<String, Object> deleteCommentIdsList = redisUtilDao.getDeleteCommentIds(pid,param);
		Map<String, Object> commentList = redisUtilDao.queryCommentEfficient(pid,param);
		List<Map<String, Object>> fansList = redisUtilDao.queryLiveFansMsgList(pid);
		Map<String, Object> rewardMsgList = redisUtilDao.queryRewardMsgByVideoRoomId(pid,param);
		
		
		
		Map<String, Object> map = videoRoomDao.queryVideoRoomById(pid);
		//最新观看粉丝
		data.put("fansList", fansList);
		//商品列表
		data.put("goodsList", goodsList);
		//禁言粉丝列表
		data.put("prohibitFansList", prohibitFansList);
		//公告
		data.put("notice", notice);
		//红包列表
		data.put("redEnvelopeList", redEnvelopeList);
		//打赏列表
		data.put("rewardMsgList", rewardMsgList);
		
		data.put("commentList", commentList);
		data.put("deleteCommentIdsList", deleteCommentIdsList);
		//isLive = 1时为正在直播。0时为结束直播,2为紧急停播
		data.put("isLive", map.get("isLive"));
		//直播间余额
		data.put("money", map.get("money"));
		return data;
	}
}
