package com.cdvcloud.douting.common;

import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.rms.dao.mongodb.QueryOperators;
import com.cdvcloud.rms.util.DateUtil;
import com.cdvcloud.rms.util.ResponseObject;
import com.cdvcloud.rms.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * 公共校验
 * ValidateCommonParam
 *
 * @author wlf
 * @Description
 * @data 2017-10-24 下午9:49:23
 */
public class ValidateCommonParam {
    public ValidateCommonParam() {

    }

    /**
     * 获取公共参数并验证
     *
     * @param commonParameters
     * @param jsonMap
     * @return
     */
    public static boolean validateCommonParam(CommonParameters commonParameters, Map<String, Object> jsonMap, Map<String, Object> mapError) {
        if (StringUtil.isEmpty(jsonMap) || jsonMap.isEmpty()) {
            mapError.put(Constants.MESSAGE, "parameter is empty!");
            return false;
        }
        // 获取公共参数
        Object accessToken = jsonMap.get(CommonParameters.ACCESSTOKEN);
        Object timeStamp = jsonMap.get(CommonParameters.TIMESTAMP);
        if (!StringUtil.isEmpty(accessToken)) {
            boolean result = true;// TODO:需要添加token验证
            if (!result) {
                mapError.put(Constants.MESSAGE, "AccessToken is lose efficacy!");
                return false;
            }
            commonParameters.setAccessToken(String.valueOf(accessToken));
        } else {
            mapError.put(Constants.MESSAGE, "AccessToken is empty!");
            return false;
        }
        if (!StringUtil.isEmpty(timeStamp)) {
            commonParameters.setTimeStamp(String.valueOf(timeStamp));
        } else {
            mapError.put(Constants.MESSAGE, "timeStamp is empty!");
            return false;
        }
        return true;
    }

    /**
     * 获取公共参数(keyWord:需要模糊匹配的字段名,keyTime:需要匹配的时间字段名)
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getWhereParam(Map<String, Object> mapJson) {
        Map<String, Object> whereMap = new HashMap<String, Object>();
        String keyWord = String.valueOf(mapJson.get("keyWord"));
        String keyValue = String.valueOf(mapJson.get("keyValue"));
        String keyTime = String.valueOf(mapJson.get("keyTime"));
        // 关键字
        if (!StringUtil.isEmpty(keyWord) && !StringUtil.isEmpty(keyValue)) {
            String regxValue = ".*" + keyValue + ".*";
            Map<String, Object> regxMap = new HashMap<String, Object>();
            regxMap.put("$regex", regxValue);
            regxMap.put("$options", "i");
            whereMap.put(keyWord, regxMap);
        }
        // 开始时间
        String startTime = String.valueOf(mapJson.get("startTime"));
        // 结束时间
        String endTime = String.valueOf(mapJson.get("endTime"));
        if (!StringUtil.isEmpty(keyTime) && !StringUtil.isEmpty(mapJson.get("startTime")) && !StringUtil.isEmpty(startTime) && !StringUtil.isEmpty(mapJson.get("endTime")) && !StringUtil.isEmpty(endTime)) {
            Map<String, Object> mapTime = new HashMap<String, Object>();
            mapTime.put(QueryOperators.GTE, startTime);
            mapTime.put(QueryOperators.LTE, endTime);
            whereMap.put(keyTime, mapTime);
        } else if (!StringUtil.isEmpty(keyTime) && !StringUtil.isEmpty(mapJson.get("startTime")) && !StringUtil.isEmpty(startTime)) {
            Map<String, Object> gteMap = new HashMap<String, Object>();
            gteMap.put(QueryOperators.GTE, startTime);
            whereMap.put(keyTime, gteMap);
        } else if (!StringUtil.isEmpty(keyTime) && !StringUtil.isEmpty(mapJson.get("endTime")) && !StringUtil.isEmpty(endTime)) {
            Map<String, Object> lteMap = new HashMap<String, Object>();
            lteMap.put(QueryOperators.LTE, endTime);
            whereMap.put(keyTime, lteMap);
        }
        if (!StringUtil.isEmpty(String.valueOf(mapJson.get("conditionParam")))) {
            Map<String, Object> mapCondition = (Map<String, Object>) mapJson.get("conditionParam");
            for (String str : mapCondition.keySet()) {
                whereMap.put(str, mapCondition.get(str));
            }
        }
        whereMap.put(BasicObject.ISDEL, Constants.IS_DEL_NO);
        return whereMap;
    }

    /**
     * 获取排序条件
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getSortMap(Map<String, Object> mapJson) {
        Map<String, Object> sortMap = new HashMap<String, Object>();
        if (!StringUtil.isEmpty(mapJson.get("sort"))) {
            sortMap = (Map<String, Object>) mapJson.get("sort");
        } else {
            sortMap.put(BasicObject.ID, 1);
        }
        return sortMap;
    }

    /**
     * 获取返回值类型
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getBackMap(Map<String, Object> mapJson) {
        Map<String, Object> backMap = new HashMap<String, Object>();
        if (!StringUtil.isEmpty(mapJson.get("backParam"))) {
            backMap = (Map<String, Object>) mapJson.get("backParam");
        }
        return backMap;
    }

    /**
     * 获取当前页
     */
    public static Integer getCurrentPage(Map<String, Object> mapJson) {
        Integer currentPage = 1;
        String currentPageFlag = String.valueOf(mapJson.get("currentPage"));
        if (!StringUtil.isEmpty(mapJson.get("currentPage")) && !StringUtil.isEmpty(currentPageFlag)) {
            currentPage = Integer.valueOf(currentPageFlag);
        }
        return currentPage;
    }

