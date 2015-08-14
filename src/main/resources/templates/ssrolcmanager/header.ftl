<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title?default('')}</title>
<link rel="stylesheet" type="text/css" href="/public/css/common_admin.css" />
<#if headerCss?has_content>
	<#list headerCss as css>
		<link rel="stylesheet" type="text/css" href="/public/css/${css}.css" />
	</#list>
</#if>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<#if headerScript?has_content>
	<#list headerScript as script>
		<script type="text/javascript" src="/public/js/${script}.js"></script>
	</#list>
</#if>
</head>
<body>
<div id="wrapper">
	<!-- header -->
	<div id="header">
		<div class="content">
			<h1 class="logo"><a href="/ssrolcmanager/main"><img src="/public/img/admin/common/logo_header.gif" alt="재능스스로러닝센터" /></a></h1>
			<ul class="lnb">
				<li><a href="/ssrolcmanager/disclosures"><img src="/public/img/admin/common/lnb_01_off.gif" alt="일반관리" /></a></li>
				<li><a href="/ssrolcmanager/boards/notice"><img src="/public/img/admin/common/lnb_02_off.gif" alt="게시판관리" /></a></li>
				<li><a href="/ssrolcmanager/popup"><img src="/public/img/admin/common/lnb_03_off.gif" alt="부가관리" /></a></li>
			</ul>
		</div>
	</div>
	<!-- //header -->
