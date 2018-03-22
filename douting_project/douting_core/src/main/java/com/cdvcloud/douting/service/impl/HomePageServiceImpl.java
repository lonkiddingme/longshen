package com.cdvcloud.douting.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.HomePageDao;
import com.cdvcloud.douting.domain.HomePage;
import com.cdvcloud.douting.service.HomePageService;
import com.cdvcloud.rms.util.Pages;

/**
 * 首页管理
 *
 * @author zhangyuelong
 */

@Service
public class HomePageServiceImpl extends BaseServiceImpl implements HomePageService {

    @Autowired
    private HomePageDao homePageDao;

    /**
     * 首页创建
     */
    @Override
    public String createHomePage(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> homePageMap = new HashMap<String, Object>();
        List<String> list = Arrays.asList(HomePage.BIND, HomePage.TYPE, HomePage.PID,HomePage.PNAME,HomePage.BINDTYPE,
        		HomePage.TITLE,HomePage.WEIGHT, HomePage.THUMBNAILURL,HomePage.CLASSIFY);
         /*更新信息*/
        homePageMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*创建信息*/
        homePageMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));
		
		 /*基础信息*/
        String type = String.valueOf(param.get(HomePage.TYPE));
        if (type.equals(Constants.STARTUPPAGE)) {
            homePageMap.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
            return homePageDao.createStartUpHomePage(type, homePageMap);

        } else {

            homePageMap.put(HomePage.PUSHSTATE, Constants.IS_UNRELEASED);
            return homePageDao.createHomePage(homePageMap);
        }

    }

    /**
     * ID修改首页
     */
    @Override
    public long updateHomePageById(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> update = new HashMap<String, Object>();
        List<String> list = Arrays.asList(HomePage.BIND, HomePage.TYPE,HomePage.PID, HomePage.PNAME,HomePage.BINDTYPE,
        		HomePage.TITLE, HomePage.THUMBNAILURL,HomePage.CLASSIFY);
		
		/*更新信息*/
        update.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));


        String id = String.valueOf(param.get("id"));

        return homePageDao.updateHomePageById(id, update);
    }

    /**
     * 首页栏目 删除
     */
    @Override
    public long deleteHomePage(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return homePageDao.deleteHomePage(id);
    }

    /**
     * ID查询首页
     */
    @Override
    public Map<String, Object> queryHomePageById(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return homePageDao.queryHomePageById(id);
    }

    /**
     * 首页分页查询
     */
    @Override
    public Pages queryHomePage4Page(CommonParameters commonParameters, Map<String, Object> param) {
        // 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>();
        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));

        // 排序
        Map<String, Object> sortMap = new HashMap<String, Object>();
        sortMap.put("sort", param.get("sort"));
        // 返回字段
        Map<String, Object> backMap = new HashMap<String, Object>();
        backMap.put("backParam", param.get("backParam"));
        // 当前页
        Map<String, Object> currentPageMap = new HashMap<String, Object>();
        currentPageMap.put("currentPage", param.get("currentPage"));
        // 每页条数
        Map<String, Object> pageNumMap = new HashMap<String, Object>();
        pageNumMap.put("pageNum", param.get("pageNum"));
        return homePageDao.queryHomePage4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
    }

    /**
     * 统计总数
     */
    @Override
    public long countHomePage(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> queryMap = new HashMap<String, Object>();

        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
	        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));
        return homePageDao.countHomePage(queryMap);
    }

    /**
     * 发布
     *
     * @param commonParameters
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public long releaseHomePage(CommonParameters commonParameters, Map<String, Object> param) {
    	Object releaseIds = param.get("ids");
    	List<String> ids = null;
    	if(releaseIds !=null){
    	  ids = (List<String>) param.get("ids");
    	}
        String type = String.valueOf(param.get(HomePage.TYPE));
        //查询需要发布的数据
        Map<String, Object> sortMap = new HashMap<String, Object>(16);
        sortMap.put("sort", param.get("sort"));
        // 返回字段
        Map<String, Object> backMap = new HashMap<String, Object>(16);
        backMap.put("backParam", param.get("backParam"));
        List<Map<String, Object>>  UnReleaseData = null;
        if(ids!=null && ids.size()>0){
        	UnReleaseData = homePageDao.queryHomePageForRelease(ids, sortMap, backMap);
        }
        List<Map<String, Object>> releaseData = new ArrayList<Map<String, Object>>();
        //删除现有同类型的已发布数据
        homePageDao.deleteRelease(type);
        if(UnReleaseData != null && UnReleaseData.size()>0){
        	for (Map<String, Object> map : UnReleaseData) {
        		
        		/*移除ID*/
        		map.remove(HomePage.ID);
        		/*更新状态*/
        		map.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
        		/*创建信息*/
        		map.putAll(ValidateCommonParam.getCreateMessage(commonParameters));
        		releaseData.add(map);
        	}
        	return homePageDao.releaseHomePage(releaseData);
        }
        return 0;
    }

    /**
     * 查询启动页
     */
    @Override
    public Map<String, Object> queryHomePage(CommonParameters commonParameters, Map<String, Object> param) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put(HomePage.TYPE, Constants.STARTUPPAGE);
        queryMap.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
        queryMap.put(HomePage.COMPANYID, commonParameters.getCompanyId());
        return homePageDao.queryHomePage(queryMap);
    }

}
