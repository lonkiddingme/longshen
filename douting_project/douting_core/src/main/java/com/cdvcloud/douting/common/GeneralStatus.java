package com.cdvcloud.douting.common;

import java.io.Serializable;

/**
 * 系统中的所有处理状态
 *
 * @author mcxin
 */
public enum GeneralStatus implements Serializable {
    //公共状态
    success(0, "处理成功", "success"), query_success(0, "查询成功", "Query success"), update_success(0, "修改成功", "Modify the success"), delete_success(0, "删除成功", "Delete the success"), insert_success(0, "添加成功", "Insert the success"),

    failure(1, "处理失败", "fail"), input_error(10001, "输入参数不合法", "Input Parameter Invalid"), inner_error(10002, "系统内部错误", "Inner Error"), unknow_error(10003, "未知错误", "Unknow Error"), query_error(10004, "查询失败", "Query error"), update_error(10005, "修改失败", "Modify the error"), delete_error(10006, "删除失败", "Delete the error"), insert_error(10007, "添加失败", "Insert the error"), user_error(10007, "用户校验失败", "user is Non-existent"),
    comment_error(10010, "评论失败", "Comment the error"),user_status_error(10008, "用户被禁言", "The user is disabled"),user_isdel_error(10009, "用户被禁用", "The user is banned"),update_fansMoney_error(10010, "领取失败", "Receive failure"),fans_task_error(10011, "任务未完成", "The task is not completed");

    public int status; // 状态值 ，0：成功，1：失败，2：处理中，3：未处理
    public String detail; // 状态描述信息
    public String enDetail;

    private GeneralStatus(int status, String detail, String enDetail) {
        this.status = status;
        this.detail = detail;
        this.enDetail = enDetail;
    }

    @Override
    public String toString() {
        return "{status:" + this.status + ",detail:" + this.detail + "}";
    }
}
