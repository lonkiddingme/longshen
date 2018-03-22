$(function(){
		initValidate("#departmentForm");
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
							op += 'selected = "selected"';
						}
						op +=' >'+node.companyName+'</option>';
					});
					$("#company").append(op);
				}		
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
		jsonObj["name"] = name;
		jsonObj["companyId"] = companyId;
		jsonObj["companyName"] = companyName;
		jsonObj["isDefault"] = "1";
		jsonObj["memo"] = memo;
		jsonStr = JSON.stringify(jsonObj);
		$.ajax({
			type:"POST",
			url:"rms/department/createDepartment/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			data:jsonStr,
			success:function(data){
				if(data.code == 0){
					layer.msg('添加成功', {icon: 1});
				}else{
					layer.msg('添加失败'+data.data, {icon: 1});
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