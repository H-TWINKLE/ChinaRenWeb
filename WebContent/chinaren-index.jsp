<!DOCTYPE html>
<%@page import="com.jwj.dao.factory.ManageFactory"%>
<%@page import="com.jwj.entity.UserQues"%>
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
<title>ChinaRen</title>
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

	<%
		int nummood = 0;
		String table = "usermess";

		try {
			nummood = ManageFactory.getInstance().getOneTableCount(table,user.getId(),"u_id");

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("  table:" + table+" num: "+nummood);
	%>


	<%
		int begin = 0;
		int end = 10;
		try {
			begin = Integer.parseInt(request.getParameter("begin"));
			end = Integer.parseInt(request.getParameter("end"));
		} catch (Exception e) {

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
				<div class="col-lg-12">
					<div class="tabbable">
						<ul
							class="nav nav-pills font-size-15 font-weight-bold default-font-family">
							<li class="active"><a href="#pub-record" data-toggle="tab"><i
									class="fa fa-heart fa-fw"></i> 心情</a></li>
							<li class=""><a href="#pub-question" data-toggle="tab"><i
									class="fa fa-quora fa-fw"></i> 提问</a></li>
						</ul>
						<br>
						<div class="tab-content default-margin-top-minus">
							<div class="tab-pane active" id="pub-record">
								<div class="row-fluid">
									<div class="span12">
										<div class="row-fluid">
											<div class="span10">
												<div class="form-group">
													<textarea class="form-control" rows="8" id="what"
														placeholder="今天心情不错  ······"></textarea>
												</div>
												<div class="form-gruop">
													<input class="form-control" type="file" name="fileToUpload"
														id="fileToUpload" value="file">
												</div>
												<br>
												<div class="form-gruop" align="right">
													<button class="btn btn-primary icon-btn" type="button"
														onclick="on()">
														<i class="fa fa-fw fa-lg fa-check-circle"></i>发布
													</button>
												</div>

											</div>

										</div>

									</div>
								</div>
								<br>
								<div class="row-fluid">
									<div class="span12 alert alert-info fade in pub-record-alert">
										<a class="close" data-dismiss="alert" href="#">×</a>
										<p>
											<strong>心情记录：</strong>快速发布心情记录，告诉好友你此时的快乐与忧伤。
										</p>
										<p>
											<strong>树洞秘密：</strong>树洞，一个袒露心声的地方，有些秘密、有些话、有些心事你可以悄悄的告诉树洞，树洞会为你保守秘密，绝不会向任何人透露出你是谁的。
										</p>
									</div>
								</div>
								<!--/row-->
							</div>
							<!--/tab-pane-->

							<!-- Publish Question Begin -->
							<div class="tab-pane" id="pub-question">
								<div class="form-group">
									<input class="form-control" placeholder="在这里概述您的问题" type="text"
										id="ques_title">
								</div>
								<div class="form-group">
									<textarea class="form-control" rows="8" id="ques_content"
										placeholder="您可在这里继续补充问题细节"></textarea>
								</div>
								<br>
								<div class="form-gruop" align="right">
									<button class="btn btn-primary icon-btn" type="button"
										onclick="onQues()">
										<i class="fa fa-fw fa-lg fa-check-circle"></i>发布提问
									</button>
								</div>
							</div>
						</div>
						<!--/tab-pane-->
						<!-- Publish Video End -->

					</div>
					<!--/tab-content-->
					<div class="row">
						<div class="col-lg-12">
							<ul
								class="nav nav-pills font-size-15 font-weight-bold default-font-family">
								<li class="active"><a href="#record" data-toggle="tab"><i
										class="fa fa-heart fa-fw"></i> 我的心情</a></li>
								<li class=""><a href="#question" data-toggle="tab"><i
										class="fa fa-quora fa-fw"></i> 我的提问</a></li>
								<li class=""><a href="#pub-topic" data-toggle="tab"><i
										class="fa fa-th-large fa-fw"></i> 好友动态</a></li>
							</ul>
						</div>
						<br> <br> <br> <br> <br>
						<div class="tab-content default-margin-top-minus">
							<div class="row active tab-pane" id="record">

								<%
									//System.out.print("" + user.getClazz().getId());
									List<UserMess> listm = null;
									try {
										listm = MessFactory.getInstance().finadAllUS(begin, end, user.getId());
									} catch (Exception e) {
										e.printStackTrace();
									}
								%>
								<%
									for (UserMess userMess : listm) {
								%>
								<div class="col-md-12">
									<div class="card">
										<p align="right">
											<a onclick="update(<%=userMess.getM_id()%>,5)"> <span
												class="label label-danger">删除</span></a>
										</p>
										<h3 class="card-title"><%=user.getName()%></h3>
										<%=userMess.getMessage()%>
										<div class="col-md-12" align="center">
											<img alt="" src="upImg/<%=userMess.getPic()%>"
												style="height: 100%; width: 50%">
										</div>
										<p class="mt-40 mb-20" align="right">
											<span class="label label-primary"><%=CommUtil.getInstance().stampToDate(userMess.getDates())%></span>
										</p>
									</div>
								</div>
								<%
									}
								%>
							</div>

							<div class="row tab-pane" id="question">

								<%
									//System.out.print("" + user.getClazz().getId());
									List<UserQues> lis = null;
									try {
										lis = MessFactory.getInstance().findAllQues(user.getId(), begin, end);
									} catch (Exception e) {
										e.printStackTrace();
									}
								%>

								<%
									for (UserQues userMess : lis) {
								%>
								<div class="col-md-12">
									<div class="card">
										<p align="right">
											<a onclick="update(<%=userMess.getQ_id()%>,6)"> <span
												class="label label-danger">删除</span></a>
										</p>
										<h3 class="card-title"><%=user.getName()%></h3>
										<h5><%=userMess.getTitle()%></h5>
										<div class="col-md-12">
											<%=userMess.getContent()%>
										</div>
										<p class="mt-40 mb-20" align="right">
											<span class="label label-primary"><%=CommUtil.getInstance().stampToDate(userMess.getDates())%></span>
										</p>
									</div>
								</div>
								<%
									}
								%>
							</div>
						</div>
					</div>
					<div class="row" align="right">
						<div class="col-lg-12">
							<div class="bs-component">
								<ul class="pagination">
									<li class="disabled"><a href="#">«</a></li>

									<%
										for (int x = 0; x < nummood / 10+1; x++) {
									%>
									<li><a
										href="chinaren-index.jsp?begin=<%= x * 10%>&end=<%=(x+1) * 10%>"><%=x+1 %></a></li>
									<%
										}
									%>
									<li><a href="#">»</a></li>
								</ul>
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
		function on() {
			var formData = new FormData();
			formData.append("file", $("#fileToUpload")[0].files[0]);
			formData.append("what", $("#what").val());
			formData.append("f", 3)
			$.ajax({
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
							message : data,
							icon : 'fa fa-check'
						}, {
							type : "info"
						});
						window.setTimeout(
								"window.location='chinaren-index.jsp'", 1000);
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

		};
		function update(data,flag) {
			$.ajax({
				url : "DeleteServlet",
				type : "post",
				data : {
					"action" : "post",
					"id" : data,
					"flag" : flag
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
								"window.location='chinaren-index.jsp'",
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
		function onQues() {

			$.ajax({
				url : "ShareQuesServlet",
				type : 'POST',
				data : {
					"action" : "post",
					"ques_title" : $("#ques_title").val(),
					"ques_content" : $("#ques_content").val()
				},
				success : function(data) {
					if (data != "0") {
						$.notify({
							title : "提示 : ",
							message : data,
							icon : 'fa fa-check'
						}, {
							type : "info"
						});
						window.setTimeout(
								"window.location='chinaren-index.jsp'", 1000);
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
</body>
</html>