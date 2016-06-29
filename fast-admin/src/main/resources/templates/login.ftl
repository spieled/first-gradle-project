<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>快用工 | 登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<!-- STYLESHEETS --><!--[if lt IE 9]>
	<script src="js/flot/excanvas.min.js"></script>
	<script src="js/html5.js"></script>
	<script src="js/css3-mediaqueries.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="css/cloud-admin.css" >
	
	<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<!-- DATE RANGE PICKER -->
	<link rel="stylesheet" type="text/css" href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
	<!-- UNIFORM -->
	<link rel="stylesheet" type="text/css" href="js/uniform/css/uniform.default.min.css" />
	<!-- ANIMATE -->
	<link rel="stylesheet" type="text/css" href="css/animatecss/animate.min.css" />
	<!-- FONTS -->
	<link href='css/useso.css' rel='stylesheet' type='text/css'>
</head>
<body class="login">	
	<!-- PAGE -->
	<section id="page">
			<!-- HEADER -->
			<header>
				<!-- NAV-BAR -->
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div id="logo">
								<a href="index.html"><img src="/img/logo/logo_mgj.png" height="40" alt="logo name" /></a>
							</div>
						</div>
					</div>
				</div>
				<!--/NAV-BAR -->
			</header>
			<!--/HEADER -->
			<!-- LOGIN -->
			<section id="login" class="visible">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div class="login-box-plain">
								<h2 class="bigintro">登 录</h2>
								<div id="errorMsg" class="divide-40 text-error" style="text-align:center; color: #b94a48;">
								</div>
								<form role="form" id="loginForm" action="/login" method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								  <div class="form-group">
									<label for="loginUsername">用户名</label>
									<i class="fa fa-user"></i><!--<i class="fa fa-envelope"></i>-->
									<input type="tel" class="form-control" name="username" id="loginUsername"  placeholder="手机号">
								  </div>
								  <div class="form-group"> 
									<label for="loginPassword">密码</label>
									<i class="fa fa-lock"></i>
									<input type="password" class="form-control" name="password" id="loginPassword" placeholder="密码" >
								  </div>
								  <div class="form-actions">
									<label class="checkbox"> <input type="checkbox" class="uniform" id="remember-me" name="remember-me"> 记住密码</label>
									<button type="submit" class="btn btn-danger">登录</button>
								  </div>
								</form>
								<div class="login-helpers">
									<a href="#" onclick="swapScreen('forgot');return false;">忘记密码?</a> <br>
									还没有账号? <a href="#" onclick="swapScreen('register');return false;">现在注册!</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!--/LOGIN -->
			<!-- REGISTER -->
			<section id="register">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div class="login-box-plain">
								<h2 class="bigintro">注 册</h2>
								<div class="divide-40"></div>
								<form role="form" name="registerForm" action="/register">
								  <div class="form-group">
									<label for="registerUsername">用户名</label>
									<i class="fa fa-user"></i>
									<input type="tel" class="form-control" name="username" id="registerUsername" placeholder="手机号" >
								  </div>
								  <div class="form-group">
									<label for="registerPassword">密码</label>
									<i class="fa fa-lock"></i>
									<input type="password" class="form-control" name="password" id="registerPassword" placeholder="密码">
								  </div>
								  <div class="form-group"> 
									<label for="registerPassword2">确认密码</label>
									<i class="fa fa-check-square-o"></i>
									<input type="password" class="form-control" name="password2" id="registerPassword2" placeholder="确认密码">
								  </div>
								  <div class="form-actions">
									<label class="checkbox"> <input type="checkbox" name="agree" class="uniform" value="1">我同意 <a href="#">服务条款</a> and <a href="#">隐私协议</a></label>
									<button type="submit" class="btn btn-success">注 册</button>
								  </div>
								</form>
								<div class="login-helpers">
									<a href="#" onclick="swapScreen('login');return false;"> 直 接 登 录</a> <br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!--/REGISTER -->
			<!-- FORGOT PASSWORD -->
			<section id="forgot">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div class="login-box-plain">
								<h2 class="bigintro">重 置 密 码</h2>
								<div class="divide-40"></div>
								<form role="form" id="forgotForm" action="/forgot/verify">
								  <div class="form-group">
									<label for="forgotUsername">手机号</label>
									<i class="fa fa-envelope"></i>
									<input type="tel" class="form-control" name="username" id="forgotUsername" placeholder="输入注册手机号">
								  </div>
									<div class="form-group">
										<label for="forgotUsername">验证码</label>
										<i class="fa fa-envelope"></i>
										<div class="input-group">
											<input type="text" class="form-control" name="verifyCode" id="forgotVerifyCode" placeholder="输入验证码">
											<span class="input-group-btn">
												<#--onclick="swapScreen('reset'); return false;"-->
												<button class="btn btn-primary" type="button" >
												发送短信验证码
												</button>
											</span>
										</div>
									</div>
								  <div class="form-actions">
									<button type="submit" class="btn btn-info">验证</button>
								  </div>
								</form>
								<div class="login-helpers">
									<a href="#" onclick="swapScreen('login');return false;">直 接 登 录</a> <br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- FORGOT PASSWORD -->
			<!-- RESET PASSWORD -->
			<section id="reset">
				<div class="container">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<div class="login-box-plain">
								<h2 class="bigintro">重 置 密 码</h2>
								<div class="divide-40"></div>
								<form role="form" id="resetForm" action="user/password/reset">
									<div class="form-group">
										<label for="registerPassword">新密码</label>
										<i class="fa fa-lock"></i>
										<input type="password" class="form-control" name="password" id="resetPassword" placeholder="新密码" >
									</div>
									<div class="form-group">
										<label for="resetPassword2">确认密码</label>
										<i class="fa fa-check-square-o"></i>
										<input type="password" class="form-control" name="password2" id="resetPassword2" placeholder="确认密码" >
									</div>
									<div class="form-actions">
										<button type="submit" class="btn btn-info">重置密码</button>
									</div>
								</form>
								<div class="login-helpers">
									<a href="#" onclick="swapScreen('login');return false;">直 接 登 录</a> <br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- FORGOT PASSWORD -->
	</section>
	<!--/PAGE -->
	<!-- JAVASCRIPTS -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- JQUERY -->
	<script src="js/jquery/jquery-2.0.3.min.js"></script>
	<!-- JQUERY UI-->
	<script src="js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
	<!-- BOOTSTRAP -->
	<script src="bootstrap-dist/js/bootstrap.min.js"></script>
    <!-- JQUERY FORM -->
    <script src="js/jquery-form/jquery.form.js"></script>
	
	
	<!-- UNIFORM -->
	<script type="text/javascript" src="js/uniform/jquery.uniform.min.js"></script>
    <!-- COOKIE -->
    <script type="text/javascript" src="js/jQuery-Cookie/jquery.cookie.min.js"></script>
	<!-- CUSTOM SCRIPT -->
	<script src="js/script.js"></script>
	<script>
		jQuery(document).ready(function() {		
			App.setPage("login");  //Set current page
			App.init(); //Initialise plugins and elements
		});
	</script>
	<script type="text/javascript">
		function swapScreen(id) {
			jQuery('.visible').removeClass('visible animated fadeInUp');
			jQuery('#'+id).addClass('visible animated fadeInUp');
		}

		/* 登录 */
		/*$('#loginForm').ajaxForm({
			success: function(response) {
				window.location.href='/index';
			}
		});*/
		/* 注册 */
		$('#registerForm').ajaxForm({
			success: function(response) {
				window.location.href = '/login';
			}
		});
		/* 忘记密码验证手机号 */
		$('#forgotForm').ajaxForm({
			success: function (response) {
				swapScreen('reset')
			}
		});
		/* 忘记密码重置密码 */
		$('#resetForm').ajaxForm({
			success: function (response) {
				window.location.href = '/login';
			}
		});
		var urlParams = $.getUrlParams();
		$.each(urlParams, function(index, item) {
			if (item == 'error') {
				$('#errorMsg').append('<div>用户名密码不正确</div>');
			}
            if (item == 'logout') {
                $('#errorMsg').append('<div>您已成功退出</div>');
            }
		})
	</script>
	<!-- /JAVASCRIPTS -->
</body>
</html>