<#include "/ssrolcfront/header_new.ftl">
<#include "/ssrolcfront/left_new.ftl">

	<div class="content">
		<div class="path">
			<span class="home"><a href="/">홈</a></span> &gt; <a href="#">스스로러닝센터소개</a> &gt; <a href="#">홍보영상</a>	
		</div>
		<form action="#" method="get" name="frm1">
		<div class="prmedia">
			<h3>홍보영상</h3>
			<!-- 플레이어 -->
			<div class="tv_player" id="tv_player">
				<object width="564" height="409" classid="CLSID:22D6F312-B0F6-11D0-94AB-0080C74C7E95" CODEBASE="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab" standby="Loading Microsoft?Windows? Media Player components..." type="application/x-oleobject">
					<param name="fileName" id="fileName" value="${prmediaUrl}"> 
					<param name="autoStart" value="true"> 
					<param name="showControls" value="true">  
					<embed id="mediaUrlId" name="mediaUrlId" width="564" height="409" type="application/x-mplayer2" pluginspage="http://www.microsoft.com/Windows/MediaPlayer/" src="${prmediaUrl}" autoStart="true" >
					</embed>
				</object>
			</div>
			<!-- //플레이어 -->
			<div class="board_total">
				전체 <strong><span id="totalCnt">0</span></strong>건
			</div>
			<div class="thumb_list">
				<ul class="clear clearfix" id="dataList"></ul>
			</div>
			<div class="pagenavi" id="pagenavi">
			</div>
			<input name="pageNum" type="hidden" id="pageNum" title="" value="1">
			<div class="ad_search">
				<fieldset>
				<legend class="skip">검색</legend>
				<select name="searchField" id="searchField" class="input-select" title="검색분류">
					<option value="prTitle">제목</option>
				</select>
				<input name="searchValue" type="text" class="input-text" id="searchValue" title="검색어입력창" value="">
				<input type="button" class="btn_search" id="searchBtn" value="검색" >
				</fieldset>
			</div>
		</div>
		</form>
	</div>
	
	<#include "/ssrolcfront/footer_new.ftl">
	
<script id="prmediasTemplate" type="text/x-handlebars-template">
	{{#each prmedia}}
		<li class="adTv_con">
			<a style="cursor:pointer;" onclick='$.playStart("{{aidx}}");' class="thumb">
			<span class="img"><img src="/ssrolcmanager/prmedias/thumbview/{{thumnailRealName}}/{{thumnailSize}}"  alt="스스로 러닝센터 홍보영상" /></span>
			<span class="info"><strong class="txt">{{prTitle}}</strong><span class="date">{{prettifyDate regDate}}</span></span>
			</a>
		</li>
	{{else}}
		<li class="adTv_con">데이터가 없습니다.</li>
	{{/each}}
</script>
