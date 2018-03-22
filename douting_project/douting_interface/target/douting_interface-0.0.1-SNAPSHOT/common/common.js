/**
 * 
 */
/**
 * 获取登陆用户
 * 
 * @param callbackFunction
 *            回调方法
 * @param callbackData
 *            回调数据
 */
function getLoginUser(callbackFunction, callbackData) {
	$.YSContent.postAjax(URL_FUNCTION.getFullUrl(URL_PATH.GET_LOGIN_USER), null, function(
			callbackData, responseData) {
		var user = null;
		if (responseData.code == 0 && null != responseData.data) {
			user = responseData.data;
			LOGIN_USER_FUNCTION.setLoginUser(user);
		}
		if(typeof(callbackFunction) == 'function'){
			var data = {
				user : user,
				data : callbackData
			};
			callbackFunction(data);
		}
		
	}, null, callbackData);
}

/**
 * 检查特殊字符
 * 
 * @param str
 * @returns {Boolean}
 */
function checkSpecialC(str) {
	// 只能输入汉字、字母和数字
	var reg = /^[\u4E00-\u9FA5\uf900-\ufa2d\w\s]*$/;
	if (str != str.match(reg)) {
		// alert("类型名称不能输入特殊字符");
		return false;
	} else {
		return true;
	}
}

/**
 * 获取字符串长度
 * 
 * @param str
 * @returns {Number}
 */
function getLength(str) {
	var realLength = 0, len = str.length, charCode = -1;
	for (var i = 0; i < len; i++) {
		charCode = str.charCodeAt(i);
		if (charCode >= 0 && charCode <= 128) {
			realLength += 1;
		} else {
			/* realLength += 2; */// 原本汉字每一个为2个字符，这里做一下限制，也为一个字符
			/**
			 * @param message
			 * @returns
			 */
			/**
			 * @param message
			 */
			realLength += 1;
		}
	}
	return realLength;
};

function alertMessage(message) {
	alert(message);
}

/**
 * 获取显示字符串
 * 
 * @param str
 * @returns
 */
function getShowStr(str) {
	if (typeof (str) == 'undefined' || null == str || 'null' == str) {
		return '';
	}
	return str;
}

/**
 * 文本框根据输入内容自适应高度
 * 
 * @param {HTMLElement}
 *            输入框元素
 * @param {Number}
 *            设置光标与输入框保持的距离(默认0)
 * @param {Number}
 *            设置最大高度(可选)
 */
var autoTextarea = function(elem, extra, maxHeight) {
	extra = extra || 0;
	var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window, isOpera = !!window.opera
			&& !!window.opera.toString().indexOf('Opera'), addEvent = function(
			type, callback) {
		elem.addEventListener ? elem.addEventListener(type, callback, false)
				: elem.attachEvent('on' + type, callback);
	}, getStyle = elem.currentStyle ? function(name) {
		var val = elem.currentStyle[name];

		if (name === 'height' && val.search(/px/i) !== 1) {
			var rect = elem.getBoundingClientRect();
			return rect.bottom - rect.top - parseFloat(getStyle('paddingTop'))
					- parseFloat(getStyle('paddingBottom')) + 'px';
		}
		;

		return val;
	} : function(name) {
		return getComputedStyle(elem, null)[name];
	}, minHeight = parseFloat(getStyle('height'));

	elem.style.resize = 'none';

	var change = function() {
		var scrollTop, height, padding = 0, style = elem.style;

		if (elem._length === elem.value.length)
			return;
		elem._length = elem.value.length;

		if (!isFirefox && !isOpera) {
			padding = parseInt(getStyle('paddingTop'))
					+ parseInt(getStyle('paddingBottom'));
		}
		;
		scrollTop = document.body.scrollTop
				|| document.documentElement.scrollTop;

		elem.style.height = minHeight + 'px';
		if (elem.scrollHeight > minHeight) {
			if (maxHeight && elem.scrollHeight > maxHeight) {
				height = maxHeight - padding;
				style.overflowY = 'auto';
			} else {
				height = elem.scrollHeight - padding;
				style.overflowY = 'hidden';
			}
			;
			style.height = height + extra + 'px';
			scrollTop += parseInt(style.height) - elem.currHeight;
			document.body.scrollTop = scrollTop;
			document.documentElement.scrollTop = scrollTop;
			elem.currHeight = parseInt(style.height);
		}
		;
	};

	addEvent('propertychange', change);
	addEvent('input', change);
	addEvent('focus', change);
	change();
};

