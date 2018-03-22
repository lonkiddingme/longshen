package com.cdvcloud.douting.domain;

/**
 * 逗秀-主播
 * Anchor
 *
 * @author wlf
 * @Description
 * @data 2017-10-24 下午5:39:29
 */
public class Anchor extends BasicObject {
    /**
     * 表名
     **/
    public static final String ANCHOR = "anchor";
    /**
     * 主播名称
     **/
    public static final String NAME = "name";
    /**
     * 主播缩略图
     **/
    public static final String THUMBNAILURL = "thumbnailUrl";
    /**
     * 主播所属节目
     */
    public static final String ANCHORCOLUMN = "anchorColumn";
    /**
     * 发帖数
     **/
    public static final String POSTNUM = "postNum";
    /**
     * 状态 （上线/下线）默认：0下线
     **/
    public static final String STATUS = "status";
    public static final String STATUS_ZH = "status_zh";
    /**
     * 管理用户列表
     **/
    public static final String USERLIST = "userList";
    /**
     * 排序权重
     */
    public static final String WEIGHT = "weight";
    /**
     * 描述
     **/
    public static final String REMARK = "remark";
}
