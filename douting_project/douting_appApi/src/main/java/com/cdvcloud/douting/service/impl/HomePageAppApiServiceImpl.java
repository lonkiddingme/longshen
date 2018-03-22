package com.cdvcloud.douting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.dao.BroadcastColumnDao;
import com.cdvcloud.douting.dao.BroadcastDao;
import com.cdvcloud.douting.dao.ColumnDao;
import com.cdvcloud.douting.dao.VideoRoomDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.Broadcast;
import com.cdvcloud.douting.domain.BroadcastColumn;
import com.cdvcloud.douting.domain.Column;
import com.cdvcloud.douting.domain.HomePage;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.domain.VideoRoom;
import com.cdvcloud.douting.service.HomePageAppApiService;
import com.cdvcloud.douting.service.HomePageService;
import com.cdvcloud.rms.util.Pages;

/**
 * 轮播图APP接口
 *
 * @author SongYuanKun
 * @date 2017/11/10
 */
@Service
public class HomePageAppApiServiceImpl extends BaseServiceImpl implements HomePageAppApiService {
    @Autowired
    private HomePageService homePageService;
    @Autowired
    private VideoRoomDao videoRoomDao;
    @Autowired
    private BroadcastDao broadcastDao;
    @Autowired
    private BroadcastColumnDao broadcastColumnDao;
    @Autowired
    private ColumnDao columnDao;
    /**
     * 查询轮播图
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 轮播图
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Map<String, Object>> queryCarousels(CommonParameters commonParameters, Map<String, Object> jsonMap) {
        String type = String.valueOf(jsonMap.get("type"));
        Integer carouselNum = (Integer) jsonMap.get("carouselNum");
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        Map<String, Object> conditionParam = new HashMap<String, Object>();
        Map<String, Object> sortMap = new HashMap<String, Object>();
        Map<String, Object> backMap = new HashMap<String, Object>();

        /*更新信息*/
        conditionParam.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
        switch (type) {
            case "recommend":
                conditionParam.put(HomePage.TYPE, "carouselFigureRecommend");
                break;
            case "douxiu":
                conditionParam.put(HomePage.TYPE, "carouselFigureColumn");
                break;
            case "doukan":
                conditionParam.put(HomePage.TYPE, "carouselFigureVideoRoom");
                break;
            default:
        }
        conditionParam.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        queryMap.put("conditionParam", conditionParam);
        // 排序
        sortMap.put(HomePage.WEIGHT, 1);
        // 返回字段
        backMap.put(HomePage.THUMBNAILURL, 1);
        backMap.put(HomePage.TITLE, 1);
        backMap.put(HomePage.BINDTYPE, 1);
        backMap.put(HomePage.BIND, 1);
        backMap.put(HomePage.PID, 1);
        backMap.put(HomePage.PNAME, 1);
        backMap.put(HomePage.CLASSIFY, 1);


        param.putAll(queryMap);
        param.put("sort", sortMap);
        // 当前页
        param.put("backParam", backMap);
        param.put("currentPage", 1);
        // 每页条数
        param.put("pageNum", carouselNum);
        Pages pages = homePageService.queryHomePage4Page(commonParameters, param);
        
        
        
        List<Map<String, Object>> homePageList = (List<Map<String, Object>>) pages.getResults();
        
