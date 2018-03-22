package com.cdvcloud.douting.service.impl;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.domain.Content;
import com.cdvcloud.douting.domain.Global;
import com.cdvcloud.douting.service.BigDataService;
import com.cdvcloud.rms.dao.ConfigurationDao;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SongYuanKun
 * @date 2017/10/31
 */
@Service
public class BigDataServiceImpl extends BaseServiceImpl implements BigDataService {
    @Autowired
    private ConfigurationDao configurationDao;
    @Autowired
    private HttpCommonUtil httpCommonUtil;

    /**
     * 通过docId查询大数据的数据
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return 单个文稿数据
     * @throws Exception 字符串转换成Map异常
     */
    @SuppressWarnings("unchecked")
	@Override
    public Map<String, Object> queryBigDataByDocId(CommonParameters commonParameters, Map<String, Object> param) throws Exception {
        String docid = String.valueOf(param.get(Content.DOCID));
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);
        jsonMap.put(CommonParameters.ACCESSTOKEN, "accessToken");
        jsonMap.put(CommonParameters.TIMESTAMP, System.currentTimeMillis());
        jsonMap.put("docid", docid);
        String httpUrl = configurationDao.getConfigValue(Global.ENGINE_URL, Global.GLOBAL) + commonParameters.getCompanyId() + "/" + commonParameters.getAppCode() + "/" + commonParameters.getUserId() + "/" + commonParameters.getServiceCode() + "/" + "v1/api/bdp/findContentById";
        String resp = httpCommonUtil.doPost(httpUrl, jsonMap);
        Map<String, Object> respMap = JSONUtils.json2map(resp);
        String code = String.valueOf(respMap.get("code"));
        Map<String, Object> data = new HashMap<String, Object>(16);
        if (Constants.SZERO.equals(code)) {
            data = (Map<String, Object>) respMap.get("data");
        }
        return data;
    }

    /**
     * 通过词标查询大数据的数据
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return 分页数据
     * @throws Exception 字符串转换成Map异常
     */
    @SuppressWarnings("unchecked")
	@Override
    public Pages queryBigDataByWordMarkId(CommonParameters commonParameters, Map<String, Object> param) throws Exception {
        String cbid = String.valueOf(param.get("cbid"));
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);
        jsonMap.put(CommonParameters.ACCESSTOKEN, "accessToken");
        jsonMap.put(CommonParameters.TIMESTAMP, System.currentTimeMillis());
        Map<String, Object> conditionParam = new HashMap<String, Object>(16);
        conditionParam.put("cibiao.cbid", cbid);
        jsonMap.put("conditionParam", conditionParam);
        String httpUrl = configurationDao.getConfigValue(Global.ENGINE_URL, Global.GLOBAL) + commonParameters.getCompanyId() + "/" + commonParameters.getAppCode() + "/" + commonParameters.getUserId() + "/" + commonParameters.getServiceCode() + "/" + "v1/api/bdp/findContent";
        String resp = httpCommonUtil.doPost(httpUrl, jsonMap);
        Map<String, Object> respMap = JSONUtils.json2map(resp);
        String code = String.valueOf(respMap.get("code"));
        Pages pages = new Pages();
        if (Constants.SZERO.equals(code)) {
            Map<String, Object> data = (Map<String, Object>) respMap.get("data");
            List<Map<String, Object>> results = (List<Map<String, Object>>) data.get("results");
            Integer pageNum = (Integer) respMap.get("pageNum");
            Long totalRecord = (Long) respMap.get("totalRecord");
            Integer currentPage = (Integer) respMap.get("currentPage");
            pages = new Pages(pageNum, totalRecord, currentPage, results);
        }
        return pages;
    }
}
