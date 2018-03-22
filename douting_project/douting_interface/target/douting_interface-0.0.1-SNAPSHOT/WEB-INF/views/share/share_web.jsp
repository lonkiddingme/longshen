<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/17
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <base href="<%=path%>"></base>
    <script src="js/share/rem.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="css/audio.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/details.css"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
</head>
<body>
<div class="details-wrap">
    <div class="bannar">
        <div class="bannar-img">
        </div>
        <div class="verticalcenter bannar-text">
            <h2 style="font-size: 0.22rem">${title}</h2>
            <p>${pushtime}</p>
        </div>
    </div>
    
    
    
 <c:choose>
   <c:when test="${videos[0].vh5url != null and videos[0].vh5url != ''}"> 
	   <div style="margin-top: 0px;">
	    	<video src="${videos[0].vh5url}" controls="controls" width="100%"></video>
	    </div>
   </c:when>
   <c:otherwise>
			   		<div class="audio-play clearfloat">
			        <section class="audio_wrap">
			            <!-- 音频 -->
			            <section class="audio_cont" onclick="audio()">
			                <section class="audio_main">
			                    <section class="audio_mess">
			                        <section class="audio_icon">
			                            <img class="audio_icon1" src="images/audio_icon1.png">
			                            <img class="audio_icon2" src="images/audio_icon2.png">
			                            <img class="audio_icon3" src="images/audio_icon3.png">
			                            <img class="audio_icon4" src="images/audio_icon4.png">
			                        </section>
			                        <p class="audio_title">${title}</p>
			                    </section>
			                    <section class="audio_progress">
			                        <section class="audio_promain" style="width: 0px;"></section>
			                    </section>
			                </section>
			                <audio src="${audios[0].aurl}" id="myAudio"></audio>
			            </section>
			        </section>
			    </div>
   	</c:otherwise>
</c:choose>
    
    <div class="scroll-cont">
        <div class="news-cont">
            ${content}
        </div>
        <div class="relevant-msg">
            <p><i class="icon-Combined-Shape"></i><span>${lookNum}</span><i class="icon-give_like"></i><span>${likeNum}</span></p>
        </div>
        <div onclick="buttonOnclick()" class="thumbs-up">
            <i class="icon-like1"></i>
            <p>点赞</p>
        </div>
        <div class="division-line"></div>
        <div class="comment-cont">
            <div class="comment-div">
                <i class="icon-Fill-1"></i>
                <input onclick="buttonOnclick()" type="text" name="" id="" value="" class="" placeholder="吃瓜群众有话说"/>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.yunshicloud.com/jquery/1.11.0/jquery.min.js"></script>
<script src="js/share/details.js" type="text/javascript" charset="utf-8"></script>
<script src="js/share/audio.js" type="text/javascript" charset="utf-8"></script>
<script>
    function buttonOnclick() {
        var u = navigator.userAgent, app = navigator.appVersion;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        if (isAndroid){
            location.href="${apkDownLoadUrl}";
        }
        if (isiOS){
            location.href="${ipaDownLoadUrl}";

        }
    }
</script>
</body>
</html>