var OnairJsUtil = {
	getRequestValue : function(name) {
		// 获取链接请求参数
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return decodeURIComponent(r[2]);
		return null;
	},
	isoDate : function() {
		return new Date().Format("yyyy-MM-dd");
	},
	isoDateTime : function() {
		return new Date().Format("yyyy-MM-dd hh:mm:ss");
	},
	isoHourTime : function() {
		return new Date().Format("hh:mm");
	},
	random : function(min, max) {
		return Math.floor(min + Math.random() * (max - min));
	},
	uuid : function() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);// 获取uuid
	},
	guid : function() {
		var guid = "";
		for (var i = 0; i < 8; i++) {
			guid += OnairJsUtil.uuid();
		}
		return guid;
	},
	stripscript : function(s) {
		// 禁止输入特殊字符
		var pattern = new RegExp(
				"[`~!@#$^&*()|{}':;'\\[\\]<>/?~@#￥……&*（）——|{}【】‘；：”“']");
		var rs = "";
		for (var i = 0; i < s.length; i++) {
			rs = rs + s.substr(i, 1).replace(pattern, '');
		}
		return rs;
	},
	checkValue : function(obj, defaultVal) {
		// 如果obj为空,返回给定的默认值
		if (!defaultVal) {
			defaultVal = "";
		}
		if (!obj) {
			return defaultVal;
		}
		return obj;
	},
	checkNull : function(value) {
		if (typeof(value) == 'undefined' || value == null || value == "") {
			return true;
		}
		return false;
	},
	getTimeShow : function(time_str) {
		var now = new Date();
		var date = new Date(Date.parse(time_str.replace(/-/g, "/")));
		var inter = parseInt((now.getTime() - date.getTime()) / 1000 / 60);
		if (inter == 0) {
			return "刚刚"; // 计算时间间隔，单位为分钟
		} else if (inter < 60) {
			return inter.toString() + "分钟前"; // 多少分钟前
		} else if (inter < 60 * 24) {
			return parseInt(inter / 60).toString() + "小时前"; // 多少小时前
		} else {
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			var hours = date.getHours();
			var minites = date.getMinutes();
			if (month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if (strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			if (hours >= 0 && hours <= 9) {
				hours = "0" + hours;
			}
			if (minites >= 0 && minites <= 9) {
				minites = "0" + minites;
			}
			if (now.getFullYear() == date.getFullYear()) {
				return month + "-" + strDate + " " + hours + ":" + minites; // 本年度内，取日期+时间
																			// 格式如
																			// 06-13
																			// 22:11
			} else {
				var year = date.getFullYear().toString().substring(2, 3);
				return year + "-" + month + "-" + strDate + " " + hours + ":"
						+ minites;
			}
		}
	},
	htmlEncodeJQ : function(str) { // 转义js,html
		return $('<span/>').text(str).html();
	},
	htmlDecodeJQ : function(str) { // 解析js,html
		return $('<span/>').html(str).text();
	},
	parseDate : function(str, parttern) { // 将字符串转为指定日期格式
		if (!str)
			return "--";
		if (!parttern)
			parttern = "yyyy-MM-dd hh:mm:ss";
		str = str.replace(/-/g, "/");
		return new Date(str).Format(parttern);
	},
	getCookie : function(c_name) {
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_name + "=");
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1;
				c_end = document.cookie.indexOf(";", c_start);
				if (c_end == -1)
					c_end = document.cookie.length;
				return unescape(decodeURI(document.cookie.substring(c_start,
						c_end)));
			}
		}
		return "";
	}
};

