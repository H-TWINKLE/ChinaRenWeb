<!DOCTYPE html>
<%@page import="com.jwj.util.CommUtil"%>
<%@page import="com.jwj.entity.Tip"%>
<%@page import="com.jwj.entity.UserQues"%>
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
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>ChinaRen - 建议恢复</title>
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
					<h1>建议</h1>
					<ul class="breadcrumb side">
						<li><i class="fa fa-home fa-lg"></i></li>
						<li>建议管理</li>
						<li class="active"><a href="#">建议回复</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<table class="table table-hover table-bordered" id="sampleTable">
								<thead>
									<tr>
										<th>id</th>
										<th>用户名</th>
										<th>回复</th>
										<th>是否已读</th>
										<th>时间</th>
										<th>删除回复</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<%
											List<Tip> list = null;

											try {
												list = ManageFactory.getInstance().findAllTip();
												//System.out.print(list.size()+"");
											} catch (Exception e) {
												e.printStackTrace();
											}
										%>

										<%
											for (Tip t : list) {
										%>
										<td align="center"><%=t.getId()%></td>
										<td><%=t.getU_id().getName()%></td>
										<td><%=t.getContent()%></td>
										<td>
											<%
												if (t.getIsread() == 1) {
											%>已读<%
												} else {
											%>未读<%
												}
											%>
										</td>
										<td><%=CommUtil.getInstance().stampToDate(t.getDates())%></td>
										<td align="center"><a onclick="update(<%=t.getId()%>)">删除回复</a></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
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
	<script type="text/javascript"
		src="js/plugins/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="js/plugins/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript">
	$('#sampleTable').DataTable();
	</script>
	<script type="text/javascript">$('body').removeClass("sidebar-mini").addClass("sidebar-collapse");</script>
	<script type="text/javascript">
			function update(data) {
				$.ajax({
					url : "DeleteServlet",
					type : "post",
					data : {
						"action" : "post",
						"id" : data,
						"flag":8						
					},
					success : function(data) {
						if (data == "删除成功！") {
							$.notify({
								title : "提示 : ",
								message : data,
								icon : 'fa fa-check'
							}, {
								type : "info"
							});
							window.setTimeout(
									"window.location='chinaren-managecomplaintre.jsp'",
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