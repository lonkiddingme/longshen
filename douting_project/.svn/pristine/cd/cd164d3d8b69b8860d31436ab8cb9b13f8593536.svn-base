package com.cdvcloud.douting.dao.impl;

import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.AnchorDao;
import com.cdvcloud.douting.domain.Anchor;
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
 * 主播管理
 *
 * @author Administrator
 */
@Repository
public class AnchorDaoImpl implements AnchorDao {
    @Autowired
    private BasicDao basicDao;

    /**
     * 创建主播
     *
     * @param anchorMap 主播
     * @return 主播ID
     */
    @Override
    public String createAnchor(Map<String, Object> anchorMap) {
        return basicDao.insert(Anchor.ANCHOR, anchorMap);
    }

    /**
     * 更新主播
     *
     * @param id     更新主播的主键
     * @param update 更新数据
     * @return 更新条数
     */
    @Override
    public long updateAnchorById(String id, Map<String, Object> update) {
        /*查询条件*/
        Map<String, Object> queryFilter = new HashMap<String, Object>(16);
        queryFilter.put(Anchor.ID, new ObjectId(id));
        return basicDao.updateOneBySet(Anchor.ANCHOR, queryFilter, update);
    }

    /**
     * 删除主播
     *
     * @param ids 需要删除主播的主键
     * @return 删除条数
     */
    @Override
    public long deleteAnchors(List<String> ids) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>(16);
        filter.put(Anchor.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.deleteMany(Anchor.ANCHOR, filter);
    }

    /**
     * 查询主播，通过Id查询
     *
     * @param id 主播id
     * @return 主播圈帖子
     */
    @Override
    public Map<String, Object> queryAnchorById(String id) {

        return basicDao.findOne(Anchor.ANCHOR, id);
    }


    /**
     * 查询主播，分页查询
     *
     * @param sortMap        排序参数
     * @param queryMap       查询参数
     * @param backMap        返回参数
     * @param currentPageMap 当前页
     * @param pageNumMap     每页条数
     * @return 分页对象
     */
    @Override
    public Pages queryAnchor4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap) {
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
        List<Map<String, Object>> results = basicDao.find(Anchor.ANCHOR, sortFilter, queryFilter, backFilter, currentPage, pageNum);
        long totalRecord = basicDao.count(Anchor.ANCHOR, queryFilter);
        return new Pages(pageNum, totalRecord, currentPage, results);
    }

    /**
     * 批量更新主播
     *
     * @param ids    需要更新的主键
     * @param update 更新字段
     * @return
     */
    @Override
    public long updateAnchors(List<String> ids, Map<String, Object> update) {
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>(16);
        filter.put(Anchor.ID, new Document(QueryOperators.IN, objectIds));
        return basicDao.updateManyBySet(Anchor.ANCHOR, filter, update, true);
    }

    /**
     * 查询所有主播
     *
     * @param sortMap  排序字段
     * @param queryMap 请求字段
     * @param backMap  返回字段
     * @return 主播对象列表
     */
    @Override
    public List<Map<String, Object>> queryAnchorAll(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap) {
        // 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
        return basicDao.findAllObject(Anchor.ANCHOR, queryFilter, backFilter, sortFilter);
    }

    /**
     * 查询主播数量
     *
     * @param queryMap 请求字段
     * @return 个数
     */
    @Override
    public long countAnchor(Map<String, Object> queryMap) {
        // 查询参数抽取
        Map<String, Object> queryFilter = ValidateCommonParam.getWhereParam(queryMap);
        return basicDao.count(Anchor.ANCHOR, queryFilter);
    }

    /**
     * 批量查询主播
     */
	@Override
	public List<Map<String, Object>> queryAnchors(List<String> ids,Map<String, Object> backMap,Map<String, Object> sortMap) {
		List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> queryFilter = new HashMap<String, Object>();
        queryFilter.put(Anchor.ID, new Document(QueryOperators.IN, objectIds));
     // 排序
        Map<String, Object> sortFilter = ValidateCommonParam.getSortMap(sortMap);
        // 返回字段
        Map<String, Object> backFilter = ValidateCommonParam.getBackMap(backMap);
		return basicDao.findAllObject(Anchor.ANCHOR, queryFilter, backFilter, sortFilter);
	}
}
