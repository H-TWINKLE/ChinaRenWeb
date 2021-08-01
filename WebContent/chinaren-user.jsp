<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.Iterator"%>
<%@page import="com.jwj.dao.factory.MessFactory"%>
<%@page import="com.jwj.entity.UserMess"%>
<%@page import="java.util.List"%>
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
		String imgg = user.getImg();
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
							<form action="" method="post" id="form_face"
								enctype="multipart/form-data" style="width: auto">
								<input type="file" name="fileToUpload" id="fileToUpload"
									value="file" onchange="fileSelected();" style="display: none;">
								<img class="user-img" id="idimg" src="<%=imgg%>"
									onclick="fileSelect();">
							</form>
							<div align="right" onclick="fileSelect();">
								<span class="label label-warning"><i
									class="fa fa-lg fa-edit"></i></span>
							</div>
							<h4><%=user.getName()%></h4>
							<p><%=user.getAuto()%></p>

						</div>
						<div class="cover-image"></div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="card p-0">
						<ul class="nav nav-tabs nav-stacked user-tabs">
							<li class="active"><a href="#user-timeline"
								data-toggle="tab">我的留言</a></li>
							<li><a href="#user-settings" data-toggle="tab">个人信息</a></li>
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
										list = MessFactory.getInstance().finadAllU(0, 999, user.getId(), "u_id");
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
												<a onclick="getUser(<%=mess.getRu_id().getId()%>)"><%=mess.getRu_id().getName()%></a>
											</h5>
										</div>
									</div>
									<div class="post-content">
										<p><%=mess.getMessage()%></p>
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
									<div class="col-md-8 mb-20">
										<label>账号</label> <input name="admin" id="admins"
											disabled="disabled" class="form-control"
											value="<%=user.getAdmin()%>" type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>姓名</label> <input name="name" id="name"
											class="form-control" value="<%=user.getName()%>" type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>性别</label> <input name="sex" id="sex"
											disabled="disabled" class="form-control"
											value="<%=user.getSex()%>" type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>昵称</label> <input name="nickname" id="nickname"
											class="form-control" value="<%=user.getNickname()%>"
											type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>电话号码</label> <input name="tel" id="tel"
											class="form-control" value="<%=user.getUsermore().getTel()%>"
											type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>班级</label> <input name="clazz" id="clazz"
											disabled="disabled" class="form-control"
											value="<%=user.getClazz().getGraden()%><%=user.getClazz().getClazzn()%>"
											type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>专业</label> <input name="major" id="major"
											disabled="disabled" class="form-control"
											value="<%=user.getClazz().getMajorn()%>" type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>学院</label> <input name="college" id="college"
											disabled="disabled" class="form-control"
											value="<%=user.getClazz().getCollegen()%>" type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>学年</label> <input class="form-control" id="year"
											name="year" disabled="disabled"
											value="<%=user.getUsermore().getYear()%>" type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>生日</label> <input class="form-control" id="birth"
											name="birth" disabled="disabled"
											value="<%=user.getUsermore().getBirth()%>" type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>故乡</label> <input class="form-control" name="home"
											id="home" value="<%=user.getUsermore().getHome()%>"
											type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>现居地</label> <input class="form-control" name="live"
											id="live" value="<%=user.getUsermore().getLive()%>"
											type="text">
									</div>
									<div class="clearfix"></div>
									<div class="col-md-8 mb-20">
										<label>个性签名</label> <input class="form-control" name="auto"
											id="auto" value="<%=user.getAuto()%>" type="text">
									</div>
									<div class="row mb-10">
										<div class="col-md-12">
											<button class="btn btn-primary" type="button" id="demoNotify">
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
	</div>
	<!-- Javascripts-->
	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/plugins/pace.min.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
	<script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
	<script type="text/javascript">
		function fileSelect() {
			document.getElementById("fileToUpload").click();
		}

		function fileSelected() {
			var formData = new FormData();
			formData.append("file", $("#fileToUpload")[0].files[0]);
			formData.append("f",1)
			$
					.ajax({
						url : "UploadPicServlet",
						type : 'POST',
						data : formData,
						// 告诉jQuery不要去处理发送的数据
						processData : false,
						// 告诉jQuery不要去设置Content-Type请求头
						contentType : false,
						success : function(data) {
							if (data != "0") {
								$.notify({
									title : "提示 : ",
									message : "上传成功！",
									icon : 'fa fa-check'
								}, {
									type : "info"
								});
								document.getElementById("idimg").src = "upImg\\"
										+ data;
							} else {
								$.notify({
									title : "提示 : ",
									message : "上传失败，请稍后再试！",
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
					});
		}
	</script>

	<script type="text/javascript">
		$('#demoNotify').click(function() {
			$.ajax({
				url : "UserInfoServlet",
				type : "post",
				data : {
					"action" : "post",					
					"auto" : $("#auto").val(),
					"name" : $("#name").val(),
					"nickname" : $("#nickname").val(),
					"tel" : $("#tel").val(),
					"birth" : $("#birth").val(),
					"year" : $("#year").val(),
					"home" : $("#home").val(),
					"live" : $("#live").val()
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
								"window.location='chinaren-user.jsp'",
								1000);
					} else {
						$.notify({
							title : "提示 : ",
							message : "修改失败！",
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

	<script type="text/javascript">
	function getUser(data) {
		window.location='chinaren-userother.jsp?id='+data;
	}</script>
</body>
</html>