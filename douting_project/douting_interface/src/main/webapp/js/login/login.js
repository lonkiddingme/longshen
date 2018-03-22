function doLogout() {
	var logouturi = ctx + "/login/logout/";
	$.ajax({
		type : 'POST',
		url : logouturi,
		success : function(data) {
			if ("success" == data) {
//				window.location = "https://passport.cloud.jstv.com/logout?service=http://omc.jstv.cdvcloud.com/";
				window.location.href=ctx;
			} else {
				document.getElementById("errorMsg").style.display = "block";
			}
		}
	});
}