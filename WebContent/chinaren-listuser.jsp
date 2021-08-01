<!DOCTYPE html>
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
<link rel="stylesheet" type="text/css" media="screen" href="css/mui.css" />
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>ChinaRen - 班级用户</title>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--if lt IE 9
    script(src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js')
    script(src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js')
    -->
</head>
<body class="sidebar-mini fixed">
	<div class="wrapper">
		<%@include file="chinaren-headernav.jsp"%>
		<%@include file="chinaren-sidenav.jsp"%>
		<div class="content-wrapper">
			<div class="page-title">
				<div>
					<h1>
						<i class="fa fa-th-list"></i> 班级用户
					</h1>
					<p>你的小伙伴都在这儿喔···</p>
				</div>
				<div>
					<ul class="breadcrumb">
						<li><a onclick="asadmin()"
							class="mui-btn mui-btn--fab mui-btn--primary">成为管理员</a></li>
						<li><a href="javascript:window.history.back();"
							class="mui-btn mui-btn--fab mui-btn--primary">返回</a></li>
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
										<th>姓名</th>
										<th>昵称</th>
										<th>电话</th>
										<th>管理员</th>
										<th>关注</th>
									</tr>
								</thead>
								<tbody>
									<%
										int flag = Integer.parseInt((String) (request.getParameter("flag")));
										List<User> list = null;

										try {
											list = UserFactory.getInstance().findClazzUser(flag);
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
										<td><a onclick="getUser(<%=pe.getId()%>)"><%=pe.getName()%></a></td>
										<td><%=pe.getNickname()%></td>
										<td><%=pe.getUsermore().getTel()%></td>
										<td>
											<% if(pe.getIsadmin()==0){ %>否<%}else{ %>是<%} %>
										</td>
										<td><a onclick="getFocus(<%=pe.getId()%>)">关注</a></td>
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
	function getUser(data) {
		window.location='chinaren-userother.jsp?id='+data;
	}
	</script>
	<script type="text/javascript">
	function getFocus(datas) {	
		$.ajax({
			url : "GetFocusServlet",
			type : "post",
			data : {
				"action" : "post",
				"id" : datas,
				"flag":1
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
		
	}</script>
	<script type="text/javascript">
	function asadmin() {	
		$.ajax({
			url : "AsAdminServlet",
			type : "post",
			data : {
				"action" : "post"				
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
		
	}</script>
</body>
</html>