<!DOCTYPE html>
<%@page import="com.jwj.dao.factory.ManageFactory"%>
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
<title>ChinaRen - 我的班级</title>
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
		String table = "clazzmess";

		try {
			nummood = ManageFactory.getInstance().getOneTableCount(table, user.getClazz().getId(), "c_id");

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("  table:" + table + " num: " + nummood);
	%>


	<%
		int begin = 0;
		int end = 10;
		try {
			begin = Integer.parseInt(request.getParameter("begin"));
			end = Integer.parseInt(request.getParameter("end"));
		} catch (Exception ee) {

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

			<%
				if (user.getClazz().getId() == 0) {
			%>


			<div class="row" align="center">
				<div class="col-lg-12">
					<h2 id="type-blockquotes">
						<font color="red"><a href="chinaren-query.jsp">您还没加入任何班级，点我加入班级 *~* </a></font>
					</h2>
				</div>
			</div>


			<%
				} else {
			%>

			<div class="row">
				<div class="col-lg-12">
					<h2 id="type-blockquotes"><%=user.getClazz().getGraden() + user.getClazz().getClazzn()%></h2>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<div class="bs-component">
						<blockquote>
							<p><%=user.getClazz().getMajorn()%></p>
							<small>-----专业----- </small>
						</blockquote>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="bs-component">
						<blockquote class="blockquote-reverse">
							<p><%=user.getClazz().getCollegen()%></p>
							<small>-----学院-----</small>
						</blockquote>
					</div>
				</div>
			</div>
			<div class="row">
				<%
					List<Integer> listco = null;

						try {
							listco = UserFactory.getInstance().findCount(user.getClazz().getId());
						} catch (Exception e) {
							e.printStackTrace();
						}
				%>
				<div class="col-md-3">
					<div class="widget-small primary">
						<i class="icon fa fa-users fa-3x"></i>
						<div class="info"
							onclick="getClazzUser(<%=user.getClazz().getId()%>)">
							<h4>
								<a style="color: white;">成员</a>
							</h4>
							<p>
								<b><%=listco.get(0) + ""%></b>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="widget-small danger">
						<i class="icon fa fa-star fa-3x"></i>
						<div class="info">
							<h4>
								<a style="color: white;" href="chinaren-listpic.jsp">图片</a>
							</h4>
							<p>
								<b><%=listco.get(1) + ""%></b>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="widget-small warning">
						<i class="icon fa fa-files-o fa-3x"></i>
						<div class="info">
							<h4>
								<a style="color: white;" onclick="getFocusClazz(0,2)">退出班级</a>
							</h4>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<h3 id="type-blockquotes">班级留言</h3>
					<form>
						<div class="form-group">
							<textarea class="form-control" rows="8" id="what"
								placeholder="你想说点什么···"></textarea>
						</div>

						<div align="right">
							<button class="btn btn-primary icon-btn" type="button"
								onclick="ToShare(<%=user.getClazz().getId()%>)">
								<i class="fa fa-fw fa-lg fa-check-circle"></i>发布
							</button>
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<h3 id="type-blockquotes">学生留言</h3>
				</div>
				<div class="row">

					<%
						//System.out.print("" + user.getClazz().getId());
							List<ClazzMess> listm = null;
							try {
								listm = MessFactory.getInstance().findAllC(begin, end, user.getClazz().getId(), "c_id");
							} catch (Exception e) {
								e.printStackTrace();
							}
					%>
					<%
						for (ClazzMess clazzmess : listm) {
					%>
					<div class="col-md-12">
						<div class="card">
							<h3 class="card-title"><%=clazzmess.getU_id().getName()%>
								<%
									if (user.getIsadmin() == 1) {
								%>
								<a><span style="float: right;" class="label label-warning"
									onclick='del(<%=clazzmess.getId()%>,12)'>删除</span></a>
								<%
									}
								%>
							</h3>
							<%=clazzmess.getContent()%>
							<p class="mt-40 mb-20" align="right">
								<span class="label label-primary"><%=CommUtil.getInstance().stampToDate(clazzmess.getDates())%></span>
							</p>
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
			<div class="row" align="right">
				<div class="col-lg-12">
					<div class="bs-component">
						<ul class="pagination">
							<li class="disabled"><a href="#">«</a></li>
							<%
								for (int x = 0; x < nummood / 10 + 1; x++) {
							%>
							<li><a
								href="chinaren-clazz.jsp?begin=<%=x * 10%>&end=<%=(x + 1) * 10%>"><%=x + 1%></a></li>
							<%
								}
							%>
							<li><a href="#">»</a></li>
						</ul>
					</div>

				</div>
			</div>


			<%
				}
			%>

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
	function ToShare(id) {
		$.ajax({
			url : "ToShareServlet",
			type : "post",
			data : {
				"action" : "post",
				"what" : $("#what").val(),
				"rid" : id,
				"f":2
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
							"window.location='chinaren-clazz.jsp'",
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
	<script type="text/javascript">		
			function del(data,flag) {
				$.ajax({
					url : "DeleteServlet",
					type : "post",
					data : {
						"action" : "post",
						"id" : data,
						"flag":flag
					},
					success : function(da) {
						
							$.notify({
								title : "提示 : ",
								message : da,
								icon : 'fa fa-check'
							}, {
								type : "info"
							});						
							window.setTimeout(
									"window.location.reload()",
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
		<script type="text/javascript">
		
		function getFocusClazz(datas,flag) {	
			$.ajax({
				url : "GetFocusServlet",
				type : "post",
				data : {
					"action" : "post",
					"id" : datas,
					"flag" : flag
				},
				success : function(da) {			
					$.notify({
					title : "提示 : ",
					message : da,
					icon : 'fa fa-check'
				}, {
					type : "info"
				});	
					window.setTimeout(
						"window.location.reload()",
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
			
		};
		
		
		
		</script>
</body>
</html>