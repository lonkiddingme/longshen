package com.cdvcloud.douting.service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.rms.util.Pages;

import java.util.List;
import java.util.Map;

/**
 * Created by SongYuanKun on 2017/11/13.
 */
public interface DownloadHistoryService extends BaseService {
    String createDownloadHistory(CommonParameters commonParameters, Map<String, Object> param);

    long updateDownloadHistoryById(CommonParameters commonParameters, Map<String, Object> param);

    long deleteDownloadHistory(CommonParameters commonParameters, Map<String, Object> param);

    Map<String, Object> queryDownloadHistoryById(CommonParameters commonParameters, Map<String, Object> param);

    Pages queryDownloadHistory4Page(CommonParameters commonParameters, Map<String, Object> param);

    Pages queryDownloadHistoryByFansId4Page(CommonParameters commonParameters, Map<String, Object> param);

    List<Map<String, Object>> queryDownloadHistoryAll(CommonParameters commonParameters, Map<String, Object> param);

    long countDownloadHistory(CommonParameters commonParameters, Map<String, Object> param);

    long updateDownloadHistorys(CommonParameters commonParameters, Map<String, Object> param);
}
