<%-- <%@page import="com.cdvcloud.upload.util.SpringUtil"%> --%>
<%-- <%@page import="com.cdvcloud.rms.common.UserAuthentication"%> --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<%-- <%
	UserAuthentication userAuthentication = (UserAuthentication)SpringUtil.getInstance("userAuthentication");
	boolean status = userAuthentication.getAccessToken(request, "loginId");
%>
<script>
var userId="<%=request.getSession().getAttribute("userId")%>";
var casUserId="<%=request.getSession().getAttribute("casUserId")%>";
var consumerId="<%=request.getSession().getAttribute("consumerId")%>";
var areaCode="<%=request.getSession().getAttribute("areaCode")%>";
var accessToken="<%=request.getSession().getAttribute("accessToken")%>";
var userRole="<%=request.getSession().getAttribute("userRole")%>";
var uname = "${userName}";
</script> --%>
<!--引入公网的css和js方法  后期有必要改为cdn或者本地-->
<link rel="stylesheet" href="http://101.201.211.31:8082/csdn/ys_admin/common/css/bootstrap.css" >
<link rel="stylesheet" href="http://101.201.211.31:8082/csdn/ys_admin/cell/toastr/toastr.min.css" >
<link rel="stylesheet" href="http://101.201.211.31:8082/csdn/ys_admin/dest/css/ysbase.css" >
<link rel="stylesheet" href="http://101.201.211.31:8082/csdn/ys_admin/dest/ys.css" >

<script src="http://101.201.211.31:8082/csdn/ys_admin/dest/js/jquery.min.js"> </script>
<!--没有报表不用引用-->
<!-- <script src="http://101.201.211.31:8082/csdn/ys_admin/dest/js/echarts.min.js"> </script> -->
<script src="http://101.201.211.31:8082/csdn/ys_admin/dest/js/bootstrap.min.js"> </script>
<script src="http://101.201.211.31:8082/csdn/ys_admin/cell/layer/layer/layer.js"> </script>
<script src="http://101.201.211.31:8082/csdn/ys_admin/cell/toastr/toastr.min.js"> </script>
<script src="http://101.201.211.31:8082/csdn/ys_admin/dest/ys.js"> </script>
<script type="text/javascript" src="${ctx }/common/base.js"></script>


<%-- 
<link rel="stylesheet" type="text/css" href="${ctx }/common/css/index.css">
<link rel="stylesheet" type="text/css" href="${ctx }/common/css/common.css">
<link rel="stylesheet" type="text/css" href="${ctx }/common/plugins/upload/css/stream-v1.css">
<link rel="stylesheet" type="text/css" href="${ctx }/common/plugins/layer/skin/layer.css">
<link rel="stylesheet" type="text/css" href="${ctx }/common/plugins/toastr/toastr.min.css">
<link rel="stylesheet" type="text/css" href="${ctx }/common/plugins/lightbox/lightbox.css">
<script type="text/javascript" src="${ctx }/common/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/behavior/behavior.js"></script>
<script type="text/javascript" src="${ctx }/common/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${ctx }/common/js/config.js"></script>
<script type="text/javascript" src="${ctx }/common/js/global.js"></script>
<script type="text/javascript" src="${ctx }/common/plugins/hashmap/onairmap-v0.9.js"></script>
<script type="text/javascript" src="${ctx }/common/plugins/toastr/toastr.min.js"></script>
<script src="${ctx }/common/plugins/lightbox/lightbox.js"></script> --%>

