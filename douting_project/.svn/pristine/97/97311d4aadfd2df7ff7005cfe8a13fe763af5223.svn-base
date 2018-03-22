package com.cdvcloud.douting.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.ValidateCommonParam;
import com.cdvcloud.douting.dao.PictureTextDao;
import com.cdvcloud.douting.domain.PictureText;
import com.cdvcloud.douting.service.PictureTextService;
import com.cdvcloud.rms.util.Pages;

/**
 * 逗看图文直播管理
 *
 * @author zhangyuelong
 */

@Service
public class PictureTextServiceImpl extends BaseServiceImpl implements PictureTextService {

    @Autowired
    private PictureTextDao pictureTextDao;

    /**
     * 创建逗看图文直播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    @Override
    public String createPictureText(CommonParameters commonParameters, Map<String, Object> param) {
        Map<String, Object> pictureTextMap = new HashMap<String, Object>();

		/*图文直播信息*/

        List<String> list = Arrays.asList(PictureText.PUSHERNAME, PictureText.PUSHERIDENTITY, PictureText.PUSHERPICTURE, PictureText.CONTENT, PictureText.PICTUREURL, PictureText.VIDEOROOMID, PictureText.VIDEOROOMNAME, PictureText.USERTYPE);
         /*更新信息*/
        pictureTextMap.putAll(ValidateCommonParam.getFieldsMap(list, param));

        /*创建信息*/
        pictureTextMap.putAll(ValidateCommonParam.getCreateMessage(commonParameters));

        return pictureTextDao.createPictureText(pictureTextMap);
    }

    /**
     * 删除逗看图文直播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    @Override
    public long deletePictureText(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return pictureTextDao.deletePictureText(id);
    }

    /**
     * 更新逗看图文直播
     * 目前没有更新功能
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    @Override
    public long updatePictureText(CommonParameters commonParameters, Map<String, Object> param) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * 按ID查询逗看图文直播
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    @Override
    public Map<String, Object> queryPictureTextById(CommonParameters commonParameters, Map<String, Object> param) {
        String id = String.valueOf(param.get("id"));
        return pictureTextDao.queryPictureTextById(id);
    }

    /**
     * 分页查询逗看图文直播
     * 目前没有更新功能
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    @Override
    public Pages queryPictureText4Page(CommonParameters commonParameters, Map<String, Object> param) {
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
        return pictureTextDao.queryPictureText4Page(sortMap, queryMap, backMap, currentPageMap, pageNumMap);
    }


    /**
     * 修改逗看图文直播状态
     * 目前没有此功能
     *
     * @param commonParameters commonParameters
     * @param param            请求参数
     * @return 直播间ID
     */
    @Override
    public long updatePictureTextStatus(CommonParameters commonParameters, Map<String, Object> param) {
        // TODO Auto-generated method stub
        return 0;
    }

}
