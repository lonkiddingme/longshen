package com.cdvcloud.douting.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.GoodsShowDao;
import com.cdvcloud.douting.domain.GoodsShow;
import com.cdvcloud.douting.service.GoodsShowService;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗看商品栏管理
 *
 * @author zhangyuelong
 */

@Service
public class GoodsShowServiceImpl extends BaseServiceImpl implements GoodsShowService {

    @Autowired
    private GoodsShowDao goodsShowDao;

    /**
     * 创建逗看商品栏
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    @Override
    public String createGoodsShow(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> goodsShowMap = new HashMap<String, Object>();
        /*商品栏信息*/

        List<String> list = Arrays.asList(GoodsShow.name, GoodsShow.price, GoodsShow.GOODSURL, GoodsShow.GOODSPICTURE, GoodsShow.VIDEOROOMID, GoodsShow.VIDEOROOMNAME, GoodsShow.USERTYPE);
		 /*更新信息*/
        goodsShowMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*创建信息*/
        goodsShowMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));
        return goodsShowDao.createGoodsShow(goodsShowMap);
    }

    /**
     * 删除逗看商品栏
     */
    @Override
    public long deleteGoodsShow(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return goodsShowDao.deleteGoodsShow(id);
    }

    /**
     * 更新逗看商品栏
     */
    @Override
    public long updateGoodsShow(CommonParameters commonParameters, Map<String, Object> param) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * ID查询逗看商品栏
     */
    @Override
    public Map<String, Object> queryGoodsShowById(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return goodsShowDao.queryGoodsShowById(id);
    }

    /**
     * 分页查询逗看商品栏
     */
    @Override
    public Pages queryGoodsShow4Page(CommonParameters commonParameters, Map<String, Object> param) {
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
        return goodsShowDao.queryGoodsShow4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
    }

    @Override
    public long updateGoodsShowStatus(CommonParameters commonParameters, Map<String, Object> param) {
        // TODO Auto-generated method stub
        return 0;
    }
}