    /**
     * 获取每页条数
     */
    public static Integer getPageNum(Map<String, Object> mapJson) {
        Integer pageNum = 10;
        String pageNumFlag = String.valueOf(mapJson.get("pageNum"));
        if (!StringUtil.isEmpty(mapJson.get("pageNum")) && !StringUtil.isEmpty(pageNumFlag)) {
            pageNum = Integer.valueOf(pageNumFlag);
            if (pageNum > 100) {
                pageNum = 100;
            }
        }
        return pageNum;
    }

    /**
     * 验证参数
     */
    @SuppressWarnings("rawtypes")
    public static boolean validateValue(List<String> names, Map<String, Object> mapJson, Map<String, Object> mapError) {
        boolean valdate = true;
        //从提交的map中遍历list中指定要验证的项
        for (String name : names) {
            //只要有一个指定项不存在就返回失败，并且将失败信息放入mapError
            if (!mapJson.containsKey(name)) {
                mapError.put("data", name + " is Non-existent!");
                valdate = false;
                break;
            }
            //只要有一个指定项的值为空就返回失败，并且将失败信息放入mapError
            Object obj = mapJson.get(name);
            if (obj == null) {
                mapError.put("data", name + " can't be empty!");
                valdate = false;
                break;
            }
            if (obj instanceof List) {
                List value = (List) mapJson.get(name);
                if (value.isEmpty()) {
                    mapError.put("data", name + " can't be empty!");
                    valdate = false;
                    break;
                }
            }
            if (obj instanceof Map) {
                Map value = (Map) mapJson.get(name);
                if (value.isEmpty()) {
                    mapError.put("data", name + " can't be empty!");
                    valdate = false;
                    break;
                }
            }
            if (obj instanceof String) {
                String value = String.valueOf(mapJson.get(name));
                if (StringUtil.isEmpty(value)) {
                    mapError.put("data", name + " can't be empty!");
                    valdate = false;
                    break;
                }
            }
        }
        return valdate;
    }

    /**
     * 返回系统内部错误信息
     */
    public static void innerError(ResponseObject resObj) {
        resObj.setCode(GeneralStatus.inner_error.status);
        resObj.setMessage(GeneralStatus.inner_error.detail);
        resObj.setData(GeneralStatus.inner_error.enDetail);
    }

