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
			url:"rms/custom/queryCustomAll/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			success:function(data){
				if(data.code == 0){
					var op = "";
					$.each(data.data,function(index,node){
						op +='<option value="'+node.companyId+'" ';
						if(node.isDefault == "1"){
							op += 'selected="selected"';
							$.ajax({
								type:"POST",
								url:"rms/menu/getMenuByCompanyId/",
								async:false,
								contentType:"application/json ; charset=utf-8",
								dataType:"json",
								data:'{"companyId":"'+node.companyId+'"}',
								success:function(data){
									if(data.code == 0){
										zNodes = data.data;
									}
								}
							});
							zTreeObj = $.fn.zTree.init($("#menuTree"), setting, zNodes);
						}
						op+=' >'+node.companyName+'</option>';
					});
					$("#company").append(op);
				}		
			}
		});
		initAuthority();
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
		var authority=$("#authority").val();
		var companyId = $("#company").val();
		var companyName = $("#company").find("option:selected").text();
		jsonObj["name"] = name;
		jsonObj["companyId"] = companyId;
		jsonObj["companyName"] = companyName;
		jsonObj["authority"] = authority;
		jsonObj["memo"] = memo;
		jsonObj["isDefault"] = "1";
		jsonObj["menuIds"] = menuIds;
		jsonStr = JSON.stringify(jsonObj);
		$.ajax({
			type:"POST",
			url:"rms/role/createRole/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.code == 0){
					layer.msg('添加成功', {icon: 1});
				}else{
					layer.msg('添加失败:'+data.data, {icon: 1});
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
	function initAuthority(){
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
					op +='<option value="'+node.key+'">'+node.name+'</option>';
				});
				$("#authority").append(op);
			}
		}
	}