// 全局日期
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
//公共方法
/**
 * 公共方法
 */
/**
 * 
 */
var COMMON_FUNCTION={
		/**检验后台返回
		 * @param response
		 */
		checkResponse:function(response){
			return !(OnairJsUtil.checkNull(response) || !response.hasOwnProperty('code') || response.code != 0);
		},
		/**获取一个字符串的展示
		 * @param string
		 */
		getShowStr:function(string,defaultShow){
			if(OnairJsUtil.checkNull(string)){
				return typeof(defaultShow)=='undefined'||null==defaultShow?'--':defaultShow;
			}
			return string;
		},
		/**获取table里是title的tr的html代码
		 * @param title
		 */
		getTableTitleTdHtml:function(title){
			return '<td class="title_td" title="'+title+'">'+title+'</td>';
		},
		
		/**获取分页html
		 * @returns {String}
		 */
		getPageHtml:function(){
			return '<ul class="page"></ul>';
		},
		
		/**获取分页插件初始化map
		 * @param responseData
		 */
		getPageInitMap:function(responseData,pageFunction){
			var pageMap={
					pageCount : 0,
					current : 1,
					totalNum : 0,
					backFn : function(p) {
						initPage(p);
					}
			};
			if(OnairJsUtil.checkNull(responseData)){
				return pageMap;
			}
			//当前页
			var currentPage=responseData.page;
			//页大小
			//var pageSize=responseData.pageSize;
			var pageSize=CONFIG_CONSTANTS.REQUEST_DEFAULT_PAGESIZE;
			//总条数
			var total=responseData.pageTotal;
			//总页数
			var totalPage=parseInt((total+pageSize-1)/pageSize);
			
			var initPageMap={
					pageCount : totalPage,
					current : currentPage,
					totalNum : total
				};
			if(typeof(pageFunction) == 'function'){
				initPageMap['backFn']=pageFunction;
			}
			return $.extend(pageMap,initPageMap);
		},
		/**获取无数据展示html
		 * @returns {String}
		 */
		getNoDataHtml:function(){
			return '暂无数据';
		},
		/**将form表单里的数据生成json
		 * @param jqueryFormObject
		 * @returns {OnairHashMap}
		 */
		getFormParameterOnairMap:function(jqueryFormObject){
			var parameArray=jqueryFormObject.serializeArray();
			var onairMap = new OnairHashMap();
			if(OnairJsUtil.checkNull(parameArray) || parameArray.length == 0){
				return onairMap;
			}
			for(var i=0;i<parameArray.length;i++){
				onairMap.put(parameArray[i].name,parameArray[i].value);
			}
			return onairMap;
		},
		/**
		 * @param postSubUrl 请求suburl,会在函数里拼接为完整url
		 * @param postDataJson 请求json数据
		 * @param hasDataCallbackFunction 当有数据时回调
		 * @param hasDataCallbackData 当有数据时额外的回调数据
		 */
		loadDataList:function(postSubUrl,postDataJson,hasDataCallbackFunction,noDataCallbackFunction,errorCallbackFunction,callbackData){
			//拼接为完整url
			var fullUrl=URL_FUNCTION.getFullUrl(postSubUrl);
			$.YSContent.postAjax(fullUrl, postDataJson, function(
					callbackData, data) {
				var responseDataKey=CONFIG_CONSTANTS.RESPONSE_DATA;
				var callbackDataKey=CONFIG_CONSTANTS.CALLBACK_DATA;
				//将
				var extraData={};
				extraData[callbackDataKey]=callbackData;
				if(!COMMON_FUNCTION.checkResponse(data)){
					extraData[responseDataKey]=data;
					errorCallbackFunction(extraData);
					return;
				}
				var dataList=data.data.dataList;
				
				if(OnairJsUtil.checkNull(dataList) || dataList.length == 0){
					noDataCallbackFunction(extraData);
				}else{
					extraData[responseDataKey]=data.data;
					hasDataCallbackFunction(extraData);
				}
			}, errorCallbackFunction, callbackData);
		},
		
		/**加载数据
		 * @param postSubUrl
		 * @param postDataJson post的数据
		 * @param successCallbackFunction 成功回调
		 * @param errorCallbackFunction 出错回调
		 * @param callbackData
		 */
		loadData:function(postSubUrl,postDataJson,successCallbackFunction,errorCallbackFunction,callbackData){
			//拼接为完整url
			var fullUrl=URL_FUNCTION.getFullUrl(postSubUrl);
			$.YSContent.postAjax(fullUrl, postDataJson, function(
					callbackData, data) {
				var responseDataKey=CONFIG_CONSTANTS.RESPONSE_DATA;
				var callbackDataKey=CONFIG_CONSTANTS.CALLBACK_DATA;
				//将
				var extraData={};
				extraData[callbackDataKey]=callbackData;
				
				if(!COMMON_FUNCTION.checkResponse(data)){
					extraData[responseDataKey]=data;
					errorCallbackFunction(extraData);
				}else{
					extraData[responseDataKey]=data.data;
					successCallbackFunction(extraData);
				}
			}, errorCallbackFunction, callbackData);
		},
		
		/**
		 * 关闭对话框并刷新分页
		 */
		closeDialogRefreshPage:function(){
			$.YSCore.closeDialog();
			initPage();
		},
		
		/**关闭对话框，如果传递了回调函数则调用
		 * @param callbackFunction
		 * @param callbackData
		 */
		closeDialogRefresh:function(callbackFunction,callbackData){
			$.YSCore.closeDialog();
			if(typeof(callbackFunction) == 'function'){
				callbackFunction(callbackData);
			}
		},
		
		/**
		 * 跳转到登陆页
		 */
		redirectLogin:function(){
			window.location='login.html';
		},
		
		/**
		 * 跳转到首页
		 */
		redirectIndex:function(){
			window.location='index.html';
		}
}

