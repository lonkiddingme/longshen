package com.cdvcloud.douting.dao.impl;

import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.HomePageDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.douting.domain.HomePage;
import com.cdvcloud.rms.dao.BasicDao;
import com.cdvcloud.rms.dao.mongodb.QueryOperators;
import com.cdvcloud.rms.util.Pages;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页dao层
 *
 * @author SongYuanKun
 * @date 2017/11/1
 */
@Repository
public class HomePageDaoImpl implements HomePageDao {
    @Autowired
    private BasicDao basicDao;

    /**
     * 创建首页
     *
     * @param homePageMap 首页
     * @return 首页ID
     */
    @Override
    public String createHomePage(Map<String, Object> homePageMap) {
        return basicDao.insert(HomePage.HOMEPAGE, homePageMap);
    }

    /**
     * 通过Id更新首页
     *
     * @param id     更新首页的主键
     * @param update 更新数据
     * @return 更新条数
     */
    @Override
    public long updateHomePageById(String id, Map<String, Object> update) {
        Map<String, Object> queryFilter = new HashMap<String, Object>(16);
        queryFilter.put(HomePage.ID, new ObjectId(id));
        return basicDao.updateOneBySet(HomePage.HOMEPAGE, queryFilter, update);
    }

    /**
     * 删除首页
     *
     * @param ids 需要删除首页的主键
     * @return 删除条数
     */
    @Override
    public long deleteHomePage(String id) {
        Map<String, Object> deleteFilter = new HashMap<String, Object>();
        deleteFilter.put(HomePage.ID, new ObjectId(id));
        return basicDao.deleteOne(HomePage.HOMEPAGE, deleteFilter);
    }

    /**
     * 查询首页，通过Id查询
     *
     * @param id 首页id
     * @return 首页
     */
    @Override
    public Map<String, Object> queryHomePageById(String id) {
        return basicDao.findOne(HomePage.HOMEPAGE, id);
    }

    /**
     * 批量更新首页
     *
     * @param ids    需要更新的主键
     * @param update 更新字段
     * @return 更新条数
     */
    @Override
    public long updateHomePages(List<String> ids, Map<String, Object> update) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>(16);
        filter.put(BasicObject.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.updateManyBySet(HomePage.HOMEPAGE, filter, update, true);
    }


    /**
     * 首页分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    @Override
    public Pages queryHomePage4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap) {
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

        List<Map<String, Object>> results = basicDao.find(HomePage.HOMEPAGE, sortFilter, queryFilter, backFilter, currentPage, pageNum);
        long totalRecord = basicDao.count(HomePage.HOMEPAGE, queryFilter);
        return new Pages(pageNum, totalRecord, currentPage, results);

    }

    @Override
    public long countHomePage(Map<String, Object> queryMap) {
        /// 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        return basicDao.count(HomePage.HOMEPAGE, queryFilter);
    }


    /**
     * 发布
     */
    @Override
    public long releaseHomePage(List<Map<String, Object>> map) {
        basicDao.insertMaps(HomePage.HOMEPAGE, map);
        return map.size();
    }

    /**
     * 查询需要发布的临时状态数据
     */
    @Override
    public List<Map<String, Object>> queryHomePageForRelease(List<String> ids, Map<String, Object> sortMap, Map<String, Object> backMap) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        Map<String, Object> queryFilter = new HashMap<String, Object>();
        queryFilter.put(HomePage.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.findAllObject(HomePage.HOMEPAGE, queryFilter, backFilter, sortFilter);
    }

    /**
     * 删除同类型的正式首页
     */
    @Override
    public long deleteRelease(String type) {
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put(HomePage.TYPE, type);
        filter.put(HomePage.PUSHSTATE, Constants.IS_RELEASED);
        return basicDao.deleteMany(HomePage.HOMEPAGE, filter);
    }

    /**
     * 启动页
     */
    @Override
    public String createStartUpHomePage(String type, Map<String, Object> queryMap) {

        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put(HomePage.TYPE, type);
        basicDao.updateOneBySet(HomePage.HOMEPAGE, filter, queryMap, true);
        return (String) basicDao.findOne(HomePage.HOMEPAGE, filter).get("_id");
    }

    @Override
    public Map<String, Object> queryHomePage(Map<String, Object> queryMap) {

        return basicDao.findOne(HomePage.HOMEPAGE, queryMap);
    }
}
