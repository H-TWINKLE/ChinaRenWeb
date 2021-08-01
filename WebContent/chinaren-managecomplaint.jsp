<!DOCTYPE html>
<%@page import="com.jwj.util.CommUtil"%>
<%@page import="com.jwj.entity.UserComplaint"%>
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
<title>ChinaRen - 提问管理</title>
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
					<h1>投诉建议</h1>
					<ul class="breadcrumb side">
						<li><i class="fa fa-home fa-lg"></i></li>
						<li>投诉建议管理</li>
						<li class="active"><a href="#">所有投诉建议</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<%
					List<UserComplaint> list = null;
					try {
						list = ManageFactory.getInstance().findAllUserComplaint();

					} catch (Exception e) {
						e.printStackTrace();
					}
				%>

				<%
					for (UserComplaint complaint : list) {
				%>
				<div class="col-md-4">
					<div class="card">
						<h3 class="card-title"><%=complaint.getU_id().getName()%></h3>
						<h5>
							<span class="label label-warning"><%=complaint.getTypes()%></span>
						</h5>
						<br>
						<h4 class="card-title"><%=complaint.getContent()%></h4>
						<p class="mt-40 mb-20" align="right">
							<span class="label label-primary"><%=CommUtil.getInstance().stampToDate(complaint.getDates())%></span>
						</p>
						<div id="<%=complaint.getU_id().getId()%>"></div>
						<div class="card-footer">
							<a class="btn btn-primary"
								onclick="adds(<%=complaint.getU_id().getId()%>)">回复</a>
						</div>
					</div>
				</div>
				<%
					}
				%>
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
		$('body').removeClass("sidebar-mini").addClass("sidebar-collapse");
	</script>
	<script type="text/javascript">
	var fc = 0;	
	
	function adds(data) {			
			var id  = $("#"+data);
			if(fc==0){
				$(id).append('<input class="form-control" name="ad" id="ad" type="text"></div><br>');
				fc=1;
			}else{				 					
				  $.ajax({
					url : "ReplayServlet",
					type : "post",
					data : {
						"action" : "post",
						"text" : $("#ad").val(),
						"flag" : 3,
						"id":data
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
									"window.location='chinaren-managecomplaintre.jsp'",
									1000);								
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
				}); 
							
			}
			
														 	
		}
	</script>
</body>
</html>