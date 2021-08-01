<!DOCTYPE html>
<%@page import="com.jwj.util.CommUtil"%>
<%@page import="com.jwj.dao.factory.UserFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jwj.entity.ClazzMess"%>
<%@page import="java.sql.RowId"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.jwj.entity.UserMess"%>
<%@page import="java.util.List"%>
<%@page import="com.jwj.dao.factory.MessFactory"%>
<%@page import="com.jwj.entity.User"%>
<%@page import="com.jwj.init.InitString"%>

<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS-->
<link rel="stylesheet" type="text/css" href="css/main.css">
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>ChinaRen - complaint</title>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--if lt IE 9
    script(src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js')
    script(src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js')
    -->
</head>
<body class="sidebar-mini fixed">

	<%
		User user;
	%>

	<%
		user = (User) session.getAttribute("admin");
		if (user == null) {
			response.setHeader("refresh", "0;URL=chinaren-login.html");
		}
	%>

	<div class="wrapper">
		<%@include file="chinaren-headernav.jsp"%>
		<%@include file="chinaren-sidenav.jsp"%>
		<div class="content-wrapper">
			<div class="page-title ">
				<div>
					<h1>
						<i class="fa fa-dashboard"></i><%=user.getAdmin() + ""%>
					</h1>
					<p><%=user.getAuto() + ""%></p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">


					<form class="form-horizontal">
						<fieldset>
							<legend>投诉建议</legend>
							<div class="form-group">
								<label class="col-lg-2 control-label" for="textArea">内容：</label>
								<div class="col-lg-10">
									<textarea class="form-control" id="textArea" rows="10"></textarea>
									<span class="help-block">您好，感谢您对ChinaRen的关注与支持。
										如果您对我们的工作和服务有任何意见、建议，请填写以下内容进行反馈，您的反馈对我们非常重要。谢谢.</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">类型</label>
								<div class="col-lg-10">
									<div class="radio">
										<label> <input id="optionsRadios1" type="radio"
											name="optionsRadios" value="投诉" checked>投诉
										</label>
									</div>
									<div class="radio">
										<label> <input id="optionsRadios2" type="radio"
											name="optionsRadios" value="建议">建议
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button class="btn btn-default" type="reset">清空</button>
									<button class="btn btn-primary" onclick="ToShare()"
										type="button">提交</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>

	</div>
	<!-- Javascripts-->
	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/plugins/pace.min.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
	<script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
	<script type="text/javascript">
		function getClazzUser(flag) {
			window.location = 'chinaren-listuser.jsp?flag=' + flag;
		}
	</script>
	<script type="text/javascript">
		function ToShare() {
			var radio = '';

		 if (document.getElementById("optionsRadios1").checked) {
				radio = "投诉";
			}

			if (document.getElementById("optionsRadios2").checked) {
				radio = "建议";
			} 

			$.ajax({
				url : "CommPostServlet",
				type : "post",
				data : {
					"action" : "post",
					"textArea" : $("#textArea").val(),
					"radio" : radio,
					"f" : 1
				},
				success : function(data) {
					$.notify({
						title : "提示 : ",
						message : data,
						icon : 'fa fa-check'
					}, {
						type : "info"
					});
					window.setTimeout(
							"window.location='chinaren-complaint.jsp'", 1000);
				},
				error : function() {
					$.notify({
						title : "错误 : ",
						message : "网络异常",
						icon : 'fa fa-check'
					}, {
						type : "info"
					});
				}
			})
		}
	</script>
</body>
</html>