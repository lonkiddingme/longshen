package com.cdvcloud.douting.domain;

/**
 * 基础类，被其它实体继承
 * BasicObject
 *
 * @author wlf
 * @Description
 * @data 2017-10-23 上午10:03:05
 */
public class BasicObject {
    /**
     * 主键
     **/
    public static final String ID = "_id";
    /**
     * 创建人id
     **/
    public static final String USERID = "userId";
    /**
     * 创建人名称
     **/
    public static final String USERNAME = "userName";
    /**
     * 创建人时间
     **/
    public static final String CTIME = "ctime";
    public static final String CTIMELONG = "ctime_Long";
    /**
     * 修改人id
     **/
    public static final String UUSERID = "uUserId";
    /**
     * 修改人名称
     **/
    public static final String UUSERNAME = "uUserName";
    /**
     * 修改时间
     **/
    public static final String UTIME = "utime";
    public static final String UTIMELONG = "utime_Long";

    /**
     * 租户id
     **/
    public static final String COMPANYID = "companyId";
    /**
     * 租户名称
     **/
    public static final String COMPANYNAME = "companyName";
    /**
     * 租户圈
     **/
    public static final String COMPANYGROUP = "companyGroup";
    /**
     * 租户标签
     **/
    public static final String COMPANYTAG = "companyTag";
    /**
     * 部门id
     **/
    public static final String DEPARTMENTID = "departmentId";
    /**
     * 部门名称
     **/
    public static final String DEPARTMENTNAME = "departmentName";
    /**
     * 删除标志 （已删除/未删除）
     **/
    public static final String ISDEL = "isdel";
    /**
     * 用户类型（粉丝/用户）
     **/
    public static final String USERTYPE = "userType";
    /** 支持高并发 入缓存 **/
	public static final String ISCACHE = "isCache";
	/** 高并发请求  **/
	public static final String HIGHCONCURRENT = "highConcurrent";
	/** 扩展字段  **/
	public static final String OTHERS = "others";
}
