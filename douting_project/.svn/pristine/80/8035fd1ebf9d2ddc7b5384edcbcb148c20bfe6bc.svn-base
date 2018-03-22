package com.cdvcloud.douting.service.impl;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.AnchorDao;
import com.cdvcloud.douting.domain.Anchor;
import com.cdvcloud.douting.service.AnchorService;
import com.cdvcloud.rms.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主播管理
 *
 * @author SongYuanKun
 */
@Service
public class AnchorServiceImpl extends BaseServiceImpl implements AnchorService {
    @Autowired
    private AnchorDao anchorDao;

    /**
     * 创建主播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 主播ID
     */
    @Override
    public String createAnchor(CommonParameters commonParameters, Map<String, Object> param) {
         /*创建主播Map*/
        Map<String, Object> anchorMap = new HashMap<String, Object>(16);

        List<String> list = Arrays.asList(Anchor.NAME, Anchor.THUMBNAILURL, Anchor.STATUS, Anchor.STATUS_ZH, Anchor.USERLIST, Anchor.USERTYPE, Anchor.ANCHORCOLUMN, Anchor.WEIGHT, Anchor.REMARK);
        /*更新信息*/
        anchorMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*创建信息*/
        anchorMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));

        /*主播基础查询*/
        anchorMap.put(Anchor.POSTNUM, Constants.ZERO);

        return anchorDao.createAnchor(anchorMap);
    }

    /**
     * 更新主播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 更新条数
     */
    @Override
    public long updateAnchorById(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> update = new HashMap<String, Object>(16);
        List<String> list = Arrays.asList(Anchor.NAME, Anchor.THUMBNAILURL, Anchor.STATUS, Anchor.STATUS_ZH, Anchor.USERLIST, Anchor.ANCHORCOLUMN, Anchor.POSTNUM, Anchor.WEIGHT, Anchor.REMARK);

        /*更新信息*/
        update.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));

        String id = String.valueOf(param.get("id"));
        return anchorDao.updateAnchorById(id, update);
    }

    /**
     * 删除主播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 删除条数
     */
    @SuppressWarnings("unchecked")
    @Override
    public long deleteAnchor(CommonParameters commonParameters, Map<String, Object> param) {
        List<String> ids = (List<String>) param.get("ids");
        return anchorDao.deleteAnchors(ids);
    }

    /**
     * 查询主播，通过Id查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 主播圈帖子
     */
    @Override
    public Map<String, Object> queryAnchorById(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return anchorDao.queryAnchorById(id);
    }

    /**
     * 查询主播，分页查询
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public Pages queryAnchor4Page(CommonParameters commonParameters, Map<String, Object> param) {
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

        return anchorDao.queryAnchor4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
    }

    /**
     * 更新主播状态
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public long updateAnchorStatus(CommonParameters commonParameters, Map<String, Object> param) {
        List<String> ids = (List<String>) param.get("ids");
        Integer status = (Integer) param.get(Anchor.STATUS);
        String status_zh = String.valueOf(param.get(Anchor.STATUS_ZH));

        Map<String, Object> update = new HashMap<String, Object>(16);
        update.put(Anchor.STATUS, status);
        update.put(Anchor.STATUS_ZH, status_zh);

         /*更新人信息*/
        update.putAll(ValidateCommonParam.getUpdateMessage(commonParameters));

        return anchorDao.updateAnchors(ids, update);
    }

    /**
     * 查询所有主播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public List<Map<String, Object>> queryAnchorAll(CommonParameters commonParameters, Map<String, Object> param) {
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

        return anchorDao.queryAnchorAll(sortMap, queryMap, backMap);
    }

    /**
     * 查询主播数量
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 分页对象
     */
    @Override
    public long countAnchor(CommonParameters commonParameters, Map<String, Object> param) {
        // 查询参数抽取
        Map<String, Object> queryMap = new HashMap<String, Object>(16);

        List<String> queryList = Arrays.asList("keyWord", "keyValue", "keyTime", "startTime", "endTime", "conditionParam");
        /*更新信息*/
        queryMap.putAll(ValidateCommonParam.getFieldsMap(queryList, param));
        return anchorDao.countAnchor(queryMap);
    }

    /**
     * 批量更新主播
     *
     * @param commonParameters commonParameters
     * @param param            参数
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public long updateAnchors(CommonParameters commonParameters, Map<String, Object> param) {
        List<String> ids = (List<String>) param.get("ids");
        List<String> list = Arrays.asList(Anchor.NAME, Anchor.THUMBNAILURL, Anchor.STATUS, Anchor.STATUS_ZH, Anchor.USERLIST, Anchor.ANCHORCOLUMN, Anchor.WEIGHT, Anchor.REMARK);
        /*更新信息*/
        Map<String, Object> update = new HashMap<String, Object>(16);
        update.putAll(ValidateCommonParam.getFieldsMap(list, param));

        return anchorDao.updateAnchors(ids, update);
    }

}
