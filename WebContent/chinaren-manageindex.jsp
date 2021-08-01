<!DOCTYPE html>
<%@page import="java.util.HashSet"%>
<%@page import="com.jwj.dao.factory.UserFactory"%>
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
<title>ChinaRen - index</title>
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
	String cla = "";
	String graden = "";
	String major = "";
	String college = "";
	String fl = "";
	boolean way = false;
	
	try{
		fl = new String(request.getParameter("fl").getBytes("iso-8859-1"), "utf-8");
	}catch(Exception e){
		fl="fl";
	}

	try {		
		cla = new String(request.getParameter("clazz").getBytes("iso-8859-1"), "utf-8");
		graden = new String(request.getParameter("graden").getBytes("iso-8859-1"), "utf-8");
		major = new String(request.getParameter("major").getBytes("iso-8859-1"), "utf-8");
		college = new String(request.getParameter("college").getBytes("iso-8859-1"), "utf-8");
		way = true;
	} catch (Exception e) {
		
		cla = "cla";
		graden = "graden";
		major = "major";
		college = "college";
	}
	
	//System.out.println("fl:"+fl+"  cla:"+cla+"   graden:"+graden+"   major:"+major+"   college:"+college+"  way:"+way);
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
						<li class="active"><a href="#">班级管理</a></li>
					</ul>
				</div>
			</div>

			<div class="row">
				<div class="col-md-2" style="width: 140px">
					<div class="form-group">
						<select class="form-control" id="graden">
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
				<div class="col-md-2" style="width: 120px">
					<div class="form-group">
						<select class="form-control" id="clazz">
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
				<div class="col-md-2" style="width: 180px">
					<div class="form-group">
						<select class="form-control" id="major">
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
				</div>

				<div class="col-md-2" style="width: 220px">
					<div class="form-group">
						<select class="form-control" id="college">
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
				</div>
				<div class="row">
					<div class="col-md-2" align="right">
						<div class="form-group">
							<a class="btn btn-success" onclick="findclazz()">班级查询</a>
						</div>
					</div>
					<div class="col-md-2" align="right">
						<div class="form-group">
							<a class="btn btn-success" href="chinaren-manageindex.jsp?fl=all">查询所有</a>
						</div>
					</div>
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
										<th>班级</th>
										<th>年级</th>
										<th>专业</th>
										<th>学院</th>
										<th>时间</th>
										<th>简介</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<%
											List<Clazz> list = null;
										
										if(!way){
											try {
												list = ManageFactory.getInstance().findAllClazz();
												//System.out.println(list.size()+":all");
											} catch (Exception e) {
												e.printStackTrace();
											}
										}else{
											try {
												list = UserFactory.getInstance().findClazzTable(cla, graden, major, college);
												//System.out.println(list.size()+":one");
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
																																		
											
										%>

										<%
											Iterator<Clazz> iterator = list.iterator();
											while (iterator.hasNext()) {
												Clazz pe = iterator.next();
										%>
										<td align="center" onclick="onid(<%=pe.getId()%>)"><a><%=pe.getId()%></a></td>
										<td id="clazz<%=pe.getId()%>"><%=pe.getClazzn()%></td>
										<td id="grade<%=pe.getId()%>"><%=pe.getGraden()%></td>
										<td id="major<%=pe.getId()%>"><%=pe.getMajorn()%></td>
										<td id="college<%=pe.getId()%>"><%=pe.getCollegen()%></td>
										<td style="width: 110px"><%=pe.getDates()%></td>
										<td id="auto<%=pe.getId()%>"><%=pe.getAuto()%></td>
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
	
	function findclazz(){	
		var clazz = document.getElementById("clazz").value;
		var graden = document.getElementById("graden").value;
		var major = document.getElementById("major").value;
		var college = document.getElementById("college").value;
		window.location=encodeURI('chinaren-manageindex.jsp?clazz='+clazz+'&graden='+graden+'&major='+major+'&college='+college);			

	};
	
	
			function update(data) {
				$.ajax({
					url : "ManageUpServlet",
					type : "post",
					data : {
						"action" : "post",
						"id" : data,
						"clazz" : $("#clazz"+data).val(),
						"grade" : $("#grade"+data).val(),
						"major" : $("#major"+data).val(),
						"college" : $("#college"+data).val(),
						"dates" : $("#dates"+data).val(),
						"auto" : $("#auto"+data).val()
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
									"window.location='chinaren-manageindex.jsp'",
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

	<script type="text/javascript">
			function onid(data) {	
				window.location='chinaren-manageclazzinfo.jsp?id='+data;												
			}
</script>
</body>
</html>