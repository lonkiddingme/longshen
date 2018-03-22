package com.cdvcloud.douting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.dao.AnchorDao;
import com.cdvcloud.douting.dao.ColumnDao;
import com.cdvcloud.douting.dao.ContentDao;
import com.cdvcloud.douting.dao.HomePageDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.Column;
import com.cdvcloud.douting.domain.Content;
import com.cdvcloud.douting.domain.HomePage;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.service.BigDataService;
import com.cdvcloud.douting.service.CheckLikeAppService;
import com.cdvcloud.douting.service.DouXiuAppService;
import com.cdvcloud.douting.service.NumCountService;
import com.cdvcloud.rms.util.Pages;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;

/**
 * APP逗秀管理
 */
@Service
public class DouXiuAppServiceImpl extends BaseServiceImpl implements DouXiuAppService {


    @Autowired
    private ColumnDao columnDao;

    @Autowired
    private HomePageDao homePageDao;

    @Autowired
    private AnchorDao anchorDao;

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private BigDataService bigDataService;

    @Autowired
    private CheckLikeAppService checkLikeAppService;
    @Autowired
    private NumCountService numCountService;

    /**
     * 逗秀首页数据查询
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> douXiuHome(CommonParameters commonParameters, Map<String, Object> param) {

        String type = String.valueOf(param.get(QueryBasicObject.TYPE));

//        Integer currentPage = null;
//        if (param.get(QueryBasicObject.CURRENTPAGE) != null) {
//
//            currentPage = Integer.valueOf(String.valueOf(param.get(QueryBasicObject.CURRENTPAGE)));
//        }
//        Integer pageNum = null;
//        if (param.get(QueryBasicObject.PAGENUM) != null) {
//
//            pageNum = Integer.valueOf(String.valueOf(param.get(QueryBasicObject.PAGENUM)));
//        }
//
//        if (currentPage == null) {
//            currentPage = 1;
//        }
//
//        if (pageNum == null) {
//            pageNum = 8;
//        }
        // 排序
        Map<String, Object> sortMap = new HashMap<String, Object>();
        Map<String, Object> sortData = new HashMap<String, Object>();
        sortData.put(QueryBasicObject.WEIGTH, 1);
        sortData.put(QueryBasicObject.CTIMELONG, -1);
        sortMap.put("sort", sortData);
        // 返回字段
        Map<String, Object> backMap = new HashMap<String, Object>();
        // 当前页
        Map<String, Object> currentPageMap = new HashMap<String, Object>();
        // 每页条数
        Map<String, Object> pageNumMap = new HashMap<String, Object>();
        //过滤条件
        Map<String, Object> queryMap = new HashMap<String, Object>();


        List<Map<String, Object>> recommends = new ArrayList<Map<String, Object>>();


        //逗秀轮播图
        if (type.equals(QueryBasicObject.ALL) || type.equals(QueryBasicObject.PICTURE)) {

            Map<String, Object> queryData = new HashMap<String, Object>();


            queryData.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
            queryData.put(HomePage.TYPE, QueryBasicObject.CAROUSELFIGURECOLUMN);
            queryData.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
            
            queryMap.put("conditionParam", queryData);

            if (param.get(QueryBasicObject.RECOMMENDNUM) == null) {
                pageNumMap.put(QueryBasicObject.PAGENUM, 5);
            } else {
                pageNumMap.put(QueryBasicObject.PAGENUM, new Integer(String.valueOf(QueryBasicObject.RECOMMENDNUM)));
            }
            currentPageMap.put(QueryBasicObject.CURRENTPAGE, 1);


            Map<String, Object> backHomePageMap = new HashMap<String, Object>();

            backMap = new HashMap<String, Object>();
            backHomePageMap.put(QueryBasicObject.ID, 1);
            backHomePageMap.put(QueryBasicObject.THUMBANAILURL, 1);
            backHomePageMap.put(QueryBasicObject.BIND, 1);
            backHomePageMap.put(QueryBasicObject.BINDTYPE, 1);
            backHomePageMap.put(QueryBasicObject.TITLE, 1);
            backHomePageMap.put(QueryBasicObject.PID, 1);
            backHomePageMap.put(QueryBasicObject.PNAME, 1);
            backMap.put("backParam", backHomePageMap);

            Pages HomePage4Page = homePageDao.queryHomePage4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
            recommends = (List<Map<String, Object>>) HomePage4Page.getResults();
        }

        //根据类型查询逗秀节目列表
        Map<String, Object> videoColumn = new HashMap<String, Object>();
        
        videoColumn.put(QueryBasicObject.CURRENTPAGE,1);
        videoColumn.put(QueryBasicObject.PAGENUM,6);
        videoColumn.put(QueryBasicObject.TYPE,"video");
        
        Map<String, Object> videoMap = queryColumnByType(commonParameters,videoColumn);
        
        List<Map<String, Object>> videos = (List<Map<String, Object>>) videoMap.get(QueryBasicObject.COLUMNS);
        
        Map<String, Object> audioColumn = new HashMap<String, Object>();
        
        audioColumn.put(QueryBasicObject.CURRENTPAGE,1);
        audioColumn.put(QueryBasicObject.PAGENUM,6);
        audioColumn.put(QueryBasicObject.TYPE,"audio");
        
        Map<String, Object> audioMap = queryColumnByType(commonParameters,audioColumn);
        
        List<Map<String, Object>> audios = (List<Map<String, Object>>) audioMap.get(QueryBasicObject.COLUMNS);

//        int currentPage1 = 0;
//        int totalPage = 0;
//        List<Map<String, Object>> columns = new ArrayList<Map<String, Object>>();
//        List<Map<String, Object>> newColumns = new ArrayList<Map<String, Object>>();
//        if (type.equals(QueryBasicObject.ALL) || type.equals(QueryBasicObject.COLUMN)) {
//            currentPageMap = new HashMap<String, Object>();
//            pageNumMap = new HashMap<String, Object>();
//            sortMap = new HashMap<String, Object>();
//            currentPageMap.put(QueryBasicObject.CURRENTPAGE, currentPage);
//            pageNumMap.put(QueryBasicObject.PAGENUM, pageNum);
//
//            //栏目第几页不查节目栏是为0
//            currentPage1 = currentPage;
//
//            queryMap = new HashMap<String, Object>();
//
//            //条件存入扩展字段
//            Map<String, Object> queryData = new HashMap<String, Object>();
//
//
//            queryData.put(Column.STATUS, Constants.ONE);
//            queryData.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
//            queryMap.put("conditionParam", queryData);
//
//
//            Map<String, Object> backColumnMap = new HashMap<String, Object>();
//            backMap = new HashMap<String, Object>();
//            backColumnMap.put(QueryBasicObject.ID, 1);
//            backColumnMap.put(QueryBasicObject.THUMBANAILURL, 1);
//            backColumnMap.put(QueryBasicObject.NAME, 1);
//            backColumnMap.put(Column.TYPE, 1);
//            backColumnMap.put(Column.REMARK, 1);
//            backMap.put("backParam", backColumnMap);
//
//            Pages Column4Page = columnDao.queryColumn4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
//
//            
//            
//            
//            
//            columns = (List<Map<String, Object>>) Column4Page.getResults();
//            
//            for (Map<String, Object> map : columns) {
//            	String id = String.valueOf(map.get(QueryBasicObject.ID));
//            	long queryNumCount = numCountService.queryNumCount("fans", commonParameters, "lookNum", id);
//            	map.put(QueryBasicObject.LOOKNUM, queryNumCount);
//            	newColumns.add(map);
//            }
//            //总页数  不查为0
//            totalPage = columns.size() % pageNum == 0 ? (columns.size() / pageNum) : (columns.size() / pageNum + 1);
//            Column4Page.setTotalPage((long) totalPage);
//
//        }
        
        
        /*逗秀首页最新内容*/

//        List<Map<String, Object>> contents = new ArrayList<Map<String, Object>>();

//        if (type.equals(QueryBasicObject.ALL) || type.equals(QueryBasicObject.CONTENT)) {
//        	
//        	sortMap = new HashMap<String, Object>();
//            sortData = new HashMap<String, Object>();
//            sortData.put("pushTimeLong", -1);
//            sortMap.put("sort", sortData);
//            currentPageMap = new HashMap<String, Object>();
//            pageNumMap = new HashMap<String, Object>();
//            currentPageMap.put(QueryBasicObject.CURRENTPAGE, 1);
//
//
//            queryMap = new HashMap<String, Object>();
//
//            //条件存入扩展字段
//            Map<String, Object> queryData = new HashMap<String, Object>();
//
//
//            queryData.put(Content.STATUS, Constants.ONE);
//            queryData.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
//            
//            queryMap.put("conditionParam", queryData);
//
//
//            Integer pageNum1 = null;
//            if (param.get(QueryBasicObject.CONTENTNUM) != null) {
//                pageNum1 = Integer.valueOf(String.valueOf(param.get(QueryBasicObject.CONTENTNUM)));
//
//            }
//            if (pageNum1 == null) {
//                pageNumMap.put(QueryBasicObject.PAGENUM, 3);
//            } else {
//                pageNumMap.put(QueryBasicObject.PAGENUM, pageNum1);
//            }
//            Map<String, Object> backContentMap = new HashMap<String, Object>();
//            backMap = new HashMap<String, Object>();
//            backContentMap.put(QueryBasicObject.DOCID, 1);
//            backContentMap.put(QueryBasicObject.THUMBANAIL, 1);
//            backContentMap.put(QueryBasicObject.TITLE, 1);
//            backContentMap.put(QueryBasicObject.PUSHTIME, 1);
//            backContentMap.put(QueryBasicObject.CIBIAO, 1);
//            backContentMap.put(QueryBasicObject.CLASSIFY, 1);
//
//            backMap.put("backParam", backContentMap);
//
//            Pages Content4Page = contentDao.queryContent4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
//
//            contents = (List<Map<String, Object>>) Content4Page.getResults();
//            if (contents.size() > 0) {
//                for (Map<String, Object> map : contents) {
//                    if (map.get(QueryBasicObject.CIBIAO) != null) {
//                        List<Map<String, Object>> cibiao = (List<Map<String, Object>>) map.get(QueryBasicObject.CIBIAO);
//                        if (cibiao.size() > 0) {
//                            Map<String, Object> map2 = cibiao.get(0);
//                            map.put(QueryBasicObject.CBID, map2.get(QueryBasicObject.CBID));
//                            map.put(QueryBasicObject.CBNAME, map2.get(QueryBasicObject.CBNAME));
//
//                        }
//                    } else {
//                        map.put(QueryBasicObject.CBID, " ");
//                        map.put(QueryBasicObject.CBNAME, " ");
//                    }
//                    map.remove(QueryBasicObject.CIBIAO);
//                    map.remove(QueryBasicObject.ID);
//
//                    if (map.get("thumbnail") != null) {
//                        map.put("thumbnailUrl", map.get("thumbnail"));
//                        map.remove("thumbnail");
//                    }
//                }
//            }
//        }


