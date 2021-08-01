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
<title>ChinaRen - 注册</title>
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
					<i class="fa fa-lg fa-fw fa-user"></i>注册
				</h3>
				<div class="form-group">
					<label class="control-label">邮箱</label> <input class="form-control"
						type="text" placeholder="Email" autofocus name="admin" id="admins">
				</div>
				<div class="form-group btn-container">
					<button class="btn btn-primary btn-block" type="button"
						id="demoNotify">
						<i class="fa fa-sign-in fa-lg fa-fw"></i>注册
					</button>
				</div>
				<div class="form-group mt-20">
					<p class="semibold-text mb-0">
						<a href="chinaren-login.html"><i
							class="fa fa-angle-left fa-fw"></i> 登录</a>
					</p>
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
	$('#demoNotify').click(function() {
		document.getElementById("demoNotify").disabled = 'disabled';
		$.ajax({
			url : "RegisterServlet",
			type : "post",
			data : {
				"action" : "post",
				"admins" : $("#admins").val()
			},
			success : function(data) {
				$.notify({
					title : "提示 : ",
					message : data,
					icon : 'fa fa-check'
				}, {
					type : "info"
				});
				document.getElementById("demoNotify").disabled = false
			},
			error : function() {
				$.notify({
					title : "错误 : ",
					message : "网络异常",
					icon : 'fa fa-check'
				}, {
					type : "info"
				});
				document.getElementById("demoNotify").disabled = false
			}
		})
	});
</script>
</html>