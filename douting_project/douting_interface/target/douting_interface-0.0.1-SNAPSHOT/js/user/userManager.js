$(function(){
		//加载table信息
		query();
		
		/*//全选
		$("#selectAll").click(function(){
			var ischecked=this.checked;
			var ids=$(".ids");
			$.each(ids,function(index,node){
			if(ischecked){
				$(node).prop("checked",true);
			}else{
				$(node).prop("checked",false);
			}
			});
		});*/
		
		//变换选择类型时清空输入栏
		$("#keyWord").change(function(){
			$("#keyValue").val("");
		});
	});

//搜索
function queryUser(){
	$("#currentPage").val("1");
	query();
}
function query(){
	$("#textBody").html("");
		//查询条件初始化
		var jsonStr = "{}";
		var conditionParamStr= "{}";
		var jsonObj = JSON.parse(jsonStr);
		var conditionParamObj = JSON.parse(conditionParamStr);
		var keyWord = $("#keyWord").val();
		var keyValue = $("#keyValue").val();
		if(keyWord == "status" && keyValue == "启用"){
			keyValue = "0";
		}
		if(keyWord == "status" && keyValue == "禁用"){
			keyValue = "1";
		}
		var startTime= $("#startTime").val();
		var endTime= $("#endTime").val();
		if(keyWord != ""){
			if($.trim(keyValue) != ""){
				jsonObj["keyWord"] = keyWord;
				jsonObj["keyValue"] = keyValue;
				//conditionParamObj[keyWord] = keyValue;
			}
		}
		if(startTime != ""){
			jsonObj["startTime"]=startTime;
		}
		if(endTime != ""){
			jsonObj["endTime"]=endTime;
		}
		jsonObj["keyTime"]="ctime";
		jsonObj["conditionParam"] = conditionParamObj;
		//分页信息
		jsonObj["pageNum"] = $("#pageNum").val();
		jsonObj["currentPage"] = $("#currentPage").val();
		
		jsonStr = JSON.stringify(jsonObj);
		//设置Controller的访问url
		var url="rms/user/queryUserList/";
		//获取数据		
		var data=tableData(jsonStr,url);
		if(data.code != undefined && data.code == 0){
			
			data=data.data;
			var list=data.results;
			//显示table数据
			if(list != undefined && list != null){
				$.each(list,function(index,node){
					//显示数字转汉字
					var status = "";
					var nstatus = "";
					if(node.status != undefined && node.status == 0){
						status = "启用";
						nstatus = "禁用";
					}
					if(node.status != undefined && node.status == 1){
						status = "禁用";
						nstatus = "启用";
					}
					//拼接table
					var str="<tr>";
					if(node.userName != undefined){
						str = str + "<td>"+node.userName+"</td>";
					}else{
						str=str +"<td></td>";
					}
					if(node.sex != undefined){
						str = str + "<td>"+node.sex+"</td>";
					}else{
						str = str + "<td></td>";
					}
					if(node.area != undefined){
						str=str+"<td>"+node.area+"</td>";
					}else{
						str=str+"<td></td>";
					}
					str=str+"<td>"+status+"</td>";
					if(node.emailAddress != undefined){
						str=str+"<td>"+node.emailAddress+"</td>";
					}else{
						str=str+"<td></td>";
					}
					if(node.phone != undefined){
						str=str+"<td>"+node.phone+"</td>";
					}else{
						str=str +"<td></td>";
					}
					if(node.ctime != undefined){
						str=str+"<td>"+node.ctime+"</td>";
					}else{
						str=str +"<td></td>";
					}
					str = str + "<td class='btn_td'>"+
					"<button type='button'  class='btn-operate btn  btn-xs' "+
					"shref='rms/base/toEdit/"+node._id+"/?page=user/editUser'  onclick='$.YSContent.openMenuUrl(this)' ><i class=' icon-startUp'></i>修改</button>"+
					"<button type='button' class='btn-operate btn btn-info btn-xs' onclick=state('"+node._id+"','"+node.status+"')><i class='icon-startUp'></i>"+nstatus+"</button>"+
					"</td></tr>";
					$("#textBody").append(str);
				});
			}
			//分页设置
			initPages(data);
		}
	}
function edit(id){
	 layer.msg('修改成功', {icon: 1});
}
function state(id,status){
	var jsonStr = "{}";
	var jsonObj = JSON.parse(jsonStr);
	var nstauts="无状态";
	if(status==1){
		nstauts="启用";
		jsonObj["status"] = "0";
	}
	if(status==0){
		nstauts="禁用";
		jsonObj["status"] = "1";
	}
	jsonObj["id"] = id;
	jsonStr = JSON.stringify(jsonObj);
	
	layer.confirm('确认要'+nstauts+'吗？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
			 $.ajax({
				  type:"POST",
					url:"rms/user/editStatus/",
					contentType:"application/json ; charset=utf-8",
					dataType:"json",
					data:jsonStr,
					success:function(data){
						if(data.code != undefined && data.code == 0){
							  layer.msg(nstauts+'成功', {icon: 1});
						}else{
							  layer.msg(nstauts+'失败', {icon: 1});
						}
						query();
					}
			  });
		});	
}
function synchroAllUser(){
	var jsonStr = "{}";
	layer.confirm('确认要同步全部用户吗？', {
		  btn: ['确认','取消'] //按钮
		}, function(index){
			layer.close(index);
		  $.ajax({
			  type:"POST",
				url:"rms/synchro/synchroAllUsers/",
				contentType:"application/json ; charset=utf-8",
				dataType:"json",
				data:jsonStr,
				success:function(data){
					if(data.data != undefined && data.code == 0){
						layer.msg('同步成功', {icon: 1});
					}else{
						var msg ="";
						$.each(data.data,function(index,node){
							msg +=node+",";
						});
						msg = msg.substring(0, msg.length-1);
						layer.confirm(msg+'同步失败', {
							btn: '确定', //按钮
							area: ['490px', ''],
							title:'提示'
						});
					}
					query();
				}
		  });
		});
}