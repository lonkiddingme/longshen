$(function(){
	initValidate("#userForm");
	 $.ajax({
		  type:"POST",
			url:"rms/user/queryUserById/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:'{"id":"'+$("#cid").val()+'"}',
			success:function(data){
				var user = data.data;
				if(user != undefined && user != null){
					$("#name").val(user.userName);
					$("#realName").val(user.realName);
					if(user.sex == 1){
						$("input:radio[value=1]").prop("checked",true);
					}
					if(user.sex == 0){
						$("input:radio[value=0]").prop("checked",true);
					}
					$("#email").val(user.email);
					$("#emailAddress").val(user.emailAddress);
					$("#phone").val(user.phone);
					$("#area").val(user.area);
					companyList(user.companyId);
					roleList(user.roleId,user.companyId);
					departmentList(user.departmentId,user.companyId);
				}
			}
	  });
	$("#search").blur(function(){
		if((this.value).trim() != ""){
			initCustom((this.value).trim());
			alert("搜索完毕");
		}
	});
	 $("#company").change(function(){
		 var companyId = $(this).val();
		 $("#role").empty();
		 $("#department").empty();
		 var op="";
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
					$.each(department,function(index,node){
						var opd="<option value='"+node._id+"'>"+ node.name +"</option>";
						$("#department").append(opd);
					});
				}
		 });
	 });
});

function roleList(roleId,companyId){
	 $.ajax({
		    type:"POST",
			url:"rms/role/queryRoleByCompanyId/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:'{"companyId":"'+companyId+'"}',
			success:function(data){
				var role = data.data;
				$.each(role,function(index,node){
					var op="<option value='"+node._id+"' ";
					if(node._id == roleId){
						op = op + "selected = 'selected'>";
					}else{
						op = op + ">";
					}
					op = op + node.name +"</option>";
					$("#role").append(op);
				});
			}
	 });
}
function companyList(companyId){
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
						op += 'selected = "selected"';
					}
					op +=' >'+node.companyName+'</option>';
				});
				$("#company").append(op);
			}		
		}
	});
}
function departmentList(departmentId,companyId){
	$.ajax({
	    type:"POST",
		url:"rms/department/queryDepartmentByCompanyId/",
		contentType:"application/json ; charset=utf-8",
		dataType:"json",
		data:'{"companyId":"'+companyId+'"}',
		success:function(data){
			var department = data.data;
			$.each(department,function(index,node){
				var op="<option value='"+node._id+"' ";
				if(node._id == departmentId){
					op = op + "selected = 'selected'>";
				}else{
					op = op + ">";
				}
				op = op + node.name +"</option>";
				$("#department").append(op);
			});
		}
 });
}
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
	jsonObj["id"] = $("#cid").val();
	jsonObj["userName"] = name;
	jsonObj["name"] = name;
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
		url:"rms/user/updateUserById/",
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
	jsonObj["key"] = "email";
	jsonObj["value"] = name;
	jsonObj["id"] = $("#cid").val();
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