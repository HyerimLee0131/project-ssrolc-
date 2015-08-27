<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>${title?default('')}</title>
<link rel="stylesheet" type="text/css" href="/public/img/css/common.css" />
<link rel="stylesheet" type="text/css" href="/public/img/css/layout_popup.css" />
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
	<div class="zip_wrap">
		<input type="hidden" id="pageNum" value="1">
		<h1>우편번호검색</h1>
		<p>찾고자 하는 주소의 "동(읍,면,동)"을 정확히 입력해 주십시오.</p>
		<p class="ex">예) 서초1동일 경우 "서초1"만 입력하시면 됩니다.</p>
		<div class="zip_search">
			<label for="addr1">동이름 입력</label>
			<input type="text" class="input-text w200" id="srchwrd" />
			<span>(읍/면동)</span>
			<a href="javascript:;" id="searchBtn" class="btn_zip_search">검색</a>
		</div>
		<div class="zip_list" id="dataList"></div>
		<!-- 페이징 -->
		<div class="paging">
			<span id="pageNavi"></span>
		</div>
		<!-- //페이징 -->
		<div class="zip_close">
			<a href="javascript:window.close()">닫기</a>
		</div>
	</div>
<script id="postTemplate" type="text/x-handlebars-template">
	<p>'{{srchwrd}}'(으)로 검색된 결과입니다. 아래 해당하는 주소를 선택해 주세요.</p>
	<ul class="clear zip_result_list">
	{{#each addressList}}
		<li><a href="javascript:$.setPost('{{zipNo}}','{{lnmAdres}}');"><span class="zip_code">[{{zipNo}}]</span><span class="addr">{{lnmAdres}}</span></a></li>
		<li><a href="javascript:$.setPost('{{zipNo}}','{{rnAdres}}');"><span class="zip_code">[{{zipNo}}]</span><span class="addr">{{rnAdres}}</span></a></li>
	{{else}}
		<li>데이터가 없습니다.</li>
	{{/each}}
	</ul>
</script>
</body>
</html>