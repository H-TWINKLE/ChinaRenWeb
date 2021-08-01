<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.jwj.entity.User"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS-->
<link rel="stylesheet" type="text/css" href="css/main.css">
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>ChinaRen - 修改密码</title>
</head>
<body>
	<%
		User admin = (User) request.getSession().getAttribute("admin");
	%>

	<section class="material-half-bg">
	<div class="cover"></div>
	</section>
	<section class="logini-content">
	<div class="logoi">
		<h1><%=admin.getAdmin() %></h1>
	</div>
	<div class="logini-box" align="left">
		<form class="logini-form" action="" method="post">
			<h3 class="logini-head">
				<i class="fa fa-lg fa-fw fa-user"></i>修改密码
			</h3>
			<div class="form-group">
				<label class="control-label">密码</label> <input class="form-control"
					type="password" placeholder="请输入密码" autofocus name="pass" id="pass">
			</div>
			<div class="form-group">
				<label class="control-label">确认密码</label> <input
					class="form-control" type="password" placeholder="请再次输入密码"
					autofocus name="rpass" id="rpass">
			</div>

			<div class="form-group btn-container">
				<button class="btn btn-primary btn-block" type="button"
					id="demoNotify">
					<i class="fa fa-sign-in fa-lg fa-fw"></i>确定
				</button>
			</div>
		</form>
	</div>
	</section>
</body>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/pace.min.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
<script type="text/javascript">
	$('#demoNotify').click(function() {
		$.ajax({
			url : "UpdatePassServlet",
			type : "post",
			data : {
				"action" : "post",
				"pass" : $("#pass").val(),
				"rpass" : $("#rpass").val()
			},
			success : function(data) {
				if (data == "false") {
					window.location.href = "chinaren-error.html";
				} else if (data == "true") {
					$.notify({
						title : "提示 : ",
						message : "修改密码成功，请登录！",
						icon : 'fa fa-check'
					}, {
						type : "info"
					});
					window.setTimeout("window.location='chinaren-login.jsp'",1000); 
				} else {
					$.notify({
						title : "提示 : ",
						message : data,
						icon : 'fa fa-check'
					}, {
						type : "info"
					})
				}

			},
			error : function() {
				$.notify({
					title : "错误 : ",
					message : "网络异常",
					icon : 'fa fa-check'
				}, {
					type : "info"
				})
			}
		})
	});
</script>
</html>