        //APP接口返回数据
        Map<String, Object> data = new HashMap<String, Object>();


        data.put(QueryBasicObject.AUDIOS, audios);
        data.put(QueryBasicObject.RECOMMENDS, recommends);
        data.put(QueryBasicObject.VIDEOS, videos);

        return data;
    }

    /**
     * 根据逗秀栏目ID 查询栏目下内容列表
     */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> queryColumnContents(CommonParameters commonParameters, Map<String, Object> param) {


        //通过逗秀栏目ID查询栏目拿到该 栏目下的词表ID

        Map<String, Object> columnMap = columnDao.queryColumnById(String.valueOf(param.get(QueryBasicObject.COLUMNID)));

		
		/*根据词表ID查内容列表 */

        // 排序
        Map<String, Object> sortMap = new HashMap<String, Object>();
        Map<String, Object> sortData = new LinkedHashMap<String, Object>();
        sortData.put(QueryBasicObject.WEIGTH, -1);
        sortData.put(QueryBasicObject.PUSHTIMELONG, -1);
        sortMap.put("sort", sortData);
        // 返回字段
        Map<String, Object> backMap = new HashMap<String, Object>();

        Map<String, Object> backContentMap = new HashMap<String, Object>();

        backContentMap.put(QueryBasicObject.ID, 1);

        backContentMap.put(QueryBasicObject.DOCID, 1);

        backContentMap.put(QueryBasicObject.TITLE, 1);
        backContentMap.put(QueryBasicObject.THUMBANAIL, 1);

        backContentMap.put(QueryBasicObject.PUSHTIME, 1);
        backContentMap.put(QueryBasicObject.CLASSIFY, 1);
        
        backContentMap.put(Content.ISCOMMENT, 1);

        String type = String.valueOf(param.get("type"));
        
        if(type.equals("audio")){
        	backContentMap.put(QueryBasicObject.AUDIOS, 1);
        }
        
        if(type.equals("video")){
        	backContentMap.put(QueryBasicObject.VIDEOS, 1);
        }
        backMap.put("backParam", backContentMap);


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

        queryData.put("cibiao.cbid", String.valueOf(columnMap.get(QueryBasicObject.WORDMARID)));
        queryData.put(QueryBasicObject.STATUS, Constants.ONE);
        queryData.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        
        queryMap.put("conditionParam", queryData);
        Pages Content4Page = contentDao.queryContent4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
        List<Map<String, Object>> sizeNum = (List<Map<String, Object>>) Content4Page.getResults();

        Integer pageNum = Integer.valueOf(String.valueOf(param.get(QueryBasicObject.PAGENUM)));

        Integer totalPage = sizeNum.size() % pageNum == 0 ? (sizeNum.size() / pageNum) : (sizeNum.size() / pageNum + 1);
        Content4Page.setTotalPage(totalPage.longValue());

        if (sizeNum.size() > 0) {
            for (Map<String, Object> map : sizeNum) {
                if (map.get(QueryBasicObject.THUMBANAIL) != null) {
                    map.put(QueryBasicObject.THUMBANAILURL, map.get(QueryBasicObject.THUMBANAIL));
                    map.remove(QueryBasicObject.THUMBANAIL);
                }
            }
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();

        dataMap.put(QueryBasicObject.PAGENUM, Content4Page.getPageNum());
        dataMap.put(QueryBasicObject.CONTENTNUM, Content4Page.getCurrentPage());

        dataMap.put(QueryBasicObject.TOTALPAGE, Content4Page.getTotalPage());
        dataMap.put("totalRecord", Content4Page.getTotalRecord());
        dataMap.put("results", Content4Page.getResults());
        return dataMap;
    }

    /**
     * 根据ID 查询逗秀节目栏根据词表ID去
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryColumn(CommonParameters commonParameters, Map<String, Object> param) {
        String id = null;
        if (param.get(QueryBasicObject.COLUMNID) != null) {
            id = String.valueOf(param.get(QueryBasicObject.COLUMNID));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (param.containsKey("findType") && String.valueOf(param.get("findType")).equals("cibiao")) {
            Map<String, Object> filter = new HashMap<String, Object>();
            filter.put(Column.WORDMARKID, id);
            filter.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
            map = columnDao.getColumnByJson(filter);
        } else {
            map = columnDao.queryColumnById(id);
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();

        dataMap.put(QueryBasicObject.ID, String.valueOf(map.get(QueryBasicObject.ID)));
        dataMap.put(QueryBasicObject.NAME, String.valueOf(map.get(QueryBasicObject.NAME)));
        dataMap.put(Column.TYPE, String.valueOf(map.get(Column.TYPE)));
        dataMap.put(Column.REMARK, String.valueOf(map.get(Column.REMARK)));
        dataMap.put(QueryBasicObject.THUMBANAILURL, String.valueOf(map.get(QueryBasicObject.THUMBANAILURL)));


        //查询主播缩略图
        if (map.get(QueryBasicObject.ANCHORSLIST) != null) {

            List<Map<String, Object>> anchorsList = (List<Map<String, Object>>) map.get(QueryBasicObject.ANCHORSLIST);

            List<String> ids = new ArrayList<String>();

            for (Map<String, Object> anchorsMap : anchorsList) {
                ids.add(String.valueOf(anchorsMap.get(QueryBasicObject.ID)));
            }
            // 排序
            Map<String, Object> sortMap = new HashMap<String, Object>();
            Map<String, Object> sortData = new HashMap<String, Object>();
            sortData.put(QueryBasicObject.WEIGTH, 1);
            sortMap.put("sort", sortData);
            // 返回字段
            Map<String, Object> backMap = new HashMap<String, Object>();


            Map<String, Object> backAnchorMap = new HashMap<String, Object>();

            backAnchorMap.put(QueryBasicObject.ID, 1);
            backAnchorMap.put(QueryBasicObject.NAME, 1);
            backAnchorMap.put(QueryBasicObject.THUMBANAILURL, 1);
            backMap.put("backParam", backAnchorMap);

            List<Map<String, Object>> queryAnchors = anchorDao.queryAnchors(ids, backMap, sortMap);
            dataMap.put(QueryBasicObject.ANCHORS, queryAnchors);
        }


        return dataMap;
    }


    /**
     * 根据DOCID查询逗秀内容详情
     *
     * @param commonParameters
     * @param param
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> queryContent(CommonParameters commonParameters, Map<String, Object> param) throws Exception {

        Map<String, Object> dataMap = new HashMap<String, Object>();

        if (param.containsKey("docid")) {
            param.put("docId", param.get("docid"));
            param.remove("docid");
        }
        Map<String, Object> map = bigDataService.queryBigDataByDocId(commonParameters, param);

        //判断大数据是否有该数据没有删除本地的数据

        if (map == null || map.isEmpty()) {
        	String docid = String.valueOf(param.get("docId"));
        	if(docid!=null&&docid.length()>0){
        		contentDao.deleteContentByDocId(docid);
        	}

            dataMap.put("delete", "yes");
            return dataMap;
        }

        Map<String, Object> contentMap = contentDao.queryContentByDocId(String.valueOf(param.get("docId")));


        if (contentMap != null) {
            dataMap.put(QueryBasicObject.DOCID, param.get("docId"));
            dataMap.put(QueryBasicObject.TITLE, String.valueOf(contentMap.get(QueryBasicObject.TITLE)));
            dataMap.put(QueryBasicObject.PUSHTIME, String.valueOf(contentMap.get(QueryBasicObject.PUSHTIME)));
            dataMap.put(QueryBasicObject.HTMLCONTENT, String.valueOf(contentMap.get(QueryBasicObject.SRCONTENT)));
            //查询查看数
            Map<String, String> numCountMap = numCountService.queryNumCountByObject("fans", commonParameters, String.valueOf(param.get("docId")));
            String lookNum = numCountMap.get(QueryBasicObject.LOOKNUM);
            if (StringUtil.isEmpty(lookNum)) {
                dataMap.put(QueryBasicObject.LOOKNUM, 0);
            } else {
                dataMap.put(QueryBasicObject.LOOKNUM, lookNum);
            }
            //点赞数
            String likeNum = numCountMap.get(QueryBasicObject.LIKENUM);
            if (StringUtil.isEmpty(likeNum)) {
                dataMap.put(QueryBasicObject.LIKENUM, 0);
            } else {
                dataMap.put(QueryBasicObject.LIKENUM, likeNum);
            }
            dataMap.put(QueryBasicObject.THUMBANAILURL, String.valueOf(contentMap.get(QueryBasicObject.THUMBANAIL)));
            dataMap.put(Content.ISCOMMENT, Integer.valueOf(String.valueOf(contentMap.get(Content.ISCOMMENT))));

            Map<String, Object> hasLikeMap = new HashMap<String, Object>();
            hasLikeMap.put("beLikeId", param.get("docId"));
            hasLikeMap.put("accessToken", param.get("accessToken"));
            hasLikeMap.put("timeStamp", param.get("timeStamp"));
            ResponseObject checkLike = checkLikeAppService.checkLike(commonParameters, hasLikeMap);
            int code = checkLike.getCode();
            if (code == 0) {
				Map<String, Object> data = (Map<String, Object>) checkLike.getData();
                if (data.get("checkFlag") != null) {
                    dataMap.put(QueryBasicObject.HASLIKE, data.get("checkFlag"));
                } else {
                    dataMap.put(QueryBasicObject.HASLIKE, false);
                }
            } else {
                dataMap.put(QueryBasicObject.HASLIKE, false);
            }
        }

        if (map != null && map.containsKey(QueryBasicObject.SRCONTENT)) {
            dataMap.put(QueryBasicObject.HTMLCONTENT, map.get(QueryBasicObject.SRCONTENT));
        }
        if (map != null && map.containsKey(QueryBasicObject.AUDIOS)) {
            dataMap.put(QueryBasicObject.AUDIOS, map.get(QueryBasicObject.AUDIOS));
        }
        if (map != null && map.containsKey(QueryBasicObject.VIDEOS)) {
            dataMap.put(QueryBasicObject.VIDEOS, map.get(QueryBasicObject.VIDEOS));
        }
        if (map != null && map.containsKey(QueryBasicObject.IMAGES)) {
            dataMap.put(QueryBasicObject.IMAGES, map.get(QueryBasicObject.IMAGES));
        }
        dataMap.put("delete", "no");
        return dataMap;
    }

    /**
     * 根据栏目类型分页查询逗秀栏目
     */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryColumnByType(CommonParameters commonParameters, Map<String, Object> param) {

		Map<String, Object> currentPageMap = new HashMap<String, Object>();
		Map<String, Object> pageNumMap = new HashMap<String, Object>();
		Map<String, Object> sortMap = new HashMap<String, Object>();
        currentPageMap.put(QueryBasicObject.CURRENTPAGE, String.valueOf(param.get(QueryBasicObject.CURRENTPAGE)));
        pageNumMap.put(QueryBasicObject.PAGENUM, String.valueOf(param.get(QueryBasicObject.PAGENUM)));


        Map<String, Object> queryMap = new HashMap<String, Object>();

        //条件存入扩展字段
        Map<String, Object> queryData = new HashMap<String, Object>();


        queryData.put(Column.STATUS, Constants.ONE);
        queryData.put(Column.TYPE, String.valueOf(param.get(Column.TYPE)));
        queryData.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        queryMap.put("conditionParam", queryData);


        Map<String, Object> backColumnMap = new HashMap<String, Object>();
        Map<String, Object> backMap = new HashMap<String, Object>();
        backColumnMap.put(QueryBasicObject.ID, 1);
        backColumnMap.put(QueryBasicObject.THUMBANAILURL, 1);
        backColumnMap.put(QueryBasicObject.NAME, 1);
        backColumnMap.put(Column.TYPE, 1);
        backColumnMap.put(Column.REMARK, 1);
        backMap.put("backParam", backColumnMap);

        Pages Column4Page = columnDao.queryColumn4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);

        
        
        
        
        List<Map<String, Object>> columns = (List<Map<String, Object>>) Column4Page.getResults();
        
        for (Map<String, Object> map : columns) { 
        	String id = String.valueOf(map.get(QueryBasicObject.ID));
        	long queryNumCount = numCountService.queryNumCount("fans", commonParameters, "lookNum", id);
        	map.put(QueryBasicObject.LOOKNUM, queryNumCount);
        }

        Map<String,Object> data = new HashMap<String,Object>();
        data.put(QueryBasicObject.COLUMNS, columns);
        return data;
	}

}
