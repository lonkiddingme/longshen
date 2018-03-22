package com.cdvcloud.douting.domain;

/**
 * 逗秀-内容
 * Content
 *
 * @author wlf
 * @Description
 * @data 2017-10-24 下午6:22:37
 */
public class Content extends BasicObject{
    /**
     * 表名
     **/
    public static final String CONTENT = "content";
    /**
     * 主键ID
     **/
    public static final String ID = "_id";
    /**
     * 内容的id
     **/
    public static final String DOCID = "docId";
    /**
     * 内容的标题
     **/
    public static final String TITLE = "title";
    /**
     * 缩略图
     **/
    public static final String THUMBNAILURL = "thumbnail";
    /**
     * 查看数
     **/
    public static final String LOOKNUM = "lookNum";
    /**
     * 点赞数
     **/
    public static final String LIKENUM = "likeNum";
    /**
     * 评论数
     **/
    public static final String COMMENTNUM = "commentNum";
    /**
     * 发布时间
     **/
    public static final String PUSHTIME = "pushTime";
    /**
     * 排序权重
     */
    public static final String WEIGHT = "weight";
    /**
     * 上下架状态 0上架；1下架
     **/
    public static final String STATUS = "status";
    public static final String STATUS_ZH = "status_zh";
    public static final String UTIME = "utime";
    public static final String UTIME_LONG = "utime_Long";

    public static final String ISCOMMENT = "isComment";
    
    public static final String NEWESTCOMMENTTIME = "newest_commentTime";


    /**
     * 词标
     */
    public static final String CIBIAO = "cibiao";

    /**
     * 租户ID
     */
    public static final String COMPANYID = "companyId";
    /**
     * 类型
     */
    public static final String CLASSIFY = "classify";
    public static final String PUSHTIME_LONG = "pushTimeLong";
    public static final String WEIGTH = "weight";
}
