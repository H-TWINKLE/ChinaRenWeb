<!DOCTYPE html>

<%@page import="com.jwj.dao.factory.ManageFactory"%>
<%@page import="com.jwj.entity.UserQues"%>
<%@page import="com.jwj.util.CommUtil"%>
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
<title>ChinaRen - 校友圈提问</title>
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
		String table = "userques";

		try {
			nummood = ManageFactory.getInstance().getOneTableCount(table, 0, "");

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
						<i class="fa fa-file-text-o"></i> 校友圈提问
					</h1>
				</div>
			</div>

			<%
				List<UserQues> list = null;
				try {
					list = ManageFactory.getInstance().findAllQues(begin, end);
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>

			<div class="row">
				<%
					for (UserQues q : list) {
				%>
				<div class="col-md-12">
					<div class="card">
						<h3 class="card-title"><%=q.getU_id().getName()%></h3>
						<h5><%=q.getTitle()%></h5>
						<div class="col-md-12"><%=q.getContent()%></div>
						<p class="mt-40 mb-20" align="right">
							<span class="label label-primary"><%=CommUtil.getInstance().stampToDate("")%></span>
						</p>
						<div id="<%=q.getQ_id()%>"></div>
						<div class="card-footer" align="right">
							<a class="btn btn-primary" onclick="adds(<%=q.getQ_id()%>)">评论</a>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<div class="row" align="right">
					<div class="col-lg-12">
						<div class="bs-component">
							<ul class="pagination">
								<li class="disabled"><a href="#">«</a></li>
								<%
									for (int x = 0; x < nummood / 10 + 1; x++) {
								%>
								<li><a
									href="chinaren-allques.jsp?begin=<%=x * 10%>&end=<%=(x + 1) * 10%>"><%=x + 1%></a></li>
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
	<!-- Javascripts-->
	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/plugins/pace.min.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
	<script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
	<script type="text/javascript" src="js/json2.js"></script>
	<script type="text/javascript" src="js/json_parse.js"></script>
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
					if (da == "true") {
						$.notify({
							title : "提示 : ",
							message : "操作成功！",
							icon : 'fa fa-check'
						}, {
							type : "info"
						});
						window.setTimeout(
								"window.location='chinaren-focus.jsp'", 1000);
					} else {
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
	<script type="text/javascript">
	
	var my=new Array();
	
	
	function adds(data) {			
		var id  = $("#"+data);
		
		var flag = false;
			
		for(j = 0; j < my.length; j++) {
		
			//console.log("data:"+data+"  my:"+j+"  value:"+my[j]);
			
			   if(my[j]==data){
				   				  			   
				   flag = true; 
				   break;
			   }else{
				   
				   flag = false;
			   }
		} ;
			
		//	console.log(data);
		//	console.log("flag:"+flag);
			
			
			if(flag){
				$.ajax({
					url : "ReplayServlet",
					type : "post",
					data : {
						"action" : "post",
						"text" : $("#adss"+data).val(),
						"flag" : 2,
						"id":data
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
									"window.location='chinaren-allques.jsp'",
									1000);								
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
			}else{
				
			my.push(data);
			
			$.ajax({
				url : "ReplayServlet",
				type : "post",
				data : {
					"action" : "post",
					"id" : data,
					"flag":1
				},
				success : function(da) {
					
					var jt = eval(da);	
					
					
					$(id).append('<div ><h4>评论:</h4>');
					
					for(var p in jt){
						$(id).append('<h5>名字：'+jt[p].u_id.name+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容：'+jt[p].content+'</h5>');
					}
					 
					$(id).append('<input class="form-control" name="adss'+data+'" id="adss'+data+'" type="text"></div><br>');
					 	
					 
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
			
		//	console.log(my.join());
			
			
			/* if(ic!=data){					 	
				ic=data;										
				 $.ajax({
						url : "ReplayServlet",
						type : "post",
						data : {
							"action" : "post",
							"id" : data,
							"flag":1
						},
						success : function(da) {
							
							var jt = eval(da);	
							
							
							$(id).append('<div ><h4>评论:</h4>');
							
							for(var p in jt){
								$(id).append('<h5>名字：'+jt[p].u_id.name+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容：'+jt[p].content+'</h5>');
							}
							 
							$(id).append('<input class="form-control" name="ad" id="ad" type="text"></div><br>');
							 	
							 
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
				 				 															
			
				}else if(ic==data){
								
				  $.ajax({
					url : "ReplayServlet",
					type : "post",
					data : {
						"action" : "post",
						"text" : $("#ad").val(),
						"flag" : 2,
						"id":data
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
									"window.location='chinaren-allques.jsp'",
									1000);								
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
			}; */
	
	/* function adds(data) {			
			var id  = $("#"+data);							
			if(ic!=data){					 	
				ic=data;										
				 $.ajax({
						url : "ReplayServlet",
						type : "post",
						data : {
							"action" : "post",
							"id" : data,
							"flag":1
						},
						success : function(da) {
							
							var jt = eval(da);	
							
							
							$(id).append('<div ><h4>评论:</h4>');
							
							for(var p in jt){
								$(id).append('<h5>名字：'+jt[p].u_id.name+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容：'+jt[p].content+'</h5>');
							}
							 
							$(id).append('<input class="form-control" name="ad" id="ad" type="text"></div><br>');
							 	
							 
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
				 				 															
			
				}else if(ic==data){
								
				  $.ajax({
					url : "ReplayServlet",
					type : "post",
					data : {
						"action" : "post",
						"text" : $("#ad").val(),
						"flag" : 2,
						"id":data
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
									"window.location='chinaren-allques.jsp'",
									1000);								
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
			}; */
			//console.log("ic"+ic+"id"+data);
												 	
		}
	</script>
</body>
</html>