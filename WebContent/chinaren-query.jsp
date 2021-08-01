<!DOCTYPE html>
<%@page import="java.util.HashSet"%>
<%@page import="com.jwj.entity.Clazz"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URI"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.jwj.dao.factory.UserFactory"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="utf-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS-->
<link rel="stylesheet" type="text/css" href="css/main.css">
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>ChinaRen - 查询</title>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--if lt IE 9
    script(src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js')
    script(src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js')
    -->
</head>
<body class="sidebar-mini fixed">


	<%
		String whats = "";
		String cla = "";
		String graden = "";
		String major = "";
		String college = "";

		try {
			whats = new String(request.getParameter("whats").getBytes("iso-8859-1"), "utf-8");
		} catch (Exception e) {
			whats = "abcdefghj";
		}

		if ("".equals(whats)) {
			whats = "abcdefghj";
		}

		try {
			cla = new String(request.getParameter("clazz").getBytes("iso-8859-1"), "utf-8");
			graden = new String(request.getParameter("graden").getBytes("iso-8859-1"), "utf-8");
			major = new String(request.getParameter("major").getBytes("iso-8859-1"), "utf-8");
			college = new String(request.getParameter("college").getBytes("iso-8859-1"), "utf-8");
		} catch (Exception e) {
			cla = "abcdefghj";
			graden = "abcdefghj";
			major = "abcdefghj";
			college = "abcdefghj";
		}

		//System.out.println(whats+cla+graden+major+college);
	%>


	<div class="wrapper">
		<%@include file="chinaren-headernav.jsp"%>
		<%@include file="chinaren-sidenav.jsp"%>
		<div class="content-wrapper">
			<div class="page-title">
				<div>
					<h1>
						<i class="fa fa-th-list"></i> 查找
					</h1>
					<p>你想找点什么···</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<div class="form-group has-success">
						<input class="form-control" type="text" placeholder="你想找谁？"
							id="whats">
					</div>
				</div>
				<div class="row">
					<div class="col-md-2" align="right">
						<div class="form-group">
							<a class="btn btn-success" onclick="findone()">好友查询</a>
						</div>
					</div>
				</div>
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
				</div>
			</div>



			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<table class="table table-hover table-bordered" id="sampleTable">
								<thead>
									<tr>
										<th>姓名</th>
										<th>昵称</th>
										<th>班级</th>
										<th>专业</th>
										<th>学院</th>
										<th>关注</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<User> list = null;

										try {
											System.out.println("whats:" + whats);
											list = UserFactory.getInstance().findUserTable(whats);
											//System.out.print(list.size()+"");
										} catch (Exception e) {
											e.printStackTrace();
										}
									%>

									<%
										Iterator<User> iterator = list.iterator();
										while (iterator.hasNext()) {
											User pe = iterator.next();
									%>

									<tr>
										<td onclick="getUser(<%=pe.getId()%>)"><a><%=pe.getName()%></a></td>
										<td><%=pe.getNickname()%></td>
										<td><%=pe.getClazz().getGraden()%><%=pe.getClazz().getClazzn()%></td>
										<td><%=pe.getClazz().getMajorn()%></td>
										<td><%=pe.getClazz().getCollegen()%></td>
										<td><a onclick="getFocus(<%=pe.getId()%>,1)">关注</a></td>
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
			<div class="clearfix"></div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<table class="table table-hover table-bordered" id="sampleTable">
								<thead>
									<tr>
										<th>班级</th>
										<th>专业</th>
										<th>学院</th>
										<th>加入班级</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<Clazz> li = null;

										try {
											li = UserFactory.getInstance().findClazzTable(cla, graden, major, college);
										} catch (Exception e) {
											e.printStackTrace();
										}
									%>

									<%
										Iterator<Clazz> ite = li.iterator();
										while (ite.hasNext()) {
											Clazz pes = ite.next();
									%>

									<tr>
										<td><%=pes.getGraden()%><%=pes.getClazzn()%></td>
										<td><%=pes.getMajorn()%></td>
										<td><%=pes.getCollegen()%></td>
										<td><a onclick="getFocusClazz(<%=pes.getId()%>,2)">加入班级</a></td>
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
	<script type="text/javascript" src="js/plugins/select2.min.js"></script>
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
	function findone(){
		var whats = document.getElementById("whats").value;
		var clazz = document.getElementById("clazz").value;
		var graden = document.getElementById("graden").value;
		var major = document.getElementById("major").value;
		var college = document.getElementById("college").value;
		window.location=encodeURI('chinaren-query.jsp?whats='+whats);			
	}
	
	function findclazz(){	
		var clazz = document.getElementById("clazz").value;
		var graden = document.getElementById("graden").value;
		var major = document.getElementById("major").value;
		var college = document.getElementById("college").value;
		window.location=encodeURI('chinaren-query.jsp?clazz='+clazz+'&graden='+graden+'&major='+major+'&college='+college);			

	}
	
	</script>
	<script type="text/javascript">
	function getUser(data) {
		window.location='chinaren-userother.jsp?id='+data;
	}
	</script>
	<script type="text/javascript">
	function getFocus(datas,flag) {	
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
	
	$('#major').select2();
	$('#college').select2();
</script>
</body>
</html>