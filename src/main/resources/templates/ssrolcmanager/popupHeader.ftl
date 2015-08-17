<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title?default('')}</title>
<link rel="stylesheet" type="text/css" href="/public/css/common_admin.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_popup_admin.css" />
<link rel="stylesheet" type="text/css" href="/public/css/jquery-ui.1.11.4.min.css" />
<#if headerCss?has_content>
	<#list headerCss as css>
		<link rel="stylesheet" type="text/css" href="/public/css/${css}.css" />
	</#list>
</#if>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/public/js/jquery-ui.1.11.4.min.js"></script>
<script type="text/javascript" src="/public/js/handlebars_3_0_3.min.js"></script>
<#if headerScript?has_content>
	<#list headerScript as script>
		<script type="text/javascript" src="/public/js/${script}.js"></script>
	</#list>
</#if>
</head>
<body>
