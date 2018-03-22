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
		jsonObj["conditionParam"] = conditionParamObj;
		//分页信息
		jsonObj["pageNum"] = $("#pageNum").val();
		jsonObj["currentPage"] = $("#currentPage").val();
		
		jsonStr = JSON.stringify(jsonObj);
		//设置Controller的访问url
		var url="rms/role/queryRoleList/";
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
						str = str + "<td>"+node.name+"</td>";
					}else{
						str=str +"<td></td>";
					}
					if(node.companyName != undefined){
						str=str+"<td>"+node.companyName+"</td>";
					}else{
						str=str+"<td></td>";
					}
					if(node.userName != undefined){
						str=str+"<td>"+node.userName+"</td>";
					}else{
						str=str +"<td></td>";
					}
					if(node.ctime != undefined){
						str=str+"<td>"+node.ctime+"</td>";
					}else{
						str=str +"<td></td>";
					}
					if(node.memo != undefined){
						str=str+"<td>"+node.memo+"</td>";
					}else{
						str=str +"<td></td>";
					}
					str = str + "<td class='btn_td'>"+
					"<button type='button'  class='btn-operate btn  btn-xs' "+
					"shref='rms/base/toEdit/"+node._id+"/?page=role/editRole'  onclick='$.YSContent.openMenuUrl(this)' ><i class=' icon-startUp'></i>修改</button>";
					if(node.companyId == "MR"){
						str = str + "</td></tr>";
					}else{
						str = str +	"<button type='button' class='btn-operate btn btn-info btn-xs' onclick=del('"+node._id+"')><i class='icon-startUp'></i>删除</button>"+
						"</td></tr>";
					}
					$("#textBody").append(str);
				});
			}
			//分页设置
			initPages(data);
		}
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
			url:"rms/role/delRoleById/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.code != undefined && data.code == 0){
					layer.msg('删除成功', {icon: 1});
				}else{
					layer.msg('删除失败', {icon: 1});
				}
				queryRole();
			}
	  });
	});
}
function authority(){
	var index = layer.open({
		  type: 1,
		  title: '权限管理',
		  shade: 0.3,
		  area: ['90%', '90%'],
		  content: '<div class="m_screen_div clearfloat" style="padding:9px 0;">\
			  <div class="form fl">\
			  <p style="margin-left:20px;">名称：<input type="text" id="authorityName" placeholder="请输入查询内容"></p>\
			  <input type="hidden" id="authorityPageNum" value="6">\
			  <input type="hidden" id="authorityCurrentPage" value="1">\
			  <button type="button" class="btn-info btn btn-xs search_btn" onclick="initAuthority()"><i class="icon-search"></i>搜索</button>\
			  </div></div>\
			  <div class="m_table_div" style="height:75%;">\
			  <table class="table">\
			   <thead>\
			  	 <tr><th>序号</th><th>名称</th><th>权限</th><th>操作</th><tr>\
			   </thead>\
			  	<tbody id="authorityBody">\
				</tbody>\
			  </table>\
			  </div>\
			  <ul class="page" id="authorityPage" style="margin:0 0;">\
			  </ul>\
			  <div align="center" style="margin-top:10px">\
			  <button type="button"  class="btn-info btn btn-xs search_btn" onclick=editAuthority("","","")>新增</button>\
			  <button type="button"  class="btn-info btn btn-xs search_btn" onclick="layer.closeAll()">关闭</button>\
			  </div>'
		});
	initAuthority();
}
function initAuthority(){
	$("#authorityBody").html("");
	//查询条件初始化
	var jsonStr = "{}";
	var sort= "{}";
	var jsonObj = JSON.parse(jsonStr);
	var sortObj = JSON.parse(sort);
	var keyValue = $("#authorityName").val();
	if(keyValue != ""){
		jsonObj["keyWord"] = "name";
		jsonObj["keyValue"] = keyValue;
	}
	//分页信息
	jsonObj["pageNum"] = $("#authorityPageNum").val();
	jsonObj["currentPage"] = $("#authorityCurrentPage").val();
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
			$.each(list,function(index,node){
				//拼接table
				var str="<tr>";
					str = str + "<td>"+(index+1)+"</td>";
				if(node.name != undefined){
					str=str+"<td>"+node.name+"</td>";
				}else{
					str=str+"<td></td>";
				}
				if(node.key != undefined){
					str=str+"<td>"+node.key+"</td>";
				}else{
					str=str +"<td></td>";
				}
				str = str + "<td class='btn_td'>"+
				"<button type='button'  class='btn-operate btn  btn-xs editauth' onclick=editAuthority('"+node._id+"','"+node.name+"','"+node.key+"')><i class=' icon-startUp'></i>修改</button>";
				str = str + "</td></tr>";
				$("#authorityBody").append(str);
			});
		}
		//分页设置
		 $("#authorityPage").createPage({
		        pageCount:data.totalPage,//表示总页数
		        current:data.currentPage,//表示当前显示页数
		        totalNum:data.totalRecord,//表示总条数  如果不添加此参数  则没有总页数展示
		        backFn:function(p){
		        	$("#authorityCurrentPage").val(p);
		        	initAuthority();
		        }
		    });
	}
}
function editAuthority(id,name,key){
	layer.closeAll();
	if(id == ""){
		layer.open({
			  type: 1,
			  title: '新增权限',
			  shade: 0.3,
			  area: ['50%', '50%'],
			  content:'<div class="m_screen_div clearfloat" style="padding:9px 0;">\
				  <div class="form fl">\
				  <p style="margin:20px 20px;float:none;">名称：<input type="text" id="addName" placeholder="请输入内容"></p>\
				  <p style="margin:20px 20px;float:none;">权限：<label class="select_label conditions">\
					<select class="resourceselect" id="addKey" onchange = "showOther()" style="width:180px">\
						<option value="companyId">所属租户</option>\
						<option value="departmentId">所属部门</option>\
						<option value="userId">所属用户</option>\
				  		<option value="companyGroup">所属租户圈</option>\
				  		<option value="companyTag">所属租户标签</option>\
				  		<option value="">其他</option>\
					</select>\
				  </label></p>\
				  <p id="otherp" style="margin:20px 20px;float:none;display:none">其他：<input type="text" id="otherKey" placeholder="请输入内容"></p>\
				  </div></div>\
				  <div align="center" style="margin-top:10px">\
				  <button type="button"  class="btn-info btn btn-xs search_btn" onclick="saveAuthority()">保存</button>\
				  </div>'
		});
	}else{
		layer.open({
			  type: 1,
			  title: '修改权限',
			  shade: 0.3,
			  area: ['50%', '50%'],
			  content:'<div class="m_screen_div clearfloat" style="padding:9px 0;">\
				  <div class="form fl">\
				  <p style="margin:20px 20px;float:none;">名称：<input type="text" id="addName" placeholder="请输入内容"></p>\
				  <p style="margin:20px 20px;float:none;">权限：<label class="select_label conditions">\
					<select class="resourceselect" id="addKey" onchange = "showOther()" style="width:180px">\
						<option value="companyId">所属租户</option>\
						<option value="departmentId">所属部门</option>\
						<option value="userId">所属用户</option>\
				  		<option value="companyGroup">所属租户圈</option>\
				  		<option value="companyTag">所属租户标签</option>\
				  		<option value="">其他</option>\
					</select>\
				  </label></p>\
				  <p id="otherp" style="margin:20px 20px;float:none;display:none">其他：<input type="text" id="otherKey" placeholder="请输入内容"></p>\
				  </div></div>\
				  <div align="center" style="margin-top:10px">\
				  <button type="button"  class="btn-info btn btn-xs search_btn" onclick=updateAuthority("'+id+'")>保存</button>\
				  </div>'
		});
		$("#addName").val(name);
		if(key == "companyId" || key == "departmentId" || key == "userId"){
			$("#addKey").val(key);
		}else if(key == "companyGroup" || key == "companyTag"){
			$("#addKey").val(key);
		}else{
			$("#addKey").val("");
			$("#otherp").css("display","block");
			$("#otherKey").val(key);
		}
	}
}
function showOther(){
	var addkey = $("#addKey").val();
	if(addkey == ""){
		$("#otherp").css("display","block");
	}else{
		$("#otherp").css("display","none");
	}
}
function saveAuthority(){
	var jsonStr = "{}";
	var jsonObj = JSON.parse(jsonStr);
	var name = $("#addName").val();
	var addkey = $("#addKey").val();
	var otherkey = $("#otherKey").val();
	if(name != ""){
		jsonObj["name"] = name;
	}else{
		layer.msg('名称不能为空');
		return ;
	}
	if(addkey !=""){
		jsonObj["key"] = addkey;
	}else if(otherkey !=""){
		jsonObj["key"] = otherkey;
	}else{
		layer.msg('权限不能为空');
		return ;
	}
	jsonStr = JSON.stringify(jsonObj);
	//询问框
	  $.ajax({
		  type:"POST",
			url:"rms/role/editAuthority/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.code != undefined && data.code == 0){
					layer.msg('添加成功', {icon: 1});
				}else{
					layer.msg('添加失败', {icon: 1});
				}
				layer.closeAll();
				authority();
			}
	  });
}
function updateAuthority(id){
	var jsonStr = "{}";
	var jsonObj = JSON.parse(jsonStr);
	var name = $("#addName").val();
	var addkey = $("#addKey").val();
	var otherkey = $("#otherKey").val();
	jsonObj["id"] = id;
	if(name != ""){
		jsonObj["name"] = name;
	}else{
		layer.msg('名称不能为空');
		return ;
	}
	if(addkey !=""){
		jsonObj["key"] = addkey;
	}else if(otherkey !=""){
		jsonObj["key"] = otherkey;
	}else{
		layer.msg('权限不能为空');
		return ;
	}
	jsonStr = JSON.stringify(jsonObj);
	//询问框
	$.ajax({
		type:"POST",
		url:"rms/role/editAuthority/",
		contentType:"application/json ; charset=utf-8",
		dataType:"json",
		data:jsonStr,
		success:function(data){
			if(data.code != undefined && data.code == 0){
				layer.msg('修改成功', {icon: 1});
			}else{
				layer.msg('修改失败', {icon: 1});
			}
			layer.closeAll();
			authority();
		}
	});
}