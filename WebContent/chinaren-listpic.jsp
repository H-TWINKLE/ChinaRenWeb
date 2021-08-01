<!DOCTYPE html>
<%@page import="com.jwj.util.CommUtil"%>
<%@page import="com.jwj.entity.ClazzPic"%>
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
<link rel="stylesheet" type="text/css" media="screen" href="css/mui.css" />
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/mui.css" />
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>ChinaRen - 班级相册</title>
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
			<div class="page-title">
				<div>
					<h1>
						<i class="fa fa-th-list"></i> 班级相册
					</h1>
					<p>青春与作伴···</p>
				</div>
				<div>
					<ul class="breadcrumb">
						<li><a href="javascript:window.history.back();"
							class="mui-btn mui-btn--fab mui-btn--primary">返回</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<h1 class="card-title">上传图片</h1>
						<form action="" method="post" id="form_face"
							enctype="multipart/form-data" style="width: auto">
							<input class="form-control" type="file" name="fileToUpload"
								id="fileToUpload" value="file" onchange="fileSelected();">
						</form>
					</div>
				</div>
			</div>
			<%
				List<ClazzPic> list = null;
				try {
					list = UserFactory.getInstance().findAllPic(0, 999, user.getClazz().getId());

				} catch (Exception e) {
					e.printStackTrace();
				}
			%>

			<%
				for (ClazzPic cPic : list) {
			%>
			<div class="col-md-4">
				<br> <br> <br>
				<div class="card">
					<h3 class="card-title">


						<%=cPic.getU_id().getName()%>
						<%
							if (user.getIsadmin() == 1) {
						%>
						<a><span style="float: right;" class="label label-warning"
							onclick='del(<%=cPic.getId()%>,7)'>删除</span></a>
						<%
							}
						%>
					</h3>
					<h5 class="card-title"><%=CommUtil.getInstance().stampToDate(cPic.getDates() + "")%></h5>
					<img src="upImg/<%=cPic.getPic()%>"
						style="width: 100%; height: 100%">
				</div>
			</div>
			<%
				}
			%>
		</div>

	</div>
	<div class="clearfix"></div>
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
			window.location = 'chinaren-userother.jsp?id=' + data;
		}
	</script>
	<script type="text/javascript">
		function fileSelected() {
			var formData = new FormData();
			formData.append("file", $("#fileToUpload")[0].files[0]);
			formData.append("f", 2);
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
							message : "上传成功！",
							icon : 'fa fa-check'
						}, {
							type : "info"
						});
						location.reload();
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
								message : "操作成功！",
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
</body>
</html>