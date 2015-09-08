<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>${title?default('')}</title>
	<link rel="stylesheet" href="/public/css/style.css" />
	<link rel="stylesheet" type="text/css" href="/public/css/jquery-ui.1.11.4.min.css" />
	<#if headerCss?has_content>
		<#list headerCss as css>
			<link rel="stylesheet" type="text/css" href="/public/css/${css}.css" />
		</#list>
	</#if>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="/public/js/jquery-ui.1.11.4.min.js"></script>
	<script type="text/javascript" src="/public/js/common.js"></script>
	<script type="text/javascript" src="/public/js/handlebars_3_0_3.min.js"></script>
	<#if headerScript?has_content>
		<#list headerScript as script>
			<script type="text/javascript" src="/public/js/${script}.js"></script>
		</#list>
	</#if>
	
</head>
<body>
	<div class="wrap">
		<!-- header -->
		<div class="header_wrap">
			<div class="header" id="header">
				<div class="logo">
				<h1><a href="/">스스로러닝센터</a></h1>
				</div>
				<div class="main_nav">
					<h2>메인메뉴</h2>
					<ul class="clear clearfix">
						<li><a href="/ssrolcfront/centersearch">스스로러닝센터</a></li>
						<li><a href="#">학습시스템</a></li>
						<li><a href="/ssrolcfront/franchise/search">가맹안내</a></li>
						<li><a href="/ssrolcfront/boards/notice">커뮤니티</a></li>
						<li><a href="/ssrolcmanager/login">로그인</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- //header -->
