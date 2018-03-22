function initValidate(form){
	$(form).validationEngine({
		showPrompts:false,
		maxErrorsPerField:1,
		custom_error_messages:{
			'required' :{
				message:"不能为空！"
			},
			'custom[phone]' :{
				message:"格式不正确！"
			},
			'custom[email]' :{
				message:"格式不正确！"
			}
		}
	});
	$('.doValidate').bind('jqv.field.result', function(event, field, isError, promptText){
		if(isError){
			field=$(field);
			var name_zh=$(field).attr("name_zh");
			var msg=field.siblings(".form_tip");
			var max=0;
			while(!msg.length>0&&max<10){
				max++;
				field = field.parent();
				msg=field.siblings(".form_tip");
			}
			msg.css("display","display");
			msg.css("color","red");
			msg.html(name_zh+promptText);
		}else{
			field=$(field);
			var msg=field.siblings(".form_tip");
			var max=0;
			while(!msg.length>0&&max<10){
				max++;
				field = field.parent();
				msg=field.siblings(".form_tip");
			}
			msg.css("display","none");
			msg.html("");
		}
	});
}
function tableData(data,url){
	var json="";
	$.ajax({
		type:"POST",
		url:url,
		async:false,
		contentType:"application/json ; charset=utf-8",
		dataType:"json",
		data:data,
		success:function(data){
			json = data;
		}
	});
	return json;
}

function initPages(pages){
	//分页初始化方法：
    $("#page").createPage({
        pageCount:pages.totalPage,//表示总页数
        current:pages.currentPage,//表示当前显示页数
        totalNum:pages.totalRecord,//表示总条数  如果不添加此参数  则没有总页数展示
        backFn:function(p){
        	$("#currentPage").val(p);
        	query();
        }
    });
}
function initCustom(search){
	var option = "<option value=''>请选择</option>";
	$("#company").html(option);
	$.ajax({
			type:"POST",
			url:"rms/custom/queryCustomAll/",
			contentType:"application/json ; charset=utf-8",
			dataType:"json",
			success:function(data){
				if(data.code == 0){
					var op = "";
					$.each(data.data,function(index,node){
						var companyName = node.companyName;
						var companyId = node.companyId;
						if(companyName.indexOf(search) >= 0 || companyId.indexOf(search) >= 0){
							op +='<option value="'+node.companyId+'">'+companyName+'</option>';
						}
					});
					$("#company").append(op);
				}		
			}
		});
}