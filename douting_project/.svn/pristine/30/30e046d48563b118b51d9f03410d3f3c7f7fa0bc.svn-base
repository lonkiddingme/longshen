$(function(){
	initValidate("#departmentForm");
		$.ajax({
			type:"POST",
			url:"rms/department/queryDepartmentById/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:'{"id":"'+$("#cid").val()+'"}',
			success:function(data){
				$("#departmentName").val(data.data.name);
				$("#memo").val(data.data.memo);
				initCompany(data.data.companyId);
			}
		});
		$("#search").blur(function(){
			if((this.value).trim() != ""){
				initCustom((this.value).trim());
				alert("搜索完毕");
			}
		});
	});
	function tijiao(){
		var f=$("#departmentForm").validationEngine("validate");
		if(f){
			var jsonStr = "{}";
			var jsonObj = JSON.parse(jsonStr);
			var name=$("#departmentName").val();
			var memo=$("#memo").val();
			var companyId = $("#company").val();
			var companyName = $("#company").find("option:selected").text();
			jsonObj["id"] = $("#cid").val();
			jsonObj["name"] = name;
			jsonObj["memo"] = memo;
			jsonObj["companyId"] = companyId;
			jsonObj["companyName"] = companyName;
			jsonStr = JSON.stringify(jsonObj);
			$.ajax({
				type:"POST",
				url:"rms/department/updateDepartment/",
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
		var name=$("#departmentName").val();
		var companyId = $("#company").val();
		jsonObj["name"] = name;
		jsonObj["companyId"] = companyId;
		jsonObj["id"] = $("#cid").val();
		jsonStr = JSON.stringify(jsonObj);
		$.ajax({
			type:"POST",
			async:false,
			url:"rms/department/nameIsHad/",
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