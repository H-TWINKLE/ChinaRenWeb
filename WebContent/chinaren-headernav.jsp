
<!-- Navbar-->

<%@page import="com.jwj.entity.User"%>
<%@page import="com.jwj.dao.factory.UserFactory"%>
<%@page import="com.jwj.entity.Tip"%>
<%@page import="java.util.List"%>

<%
	User uu = (User) session.getAttribute("admin");
%>


<%
	List<Tip> l = null;
	try {
		l = UserFactory.getInstance().findOneTip(uu.getId());
	} catch (Exception e) {

	}
%>

<header class="main-header hidden-print">
	<a class="logo" href="index.html">ChinaRen</a>
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a class="sidebar-toggle" href="#" data-toggle="offcanvas"></a>
		<!-- Navbar Right Menu-->
		<div class="navbar-custom-menu">
			<ul class="top-nav">
				<!--Notification Menu-->
				<li class="dropdown notification-menu"><a
					class="dropdown-toggle" href="#" data-toggle="dropdown"
					aria-expanded="false"><i class="fa fa-bell-o fa-lg"></i></a>
					<ul class="dropdown-menu">
						<li class="not-head">你有<%=l.size()%>条新消息.
						</li>

						<%
							for (Tip y : l) {
						%>
						<li><a class="media" href="javascript:;"><span
								class="media-left media-icon"><span
									class="fa-stack fa-lg"><i
										class="fa fa-circle fa-stack-2x text-primary"></i><i
										class="fa fa-envelope fa-stack-1x fa-inverse"></i></span></span>
								<div class="media-body">
									<span class="block"><%=y.getContent()%></span>
								</div></a></li>

						<%
							}
						%>
						<li class="not-footer"><a href="chinaren-mess.jsp">查看所有消息.</a></li>
					</ul></li>
				<!-- User Menu-->
				<li class="dropdown"><a class="dropdown-toggle" href="#"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><i class="fa fa-user fa-lg"></i></a>
					<ul class="dropdown-menu settings-menu">
						<li><a href="chinaren-user.jsp"><i
								class="fa fa-user fa-lg"></i> 个人信息</a></li>
						<li><a href="chinaren-login.jsp"><i
								class="fa fa-cog fa-lg"></i> 忘记密码</a></li>
						<li><a href="chinaren-login.jsp"><i
								class="fa fa-sign-out fa-lg"></i> 注销</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
</header>
