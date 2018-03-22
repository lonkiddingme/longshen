package com.cdvcloud.douting.service.impl;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.ColumnDao;
import com.cdvcloud.douting.domain.Column;
import com.cdvcloud.douting.domain.Global;
import com.cdvcloud.douting.service.ColumnService;
import com.cdvcloud.rms.dao.ConfigurationDao;
import com.cdvcloud.rms.util.JSONUtils;
import com.cdvcloud.rms.util.Pages;
import com.cdvcloud.rms.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author SongYuanKun
 * @date 2017/10/31
 */
@Service
public class ColumnServiceImpl extends BaseServiceImpl implements ColumnService {

    private static final Logger logger = Logger.getLogger("ValidateCommonParam");

    @Autowired
    private ColumnDao columnDao;
    @Autowired
    private ConfigurationDao configurationDao;
    @Autowired
    private HttpCommonUtil httpCommonUtil;

    /**
     * 创建栏目
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 栏目ID
     */
    @Override
    public String createColumn(CommonParameters commonParameters, Map<String, Object> param) {
         /*创建栏目Map*/
        Map<String, Object> columnMap = new HashMap<String, Object>(16);
        List<String> list = Arrays.asList(Column.NAME, Column.THUMBNAILURL, Column.STATUS, Column.STATUS_ZH, Column.WORDMARKID, Column.WORDMARKNAME, Column.USERTYPE, Column.ANCHORLIST,Column.TYPE ,Column.REMARK);
        columnMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

        columnMap.put(Column.LOONNUM, Constants.ZERO);
        columnMap.put(Column.FOLLOWNUM, Constants.ZERO);

        /*创建信息*/
        columnMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));
        return columnDao.createColumn(columnMap);
    }

    /**
     * 更新栏目
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    @Override
    public long updateColumnById(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> update = new HashMap<String, Object>(16);
        List<String> list = Arrays.asList(Column.NAME, Column.THUMBNAILURL, Column.STATUS, Column.TYPE,Column.STATUS_ZH, Column.WORDMARKID, Column.WORDMARKNAME, Column.ANCHORLIST, Column.FOLLOWNUM, Column.LOONNUM, Column.REMARK);
        update.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));

        String id = String.valueOf(param.get("id"));
        return columnDao.updateColumnById(id, update);
    }

    /**
     * 删除栏目
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 删除条数
     */
    @SuppressWarnings("unchecked")
	@Override
    public long deleteColumn(CommonParameters commonParameters, Map<String, Object> param) {
        List<String> ids = (List<String>) param.get("ids");
        return columnDao.deleteColumns(ids);
    }

    /**
     * 查询栏目，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 栏目圈帖子
     */
    @Override
    public Map<String, Object> queryColumnById(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return columnDao.queryColumnById(id);
    }

    /**
     * 查询栏目，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public Pages queryColumn4Page(CommonParameters commonParameters, Map<String, Object> param) {
        // 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>(16);

        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));
        // 排序
        Map<String, Object> sortMap = new HashMap<String, Object>(16);
        sortMap.put("sort", param.get("sort"));
        // 返回字段
        Map<String, Object> backMap = new HashMap<String, Object>(16);
        backMap.put("backParam", param.get("backParam"));
        // 当前页
        Map<String, Object> currentPageMap = new HashMap<String, Object>(16);
        currentPageMap.put("currentPage", param.get("currentPage"));
        // 每页条数
        Map<String, Object> pageNumMap = new HashMap<String, Object>(16);
        pageNumMap.put("pageNum", param.get("pageNum"));
        return columnDao.queryColumn4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);

    }

    /**
     * 更新栏目状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public long updateColumnStatus(CommonParameters commonParameters, Map<String, Object> param) {
        List<String> ids = (List<String>) param.get("ids");
        Map<String, Object> update = new HashMap<String, Object>(16);
        update.putAll(ValidateCommonParam.getFieldsMap(Arrays.asList(Column.STATUS, Column.STATUS_ZH), param));

         /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));

        return columnDao.updateColumns(ids, update);
    }

    /**
     * 获取词标列表
     *
     * @param commonParameters commonParameters
     * @param jsonMap          请求参数
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<Map<String, Object>> findList(CommonParameters commonParameters, Map<String, Object> jsonMap) throws Exception {
        String url = configurationDao.getConfigValue(Global.ALLMEDIA_URL, Global.GLOBAL) + commonParameters.getCompanyId() + "/" + commonParameters.getAppCode() + "/" + commonParameters.getUserId() + "/" + commonParameters.getServiceCode() + "/api4wx/findcb/";
        List<Map<String, Object>> cblist = new ArrayList<Map<String, Object>>();
        jsonMap.put(CommonParameters.ACCESSTOKEN, "accessToken");
        jsonMap.put(CommonParameters.TIMESTAMP, System.currentTimeMillis());
        logger.info("调用词标接口：" + url + ";参数：" + jsonMap);
        String ret = httpCommonUtil.doPost(url, jsonMap);
        logger.info("调用词标接口返回：" + ret);
        if (!StringUtil.isEmpty(ret)) {
            Map<String, Object> map = JSONUtils.json2map(ret);
            if (map.containsKey("status") && "0".equals(String.valueOf(map.get("status")))) {
                cblist = (List<Map<String, Object>>) map.get("result");
            }
        }
        return cblist;
    }
}
