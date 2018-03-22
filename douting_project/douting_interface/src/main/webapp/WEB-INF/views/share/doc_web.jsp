<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/17
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <base href="<%=path%>"></base>
    <script src="js/share/rem.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/details.css"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
</head>
<body>
<div class="details-wrap">
    <div class="scroll-cont">
        <div class="news-cont">
            ${content}
        </div>
    </div>
</div>
<script src="https://cdn.yunshicloud.com/jquery/1.11.0/jquery.min.js"></script>
<script src="js/share/details.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>


