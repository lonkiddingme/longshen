$(function(){
    $("audio").each(function(){
        var audioID=$(this).attr("id");
        audio_load(audioID);
    })
});
$(function(){
    // $(".bannar").height($(".bannar").width()/1.78);
    // $(".scroll-cont").height($(window).height()-$(".bannar").height()-$(".audio-play ").height());
    // $(".audio-play-btn i,.audio-text p i,.thumbs-up i").click(function(){
    //     $(this).toggleClass('active');
    /*// })*/
	/*var aa = $(".audio_cont").width("50px");
	$(".audio_cont").css({"backgroundColor":"red"});
	console.log(aa);*/
	
});
function audio(){
	var audio1 = $("#myAudio")[0];
    if(audio1!==null){
        if(!audio1.paused){  
        	audio1.pause();
        }else{
        	audio1.play();
        }  
    }  
}

