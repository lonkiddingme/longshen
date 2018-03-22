package com.cdvcloud.douting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.dao.GoodsShowDao;
import com.cdvcloud.douting.dao.HomePageDao;
import com.cdvcloud.douting.dao.PictureTextDao;
import com.cdvcloud.douting.dao.VideoRoomDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.HomePage;
import com.cdvcloud.douting.domain.PictureText;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.domain.VideoRoom;
import com.cdvcloud.douting.service.NumCountService;
import com.cdvcloud.douting.service.VideoRoomAppService;
import com.cdvcloud.rms.util.Pages;
/**
 * APP逗看管理
 *
 */
@Service
public class VideoRoomAppServiceImpl extends BaseServiceImpl implements VideoRoomAppService {
	
	
	@Autowired
	private VideoRoomDao videoRoomDao;
	
	@Autowired
	private HomePageDao homePageDao;

	@Autowired
	private PictureTextDao pictureTextDao;
	
	@Autowired
	private GoodsShowDao goodsShowDao;
	
	@Autowired
	private NumCountService numCountService;
	
	/**
	 * 逗看首頁查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> doukanHome(CommonParameters commonParameters, Map<String, Object> param) {
		
		String type = String.valueOf(param.get(QueryBasicObject.TYPE));
		
		Integer currentPage =null;
		if( param.get(QueryBasicObject.CURRENTPAGE) !=null){
				
			currentPage =Integer.valueOf(String.valueOf(param.get(QueryBasicObject.CURRENTPAGE)));
		}
		Integer pageNum = null;
		if(param.get(QueryBasicObject.PAGENUM) !=null){
				
			pageNum =Integer.valueOf(String.valueOf(param.get(QueryBasicObject.PAGENUM)));
		}
			 
		if(currentPage==null){
			currentPage = 1;
		}
			
		if(pageNum==null){
			pageNum = 8;
		}
		// 排序
		
		// 返回字段
		Map<String, Object> backMap = new HashMap<String, Object>();
		// 当前页
		Map<String, Object> currentPageMap = new HashMap<String, Object>();
		// 每页条数
		Map<String, Object> pageNumMap = new HashMap<String, Object>();
				//过滤条件
		Map<String, Object> queryMap = new HashMap<String, Object>();		
		
		//逗看直播间数据
		int currentPage1 = 0;
		int totalPage = 0;
		 List<Map<String, Object>> videoRooms  = new ArrayList<Map<String, Object>>();
		
		if(type.equals(QueryBasicObject.ALL) || type.equals(QueryBasicObject.VIDEOROOM)){
			
			Map<String, Object> sortMap = new HashMap<String, Object>();
			Map<String, Object> sort = new HashMap<String, Object>();
			sort.put(QueryBasicObject.WEIGTH, -1);
			sortMap.put(QueryBasicObject.SORT, sort);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
        	map.put(QueryBasicObject.STATUS, Constants.ONE);
        	map.put(BasicObject.COMPANYID, commonParameters.getCompanyId());

			queryMap.put(QueryBasicObject.CONDITIONPARAM, map);
			
			currentPageMap.put(QueryBasicObject.CURRENTPAGE, currentPage);
			pageNumMap.put(QueryBasicObject.PAGENUM, pageNum);
			

			Map<String, Object> backVideoRoomMap = new HashMap<String, Object>();
			backVideoRoomMap.put(QueryBasicObject.ID, 1);
			backVideoRoomMap.put(QueryBasicObject.THUMBANAILURL, 1);
			backVideoRoomMap.put(QueryBasicObject.NAME, 1);
			backVideoRoomMap.put(QueryBasicObject.LOOKNUM, 1);
			backVideoRoomMap.put(QueryBasicObject.H5URL, 1);
			backVideoRoomMap.put(QueryBasicObject.OTHERSH5URL, 1);
			backVideoRoomMap.put(QueryBasicObject.STARTTIME, 1);
			backVideoRoomMap.put(QueryBasicObject.ENDTIME, 1);
			backVideoRoomMap.put(QueryBasicObject.OPTIONS, 1);
			backVideoRoomMap.put(QueryBasicObject.REMARK, 1);
			backVideoRoomMap.put(QueryBasicObject.ISLIVE, 1);
			backVideoRoomMap.put(QueryBasicObject.TYPE, 1);
			backVideoRoomMap.put(QueryBasicObject.REMARK, 1);
			backMap.put(QueryBasicObject.BACKPARAM, backVideoRoomMap);
			
			Pages VideoRoom4Page = videoRoomDao.queryVideoRoom4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
			videoRooms = (List<Map<String, Object>>) VideoRoom4Page.getResults();
			//查询统计数
			for (Map<String, Object> map2 : videoRooms) {
				String id = String.valueOf(map2.get(QueryBasicObject.ID));
				Map<String, String> num = numCountService.queryNumCountByObject("fans", commonParameters, id);
				map2.putAll(num);
			}
			//总页数  不查是为0；
			totalPage = videoRooms.size()%pageNum==0?(videoRooms.size()/pageNum):(videoRooms.size()/pageNum+1);
			//栏目第几页不查节目栏是为0
			currentPage1=currentPage;
		}
		
		//逗看轮播图列表
		List<Map<String, Object>> recommends  = new ArrayList<Map<String, Object>>();
		if(type.equals(QueryBasicObject.ALL) || type.equals(QueryBasicObject.PICTURE)){
			
			
			Map<String, Object> sortMap = new HashMap<String, Object>();
			Map<String, Object> sort = new HashMap<String, Object>();
			sort.put(QueryBasicObject.WEIGTH, 1);
			sort.put(QueryBasicObject.CTIMELONG, -1);
			sortMap.put(QueryBasicObject.SORT, sort);
			
			Map<String, Object> queryData = new HashMap<String, Object>();
			
			
			queryData.put(HomePage.PUSHSTATE,Constants.IS_RELEASED);
			queryData.put(HomePage.TYPE,QueryBasicObject.CAROUSELFIGUREVIDEOROOM);
			queryData.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
			queryMap.put(QueryBasicObject.CONDITIONPARAM, queryData);
			
			pageNumMap = new HashMap<String, Object>();
			
			
			Integer pageNum1 = null;
			if(param.get(QueryBasicObject.RECOMMENDNUM)!=null){
				pageNum1 = Integer.valueOf(String.valueOf( param.get(QueryBasicObject.RECOMMENDNUM)));
				
			}
			if(pageNum1 == null){
				pageNumMap.put(QueryBasicObject.PAGENUM, 5);
			}else{
				pageNumMap.put(QueryBasicObject.PAGENUM,pageNum1);
			}
			
			
			currentPageMap = new HashMap<String, Object>();
			currentPageMap.put(QueryBasicObject.CURRENTPAGE, 1);
			
			//返回数据
			Map<String, Object> backVideoRoomMap = new HashMap<String, Object>();
			backMap = new HashMap<String, Object>();
			backVideoRoomMap.put(QueryBasicObject.ID, 1);
			backVideoRoomMap.put(QueryBasicObject.THUMBANAILURL, 1);
			backVideoRoomMap.put(QueryBasicObject.BIND, 1);
			backVideoRoomMap.put(QueryBasicObject.BINDTYPE, 1);
			backVideoRoomMap.put(QueryBasicObject.TITLE, 1);
			
			backMap.put(QueryBasicObject.BACKPARAM, backVideoRoomMap);
			Pages HomePage4Page = homePageDao.queryHomePage4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
			
			
			List<Map<String, Object>> homePageList = (List<Map<String, Object>>) HomePage4Page.getResults();
			
			for (Map<String, Object> map : homePageList) {
				String bindType = String.valueOf(map.get(QueryBasicObject.BINDTYPE));
				String bind = String.valueOf(map.get(QueryBasicObject.BIND));
				if(bindType.equals("videoRoom")){
					Map<String, Object> videoRoomMap = videoRoomDao.queryVideoRoomById(bind);
					//isLive = 1时为正在直播。0时为结束直播
					Integer isLive = 0;
					if(videoRoomMap!=null&&!videoRoomMap.isEmpty()){
						if(videoRoomMap.get("isLive")!=null){
							isLive = (Integer) videoRoomMap.get("isLive");
						} 
						if(isLive==1){
							map.put("url", videoRoomMap.get("vPlayHlsAddress")==null?"":videoRoomMap.get("vPlayHlsAddress"));
						}
						if(isLive==0){
							map.put("url", videoRoomMap.get("liveReviewUrl")==null?"":videoRoomMap.get("liveReviewUrl"));
						}
						map.put(VideoRoom.REMARK, String.valueOf(videoRoomMap.get(VideoRoom.REMARK)));
					}
				}
				recommends.add(map);
	        }
			
		}
		
		  //APP接口返回数据
	    Map<String,Object> data = new HashMap<String,Object>();
		
	    
	    data.put("videoRooms", videoRooms);
	    data.put(QueryBasicObject.RECOMMENDS, recommends);
	    data.put(QueryBasicObject.CURRENTPAGE, currentPage1);
	    data.put(QueryBasicObject.TOTALPAGE, totalPage);
	    
		return data;
	}

	/**
	 * 通过直播间ID查询图文直播
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> queryPictureTexts(CommonParameters commonParameters, Map<String, Object> param) {
				// 排序
				Map<String, Object> sortMap = new HashMap<String, Object>();
				sortMap.put(QueryBasicObject.SORT,  param.get("sort"));
				
				// 返回字段
				Map<String, Object> backMap = new HashMap<String, Object>();
				
				Map<String, Object> backPictureTextsMap = new HashMap<String, Object>();
				
				backPictureTextsMap.put(QueryBasicObject.ID, 1);
				
				backPictureTextsMap.put(QueryBasicObject.PUSHERNAME, 1);
				backPictureTextsMap.put(QueryBasicObject.PUSHERIDENTITY, 1);
				
				backPictureTextsMap.put(QueryBasicObject.PUSHERPICTURE, 1);
				backPictureTextsMap.put(QueryBasicObject.CONTENT, 1);
				backPictureTextsMap.put(QueryBasicObject.PICTUREURL, 1);
				
				backPictureTextsMap.put(QueryBasicObject.CTIME, 1);
				
				
				backMap.put(QueryBasicObject.BACKPARAM,backPictureTextsMap);
				
				
				// 当前页
				Map<String, Object> currentPageMap = new HashMap<String, Object>();
				currentPageMap.put(QueryBasicObject.CURRENTPAGE, String.valueOf(param.get(QueryBasicObject.CURRENTPAGE)));
				
				
				// 每页条数
				Map<String, Object> pageNumMap = new HashMap<String, Object>();
				pageNumMap.put(QueryBasicObject.PAGENUM, String.valueOf(param.get(QueryBasicObject.PAGENUM)));
				//过滤条件
				Map<String, Object> queryMap = new HashMap<String, Object>();
				
				//条件存入扩展字段
				Map<String, Object> queryData = new HashMap<String, Object>();
				queryData.put(PictureText.ISDEL,Constants.IS_DEL_NO);
				queryData.put(PictureText.VIDEOROOMID,String.valueOf(param.get(PictureText.VIDEOROOMID)));
				
				queryData.put(BasicObject.COMPANYID, commonParameters.getCompanyId());

				queryMap.put(QueryBasicObject.CONDITIONPARAM, queryData);
				
				Pages pictureText4Page = pictureTextDao.queryPictureText4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);

				List<Map<String, Object>> sizeNum = (List<Map<String, Object>>) pictureText4Page.getResults();
				
				Integer pageNum = Integer.valueOf(String.valueOf(param.get(QueryBasicObject.PAGENUM)));
				
				Integer totalPage = sizeNum.size()%pageNum==0?(sizeNum.size()/pageNum):(sizeNum.size()/pageNum+1);
				
				pictureText4Page.setTotalPage(totalPage.longValue());				
				
				
				Map<String, Object> dataMap = new HashMap<String,Object>();
				
				dataMap.put(QueryBasicObject.PAGENUM, pictureText4Page.getPageNum());
				dataMap.put(QueryBasicObject.CONTENTNUM, pictureText4Page.getCurrentPage());
				
				dataMap.put(QueryBasicObject.TOTALPAGE, pictureText4Page.getTotalPage());
				dataMap.put("totalRecord", pictureText4Page.getTotalRecord());
				dataMap.put("results", pictureText4Page.getResults());
				return dataMap;
				
				
	}

	/**
	 * 根据逗看直播间ID 查询商品
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryGoodsShows(CommonParameters commonParameters, Map<String, Object> param) {
		// 排序
		Map<String, Object> sortMap = new HashMap<String, Object>();
		sortMap.put(QueryBasicObject.SORT, param.get("sort"));
		
		
		// 返回字段
		Map<String, Object> backMap = new HashMap<String, Object>();
		
		Map<String, Object> backGoodsMap = new HashMap<String, Object>();
		
		backGoodsMap.put(QueryBasicObject.ID, 1);
		
		backGoodsMap.put(QueryBasicObject.NAME, 1);
		backGoodsMap.put(QueryBasicObject.PRICE, 1);
		
		backGoodsMap.put(QueryBasicObject.GOODSURL, 1);
		backGoodsMap.put(QueryBasicObject.GOODSPICTURE, 1);
		
		backGoodsMap.put(QueryBasicObject.CTIME, 1);
		
		
		backMap.put(QueryBasicObject.BACKPARAM,backGoodsMap);
		
		
		// 当前页
		Map<String, Object> currentPageMap = new HashMap<String, Object>();
		currentPageMap.put(QueryBasicObject.CURRENTPAGE, String.valueOf(param.get(QueryBasicObject.CURRENTPAGE)));
		
		
		// 每页条数
		Map<String, Object> pageNumMap = new HashMap<String, Object>();
		pageNumMap.put(QueryBasicObject.PAGENUM, String.valueOf(param.get(QueryBasicObject.PAGENUM)));
		//过滤条件
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		//条件存入扩展字段
		Map<String, Object> queryData = new HashMap<String, Object>();
		queryData.put(PictureText.ISDEL,Constants.IS_DEL_NO);
		queryData.put(PictureText.VIDEOROOMID,String.valueOf(param.get(PictureText.VIDEOROOMID)));
		queryData.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
		
		queryMap.put(QueryBasicObject.CONDITIONPARAM, queryData);
		
		Pages goodsShow4Page = goodsShowDao.queryGoodsShow4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);

		List<Map<String, Object>> sizeNum = (List<Map<String, Object>>) goodsShow4Page.getResults();
		
		Integer pageNum = Integer.valueOf(String.valueOf(param.get(QueryBasicObject.PAGENUM)));
		
		Integer totalPage = sizeNum.size()%pageNum==0?(sizeNum.size()/pageNum):(sizeNum.size()/pageNum+1);
		
		goodsShow4Page.setTotalPage(totalPage.longValue());				
		
		Map<String, Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put(QueryBasicObject.PAGENUM, goodsShow4Page.getPageNum());
		dataMap.put(QueryBasicObject.CONTENTNUM, goodsShow4Page.getCurrentPage());
		
		dataMap.put(QueryBasicObject.TOTALPAGE, goodsShow4Page.getTotalPage());
		dataMap.put("totalRecord", goodsShow4Page.getTotalRecord());
		dataMap.put("results", goodsShow4Page.getResults());
		return dataMap;
	}

}
