﻿<!-- HEADER -->
	<header class="navbar clearfix navbar-fixed-top" id="header">
		<div class="container">
				<div class="navbar-brand">
					<!-- COMPANY LOGO -->
					<a href="index.html">
						<img src="/img/logo/logo_mgj.png" alt="Min Gong Jia Logo" class="img-responsive" height="30" width="120">
					</a>
					<!-- /COMPANY LOGO -->
					<!-- SIDEBAR COLLAPSE -->
					<div id="sidebar-collapse" class="sidebar-collapse btn">
						<i class="fa fa-bars" 
							data-icon1="fa fa-bars" 
							data-icon2="fa fa-bars" ></i>
					</div>
					<!-- /SIDEBAR COLLAPSE -->
				</div>
				<ul class="nav navbar-nav pull-right">
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown user" id="header-user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img alt="" src="${currentAvatar}" />
							<span class="username">${currentUsername}</span>
							<i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/profile"><i class="fa fa-user"></i>个人中心</a></li>
							<li><a href="javascript:;" id="logoutBtn"><i class="fa fa-power-off"></i>退出</a></li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			<form action="/logout" method="post" id="logoutForm">
				<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
				<!-- END TOP NAVIGATION MENU -->
		</div>
		
	</header>
	<!--/HEADER -->
	
