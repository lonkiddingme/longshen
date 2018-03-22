package com.cdvcloud.douting.common;

import java.io.Serializable;

public class CommonParameters implements Serializable {
    private static final long serialVersionUID = -6531451957081138067L;
    /*** 授权令牌 */
    public static final String ACCESSTOKEN = "accessToken";
    /*** 时间戳 */
    public static final String TIMESTAMP = "timeStamp";
    /*** 控制服务启用开关（on:启用，off：停用） */
    public static final String ON = "on";
    public static final String OFF = "off";

    /*** 企业标识 */
    private String companyId;
    /*** 应用标识 */
    private String appCode;
    /*** 用户标识 */
    private String userId;
    /*** 服务类型标识 */
    private String serviceCode;
    /*** 版本号 */
    private String versionId;
    /**
     * 租户圈
     */
    private String companyGroup;
    /**
     * 租户标签
     */
    private String companyTag;
    /*** 授权令牌 */
    private String accessToken;
    /*** 时间戳 */
    private String timeStamp;
    /*** 所属商名称 */
    private String companyName;
    /*** 用户名称 */
    private String userName;
    /*** 用户部门id */
    private String departmentId;
    /*** 用户部门名称 */
    private String departmentName;
    /*** 访问ip */
    private String ip;
    /*** 操作 */
    private String action;

    private String roleId;

    public Object getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public CommonParameters() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyGroup() {
        return companyGroup;
    }

    public void setCompanyGroup(String companyGroup) {
        this.companyGroup = companyGroup;
    }

    public String getCompanyTag() {
        return companyTag;
    }

    public void setCompanyTag(String companyTag) {
        this.companyTag = companyTag;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "CommonParameters [companyId=" + companyId + ", appCode=" + appCode + ", userId=" + userId + ", serviceCode=" + serviceCode + ", versionId=" + versionId + ", accessToken=" + accessToken + ", timeStamp=" + timeStamp + "]";
    }

    /**
     * 返回五个公共参数
     **/
    public String getCommonParam() {
        return companyId + appCode + userId + serviceCode + versionId;
    }
}
