package com.cdvcloud.douting.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.GoodsShowDao;
import com.cdvcloud.douting.domain.GoodsShow;
import com.cdvcloud.rms.dao.BasicDao;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗看商品栏管理
 */
@Repository
public class GoodsShowDaoImpl implements GoodsShowDao {

    @Autowired
    private BasicDao basicDao;

    /**
     * 创建逗看商品栏
     */
    @Override
    public String createGoodsShow(Map<String, Object> goodsShowMap) {

        return basicDao.insert(GoodsShow.GOODSSHOW, goodsShowMap);
    }

    @Override
    public long updateGoodsShow(String id, Map<String, Object> update) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * 删除逗看商品栏
     */
    @Override
    public long deleteGoodsShow(String id) {
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put(GoodsShow.ID, new ObjectId(id));
        return basicDao.deleteOne(GoodsShow.GOODSSHOW, filter);
    }

    /**
     * 按ID查询逗看商品栏
     */
    @Override
    public Map<String, Object> queryGoodsShowById(String id) {
        // TODO Auto-generated method stub
        return basicDao.findOne(GoodsShow.GOODSSHOW, id);
    }

    /**
     * 分页查询逗看商品栏
     */
    @Override
    public Pages queryGoodsShow4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap) {
        // 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        // 当前页
        Integer currentPage = ValidateCommonParam.getCurrentPage(currentPageMap);
        // 每页条数
        Integer pageNum = ValidateCommonParam.getPageNum(pageNumMap);

        List<Map<String, Object>> results = basicDao.find(GoodsShow.GOODSSHOW, sortFilter, queryFilter, backFilter, currentPage, pageNum);
        long totalRecord = basicDao.count(GoodsShow.GOODSSHOW, queryFilter);
        return new Pages(pageNum, totalRecord, currentPage, results);
    }

    @Override
    public long updateGoodsShowStatus(String id, Map<String, Object> param) {
        // TODO Auto-generated method stub
        return 0;
    }

}
