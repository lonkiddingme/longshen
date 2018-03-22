package com.cdvcloud.douting.common;

/**
 * 公共属性
 * Constants
 *
 * @author wlf
 * @Description
 * @data 2017-10-24 下午9:47:54
 */
public class Constants {
    /**
     * 数字
     **/
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;
    public static final Integer THREE = 3;
    public static final Integer FOUR = 4;
    public static final Integer FIVE = 5;
    /**
     * 字符串类型数字
     **/
    public static final String SZERO = "0";
    public static final String SONE = "1";
    public static final String STWO = "2";
    public static final String STHREE = "3";
    public static final String SFOUR = "4";
    public static final String SFIVE = "5";
    /**
     * 各类状态
     **/
    public static final String IS_DEL_NO = "未删除";
    public static final String IS_DEL_YES = "已删除";
    public static final String IS_ENABLE = "启用";
    public static final String IS_WAIT_AUDIT = "待审核";
    public static final String IS_CHECK_OUT = "通过";
    public static final String IS_TURN_DOWN = "不通过";
    public static final String IS_GROUNDING = "上架";
    public static final String IS_UNDERCARRIAGE = "下架";
    public static final String IS_LINE = "上线";
    public static final String IS_UNLINE = "下线";
    public static final String IS_UNRELEASED = "未发布";
    public static final String IS_RELEASED = "已发布";
    public static final String MESSAGE = "message";
    public static final String STARTUPPAGE = "homePage";
    
    /** 登录用户信息 **/
	public static final String CURRENT_USERID = "current_userId";
	public static final String CURRENT_USERNAME = "current_userName";
	public static final String CURRENT_COMPANYID = "current_companyId";
	public static final String CURRENT_COMPANYNAME = "current_companyName";
	public static final String CURRENT_DEPARTMENTID = "current_departmentId";
	public static final String CURRENT_DEPARTMENTNAME = "current_departmentName";
	public static final String CURRENT_COMPANYGROUP = "current_companyGroup";
	public static final String CURRENT_APPCODE = "current_appCode";
	public static final String CURRENT_COMPANYTAG = "current_companyTag";
	public static final String CURRENT_ROLEID = "current_roleId";
	
	public static final boolean BIND = false;
	/** 分隔符 逗号',' */
	public static final String DELIMITER = ",";
	/** UTF-8编码 */
	public static final String CODED_FORMAT = "UTF-8";
	public static final String USERAPIURL = "userApiUrl";
	public static final String IDS = "ids";
	
	public static final String NEWCOMMENTOUTTIME = "newCommentOutTime";
	
    public static final String LIVENOTICE = "liveNotice";
    
    public static final String PROHIBIFANS = "prohibitFans";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String FANSID = "fansId";
    public static final String LIVEGOODS = "liveGoods";
    /** redis红包列表（房间下所有红包） **/
	public static final String REDENVELOPEPLIST = "redEnvelopePList";
	/** redis评论池id列表 **/
	public static final String COMMENTPOOLIDS = "commentPoolIds";
	/** redis评论删除池id列表 **/
	public static final String COMMENTDELPOOLIDS = "commentDelPoolIds";
	public static final String COMMENT = "comment";
	public static final String TIME = "time";
	public static final String FANSMSG = "fansMsg";
	
	public static final String FANSLOGINMSG = "fansLoginMsg";
	
	public static final String REWARDMSG = "RewardMsg";
}