    /**
     * 返回参数错误的验证信息
     */
    public static void parameterError(ResponseObject resObj, Object o) {
        resObj.setCode(GeneralStatus.input_error.status);
        resObj.setMessage(GeneralStatus.input_error.detail);
        resObj.setData(o);
    }

    /**
     * 返回用户错误的验证信息
     */
    public static void userError(ResponseObject resObj) {
        resObj.setCode(GeneralStatus.user_error.status);
        resObj.setMessage(GeneralStatus.user_error.detail);
        resObj.setData(GeneralStatus.user_error.enDetail);
    }

    /**
     * 返回用户状态验证信息
     */
    public static void userStatusError(ResponseObject resObj) {
        resObj.setCode(GeneralStatus.user_status_error.status);
        resObj.setMessage(GeneralStatus.user_status_error.detail);
        resObj.setData(GeneralStatus.user_status_error.enDetail);
    }
    /**
     * 返回用户状态验证信息
     */
    public static void userIsdelError(ResponseObject resObj) {
        resObj.setCode(GeneralStatus.user_isdel_error.status);
        resObj.setMessage(GeneralStatus.user_isdel_error.detail);
        resObj.setData(GeneralStatus.user_isdel_error.enDetail);
    }
    /**
     * 返回处理成功的信息
     */
    public static void executeSuccess(ResponseObject resObj, Object o) {
        resObj.setCode(GeneralStatus.success.status);
        resObj.setMessage(GeneralStatus.success.detail);
        resObj.setData(o);
    }

    /**
     * 拼装创建人信息
     *
     * @param commonParameters commonParameters
     * @return 创建对象的租户信息、部门信息、创建人信息、更新人信息、基础信息
     */
    public static Map<String, Object> getCreateMessage(CommonParameters commonParameters) {
        Map<String, Object> create = new HashMap<String, Object>(16);
        /* 租户信息 */
        if (!StringUtil.isEmpty(commonParameters.getCompanyId())) {
            create.put(BasicObject.COMPANYID, commonParameters.getCompanyId());
        }
        if (!StringUtil.isEmpty(commonParameters.getCompanyGroup())) {
            create.put(BasicObject.COMPANYGROUP, commonParameters.getCompanyGroup());
        }
        if (!StringUtil.isEmpty(commonParameters.getCompanyName())) {
            create.put(BasicObject.COMPANYNAME, commonParameters.getCompanyName());
        }

        /* 部门信息 */
        if (!StringUtil.isEmpty(commonParameters.getDepartmentId())) {
            create.put(BasicObject.DEPARTMENTID, commonParameters.getDepartmentId());
        }
        if (!StringUtil.isEmpty(commonParameters.getDepartmentName())) {
            create.put(BasicObject.DEPARTMENTNAME, commonParameters.getDepartmentName());
        }

        /* 创建人信息 */
        if (!StringUtil.isEmpty(commonParameters.getUserId())) {
            create.put(BasicObject.USERID, commonParameters.getUserId());

        }
        if (!StringUtil.isEmpty(commonParameters.getUserName())) {
            create.put(BasicObject.USERNAME, commonParameters.getUserName());

        }
        create.put(BasicObject.CTIME, DateUtil.getCurrentDateTime());
        create.put(BasicObject.CTIMELONG, System.currentTimeMillis());

        /*更新人信息*/
        create.putAll(getUpdateMessage(commonParameters));

        /* 基础信息 */
        create.put(BasicObject.ISDEL, Constants.IS_DEL_NO);
        return create;
    }

