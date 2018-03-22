$(function(){
		//加载table信息
		query();
		
		
		//变换选择类型时清空输入栏
		$("#keyWord").change(function(){
			$("#keyValue").val("");
		});
	});
//搜索
function queryDepartment(){
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
		jsonObj["conditionParam"] = conditionParamObj;
		//分页信息
		jsonObj["pageNum"] = $("#pageNum").val();
		jsonObj["currentPage"] = $("#currentPage").val();
		
		jsonStr = JSON.stringify(jsonObj);
		//设置Controller的访问url
		var url="rms/department/queryDepartmentList/";
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
					if(node.name != undefined){
						str = str+"<td>"+node.name+"</td>";
					}else{
						str = str+"<td></td>";
					}
					if(node.companyName != undefined){
						str = str+"<td>"+node.companyName+"</td>";
					}else{
						str = str+"<td></td>";
					}
					if(node.userName != undefined ){
						str = str+"<td>"+node.userName+"</td>";
					}else{
						str = str+"<td></td>";
					}
					if(node.ctime != undefined){
						str = str+"<td>"+node.ctime+"</td>";
					}else{
						str = str+"<td></td>";
					}
					if(node.memo != undefined){
						str = str+"<td>"+node.memo+"</td>";
					}else{
						str = str+"<td></td>";
					}
					str = str+"<td class='btn_td'>"+
					"<button type='button'  class='btn-operate btn  btn-xs' "+
					"shref='rms/base/toEdit/"+node._id+"/?page=department/editDepartment'  onclick='$.YSContent.openMenuUrl(this)' ><i class=' icon-startUp'></i>修改</button>";
					if(node.companyId == "MR"){
						str +="</td></tr>";
					}else{
						str +="<button type='button' class='btn-operate btn btn-info btn-xs' onclick=del('"+node._id+"')><i class='icon-startUp'></i>删除</button>"+
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
function del(id){
	var jsonStr = "{}";
	var jsonObj = JSON.parse(jsonStr);
	var idsArr = Array();
	idsArr.push(id);
	jsonObj["ids"] = idsArr;
	jsonStr = JSON.stringify(jsonObj);
	//询问框
	layer.confirm('确认要删除吗？', {
	  btn: ['确认','取消'] //按钮
	}, function(){
	  $.ajax({
		  type:"POST",
			url:"rms/department/delDepartmentById/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.code != undefined && data.code == 0){
					layer.msg('删除成功', {icon: 1});
				}else{
					layer.msg('删除失败', {icon: 1});
				}
				queryDepartment();
			}
	  });
	});
}