package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;

public interface DownloadHistoryAppService extends BaseService{

	/**
	 *  逗看首页查询
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> doukanHome(CommonParameters commonParameters,Map<String, Object> param);
	/**
	 *  逗看直播间栏图文直播内容查询
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> queryPictureTexts(CommonParameters commonParameters,Map<String, Object> param);
	/**
	 *  根据ID查询逗看直播间下的商品
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String, Object> queryGoodsShows(CommonParameters commonParameters,Map<String, Object> param);
}
