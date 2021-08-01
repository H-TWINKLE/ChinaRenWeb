<!DOCTYPE html>
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
<title>ChianRen - 登录</title>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
<!--if lt IE 9
    script(src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js')
    script(src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js')
    -->
</head>
<body>

	<section class="material-half-bg">
		<div class="cover"></div>
	</section>
	<section class="logini-content">
		<div class="logoi">
			<h1>ChinaRen</h1>
		</div>
		<div class="logini-box">
			<form class="logini-form" action="" method="post">
				<h3 class="logini-head">
					<i class="fa fa-lg fa-fw fa-user"></i>管理员
				</h3>
				<div class="form-group">
					<label class="control-label">邮箱</label> <input class="form-control"
						type="text" placeholder="Email" autofocus name="admin" id="admins">
				</div>
				<div class="form-group">
					<label class="control-label">密码</label> <input class="form-control"
						type="password" placeholder="Password" name="password"
						id="password">
				</div>
				<div class="form-group btn-container">
					<button type="button" class="btn btn-primary btn-block"
						id="demoNotify">
						<i class="fa fa-sign-in fa-lg fa-fw"></i>登录
					</button>
				</div>
			</form>
		</div>
	</section>
</body>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/pace.min.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
<script type="text/javascript">

	$('#demoNotify').click(
			function() {
				$.ajax({
					url : "ManageLoginServlet",
					type : "post",
					data : {
						"action" : "post",
						"admins" : $("#admins").val(),
						"password" : $("#password").val()
					},
					success : function(data) {
						if (data == "登陆成功!") {
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
			});

function btforgetpss() {
	document.getElementById("btforget").disabled = 'disabled';
	$.ajax({
		url : "ForgetPassServlet",
		type : "post",
		data : {
			"action" : "post",
			"forgetpassadmin" : $("#forgetpassadmin").val()
		},
		success : function(data) {			
				$.notify({
					title : "提示 : ",
					message : data,
					icon : 'fa fa-check'
				}, {
					type : "info"
				});
				document.getElementById("btforget").disabled = false
			
		},
		error : function() {
			$.notify({
				title : "错误 : ",
				message : "网络异常",
				icon : 'fa fa-check'
			}, {
				type : "info"
			});
			document.getElementById("btforget").disabled = false
		}
	})
}
</script>

</html>