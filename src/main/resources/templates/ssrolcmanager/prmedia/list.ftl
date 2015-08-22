<#include "/ssrolcmanager/header.ftl">
<#include "/ssrolcmanager/left.ftl">
		
			
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="/ssrolcmanager/main">홈</a> &gt; </li>
						<li><a href="/ssrolcmanager/popups">부가관리</a> &gt; </li>
						<li class="current"><a href="/ssrolcmanager/prmedia">홍보영상관리</a></li>
					</ul>
				</div>
				<p class="page-title">홍보영상관리</p>
				<div class="mgt-20 search-class">
					<fieldset>
						<legend></legend>
						<p>
							<select name="searchField" id="searchField" style="width:75px;" title="">
								<option value="prTitle">제목</option>
							</select>&nbsp;&nbsp;
							<input name="searchValue" type="text" class="text" id="searchValue" style="width: 300px;" title="">
							&nbsp;<span class="button"><input type="button" id="searchBtn" title="" value="검색"></span>
							&nbsp;<span class="button"><input type="button" id="resetBtn" title="" value="초기화"></span>
							
						</p>
					</fieldset>
				</div>
				<h1 class="mgt-20"></h1>
				<p>* 총 <em class="bor-none"><span id="totalCnt">0</span></em>건</p>
				<div class="tbl-type-D">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="">
							<colgroup>
							
							<col width="5%">
							<col width="15%">
							<col width="20%">
							<col width="10%">
							<col width="15%">
							<col width="10%">					
							<col width="10%">
							<col width="10%">
							</colgroup>
							<tbody>
							  <tr>
								
								<th>NO</th>
								<th>썸네일</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>메뉴위치</th>
								<th>제작년월</th>
								<th>등록일</th>
								<th>조회수</th>
							  </tr>
							</tbody>
							<tbody id="dataList"></tbody>
					</table>
				</div>
				<!--
				<div class="btn-box float-l mgt-10">
					<span class="button btn-type-J"><a onclick="$.selectDelete();">선택삭제</a></span>						
				</div>-->
				<div class="btn-box float-r mgt-10">
					<span class="button btn-type-I"><a href="/ssrolcmanager/prmedias/new">글쓰기</a></span>
				</div>			
				<div class="paging" >
					<span id="pageNavi"></span>
				</div>
				<input name="pageNum" type="hidden" id="pageNum" title="" value="1">
			</div>	
			
			
			
		</div>
	</div>
	<!-- //container -->
<#include "/ssrolcmanager/footer.ftl">
</div>
<script id="prmediasTemplate" type="text/x-handlebars-template">
	{{#each prmedia}}
	<tr>
		<td>{{inc @index}}</td>
		<td>
			<img src="/ssrolcmanager/prmedias/thumbview/{{thumnailRealName}}/{{thumnailSize}}">
		</td>
		<td><a href='/ssrolcmanager/prmedia/{{aidx}}'>{{prTitle}}</a></td>
		<td>{{regId}}</td>
		<td>{{mediaLocation}}</td>
		<td>{{prettifyDate updateDate}}</td>
		<td>{{prettifyDate regDate}}</td>
		<td>{{hitCnt}}</td>
	</tr>
	{{else}}
	<tr>
		<td colspan="7">데이터가 없습니다.</td>
	</tr>
	{{/each}}
</script>
</body>
</html>

