$(function(){
	initValidate("#customForm");
		$.ajax({
			type:"POST",
			url:"rms/custom/queryCustomById/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:'{"id":"'+$("#cid").val()+'"}',
			success:function(data){
				$("#companyName").val(data.data.companyName);
				$("#companyId").val(data.data.companyId);
				$("#companyGroup").val(data.data.companyGroup);
				$("#companyTag").val(data.data.companyTag);
				$("#otherConfig").val(data.data.otherConfig);
				initMenu(data.data.menuTree);
			}
		});
	});
	function tijiao(){
		var f=$("#customForm").validationEngine("validate");
		if(f){
			var zTree = $.fn.zTree.getZTreeObj("menuTree");
			var menus=zTree.getCheckedNodes(true);
			if(!(menus.length>0)){
				$("#menuMsg").css("display","display");
				$("#menuMsg").css("color","red");
				$("#menuMsg").html("菜单不能为空！");
				return ;
			}
			var menuIds = Array();
			$.each(menus,function(index,node){
				menuIds.push(node.id);
			});
			var jsonStr = "{}";
			var jsonObj = JSON.parse(jsonStr);
			var companyName=$("#companyName").val();
			var companyId=$("#companyId").val();
			var companyGroup=$("#companyGroup").val();
			var companyTag=$("#companyTag").val();
			var otherConfig=$("#otherConfig").val();
			jsonObj["id"] = $("#cid").val();
			jsonObj["menuIds"] = menuIds;
			jsonObj["companyName"] = companyName;
			jsonObj["companyId"] = companyId;
			jsonObj["companyGroup"] = companyGroup;
			jsonObj["companyTag"] = companyTag;
			jsonObj["otherConfig"] = otherConfig;
			jsonStr = JSON.stringify(jsonObj);
			$.ajax({
				type:"POST",
				url:"rms/custom/updateCustom/",
				contentType:"application/json ; charset=utf-8",
				dataType:"json",
				data:jsonStr,
				success:function(data){
					if(data.code == 0){
						layer.msg('修改成功', {icon: 1});
					}else{
						layer.msg('修改失败', {icon: 1});
					}
					$("#toList").click();
				}
			});
		}
	}
	function checkCompanyId(field, rules, i, options){
		var isHad=true;
		var jsonStr = "{}";
		var jsonObj = JSON.parse(jsonStr);
		var companyId=$("#companyId").val();
		jsonObj["companyId"] = companyId;
		jsonObj["id"] = $("#cid").val();
		jsonStr = JSON.stringify(jsonObj);
		$.ajax({
			type:"POST",
			async:false,
			url:"rms/custom/customIsHad/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.data>0){
					isHad=false;
				}
			}
		});
		if(isHad){
			return true;
		}else{
			return "已存在";
		}
	}
	function initMenu(menuTree){
		var setting = {
				check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: {
						"Y":"ps",
						"N":"ps"
					}
				},
				data: {
					simpleData: {
						enable: true
					}
				}
				};
			zTreeObj = $.fn.zTree.init($("#menuTree"), setting, menuTree);
	}