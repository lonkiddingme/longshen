package com.cdvcloud.douting.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.AnchorDao;
import com.cdvcloud.douting.domain.QueryBasicObject;
import com.cdvcloud.douting.service.AnchorAppService;
import com.cdvcloud.rms.util.Pages;
/**
 * @date 2017/11/12
 */
@Service
public class AnchorAppServiceImpl extends BaseServiceImpl implements AnchorAppService {
	
	 @Autowired
	 private AnchorDao anchorDao;

	/**
	 * 分页查询主播
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pages queryAnchorByApp(CommonParameters commonParameters, Map<String, Object> param) {
		
		 // 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>(16);

        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));
        // 排序
        Map<String, Object> sortMap = new HashMap<String, Object>(16);
        sortMap.put(QueryBasicObject.SORT, param.get(QueryBasicObject.SORT));
        // 返回字段
        Map<String, Object> backMap = new HashMap<String, Object>(16);
        	Map<String, Object> backData = new HashMap<String, Object>(16);
        	backData.put(QueryBasicObject.ID, 1);
        	backData.put(QueryBasicObject.THUMBANAILURL, 1);
        	backData.put(QueryBasicObject.NAME, 1);
        	backData.put(QueryBasicObject.ANCHORCOLUMN, 1);
        	backData.put(QueryBasicObject.CTIME, 1);
        	backData.put(QueryBasicObject.REMARK, 1);
        	backMap.put(QueryBasicObject.BACKPARAM, backData);
        // 当前页
        Map<String, Object> currentPageMap = new HashMap<String, Object>(16);
        currentPageMap.put(QueryBasicObject.CURRENTPAGE, param.get(QueryBasicObject.CURRENTPAGE));
        // 每页条数
        Map<String, Object> pageNumMap = new HashMap<String, Object>(16);
        pageNumMap.put(QueryBasicObject.PAGENUM, param.get(QueryBasicObject.PAGENUM));

        Pages page =  anchorDao.queryAnchor4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
        List<Map<String, Object>> anchors =(List<Map<String, Object>>) page.getResults();
        Integer pageNum = null;
        if(param.get(QueryBasicObject.PAGENUM) !=null){
			
			pageNum =Integer.valueOf(String.valueOf(param.get(QueryBasicObject.PAGENUM)));
		}
		//总页数  不查为0
		int totalPage = anchors.size()/pageNum==0?(anchors.size()/pageNum):(anchors.size()/pageNum+1);
		page.setTotalPage((long) totalPage);
		return page;
	}

}
