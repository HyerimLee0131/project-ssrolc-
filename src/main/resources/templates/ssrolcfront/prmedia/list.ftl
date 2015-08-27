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
				<param name="fileName" value="mms://jei-stream.jei.com/jei/jeislc/franchise.wmv"> 
				<param name="autoStart" value="true"> 
				<param name="showControls" value="true">  
				<embed  width="564" height="409" type="video/x-ms-wmv" src="mms://jei-stream.jei.com/jei/jeislc/franchise.mp4" autoStart="true" ></embed>
				</object>
			</div>
			<!-- //플레이어 -->
			<div class="board_total">
				전체 <strong>17</strong>건
			</div>
			<div class="thumb_list">
				<ul class="clear clearfix">
					<li class="adTv_con">
						<a href="javascript:;"  class="thumb">
						<span class="img"><img src="/public/img/tv_img01.jpg" alt="스스로 러닝센터 홍보영상" /></span>
						<span class="info"><strong class="txt">학부모 만족사례</strong><span class="date">2014-01-13</span></span>
						</a>
					</li>
					<li class="adTv_con">
						<a href="javascript:;"  class="thumb">
						<span class="img"><img src="/public/img/tv_img02.jpg" alt="스스로 러닝센터 홍보영상" /></span>
						<span class="info"><strong class="txt">학부모 만족사례</strong><span class="date">2014-01-13</span></span>
						</a>
					</li>
					<li class="adTv_con">
						<a href="javascript:;"  class="thumb">
						<span class="img"><img src="/public/img/tv_img03.jpg" alt="스스로 러닝센터 홍보영상" /></span>
						<span class="info"><strong class="txt">학부모 만족사례</strong><span class="date">2014-01-13</span></span>
						</a>
					</li>
					<li class="adTv_con">
						<a href="javascript:;"  class="thumb">
						<span class="img"><img src="/public/img/tv_img04.jpg" alt="스스로 러닝센터 홍보영상" /></span>
						<span class="info"><strong class="txt">학부모 만족사례</strong><span class="date">2014-01-13</span></span>
						</a>
					</li>
					<li class="adTv_con">
						<a href="javascript:;"  class="thumb">
						<span class="img"><img src="/public/img/tv_img05.jpg" alt="스스로 러닝센터 홍보영상" /></span>
						<span class="info"><strong class="txt">학부모 만족사례</strong><span class="date">2014-01-13</span></span>
						</a>
					</li>
				</ul>
			</div>
			<div class="pagenavi">
				<a href="javascript:" class="first">첫페이지</a>
				<a href="javascript:" class="prev">이전</a>
				<a href="javascript:;">1</a>
				<strong>2</strong>
				<a href="javascript:;">3</a>
				<a href="javascript:;">4</a>
				<a href="javascript:;">5</a>
				<a href="javascript:;">6</a>
				<a href="javascript:;">7</a>
				<a href="javascript:;">8</a>
				<a href="javascript:;">9</a>
				<a href="javascript:;">10</a>
				<a href="javascript:" class="next">다음</a>
				<a href="javascript:" class="last">마지막페이지</a>
			</div>
			<div class="ad_search">
				<fieldset>
				<legend class="skip">검색</legend>
				<select name="pSearchKey" id="pSearchKey" class="input-select" title="검색분류">
					<option value="title">제목</option>
					<option value="name">글쓴이</option>
				</select>
				<input name="pSearchValue" type="text" class="input-text" id="pSearchValue" title="검색어입력창" value="">
				<input type="button" class="btn_search" id="searchBtn" value="검색" >
				</fieldset>
			</div>
		</div>
		</form>
	</div>
	<#include "/ssrolcfront/footer.ftl">