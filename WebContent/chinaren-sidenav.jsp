<%@page import="com.jwj.entity.User"%>

<%
	User u = (User) session.getAttribute("admin");
	if (u == null) {
%>

<%
	response.setHeader("refresh", "0.1;URL=chinaren-login.html");
	}
%>

<%
	String img = u.getImg() + "";

	if (img == null || img.equals("")) {
		img = "https://s3.amazonaws.com/uifaces/faces/twitter/jsa/48.jpg";
	} else {
		img = "upImg/" + img;
	}
%>


<!-- Side-Nav-->
<aside class="main-sidebar hidden-print">
	<section class="sidebar">
		<div class="user-panel">
			<div class="pull-left image">
				<img class="img-circle" src="<%=img%>" alt="User Image">
			</div>
			<div class="pull-left info">
				<p><%=u.getName()%></p>
				<p class="designation"><%=u.getAuto()%></p>
			</div>
			<br> <br>
		</div>
		<!-- Sidebar Menu-->
		<ul class="sidebar-menu">
			<li class="treeview"><a href="chinaren-index.jsp"><i
					class="fa fa-dashboard"></i><span>首页</span></a></li>
			<li class="treeview"><a href="chinaren-clazz.jsp"><i
					class="fa fa-file-text"></i><span>我的班级</span></a></li>
			<li class="treeview"><a href="chinaren-user.jsp"><i
					class="fa fa-laptop"></i><span>个人信息</span></a></li>
			<li><a href="chinaren-focus.jsp"><i class="fa fa-pie-chart"></i><span>我的关注</span></a></li>
			<li class="treeview"><a href="chinaren-query.jsp"><i
					class="fa fa-edit"></i><span>查询</span></a></li>
			<li class="treeview"><a href="chinaren-allques.jsp"><i
					class="fa fa-edit"></i><span>校友圈提问</span></a></li>
			<li class="treeview"><a href="#"><i class="fa fa-th-list"></i><span>更多</span>
					<i class="fa fa-angle-right"></i></a>
				<ul class="treeview-menu">
					<li><a href="chinaren-complaint.jsp"><i
							class="fa fa-circle-o"></i> 投诉建议</a></li>
					<li><a href="chinaren-mess.jsp"><i class="fa fa-circle-o"></i>
							我的消息</a></li>
				</ul></li>
		</ul>
	</section>
</aside>