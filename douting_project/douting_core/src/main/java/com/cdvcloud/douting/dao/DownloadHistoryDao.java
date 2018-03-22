package com.cdvcloud.douting.dao;

import com.cdvcloud.rms.util.Pages;

import java.util.List;
import java.util.Map;

/**
 * Created by SongYuanKun on 2017/11/13.
 */
public interface DownloadHistoryDao {
    String createDownloadHistory(Map<String, Object> downloadHistoryMap);

    long updateDownloadHistoryById(String id, Map<String, Object> update);

    long deleteDownloadHistorys(List<String> ids);

    Map<String, Object> queryDownloadHistoryById(String id);

    Pages queryDownloadHistory4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap);

    long updateDownloadHistorys(List<String> ids, Map<String, Object> update);

    List<Map<String, Object>> queryDownloadHistoryAll(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap);

    long countDownloadHistory(Map<String, Object> queryMap);

    List<Map<String, Object>> queryDownloadHistorys(List<String> ids, Map<String, Object> backMap, Map<String, Object> sortMap);
}
