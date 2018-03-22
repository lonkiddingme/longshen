$(function(){
	initValidate("#menuForm");
	var setting = {
        view: {
            addHoverDom: addHoverDom,//用于当鼠标移动到节点上时，显示用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
            removeHoverDom: removeHoverDom,//设置鼠标移到节点上，在后面显示一个按钮
            selectedMulti: false// 禁止多点同时选中的功能
        },
		data: {
			simpleData: {
				enable: true
			}
		}
		};
	var zNodes =[];
	$.ajax({
		type:"POST",
		url:"rms/menu/getMenuZtree/",
		async:false,
		contentType:"application/json ; charset=utf-8",
		dataType:"json",
		success:function(data){
			if(data.code == 0){
				zNodes = data.data;
			}
		}
	});
	zTreeObj = $.fn.zTree.init($("#menuTree"), setting, zNodes);
	
});
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    //删除子节点
    if(!treeNode.isParent && $("#removeBtn_" + treeNode.tId).length == 0){
    	var addStr2 = "<span class='button remove' id='removeBtn_"
			+ treeNode.tId
			+ "' title='remove node' onclick=\"deltreeNode('是否确认删除"+treeNode.name+"','"+treeNode.id+"');\"></span>";
	sObj.after(addStr2);
    }
    //更新节点
    if (treeNode.id != 1 && $("#editBtn_" + treeNode.tId).length == 0) {
    	var addStr1 = "<span class='button edit' id='editBtn_" + treeNode.tId
		+ "' title='edit node' ></span>";
    	sObj.after(addStr1);
    	var btn = $("#editBtn_" + treeNode.tId);
    	if (btn) btn.bind("click", function(){
    		initValues();
	    	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	    	var node = treeObj.getNodeByParam("id", treeNode.pId, null);
	    	$("#parentName").val(node.name);
	    	$("#nid").val(treeNode.id);
	    	$.ajax({
	    		type:"POST",
	    		url:"rms/menu/queryMenuById/",
	    		async:false,
	    		contentType:"application/json ; charset=utf-8",
	    		dataType:"json",
	    		data:'{"id":"'+treeNode.id+'"}',
	    		success:function(data){
	    			if(data.code == 0){
	    				var menu = data.data;
	    				$("#menuName").val(menu.name);
	    				if(menu.menuName == undefined){
	    					$("#treeName").val("");
	    				}else{
	    					$("#treeName").val(menu.menuName);
	    				}
	    				$("#menuUrl").val(menu.url);
	    				$("#menuIcon").val(menu.icon);
	    				$("#memo").val(menu.memo);
	    				$("#formDiv").css("display","display");
	    			}
	    		}
	    	});
	    });
    }
    //新增
    if (treeNode.isParent && $("#addBtn_" + treeNode.tId).length == 0) {
    	var addStr3 = "<span class='button add' style='margin-right:2px; background-position:-144px -0px; vertical-align:top; *vertical-align:middle' id='addBtn_"+
    	treeNode.tId + "' title='add node' ></span>";
    	sObj.after(addStr3);
    	var btn = $("#addBtn_" + treeNode.tId);
	    if (btn) btn.bind("click", function(){
	    	initValues();
	    	$("#formDiv").css("display","display");
	    	$("#parentName").val(treeNode.name);
	    });
    }
    if (!treeNode.isParent && treeNode.pId == 1 && $("#addBtn_" + treeNode.tId).length == 0) {
    	var addStr3 = "<span class='button add' style='margin-right:2px; background-position:-144px -0px; vertical-align:top; *vertical-align:middle' id='addBtn_"+
    	treeNode.tId + "' title='add node' ></span>";
    	sObj.after(addStr3);
    	var btn = $("#addBtn_" + treeNode.tId);
	    if (btn) btn.bind("click", function(){
	    	initValues();
	    	$("#formDiv").css("display","display");
	    	$("#parentName").val(treeNode.name);
	    });
    }
};
//设置鼠标移到节点上，在后面显示一个按钮
function removeHoverDom(treeId, treeNode) {
    $("#removeBtn_" + treeNode.tId).unbind().remove();
    $("#editBtn_" + treeNode.tId).unbind().remove();
    $("#addBtn_" + treeNode.tId).unbind().remove();
};
function tijiao(){
	var f=$("#menuForm").validationEngine("validate");
	if(f){
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	var parentNode = zTree.getSelectedNodes()[0];
	var jsonStr = "{}";
	var jsonObj = JSON.parse(jsonStr);
	var menuName = $("#menuName").val();
	var treeName = $("#treeName").val();
	var menuUrl = $("#menuUrl").val();
	var menuIcon = $("#menuIcon").val();
	var memo = $("#memo").val();
	var nid = $("#nid").val();
	jsonObj["name"] = menuName;
	jsonObj["menuName"] = treeName;
	jsonObj["url"] = menuUrl;
	jsonObj["icon"] = menuIcon;
	jsonObj["memo"] = memo;
	if(nid != ""){
		jsonObj["id"] = parentNode.id;
		jsonStr = JSON.stringify(jsonObj);
		$.ajax({
			type:"POST",
			url:"rms/menu/updateMenu/",
			async:false,
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.code == 0){
					$("#formDiv").css("display","none");
					if(treeName != ""){
						parentNode.name = treeName;
					}else{
						parentNode.name = menuName;
					}
						zTree.updateNode(parentNode);
					layer.msg('修改成功', {icon: 1});
				}else{
					layer.msg('修改失败', {icon: 1});
				}
			}
		});
	}else{
		jsonObj["pid"] = parentNode.id;
		jsonStr = JSON.stringify(jsonObj);
		$.ajax({
			type:"POST",
			url:"rms/menu/createMenu/",
			async:false,
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.code == 0){
					$("#formDiv").css("display","none");
					zTree.addNodes(parentNode, { id: data.data, pId: parentNode.id, name: menuName });
					var newNode = zTree.getNodeByParam("id",data.data, null);
					zTree.selectNode(newNode);
					layer.msg('添加成功', {icon: 1});
				}else{
					layer.msg('添加失败', {icon: 1});
				}
			}
		});
	}
	}
}
function deltreeNode(msg,id){
	$("#formDiv").css("display","none");
	layer.confirm(msg, {
		  btn: ['确认','取消'] //按钮
		}, function(){
			$.ajax({
				type:"POST",
				url:"rms/menu/delMenu/",
				async:false,
				contentType:"application/json ; charset=utf-8",
				dataType:"json",
				data:'{"id":"'+id+'"}',
				success:function(data){
					if(data.code == 0){
						var zTree = $.fn.zTree.getZTreeObj("menuTree");
						var parentNode = zTree.getNodeByParam("id", id, null);
						zTree.removeNode(parentNode);
						layer.msg('删除成功', {icon: 1});
					}else{
						layer.msg('删除失败', {icon: 1});
					}
				}
			});
		});
}
function initValues(){
	$("#menuName").val("");
	$("#treeName").val("");
	$("#menuUrl").val("");
	$("#memo").val("");
	$("#menuIcon").val("");
	$("#nid").val("");
}