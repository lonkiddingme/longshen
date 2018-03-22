package com.cdvcloud.douting.domain;

/**
 * 首页
 *
 * @author SongYuanKun
 * @date 2017/11/1
 */
public class HomePage extends BasicObject {


    /**
     * 表名
     */
    public static final String HOMEPAGE = "homePage";
    /**
     * 类别1
     * 启动页、轮播图、推荐页
     * 轮播图：推荐/逗秀/逗看
     * 推荐页：秀场/看看/互动
     */
    public static final String TYPE = "type";
    /**
     * 状态：已发布/未发布
     */
    public static final String PUSHSTATE = "pushState";

    /**
     * 首页缩略图
     **/
    public static final String THUMBNAILURL = "thumbnailUrl";
    /**
	 * 逗秀内容所属节目ID
	 */
	 public static final String PID= "pId";
	/**
	* 逗秀内容所属节目名字
	*/
	public static final String PNAME= "pName";
    /**
     * 绑定位置；
     */
    public static final String BIND = "bind";
    /**
     * 绑定状态：外链/内容/直播间
     */
    public static final String BINDTYPE = "bindType";
    /**
     * 绑定内容
     */
    public static final String TITLE = "title";
    /**
     * 排序权重
     */
    public static final String WEIGHT = "weight";
    
    public static final String CLASSIFY = "classify";
}
