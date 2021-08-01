<!DOCTYPE html>
<%@page import="com.jwj.dao.factory.UserFactory"%>
<%@page import="com.jwj.entity.ClazzPic"%>
<%@page import="com.jwj.dao.factory.MessFactory"%>
<%@page import="com.jwj.entity.ClazzMess"%>
<%@page import="com.jwj.entity.UserMess"%>
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
<link rel="stylesheet" type="text/css" media="screen" href="css/mui.css" />
<link rel="stylesheet" type="text/css" href="css/main.css">
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>ChinaRen - 班级信息</title>
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

<%
	int id = Integer.parseInt(request.getParameter("id"));
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

			<div class="row user">
				<div class="col-md-12">
					<div class="profile">
						<div class="cover-image">
							<a style="margin-left: 30px"
								href="javascript:window.history.back();"
								class="mui-btn mui-btn--fab mui-btn--primary">返回</a>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="card p-0">
						<ul class="nav nav-tabs nav-stacked user-tabs">
							<li class="active"><a href="#user-timeline"
								data-toggle="tab">班级留言</a></li>
							<li><a href="#user-settings" data-toggle="tab">班级照片</a></li>
							<li><a href="#user-info" data-toggle="tab">班级信息</a></li>
						</ul>

					</div>
				</div>
				<div class="col-md-11">
					<div class="tab-content">
						<div class="tab-pane active" id="user-timeline">
							<div class="timeline">
								<div class="card user-settings">
									<table class="table table-hover table-bordered"
										id="sampleTable">
										<thead>
											<tr>
												<th>id</th>
												<th>用户名称</th>
												<th>留言内容</th>
												<th>删除留言</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<%
													//System.out.print("" + user.getClazz().getId());
													List<ClazzMess> listm = null;
													try {
														listm = MessFactory.getInstance().findAllC(0, 999, id, "c_id");
													} catch (Exception e) {
														e.printStackTrace();
													}
												%>

												<%
													Iterator<ClazzMess> iterator = listm.iterator();
													while (iterator.hasNext()) {
														ClazzMess pe = iterator.next();
												%>
												<td align="center"><%=pe.getId()%></td>
												<td><%=pe.getU_id().getName()%></td>
												<td><%=pe.getContent()%></td>
												<td align="center"><a
													onclick="update(<%=pe.getId()%>,3)">删除留言</a></td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="user-settings">
							<div class="card user-settings">
								<table class="table table-hover table-bordered" id="sampleTable">
									<thead>
										<tr>
											<th>id</th>
											<th>用户名称</th>
											<th>图片</th>
											<th>删除图片</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<%
												List<ClazzPic> list = null;
												try {
													list = UserFactory.getInstance().findAllPic(0, 999, id);

												} catch (Exception e) {
													e.printStackTrace();
												}
											%>

											<%
												for (ClazzPic clazzPic : list) {
											%>
											<td align="center"><%=clazzPic.getId()%></td>
											<td><%=clazzPic.getU_id().getName()%></td>
											<td><img style="width: 10%" alt=""
												src="upImg/<%=clazzPic.getPic()%>"></td>
											<td align="center"><a
												onclick="update(<%=clazzPic.getId()%>,4)">删除图片</a></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>

						<%
						Clazz clazz = new Clazz();
						try{
							clazz = ManageFactory.getInstance().findOneClazz(id);
						}catch(Exception e){
							e.printStackTrace();
						}
						
						%>
						<div class="tab-pane fade" id="user-info" align="center">
							<div class="card user-settings">
								<form class="logini-form" action="" method="post"
									style="width: 50%">
									<br>
									<br>
									<div class="form-group">
										<label class="col-lg-2 control-label">班级</label>
										<div class="col-lg-10">
											<input class="form-control" id="clazz" type="text"
												value="<%=clazz.getClazzn() %>">
										</div>
									</div>
									<br>
									<br>
									<div class="form-group">
										<label class="col-lg-2 control-label">年级</label>
										<div class="col-lg-10">
											<input class="form-control" id="grade" type="text"
												value="<%=clazz.getGraden() %>">
										</div>
									</div>
									<br>
									<br>
									<div class="form-group">
										<label class="col-lg-2 control-label">专业</label>
										<div class="col-lg-10">
											<input class="form-control" id="major" type="text"
												value="<%=clazz.getMajorn() %>">
										</div>
									</div>
									<br>
									<br>
									<div class="form-group">
										<label class="col-lg-2 control-label">学院</label>
										<div class="col-lg-10">
											<input class="form-control" id="college" type="text"
												value="<%=clazz.getCollegen() %>">
										</div>
									</div>
									<br>
									<br>
									<div class="form-group">
										<label class="col-lg-2 control-label">简介</label>
										<div class="col-lg-10">
											<input class="form-control" id="auto" type="text"
												value="<%=clazz.getAuto() %>">
										</div>
									</div>
									<br>
									<br>
									<button class="btn btn-primary" type="button"
										onclick="update(<%=clazz.getId()%>)">
										<i class="fa fa-fw fa-lg fa-check-circle"></i> 修改
									</button>

								</form>
							</div>
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
	<script type="text/javascript">
		function update(data,flag) {
			$.ajax({
				url : "DeleteServlet",
				type : "post",
				data : {
					"action" : "post",
					"id" : data,
					"flag":flag
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
						location.reload();
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

	<script type="text/javascript">
			function update(data) {
				$.ajax({
					url : "ManageUpServlet",
					type : "post",
					data : {
						"action" : "post",
						"id" : data,
						"clazz" : $("#clazz").val(),
						"grade" : $("#grade").val(),
						"major" : $("#major").val(),
						"college" : $("#college").val(),
						"dates" : $("#dates").val(),
						"auto" : $("#auto").val()
					},
					success : function(data) {
						if (data == "修改成功！") {
							$.notify({
								title : "提示 : ",
								message : data,
								icon : 'fa fa-check'
							}, {
								type : "info"
							});
							window.setTimeout(
									"window.location.reload()",
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