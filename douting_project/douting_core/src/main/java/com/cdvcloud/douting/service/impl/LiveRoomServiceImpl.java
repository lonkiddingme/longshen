package com.cdvcloud.douting.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.GoodsShowDao;
import com.cdvcloud.douting.dao.RedisUtilDao;
import com.cdvcloud.douting.dao.VideoRoomDao;
import com.cdvcloud.douting.domain.GoodsShow;
import com.cdvcloud.douting.domain.VideoRoom;
import com.cdvcloud.douting.service.LiveChannelService;
import com.cdvcloud.douting.service.LiveRoomService;
import com.cdvcloud.douting.service.NumCountService;
import com.cdvcloud.rms.util.ResponseObject;

/**
 * 调用其他服务
 *
 * @author SongYuanKun
 * @date 2017/11/9
 */
@Service
public class LiveRoomServiceImpl extends BaseServiceImpl implements LiveRoomService {
	
	@Autowired
	private VideoRoomDao videoRoomDao;
	@Autowired
    private NumCountService numCountService;
	@Autowired
	private LiveChannelService liveChannelService;
	@Autowired
	private RedisUtilDao redisUtilDao;
	@Autowired
	private GoodsShowDao goodsShowDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public String createLiveRoom(CommonParameters commonParameters, Map<String, Object> param) {
		Map<String, Object> videoRoomMap = new HashMap<String, Object>();

        List<String> list = Arrays.asList(VideoRoom.REMARK,VideoRoom.NAME, VideoRoom.STATUS,VideoRoom.H5URL, VideoRoom.THUMBNAILURL, VideoRoom.OTHERSH5URL, VideoRoom.STARTTIME, VideoRoom.ENDTIME,
        		 VideoRoom.TYPE,VideoRoom.OPTIONS, VideoRoom.USERTYPE, VideoRoom.WEIGHT);
         /*更新信息*/
        videoRoomMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*创建信息*/
        videoRoomMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));

        /* 基础信息 */
        if(param.get(VideoRoom.OTHERS) instanceof Map){
    		Map<String, Object> map = (Map<String, Object>) param.get(VideoRoom.OTHERS);
    		videoRoomMap.putAll(map);
    	}
        videoRoomMap.put(VideoRoom.LOOKNUM, Constants.ZERO);
        videoRoomMap.put(VideoRoom.COMMENTNUM, Constants.ZERO);
        videoRoomMap.put(VideoRoom.STATUS, Constants.ZERO);
        videoRoomMap.put(VideoRoom.STATUS_ZH, Constants.IS_UNLINE);
        String id = null;
        ResponseObject responseObject = liveChannelService.createLiveChannel(commonParameters, param);
        Map<String, Object> liveChannel = new HashMap<String, Object>();
        if( responseObject.getData()!=null && responseObject.getCode()==0){
        	liveChannel = (Map<String, Object>) responseObject.getData();
        	String vPushAddress = String.valueOf(liveChannel.get("vPushAddress"));
        	vPushAddress = vPushAddress.replace("onlive.aircdvcloud.com", "192.168.0.103");
        	
        	String vPlayRtmpAddress = String.valueOf(liveChannel.get("vPlayRtmpAddress"));
        	vPlayRtmpAddress = vPlayRtmpAddress.replace("outlive.aircdvcloud.com", "192.168.0.104");
        	
        	videoRoomMap.put("virtualId", liveChannel.get("virtualId"));
//        	videoRoomMap.put("vPushAddress", liveChannel.get("vPushAddress"));
        	videoRoomMap.put("vPushAddress", vPushAddress);
//        	videoRoomMap.put("vPlayRtmpAddress", liveChannel.get("vPlayRtmpAddress"));
        	
        	videoRoomMap.put("vPlayRtmpAddress", vPlayRtmpAddress);
        	videoRoomMap.put("vPlayHlsAddress", liveChannel.get("vPlayHlsAddress"));
//        	videoRoomMap.put("vPlayCdnAddress", liveChannel.get("vPlayCdnAddress"));
        	
        	
        	//线上掩饰环境
//        	String vPushAddress = String.valueOf(liveChannel.get("vPushAddress"));
//        	StringBuilder sb = new StringBuilder(vPushAddress);
//            sb.insert(7, "push.onairsaas.com");
//            vPushAddress = sb.toString();
//        	
//        	String vPlayRtmpAddress = String.valueOf(liveChannel.get("vPlayRtmpAddress"));
//        	sb = new StringBuilder(vPlayRtmpAddress);
//            sb.insert(7, "onairzb.onairsaas.com");
//            vPlayRtmpAddress = sb.toString();
//        	
//        	videoRoomMap.put("virtualId", liveChannel.get("virtualId"));
////        	videoRoomMap.put("vPushAddress", liveChannel.get("vPushAddress"));
//        	videoRoomMap.put("vPushAddress", vPushAddress);
//        	videoRoomMap.put("vPlayRtmpAddress", vPlayRtmpAddress);
//        	
//        	String vPlayHlsAddress = String.valueOf(liveChannel.get("vPlayHlsAddress"));
//        	sb = new StringBuilder(vPlayHlsAddress);
//            sb.insert(7, "onairzb.onairsaas.com");
//            vPlayHlsAddress = sb.toString();
//            
//            String vPlayCdnAddress = String.valueOf(liveChannel.get("vPlayCdnAddress"));
//        	sb = new StringBuilder(vPlayCdnAddress);
//            sb.insert(7, "onairzb.onairsaas.com");
//            vPlayCdnAddress = sb.toString();
//        	videoRoomMap.put("vPlayHlsAddress", vPlayHlsAddress);
//        	videoRoomMap.put("vPlayCdnAddress", vPlayCdnAddress);
        	
        	
        	
        	
        	id = videoRoomDao.createVideoRoom(videoRoomMap);
        }
		return id;
	}
	/**
	 * 通过直播间ID查询直播间信息
	 */
	@Override
	public Map<String, Object> queryLiveRoomById(CommonParameters commonParameters, Map<String, Object> param) {
		String id = String.valueOf(param.get("id"));
        Map<String, Object> videoRoomMap = videoRoomDao.queryVideoRoomById(id);
        Map<String, String> numCount = numCountService.queryNumCountByObject("user", commonParameters, String.valueOf(videoRoomMap.get(VideoRoom.ID)));
        videoRoomMap.putAll(numCount);
        return videoRoomMap;
	}
	/**
	 * 删除直播间直播通道
	 */
	@Override
	public long deleteLiveRoomChannel(CommonParameters commonParameters, Map<String, Object> param) {
		String id = String.valueOf(param.get("id"));
		String virtualId = "";
		if(param.get("virtualId")!=null){
			virtualId = String.valueOf(param.get("virtualId"));
			if(virtualId!=""){
				liveChannelService.deleteLiveChannel(commonParameters, param);
			}
		}
		return videoRoomDao.deleteVideoRoom(id);
	}
	/**
	 * 重制直播间通道
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long resetLiveChannelById(CommonParameters commonParameters, Map<String, Object> param) {
		String id = String.valueOf(param.get("id"));
		long temp = 0;
		ResponseObject responseObject = null;
		if(param.get("virtualId")!=null && param.get("virtualId")!=""){
			//删除原有通道信息释放资源
			responseObject = liveChannelService.deleteLiveChannel(commonParameters, param);
		}
		//新建直播通道
		responseObject = liveChannelService.createLiveChannel(commonParameters, param);
		
		Map<String, Object> liveChannel = new HashMap<String, Object>();
        if( responseObject.getData()!=null && responseObject.getCode()==0){
        	
        	//重置时停止原有收录
        	Map<String, Object> map = videoRoomDao.queryVideoRoomById(id);
        	String includeId = "";
        	if(map.get("includeId")!=null){
        		includeId = String.valueOf(map.get("includeId"));
        	}
        	if(includeId != ""){
        		param = new HashMap<String, Object>();
        		param.put("taskId", includeId);
        		liveChannelService.stopInclude(commonParameters, param);
        	}
        	
        	liveChannel = (Map<String, Object>) responseObject.getData();
        	String vPushAddress = String.valueOf(liveChannel.get("vPushAddress"));
        	vPushAddress = vPushAddress.replace("onlive.aircdvcloud.com", "192.168.0.103");
//        	StringBuilder sb = new StringBuilder(vPushAddress);
//            sb.insert(7, "push.onairsaas.com");
//            vPushAddress = sb.toString();
        	
        	String vPlayRtmpAddress = String.valueOf(liveChannel.get("vPlayRtmpAddress"));
//        	sb = new StringBuilder(vPlayRtmpAddress);
//            sb.insert(7, "onairzb.onairsaas.com");
//            vPlayRtmpAddress = sb.toString();
        	
        	vPlayRtmpAddress = vPlayRtmpAddress.replace("outlive.aircdvcloud.com", "192.168.0.104");
        	
//        	String vPlayHlsAddress = String.valueOf(liveChannel.get("vPlayHlsAddress"));
//        	sb = new StringBuilder(vPlayHlsAddress);
//            sb.insert(7, "onairzb.onairsaas.com");
//            vPlayHlsAddress = sb.toString();
            
//            String vPlayCdnAddress = String.valueOf(liveChannel.get("vPlayCdnAddress"));
//        	sb = new StringBuilder(vPlayCdnAddress);
//            sb.insert(7, "onairzb.onairsaas.com");
//            vPlayCdnAddress = sb.toString();
        	
        	
        	
        	Map<String, Object> videoRoomMap = new HashMap<String, Object>();
        	videoRoomMap.put("virtualId", liveChannel.get("virtualId"));
        	videoRoomMap.put("isLive", 0);
        	videoRoomMap.put("vPushAddress", vPushAddress);
        	videoRoomMap.put("vPlayRtmpAddress", vPlayRtmpAddress);
        	videoRoomMap.put("includeId", "");
        	videoRoomMap.put("vPlayHlsAddress", liveChannel.get("vPlayHlsAddress"));
        	videoRoomMap.put("vPlayCdnAddress", liveChannel.get("vPlayCdnAddress"));
//        	videoRoomMap.put("vPlayHlsAddress", vPlayHlsAddress);
//        	videoRoomMap.put("vPlayCdnAddress", vPlayCdnAddress);
        	
        	
        	
        	temp = videoRoomDao.updateVideoRoomById(id, videoRoomMap);
        }
		return temp;
	}
	/**
	 * 结束直播删除直播通道是释放资源
	 */
	@Override
	public long stopLive(CommonParameters commonParameters, Map<String, Object> param) {
		String id = String.valueOf(param.get("id"));
		long temp = 0;
		ResponseObject responseObject = null;
		//删除原有通道信息释放资源
		responseObject = liveChannelService.deleteLiveChannel(commonParameters, param);
        if(responseObject.getCode()==0){
        	Map<String, Object> videoRoomMap = videoRoomDao.queryVideoRoomById(id);
        	//停止收录
        	String includeId = "";
    		if(videoRoomMap.get("includeId")!=null){
    				includeId = String.valueOf(videoRoomMap.get("includeId"));
    		}
    		if(includeId!=""){
    				param.put("taskId", includeId);
    				responseObject = liveChannelService.stopInclude(commonParameters, param);
    				//收录有回调，不需要此处处理
    		}
    		//停止拉流
    		String taskId = "";
    		if(videoRoomMap.get("taskId")!=null){
				includeId = String.valueOf(videoRoomMap.get("taskId"));
			}
    		
    		videoRoomMap = new HashMap<String, Object>();
			if(taskId!=""){
					param.put("taskId", includeId);
					responseObject = liveChannelService.stop4Json(commonParameters, param);
					if(responseObject.getCode()==0){
						videoRoomMap.put("flowUrl", "");
			        	videoRoomMap.put("taskId", "");
					}
			}
			
        	videoRoomMap.put("isLive", 3);
        	videoRoomMap.put("virtualId", "");
        	videoRoomMap.put("vPushAddress", "");
        	videoRoomMap.put("vPlayRtmpAddress", "");
        	videoRoomMap.put("vPlayHlsAddress", "");
        	videoRoomMap.put("vPlayCdnAddress", "");
        	temp = videoRoomDao.updateVideoRoomById(id, videoRoomMap);
        }
		return temp;
	}
	/**
	 * 创建网红直播公告
	 */
	@Override
	public long createPictureText(CommonParameters commonParameters, Map<String, Object> param) {
		String pid = String.valueOf(param.get("videoRoomId"));
		String content = String.valueOf(param.get("content"));
		redisUtilDao.publishNotice(pid, content);
		return 1;
	}
	/**
	 * 直播间禁言
	 */
	@Override
	public long liveRoomFansGag(CommonParameters commonParameters, Map<String, Object> param) {
		String pid = String.valueOf(param.get("videoRoomId"));
		String fansId = String.valueOf(param.get("fansId"));
		String name = String.valueOf(param.get("name"));
		String phone = String.valueOf(param.get("phone"));
		redisUtilDao.prohibitFans(pid, fansId, name, phone);
		return 1;
	}
	/**
	 * 查询直播间公告
	 */
	@Override
	public String queryAnnouncement(CommonParameters commonParameters, Map<String, Object> param) {
		String pid = String.valueOf(param.get("videoRoomId"));
		return redisUtilDao.getNotice(pid);
	}
	/**
	 * 解除粉丝禁言
	 */
	@Override
	public long relieveProhibit(CommonParameters commonParameters, Map<String, Object> param) {
		String pid = String.valueOf(param.get("videoRoomId"));
		String fansId = String.valueOf(param.get("fansId"));
        //删除缓存
        redisUtilDao.relieveProhibit(pid, fansId);
		return 1;
	}
	/**
	 * 添加直播间商品
	 */
	@Override
	public String createLiveGoods(CommonParameters commonParameters, Map<String, Object> param) {
		Map<String, Object> goodsShowMap = new HashMap<String, Object>();
		Map<String, Object> goodsMap = new HashMap<String, Object>();
        /*商品栏信息*/

        List<String> list = Arrays.asList(GoodsShow.name, GoodsShow.price, GoodsShow.GOODSURL, GoodsShow.GOODSPICTURE, GoodsShow.VIDEOROOMID, GoodsShow.VIDEOROOMNAME, GoodsShow.USERTYPE);
		 /*更新信息*/
        goodsShowMap.putAll(ValidateCommonParam.getFieldsMap(list, param));
        goodsMap.putAll(goodsShowMap);
        /*创建信息*/
        goodsShowMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));
		String id = goodsShowDao.createGoodsShow(goodsShowMap);
		String pid = String.valueOf(goodsMap.get(GoodsShow.VIDEOROOMID));
		redisUtilDao.createLiveGoods(pid, id,goodsMap);
		return id;
	}
	/**
	 * 删除直播间商品
	 */
	@Override
	public long deleteLiveGoods(CommonParameters commonParameters, Map<String, Object> param) {
		String id = String.valueOf(param.get("id"));
		long temp = goodsShowDao.deleteGoodsShow(id);
		String pid = String.valueOf(param.get(GoodsShow.VIDEOROOMID));
		if(temp>0){
			redisUtilDao.deleteLiveGoods(pid, id);
		}
		return temp;
	}
	/**
	 * 查询直播间粉丝禁言列表
	 */
	@Override
	public List<Map<String, Object>> queryProhibitFansList(CommonParameters commonParameters,
			Map<String, Object> param) {
		String pid = String.valueOf(param.get("videoRoomId"));
		return redisUtilDao.queryProhibitFansList(pid);
	}
	
	
	/**
	 * 查询直播间商品列表(APP端)
	 */
	@Override
	public List<Map<String, Object>> queryLiveGoodsList(CommonParameters commonParameters,
			Map<String, Object> param) {
		String pid = String.valueOf(param.get("videoRoomId"));
		return redisUtilDao.queryLiveGoodsList(pid);
	}
}