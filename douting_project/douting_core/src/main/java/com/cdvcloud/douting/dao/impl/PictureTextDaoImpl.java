package com.cdvcloud.douting.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.PictureTextDao;
import com.cdvcloud.douting.domain.PictureText;
import com.cdvcloud.rms.dao.BasicDao;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗看视频直播间管理
 */
@Repository
public class PictureTextDaoImpl implements PictureTextDao {

    @Autowired
    private BasicDao basicDao;

    /**
     * 创建逗看图文直播
     */
    @Override
    public String createPictureText(Map<String, Object> pictureTextMap) {
        return basicDao.insert(PictureText.PICTURETEXT, pictureTextMap);
    }

    /**
     *公告，直播间只有一条
     *
     *发布者身份和直播间ID 都是直播间ID 表示为公告
     * @ 
     */
    @Override
    public long updatePictureText(Map<String, Object> update) {
    	Map<String, Object> filter = new HashMap<String, Object>();
    	filter.put("videoRoomId", update.get("videoRoomId"));
    	filter.put("pusherIdentity", update.get("pusherIdentity"));
    	return basicDao.updateOneBySet(PictureText.PICTURETEXT, filter, update, true);
    }
    /**
     * 删除逗看图文直播
     */
    @Override
    public long deletePictureText(String id) {
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put(PictureText.ID, new ObjectId(id));
        return basicDao.deleteOne(PictureText.PICTURETEXT, filter);
    }

    /**
     * 主键ID查询逗看图文直播
     */
    @Override
    public Map<String, Object> queryPictureTextById(String id) {
        return basicDao.findOne(PictureText.PICTURETEXT, id);
    }

    /**
     * 分页查询逗看图文直播
     */
    @Override
    public Pages queryPictureText4Page(Map<String, Object> sortMap, Map<String, Object> queryMap, Map<String, Object> backMap, Map<String, Object> currentPageMap, Map<String, Object> pageNumMap) {
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

        List<Map<String, Object>> results = basicDao.find(PictureText.PICTURETEXT, sortFilter, queryFilter, backFilter, currentPage, pageNum);
        long totalRecord = basicDao.count(PictureText.PICTURETEXT, queryFilter);
        return new Pages(pageNum, totalRecord, currentPage, results);
    }

    /**
     * 更新逗看图文直播状态
     *
     * @ 目前没有此功能
     */
    @Override
    public long updatePictureTextStatus(String id, Map<String, Object> param) {
        // TODO Auto-generated method stub
        return 0;
    }


}
