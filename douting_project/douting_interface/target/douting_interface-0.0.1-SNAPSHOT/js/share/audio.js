
function audio_load(audio_id){
	var myAudio = document.getElementById(audio_id);

    //var audio_src=myAudio.src;
	//myAudio.src=audio_src;
    $("#"+audio_id).next(".audio_mark").one('click',function(e){
        e.stopPropagation();
        myAudio.play();
        $(this).hide();
    });

    //audio播放完毕
    myAudio.addEventListener('ended', function () {
        $("#"+audio_id).prev().find(".audio_icon").removeClass("active").attr("flag","2");
    });

	//获取音频时长
    var audio_timeBefore,audio_timeAfter;
    myAudio.addEventListener("canplay", function(){
        myAudio.pause();
        audio_timeBefore = myAudio.duration;
        audio_timeAfter = time_change(audio_timeBefore);
        $("#"+audio_id).prev().find(".audio_time").html(audio_timeAfter);
    },false);

	//播放、停止
    $("#"+audio_id).parents(".audio_cont").click(function(){
		var flag = $(this).attr("flag");
		if(flag != 1){
            $("#"+audio_id).prev().find(".audio_promain").css({"width":"0"});

            if(typeof(audio_timeBefore)=="undefined"){
                //$(".audio_tip").show();
            }else{
                myAudio.play();
                $(this).find(".audio_icon").addClass("active");
                audio_pro(audio_id,audio_timeBefore);
                $(this).attr("flag","1");
            }
		}else if(flag != 2){
			$(this).find(".audio_icon").removeClass("active");
			myAudio.pause();
			myAudio.currentTime = 0.0;
            $("#"+audio_id).prev().find(".audio_promain").stop();
            $(this).attr("flag","2");
		}
	})

}


//进度条变化
function audio_pro(audio_id,audio_time){
	var ani_time = audio_time * 1000;
    $("#"+audio_id).prev().find(".audio_promain").animate({"width":"100%"},ani_time);

}

//时间格式转换函数
function time_change(tol){
	var hover_time,hour,minute,second;

	hour=Math.floor(tol/60/60);
    minute=Math.floor(tol/60%60);
    second=Math.floor(tol%60);

    if(second<10){
    	second="0"+second;
    }
    if(minute<10){
    	minute="0"+minute;
    }
    if(hour<10){
    	hour="0"+hour;
    }
    hover_time=hour+":"+minute+":"+second;
    return hover_time;
}