    /**
     * 拼装更新人信息
     *
     * @param commonParameters commonParameters
     * @return 更新人id、更新人名称、更新时间
     */
    public static Map<String, Object> getUpdateMessage(CommonParameters commonParameters) {
        Map<String, Object> update = new HashMap<String, Object>(16);
        String userId = commonParameters.getUserId();
        if (!StringUtil.isEmpty(userId)) {
            update.put(BasicObject.UUSERID, userId);
        }
        String userName = commonParameters.getUserName();
        if (!StringUtil.isEmpty(userName)) {
            update.put(BasicObject.UUSERNAME, userName);
        }
        update.put(BasicObject.UTIME, DateUtil.getCurrentDateTime());
        update.put(BasicObject.UTIMELONG, System.currentTimeMillis());
        return update;
    }

    /**
     * 获取Map中的字段
     *
     * @param Keys    字段列表
     * @param mapJson 接收到的参数
     * @return 参数
     */
    public static Map<String, Object> getFieldsMap(List<String> Keys, Map<String, Object> mapJson) {
        Map<String, Object> updateMap = new HashMap<String, Object>(16);
        for (String name : Keys) {
            if (mapJson.containsKey(name)) {
                Object value = mapJson.get(name);
                if (!StringUtil.isEmpty(value)) {
                    updateMap.put(name, value);
                }
            }
        }
        return updateMap;
    }

    /**
     * 生成请求信息日志字符串
     *
     * @param companyId   租户ID
     * @param appCode     应用标识
     * @param userId      用户ID
     * @param serviceCode 服务标识
     * @param strJson     请求参数
     * @return 返回日志信息
     */
    public static String getRequestInfo(String companyId, String appCode, String userId, String serviceCode, String strJson) {
        StringBuffer stringBuffer = new StringBuffer("接收到的参数为：");
        stringBuffer.append("[companyId=").append(companyId).append("],");
        stringBuffer.append("[appCode=").append(appCode).append("],");
        stringBuffer.append("[userId=").append(userId).append("],");
        stringBuffer.append("[serviceCode=").append(serviceCode).append("],");
        stringBuffer.append("[strJson=").append(strJson).append("]");
        return stringBuffer.toString();
    }


    public static CommonParameters getLoginUserMessage(HttpSession session) {
        CommonParameters commonParameters = new CommonParameters();
        String currentUserId = String.valueOf(session.getAttribute(Constants.CURRENT_USERID));
        String currentUsername = String.valueOf(session.getAttribute(Constants.CURRENT_USERNAME));
        String currentCompanyId = String.valueOf(session.getAttribute(Constants.CURRENT_COMPANYID));
        String currentCompanyName = String.valueOf(session.getAttribute(Constants.CURRENT_COMPANYNAME));
        String currentDepartmentId = String.valueOf(session.getAttribute(Constants.CURRENT_DEPARTMENTID));
        String currentDepartmentname = String.valueOf(session.getAttribute(Constants.CURRENT_DEPARTMENTNAME));
        String currentCompanygroup = String.valueOf(session.getAttribute(Constants.CURRENT_COMPANYGROUP));
        String currentAppcode = String.valueOf(session.getAttribute(Constants.CURRENT_APPCODE));

        commonParameters.setUserId(currentUserId);
        commonParameters.setUserName(currentUsername);
        commonParameters.setCompanyId(currentCompanyId);
        commonParameters.setCompanyName(currentCompanyName);
        commonParameters.setDepartmentId(currentDepartmentId);
        commonParameters.setDepartmentName(currentDepartmentname);
        commonParameters.setCompanyGroup(currentCompanygroup);
        commonParameters.setAppCode(currentAppcode);

        return commonParameters;
    }

    public static String getCommonParametersUrl(CommonParameters commonParameters, String serviceCode) {
        return commonParameters.getCompanyId() + "/" + commonParameters.getAppCode() + "/" + commonParameters.getUserId() + "/" + serviceCode + "/";
    }
    
    public static String getCommonParametersUrl2(CommonParameters commonParameters,String token) {
        return "?companyId="+commonParameters.getCompanyId() + "&appCode=" + commonParameters.getAppCode() + "&userId=" + commonParameters.getUserId() + "&userType=fans&token="+token;
    }
}