/**
 * 登陆用户方法
 */
var LOGIN_USER_FUNCTION={
		/**设置登录用户
		 * @param loginUser
		 */
		setLoginUser:function(loginUser){
			SYSTEM_VARIABLE[CONFIG_CONSTANTS.LOGIN_USER]=loginUser;
		},
		/**获取登录用户
		 * @returns
		 */
		getLoginUser:function(){
			var loginUser=SYSTEM_VARIABLE[CONFIG_CONSTANTS.LOGIN_USER];
			if(null == loginUser){
				COMMON_FUNCTION.redirectLogin();
			}
			return loginUser;
		}
}

/**
 * 打印指定内容
 * 
 * @param myDiv
 *            元素的标识
 * @returns {Boolean}
 */
function printPage(myDiv) {
	var newstr = document.getElementById(myDiv).innerHTML;
	var oldstr = document.body.innerHTML;
	document.body.innerHTML = newstr;
	window.print();
	document.body.innerHTML = oldstr;
	return false;
}

/**
 * 检查余额
 * 
 * @param o
 */
function checkMoney(o) {
	var user = LOGIN_USER_FUNCTION.getLoginUser();
	var $o = $(o);
	if (null == user.money || "" == user.money || "undefined" == user.money) {
		layer.confirm('余额不足，无法兑换云币！', {
			btn : [ '我要充值', '取消' ]
		}, function() {
			$o.attr("swidth", "350");
			$o.attr("sheight", "200");
			$o.attr("stitle", "消息提醒");
			$o.attr("shref", "views/usercenter/recharge.html");
			layer.closeAll('dialog');
			$.YSCore.sDialog(o);
		});
	} else {
		$.YSCore.sDialog(o);
	}
}

/**
 * 根据checkbox的名称属性获取选中的checkbox的id属性值
 */
function findIdsByCheckName(name){
	var selectedIds = [];
	$('input[name="'+name+'"]:checked').each(function(){
		selectedIds.push($(this).attr("id"));
	});
	return selectedIds;
}
