<!DOCTYPE html>
<%@page import="java.util.Iterator"%>
<%@page import="com.jwj.entity.Clazz"%>
<%@page import="java.util.List"%>
<%@page import="com.jwj.dao.factory.ManageFactory"%>
<%@page import="com.jwj.entity.Manage"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS-->
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/mui.css" />
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>ChinaRen - 班级创建</title>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--if lt IE 9
    script(src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js')
    script(src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js')
    -->
</head>

<%
	Manage user;
%>
<%
	user = (Manage) session.getAttribute("admin");
	if (user == null) {
		response.setHeader("refresh", "0;URL=chinaren-login.jsp");
	}
%>


<body class="sidebar-mini fixed">
	<div class="wrapper">
		<!-- Navbar-->
		<header class="main-header hidden-print">
			<a class="logo" href="chinaren-manageindex.jsp">管理员</a>
			<nav class="navbar navbar-static-top">
				<!-- Sidebar toggle button-->
				<a class="sidebar-toggle" data-toggle="offcanvas"></a>
				<!-- Navbar Right Menu-->
				<div class="navbar-custom-menu">
					<ul class="top-nav">
						<!-- User Menu-->
						<li class="dropdown"><a class="dropdown-toggle" href="#"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><i class="fa fa-user fa-lg"></i></a>
							<ul class="dropdown-menu settings-menu">
								<li><a href="chinaren-managelogin.jsp"><i
										class="fa fa-sign-out fa-lg"></i> 退出登录</a></li>
							</ul></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Side-Nav-->
		<aside class="main-sidebar hidden-print">
			<section class="sidebar">
				<div class="user-panel">
					<div class="pull-left image">
						<img class="img-circle"
							src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg"
							alt="User Image">
					</div>
					<div class="pull-left info">
						<p><%=user.getName()%></p>
						<p class="designation"><%=user.getAdmin()%></p>
					</div>
				</div>
				<!-- Sidebar Menu-->
				<%@include file="chinaren-managenav.jsp"%>
			</section>
		</aside>
		<div class="content-wrapper">
			<div class="page-title hidden-print">
				<div>
					<h1>班级</h1>
					<ul class="breadcrumb side">
						<li><i class="fa fa-home fa-lg"></i></li>
						<li>班级信息</li>
						<li class="active"><a href="#">班级创建</a></li>
					</ul>
				</div>
			</div>
			<div class="row" style="margin-left: 400px">
				<div class="col-md-12">
					<div class="row">
						<div class="card-body">
							<form action="" method="post">
								<div class="col-md-3" style="margin-left: 40px">
									<div class="form-group">
										<label>请选择年级</label> <select class="form-control" id="graden">
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
										</select>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>请选择班级</label> <select class="form-control" id="clazz">
											<optgroup label="请选择班级">
												<%
													for (int x = 1; x < 25; x++) {
												%>
												<option><%=x%>班
												</option>
												<%
													}
												%>
											</optgroup>
										</select>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="col-md-8 mb-20">
									<label>专业</label> <input name="major" id="major"
										class="form-control" type="text">
								</div>
								<div class="clearfix"></div>
								<div class="col-md-8 mb-20">
									<label>学院</label> <input name="college" id="college"
										class="form-control" type="text">
								</div>
								<div class="clearfix"></div>
								<div class="col-md-8 mb-20">
									<label>介绍</label> <input class="form-control" id="auto"
										name="auto" type="text">
								</div>
								<div class="row mb-5" style="margin-left: 140px">
									<div class="col-md-12">
										<button class="btn btn-primary" type="button" onclick="up()">
											<i class="fa fa-fw fa-lg fa-check-circle"></i> 修改
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
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
		function up() {
			$.ajax({
				url : "CreateClazzServlet",
				type : "post",
				data : {
					"action" : "post",					
					"clazz" : $("#clazz").val(),
					"graden" : $("#graden").val(),
					"major" : $("#major").val(),
					"college" : $("#college").val(),					
					"auto" : $("#auto").val()
				},
				success : function(data) {
					if (data == "创建班级成功！") {
						$.notify({
							title : "提示 : ",
							message : data,
							icon : 'fa fa-check'
						}, {
							type : "info"
						});
						window.setTimeout(
								"window.location='chinaren-manageclazzcreate.jsp'",
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
		};
	</script>
</body>
</html>