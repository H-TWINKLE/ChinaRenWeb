<!DOCTYPE html>

<%@page import="com.jwj.dao.factory.UserFactory"%>
<%@page import="com.jwj.entity.UserFocus"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.jwj.entity.UserMess"%>
<%@page import="java.util.List"%>
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
<title>ChinaRen - 我的关注</title>
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
						<i class="fa fa-file-text-o"></i> 我的关注
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4">
					<h2>我的关注</h2>
					<div class="bs-component">
						<%
							List<UserFocus> list = null;

							try {
								list = UserFactory.getInstance().findAllUserFocus(0, 999, user.getId(), "ru_id");
							} catch (Exception e) {
								e.printStackTrace();
							}
						%>

						<%
							Iterator<UserFocus> iterator = list.iterator();
							while (iterator.hasNext()) {
								UserFocus focu = iterator.next();
						%>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title"><%=focu.getU_id().getAdmin()%></h3>
							</div>
							<div class="panel-body">
								<a
									onclick="getUser(<%=String.valueOf(focu.getU_id().getId())%>);">
									<%=focu.getU_id().getName()%></a> <a><span
									style="float: right;" class="label label-warning"
									onclick='cancel(<%=String.valueOf(focu.getId())%>)'>取消关注</span></a>
							</div>
						</div>

						<%
							}
						%>
					</div>
				</div>
				<div class="col-lg-2">
					<br>
				</div>
				<div class="col-lg-4">
					<h2>我的粉丝</h2>
					<div class="bs-component">
						<%
							List<UserFocus> li = null;

							try {
								li = UserFactory.getInstance().findAllUserFocus(0, 999, user.getId(), "u_id");
							} catch (Exception e) {
								e.printStackTrace();
							}
						%>

						<%
							for (UserFocus focus : li) {
						%>
						<div class="panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title"><%=focus.getRu_id().getAdmin()%></h3>
							</div>
							<div class="panel-body">
								<a
									onclick="getUser(<%=String.valueOf(focus.getRu_id().getId())%>);"><%=focus.getRu_id().getName()%></a>
								<a><span style="float: right;"
									onclick='cancel(<%=String.valueOf(focus.getId())%>)'
									class="label label-warning">移除粉丝</span></a>
							</div>
						</div>

						<%
							}
						%>
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
			function getUser(data) {
				window.location = 'chinaren-userother.jsp?id=' + data;
			}
			</script>
	<script type="text/javascript">		
			function cancel(data) {
				$.ajax({
					url : "CancleFocusServlet",
					type : "post",
					data : {
						"action" : "post",
						"id" : data
					},
					success : function(da) {
						if(da == "true"){
							$.notify({
								title : "提示 : ",
								message : "操作成功！",
								icon : 'fa fa-check'
							}, {
								type : "info"
							});						
							window.setTimeout(
									"window.location='chinaren-focus.jsp'",
									1000);
					}else{
						$.notify({
						title : "提示 : ",
						message : da,
						icon : 'fa fa-check'
					}, {
						type : "info"
					});
						}
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