$(function () {
    document.addEventListener('touchmove', function(e){
        //e.preventDefault()
    }, false);
    var _h=document.body.clientHeight;
    document.getElementById('videobox').style.height=_h+'px';
    document.getElementById('info_img').onclick=function(){
        document.getElementById('info_img').classList.add("hide");
        console.log(videoinfo);
        videoinfo.play();
        document.addEventListener("WeixinJSBridgeReady", function () {
            videoinfo.play();
        }, false);
    };
    var videobox=document.getElementById('videoinfo');
    window.onresize=function(){
        videobox.style.width=window.innerWidth+'px';
        videobox.style.height=window.innerHeight+'px';
    };

    videobox.addEventListener('timeupdate',function(){//显示当前播放时间
        var currenttime=videobox.currentTime;
        //console.log('当前播放时间：'+videobox.currentTime);
    });

    //视频播放状态
    videobox.addEventListener('canplaythrough',function(){
        //console.log('canplaythrough');
        //alert('canplaythrough');
        var alltime=videobox.duration;
        //console.log(alltime)
    });
    videobox.addEventListener('ended',function(){//播放事件：ended
        //console.log('播放事件：ended');
    });
});
var delCommentLastSize = 1;
var delCommentLastPoolId = 1;
var lastSize = 1;
var lastPoolId = 1;


//页面轮询数据
window.setTimeout("msgList()",7000); 
function msgList(){
	var jsonObj = new OnairHashMap();
	jsonObj.put("id", $("#videoRoomId").val());
	jsonObj.put("delCommentLastSize", delCommentLastSize);
	jsonObj.put("delCommentLastPoolId", delCommentLastPoolId);
	jsonObj.put("lastSize", lastSize);
	jsonObj.put("lastPoolId", lastPoolId);
    var url = "shareLive/queryPollingData";
	 var data = tableData(jsonObj.toJson(), url);
	 if (data.code != undefined && data.code == 0) {
		   $('#commentArea').scrollTop( $('#commentArea')[0].scrollHeight );
		 
	        var list = data.data;
	        var commentList = "";
	        var delCommentList = "";
	        //评论
	        var commenet = list.commentList;
	        lastSize = commenet.size;
	        lastPoolId = commenet.poolId;
	        if (commenet.results != "" && commenet.results != null) {
	        	var r = commenet.results;
	        	for (var i = r.length-1; r.length >0; i--) {
	        		commentList +=' <li id="'+r[i].commentId+'"><span>'+r[i].doCommentName+":"+r[i].content+'</span></li>'
				}
		        $("#commentArea").append(commentList);
	        }
	        //删除评论
	        
	        var deleteCommenet = list.deleteCommentIdsList;
	        delCommentLastSize = deleteCommenet.size;
	        delCommentLastPoolId = deleteCommenet.poolId;
	        if (deleteCommenet.results != "" && deleteCommenet.results != null) {
	        	var r = deleteCommenet.results;
	        	for (var i = r.length-1; r.length >0; i--) {
		        	var result = r[i].split("_");
		        	delCommentList +='<li class="system_news"><span>系统消息：'+result[1]+'的评论被删除</span></li>';
		        	$("#"+result[0]).remove();
		        };
		        $("#commentArea").append(delCommentList);
	        }
	 }
	 window.setTimeout("msgList()",7000); 
} 




