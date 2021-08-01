<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.HashSet"%>
<%@page import="com.jwj.entity.Clazz"%>
<%@page import="java.util.List"%>
<%@page import="com.jwj.dao.factory.UserFactory"%>
<%@page import="com.jwj.dao.UserDao"%>
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
<title>ChinaRen -个人信息</title>
</head>
<body>


	<section class="material-half-bg">
	<div class="cover"></div>
	</section>
	<section class="logini-content">
	<div class="logoi">
		<h1>ChinaRen</h1>
	</div>
	<div class="logini-box" style="height: 1150px">
		<form class="logini-form" action="" method="post">
			<div class="form-group">
				<label class="col-lg-10 control-label">请填写真实姓名</label> <input
					class="form-control" id="name" type="text">
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label">请填写昵称</label> <input
					class="form-control" id="nickname" type="text">
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label" for="selectsex">请选择性别</label>
				<div class="col-lg-14">
					<select class="form-control" id="selectsex">
						<option>男</option>
						<option>女</option>
					</select><br>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label" for="selectyear">请选择学年</label>
				<div class="col-lg-14">
					<select class="form-control" id="selectyear" style="width: 270px;">
						<optgroup label="请选择学年">
							<%
								for (int x = 10; x > 0; x--) {
							%>
							<option><%=x%>年
							</option>
							<%
								}
							%>
						</optgroup>
					</select><br>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label" for="selectgraden">请选择年级</label>
				<div class="col-lg-14">
					<select class="form-control" id="selectgraden"
						style="width: 270px;">
						<optgroup label="请选择年级">
							<%
								for (int x = 2018; x > 1850; x--) {
							%>
							<option><%=x%>级
							</option>
							<%
								}
							%>
						</optgroup>
					</select><br>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label" for="selectclazz">请选择班级</label>
				<div class="col-lg-14">
					<select class="form-control" id="selectclazz" style="width: 270px;">
						<optgroup label="请选择班级">
							<%
								for (int x = 1; x < 50; x++) {
							%>
							<option><%=x%>班
							</option>
							<%
								}
							%>
						</optgroup>
					</select><br>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label">请选择专业</label> <select
					class="form-control" id="selectmajor" style="width: 270px;">
					<optgroup label="请选择专业">
						<%
							List<Clazz> clazz = null;
							try {
								clazz = UserFactory.getInstance().findAllM_C();
							} catch (Exception e) {
								e.printStackTrace();
							}
							HashSet<String> set;
						%>
						<%
							set = new HashSet<String>();

							for (Clazz clazzs : clazz) {
								set.add(clazzs.getMajorn());
							}
						%>

						<%
							for (String sets : set) {
						%>

						<option><%=sets%></option>
						<%
							}
						%>
					</optgroup>
				</select>
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label">请选择学院</label> <select
					class="form-control" id="selectcollege" style="width: 270px;">
					<optgroup label="请选择学院">
						<%
							set = new HashSet<String>();

							for (Clazz clazzs : clazz) {
								set.add(clazzs.getCollegen());
							}
						%>

						<%
							for (String sets : set) {
						%>

						<option><%=sets%></option>
						<%
							}
						%>
					</optgroup>
				</select>
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label">请填写电话</label> <input
					class="form-control" id="tel" type="text">
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label">请选择生日</label> <input
					class="form-control" id="selectdate" type="text">
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label">请填写您的家乡</label> <input
					class="form-control" id="home" type="text">
			</div>
			<div class="form-group">
				<label class="col-lg-10 control-label">请填写您的现居地</label> <input
					class="form-control" id="live" type="text">
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
<script type="text/javascript"
	src="js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="js/plugins/select2.min.js"></script>
<script type="text/javascript"
	src="js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
<script type="text/javascript">
	$('#demoNotify').click(
			function() {
				$.ajax({
					url : "SetCollegeServlet",
					type : "post",
					data : {
						"action" : "post",
						"name" : $("#name").val(),
						"nickname" : $("#nickname").val(),
						"selectsex" : $("#selectsex").val(),
						"selectyear" : $("#selectyear").val(),
						"selectgraden" : $("#selectgraden").val(),
						"selectclazz" : $("#selectclazz").val(),
						"selectmajor" : $("#selectmajor").val(),
						"selectcollege" : $("#selectcollege").val(),
						"tel" : $("#tel").val(),
						"selectdate" : $("#selectdate").val(),
						"home" : $("#home").val(),
						"live" : $("#live").val()
					},
					success : function(data) {
						if (data == "false") {
							window.location.href = "chinaren-error.html";
						} else if (data == "true") {
							$.notify({
								title : "提示 : ",
								message : "注册成功！",
								icon : 'fa fa-check'
							}, {
								type : "info"
							});
							window.setTimeout(
									"window.location='chinaren-login.html'",
									1000);
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
	$('#selectdate').datepicker({
		format : "dd/mm/yyyy",
		autoclose : true,
		todayHighlight : true
	});
	$('#selectmajor').select2();
	$('#selectcollege').select2();
	$('#selectgrade').select2();
</script>
</html>