package com.cdvcloud.douting.domain;

/**
 * 逗播-栏目
 * BroadcastColumn
 *
 * @author wlf
 * @Description
 * @data 2017-10-24 下午8:32:03
 */
public class BroadcastColumn extends BasicObject {
    /**
     * 表名
     **/
    public static final String BROADCASTCOLUMN = "broadcastColumn";
    /**
     * 广播栏目名称
     **/
    public static final String NAME = "name";
    /**
     * 广播栏目缩略图
     **/
    public static final String THUMBNAILURL = "thumbnailUrl";
    /**
     * 开始时间
     **/
    public static final String STARTTIME = "startTime";
    /**
     * 结束时间
     **/
    public static final String ENDTIME = "endTime";
    /**
     * 广播直播间id
     **/
    public static final String BROADCASTID = "broadcastId";
    /**
     * 广播直播间名称
     **/
    public static final String BROADCASTNAME = "broadcastName";
    /**
     * 状态 （上线/下线）默认：0下线
     **/
    public static final String STATUS = "status";
    public static final String STATUS_ZH = "status_zh";
}
