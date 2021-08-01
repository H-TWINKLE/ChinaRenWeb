<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.jwj.util.CommUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.jwj.dao.factory.MessFactory"%>
<%@page import="com.jwj.entity.UserMess"%>
<%@page import="java.util.List"%>
<%@page import="com.jwj.dao.factory.UserFactory"%>
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
<title>ChinaRen - 个人信息</title>
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

	<%
		int id = 0;
		try {
			id =Integer.parseInt((request.getParameter("id")));
		} catch (Exception e) {

		}
		
	%>

	<%
		User us = null;
		try {
			us = UserFactory.getInstance().getUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (us != null) {
	%>

	<%
		String imgg = us.getImg();
			if (imgg == null || imgg.equals("")) {
				imgg = "https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg";
			} else {
				imgg = "upImg/" + imgg;
			}
	%>

	<div class="wrapper">
		<%@include file="chinaren-headernav.jsp"%>
		<%@include file="chinaren-sidenav.jsp"%>
		<div class="content-wrapper">
			<div class="row user">
				<div class="col-md-12">
					<div class="profile">
						<div class="info">
							<img class="user-img" src="<%=imgg%>" onclick="fileSelect();">
							<h4><%=us.getName()%></h4>
							<p><%=us.getAuto()%></p>

						</div>
						<div class="cover-image"></div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="card p-0">
						<ul class="nav nav-tabs nav-stacked user-tabs">
							<li class="active"><a href="#user-timeline"
								data-toggle="tab">他的留言</a></li>
							<li><a href="#user-settings" data-toggle="tab">个人信息</a></li>
							<li><a href="#user-share" data-toggle="tab">给他留言</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-9">
					<div class="tab-content">
						<div class="tab-pane active" id="user-timeline">
							<div class="timeline">
								<%
									List<UserMess> list = null;

										try {
											list = MessFactory.getInstance().finadAllU(0, 999, Integer.valueOf(id), "u_id");
										} catch (Exception e) {
											e.printStackTrace();
										}
								%>

								<%
									Iterator<UserMess> iterator = list.iterator();
										while (iterator.hasNext()) {
											UserMess mess = iterator.next();
								%>
								<div class="post">
									<div class="post-media">
										<div class="content">
											<h5>
												<a href="#"><%=mess.getRu_id().getName()%></a>
											</h5>
										</div>
									</div>
									<div class="post-content">
										<p><%=mess.getMessage()%></p>
										<p class="mt-40 mb-20" align="right">
											<span class="label label-primary"><%=CommUtil.getInstance().stampToDate(mess.getDates())%></span>
										</p>
									</div>
								</div>
								<%
									}
								%>
							</div>
						</div>
						<div class="tab-pane fade" id="user-settings">
							<div class="card user-settings">
								<h4 class="line-head">个人信息</h4>
								<form action="" method="post">
									<div class="row">
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>账号</label> <input name="admins" id="admins"
												disabled="disabled" class="form-control"
												value="<%=us.getAdmin()%>" type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>姓名</label> <input disabled="disabled" name="name"
												id="name" class="form-control" value="<%=us.getName()%>"
												type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>昵称</label> <input disabled="disabled" name="nickname"
												id="nickname" class="form-control"
												value="<%=us.getNickname()%>" type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>性别</label> <input disabled="disabled" name="sex"
												id="sex" class="form-control" value="<%=us.getSex()%>"
												type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>班级</label> <input disabled="disabled"
												class="form-control"
												value="<%=us.getClazz().getGraden()+us.getClazz().getClazzn()%>"
												type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>专业</label> <input disabled="disabled"
												class="form-control" value="<%=us.getClazz().getMajorn()%>"
												type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>学院</label> <input disabled="disabled" name="tel"
												id="tel" class="form-control"
												value="<%=us.getClazz().getCollegen()%>" type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>学年</label> <input disabled="disabled"
												class="form-control" value="<%=us.getUsermore().getYear()%>"
												type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>生日</label> <input disabled="disabled"
												class="form-control"
												value="<%=us.getUsermore().getBirth()%>" type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>家乡</label> <input disabled="disabled" name="tel"
												id="tel" class="form-control"
												value="<%=us.getUsermore().getHome()%>" type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>现居地</label> <input disabled="disabled" name="tel"
												id="tel" class="form-control"
												value="<%=us.getUsermore().getLive()%>" type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>电话号码</label> <input disabled="disabled" name="tel"
												id="tel" class="form-control"
												value="<%=us.getUsermore().getTel()%>" type="text">
										</div>
										<div class="clearfix"></div>
										<div class="col-md-8 mb-20">
											<label>个性签名</label> <input disabled="disabled"
												class="form-control" name="auto" id="auto"
												value="<%=us.getAuto()%>" type="text">
										</div>
									</div>
								</form>
							</div>
						</div>
						<%
							}
						%>
						<div class="tab-pane fade" id="user-share">
							<div class="card user-settings">
								<h4 class="line-head">你想说点什么···</h4>
								<section class="invoice">
								<div class="card-body">
									<form>
										<div class="form-group">
											<textarea class="form-control" rows="8" id="what"
												placeholder="你想说点什么···"></textarea>
										</div>
									</form>
								</div>
								<div class="card-footer">
									<button class="btn btn-primary icon-btn" type="button"
										onclick="ToShare(<%=id%>)">
										<i class="fa fa-fw fa-lg fa-check-circle"></i>发布
									</button>
								</div>
								</section>
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
	<script type="text/javascript">
	function ToShare(id) {
		$.ajax({
			url : "ToShareServlet",
			type : "post",
			data : {
				"action" : "post",
				"what" : $("#what").val(),
				"rid" : id,
				"f":1
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
							"window.location='chinaren-userother.jsp?id="+id+"'",
							1000);
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