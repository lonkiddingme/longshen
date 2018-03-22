package com.cdvcloud.douting.service.impl;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.DownloadHistoryDao;
import com.cdvcloud.douting.domain.DownloadHistory;
import com.cdvcloud.douting.service.DownloadHistoryService;
import com.cdvcloud.rms.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下载历史管理
 *
 * @author SongYuanKun
 */
@Service
public class DownloadHistoryServiceImpl extends BaseServiceImpl implements DownloadHistoryService {
    @Autowired
    private DownloadHistoryDao downloadHistoryDao;

    /**
     * 创建下载历史
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 下载历史ID
     */
    @Override
    public String createDownloadHistory(CommonParameters commonParameters, Map<String, Object> param) {
         /*创建下载历史Map*/
        Map<String, Object> downloadHistoryMap = new HashMap<String, Object>(16);

        List<String> list = Arrays.asList(DownloadHistory.DOCID, DownloadHistory.TITLE, DownloadHistory.FANSID, DownloadHistory.FANSNAME);
        /*更新信息*/
        downloadHistoryMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*创建信息*/
        downloadHistoryMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));

        return downloadHistoryDao.createDownloadHistory(downloadHistoryMap);
    }

    /**
     * 更新下载历史
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    @Override
    public long updateDownloadHistoryById(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> update = new HashMap<String, Object>(16);
        List<String> list = Arrays.asList(DownloadHistory.DOCID, DownloadHistory.TITLE, DownloadHistory.FANSID,DownloadHistory.FANSNAME);


        /*更新信息*/
        update.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));

        String id = String.valueOf(param.get("id"));
        return downloadHistoryDao.updateDownloadHistoryById(id, update);
    }

    /**
     * 删除下载历史
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 删除条数
     */
    @SuppressWarnings("unchecked")
    @Override
    public long deleteDownloadHistory(CommonParameters commonParameters, Map<String, Object> param) {
        List<String> ids = (List<String>) param.get("ids");
        return downloadHistoryDao.deleteDownloadHistorys(ids);
    }

    /**
     * 查询下载历史，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 下载历史圈帖子
     */
    @Override
    public Map<String, Object> queryDownloadHistoryById(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return downloadHistoryDao.queryDownloadHistoryById(id);
    }

    /**
     * 查询下载历史，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public Pages queryDownloadHistory4Page(CommonParameters commonParameters, Map<String, Object> param) {
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

        return downloadHistoryDao.queryDownloadHistory4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
    }

    /**
     * 查询下载历史，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public Pages queryDownloadHistoryByFansId4Page(CommonParameters commonParameters, Map<String, Object> param) {
    	// 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>();
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

        return downloadHistoryDao.queryDownloadHistory4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
    }


    /**
     * 查询所有下载历史
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public List<Map<String, Object>> queryDownloadHistoryAll(CommonParameters commonParameters, Map<String, Object> param) {
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

        return downloadHistoryDao.queryDownloadHistoryAll(sortMap, queryMap, backMap);
    }

    /**
     * 查询下载历史数量
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public long countDownloadHistory(CommonParameters commonParameters, Map<String, Object> param) {
        // 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>(16);

        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));
        return downloadHistoryDao.countDownloadHistory(queryMap);
    }

    /**
     * 批量更新下载历史
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public long updateDownloadHistorys(CommonParameters commonParameters, Map<String, Object> param) {
        List<String> ids = (List<String>) param.get("ids");
        List<String> list = Arrays.asList(DownloadHistory.DOCID, DownloadHistory.TITLE, DownloadHistory.FANSID,DownloadHistory.FANSNAME);
        /*更新信息*/
        Map<String, Object> update = new HashMap<String, Object>(16);
        update.putAll(ValidateCommonParam.getFieldsMap(list, param));

        return downloadHistoryDao.updateDownloadHistorys(ids, update);
    }

}