        for (Map<String, Object> map : homePageList) {
			String bindType = String.valueOf(map.get(QueryBasicObject.BINDTYPE));
			String bind = String.valueOf(map.get(QueryBasicObject.BIND));
			if(bindType.equals("videoRoom")){
				Map<String, Object> videoRoomMap = videoRoomDao.queryVideoRoomById(bind);
				map.put("url", String.valueOf(videoRoomMap.get(VideoRoom.H5URL)));
				map.put(VideoRoom.REMARK, String.valueOf(videoRoomMap.get(VideoRoom.REMARK)));
			}
			if(bindType.equals("broadcast")){
				Map<String, Object> broadcastMap = broadcastDao.queryBroadcastById(bind);
				map.put("url", String.valueOf(broadcastMap.get(Broadcast.URL)));
				map.put(Broadcast.REMARK, String.valueOf(broadcastMap.get(Broadcast.REMARK)));
				map.put("broadcastThumbnailUrl", String.valueOf(broadcastMap.get(Broadcast.THUMBNAILURL)));
			}
        }
        return homePageList;
    }

    /**
     * 查询推荐内容
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 推荐
     */
    @SuppressWarnings("unchecked")
	@Override
    public Pages queryRecommendContents(CommonParameters commonParameters, Map<String, Object> jsonMap) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        Map<String, Object> conditionParam = new HashMap<String, Object>(16);
        Map<String, Object> sortMap = new HashMap<String, Object>(16);
        Map<String, Object> backMap = new HashMap<String, Object>(16);


        Integer pageNum = (Integer) jsonMap.get("pageNum");
        Integer currentPage = (Integer) jsonMap.get("currentPage");
        conditionParam.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
        conditionParam.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        conditionParam.put(HomePage.TYPE, "recommendContent");
        backMap.put(HomePage.THUMBNAILURL, 1);
        backMap.put(HomePage.TITLE, 1);
        backMap.put(HomePage.PID, 1);
        backMap.put(HomePage.BIND, 1);
        backMap.put(HomePage.PNAME, 1);
        backMap.put(HomePage.CLASSIFY, 1);


        param.put("conditionParam", conditionParam);
        param.put("sort", sortMap);
        // 当前页
        param.put("backParam", backMap);
        param.put("currentPage", currentPage);
        // 每页条数
        param.put("pageNum", pageNum);
        Pages pages = homePageService.queryHomePage4Page(commonParameters, param);
        List<Map<String, Object>> results = (List<Map<String, Object>>) pages.getResults();
        if (pages.getCurrentPage() > 1 && pages.getPageNum() > results.size()) {
            param.put("currentPage", 1);
            param.put("pageNum", pages.getPageNum() - results.size());
            Pages pages1 = homePageService.queryHomePage4Page(commonParameters, param);
            results.addAll((List<Map<String, Object>>) pages1.getResults());
        }
        for (Map<String, Object> map : results) {
            map.put("docId", map.get(HomePage.BIND));
            map.remove("docId");
            String url = String.valueOf(map.get(HomePage.THUMBNAILURL));
            if(url==null || url.length()<=0 ){
            	String columnId = String.valueOf(map.get(HomePage.PID));
            	Map<String, Object> columnMap = columnDao.queryColumnById(columnId);
            	map.put(HomePage.THUMBNAILURL, columnMap.get(Column.THUMBNAILURL));
            }
        }
        pages.setResults(results);
        return pages;
    }

    /**
     * 查询逗播直播间
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 推荐
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Map<String, Object>> queryRecommendBroadcasts(CommonParameters commonParameters, Map<String, Object> jsonMap) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        Map<String, Object> conditionParam = new HashMap<String, Object>(16);
        Map<String, Object> sortMap = new HashMap<String, Object>(16);
        Map<String, Object> backMap = new HashMap<String, Object>(16);


        Integer currentPage = 1;
        Integer pageNum = (Integer) jsonMap.get("broadcastNum");
        conditionParam.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
        conditionParam.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        conditionParam.put(HomePage.TYPE, "recommendBroadcast");
        backMap.put(HomePage.BIND, 1);
        backMap.put(HomePage.THUMBNAILURL, 1);

        param.put("conditionParam", conditionParam);
        param.put("sort", sortMap);
        // 当前页
        param.put("backParam", backMap);
        param.put("currentPage", currentPage);
        // 每页条数
        param.put("pageNum", pageNum);
        Pages pages = homePageService.queryHomePage4Page(commonParameters, param);
        List<Map<String, Object>> mapIds = (List<Map<String, Object>>) pages.getResults();
        List<String> ids = new ArrayList<String>();

        for (Map<String, Object> idMap : mapIds) {
            ids.add(String.valueOf(idMap.get(HomePage.BIND)));
        }

        Map<String, Object> sortMap1 = new HashMap<String, Object>(16);
        Map<String, Object> backMap1 = new HashMap<String, Object>(16);
        backMap1.put(Broadcast.NAME, 1);
        backMap1.put(Broadcast.URL, 1);
        backMap1.put(Broadcast.THUMBNAILURL, 1);
        backMap1.put(Broadcast.STARTTIME, 1);
        backMap1.put(Broadcast.ENDTIME, 1);
        backMap1.put(Broadcast.CTIME, 1);
        backMap1.put(QueryBasicObject.REMARK, 1);
        Map<String, Object> backParam = new HashMap<String, Object>(16);
        Map<String, Object> sortParam = new HashMap<String, Object>(16);
        backParam.put("backParam", backMap1);
        sortParam.put("sortParam", sortMap1);

        List<Map<String, Object>> broadcasts = broadcastDao.queryBroadcastByIds(ids, backParam, sortParam);
      //判断推荐页直播间是否上传图片  没有图片就用直播间图片，有就替换。
        for (Map<String, Object> map2 : broadcasts) {
        	String id = String.valueOf(map2.get(Broadcast.ID));
			for (Map<String, Object> map : mapIds) {
				String imgUrl = String.valueOf(map.get(HomePage.THUMBNAILURL));
				String homeId = String.valueOf(map.get(HomePage.BIND));
				if((imgUrl!=null && imgUrl.length()>0) && homeId.equals(id)){
					map2.put(Broadcast.THUMBNAILURL, imgUrl);
				}
			}
		}
        
        
        return broadcasts;
    }

    /**
     * 查询逗看直播间
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 推荐
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Map<String, Object>> queryRecommendVideoRooms(CommonParameters commonParameters, Map<String, Object> jsonMap) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        Map<String, Object> conditionParam = new HashMap<String, Object>(16);
        Map<String, Object> sortMap = new HashMap<String, Object>(16);
        Map<String, Object> backMap = new HashMap<String, Object>(16);


        Integer videoRoomNum = (Integer) jsonMap.get("videoRoomNum");
        conditionParam.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
        conditionParam.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        conditionParam.put(HomePage.TYPE, "recommendVideoRoom");
        backMap.put(HomePage.BIND, 1);
        backMap.put(HomePage.THUMBNAILURL, 1);

        param.put("conditionParam", conditionParam);
        param.put("sort", sortMap);
        // 当前页
        param.put("backParam", backMap);
        param.put("currentPage", 1);
        // 每页条数
        param.put("pageNum", videoRoomNum);
        Pages pages = homePageService.queryHomePage4Page(commonParameters, param);
        List<Map<String, Object>> mapIds = (List<Map<String, Object>>) pages.getResults();
        List<String> ids = new ArrayList<String>();

        for (Map<String, Object> idMap : mapIds) {
            ids.add(String.valueOf(idMap.get(HomePage.BIND)));
        }

        Map<String, Object> sortMap1 = new HashMap<String, Object>(16);
        Map<String, Object> backMap1 = new HashMap<String, Object>(16);
        backMap1.put(VideoRoom.NAME, 1);
        backMap1.put(VideoRoom.H5URL, 1);
        backMap1.put(VideoRoom.OTHERSH5URL, 1);
        backMap1.put(VideoRoom.STARTTIME, 1);
        backMap1.put(VideoRoom.ENDTIME, 1);
        backMap1.put(VideoRoom.OPTIONS, 1);
        backMap1.put(VideoRoom.LOOKNUM, 1);
        backMap1.put(VideoRoom.THUMBNAILURL, 1);
        backMap1.put("isLive", 1);
        backMap1.put(QueryBasicObject.REMARK, 1);
        Map<String, Object> backParam = new HashMap<String, Object>(16);
        Map<String, Object> sortParam = new HashMap<String, Object>(16);
        backParam.put("backParam", backMap1);
        sortParam.put("sortParam", sortMap1);

        List<Map<String, Object>> videoRooms = videoRoomDao.queryVideoRoomByIds(ids, backParam, sortParam);
        
        //判断推荐页直播间是否上传图片  没有图片就用直播间图片，有就替换。
			for (Map<String, Object> map2 : videoRooms) {
				String id = String.valueOf(map2.get(VideoRoom.ID));
				for (Map<String, Object> map : mapIds) {
					String imgUrl = String.valueOf(map.get(HomePage.THUMBNAILURL));
					String homeId = String.valueOf(map.get(HomePage.BIND));
					if((imgUrl!=null && imgUrl.length()>0) && homeId.equals(id)){
						map2.put(VideoRoom.THUMBNAILURL, imgUrl);
					}
				}
			}
        
        
        return videoRooms;
    }


    /**
     * 查询逗播正在进行栏目集合
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 推荐
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Map<String, Object>> queryRecommendBroadcastColumnsByBroadCastIds(CommonParameters commonParameters, Map<String, Object> jsonMap) {

        List<String> broadcastIds = (List<String>) jsonMap.get("broadcastIds");

        Map<String, Object> sortMap1 = new HashMap<String, Object>(16);
        Map<String, Object> backMap1 = new HashMap<String, Object>(16);
        backMap1.put(BroadcastColumn.NAME, 1);
        backMap1.put(BroadcastColumn.BROADCASTID, 1);
        backMap1.put(BroadcastColumn.STARTTIME, 1);
        backMap1.put(BroadcastColumn.ENDTIME, 1);
        backMap1.put(BroadcastColumn.CTIME, 1);
        backMap1.put(BroadcastColumn.THUMBNAILURL, 1);

        
        
        Map<String, Object> backParam = new HashMap<String, Object>(16);
        Map<String, Object> sortParam = new HashMap<String, Object>(16);
        Map<String, Object> queryParam = new HashMap<String, Object>(16);
        queryParam.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        backParam.put("backParam", backMap1);
        sortParam.put("sortParam", sortMap1);

        List<Map<String, Object>> broadcastColumns = broadcastColumnDao.queryRecommendBroadcastColumnsByBroadCastIds(broadcastIds, backParam, sortParam,queryParam);
        return broadcastColumns;
    }

    /**
     * queryRecommendBroadcastColumns
     *
     * @param commonParameters commonParameters
     * @param jsonMap          参数
     * @return 推荐
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Map<String, Object>> queryRecommendBroadcastColumns(CommonParameters commonParameters, Map<String, Object> jsonMap) {

        List<String> broadcastColumnIds = (List<String>) jsonMap.get("broadcastColumnIds");

        Map<String, Object> sortMap1 = new HashMap<String, Object>(16);
        Map<String, Object> backMap1 = new HashMap<String, Object>(16);
        backMap1.put(BroadcastColumn.NAME, 1);
        backMap1.put(BroadcastColumn.BROADCASTID, 1);
        backMap1.put(BroadcastColumn.STARTTIME, 1);
        backMap1.put(BroadcastColumn.ENDTIME, 1);
        backMap1.put(BroadcastColumn.CTIME, 1);
        backMap1.put(BroadcastColumn.THUMBNAILURL, 1);

        
        Map<String, Object> backParam = new HashMap<String, Object>(16);
        Map<String, Object> sortParam = new HashMap<String, Object>(16);
        Map<String, Object> queryParam = new HashMap<String, Object>(16);
        queryParam.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        backParam.put("backParam", backMap1);
        sortParam.put("sortParam", sortMap1);

        List<Map<String, Object>> broadcastColumns = broadcastColumnDao.queryRecommendBroadcastColumnsByBroadCastIds(broadcastColumnIds, backParam, sortParam,queryParam);
        return broadcastColumns;
    }

    @Override
    public Map<String, Object> getSystem(CommonParameters commonParameters, Map<String, Object> jsonMap) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        Map<String, Object> backParam = new HashMap<String, Object>(16);
        Map<String, Object> conditionParam = new HashMap<String, Object>(16);
        conditionParam.put(HomePage.TYPE, "homePage");
        conditionParam.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        backParam.put(HomePage.BIND, 1);
        backParam.put(HomePage.BINDTYPE, 1);
        backParam.put(HomePage.THUMBNAILURL, 1);
        param.put(QueryBasicObject.CONDITIONPARAM, conditionParam);
        param.put("backParam", backParam);
        return homePageService.queryHomePage(commonParameters, param);
    }
}
