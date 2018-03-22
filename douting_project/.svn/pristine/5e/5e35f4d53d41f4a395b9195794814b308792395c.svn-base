$(function(){
	initValidate("#roleForm");
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
		var zNodes =[];
		$.ajax({
			type:"POST",
			url:"rms/role/queryRoleById/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:'{"id":"'+$("#cid").val()+'"}',
			success:function(data){
				$("#name").val(data.data.name);
				$("#memo").val(data.data.memo);
				initCompany(data.data.companyId);
				initAuthority(data.data.authority);
				initMenuTree(data.data.menuTree);
			}
		});
		$("#company").change(function(){
			$.ajax({
				type:"POST",
				url:"rms/menu/getMenuByCompanyId/",
				async:false,
				contentType:"application/json ; charset=utf-8",
				dataType:"json",
				data:'{"companyId":"'+$(this).val()+'"}',
				success:function(data){
					if(data.code == 0){
						zNodes = data.data;
					}
				}
			});
			zTreeObj = $.fn.zTree.init($("#menuTree"), setting, zNodes);
		});
		$("#search").blur(function(){
				if((this.value).trim() != ""){
					initCustom((this.value).trim());
					alert("搜索完毕");
				}
			});
	});
	function tijiao(){
		var f=$("#roleForm").validationEngine("validate");
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
			var name=$("#name").val();
			var memo=$("#memo").val();
			var companyId = $("#company").val();
			var authority = $("#authority").val();
			var companyName = $("#company").find("option:selected").text();
			jsonObj["id"] = $("#cid").val();
			jsonObj["name"] = name;
			jsonObj["memo"] = memo;
			jsonObj["authority"] = authority;
			jsonObj["companyId"] = companyId;
			jsonObj["companyName"] = companyName;
			jsonObj["menuIds"] = menuIds;
			jsonStr = JSON.stringify(jsonObj);
			$.ajax({
				type:"POST",
				url:"rms/role/updateRole/",
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
	function checkName(){
		var isHad=false;
		var jsonStr = "{}";
		var jsonObj = JSON.parse(jsonStr);
		var name=$("#name").val();
		var companyId = $("#company").val();
		jsonObj["name"] = name;
		jsonObj["companyId"] = companyId;
		jsonObj["id"] = $("#cid").val();
		jsonStr = JSON.stringify(jsonObj);
		$.ajax({
			type:"POST",
			async:false,
			url:"rms/role/nameIsHad/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.data>0){
					isHad=true;
				}
			}
		});
		if(isHad){
			return "已存在！";
		}else{
			return true;
		}
	}
	function initCompany(companyId){
		$.ajax({
			type:"POST",
			url:"rms/custom/queryCustomAll/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			success:function(data){
				if(data.code == 0){
					var op = "";
					$.each(data.data,function(index,node){
						op +='<option value="'+node.companyId+'" ';
						if(node.companyId == companyId){
							op += ' selected="selected" ';
						}
						op +=' >'+node.companyName+'</option>';
					});
					$("#company").append(op);
				}		
			}
		});
	}
function initMenuTree(menuTree){
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
function initAuthority(authority){
	//查询条件初始化
	var jsonStr = "{}";
	var sort= "{}";
	var jsonObj = JSON.parse(jsonStr);
	var sortObj = JSON.parse(sort);
	//分页信息
	jsonObj["pageNum"] = 1000;
	jsonObj["currentPage"] = 1;
	sortObj["utime"] = -1;
	jsonObj["sort"] = sortObj;
	jsonStr = JSON.stringify(jsonObj);
	//设置Controller的访问url
	var url="rms/role/queryAuthority/";
	//获取数据		
	var data=tableData(jsonStr,url);
	if(data.code != undefined && data.code == 0){
		data=data.data;
		var list=data.results;
		//显示table数据
		if(list != "" && list != null){
			var op = "";
			$.each(list,function(index,node){
				op +='<option value="'+node.key+'" ';
				if(authority == node.key){
					op += ' selected="selected" ';
				}
				op += '>'+node.name+'</option>';
			});
			$("#authority").append(op);
		}
	}
}