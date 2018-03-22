jQuery.YSPublic={
    scollToTop:function(){
        $('html, body').scrollTop(0);
    },
    historyUrl:function($o){
        //此次修改为添加特殊的参数
        var mid=$o.attr("id");
        var href=window.location.href;
        if(href.lastIndexOf('#')>0){
            href=href.substring(0,href.lastIndexOf('#'));
        }
        href=href+"#"+mid;
        var stateObject = {};
        history.pushState(stateObject,"",href);
    },
    pageJump:function(){
        var params=window.location.hash.substr(1);
        if(null!=params && ("")!=params){
            var $openId;
            var oldshref="";
            if(params.indexOf("/")>=0){
                $openId=$("#"+params.split("/")[0]);
                var shref=$openId.attr("shref");
                oldshref=shref;
                shref=shref+"&"+params.split("/")[1];
                if(params.split("/").length==3){
                    shref=shref+"&"+params.split("/")[2];
                }
                shref=decodeURI(shref);//由于window.location.hash对中文默认编过码，同时下面click事件函数还会编码，所以避免重复编码此处先解码
                $openId.attr("shref",shref);
            }else{
                $openId=$("#"+params);
                var shref=$openId.attr("shref");
                oldshref=shref;
            }
            //$openId.parents(".treeview").find("a:first").click();
            $openId.parents("li").find("a:first").click();
            $openId.click();
            $openId.attr("shref",oldshref);
        }else{
            $("#menutree a:first").click();
            $("#menutree ul a:first").click();
        }
    },
    //根据菜单初始化内容页的导航
    initNav:function($o){
        var cname=$o.find("span").text();
        var pname=$o.attr("pname");
        var shtml='<h2 ><i class="icon-home"></i>'+pname+'<b>></b><span >'+cname+'<span></h2>';
        if(pname==undefined)shtml='<h2 ><i class="icon-home"></i><span >'+cname+'<span></h2>';
        $("#initNav").html(shtml);
    },
    changeNav:function(navvalue){
        if($("#initNav-navvalue").length>0){
            $("#initNav-navvalue").html("<b>></b>"+navvalue+"");
        }else{
            $("#initNav").find("span").addClass("click_style").bind("click", function(){
                $(this).removeClass("click_style").unbind("click");
                $("#initNav-navvalue").remove();
                $.YSContent.closeHiddenContent();
            });
            $("#initNav").find("h2").append("<span id='initNav-navvalue'><b>></b>"+navvalue+"</span>");
        }
    }
}
jQuery.YSContent= {
    //打开菜单中的连接 并可指定添加div的ID 这个方法只针对于左侧菜单调用
    openMenuUrl:function(o){
        var $o=$(o);
        $.YSPublic.historyUrl($o);
        //$.YSPublic.initNav($o);//动态根据菜单的值给导航赋值
        $.Mark.show();
        var YSId="YSContent";
        if($o.attr("ysid")!=null) YSId=$o.attr("ysid");
        url=$o.attr("shref");
        var sparam="params";
        if($o.attr("sparam")!=null)sparam=$o.attr("sparam");
        var svalue=null;
        if($o.attr("svalue")!=null)svalue=$o.attr("svalue");
        $("#"+YSId).load(url,function(){$.Mark.hide();});
    },
    //打开新的连接 并可指定添加div的ID
    openMenuUrlNew:function(o){
        var $o=$(o);
        $.Mark.show();
        var YSId="YSContent";
        if($o.attr("ysid")!=null) YSId=$o.attr("ysid");
        url=$o.attr("shref");
        var sparam="params";
        if($o.attr("sparam")!=null)sparam=$o.attr("sparam");
        var svalue=null;
        if($o.attr("svalue")!=null)svalue=$o.attr("svalue");
        $.ajax({
            type: 'POST',
            url: url,
            data:sparam+'='+svalue,
            cache: false,
            success: function(response){
                $.Mark.hide();
                $("#"+YSId).html(response);
                $.YSPublic.scollToTop();
            },
            error:function(a,b,c){
                $.YSAjax.error(a,b,c);
            }
        });
    },
    //打开菜单中的连接 并可指定添加div的ID
    openHiddenContent:function(o){
        var $o=$(o);
        $.Mark.show();
        var YSId="YSContent";
        if($o.attr("ysid")!=null) YSId=$o.attr("ysid");
        var YSCId="YSContentTwo"; //目前二级页面的id值固定
        //if($o.attr("yscid")!=null) YSCId=$o.attr("yscid");
        url=$o.attr("shref");
        var sparam="params";
        if($o.attr("sparam")!=null)sparam=$o.attr("sparam");
        var svalue=null;
        if($o.attr("svalue")!=null)svalue=$o.attr("svalue");
        //设置导航 添加新的导航及可点击导航的颜色
        var navvalue="详情";
        if($o.attr("navvalue")!=null)navvalue=$o.attr("navvalue");
        $.YSPublic.changeNav(navvalue);
        url=encodeURI(url);
        $.ajax({
            type: 'POST',
            url: url,
            data:sparam+'='+svalue,
            cache: false,
            success: function(response){
                $.Mark.hide();
                $YSId=$("#"+YSId);
                $YSCId=$("#YSContentTwo");
                $YSId.css("display","none");
                $YSCId.remove();
                $YSId.after("<div id='YSContentTwo' class='main-content'></div>");
                $("#YSContentTwo").html(response);
                $.YSPublic.scollToTop();
            },
            error:function(a,b,c){
                $.YSAjax.error(a,b,c);
            }
        });
    },
    closeHiddenContent:function(ysid){
        var YSId=ysid;
        if(YSId==null || YSId==undefined)
            var YSId="YSContent";;
        $("#"+YSId).css("display","block");
        $("#YSContentTwo").remove();
        $("#initNav").find("span.click_style").click();
        $.YSPublic.scollToTop();
    }

};
