$(function(){
	initValidate("#userForm");
	initCustom("");
	$("#search").blur(function(){
		if((this.value).trim() != ""){
			initCustom((this.value).trim());
			alert("搜索完毕");
		}
	});
	 $("#company").change(function(){
		 var companyId = $(this).val();
		 var op="<option value=''>请选择</option>";
		 $("#role").empty();
		 $("#department").empty();
		 if(companyId == ""){
			 $("#role").append(op);
			 $("#department").append(op);
			 return ;
		 }
		 $.ajax({
			    type:"POST",
				url:"rms/role/queryRoleByCompanyId/",
				contentType:"application/json ; charset=utf-8",
				dataType:"json",
				data:'{"companyId":"'+companyId+'"}',
				success:function(data){
					var role = data.data;
					$.each(role,function(index,node){
						op +="<option value='"+node._id+"'>"+ node.name +"</option>";
					});
					$("#role").append(op);
				}
		 });
		 $.ajax({
			    type:"POST",
				url:"rms/department/queryDepartmentByCompanyId/",
				contentType:"application/json ; charset=utf-8",
				dataType:"json",
				data:'{"companyId":"'+companyId+'"}',
				success:function(data){
					var department = data.data;
					var opd = "<option value=''>请选择</option>";
					$.each(department,function(index,node){
						opd +="<option value='"+node._id+"'>"+ node.name +"</option>";
					});
					$("#department").append(opd);
				}
		 });
	 });
});
function tijiao(){
	var f=$("#userForm").validationEngine("validate");
	if(f){
	var jsonStr = "{}";
	var jsonObj = JSON.parse(jsonStr);
	//var portrait = $("#portrait").val();
	var name=$("#name").val();
	var realName = $("#realName").val();
	var sex = $("input[name = sex]:checked").val();
	var roleId = $("#role option:selected").val();
	var roleName = $("#role option:selected").text();
	var companyId = $("#company option:selected").val();
	var companyName = $("#company option:selected").text();
	var departmentId = $("#department option:selected").val();
	var departmentName = $("#department option:selected").text();
	var email = $("#email").val();
	var emailAddress = $("#emailAddress").val();
	var phone = $("#phone").val();
	var area = $("#area").val();
	jsonObj["name"] = name;
	jsonObj["userName"] = name;
	jsonObj["type"] = "admin";
	//jsonObj["portrait"] = portrait;
	jsonObj["realName"] = realName;
	jsonObj["sex"] = sex;
	jsonObj["area"] = area;
	jsonObj["roleId"] = roleId;
	jsonObj["companyId"] = companyId;
	jsonObj["companyName"] = companyName;
	jsonObj["email"] = email;
	jsonObj["emailAddress"] = emailAddress;
	jsonObj["phone"] = phone;
	jsonStr = JSON.stringify(jsonObj);
	$.ajax({
		type:"POST",
		url:"rms/user/createUser/",
		contentType:"application/json ; charset=utf-8",
		dataType:"json",
		data:jsonStr,
		success:function(data){
			if(data.code == 0){
				layer.msg('添加成功', {icon: 1});
			}else{
				layer.msg('添加失败', {icon: 1});
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
	var name=$("#email").val();
	jsonObj["key"] = "email";
	jsonObj["value"] = name;
	jsonStr = JSON.stringify(jsonObj);
	$.ajax({
		type:"POST",
		async:false,
		url:"rms/user/nameIsHad/",
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