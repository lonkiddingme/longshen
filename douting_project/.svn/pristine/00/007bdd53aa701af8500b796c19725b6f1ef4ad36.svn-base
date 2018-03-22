$(function(){
		//加载table信息
		query();
		
		//变换选择类型时清空输入栏
		$("#keyWord").change(function(){
			$("#keyValue").val("");
		});
	});

//搜索
function queryRole(){
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
		var sort = new Object();
		sort["ctime"] = 1;
		jsonObj["sort"]=sort;
		jsonObj["conditionParam"] = conditionParamObj;
		//分页信息
		jsonObj["pageNum"] = $("#pageNum").val();
		jsonObj["currentPage"] = $("#currentPage").val();
		
		jsonStr = JSON.stringify(jsonObj);
		//设置Controller的访问url
		var url="rms/custom/queryCustomList/";
		//获取数据		
		var data=tableData(jsonStr,url);
		if(data.code != undefined && data.code == 0){
			
			data=data.data;
			var list=data.results;
			//显示table数据
			if(list != "" && list != null){
				$.each(list,function(index,node){
					//拼接table
					var str="<tr>";
					if(node.companyName != undefined){
						str = str + "<td>"+node.companyName+"</td>";
					}else{
						str=str +"<td></td>";
					}
					if(node.companyId != undefined){
						str=str+"<td>"+node.companyId+"</td>";
					}else{
						str=str+"<td></td>";
					}
					if(node.companyGroup != undefined){
						str=str+"<td>"+node.companyGroup+"</td>";
					}else{
						str=str+"<td></td>";
					}
					if(node.companyTag != undefined){
						str=str+"<td>"+node.companyTag+"</td>";
					}else{
						str=str +"<td></td>";
					}
					var status="";
					var nstatus="";
					if(node.status != undefined && node.status==0){
						status = "启用";
						nstatus = "禁用";
					}
					if(node.status != undefined && node.status==1){
						nstatus = "启用";
						status = "禁用";
					}
					str=str+"<td>"+status+"</td>";
					if(node.otherConfig != undefined){
						str=str+"<td>"+node.otherConfig+"</td>";
					}else{
						str=str +"<td></td>";
					}
					if(node.ctime != undefined){
						str=str+"<td>"+node.ctime+"</td>";
					}else{
						str=str +"<td></td>";
					}
					str = str + "<td class='btn_td'>"+
					"<button type='button' class='btn-operate btn  btn-xs' "+
					"shref='rms/base/toEdit/"+node._id+"/?page=custom/editCustom'  onclick='$.YSContent.openMenuUrl(this)' ><i class=' icon-startUp'></i>修改</button>";
					if(node.companyId == "MR"){
						str +="</td></tr>";
					}else{
						str +="<button type='button' class='btn-operate btn btn-info btn-xs' onclick=status('"+node._id+"','"+node.status+"')><i class='icon-startUp'></i>"+nstatus+"</button>"+
						"<button type='button' class='btn-operate btn btn-info btn-xs' onclick=synchroUser('"+node.companyId+"')><i class='icon-startUp'></i>同步用户</button>"+
						"</td></tr>";
						
					}
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
function status(id,status){
	var jsonStr = "{}";
	var jsonObj = JSON.parse(jsonStr);
	jsonObj["id"] = id;
	if(status == 1){
		jsonObj["status"] = 0;
	}
	if(status == 0){
		jsonObj["status"] = 1;
	}
	jsonStr = JSON.stringify(jsonObj);
	//询问框
	layer.confirm('确认要更改吗？', {
	  btn: ['确认','取消'] //按钮
	}, function(){
	  $.ajax({
		  type:"POST",
			url:"rms/custom/editStatus/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.code != undefined && data.code == 0){
					layer.msg('更改成功', {icon: 1});
				}else{
					layer.msg('更改失败', {icon: 1});
				}
				query();
			}
	  });
	});
}
function synchroCustom(){
	layer.confirm('确认要同步租户吗？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
		  $.ajax({
			  type:"POST",
				url:"rms/synchro/synchroCustom/",
				contentType:"application/json ; charset=utf-8",
				dataType:"json",
				success:function(data){
					if(data.data != undefined && data.data == 0){
						layer.msg('同步成功', {icon: 1});
					}else{
						layer.msg('同步失败', {icon: 1});
					}
					query();
				}
		  });
		});
}
function synchroUser(companyId){
	var jsonStr = "{}";
	var jsonObj = JSON.parse(jsonStr);
	jsonObj["companyId"] = companyId;
	jsonStr = JSON.stringify(jsonObj);
	layer.confirm('确认要同步用户吗？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
		  $.ajax({
			  type:"POST",
				url:"rms/synchro/synchroUsers/",
				contentType:"application/json ; charset=utf-8",
				dataType:"json",
				data:jsonStr,
				success:function(data){
					if(data.data != undefined && data.data == 0){
						layer.msg('同步成功', {icon: 1});
					}else{
						layer.msg('同步失败', {icon: 1});
					}
					query();
				}
		  });
		});
}