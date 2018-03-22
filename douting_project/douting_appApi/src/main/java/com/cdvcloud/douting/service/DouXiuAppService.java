package com.cdvcloud.douting.service;

import java.util.Map;

import com.cdvcloud.douting.common.CommonParameters;

public interface DouXiuAppService extends BaseService{

	/**
	 *  逗秀首页查询
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> douXiuHome(CommonParameters commonParameters,Map<String, Object> param);
	/**
	 *  逗秀栏目下 内容查询
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> queryColumnContents(CommonParameters commonParameters,Map<String, Object> param);
	/**
	 *  根据ID查询逗秀节目栏
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> queryColumn(CommonParameters commonParameters,Map<String, Object> param);
	
	/**
	 *  根据DOCID查询逗秀内容详情
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> queryContent(CommonParameters commonParameters,Map<String, Object> param)throws Exception;;



	/**
	 * 根据类型查询分页查询逗秀栏目列表
	 * @param commonParameters
	 * @param param
	 * @return
	 */
	Map<String,Object> queryColumnByType(CommonParameters commonParameters,Map<String, Object> param);
}
